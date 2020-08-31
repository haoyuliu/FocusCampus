package com.sam.rental.ui.activity;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.androidkun.xtablayout.XTabLayout;
import com.sam.rental.R;
import com.sam.rental.common.MyActivity;
import com.sam.rental.ui.adapter.CommPagerAdapter;
import com.sam.rental.ui.fragment.OrderCompleteFragment;
import com.sam.rental.ui.fragment.OrderOnGoingFragment;
import com.sam.rental.ui.fragment.PersonalLoveFragment;
import com.sam.rental.ui.fragment.PersonalProductionFragment;

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

        fragments.add(mOrderCompleteFragment);
        fragments.add(mOrderOnGoingFragment);
        mOrderXtabLayout.addTab(mOrderXtabLayout.newTab().setText("已完成"));
        mOrderXtabLayout.addTab(mOrderXtabLayout.newTab().setText("进行中"));

        pagerAdapter = new CommPagerAdapter(getSupportFragmentManager(), fragments, new String[]{"已完成", "进行中"});
        mOrderViewPager.setAdapter(pagerAdapter);
        mOrderXtabLayout.setupWithViewPager(mOrderViewPager);

        mOrderXtabLayout.getTabAt(0).select();
        mOrderXtabLayout.setupWithViewPager(mOrderViewPager);
    }

    @Override
    protected void initData() {

    }
}
