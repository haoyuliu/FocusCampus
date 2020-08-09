package com.sam.rental.ui.activity;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.androidkun.xtablayout.XTabLayout;
import com.sam.base.BaseActivity;
import com.sam.rental.R;
import com.sam.rental.common.MyActivity;
import com.sam.rental.ui.adapter.CommPagerAdapter;
import com.sam.rental.ui.fragment.FansFragment;
import com.sam.rental.ui.fragment.FocusFragment;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * description 粉丝关注人页面
 */
public class FocusActivity extends MyActivity {
    @BindView(R.id.focus_tablayout)
    XTabLayout mFocusXTabLayout;
    @BindView(R.id.focus_viewpager)
    ViewPager mFocusViewPager;
    private FocusFragment mFocusFragment;
    private FansFragment mFansFragment;
    private CommPagerAdapter pagerAdapter;
    private ArrayList<Fragment> fragments = new ArrayList<>();


    @Override
    protected int getLayoutId() {
        return R.layout.activity_focus;
    }

    @Override
    protected void initView() {
        mFocusFragment = new FocusFragment();
        mFansFragment = new FansFragment();
        fragments.add(mFocusFragment);
        fragments.add(mFansFragment);
        mFocusXTabLayout.addTab(mFocusXTabLayout.newTab().setText("关注"));
        mFocusXTabLayout.addTab(mFocusXTabLayout.newTab().setText("粉丝"));

        pagerAdapter = new CommPagerAdapter(getSupportFragmentManager(), fragments, new String[]{"关注", "粉丝"});
        mFocusViewPager.setAdapter(pagerAdapter);
        mFocusXTabLayout.setupWithViewPager(mFocusViewPager);

        mFocusXTabLayout.getTabAt(0).select();
        mFocusXTabLayout.setupWithViewPager(mFocusViewPager);
    }

    @Override
    protected void initData() {

    }
}
