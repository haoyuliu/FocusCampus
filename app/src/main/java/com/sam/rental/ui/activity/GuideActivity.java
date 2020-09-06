package com.sam.rental.ui.activity;

import android.view.View;
import android.widget.ImageView;

import androidx.viewpager.widget.ViewPager;

import com.rd.PageIndicatorView;
import com.sam.rental.R;
import com.sam.rental.aop.SingleClick;
import com.sam.rental.common.MyActivity;
import com.sam.rental.ui.pager.GuidePagerAdapter;

import butterknife.BindView;

/**
 * desc   : APP 引导页
 */
public final class GuideActivity extends MyActivity
        implements ViewPager.OnPageChangeListener {

    @BindView(R.id.vp_guide_pager)
    ViewPager mViewPager;
    @BindView(R.id.pv_guide_indicator)
    PageIndicatorView mIndicatorView;
    @BindView(R.id.iv_guide_complete)
    ImageView mCompleteView;

    private GuidePagerAdapter mPagerAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_guide;
    }

    @Override
    protected void initView() {
        mIndicatorView.setViewPager(mViewPager);
        setOnClickListener(R.id.iv_guide_complete);
    }

    @Override
    protected void initData() {
        mPagerAdapter = new GuidePagerAdapter();
        mViewPager.setAdapter(mPagerAdapter);
        mViewPager.addOnPageChangeListener(this);
    }

    @SingleClick
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.iv_guide_complete) {
            startActivity(HomeActivity.class);
            finish();
        }
    }

    /**
     * {@link ViewPager.OnPageChangeListener}
     */

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        if (mViewPager.getCurrentItem() == mPagerAdapter.getCount() - 1 && positionOffsetPixels > 0) {
            mCompleteView.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onPageSelected(int position) {
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        if (state == ViewPager.SCROLL_STATE_IDLE) {
            mCompleteView.setVisibility(mViewPager.getCurrentItem() == mPagerAdapter.getCount() - 1 ? View.VISIBLE : View.INVISIBLE);
        }
    }

    @Override
    public boolean isSwipeEnable() {
        return false;
    }
}