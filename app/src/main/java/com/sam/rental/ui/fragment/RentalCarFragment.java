package com.sam.rental.ui.fragment;

import android.view.View;
import android.widget.ImageView;

import com.sam.rental.R;
import com.sam.rental.aop.SingleClick;
import com.sam.rental.common.MyFragment;
import com.sam.rental.http.glide.GlideApp;
import com.sam.rental.ui.activity.ChoiceCarActivity;
import com.sam.rental.ui.activity.HomeActivity;
import com.sam.rental.ui.activity.RentalCarOrderActivity;
import com.sam.widget.view.CountdownView;
import com.sam.widget.view.SwitchButton;

import butterknife.BindView;

/**
 * desc   : 租车页面
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
        setOnClickListener(R.id.but_choice_car, R.id.rental_car_order);
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
        switch (v.getId()) {
            case R.id.but_choice_car:
                startActivity(ChoiceCarActivity.class);
                break;
            case R.id.rental_car_order:
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

}