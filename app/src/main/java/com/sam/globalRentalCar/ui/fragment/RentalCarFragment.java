package com.sam.globalRentalCar.ui.fragment;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.LinearLayout;

import com.sam.globalRentalCar.R;
import com.sam.globalRentalCar.aop.SingleClick;
import com.sam.globalRentalCar.common.MyFragment;
import com.sam.globalRentalCar.constant.Constant;
import com.sam.globalRentalCar.http.net.RetrofitApi;
import com.sam.globalRentalCar.http.net.RetrofitClient;
import com.sam.globalRentalCar.http.response.SwitchResponseBean;
import com.sam.globalRentalCar.ui.activity.BrowserActivity;
import com.sam.globalRentalCar.ui.activity.HomeActivity;
import com.sam.globalRentalCar.ui.activity.RentalCarActivity;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * desc   : 租车页面
 */
public final class RentalCarFragment extends MyFragment<HomeActivity> {
    @BindView(R.id.ll_rental_car)
    LinearLayout mLinearLayoutRentalCar;

    @BindView(R.id.ll_buy_car)
    LinearLayout mLinearLayoutBuyCar;

    @BindView(R.id.ll_maintain)
    LinearLayout mLinearLayoutMaintain;

    @BindView(R.id.ll_financial)
    LinearLayout mLinearLayoutFinanical;

    @BindView(R.id.ll_query)
    LinearLayout mLinearLayoutQuery;

    public static RentalCarFragment newInstance() {
        return new RentalCarFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_rental_car;
    }

    @Override
    protected void initView() {
        setOnClickListener(R.id.ll_rental_car, R.id.ll_buy_car, R.id.ll_maintain, R.id.ll_financial, R.id.ll_query);
    }

    @Override
    protected void initData() {
        // 开关
        RetrofitClient.getRetrofitService().getSwitch().enqueue(new Callback<SwitchResponseBean>() {
            @Override
            public void onResponse(Call<SwitchResponseBean> call, Response<SwitchResponseBean> response) {
                SwitchResponseBean body = response.body();
                if (body.getCode().equals("200")) {
                    if (!body.isData()) {
                        //打开
                        mLinearLayoutBuyCar.setVisibility(View.VISIBLE);
                        mLinearLayoutMaintain.setVisibility(View.VISIBLE);
                        mLinearLayoutFinanical.setVisibility(View.VISIBLE);
                    } else {
                        //隐藏
                        mLinearLayoutBuyCar.setVisibility(View.GONE);
                        mLinearLayoutMaintain.setVisibility(View.GONE);
                        mLinearLayoutFinanical.setVisibility(View.GONE);
                    }
                } else {
                    //隐藏
                    mLinearLayoutBuyCar.setVisibility(View.GONE);
                    mLinearLayoutMaintain.setVisibility(View.GONE);
                    mLinearLayoutFinanical.setVisibility(View.GONE);
                }

            }

            @Override
            public void onFailure(Call<SwitchResponseBean> call, Throwable t) {

            }
        });

    }


    @SingleClick
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_rental_car:
                //租车服务
                startActivity(RentalCarActivity.class);
                break;
            case R.id.ll_buy_car:
                //新车购买
                break;
            case R.id.ll_maintain:
                // 维修服务
                break;
            case R.id.ll_financial:
                // 金融服务
                break;
            case R.id.ll_query:
                // 违章查询
                BrowserActivity.start(getContext(), Constant.QUERY_REGULATIONS_URL);
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