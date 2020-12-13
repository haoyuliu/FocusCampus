package com.sam.rentalcar.ui.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatCheckBox;

import com.alibaba.sdk.android.vod.upload.common.utils.StringUtil;
import com.sam.rentalcar.R;
import com.sam.rentalcar.common.MyActivity;
import com.sam.rentalcar.constant.Constant;
import com.sam.rentalcar.http.net.RetrofitClient;
import com.sam.rentalcar.http.request.ConfirmOrderRequestBean;
import com.sam.rentalcar.http.response.ConfirmOrderResponseBean;
import com.sam.rentalcar.http.response.GetUserConfirmInfoResponseBean;
import com.sam.rentalcar.http.response.GetUserCouponListResponseBean;
import com.sam.rentalcar.utils.SPUtils;
import com.sam.widget.layout.SettingBar;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 订单确认界面
 */
public class OrderConfirmationActivity extends MyActivity {
    @BindView(R.id.tv_confirm_car_name)
    TextView mTextViewCarName;

    @BindView(R.id.iv_confirm_car)
    ImageView mImageViewConfirmCar;
    /**
     * 车辆租赁费
     */
    @BindView(R.id.car_rental_fee)
    SettingBar mSettingBarRentalFee;
    /**
     * 基础服务费
     */
    @BindView(R.id.car_base_fee)
    SettingBar mSettingBarBaseFee;

    /**
     * 尊享服务费
     */
    @BindView(R.id.service_fee)
    SettingBar mSettingBarServiceFee;

    @BindView(R.id.setting_bar_coupons)
    SettingBar mSettingBarCoupons;


    @BindView(R.id.confirm_order)
    Button mButtonConfirmOrder;

    @BindView(R.id.order_checkbox)
    AppCompatCheckBox mCheckBox;

    @BindView(R.id.start_time)
    TextView mTextViewStartTime;

    @BindView(R.id.end_time)
    TextView mTextViewEndTime;

    @BindView(R.id.pay_price)
    TextView mTextViewPayPrice;


    private String stringOrderEndMonth;
    private String stringOrderEndDay;
    private String stringOrderEndHour;
    private String stringOrderMin;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_order_confirmation;
    }

    @Override
    protected void initView() {
        // 设置获取的数据
        Intent orderIntent = getIntent();
        mTextViewStartTime.setText(orderIntent.getStringExtra(Constant.GET_CAR_DEFAULT_MONTH) + getString(R.string.common_month) + orderIntent.getStringExtra(Constant.GET_CAR_DEFAULT_DAY) + getString(R.string.common_day) + orderIntent.getStringExtra(Constant.GET_CAR_DEFAULT_HOUR) + ":" + orderIntent.getStringExtra(Constant.GET_CAR_DEFAULT_MIN));

        stringOrderEndMonth = orderIntent.getStringExtra(Constant.GET_CAR_END_MONTH);
        stringOrderEndDay = orderIntent.getStringExtra(Constant.GET_CAR_END_DAY);
        stringOrderEndHour = orderIntent.getStringExtra(Constant.GET_CAR_END_HOUR);
        stringOrderMin = orderIntent.getStringExtra(Constant.GET_CAR_END_MIN);

        SimpleDateFormat alldate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat yearDate = new SimpleDateFormat("yyyy");
        SimpleDateFormat monthDate = new SimpleDateFormat("MM");
        SimpleDateFormat dayDate = new SimpleDateFormat("dd");
        SimpleDateFormat hourDate = new SimpleDateFormat("HH");
        SimpleDateFormat minuteData = new SimpleDateFormat("mm");

        if (StringUtil.isEmpty(stringOrderEndMonth) || StringUtil.isEmpty(stringOrderEndDay)) {
            stringOrderEndMonth = monthDate.format(new Date());
            stringOrderEndDay = dayDate.format(new Date());
        }
        if (StringUtil.isEmpty(stringOrderEndHour) || StringUtil.isEmpty(stringOrderMin)) {
            stringOrderEndHour = hourDate.format(new Date());
            stringOrderMin = minuteData.format(new Date());
        }

        mTextViewEndTime.setText(stringOrderEndMonth + getString(R.string.common_month) + stringOrderEndDay + getString(R.string.common_day) + stringOrderEndHour + ":" + stringOrderMin);


        //默认设置不可点击
        mButtonConfirmOrder.setEnabled(false);
        mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mButtonConfirmOrder.setClickable(true);
                    mButtonConfirmOrder.setEnabled(true);
                } else {
                    mButtonConfirmOrder.setClickable(false);
                    mButtonConfirmOrder.setEnabled(false);
                }
            }
        });
        // 确认订单
        mButtonConfirmOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 确认订单，需要先调用接口，如果返回999则需要提示用户进行认证，否则进入订单支付界面
                confirmOrder();
            }
        });
        // 优惠券
        mSettingBarCoupons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 选择优惠券,默认不给，需要用户手动选择优惠券
                startActivity(CouponListActivity.class);
            }
        });
    }

    private void confirmOrder() {
        //获取订单确认页的信息
        String token = SPUtils.getInstance(OrderConfirmationActivity.this).getString("token");
        ConfirmOrderRequestBean confirmOrderRequestBean = new ConfirmOrderRequestBean();
        confirmOrderRequestBean.setBackPickUpId(1);
        confirmOrderRequestBean.setBeginDate("2020-12-01 09:00:00");
        confirmOrderRequestBean.setCarId(1);
        confirmOrderRequestBean.setCouponId(1);
        confirmOrderRequestBean.setEndDate("2020-12-02 09:00:00");
        confirmOrderRequestBean.setTakePickUpId(1);
        confirmOrderRequestBean.setVipService(1);
        RetrofitClient.getRetrofitService().getUserConfirmOrder(token, confirmOrderRequestBean).enqueue(new Callback<ConfirmOrderResponseBean>() {
            @Override
            public void onResponse(Call<ConfirmOrderResponseBean> call, Response<ConfirmOrderResponseBean> response) {
                ConfirmOrderResponseBean confirmOrderResponseBean = response.body();
                if (confirmOrderResponseBean.getCode().equals("200")) {
                    String orderId = confirmOrderResponseBean.getData().getId() + "";
                    Intent orderIntent = new Intent(OrderConfirmationActivity.this, OrderPayActivity.class);
                    orderIntent.putExtra(Constant.GET_ORDER_ID, orderId);
                    // 进入支付页面
                    startActivity(orderIntent);
                } else if (confirmOrderResponseBean.getCode().equals("999")) {
                    //进入认证界面
                    startActivity(IdentityAuthenticationActivity.class);
                } else {
                    toast("获取数据失败");
                }
            }

            @Override
            public void onFailure(Call<ConfirmOrderResponseBean> call, Throwable t) {
                toast("网络错误");
            }
        });

    }


    @Override
    protected void initData() {
        //获取订单确认页的信息
        String token = SPUtils.getInstance(OrderConfirmationActivity.this).getString("token");

        RetrofitClient.getRetrofitService().getUserOrderConfirmInfo(token, 1 + "", 1 + "").enqueue(new Callback<GetUserConfirmInfoResponseBean>() {
            @Override
            public void onResponse(Call<GetUserConfirmInfoResponseBean> call, Response<GetUserConfirmInfoResponseBean> response) {
                GetUserConfirmInfoResponseBean getUserConfirmInfoResponseBean = response.body();
                if ("200".equals(getUserConfirmInfoResponseBean.getCode())) {
                    GetUserConfirmInfoResponseBean.DataBean userConfirmInfoResponseBeanData = getUserConfirmInfoResponseBean.getData();
                    mSettingBarRentalFee.setRightText("￥" + userConfirmInfoResponseBeanData.getBasicCost().get(0).getCostMoney());
                    mSettingBarBaseFee.setRightText("￥" + userConfirmInfoResponseBeanData.getBasicCost().get(1).getCostMoney());
                    mSettingBarServiceFee.setRightText("￥" + userConfirmInfoResponseBeanData.getVipCost().get(0).getCostMoney());
                    mTextViewPayPrice.setText("金额￥" + userConfirmInfoResponseBeanData.getBasicCost().get(0).getCostMoney());
                } else {
                    toast("获取数据失败");
                }
            }

            @Override
            public void onFailure(Call<GetUserConfirmInfoResponseBean> call, Throwable t) {
                toast("网络错误");
            }

        });

    }
}
