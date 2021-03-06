package com.sam.globalRentalCar.ui.activity;

import android.view.View;
import android.widget.Toast;

import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.sam.globalRentalCar.R;
import com.sam.globalRentalCar.aop.SingleClick;
import com.sam.globalRentalCar.common.MyActivity;
import com.sam.globalRentalCar.helper.ActivityStackManager;
import com.sam.globalRentalCar.helper.CacheDataManager;
import com.sam.globalRentalCar.http.glide.GlideApp;
import com.sam.globalRentalCar.utils.SPUtils;
import com.sam.widget.layout.SettingBar;
import com.sam.widget.view.SwitchButton;

import butterknife.BindView;

/**
 * desc   : 设置界面
 */
public final class SettingActivity extends MyActivity
        implements SwitchButton.OnCheckedChangeListener {

    @BindView(R.id.sb_setting_cache)
    SettingBar mCleanCacheView;

    @BindView(R.id.sb_feedback)
    SettingBar mFeedbackView;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_setting;
    }

    @Override
    protected void initView() {
        setOnClickListener(R.id.sb_setting_update, R.id.sb_setting_agreement,
                R.id.sb_setting_cache, R.id.sb_setting_exit, R.id.sb_feedback);
    }

    @Override
    protected void initData() {
        // 获取应用缓存大小
        mCleanCacheView.setRightText(CacheDataManager.getTotalCacheSize(this));
    }

    @SingleClick
    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.sb_setting_update:
                // 本地的版本码和服务器的进行比较
                /*if (20 > AppConfig.getVersionCode()) {

                    new UpdateDialog.Builder(this)
                            // 版本名
                            .setVersionName("2.0")
                            // 是否强制更新
                            .setForceUpdate(false)
                            // 更新日志
                            .setUpdateLog("修复Bug\n优化用户体验")
                            // 下载 url
                            .setDownloadUrl("https://raw.githubusercontent.com/getActivity/AndroidProject/master/AndroidProject.apk")
                            .show();
                } else {
                    toast(R.string.update_no_update);
                }*/
                //toast(R.string.update_no_update);
                break;
            case R.id.sb_setting_agreement:
                //BrowserActivity.start(this, "https://github.com/getActivity/Donate");
                break;
            case R.id.sb_setting_cache:
                // 清除内存缓存（必须在主线程）
                GlideApp.get(getActivity()).clearMemory();
                new Thread(() -> {
                    // 清除本地缓存（必须在子线程）
                    GlideApp.get(getActivity()).clearDiskCache();
                }).start();
                CacheDataManager.clearAllCache(this);
                postDelayed(() -> {
                    // 重新获取应用缓存大小
                    mCleanCacheView.setRightText(CacheDataManager.getTotalCacheSize(getActivity()));
                }, 500);
                break;
            case R.id.sb_setting_exit:
                if (true) {

                    // 退出环信
                    EMClient.getInstance().logout(true, new EMCallBack() {
                        @Override
                        public void onSuccess() {

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    SPUtils.getInstance(SettingActivity.this).clear();
                                    startActivity(LoginActivity.class);
                                    // 进行内存优化，销毁除登录页之外的所有界面
                                    ActivityStackManager.getInstance().finishAllActivities(LoginActivity.class);
                                    Toast.makeText(SettingActivity.this, "您已退出登录！", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }

                        @Override
                        public void onError(int i, String s) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(SettingActivity.this, "退出失败！", Toast.LENGTH_SHORT).show();
                                }
                            });

                        }

                        @Override
                        public void onProgress(int i, String s) {

                        }
                    });

                    return;
                }
                break;
            case R.id.sb_feedback:
                // 意见反馈
                startActivity(FeedBackActivity.class);
                break;
            default:
                break;
        }
    }

    /**
     * {@link SwitchButton.OnCheckedChangeListener}
     */

    @Override
    public void onCheckedChanged(SwitchButton button, boolean isChecked) {
        toast(isChecked);
    }
}