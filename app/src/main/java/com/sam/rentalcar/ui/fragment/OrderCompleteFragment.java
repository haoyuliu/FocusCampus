package com.sam.rentalcar.ui.fragment;

import android.util.Log;
import android.view.View;

import com.sam.rentalcar.R;
import com.sam.rentalcar.common.MyFragment;
import com.sam.rentalcar.ui.activity.HomeActivity;

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