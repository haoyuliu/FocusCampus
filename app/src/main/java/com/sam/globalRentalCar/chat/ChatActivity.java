package com.sam.globalRentalCar.chat;

import android.content.Intent;
import android.util.Log;
import android.view.View;

import com.hyphenate.chat.EMMessage;
import com.hyphenate.easeui.ui.EaseChatFragment;
import com.hyphenate.easeui.widget.chatrow.EaseCustomChatRowProvider;
import com.sam.globalRentalCar.R;
import com.sam.globalRentalCar.common.MyActivity;
import com.sam.globalRentalCar.constant.Constant;
import com.sam.globalRentalCar.utils.SPUtils;

/**
 * 聊天详情页面
 */
public class ChatActivity extends MyActivity implements EaseChatFragment.EaseChatFragmentHelper {

    public static final String TAG = "ChatActivity";
    EaseChatFragment chatFragment;
    String hxId;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_chat;
    }

    @Override
    protected void initView() {
        //创建一个回话的Fragment
        chatFragment = new EaseChatFragment();
        Intent intent = getIntent();
        hxId = intent.getStringExtra("userId");
        // pass parameters to chat fragment
        chatFragment.setArguments(getIntent().getExtras());
        // 替换Fragment
        getSupportFragmentManager().beginTransaction().replace(R.id.layout_chat, chatFragment).commit();
        chatFragment.setChatFragmentHelper(this);
    }

    @Override
    protected void initData() {
        chatFragment.setChatFragmentHelper(new EaseChatFragment.EaseChatFragmentHelper() {
            @Override
            public void onSetMessageAttributes(EMMessage message) {

            }

            @Override
            public void onEnterToChatDetails() {
                // 进入群组详情页面
                Intent intent = new Intent(ChatActivity.this, GroupDetailActivity.class);
                intent.putExtra(Constant.GROUP_ID, hxId);
                startActivity(intent);
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
        });
    }

    @Override
    protected boolean isStatusBarEnabled() {
        return false;
    }


    @Override
    public void onSetMessageAttributes(EMMessage message) {
        //获取当前用户的信息
//        String image = "https://image.baidu.com/search/detail?ct=503316480&z=0&ipn=d&word=%E5%9B%BE%E7%89%87&hs=2&pn=0&spn=0&di=5830&pi=0&rn=1&tn=baiduimagedetail&is=0%2C0&ie=utf-8&oe=utf-8&cl=2&lm=-1&cs=3363295869%2C2467511306&os=892371676%2C71334739&simid=4203536407%2C592943110&adpicid=0&lpn=0&ln=30&fr=ala&fm=&sme=&cg=&bdtype=0&oriquery=%E5%9B%BE%E7%89%87&objurl=https%3A%2F%2Fgimg2.baidu.com%2Fimage_search%2Fsrc%3Dhttp%3A%2F%2Fa0.att.hudong.com%2F30%2F29%2F01300000201438121627296084016.jpg%26refer%3Dhttp%3A%2F%2Fa0.att.hudong.com%26app%3D2002%26size%3Df9999%2C10000%26q%3Da80%26n%3D0%26g%3D0n%26fmt%3Djpeg%3Fsec%3D1613745064%26t%3Dc8d8cdc77fa6e237c36d42838fc224a5&fromurl=ippr_z2C%24qAzdH3FAzdH3Fooo_z%26e3Bfhyvg8_z%26e3Bv54AzdH3F4AzdH3Fetjo_z%26e3Brir%3Fwt1%3Dmb9l9&gsm=1&islist=&querylist=";
//        String userId = SPUtils.getInstance(ChatActivity.this).getString("UserId");
//        String nickName = SPUtils.getInstance(ChatActivity.this).getString("NickName");
//        String headPic = SPUtils.getInstance(ChatActivity.this).getString("HeadImage");
//        message.setAttribute("nickName", nickName);
//       // message.setAttribute("headPic", headPic);
//        message.setAttribute("headPic", image);
//        message.setAttribute("userId", userId);
        //Log.d("用户信息", "onSetMessageAttributes" + "userId--->" + userId + "NickName--->" + nickName + "headPic--->" + headPic);
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
