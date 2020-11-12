package com.sam.rentalcar.ui.activity;

import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatCheckBox;

import com.sam.rentalcar.R;
import com.sam.rentalcar.common.MyActivity;
import com.sam.rentalcar.http.net.RetrofitClient;
import com.sam.rentalcar.http.response.GetUserConfirmInfoResponseBean;
import com.sam.rentalcar.http.response.GetUserCouponListResponseBean;
import com.sam.rentalcar.utils.SPUtils;
import com.sam.widget.layout.SettingBar;

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

    @BindView(R.id.car_rental_fee)
    SettingBar mSettingBarRentalFee;

    @BindView(R.id.car_base_fee)
    SettingBar mSettingBarBaseFee;

    @BindView(R.id.setting_bar_coupons)
    SettingBar mSettingBarCoupons;


    @BindView(R.id.confirm_order)
    Button mButtonConfirmOrder;

    @BindView(R.id.order_checkbox)
    AppCompatCheckBox mCheckBox;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_order_confirmation;
    }

    @Override
    protected void initView() {
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
        mButtonConfirmOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(IdentityAuthenticationActivity.class);
            }
        });
        // 优惠券
        mSettingBarCoupons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 选择优惠券
                startActivity(CouponListActivity.class);
            }
        });
    }

    @Override
    protected void initData() {
        String token = SPUtils.getInstance(OrderConfirmationActivity.this).getString("token");

        RetrofitClient.getRetrofitService().getUserOrderConfirmInfo(token, 1, 2).enqueue(new Callback<GetUserConfirmInfoResponseBean>() {
            @Override
            public void onResponse(Call<GetUserConfirmInfoResponseBean> call, Response<GetUserConfirmInfoResponseBean> response) {
                GetUserConfirmInfoResponseBean getUserConfirmInfoResponseBean = response.body();
                if (getUserConfirmInfoResponseBean.getCode().equals("200")) {
                    GetUserConfirmInfoResponseBean.DataBean userConfirmInfoResponseBeanData = getUserConfirmInfoResponseBean.getData();
                    mSettingBarRentalFee.setRightText("￥" + userConfirmInfoResponseBeanData.getBasicCost().get(0).getCostMoney());
                    mSettingBarBaseFee.setRightText("￥" + userConfirmInfoResponseBeanData.getBasicCost().get(1).getCostMoney());
                } else {
                    toast("获取数据失败");
                }
            }

            @Override
            public void onFailure(Call<GetUserConfirmInfoResponseBean> call, Throwable t) {

            }
        });

    }
}
