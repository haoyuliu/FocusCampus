package com.sam.globalRentalCar.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.sam.globalRentalCar.R;
import com.sam.globalRentalCar.aop.DebugLog;
import com.sam.globalRentalCar.aop.SingleClick;
import com.sam.globalRentalCar.common.MyActivity;
import com.sam.globalRentalCar.helper.InputTextHelper;
import com.sam.globalRentalCar.other.IntentKey;

import butterknife.BindView;

/**
 * desc   : 重置密码
 */
public final class PasswordResetActivity extends MyActivity {

    @DebugLog
    public static void start(Context context, String phone, String code) {
        Intent intent = new Intent(context, PasswordResetActivity.class);
        intent.putExtra(IntentKey.PHONE, phone);
        intent.putExtra(IntentKey.CODE, code);
        context.startActivity(intent);
    }

    @BindView(R.id.et_password_reset_password1)
    EditText mPasswordView1;
    @BindView(R.id.et_password_reset_password2)
    EditText mPasswordView2;
    @BindView(R.id.btn_password_reset_commit)
    Button mCommitView;

    /**
     * 手机号
     */
    private String mPhone;
    /**
     * 验证码
     */
    private String mCode;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_password_reset;
    }

    @Override
    protected void initView() {
        InputTextHelper.with(this)
                .addView(mPasswordView1)
                .addView(mPasswordView2)
                .setMain(mCommitView)
                .setListener(helper -> mPasswordView1.getText().toString().length() >= 6 &&
                        mPasswordView1.getText().toString().equals(mPasswordView2.getText().toString()))
                .build();
        setOnClickListener(R.id.btn_password_reset_commit);
    }

    @Override
    protected void initData() {
        mPhone = getString(IntentKey.PHONE);
        mCode = getString(IntentKey.CODE);
    }

    @SingleClick
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_password_reset_commit) {
            if (true) {
                toast(R.string.password_reset_success);
                finish();
                return;
            }
        }
    }
}