package com.sam.rental.ui.activity;

import android.util.Log;
import android.view.View;

import com.hyphenate.chat.EMMessage;
import com.hyphenate.easeui.ui.EaseChatFragment;
import com.hyphenate.easeui.widget.chatrow.EaseCustomChatRowProvider;
import com.sam.rental.R;
import com.sam.rental.common.MyActivity;

/**
 * 聊天详情页面
 */
public class ChatActivity extends MyActivity implements EaseChatFragment.EaseChatFragmentHelper {

    public static final String TAG = "ChatActivity";

    @Override
    protected int getLayoutId() {
        return R.layout.activity_chat;
    }

    @Override
    protected void initView() {
        //use EaseChatFratFragment
        EaseChatFragment chatFragment = new EaseChatFragment();
        chatFragment.setChatFragmentHelper(this);
        // pass parameters to chat fragment
        chatFragment.setArguments(getIntent().getExtras());
        getSupportFragmentManager().beginTransaction().add(R.id.layout_chat, chatFragment).commit();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected boolean isStatusBarEnabled() {
        return false;
    }

    @Override
    public void onSetMessageAttributes(EMMessage message) {

        //设置要发送扩展消息用户昵称
        message.setAttribute("name", "nike");
        //设置要发送扩展消息用户头像
        message.setAttribute("image", "https://c-ssl.duitang.com/uploads/item/202006/08/20200608163837_GeLkP.thumb.1000_0.jpeg");
        Log.d(TAG, "设置扩展属性成功" + message);
    }

    @Override
    public void onEnterToChatDetails() {

    }

    @Override
    public void onAvatarClick(String username) {
        Log.d(TAG, "userNmae" + username);
    }

    @Override
    public void onAvatarLongClick(String username) {

    }

    @Override
    public boolean onMessageBubbleClick(EMMessage message) {
        return false;
    }

    @Override
    public void onMessageBubbleLongClick(EMMessage message) {

    }

    @Override
    public boolean onExtendMenuItemClick(int itemId, View view) {
        return false;
    }

    @Override
    public EaseCustomChatRowProvider onSetCustomChatRowProvider() {
        return null;
    }
}
