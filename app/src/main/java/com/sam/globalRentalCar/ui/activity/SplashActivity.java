package com.sam.globalRentalCar.ui.activity;

import android.Manifest;
import android.os.Handler;
import android.view.View;

import com.gyf.immersionbar.BarHide;
import com.gyf.immersionbar.ImmersionBar;
import com.hjq.permissions.OnPermission;
import com.hjq.permissions.XXPermissions;
import com.sam.globalRentalCar.R;
import com.sam.globalRentalCar.common.MyActivity;

import java.util.List;

import butterknife.BindView;

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
        new Handler().postDelayed(() -> {
            // 权限的申请

            XXPermissions.with(this).permission(Manifest.permission.CAMERA, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.READ_PHONE_STATE, Manifest.permission.WRITE_EXTERNAL_STORAGE).request(new OnPermission() {
                @Override
                public void hasPermission(List<String> granted, boolean all) {
                    startActivity(HomeActivity.class);
                    finish();
                }

                @Override
                public void noPermission(List<String> denied, boolean quick) {
                    XXPermissions.gotoPermissionSettings(SplashActivity.this);
                }
            });
        }, 3000);
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
}