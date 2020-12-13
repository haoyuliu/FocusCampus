package com.sam.rentalcar.ui.activity;

import android.widget.TextView;

import com.sam.rentalcar.R;
import com.sam.rentalcar.common.MyActivity;
import com.sam.rentalcar.constant.Constant;
import com.sam.rentalcar.http.net.RetrofitClient;
import com.sam.rentalcar.http.response.OrderDetailResponseBean;
import com.sam.rentalcar.utils.SPUtils;
import com.sam.widget.layout.SettingBar;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 订单详情页面
 */
public class OrderDetailActivity extends MyActivity {

    @BindView(R.id.order_detail_name)
    TextView mTextViewOrderDetailName;

    @BindView(R.id.order_detail_place_get)
    TextView mTextViewOrderDetailPlaceGet;

    @BindView(R.id.order_detail_place_return)
    TextView mTextViewOrderDetailPlaceReturn;

    @BindView(R.id.order_detail_get_time)
    TextView mTextViewOrderDetailGetTime;

    @BindView(R.id.order_detail_return_time)
    TextView mTextViewOrderDetailReturnTime;

    @BindView(R.id.order_detail_fee_all)
    SettingBar mSettingBarAllFee;

    @BindView(R.id.order_detail_rental_fee)
    SettingBar mSettingBarRentalFee;

    @BindView(R.id.order_detail_discount_fee)
    SettingBar mSettingBarDiscountFee;

    @BindView(R.id.order_detail_service_fee)
    SettingBar mSettingBarServiceFee;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_order_detail;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        String detailOrderId = getIntent().getStringExtra(Constant.ORDER_ID);
        String userId = SPUtils.getInstance(getActivity()).getString("UserId");
        RetrofitClient.getRetrofitService().getUserOrderDetail(userId, detailOrderId).enqueue(new Callback<OrderDetailResponseBean>() {
            @Override
            public void onResponse(Call<OrderDetailResponseBean> call, Response<OrderDetailResponseBean> response) {
                OrderDetailResponseBean body = response.body();
                if (body.getCode().equals("200")) {
                    OrderDetailResponseBean.DataBean dataBean = body.getData();
                    mTextViewOrderDetailName.setText(dataBean.getName());
                    mTextViewOrderDetailPlaceGet.setText(dataBean.getRemark());
                    mTextViewOrderDetailPlaceReturn.setText(dataBean.getName());
                    mTextViewOrderDetailGetTime.setText(dataBean.getCreateTime() + "-");
                    mTextViewOrderDetailReturnTime.setText(dataBean.getCreateTime());
                    mSettingBarAllFee.setRightText("￥" + dataBean.getMoney());
                    mSettingBarRentalFee.setRightText("￥" + dataBean.getMoney());
                    mSettingBarDiscountFee.setRightText("￥" + dataBean.getMoney());
                    mSettingBarServiceFee.setRightText("￥" + dataBean.getMoney());
                } else {
                    toast("获取数据失败");
                }
            }

            @Override
            public void onFailure(Call<OrderDetailResponseBean> call, Throwable t) {
                toast("网络错误");
            }
        });

    }
}
