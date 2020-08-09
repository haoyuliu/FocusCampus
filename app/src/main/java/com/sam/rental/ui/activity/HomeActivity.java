package com.sam.rental.ui.activity;

import android.widget.FrameLayout;

import com.androidkun.xtablayout.XTabLayout;
import com.sam.rental.R;
import com.sam.rental.bean.PauseVideoEvent;
import com.sam.rental.common.MyActivity;
import com.sam.rental.helper.ActivityStackManager;
import com.sam.rental.helper.DoubleClickHelper;
import com.sam.rental.ui.fragment.FragmentManagerHelper;
import com.sam.rental.ui.fragment.HomeFragment;
import com.sam.rental.ui.fragment.MessageFragment;
import com.sam.rental.ui.fragment.MineFragment;
import com.sam.rental.ui.fragment.RentalCarFragment;
import com.sam.rental.utils.RxBus;

import butterknife.BindView;

/**
 * desc   : 项目的主页界面，包含底部的四个Fragment
 * 拍照按钮单独设置，进行短视频的拍摄
 */
public final class HomeActivity extends MyActivity {

    private HomeFragment mHomeFragment;
    private RentalCarFragment mRentalCarFragment;
    private MessageFragment mMessageFragment;
    private MineFragment mMineFragment;

    private FragmentManagerHelper mFragmentManagerHelper;
    @BindView(R.id.main_tab_content)
    FrameLayout mFrameLayout;
    @BindView(R.id.tab_main_menu)
    XTabLayout tabMainMenu;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    protected void initView() {
        // 不使用图标默认变色
        mFragmentManagerHelper = new FragmentManagerHelper(getSupportFragmentManager(), R.id.main_tab_content);
        //默认进来，加载首页
        mHomeFragment = new HomeFragment();
        //把第一个fragment添加进来,第一个参数是容器ID
        mFragmentManagerHelper.add(mHomeFragment);
        setMainMenu();
        setOnClick();
    }

    @Override
    protected void initData() {
    }

    private void setMainMenu() {
        tabMainMenu.addTab(tabMainMenu.newTab().setText("首页"));
        tabMainMenu.addTab(tabMainMenu.newTab().setText("租车"));
        tabMainMenu.addTab(tabMainMenu.newTab().setText(""));
        tabMainMenu.addTab(tabMainMenu.newTab().setText("消息"));
        tabMainMenu.addTab(tabMainMenu.newTab().setText("我的"));
    }

    /**
     * 底部按钮点击事件
     */
    private void setOnClick() {
        tabMainMenu.setOnTabSelectedListener(new XTabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(XTabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        //首页
                        //判断
                        if (mHomeFragment == null) {
                            mHomeFragment = new HomeFragment();
                        }
                        //替换Fragment
                        mFragmentManagerHelper.switchFragment(mHomeFragment);
                        RxBus.getDefault().post(new PauseVideoEvent(false));
                        break;
                    case 1:
                        //租车
                        //判断
                        if (mRentalCarFragment == null) {
                            mRentalCarFragment = new RentalCarFragment();
                        }
                        //替换Fragment
                        mFragmentManagerHelper.switchFragment(mRentalCarFragment);
                        RxBus.getDefault().post(new PauseVideoEvent(false));
                        break;
                    case 2:
                        //拍视频
                        startActivity(UpLoadVedioActivity.class);
                        RxBus.getDefault().post(new PauseVideoEvent(false));
                        break;
                    case 3:
                        //消息
                        RxBus.getDefault().post(new PauseVideoEvent(false));
                        //判断
                        if (mMessageFragment == null) {
                            mMessageFragment = new MessageFragment();
                        }
                        //替换Fragment
                        mFragmentManagerHelper.switchFragment(mMessageFragment);
                        break;
                    case 4:
                        //我的
                        if (mMineFragment == null) {
                            mMineFragment = new MineFragment();
                        }
                        //替换Fragment
                        mFragmentManagerHelper.switchFragment(mMineFragment);
                        RxBus.getDefault().post(new PauseVideoEvent(false));
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onTabUnselected(XTabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(XTabLayout.Tab tab) {

            }
        });
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
                // System.exit(0);

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