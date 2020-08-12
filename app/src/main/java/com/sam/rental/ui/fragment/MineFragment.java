package com.sam.rental.ui.fragment;

import android.view.View;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.androidkun.xtablayout.XTabLayout;
import com.hjq.bar.OnTitleBarListener;
import com.hjq.bar.TitleBar;
import com.sam.rental.R;
import com.sam.rental.aop.SingleClick;
import com.sam.rental.common.MyFragment;
import com.sam.rental.ui.activity.FocusActivity;
import com.sam.rental.ui.activity.HomeActivity;
import com.sam.rental.ui.activity.PersonalDataActivity;
import com.sam.rental.ui.activity.SettingActivity;
import com.sam.rental.ui.adapter.CommPagerAdapter;
import com.sam.rental.widget.CircleImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * desc: 我的
 */
public final class MineFragment extends MyFragment<HomeActivity> {
    @BindView(R.id.mine_xtab_layout)
    XTabLayout mMineXtabLayout;

    @BindView(R.id.mine_view_pager)
    ViewPager mViewPager;

    @BindView(R.id.iv_head)
    CircleImageView mHeadView;

    @BindView(R.id.edit_mine_resource)
    TextView mEidtMineTextView;


    private PersonalProductionFragment mMineProductionFragment;
    private PersonalLoveFragment mPraiseFragment;
    private CommPagerAdapter pagerAdapter;
    private ArrayList<Fragment> fragments = new ArrayList<>();

    public static MineFragment newInstance() {
        return new MineFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initView() {
        mMineProductionFragment = new PersonalProductionFragment();
        mPraiseFragment = new PersonalLoveFragment();
        fragments.add(mMineProductionFragment);
        fragments.add(mPraiseFragment);
        mMineXtabLayout.addTab(mMineXtabLayout.newTab().setText("作品"));
        mMineXtabLayout.addTab(mMineXtabLayout.newTab().setText("赞过"));

        pagerAdapter = new CommPagerAdapter(getChildFragmentManager(), fragments, new String[]{"作品", "赞过"});
        mViewPager.setAdapter(pagerAdapter);
        mMineXtabLayout.setupWithViewPager(mViewPager);

        mMineXtabLayout.getTabAt(0).select();
        mMineXtabLayout.setupWithViewPager(mViewPager);
        //点击事件
        setOnClickListener(R.id.ll_fans, R.id.ll_focus, R.id.iv_head, R.id.edit_mine_resource);
        //设置
        TitleBar titleBar = findViewById(R.id.mine_title_bar);
        titleBar.setOnTitleBarListener(new OnTitleBarListener() {
            @Override
            public void onLeftClick(View v) {

            }

            @Override
            public void onTitleClick(View v) {

            }

            @Override
            public void onRightClick(View v) {
                startActivity(SettingActivity.class);
            }
        });
    }

    @Override
    protected void initData() {

    }

    @SingleClick
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_focus:
                startActivity(FocusActivity.class);
                break;
            case R.id.ll_fans:
                startActivity(FocusActivity.class);
                break;
            case R.id.edit_mine_resource:
                startActivity(PersonalDataActivity.class);
                break;
            default:
                break;
        }
    }

    @Override
    public boolean isStatusBarEnabled() {
        // 使用沉浸式状态栏
        return !super.isStatusBarEnabled();
    }
}