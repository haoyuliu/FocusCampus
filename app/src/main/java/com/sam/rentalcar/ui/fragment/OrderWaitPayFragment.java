package com.sam.rentalcar.ui.fragment;

import android.util.Log;
import android.view.View;

import com.sam.rentalcar.R;
import com.sam.rentalcar.common.MyFragment;
import com.sam.rentalcar.ui.activity.HomeActivity;

/**
 * desc:订单待付款页面
 */
public final class OrderWaitPayFragment extends MyFragment<HomeActivity> {
    public static final String TAG = "OrderCompleteFragment";

    public static OrderWaitPayFragment newInstance() {
        return new OrderWaitPayFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_wait_pay;
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