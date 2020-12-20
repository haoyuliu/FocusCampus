package com.sam.globalRentalCar.ui.fragment;

import android.util.Log;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sam.globalRentalCar.R;
import com.sam.globalRentalCar.common.MyFragment;
import com.sam.globalRentalCar.http.net.RetrofitClient;
import com.sam.globalRentalCar.http.response.OrderListResponseBean;
import com.sam.globalRentalCar.ui.activity.HomeActivity;
import com.sam.globalRentalCar.adapter.OrderAdapter;
import com.sam.globalRentalCar.utils.SPUtils;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * desc:订单已完成页面
 */
public class OrderCompleteFragment extends MyFragment<HomeActivity> {
    public static final String TAG = "OrderCompleteFragment";

    public static final int status = 2;

    @BindView(R.id.order_complete_recyclerview)
    RecyclerView mRecyclerViewComplete;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_order_complete;
    }

    @Override
    protected void initView() {
        mRecyclerViewComplete.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void onRightClick(View v) {
        Log.d(TAG, "right_click");
    }

    @Override
    protected void initData() {
        String userId = SPUtils.getInstance(getActivity()).getString("UserId");
        RetrofitClient.getRetrofitService().getUserOrderListInfo(userId, status).enqueue(new Callback<OrderListResponseBean>() {
            @Override
            public void onResponse(Call<OrderListResponseBean> call, Response<OrderListResponseBean> response) {
                OrderListResponseBean orderListResponseBean = response.body();
                if (orderListResponseBean.getCode().equals("200")) {

                    if (orderListResponseBean.getData().size() > 0) {
                        OrderAdapter orderAdapter = new OrderAdapter(getContext(), orderListResponseBean.getData());
                        orderAdapter.setStatus(status);
                        mRecyclerViewComplete.setAdapter(orderAdapter);
                    }

                } else {
                    toast("获取数据失败");
                }

            }

            @Override
            public void onFailure(Call<OrderListResponseBean> call, Throwable t) {
                toast("获取数据失败");
            }
        });

    }

    @Override
    public boolean isStatusBarEnabled() {
        // 使用沉浸式状态栏
        return !super.isStatusBarEnabled();
    }


}