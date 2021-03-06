package com.sam.globalRentalCar.ui.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Looper;
import android.os.MessageQueue;
import android.util.Log;
import android.util.Printer;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.alibaba.sdk.android.vod.upload.common.utils.StringUtil;
import com.sam.globalRentalCar.R;
import com.sam.globalRentalCar.chat.MessageFragment;
import com.sam.globalRentalCar.common.MyActivity;
import com.sam.globalRentalCar.helper.ActivityStackManager;
import com.sam.globalRentalCar.helper.DoubleClickHelper;
import com.sam.globalRentalCar.http.net.BaseResponse;
import com.sam.globalRentalCar.common.FragmentManagerHelper;
import com.sam.globalRentalCar.ui.fragment.HomeFragment;
import com.sam.globalRentalCar.ui.fragment.MineFragment;
import com.sam.globalRentalCar.ui.fragment.RentalCarFragment;
import com.sam.globalRentalCar.utils.RxBus;
import com.sam.globalRentalCar.utils.SPUtils;
import com.sam.globalRentalCar.video.PermissionChecker;
import com.sam.globalRentalCar.video.ToastUtils;
import com.sam.globalRentalCar.video.VideoRecordActivity;

import butterknife.BindView;
import butterknife.OnClick;
import rx.functions.Action1;

/**
 * desc   : 项目的主页界面，包含底部的四个Fragment
 * 拍照按钮单独设置，进行短视频的拍摄
 */
public class HomeActivity extends MyActivity {

    private static final String TAG = "ResponseLogInterceptor";
    private HomeFragment mHomeFragment;
    private RentalCarFragment mRentalCarFragment;
    private MessageFragment mMessageFragment;
    private MineFragment mMineFragment;

    private FragmentManagerHelper mFragmentManagerHelper;

    @BindView(R.id.main_tab_content)
    FrameLayout mainTabContent;
    @BindView(R.id.radioGroup)
    RadioGroup mRadioGroup;
    @BindView(R.id.rbHome)
    RadioButton rbHome;
    @BindView(R.id.rbRentalCar)
    RadioButton rbRentalCar;
    @BindView(R.id.rbMessage)
    RadioButton rbMessage;
    @BindView(R.id.rbMine)
    RadioButton rbMine;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    protected void initView() {
        //默认第一个是被选中的
        rbHome.setChecked(true);
        // 不使用图标默认变色
        mFragmentManagerHelper = new FragmentManagerHelper(getSupportFragmentManager(), R.id.main_tab_content);
        //默认进来，加载首页
        swicthToHomeFragment();
    }

    @Override
    protected void initData() {
        Log.d(TAG, "HomeActivity ---->:initData");
        RxBus.getDefault().toObservable(BaseResponse.class)
                .subscribe(new Action1<BaseResponse>() {
                    @Override
                    public void call(BaseResponse o) {
                        Log.d(TAG, "HomeActivity ---->:baseResponse+收到发送的消息了");
                        if (o.getCode().equals("401")) {
                            startActivity(LoginActivity.class);
                        }
                    }
                });
    }

    /**
     * 点击事件
     */
    @OnClick({R.id.rbHome, R.id.rbRentalCar, R.id.rbMessage, R.id.rbMine, R.id.rbTakePhoto})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rbHome:
                //首页
                //setLayoutBottonMargin(0);
                swicthToHomeFragment();
                break;
            case R.id.rbRentalCar:
                //租车
                if (isLogin()) {
                    //setLayoutBottonMargin(mRadioGroup.getHeight());
                    switchToRentalCarFragment();
                    rbRentalCar.setChecked(true);
                }
                break;
            case R.id.rbMessage:
                //消息
                if (isLogin()) {
                    // setLayoutBottonMargin(mRadioGroup.getHeight());
                    switchToMessageFragment();
                    rbMessage.setChecked(true);
                }
                break;
            case R.id.rbMine:
                //我的
                if (isLogin()) {
                    //setLayoutBottonMargin(mRadioGroup.getHeight());
                    switchToMineFragment();
                    rbMine.setChecked(true);
                }
                break;
            case R.id.rbTakePhoto:
                if (isLogin()) {
                    // setLayoutBottonMargin(mRadioGroup.getHeight());
                    //startActivity(UpLoadVedioActivity.class);
                    //短视频拍摄
                    if (isPermissionOK()) {
                        jumpToCaptureActivity();
                    }
                }

            default:
                break;
        }
    }

/*    private void setLayoutBottonMargin(int bottom) {
        if (bottom > 0) {
            mRadioGroup.setBackgroundColor(getResources().getColor(R.color.white));
        } else {
            mRadioGroup.setBackgroundColor(getResources().getColor(R.color.transparent));
        }
        LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) mainTabContent.getLayoutParams();
        layoutParams2.setMargins(0, 0, 0, bottom);
    }*/

    private void swicthToHomeFragment() {
        //首页
        if (mHomeFragment == null) {
            mHomeFragment = new HomeFragment();
        }
        //替换Fragment
        mFragmentManagerHelper.switchFragment(mHomeFragment);
    }

    private void switchToRentalCarFragment() {
        if (mRentalCarFragment == null) {
            mRentalCarFragment = new RentalCarFragment();
        }
        //替换Fragment
        mFragmentManagerHelper.switchFragment(mRentalCarFragment);
    }

    private void switchToMessageFragment() {
        if (mMessageFragment == null) {
            mMessageFragment = new MessageFragment();
        }
        //替换Fragment
        mFragmentManagerHelper.switchFragment(mMessageFragment);
    }

    private void switchToMineFragment() {
        //判断
        if (mMineFragment == null) {
            mMineFragment = new MineFragment();
        }
        //替换Fragment
        mFragmentManagerHelper.switchFragment(mMineFragment);
    }

    private boolean isLogin() {
        String token = SPUtils.getInstance(HomeActivity.this).getString("token");
        if (StringUtil.isEmpty(token)) {
            startActivity(LoginActivity.class);
            rbHome.setChecked(true);
            return false;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        if (DoubleClickHelper.isOnDoubleClick()) {
            // 移动到上一个任务栈，避免侧滑引起的不良反应
            moveTaskToBack(false);
            postDelayed(() -> {

                // 进行内存优化，销毁掉所有的界面
                ActivityStackManager.getInstance().finishAllActivities();
                // 销毁进程（注意：调用此 API 可能导致当前 Activity onDestroy 方法无法正常回调）
                finish();
            }, 300);
        } else {
            toast(R.string.home_exit_hint);
        }
    }

    @Override
    public boolean isSwipeEnable() {
        return false;
    }

    private boolean isPermissionOK() {
        PermissionChecker checker = new PermissionChecker(this);
        boolean isPermissionOK = Build.VERSION.SDK_INT < Build.VERSION_CODES.M || checker.checkPermission();
        if (!isPermissionOK) {
            ToastUtils.s(this, "请同意权限!!!");
        }
        return isPermissionOK;
    }

    public void jumpToCaptureActivity() {

        int PREVIEW_SIZE_RATIO_POS = 0;
        int PREVIEW_SIZE_LEVEL_POS = 3;
        int ENCODING_MODE_LEVEL_POS = 0;
        int ENCODING_SIZE_LEVEL_POS = 7;
        int ENCODING_BITRATE_LEVEL_POS = 2;
        int AUDIO_CHANNEL_NUM_POS = 0;
        Intent intent = new Intent(HomeActivity.this, VideoRecordActivity.class);
        intent.putExtra(VideoRecordActivity.PREVIEW_SIZE_RATIO, 0);
        intent.putExtra(VideoRecordActivity.PREVIEW_SIZE_LEVEL, 3);
        intent.putExtra(VideoRecordActivity.ENCODING_MODE, 0);
        intent.putExtra(VideoRecordActivity.ENCODING_SIZE_LEVEL, 7);
        intent.putExtra(VideoRecordActivity.ENCODING_BITRATE_LEVEL, 2);
        intent.putExtra(VideoRecordActivity.AUDIO_CHANNEL_NUM, 0);
        startActivity(intent);
    }
}