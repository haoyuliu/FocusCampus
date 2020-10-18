package com.sam.rentalcar.ui.activity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sam.rentalcar.R;
import com.sam.rentalcar.adapter.RentalCarCouponListAdapter;
import com.sam.rentalcar.common.MyActivity;
import com.sam.rentalcar.common.MyApplication;
import com.sam.rentalcar.http.net.RetrofitClient;
import com.sam.rentalcar.http.response.GetUserCouponListResponseBean;
import com.sam.rentalcar.utils.SPUtils;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 优惠券列表
 */
public class CouponListActivity extends MyActivity {

    @BindView(R.id.rv_coupon)
    RecyclerView mRecyclerView;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_coupon_list;
    }

    @Override
    protected void initView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }

    @Override
    protected void initData() {
        String token = SPUtils.getInstance(MyApplication.getInstance()).getString("token");
        RetrofitClient.getRetrofitService().getUserCouponList(token).enqueue(new Callback<GetUserCouponListResponseBean>() {
            @Override
            public void onResponse(Call<GetUserCouponListResponseBean> call, Response<GetUserCouponListResponseBean> response) {
                GetUserCouponListResponseBean couponListResponseBean = response.body();
                if (couponListResponseBean.getCode().equals("200")) {
                    RentalCarCouponListAdapter adapter = new RentalCarCouponListAdapter(CouponListActivity.this, couponListResponseBean.getData());
                    mRecyclerView.setAdapter(adapter);
                }
                toast("获取数据失败");
            }

            @Override
            public void onFailure(Call<GetUserCouponListResponseBean> call, Throwable t) {
                toast("获取数据失败");
            }
        });
    }
}
