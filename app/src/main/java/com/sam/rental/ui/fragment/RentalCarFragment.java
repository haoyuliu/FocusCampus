package com.sam.rental.ui.fragment;

import android.view.View;
import android.widget.ImageView;

import com.sam.rental.R;
import com.sam.rental.aop.SingleClick;
import com.sam.rental.common.MyFragment;
import com.sam.rental.http.glide.GlideApp;
import com.sam.rental.ui.activity.HomeActivity;
import com.sam.widget.view.CountdownView;
import com.sam.widget.view.SwitchButton;

import butterknife.BindView;

/**
 * author : Android 轮子哥
 * github : https://github.com/getActivity/AndroidProject
 * time   : 2018/10/18
 * desc   : 租车
 */
public final class RentalCarFragment extends MyFragment<HomeActivity> {


    public static RentalCarFragment newInstance() {
        return new RentalCarFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_rental_car;
    }

    @Override
    protected void initView() {
        //setOnClickListener(R.id.cv_test_countdown);
    }

    @Override
    protected void initData() {
       /* GlideApp.with(this)
                .load(R.drawable.bg_launcher)
                .circleCrop()
                .into(mCircleView);*/
    }

    @SingleClick
    @Override
    public void onClick(View v) {
       /* if (v.getId() == R.id.cv_test_countdown) {
            toast(R.string.common_code_send_hint);
            mCountdownView.start();
        }*/
    }

    @Override
    public boolean isStatusBarEnabled() {
        // 使用沉浸式状态栏
        return !super.isStatusBarEnabled();
    }

}