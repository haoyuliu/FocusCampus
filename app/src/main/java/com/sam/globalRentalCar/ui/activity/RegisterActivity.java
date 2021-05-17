package com.sam.globalRentalCar.ui.activity;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatButton;

import com.alibaba.sdk.android.vod.upload.common.utils.StringUtil;
import com.gyf.immersionbar.ImmersionBar;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.sam.globalRentalCar.R;
import com.sam.globalRentalCar.aop.SingleClick;
import com.sam.globalRentalCar.common.MyActivity;
import com.sam.globalRentalCar.constant.Constant;
import com.sam.globalRentalCar.helper.InputTextHelper;
import com.sam.globalRentalCar.http.net.RetrofitClient;
import com.sam.globalRentalCar.http.request.LoginRequestBean;
import com.sam.globalRentalCar.http.response.LoginBean;
import com.sam.globalRentalCar.http.response.VerficationCodeBean;
import com.sam.globalRentalCar.utils.IpUtils;
import com.sam.globalRentalCar.utils.SPUtils;
import com.sam.widget.view.CountdownView;

import java.net.HttpURLConnection;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * desc   : 注册界面
 */
public final class RegisterActivity extends MyActivity {

    @BindView(R.id.tv_register_title)
    TextView mTitleView;

    @BindView(R.id.et_register_phone)
    EditText mRegisterPhoneView;

    @BindView(R.id.cv_register_countdown)
    CountdownView mCountdownView;

    @BindView(R.id.et_register_code)
    EditText mRegisterCodeView;

    @BindView(R.id.et_register_password1)
    EditText mRegisterPasswordView1;

    @BindView(R.id.btn_register_commit)
    AppCompatButton mRegisterCommitView;

    String traceId;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    protected void initView() {
        // 给这个 View 设置沉浸式，避免状态栏遮挡
        ImmersionBar.setTitleBar(this, mTitleView);
        setOnClickListener(R.id.cv_register_countdown, R.id.btn_register_commit, R.id.privacy_protocol, R.id.user_protocol);
    }

    @Override
    protected void initData() {

    }

    @Override
    public ImmersionBar createStatusBarConfig() {
        // 不要把整个布局顶上去
        return super.createStatusBarConfig().keyboardEnable(true);
    }

    @SingleClick
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cv_register_countdown:
                // 获取验证码
                if (mRegisterPhoneView.getText().toString().length() != 11) {
                    toast(R.string.common_phone_input_error);
                    return;
                }

                // 获取验证码
                RetrofitClient.getRetrofitService().loadVerficationCode(mRegisterPhoneView.getText().toString()).enqueue(new Callback<VerficationCodeBean>() {
                    @Override
                    public void onResponse(Call<VerficationCodeBean> call, Response<VerficationCodeBean> response) {
                        if (response.code() == HttpURLConnection.HTTP_OK) {
                            toast("验证码以发送" + response.message());
                            if (response.body().getTraceId() == null) {
                                traceId = "111";
                                return;
                            }
                            traceId = response.body().getData();
                            mCountdownView.start();
                        } else {
                            toast("验证码获取失败" + response.message());
                            mCountdownView.stop();
                        }

                    }

                    @Override
                    public void onFailure(Call<VerficationCodeBean> call, Throwable t) {
                        toast("验证码获取失败" + t.getMessage().toString());
                    }
                });
                break;
            case R.id.btn_register_commit:
                showDialog();
                if (mRegisterPhoneView.getText().toString().length() != 11) {
                    toast(R.string.common_phone_input_error);
                    hideDialog();
                    return;
                }
                if (StringUtil.isEmpty(mRegisterCodeView.getText().toString())) {
                    toast("请输入验证码");
                    hideDialog();
                    return;
                }
                if (StringUtil.isEmpty(mRegisterPasswordView1.getText().toString())) {
                    toast("请输入密码");
                    hideDialog();
                    return;
                }
                LoginRequestBean loginRequestBean = new LoginRequestBean();
                loginRequestBean.setIp(IpUtils.getHostIP());
                loginRequestBean.setPhone(mRegisterPhoneView.getText().toString());
                loginRequestBean.setRequestId("222");
                loginRequestBean.setVerifcationCode(mRegisterCodeView.getText().toString());
                loginRequestBean.setUserPwd(mRegisterPasswordView1.getText().toString());
                RetrofitClient.getRetrofitService().loadLogin(loginRequestBean)
                        .enqueue(new Callback<LoginBean>() {
                            @Override
                            public void onResponse(Call<LoginBean> call, Response<LoginBean> response) {
                                Log.d("login", response.code() + "");
                                int Code = Integer.parseInt(response.body().getCode());
                                if (Code == HttpURLConnection.HTTP_OK) {
                                    EMClient.getInstance().login(response.body().getData().getHxuid(), response.body().getData().getHxpwd(), new EMCallBack() {
                                        @Override
                                        public void onSuccess() {
                                            hideDialog();
                                            SPUtils.getInstance(RegisterActivity.this).put("token", response.body().getData().getToken());
                                            SPUtils.getInstance(RegisterActivity.this).put("HeadImage", response.body().getData().getHeadImg());
                                            SPUtils.getInstance(RegisterActivity.this).put("NickName", response.body().getData().getNickName());
                                            SPUtils.getInstance(RegisterActivity.this).put("UserId", response.body().getData().getUserId() + "");
                                            SPUtils.getInstance(RegisterActivity.this).put("userSex", response.body().getData().getUserSex());
                                            SPUtils.getInstance(RegisterActivity.this).put("userDesc", response.body().getData().getUserDesc() + "");
                                            SPUtils.getInstance(RegisterActivity.this).put("userBirthday", response.body().getData().getUserBirthday() + "");
                                            SPUtils.getInstance(RegisterActivity.this).put("userLocation", response.body().getData().getUserLocation() + "");
                                            startActivity(com.sam.globalRentalCar.ui.activity.HomeActivity.class);
                                        }

                                        @Override
                                        public void onError(int i, String s) {
                                            hideDialog();
                                            toast("注册失败" + s.toString());
                                        }

                                        @Override
                                        public void onProgress(int i, String s) {
                                            showDialog();
                                        }
                                    });

                                } else {
                                    hideDialog();
                                    toast("注册失败" + response.message());
                                }

                            }

                            @Override
                            public void onFailure(Call<LoginBean> call, Throwable t) {
                                hideDialog();
                                toast("注册失败" + t.getMessage().toString());
                            }
                        });

                break;
            case R.id.user_protocol:
                // 用户协议
                BrowserActivity.start(RegisterActivity.this, Constant.USER_PROTOTAL);
                break;
            case R.id.privacy_protocol:
                //隐私协议
                BrowserActivity.start(RegisterActivity.this, Constant.USER_PROTOTAL);
                break;
            default:
                break;
        }
    }

    @Override
    public boolean isSwipeEnable() {
        return false;
    }
}