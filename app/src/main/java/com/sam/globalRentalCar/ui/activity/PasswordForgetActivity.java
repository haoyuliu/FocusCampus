package com.sam.globalRentalCar.ui.activity;

import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.widget.AppCompatButton;

import com.alibaba.sdk.android.vod.upload.common.utils.StringUtil;
import com.sam.globalRentalCar.R;
import com.sam.globalRentalCar.aop.SingleClick;
import com.sam.globalRentalCar.common.MyActivity;
import com.sam.globalRentalCar.helper.InputTextHelper;
import com.sam.globalRentalCar.http.net.RetrofitClient;
import com.sam.globalRentalCar.http.request.LoginWithAccountRequestBean;
import com.sam.globalRentalCar.http.response.ForgetPassword;
import com.sam.globalRentalCar.http.response.VerficationCodeBean;
import com.sam.globalRentalCar.utils.IpUtils;
import com.sam.widget.view.CountdownView;

import java.net.HttpURLConnection;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * desc   : 找回(重置)密码界面
 */
public class PasswordForgetActivity extends MyActivity {

    @BindView(R.id.et_forget_password_phone)
    EditText mPhoneForgetView;

    @BindView(R.id.et_forget_phone_code)
    EditText mCodeView;

    @BindView(R.id.cv_password_forget_countdown)
    CountdownView mCountdownView;

    @BindView(R.id.et_forget_password1)
    EditText mPhoneForgetPasswordView;

    @BindView(R.id.btn_reset_password_commit)
    AppCompatButton mCommitButtonView;

    String traceId;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_password_forget;
    }

    @Override
    protected void initView() {
       /* InputTextHelper.with(this)
                .addView(mPhoneForgetView)
                .addView(mCodeView)
                .setMain(mCommitButtonView)
                .setListener(helper -> mPhoneForgetView.getText().toString().length() == 11 && mCodeView.getText().toString().length() == 4)
                .build();
*/
        setOnClickListener(R.id.cv_password_forget_countdown, R.id.btn_reset_password_commit);
    }

    @Override
    protected void initData() {

    }

    @SingleClick
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cv_password_forget_countdown:
                if (mPhoneForgetView.getText().toString().length() != 11) {
                    toast(R.string.common_phone_input_error);
                    return;
                }

                if (true) {
                    // 获取验证码
                    RetrofitClient.getRetrofitService().loadVerficationCode(mPhoneForgetView.getText().toString()).enqueue(new Callback<VerficationCodeBean>() {
                        @Override
                        public void onResponse(Call<VerficationCodeBean> call, Response<VerficationCodeBean> response) {
                            VerficationCodeBean verficationCodeBean = response.body();
                            if (verficationCodeBean.getCode().equals("200")) {
                                toast("验证码以发送" + response.message());
                                if (response.body().getTraceId() == null) {
                                    traceId = "111";
                                    return;
                                }
                                traceId = response.body().getData();
                                mCountdownView.start();
                            } else {
                                toast("验证码获取失败" + response.message());
                                //mCountdownView.stop();
                            }

                        }

                        @Override
                        public void onFailure(Call<VerficationCodeBean> call, Throwable t) {
                            toast("验证码获取失败" + t.getMessage().toString());
                            //mCountdownView.stop();
                        }
                    });
                    toast(R.string.common_code_send_hint);
                    mCountdownView.start();
                    return;
                }
                break;
            case R.id.btn_reset_password_commit:
                showDialog();
                if (mPhoneForgetView.getText().toString().length() != 11) {
                    toast(R.string.common_phone_input_error);
                    hideDialog();
                    return;
                }
                if (StringUtil.isEmpty(mCodeView.getText().toString())) {
                    toast("请输入验证码");
                    hideDialog();
                    return;
                }
                if (StringUtil.isEmpty(mPhoneForgetPasswordView.getText().toString())) {
                    toast("请输入密码");
                    hideDialog();
                    return;
                }
                LoginWithAccountRequestBean loginRequestBean = new LoginWithAccountRequestBean();
                loginRequestBean.setIpAddress(IpUtils.getHostIP());
                loginRequestBean.setAccount(mPhoneForgetView.getText().toString());
                loginRequestBean.setVerifcationCode(mCodeView.getText().toString());
                loginRequestBean.setVerifcationCode(mPhoneForgetPasswordView.getText().toString());
                RetrofitClient.getRetrofitService().forgetLoginPassword(loginRequestBean)
                        .enqueue(new Callback<ForgetPassword>() {
                            @Override
                            public void onResponse(Call<ForgetPassword> call, Response<ForgetPassword> response) {
                                Log.d("login", response.code() + "");
                                int Code = Integer.parseInt(response.body().getCode());
                                if (Code == HttpURLConnection.HTTP_OK) {
                                    hideDialog();
                                    toast(response.body().getData());
                                    startActivity(com.sam.globalRentalCar.ui.activity.AccountLoginActivity.class);
                                    finish();
                                } else {
                                    hideDialog();
                                    toast(response.body().getData());
                                }

                            }

                            @Override
                            public void onFailure(Call<ForgetPassword> call, Throwable t) {
                                hideDialog();
                                toast("重置密码失败" + t.getMessage().toString());
                            }
                        });

                break;
            default:
                break;
        }
    }
}