package com.sam.rental.ui.fragment;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.androidkun.xtablayout.XTabLayout;
import com.sam.rental.R;
import com.sam.rental.common.MyFragment;
import com.sam.rental.ui.activity.HomeActivity;
import com.sam.rental.ui.adapter.CommPagerAdapter;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * author : Android 轮子哥
 * time   : 2018/10/18
 * desc   : 这个是项目的HomeFragment，整体是一个ViewPager
 */
public class HomeFragment extends MyFragment<HomeActivity> {

    @BindView(R.id.home_tab_title)
    XTabLayout mXTabLayout;

    @BindView(R.id.home_view_pager)
    ViewPager mViewPager;
    private CurrentLocationFragment currentLocationFragment;
    private RecommendFragment recommendFragment;
    private CommPagerAdapter pagerAdapter;
    private ArrayList<Fragment> fragments = new ArrayList<>();
    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView() {
        currentLocationFragment = new CurrentLocationFragment();
        recommendFragment = new RecommendFragment();
        fragments.add(currentLocationFragment);
        fragments.add(recommendFragment);
        mXTabLayout.addTab(mXTabLayout.newTab().setText("关注"));
        mXTabLayout.addTab(mXTabLayout.newTab().setText("推荐"));

        pagerAdapter = new CommPagerAdapter(getChildFragmentManager(), fragments, new String[]{"关注", "推荐"});
        mViewPager.setAdapter(pagerAdapter);
        mXTabLayout.setupWithViewPager(mViewPager);

        mXTabLayout.getTabAt(1).select();
        mXTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    protected void initData() {

    }
}