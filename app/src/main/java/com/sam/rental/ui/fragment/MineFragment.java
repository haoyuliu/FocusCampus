package com.sam.rental.ui.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.androidkun.xtablayout.XTabLayout;
import com.bumptech.glide.Glide;
import com.hjq.bar.OnTitleBarListener;
import com.hjq.bar.TitleBar;
import com.sam.rental.R;
import com.sam.rental.aop.SingleClick;
import com.sam.rental.common.MyFragment;
import com.sam.rental.ui.activity.FocusActivity;
import com.sam.rental.ui.activity.HomeActivity;
import com.sam.rental.ui.activity.LoginActivity;
import com.sam.rental.ui.activity.PersonalDataActivity;
import com.sam.rental.ui.activity.SettingActivity;
import com.sam.rental.ui.adapter.CommPagerAdapter;
import com.sam.rental.utils.SPUtils;
import com.sam.rental.widget.CircleImageView;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * desc: 我的
 */
public final class MineFragment extends MyFragment<HomeActivity> {
    @BindView(R.id.mine_xtab_layout)
    XTabLayout mMineXtabLayout;

    @BindView(R.id.mine_view_pager)
    ViewPager mViewPager;

    @BindView(R.id.mine_user_head)
    CircleImageView mHeadView;

    @BindView(R.id.edit_mine_resource)
    TextView mEidtMineTextView;

    @BindView(R.id.mine_user_id)
    TextView mTextViewUserID;

    @BindView(R.id.mine_user_nick_name)
    TextView mTextViewUserNickName;

    @BindView(R.id.mine_sex_location)
    TextView mTextViewUserSexLocation;

    @BindView(R.id.tv_mine_desc)
    TextView mTextViewUserDesc;
    @BindView(R.id.mine_add)
    TextView mTextViewMineAdd;

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
        SPUtils.getInstance(getContext()).getString("token");
        SPUtils.getInstance(getContext()).getString("UserId");
        SPUtils.getInstance(getContext()).getString("HeadImage");
        SPUtils.getInstance(getContext()).getString("NickName");
        SPUtils.getInstance(getContext()).getString("userSex");
        SPUtils.getInstance(getContext()).getString("userDesc");
        SPUtils.getInstance(getContext()).getString("userBirthday");
        SPUtils.getInstance(getContext()).getString("userLocation");


        Glide.with(getContext()).load(SPUtils.getInstance(getContext()).getString("HeadImage")).into(mHeadView);
        mTextViewUserID.setText("ID:" + SPUtils.getInstance(getContext()).getString("UserId"));
        mTextViewUserNickName.setText(SPUtils.getInstance(getContext()).getString("NickName"));
        //mTextViewUserSexLocation.setText(SPUtils.getInstance(getContext()).getString("userSex"));
       // mTextViewUserDesc.setText(SPUtils.getInstance(getContext()).getString("userDesc"));

        Bundle bundle = new Bundle();
        bundle.putString("userId", SPUtils.getInstance(getContext()).getString("UserId"));
        mMineProductionFragment = new PersonalProductionFragment();
        mMineProductionFragment.setArguments(bundle);
        mPraiseFragment = new PersonalLoveFragment();
        mPraiseFragment.setArguments(bundle);
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
        setOnClickListener(R.id.ll_fans, R.id.ll_focus, R.id.mine_user_head, R.id.edit_mine_resource, R.id.mine_add);
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
            case R.id.mine_add:
                startActivity(FocusActivity.class);
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