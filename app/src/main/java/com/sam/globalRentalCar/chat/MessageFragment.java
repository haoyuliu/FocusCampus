package com.sam.globalRentalCar.chat;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.hjq.bar.OnTitleBarListener;
import com.hjq.bar.TitleBar;
import com.hyphenate.EMMessageListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.easeui.EaseConstant;
import com.hyphenate.easeui.EaseUI;
import com.hyphenate.easeui.domain.EaseUser;
import com.hyphenate.easeui.ui.EaseConversationListFragment;
import com.hyphenate.exceptions.HyphenateException;
import com.sam.globalRentalCar.R;
import com.sam.globalRentalCar.ui.activity.LoginActivity;
import com.sam.globalRentalCar.utils.SPUtils;

import java.util.List;

/**
 * desc   : 消息页面，顶部增加一个添加群聊
 */
public class MessageFragment extends EaseConversationListFragment {
    @Override
    protected void initView() {
        super.initView();
        // 监听消息回话
        EMClient.getInstance().chatManager().addMessageListener(new EMMessageListener() {
            @Override
            public void onMessageReceived(List<EMMessage> list) {
                Log.d("用户信息", "onMessageReceived" + "list--->" + list.toString());
                for (EMMessage emMessage : list) {
                    try {
                        SPUtils.getInstance(getContext()).put("OtherHeadImage", emMessage.getStringAttribute("NickName", ""));
                        SPUtils.getInstance(getContext()).put("OtherNickName", emMessage.getStringAttribute("HeadImage", ""));
                        SPUtils.getInstance(getContext()).put("OtherUserId", emMessage.getStringAttribute("UserId") + "");
                        EaseUser easeUI = new EaseUser(emMessage.getStringAttribute("UserId") + "");
                        easeUI.setNickname(emMessage.getStringAttribute("NickName", ""));
                        easeUI.setAvatar(emMessage.getStringAttribute("HeadImage", ""));
                    } catch (HyphenateException e) {
                        e.printStackTrace();
                    }

                }

                //设置数据
                EaseUI.getInstance().getNotifier().notify(list);
                //刷新页面
                refresh();

            }

            @Override
            public void onCmdMessageReceived(List<EMMessage> list) {

            }

            @Override
            public void onMessageRead(List<EMMessage> list) {

            }

            @Override
            public void onMessageDelivered(List<EMMessage> list) {

            }

            @Override
            public void onMessageRecalled(List<EMMessage> list) {

            }

            @Override
            public void onMessageChanged(EMMessage emMessage, Object o) {

            }
        });
        // 点击的时候跳转到回话详情页面
        setConversationListItemClickListener(conversation -> {
            Intent intent = new Intent(getActivity(), ChatActivity.class);
            // 设置参数，跳转到回话列表
            intent.putExtra(EaseConstant.EXTRA_USER_ID, conversation.conversationId());
            // 回话中有单聊和群聊，需要自己设置
            //是否是群聊
            if (conversation.getType() == EMConversation.EMConversationType.GroupChat) {
                intent.putExtra(EaseConstant.EXTRA_CHAT_TYPE, EaseConstant.CHATTYPE_GROUP);
            }
            startActivity(intent);
        });

        TitleBar titleBar = getActivity().findViewById(R.id.title_bar_sam);
        titleBar.setOnTitleBarListener(new OnTitleBarListener() {
            @Override
            public void onLeftClick(View v) {

            }

            @Override
            public void onTitleClick(View v) {

            }

            @Override
            public void onRightClick(View v) {
                //进入选择好友列表
                ChoiceFriendFragment choiceFriendFragment = new ChoiceFriendFragment();
                choiceFriendFragment.show(getFragmentManager(), "tag");
            }
        });

        // 搜索
        TextView editTextSearch = getActivity().findViewById(R.id.query);
        editTextSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取焦点
                Intent searchIntent = new Intent(getContext(), SearchActivity.class);
                startActivity(searchIntent);
            }
        });
    }


    @Override
    protected void setUpView() {
        super.setUpView();
    }
}