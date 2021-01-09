package com.sam.globalRentalCar.chat;

import android.content.Intent;
import android.view.View;

import com.hyphenate.chat.EMMessage;
import com.hyphenate.easeui.ui.EaseChatFragment;
import com.hyphenate.easeui.widget.chatrow.EaseCustomChatRowProvider;
import com.sam.globalRentalCar.R;
import com.sam.globalRentalCar.common.MyActivity;
import com.sam.globalRentalCar.utils.SPUtils;

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
        //创建一个回话的Fragment
        EaseChatFragment chatFragment = new EaseChatFragment();
        Intent intent = getIntent();
        String hxId = intent.getStringExtra("userId");
        // pass parameters to chat fragment
        chatFragment.setArguments(getIntent().getExtras());
        // 替换Fragment
        getSupportFragmentManager().beginTransaction().replace(R.id.layout_chat, chatFragment).commit();
        chatFragment.setChatFragmentHelper(this);
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
        //获取当前用户的信息
        String userId = SPUtils.getInstance(ChatActivity.this).getString("UserId");
        String nickName = SPUtils.getInstance(ChatActivity.this).getString("NickName");
        String headPic = SPUtils.getInstance(ChatActivity.this).getString("HeadImage");
        message.setAttribute("nickName", nickName);
        message.setAttribute("headPic", headPic);
        message.setAttribute("userId", userId);
    }

    @Override
    public void onEnterToChatDetails() {

    }

    @Override
    public void onAvatarClick(String username) {

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
