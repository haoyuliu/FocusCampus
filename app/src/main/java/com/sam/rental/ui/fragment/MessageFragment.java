package com.sam.rental.ui.fragment;

import android.content.Intent;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.hyphenate.EMCallBack;
import com.hyphenate.EMError;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.easeui.EaseConstant;
import com.hyphenate.exceptions.HyphenateException;
import com.sam.rental.R;
import com.sam.rental.aop.SingleClick;
import com.sam.rental.common.MyFragment;
import com.sam.rental.http.glide.GlideApp;
import com.sam.rental.ui.activity.HomeActivity;
import com.sam.rental.ui.activity.PhotoActivity;
import com.hjq.permissions.OnPermission;
import com.hjq.permissions.Permission;
import com.hjq.permissions.XXPermissions;

import java.util.List;

import butterknife.BindView;

import static com.hjq.http.EasyUtils.runOnUiThread;

/**
 * desc   : 消息页面
 */
public final class MessageFragment extends MyFragment<HomeActivity> {
    @BindView(R.id.name)
    EditText userName;

    @BindView(R.id.password)
    EditText userPwd;
    @BindView(R.id.register)
    Button mButtonRegister;

    @BindView(R.id.login)
    Button mButtonLogin;

    @BindView(R.id.login_out)
    Button mButtonLoginOut;

    public static MessageFragment newInstance() {
        return new MessageFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_message;
    }

    @Override
    protected void initView() {
        setOnClickListener(R.id.register, R.id.login, R.id.login_out);
    }

    @Override
    protected void initData() {

    }

    @Override
    public boolean isStatusBarEnabled() {
        // 使用沉浸式状态栏
        return !super.isStatusBarEnabled();
    }

    @SingleClick
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.register:
//注册
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            EMClient.getInstance().createAccount(userName.getText().toString().trim(), userPwd.getText().toString().trim());
                            toast("注册成功，用户名是:" + userName.getText().toString() + "  快开始聊天吧");
                        } catch (final HyphenateException e) {
                            e.printStackTrace();
                            /**
                             * 关于错误码可以参考官方api详细说明
                             * http://www.easemob.com/apidoc/android/chat3.0/classcom_1_1hyphenate_1_1_e_m_error.html
                             */
                            int errorCode = e.getErrorCode();
                            String message = e.getMessage();
                            switch (errorCode) {
                                case EMError.NETWORK_ERROR:
                                    toast("网络异常，请检查网络！ code: " + errorCode + "，message: " + message);
                                    break;
                                case EMError.USER_ALREADY_EXIST:
                                    toast("用户名已存在,请尝试登录！ code: " + errorCode + "，message: " + message);
                                    break;
                                case EMError.USER_ALREADY_LOGIN:
                                    toast("用户已登录！ code: " + errorCode + "，message: " + message);
                                    break;
                                case EMError.USER_AUTHENTICATION_FAILED:
                                    toast("用户id或密码错误！ code: " + errorCode + "，message: " + message);
                                    break;
                                case EMError.SERVER_UNKNOWN_ERROR:
                                    toast("服务器位置错误！ code: " + errorCode + "，message: " + message);
                                    break;
                                case EMError.USER_REG_FAILED:
                                    toast("注册失败！ code: " + errorCode + "，message: " + message);
                                    break;
                                default:
                                    toast("ml_sign_up_failed  code: " + errorCode + "，message: " + message);
                                    break;
                            }
                        }
                    }
                }).start();
                break;
            case R.id.login:
//登录
                EMClient.getInstance().login(userName.getText().toString(), userPwd.getText().toString(), new EMCallBack() {
                    @Override
                    public void onSuccess() {
                        // 加载所有群组到内存，如果使用了群组的话
//                EMClient.getInstance().groupManager().loadAllGroups();
                        // 加载所有会话到内存
                        EMClient.getInstance().chatManager().loadAllConversations();
                        toast("登录，成功开始聊天吧");
                        Intent intent = new Intent(getActivity(), ChatActivity.class);
                        intent.putExtra(EaseConstant.EXTRA_USER_ID, "111");
                        intent.putExtra(EaseConstant.EXTRA_CHAT_TYPE, EMMessage.ChatType.Chat);
                        startActivity(intent);
                    }

                    @Override
                    public void onError(final int i, final String s) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                toast("登录失败 code: " + i + ",message: " + s);
                                switch (i) {
                                    case EMError.NETWORK_ERROR:
                                        toast("网络异常，请检查网络！ code: " + i + "，message: " + s);
                                        break;
                                    case EMError.INVALID_USER_NAME:
                                        toast("无效用户名！ code: " + i + "，message: " + s);
                                        break;
                                    case EMError.INVALID_PASSWORD:
                                        toast("用户密码不正确！ code: " + i + "，message: " + s);
                                        break;
                                    case EMError.USER_AUTHENTICATION_FAILED:
                                        toast("用户名或密码不正确！ code: " + i + "，message: " + s);
                                        break;
                                    case EMError.USER_NOT_FOUND:
                                        toast("用户不存在！ code: " + i + "，message: " + s);
                                        break;
                                    case EMError.SERVER_NOT_REACHABLE:
                                        toast("无法连接到服务器！ code: " + i + "，message: " + s);
                                        break;
                                    case EMError.SERVER_BUSY:
                                        toast("服务器繁忙，请稍后.... code: " + i + "，message: " + s);
                                        break;
                                    case EMError.SERVER_TIMEOUT:
                                        toast("等待服务器响应超时！ code: " + i + "，message: " + s);
                                        break;
                                    case EMError.SERVER_UNKNOWN_ERROR:
                                        toast("未知服务器错误！ code: " + i + "，message: " + s);
                                        break;
                                    case EMError.USER_ALREADY_LOGIN:
                                        toast("用户已登录！ code: " + i + "，message: " + s);
                                        break;

                                }
                            }
                        });


                    }

                    @Override
                    public void onProgress(int i, String s) {

                    }
                });
                break;
            case R.id.login_out:
                EMClient.getInstance().logout(false, new EMCallBack() {
                    @Override
                    public void onSuccess() {
                        toast("退出成功");
                    }

                    @Override
                    public void onError(int i, String s) {

                    }

                    @Override
                    public void onProgress(int i, String s) {

                    }
                });
                break;
            default:
                break;
        }
    }
}