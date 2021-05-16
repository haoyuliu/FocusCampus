package com.sam.globalRentalCar.ui.activity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatButton;

import com.gyf.immersionbar.ImmersionBar;
import com.sam.globalRentalCar.R;
import com.sam.globalRentalCar.aop.SingleClick;
import com.sam.globalRentalCar.common.MyActivity;
import com.sam.globalRentalCar.constant.Constant;
import com.sam.globalRentalCar.helper.InputTextHelper;
import com.sam.widget.view.CountdownView;

import butterknife.BindView;

/**
 * desc   : 注册界面
 */
public final class RegisterActivity extends MyActivity {

    @BindView(R.id.tv_register_title)
    TextView mTitleView;

    @BindView(R.id.et_register_phone)
    EditText mPhoneView;
    @BindView(R.id.cv_register_countdown)
    CountdownView mCountdownView;

    @BindView(R.id.et_register_code)
    EditText mCodeView;

    @BindView(R.id.et_register_password1)
    EditText mPasswordView1;

    @BindView(R.id.btn_register_commit)
    AppCompatButton mCommitView;

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
                if (mPhoneView.getText().toString().length() != 11) {
                    toast(R.string.common_phone_input_error);
                    return;
                }

                if (true) {
                    toast(R.string.common_code_send_hint);
                    mCountdownView.start();
                    return;
                }
                break;
            case R.id.btn_register_commit:
                if (true) {
                    // LoginActivity.start(getActivity(), mPhoneView.getText().toString(), mPasswordView1.getText().toString());
                    setResult(RESULT_OK);
                    finish();
                    return;
                }
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