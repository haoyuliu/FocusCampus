package com.sam.rentalcar.ui.fragment;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sam.base.BaseDialog;
import com.sam.rentalcar.R;
import com.sam.rentalcar.adapter.HomeRentalCarLowPriceAdapter;
import com.sam.rentalcar.aop.SingleClick;
import com.sam.rentalcar.common.MyFragment;
import com.sam.rentalcar.constant.Constant;
import com.sam.rentalcar.http.glide.GlideApp;
import com.sam.rentalcar.http.net.RetrofitClient;
import com.sam.rentalcar.http.response.GetRentalCarHomeMessageResponseBean;
import com.sam.rentalcar.ui.activity.ChoiceCarActivity;
import com.sam.rentalcar.ui.activity.HomeActivity;
import com.sam.rentalcar.ui.activity.RentalCarActivity;
import com.sam.rentalcar.ui.activity.RentalCarOrderActivity;
import com.sam.rentalcar.ui.dialog.DateDialog;
import com.sam.rentalcar.ui.dialog.TimeDialog;
import com.sam.widget.view.CountdownView;
import com.sam.widget.view.SwitchButton;

import java.util.Calendar;
import java.util.List;

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
                Uri uri = Uri.parse(Constant.QUERY_REGULATIONS_URL);
                Intent intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                intent.setData(uri);
                startActivity(intent);
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