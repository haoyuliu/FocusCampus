package com.sam.globalRentalCar.ui.activity;

import android.view.Gravity;
import android.view.View;
import android.widget.Button;

import com.gyf.immersionbar.BarHide;
import com.gyf.immersionbar.ImmersionBar;
import com.sam.base.BaseDialog;
import com.sam.globalRentalCar.R;
import com.sam.globalRentalCar.common.MyActivity;
import com.sam.globalRentalCar.constant.Constant;
import com.sam.globalRentalCar.utils.SPUtils;

/**
 * desc   : 闪屏界面
 */
public final class SplashActivity extends MyActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initView() {
        boolean isSign = SPUtils.getInstance(SplashActivity.this).getBoolean("isSign");
        if (!isSign) {
            showPrivacyDialog();
        } else {
            startActivity(HomeActivity.class);
        }

    }

    private void showPrivacyDialog() {
        new BaseDialog.Builder(this)
                .setContentView(R.layout.dialog_custom)
                .setOnClickListener(R.id.btn_dialog_custom_ok, (BaseDialog.OnClickListener<Button>) (dialog, view) -> doOther())
                .setOnClickListener(R.id.btm_no, (BaseDialog.OnClickListener<Button>) (dialog, view) -> finish())
                .setCancelable(false)
                .setGravity(Gravity.CENTER)
                .setOnKeyListener((dialog, event) -> {
                    return false;
                })
                .show();
    }

    private void doOther() {
        startActivity(HomeActivity.class);
        SPUtils.getInstance(SplashActivity.this).put("isSign", true);
    }

    @Override
    protected void initData() {

    }

    @Override
    public ImmersionBar createStatusBarConfig() {
        return super.createStatusBarConfig()
                // 有导航栏的情况下，activity全屏显示，也就是activity最下面被导航栏覆盖，不写默认非全屏
                .fullScreen(true)
                // 隐藏状态栏
                .hideBar(BarHide.FLAG_HIDE_STATUS_BAR)
                // 透明导航栏，不写默认黑色(设置此方法，fullScreen()方法自动为true)
                .transparentNavigationBar();
    }

    @Override
    public void onBackPressed() {
        //禁用返回键
        //super.onBackPressed();
    }

    @Override
    public boolean isSwipeEnable() {
        return false;
    }

    public void userProtocol(View view) {
        // 用户协议
        BrowserActivity.start(SplashActivity.this, Constant.USER_PROTOTAL);
    }

    public void privacyProtocol(View view) {
        //隐私协议
        BrowserActivity.start(SplashActivity.this, Constant.USER_PROTOTAL);
    }
}