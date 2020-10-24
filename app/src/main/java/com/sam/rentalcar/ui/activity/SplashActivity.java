package com.sam.rentalcar.ui.activity;

import android.Manifest;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.os.Handler;
import android.view.View;

import com.airbnb.lottie.LottieAnimationView;
import com.gyf.immersionbar.BarHide;
import com.gyf.immersionbar.ImmersionBar;
import com.hjq.http.EasyHttp;
import com.hjq.http.listener.HttpCallback;
import com.hjq.permissions.OnPermission;
import com.hjq.permissions.XXPermissions;
import com.sam.rentalcar.R;
import com.sam.rentalcar.common.MyActivity;
import com.sam.rentalcar.http.model.HttpData;
import com.sam.rentalcar.http.request.UserInfoApi;
import com.sam.rentalcar.http.response.UserInfoBean;
import com.sam.rentalcar.other.AppConfig;

import java.util.List;

import butterknife.BindView;

/**
 * desc   : 闪屏界面
 */
public final class SplashActivity extends MyActivity {

    @BindView(R.id.tv_splash_debug)
    View mDebugView;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initView() {
        new Handler().postDelayed(() -> {
            // 权限的申请

            XXPermissions.with(this).permission(Manifest.permission.CAMERA).request(new OnPermission() {
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
        if (AppConfig.isDebug()) {
            mDebugView.setVisibility(View.VISIBLE);
        } else {
            mDebugView.setVisibility(View.INVISIBLE);
        }

        if (true) {
            return;
        }
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