package com.sam.rental.ui.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.androidkun.xtablayout.XTabLayout;
import com.bumptech.glide.Glide;
import com.sam.rental.R;
import com.sam.rental.aop.SingleClick;
import com.sam.rental.common.MyFragment;
import com.sam.rental.ui.activity.FocusActivity;
import com.sam.rental.ui.activity.HomeActivity;
import com.sam.rental.ui.activity.PersonalDataActivity;
import com.sam.rental.ui.activity.SettingActivity;
import com.sam.rental.ui.adapter.CommPagerAdapter;
import com.sam.rental.utils.SPUtils;
import com.sam.rental.widget.CircleImageView;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * desc:订单进行中
 */
public final class OrderOnGoingFragment extends MyFragment<HomeActivity> {
    public static final String TAG = "OrderOnGoingFragment";

    public static OrderOnGoingFragment newInstance() {
        return new OrderOnGoingFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_order_on_going;
    }

    @Override
    protected void initView() {

    }

    @Override
    public void onRightClick(View v) {
        Log.d(TAG, "right_click");
    }

    @Override
    protected void initData() {

    }

    @Override
    public boolean isStatusBarEnabled() {
        // 使用沉浸式状态栏
        return !super.isStatusBarEnabled();
    }

}