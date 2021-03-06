package com.sam.globalRentalCar.ui.activity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.sam.globalRentalCar.R;
import com.sam.globalRentalCar.aop.SingleClick;
import com.sam.globalRentalCar.common.MyActivity;
import com.sam.globalRentalCar.helper.InputTextHelper;
import com.sam.widget.view.CountdownView;

import butterknife.BindView;

/**
 * desc   : 校验手机号
 */
public final class PhoneVerifyActivity extends MyActivity {

    @BindView(R.id.tv_phone_verify_phone)
    TextView mPhoneView;
    @BindView(R.id.et_phone_verify_code)
    EditText mCodeView;
    @BindView(R.id.cv_phone_verify_countdown)
    CountdownView mCountdownView;
    @BindView(R.id.btn_phone_verify_commit)
    Button mCommitView;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_phone_verify;
    }

    @Override
    protected void initView() {
        InputTextHelper.with(this)
                .addView(mCodeView)
                .setMain(mCommitView)
                .setListener(helper -> mCodeView.getText().toString().length() == 4)
                .build();

        setOnClickListener(R.id.cv_phone_verify_countdown, R.id.btn_phone_verify_commit);
    }

    @Override
    protected void initData() {
        mPhoneView.setText(String.format(getString(R.string.phone_verify_current_phone), "18888888888"));
    }

    @SingleClick
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cv_phone_verify_countdown:
                if (true) {
                    toast(R.string.common_code_send_hint);
                    return;
                }
                break;
            case R.id.btn_phone_verify_commit:
                if (true) {
                    // 跳转到绑定手机号页面
                    PhoneResetActivity.start(getActivity(), mCodeView.getText().toString());
                    finish();
                    return;
                }
                break;
            default:
                break;
        }
    }
}