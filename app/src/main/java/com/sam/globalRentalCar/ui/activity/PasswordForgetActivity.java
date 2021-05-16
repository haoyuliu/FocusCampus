package com.sam.globalRentalCar.ui.activity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.sam.globalRentalCar.R;
import com.sam.globalRentalCar.aop.SingleClick;
import com.sam.globalRentalCar.common.MyActivity;
import com.sam.globalRentalCar.helper.InputTextHelper;
import com.sam.widget.view.CountdownView;

import butterknife.BindView;

/**
 * desc   : 找回密码界面
 */
public final class PasswordForgetActivity extends MyActivity {

    @BindView(R.id.et_forget_password_phone)
    EditText mPhoneForgetView;

    @BindView(R.id.et_forget_phone_code)
    EditText mCodeView;

    @BindView(R.id.cv_password_forget_countdown)
    CountdownView mCountdownView;

    @BindView(R.id.et_forget_password1)
    EditText mPhoneForgetPasswordView;

    @BindView(R.id.btn_reset_commit)
    Button mCommitView;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_password_forget;
    }

    @Override
    protected void initView() {
      /*  InputTextHelper.with(this)
                .addView(mPhoneForgetView)
                .addView(mCodeView)
                .setMain(mCommitView)
                .setListener(helper -> mPhoneForgetView.getText().toString().length() == 11 && mCodeView.getText().toString().length() == 4)
                .build();

        setOnClickListener(R.id.cv_password_forget_countdown, R.id.btn_reset_commit);*/
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
                    toast(R.string.common_code_send_hint);
                    mCountdownView.start();
                    return;
                }
                break;
            case R.id.btn_reset_commit:
                if (true) {
                    PasswordResetActivity.start(getActivity(), mPhoneForgetView.getText().toString(), mCodeView.getText().toString());
                    finish();
                    return;
                }
                break;
            default:
                break;
        }
    }
}