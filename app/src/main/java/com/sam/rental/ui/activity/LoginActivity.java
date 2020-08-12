package com.sam.rental.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;

import com.hjq.http.EasyConfig;
import com.hjq.http.EasyHttp;
import com.hjq.http.listener.HttpCallback;
import com.sam.rental.R;
import com.sam.rental.aop.DebugLog;
import com.sam.rental.aop.SingleClick;
import com.sam.rental.common.MyActivity;
import com.sam.rental.helper.InputTextHelper;
import com.sam.rental.http.model.HttpData;
import com.sam.rental.http.net.RetrofitClient;
import com.sam.rental.http.request.GetCodeApi;
import com.sam.rental.http.request.LoginRequestBean;
import com.sam.rental.http.response.LoginBean;
import com.sam.rental.other.IntentKey;
import com.sam.rental.utils.SPUtils;
import com.sam.rental.wxapi.WXEntryActivity;
import com.sam.umeng.Platform;
import com.sam.umeng.UmengClient;
import com.sam.umeng.UmengLogin;
import com.sam.widget.view.CountdownView;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * desc   : 登录界面
 */
public final class LoginActivity extends MyActivity
        implements UmengLogin.OnLoginListener {

    @BindView(R.id.et_user_phone)
    EditText mPhoneView;
    @BindView(R.id.et_user_code)
    EditText mCodeView;
    @BindView(R.id.cv_password_forget_countdown)
    CountdownView mCountdownView;
    @BindView(R.id.btn_login_commit)
    Button mCommitView;

    @BindView(R.id.v_login_blank)
    View mBlankView;

    @BindView(R.id.ll_login_other)
    View mOtherView;
    @BindView(R.id.iv_login_qq)
    View mQQView;
    @BindView(R.id.iv_login_wx)
    View mWeChatView;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {

        setOnClickListener(R.id.cv_password_forget_countdown, R.id.btn_login_commit, R.id.iv_login_qq, R.id.iv_login_wx);
    }

    @Override
    protected void initData() {
        // 判断用户当前有没有安装 QQ
        if (!UmengClient.isAppInstalled(this, Platform.QQ)) {
            mQQView.setVisibility(View.GONE);
        }

        // 判断用户当前有没有安装微信
        if (!UmengClient.isAppInstalled(this, Platform.WECHAT)) {
            mWeChatView.setVisibility(View.GONE);
        }

        // 如果这两个都没有安装就隐藏提示
        if (mQQView.getVisibility() == View.GONE && mWeChatView.getVisibility() == View.GONE) {
            mOtherView.setVisibility(View.GONE);
        }

    }

    @Override
    public void onLeftClick(View v) {
        // 跳转到主界面
        startActivity(HomeActivity.class);

    }

    @SingleClick
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //登录
            case R.id.btn_login_commit:
                if (mPhoneView.getText().toString().length() != 11) {
                    toast(R.string.common_phone_input_error);
                    return;
                }
                LoginRequestBean loginRequestBean = new LoginRequestBean();
                loginRequestBean.setIp("1111");
                loginRequestBean.setPhone(mPhoneView.getText().toString());
                loginRequestBean.setRequestId("2222");
                loginRequestBean.setVerifcationCode("2222");
                RetrofitClient.getRetrofitService().loadLogin(loginRequestBean)
                        .enqueue(new Callback<LoginBean>() {
                            @Override
                            public void onResponse(Call<LoginBean> call, Response<LoginBean> response) {
                                toast("登录成功");
                                SPUtils.getInstance(LoginActivity.this).put("token", response.body().getData().getToken());
                                toast("保存成功"+response.body().getData().getToken());
                                startActivity(HomeActivity.class);
                                finish();
                            }

                            @Override
                            public void onFailure(Call<LoginBean> call, Throwable t) {
                                toast("登录失败"+ t.getMessage().toString());
                            }
                        });

                break;
            case R.id.cv_password_forget_countdown:
                if (mPhoneView.getText().toString().length() != 11) {
                    toast(R.string.common_phone_input_error);
                    return;
                }

                if (true) {
                    toast(R.string.common_code_send_hint);
                    mCountdownView.start();
                    return;
                }

                // 获取验证码
              /*  EasyHttp.post(this)
                        .api(new GetCodeApi()
                                .setPhone(mPhoneView.getText().toString()))
                        .request(new HttpCallback<HttpData<Void>>(this) {

                            @Override
                            public void onSucceed(HttpData<Void> data) {
                                toast(R.string.common_code_send_hint);
                                mCountdownView.start();
                            }
                        });*/
                break;
            case R.id.iv_login_qq:
            case R.id.iv_login_wx:
                toast("记得改好第三方 AppID 和 AppKey，否则会调不起来哦");
                Platform platform;
                switch (v.getId()) {
                    case R.id.iv_login_qq:
                        platform = Platform.QQ;
                        break;
                    case R.id.iv_login_wx:
                        platform = Platform.WECHAT;
                        toast("也别忘了改微信 " + WXEntryActivity.class.getSimpleName() + " 类所在的包名哦");
                        break;
                    default:
                        throw new IllegalStateException("are you ok?");
                }
                UmengClient.login(this, platform, this);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 友盟登录回调
        UmengClient.onActivityResult(this, requestCode, resultCode, data);
    }

    /**
     * {@link UmengLogin.OnLoginListener}
     */

    /**
     * 授权成功的回调
     *
     * @param platform 平台名称
     * @param data     用户资料返回
     */
    @Override
    public void onSucceed(Platform platform, UmengLogin.LoginData data) {
        // 判断第三方登录的平台
        switch (platform) {
            case QQ:
                break;
            case WECHAT:
                break;
            default:
                break;
        }
        toast("昵称：" + data.getName() + "\n" + "性别：" + data.getSex());
        toast("id：" + data.getId());
        toast("token：" + data.getToken());
    }

    /**
     * 授权失败的回调
     *
     * @param platform 平台名称
     * @param t        错误原因
     */
    @Override
    public void onError(Platform platform, Throwable t) {
        toast("第三方登录出错：" + t.getMessage());
    }

    /**
     * 授权取消的回调
     *
     * @param platform 平台名称
     */
    @Override
    public void onCancel(Platform platform) {
        toast("取消第三方登录");
    }

    @Override
    public boolean isSwipeEnable() {
        return false;
    }
}