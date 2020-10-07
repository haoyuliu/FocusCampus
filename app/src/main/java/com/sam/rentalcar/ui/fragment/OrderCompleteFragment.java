package com.sam.rentalcar.ui.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.androidkun.xtablayout.XTabLayout;
import com.bumptech.glide.Glide;
import com.sam.rentalcar.R;
import com.sam.rentalcar.aop.SingleClick;
import com.sam.rentalcar.common.MyFragment;
import com.sam.rentalcar.ui.activity.FocusActivity;
import com.sam.rentalcar.ui.activity.HomeActivity;
import com.sam.rentalcar.ui.activity.PersonalDataActivity;
import com.sam.rentalcar.ui.activity.SettingActivity;
import com.sam.rentalcar.ui.adapter.CommPagerAdapter;
import com.sam.rentalcar.utils.SPUtils;
import com.sam.rentalcar.widget.CircleImageView;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * desc:订单已完成页面
 */
public final class OrderCompleteFragment extends MyFragment<HomeActivity> {
    public static final String TAG = "OrderCompleteFragment";

    public static OrderCompleteFragment newInstance() {
        return new OrderCompleteFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_order_complete;
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