package com.sam.rental.ui.activity;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import com.alibaba.sdk.android.vod.upload.common.utils.StringUtil;
import com.sam.rental.R;
import com.sam.rental.common.MyActivity;
import com.sam.rental.helper.ActivityStackManager;
import com.sam.rental.helper.DoubleClickHelper;
import com.sam.rental.ui.fragment.FragmentManagerHelper;
import com.sam.rental.ui.fragment.HomeFragment;
import com.sam.rental.ui.fragment.MessageFragment;
import com.sam.rental.ui.fragment.MineFragment;
import com.sam.rental.ui.fragment.RentalCarFragment;
import com.sam.rental.utils.SPUtils;
import com.superrtc.FrameEncryptor;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * desc   : 项目的主页界面，包含底部的四个Fragment
 * 拍照按钮单独设置，进行短视频的拍摄
 */
public class HomeActivity extends MyActivity {

    private HomeFragment mHomeFragment;
    private RentalCarFragment mRentalCarFragment;
    private MessageFragment mMessageFragment;
    private MineFragment mMineFragment;

    private FragmentManagerHelper mFragmentManagerHelper;

    @BindView(R.id.main_tab_content)
    FrameLayout mainTabContent;
    // 底部
    @BindView(R.id.radioGroup)
    RadioGroup mRadioGroup;
    @BindView(R.id.rbHome)
    RadioButton rbHome;
    @BindView(R.id.rbRentalCar)
    RadioButton rbRentalCar;
    @BindView(R.id.rbTakePhoto)
    RadioButton rbTakePhoto;
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
    }

    /**
     * 点击事件
     */
    @OnClick({R.id.rbHome, R.id.rbRentalCar, R.id.rbMessage, R.id.rbMine, R.id.rbTakePhoto})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rbHome:
                //首页
                setLayoutBottonMargin(0);
                swicthToHomeFragment();
                break;
            case R.id.rbRentalCar:
                //租车
                if (isLogin()) {
                    setLayoutBottonMargin(mRadioGroup.getHeight());
                    switchToRentalCarFragment();
                    rbRentalCar.setChecked(true);
                }
                break;
            case R.id.rbMessage:
                //消息
                if (isLogin()) {
                    setLayoutBottonMargin(mRadioGroup.getHeight());
                    switchToMessageFragment();
                    rbMessage.setChecked(true);
                }
                break;
            case R.id.rbMine:
                //我的
                if (isLogin()) {
                    setLayoutBottonMargin(mRadioGroup.getHeight());
                    switchToMineFragment();
                    rbMine.setChecked(true);
                }
                break;
            case R.id.rbTakePhoto:
                if (isLogin()) {
                    setLayoutBottonMargin(mRadioGroup.getHeight());
                    startActivity(UpLoadVedioActivity.class);
                    rbHome.setChecked(true);
                }

            default:
                break;
        }
    }

    private void setLayoutBottonMargin(int bottom) {
        if (bottom > 0) {
            mRadioGroup.setBackgroundColor(getResources().getColor(R.color.white));
        } else {
            mRadioGroup.setBackgroundColor(getResources().getColor(R.color.transparent));
        }
        LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) mainTabContent.getLayoutParams();
        layoutParams2.setMargins(0, 0, 0, bottom);
    }

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
                System.exit(0);

            }, 300);
        } else {
            toast(R.string.home_exit_hint);
        }
    }

    @Override
    public boolean isSwipeEnable() {
        return false;
    }
}