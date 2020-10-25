package com.sam.rentalcar.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.sam.rentalcar.R;
import com.sam.rentalcar.aop.DebugLog;
import com.sam.rentalcar.aop.SingleClick;
import com.sam.rentalcar.common.MyActivity;
import com.sam.rentalcar.helper.InputTextHelper;
import com.sam.rentalcar.http.model.HttpData;
import com.sam.rentalcar.http.request.PasswordApi;
import com.sam.rentalcar.other.IntentKey;
import com.hjq.http.EasyHttp;
import com.hjq.http.listener.HttpCallback;

import butterknife.BindView;

/**
 *    desc   : 重置密码
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

    /** 手机号 */
    private String mPhone;
    /** 验证码 */
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

            // 重置密码
            EasyHttp.post(this)
                    .api(new PasswordApi()
                    .setPhone(mPhone)
                    .setCode(mCode)
                    .setPassword(mPasswordView1.getText().toString()))
                    .request(new HttpCallback<HttpData<Void>>(this) {

                        @Override
                        public void onSucceed(HttpData<Void> data) {
                            toast(R.string.password_reset_success);
                            finish();
                        }
                    });
        }
    }
}