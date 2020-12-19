package com.sam.globalRentalCar.ui.activity;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.androidkun.xtablayout.XTabLayout;
import com.sam.globalRentalCar.R;
import com.sam.globalRentalCar.common.MyActivity;
import com.sam.globalRentalCar.ui.adapter.CommPagerAdapter;
import com.sam.globalRentalCar.ui.fragment.OrderCancelFragment;
import com.sam.globalRentalCar.ui.fragment.OrderCompleteFragment;
import com.sam.globalRentalCar.ui.fragment.OrderOnGoingFragment;
import com.sam.globalRentalCar.ui.fragment.OrderWaitPayFragment;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * 租车订单界面
 */
public class RentalCarOrderActivity extends MyActivity {

    @BindView(R.id.order_xtab_layout)
    XTabLayout mOrderXtabLayout;

    @BindView(R.id.order_view_pager)
    ViewPager mOrderViewPager;

    private OrderCompleteFragment mOrderCompleteFragment;
    private OrderOnGoingFragment mOrderOnGoingFragment;
    private OrderCancelFragment mOrderCancelFragment;
    private OrderWaitPayFragment mOrderWaitPayFragment;
    private CommPagerAdapter pagerAdapter;
    private ArrayList<Fragment> fragments = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_rental_car_order;
    }

    @Override
    protected void initView() {
        mOrderWaitPayFragment = new OrderWaitPayFragment();
        mOrderOnGoingFragment = new OrderOnGoingFragment();
        mOrderCompleteFragment = new OrderCompleteFragment();
        mOrderCancelFragment = new OrderCancelFragment();

        fragments.add(mOrderWaitPayFragment);
        fragments.add(mOrderOnGoingFragment);
        fragments.add(mOrderCompleteFragment);
        fragments.add(mOrderCancelFragment);


        mOrderXtabLayout.addTab(mOrderXtabLayout.newTab().setText("待付款"));
        mOrderXtabLayout.addTab(mOrderXtabLayout.newTab().setText("进行中"));
        mOrderXtabLayout.addTab(mOrderXtabLayout.newTab().setText("已完成"));
        mOrderXtabLayout.addTab(mOrderXtabLayout.newTab().setText("已取消"));

        pagerAdapter = new CommPagerAdapter(getSupportFragmentManager(), fragments, new String[]{"待付款", "进行中", "已完成", "已取消"});
        mOrderViewPager.setAdapter(pagerAdapter);
        mOrderXtabLayout.setupWithViewPager(mOrderViewPager);

        mOrderXtabLayout.getTabAt(0).select();
        mOrderXtabLayout.setupWithViewPager(mOrderViewPager);
    }

    @Override
    protected void initData() {

    }
}
