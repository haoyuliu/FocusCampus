package com.sam.rental.ui.activity;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.androidkun.xtablayout.XTabLayout;
import com.sam.base.BaseActivity;
import com.sam.rental.R;
import com.sam.rental.ui.adapter.CommPagerAdapter;
import com.sam.rental.ui.fragment.FansFragment;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * description 粉丝关注人页面
 */
public class FocusActivity extends BaseActivity {
    @BindView(R.id.tablayout)
    XTabLayout tabLayout;
    @BindView(R.id.viewpager)
    ViewPager viewPager;
    private ArrayList<Fragment> fragments = new ArrayList<>();
    private CommPagerAdapter pagerAdapter;
    private String[] titles = new String[]{"关注 128", "粉丝 128", "推荐关注"};


    @Override
    protected int getLayoutId() {
        return R.layout.activity_focus;
    }

    @Override
    protected void initView() {
        for (int i = 0; i < titles.length; i++) {
            fragments.add(new FansFragment());
            tabLayout.addTab(tabLayout.newTab().setText(titles[i]));
        }

        pagerAdapter = new CommPagerAdapter(getSupportFragmentManager(), fragments, titles);
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    protected void initData() {

    }
}
