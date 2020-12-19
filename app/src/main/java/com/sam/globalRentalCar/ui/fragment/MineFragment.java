package com.sam.globalRentalCar.ui.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.alibaba.sdk.android.vod.upload.common.utils.StringUtil;
import com.androidkun.xtablayout.XTabLayout;
import com.bumptech.glide.Glide;
import com.sam.globalRentalCar.R;
import com.sam.globalRentalCar.videoplayer.PersonalLoveFragment;
import com.sam.globalRentalCar.videoplayer.PersonalProductionFragment;
import com.sam.globalRentalCar.aop.SingleClick;
import com.sam.globalRentalCar.common.MyFragment;
import com.sam.globalRentalCar.http.net.RetrofitClient;
import com.sam.globalRentalCar.http.response.GetUserHomePagerMessageResponseBean;
import com.sam.globalRentalCar.ui.activity.FocusActivity;
import com.sam.globalRentalCar.ui.activity.HomeActivity;
import com.sam.globalRentalCar.ui.activity.PersonalDataActivity;
import com.sam.globalRentalCar.ui.activity.RentalCarOrderActivity;
import com.sam.globalRentalCar.ui.activity.SettingActivity;
import com.sam.globalRentalCar.ui.adapter.CommPagerAdapter;
import com.sam.globalRentalCar.utils.SPUtils;
import com.sam.globalRentalCar.widget.CircleImageView;

import java.net.HttpURLConnection;
import java.util.ArrayList;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * desc: 我的页面
 */
public final class MineFragment extends MyFragment<HomeActivity> {
    public static final String TAG = "MineFragment";
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

    @BindView(R.id.tv_mine_zan)
    TextView mTextViewMineZan;

    @BindView(R.id.tv_mine_fans)
    TextView mTextViewMineFans;

    @BindView(R.id.tv_mine_focus)
    TextView mTextViewMineFocus;

    @BindView(R.id.mine_sex_image)
    ImageView mImageViewSex;

    @BindView(R.id.tv_mine_desc)
    TextView mTextViewUserDesc;
    @BindView(R.id.mine_add)
    TextView mTextViewMineAdd;

    @BindView(R.id.ll_order_rental_car)
    LinearLayout mLinearLayoutOrderRentalCar;

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
        setOnClickListener(R.id.ll_fans, R.id.ll_focus, R.id.mine_user_head, R.id.edit_mine_resource, R.id.mine_add, R.id.ll_order_rental_car);
    }

    @Override
    public void onRightClick(View v) {
        Log.d(TAG, "right_click");
        startActivity(SettingActivity.class);
    }

    @Override
    protected void initData() {
        Log.d(TAG, "init_data");
        // 获取数据
        String userId = SPUtils.getInstance(getContext()).getString("UserId");
        if (!StringUtil.isEmpty(userId)) {
            getMineFragmentData(userId);
        }
    }

    private void getMineFragmentData(String userId) {
        Log.d(TAG, "getMineFragmentData");
        RetrofitClient.getRetrofitService().getPersonalHomeMessageParams(userId)
                .enqueue(new Callback<GetUserHomePagerMessageResponseBean>() {
                    @Override
                    public void onResponse(Call<GetUserHomePagerMessageResponseBean> call, Response<GetUserHomePagerMessageResponseBean> response) {
                        GetUserHomePagerMessageResponseBean getUserHomePagerMessageResponseBean = response.body();
                        if (Integer.parseInt(getUserHomePagerMessageResponseBean.getCode()) == HttpURLConnection.HTTP_OK) {
                            Glide.with(getActivity()).load(response.body().getData().getHeadImg()).into(mHeadView);
                            mTextViewUserID.setText("ID:" + response.body().getData().getUserCode() + "");
                            mTextViewUserNickName.setText(response.body().getData().getNickName());

                            if (response.body().getData().getUserDesc() != null) {
                                mTextViewUserDesc.setText(response.body().getData().getUserDesc());
                            } else {
                                mTextViewUserDesc.setText("你还没有设置签名");
                            }
                            if (response.body().getData().getUserSex() == 1) {
                                mTextViewUserSexLocation.setText("女");
                                mImageViewSex.setBackground(getResources().getDrawable(R.drawable.nv));
                            } else {
                                mTextViewUserSexLocation.setText("男");
                                mImageViewSex.setBackground(getResources().getDrawable(R.drawable.nan));
                            }
                            mTextViewMineZan.setText(response.body().getData().getLikesCount() + "");
                            mTextViewMineFans.setText(response.body().getData().getFansCount() + "");
                            mTextViewMineFocus.setText(response.body().getData().getFollowCount() + "");
                        }

                    }

                    @Override
                    public void onFailure(Call<GetUserHomePagerMessageResponseBean> call, Throwable t) {

                    }
                });
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
            case R.id.ll_order_rental_car:
                startActivity(RentalCarOrderActivity.class);
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

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
        initData();
    }
}