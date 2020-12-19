package com.sam.globalRentalCar.chat;

import android.content.Intent;
import android.view.View;

import com.hjq.bar.OnTitleBarListener;
import com.hjq.bar.TitleBar;
import com.hyphenate.EMMessageListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.easeui.EaseConstant;
import com.hyphenate.easeui.EaseUI;
import com.hyphenate.easeui.ui.EaseConversationListFragment;
import com.sam.globalRentalCar.R;
import com.sam.globalRentalCar.ui.activity.ChatActivity;

import java.util.List;

import static com.hjq.http.EasyUtils.runOnUiThread;

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
    }


    @Override
    protected void setUpView() {
        super.setUpView();
    }
}