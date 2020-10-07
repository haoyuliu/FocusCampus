package com.sam.rentalcar.ui.fragment;

import android.content.Intent;

import com.hyphenate.chat.EMConversation;
import com.hyphenate.easeui.EaseConstant;
import com.hyphenate.easeui.ui.EaseConversationListFragment;
import com.sam.rentalcar.ui.activity.ChatActivity;

/**
 * desc   : 消息页面
 */
public class MessageFragment extends EaseConversationListFragment {
    @Override
    protected void initView() {
        super.initView();
        // 点击的时候跳转到回话详情页面
        setConversationListItemClickListener(new EaseConversationListItemClickListener() {
            @Override
            public void onListItemClicked(EMConversation conversation) {
                Intent intent = new Intent(getActivity(), ChatActivity.class);
                // 设置参数
                intent.putExtra(EaseConstant.EXTRA_USER_ID,conversation.conversationId());
                startActivity(intent);
            }
        });
    }

    @Override
    protected void setUpView() {
        super.setUpView();
    }
}