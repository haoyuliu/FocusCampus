package com.sam.rental.ui.activity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.androidkun.xtablayout.XTabLayout;
import com.google.android.material.appbar.AppBarLayout;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.easeui.EaseConstant;
import com.sam.rental.R;
import com.sam.rental.bean.VideoBean;
import com.sam.rental.common.MyActivity;
import com.sam.rental.ui.adapter.CommPagerAdapter;
import com.sam.rental.ui.fragment.PersonalLoveFragment;
import com.sam.rental.ui.fragment.PersonalProductionFragment;
import com.sam.rental.widget.CircleImageView;
import com.sam.rental.widget.IconFontTextView;

import org.reactivestreams.Subscription;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * 个人主页的Activity
 */
public class PersonalHomeActivity extends MyActivity {
    @BindView(R.id.iv_bg)
    ImageView ivBg;
    @BindView(R.id.iv_head)
    CircleImageView ivHead;
    @BindView(R.id.rl_dropdown)
    RelativeLayout rlDropdown;
    @BindView(R.id.ll_focus)
    LinearLayout llFocus;
    @BindView(R.id.ll_fans)
    LinearLayout llFans;
    @BindView(R.id.iv_return)
    ImageView ivReturn;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_focus)
    TextView tvFocus;
    @BindView(R.id.iv_more)
    IconFontTextView ivMore;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tablayout)
    XTabLayout tabLayout;
    @BindView(R.id.appbarlayout)
    AppBarLayout appBarLayout;
    @BindView(R.id.viewpager)
    ViewPager viewPager;
    @BindView(R.id.tv_nickname)
    TextView tvNickname;
    @BindView(R.id.tv_sign)
    TextView tvSign;
    @BindView(R.id.tv_getlike_count)
    TextView tvGetLikeCount;
    @BindView(R.id.tv_focus_count)
    TextView tvFocusCount;
    @BindView(R.id.tv_fans_count)
    TextView tvFansCount;
    @BindView(R.id.tv_addfocus)
    TextView tvAddfocus;
    private ArrayList<Fragment> fragments = new ArrayList<>();
    private CommPagerAdapter pagerAdapter;
    private VideoBean.UserBean curUserBean;
    private Subscription subscription;
    private PersonalProductionFragment mPersonalProductionFragment;
    private PersonalLoveFragment mPersonalLoveFragment;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_personal_home;
    }

    @Override
    protected void initView() {
        mPersonalProductionFragment = new PersonalProductionFragment();
        mPersonalLoveFragment = new PersonalLoveFragment();
        fragments.add(mPersonalProductionFragment);
        fragments.add(mPersonalLoveFragment);
        tabLayout.addTab(tabLayout.newTab().setText("作品"));
        tabLayout.addTab(tabLayout.newTab().setText("喜欢"));

        pagerAdapter = new CommPagerAdapter(getSupportFragmentManager(), fragments, new String[]{"作品", "喜欢"});
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).select();
        tabLayout.setupWithViewPager(viewPager);
        tvAddfocus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvAddfocus.setText("发送消息");

                Intent intent = new Intent(getActivity(), ChatActivity.class);
                intent.putExtra(EaseConstant.EXTRA_USER_ID, "111");
                intent.putExtra(EaseConstant.EXTRA_CHAT_TYPE, EMMessage.ChatType.Chat);
                startActivity(intent);

            }
        });
    }

    @Override
    protected void initData() {

    }

}
