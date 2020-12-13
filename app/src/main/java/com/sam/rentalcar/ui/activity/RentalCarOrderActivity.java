package com.sam.rentalcar.ui.activity;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.androidkun.xtablayout.XTabLayout;
import com.sam.rentalcar.R;
import com.sam.rentalcar.common.MyActivity;
import com.sam.rentalcar.ui.adapter.CommPagerAdapter;
import com.sam.rentalcar.ui.fragment.OrderCancelFragment;
import com.sam.rentalcar.ui.fragment.OrderCompleteFragment;
import com.sam.rentalcar.ui.fragment.OrderOnGoingFragment;
import com.sam.rentalcar.ui.fragment.OrderWaitPayFragment;

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
        mOrderCompleteFragment = new OrderCompleteFragment();
        mOrderOnGoingFragment = new OrderOnGoingFragment();
        mOrderCancelFragment = new OrderCancelFragment();
        mOrderWaitPayFragment = new OrderWaitPayFragment();

        fragments.add(mOrderCompleteFragment);
        fragments.add(mOrderOnGoingFragment);
        fragments.add(mOrderCancelFragment);
        fragments.add(mOrderWaitPayFragment);

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
