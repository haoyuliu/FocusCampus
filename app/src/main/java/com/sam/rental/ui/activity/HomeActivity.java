package com.sam.rental.ui.activity;

import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.sam.base.BaseFragmentAdapter;
import com.sam.rental.R;
import com.sam.rental.common.MyActivity;
import com.sam.rental.common.MyFragment;
import com.sam.rental.helper.ActivityStackManager;
import com.sam.rental.helper.DoubleClickHelper;
import com.sam.rental.other.KeyboardWatcher;
import com.sam.rental.ui.fragment.HomeFragment;
import com.sam.rental.ui.fragment.RentalCarFragment;
import com.sam.rental.ui.fragment.MessageFragment;
import com.sam.rental.ui.fragment.MineFragment;

import butterknife.BindView;

/**
 * desc   : 项目的主页界面，包含底部的四个Fragment
 * 拍照按钮单独设置，进行短视频的拍摄
 */
public final class HomeActivity extends MyActivity
        implements KeyboardWatcher.SoftKeyboardStateListener,
        BottomNavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.vp_home_pager)
    ViewPager mViewPager;
    @BindView(R.id.bv_home_navigation)
    BottomNavigationView mBottomNavigationView;

    private BaseFragmentAdapter<MyFragment> mPagerAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    protected void initView() {
        // 不使用图标默认变色
        mBottomNavigationView.setItemIconTintList(null);
        mBottomNavigationView.setOnNavigationItemSelectedListener(this);

        KeyboardWatcher.with(this)
                .setListener(this);
    }

    @Override
    protected void initData() {
        mPagerAdapter = new BaseFragmentAdapter<>(this);
        mPagerAdapter.addFragment(HomeFragment.newInstance());
        mPagerAdapter.addFragment(RentalCarFragment.newInstance());
        mPagerAdapter.addFragment(MessageFragment.newInstance());
        mPagerAdapter.addFragment(MineFragment.newInstance());

        mViewPager.setAdapter(mPagerAdapter);

        // 限制页面数量
        mViewPager.setOffscreenPageLimit(mPagerAdapter.getCount());
    }

    /**
     * {@link BottomNavigationView.OnNavigationItemSelectedListener}
     */

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_home:
                mPagerAdapter.setCurrentItem(HomeFragment.class);
                return true;
            case R.id.home_found:
                mPagerAdapter.setCurrentItem(RentalCarFragment.class);
                return true;
            case R.id.home_message:
                mPagerAdapter.setCurrentItem(MessageFragment.class);
                return true;
            case R.id.home_me:
                mPagerAdapter.setCurrentItem(MineFragment.class);
                return true;
            default:
                break;
        }
        return false;
    }

    /**
     * {@link KeyboardWatcher.SoftKeyboardStateListener}
     */
    @Override
    public void onSoftKeyboardOpened(int keyboardHeight) {
        mBottomNavigationView.setVisibility(View.GONE);
    }

    @Override
    public void onSoftKeyboardClosed() {
        mBottomNavigationView.setVisibility(View.VISIBLE);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // 回调当前 Fragment 的 onKeyDown 方法
        if (mPagerAdapter.getCurrentFragment().onKeyDown(keyCode, event)) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
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
    protected void onDestroy() {
        mViewPager.setAdapter(null);
        mBottomNavigationView.setOnNavigationItemSelectedListener(null);
        super.onDestroy();
    }

    @Override
    public boolean isSwipeEnable() {
        return false;
    }
}