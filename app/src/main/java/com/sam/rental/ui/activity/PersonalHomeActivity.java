package com.sam.rental.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.alibaba.sdk.android.vod.upload.common.utils.StringUtil;
import com.androidkun.xtablayout.XTabLayout;
import com.bumptech.glide.Glide;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.easeui.EaseConstant;
import com.sam.rental.R;
import com.sam.rental.bean.FansBean;
import com.sam.rental.bean.VideoBean;
import com.sam.rental.common.MyActivity;
import com.sam.rental.http.net.RetrofitClient;
import com.sam.rental.http.response.FollowResponseBean;
import com.sam.rental.http.response.GetUserHomePagerMessageResponseBean;
import com.sam.rental.ui.adapter.CommPagerAdapter;
import com.sam.rental.ui.fragment.MineFragment;
import com.sam.rental.ui.fragment.PersonalLoveFragment;
import com.sam.rental.ui.fragment.PersonalProductionFragment;
import com.sam.rental.utils.SPUtils;
import com.sam.rental.widget.CircleImageView;

import org.reactivestreams.Subscription;

import java.net.HttpURLConnection;
import java.util.ArrayList;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 点击某个用户的个人主页的Activity
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
    @BindView(R.id.personal_hone_focus)
    TextView mPersonalHomeFocus;
    @BindView(R.id.personal_home_send_message)
    TextView mPersonalHomeSendMessage;

    @BindView(R.id.user_home_zan)
    TextView mPersonalHomeZan;
    @BindView(R.id.user_home_fans)
    TextView mPersonalHomeFans;
    @BindView(R.id.user_home_follow)
    TextView mPersonalHomeFollow;


    private ArrayList<Fragment> fragments = new ArrayList<>();
    private CommPagerAdapter pagerAdapter;
    private VideoBean.UserBean curUserBean;
    private Subscription subscription;
    private PersonalProductionFragment mPersonalProductionFragment;
    private PersonalLoveFragment mPersonalLoveFragment;
    private int follow;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_personal_home;
    }

    @Override
    protected void initView() {
        // 获取数据
        Intent intent = getIntent();
        String userId = intent.getStringExtra("userId");
        getUserData(userId);
        String token = SPUtils.getInstance(PersonalHomeActivity.this).getString("token");

        mPersonalHomeFocus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (StringUtil.isEmpty(token)) {
                    startActivity(LoginActivity.class);
                    return;
                }
                RetrofitClient.getRetrofitService().FocusUser(token, intent.getStringExtra("userId"), follow + "").enqueue(new Callback<FollowResponseBean>() {
                    @Override
                    public void onResponse(Call<FollowResponseBean> call, Response<FollowResponseBean> response) {
                        if (response.code() == HttpURLConnection.HTTP_OK) {
                            toast(response.message());
                            mPersonalHomeFocus.setText("已关注");
                        } else {
                            toast(response.message());
                            mPersonalHomeFocus.setText("关注");
                        }

                    }

                    @Override
                    public void onFailure(Call<FollowResponseBean> call, Throwable t) {
                        toast(t.getMessage());
                        mPersonalHomeFocus.setText("关注");
                    }
                });
            }
        });
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

        //发送消息
        mPersonalHomeSendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), ChatActivity.class);
                intent.putExtra(EaseConstant.EXTRA_USER_ID, getIntent().getStringExtra("huid"));
                intent.putExtra(EaseConstant.EXTRA_CHAT_TYPE, EMMessage.ChatType.Chat);
                startActivity(intent);

            }
        });
    }

    private void getUserData(String userId) {
        RetrofitClient.getRetrofitService().getPersonalHomeMessageParams(userId)
                .enqueue(new Callback<GetUserHomePagerMessageResponseBean>() {
                    @Override
                    public void onResponse(Call<GetUserHomePagerMessageResponseBean> call, Response<GetUserHomePagerMessageResponseBean> response) {
                        if (response.code() == HttpURLConnection.HTTP_OK) {
                            Glide.with(PersonalHomeActivity.this).load(response.body().getData().getHeadImg()).into(ivHead);
                            tvNickName.setText(response.body().getData().getNickName());
                            tvNickId.setText("ID:" + response.body().getData().getUserId() + "");
                            if (response.body().getData().getUserDesc() != null) {
                                tvDesc.setText(response.body().getData().getUserDesc());
                            }
                            mPersonalHomeZan.setText(response.body().getData().getLikesCount() + "");
                            mPersonalHomeFans.setText(response.body().getData().getFansCount() + "");
                            mPersonalHomeFollow.setText(response.body().getData().getFollowCount() + "");
                            follow = response.body().getData().getHasFollowed();
                            if (response.body().getData().getHasFollowed() == 0) {
                                mPersonalHomeFocus.setText("关注");
                            } else {
                                mPersonalHomeFocus.setText("已关注");
                            }
                        }

                    }

                    @Override
                    public void onFailure(Call<GetUserHomePagerMessageResponseBean> call, Throwable t) {

                    }
                });
    }

    @Override
    protected void initData() {

    }

}
