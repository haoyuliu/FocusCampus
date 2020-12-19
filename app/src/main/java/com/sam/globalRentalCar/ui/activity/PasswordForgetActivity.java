package com.sam.globalRentalCar.ui.activity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.sam.globalRentalCar.R;
import com.sam.globalRentalCar.aop.SingleClick;
import com.sam.globalRentalCar.common.MyActivity;
import com.sam.globalRentalCar.helper.InputTextHelper;
import com.sam.globalRentalCar.http.model.HttpData;
import com.sam.globalRentalCar.http.request.GetCodeApi;
import com.sam.globalRentalCar.http.request.VerifyCodeApi;
import com.hjq.http.EasyHttp;
import com.hjq.http.listener.HttpCallback;
import com.sam.widget.view.CountdownView;

import butterknife.BindView;

/**
 *    desc   : 忘记密码
 */
public final class PasswordForgetActivity extends MyActivity {

    @BindView(R.id.et_password_forget_phone)
    EditText mPhoneView;
    @BindView(R.id.et_password_forget_code)
    EditText mCodeView;
    @BindView(R.id.cv_password_forget_countdown)
    CountdownView mCountdownView;
    @BindView(R.id.btn_password_forget_commit)
    Button mCommitView;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_password_forget;
    }

    @Override
    protected void initView() {
        InputTextHelper.with(this)
                .addView(mPhoneView)
                .addView(mCodeView)
                .setMain(mCommitView)
                .setListener(helper -> mPhoneView.getText().toString().length() == 11 && mCodeView.getText().toString().length() == 4)
                .build();

        setOnClickListener(R.id.cv_password_forget_countdown, R.id.btn_password_forget_commit);
    }

    @Override
    protected void initData() {

    }

    @SingleClick
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
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
                EasyHttp.post(this)
                        .api(new GetCodeApi()
                        .setPhone(mPhoneView.getText().toString()))
                        .request(new HttpCallback<HttpData<Void>>(this) {

                            @Override
                            public void onSucceed(HttpData<Void> data) {
                                toast(R.string.common_code_send_hint);
                                mCountdownView.start();
                            }
                        });
                break;
            case R.id.btn_password_forget_commit:
                if (true) {
                    PasswordResetActivity.start(getActivity(), mPhoneView.getText().toString(), mCodeView.getText().toString());
                    finish();
                    return;
                }

                // 验证码校验
                EasyHttp.post(this)
                        .api(new VerifyCodeApi()
                        .setPhone(mPhoneView.getText().toString())
                        .setCode(mCodeView.getText().toString()))
                        .request(new HttpCallback<HttpData<Void>>(this) {

                            @Override
                            public void onSucceed(HttpData<Void> data) {
                                PasswordResetActivity.start(getActivity(), mPhoneView.getText().toString(), mCodeView.getText().toString());
                                finish();
                            }
                        });
                break;
            default:
                break;
        }
    }
}