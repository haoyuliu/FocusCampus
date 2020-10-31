package com.sam.rentalcar.chat;

import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.hjq.bar.OnTitleBarListener;
import com.hjq.bar.TitleBar;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.easeui.EaseConstant;
import com.hyphenate.easeui.ui.EaseConversationListFragment;
import com.sam.rentalcar.R;
import com.sam.rentalcar.ui.activity.ChatActivity;

/**
 * desc   : 消息页面，顶部增加一个添加群聊
 */
public class MessageFragment extends EaseConversationListFragment {
    @Override
    protected void initView() {
        super.initView();
        // 点击的时候跳转到回话详情页面
        setConversationListItemClickListener(conversation -> {
            Intent intent = new Intent(getActivity(), ChatActivity.class);
            // 设置参数，跳转到回话列表
            intent.putExtra(EaseConstant.EXTRA_USER_ID, conversation.conversationId());
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
                //创建群组
                Toast.makeText(getActivity(), "开始创建群组", Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    protected void setUpView() {
        super.setUpView();
    }
}