package com.sam.rental.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.androidkun.xtablayout.XTabLayout;
import com.bumptech.glide.Glide;
import com.sam.rental.R;
import com.sam.rental.bean.VideoBean;
import com.sam.rental.common.MyActivity;
import com.sam.rental.ui.adapter.CommPagerAdapter;
import com.sam.rental.ui.fragment.PersonalLoveFragment;
import com.sam.rental.ui.fragment.PersonalProductionFragment;
import com.sam.rental.widget.CircleImageView;

import org.reactivestreams.Subscription;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * 点击查看个人主页的Activity
 */
public class PersonalHomeActivity extends MyActivity {
    @BindView(R.id.iv_head)
    CircleImageView ivHead;
    @BindView(R.id.ll_focus)
    LinearLayout llFocus;
    @BindView(R.id.ll_fans)
    LinearLayout llFans;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_focus)
    TextView tvFocus;
    @BindView(R.id.tablayout)
    XTabLayout tabLayout;
    @BindView(R.id.viewpager)
    ViewPager viewPager;
    @BindView(R.id.tv_nick_id)
    TextView tvNickId;
    @BindView(R.id.tv_nick_name)
    TextView tvNickName;
    @BindView(R.id.tv_desc)
    TextView tvDesc;

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
        // 获取数据
        Intent intent = getIntent();
        Glide.with(PersonalHomeActivity.this).load(intent.getStringExtra("HeadImage")).into(ivHead);
        tvNickId.setText("id :" + intent.getStringExtra("userId"));
        tvNickName.setText(intent.getStringExtra("NickName"));
        //tvDesc.setText(intent.getStringExtra("id"));

        Bundle bundle = new Bundle();
        bundle.putString("userId", intent.getStringExtra("userId"));

        mPersonalProductionFragment = new PersonalProductionFragment();
        mPersonalProductionFragment.setArguments(bundle);
        mPersonalLoveFragment = new PersonalLoveFragment();
        mPersonalLoveFragment.setArguments(bundle);

        fragments.add(mPersonalProductionFragment);
        fragments.add(mPersonalLoveFragment);
        tabLayout.addTab(tabLayout.newTab().setText("作品"));
        tabLayout.addTab(tabLayout.newTab().setText("喜欢"));

        pagerAdapter = new CommPagerAdapter(getSupportFragmentManager(), fragments, new String[]{"作品", "喜欢"});
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).select();
        tabLayout.setupWithViewPager(viewPager);
       /* tvAddfocus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), ChatActivity.class);
                intent.putExtra(EaseConstant.EXTRA_USER_ID, "111");
                intent.putExtra(EaseConstant.EXTRA_CHAT_TYPE, EMMessage.ChatType.Chat);
                startActivity(intent);

            }
        });*/
    }

    @Override
    protected void initData() {

    }

}
