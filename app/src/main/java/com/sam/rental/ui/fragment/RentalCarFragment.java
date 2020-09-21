package com.sam.rental.ui.fragment;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sam.base.BaseDialog;
import com.sam.rental.R;
import com.sam.rental.adapter.HomeRentalCarLowPriceAdapter;
import com.sam.rental.aop.SingleClick;
import com.sam.rental.common.MyFragment;
import com.sam.rental.http.glide.GlideApp;
import com.sam.rental.http.net.RetrofitClient;
import com.sam.rental.http.response.GetRentalCarHomeMessageResponseBean;
import com.sam.rental.ui.activity.ChoiceCarActivity;
import com.sam.rental.ui.activity.HomeActivity;
import com.sam.rental.ui.activity.RentalCarOrderActivity;
import com.sam.rental.ui.dialog.DateDialog;
import com.sam.rental.ui.dialog.TimeDialog;
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
    @BindView(R.id.home_rental_low_price_car)
    RecyclerView mLowPriceRecyclerView;

    @BindView(R.id.tv_start_date)
    TextView mStartDateTextView;

    @BindView(R.id.tv_start_time)
    TextView mStartTimeTextView;

    @BindView(R.id.tv_end_date)
    TextView mEndDateTextView;

    @BindView(R.id.tv_end_time)
    TextView mEndTimeTextView;

    public static RentalCarFragment newInstance() {
        return new RentalCarFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_rental_car;
    }

    @Override
    protected void initView() {
        mLowPriceRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        setOnClickListener(R.id.but_choice_car, R.id.rental_car_order, R.id.tv_start_date, R.id.tv_start_time, R.id.tv_end_date, R.id.tv_end_time);
    }

    @Override
    protected void initData() {
       /* GlideApp.with(this)
                .load(R.drawable.bg_launcher)
                .circleCrop()
                .into(mCircleView);*/
        //获取低价车型数据
        getRentalCarHomeData();
    }

    private void getRentalCarHomeData() {
        RetrofitClient.getRetrofitService().getRentalCarHomeMessage("101010")
                .enqueue(new Callback<GetRentalCarHomeMessageResponseBean>() {
                    @Override
                    public void onResponse(Call<GetRentalCarHomeMessageResponseBean> call, Response<GetRentalCarHomeMessageResponseBean> response) {
                        GetRentalCarHomeMessageResponseBean rentalCarHomeMessageResponseBean = response.body();
                        if (rentalCarHomeMessageResponseBean.getCode().equals("200")) {
                            List<GetRentalCarHomeMessageResponseBean.DataBean.LowPriceCarBean> lowPriceCar = rentalCarHomeMessageResponseBean.getData().getLowPriceCar();
                            if (lowPriceCar.size() > 0) {
                                HomeRentalCarLowPriceAdapter homeRentalCarLowPriceAdapter = new HomeRentalCarLowPriceAdapter(getContext(), lowPriceCar);
                                mLowPriceRecyclerView.setAdapter(homeRentalCarLowPriceAdapter);
                            }
                            toast("暂无数据");
                        }

                    }

                    @Override
                    public void onFailure(Call<GetRentalCarHomeMessageResponseBean> call, Throwable t) {
                        toast("获取数据失败");
                    }
                });

    }

    @SingleClick
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.but_choice_car:
                startActivity(ChoiceCarActivity.class);
                break;
            case R.id.rental_car_order:
                //订单
                startActivity(RentalCarOrderActivity.class);
                break;
            case R.id.tv_start_date:
                // 开始日期选择
                choiceDate();
                break;
            case R.id.tv_start_time:
                // 开始时间选择
                choiceTime();
                break;
            case R.id.tv_end_date:
                // 结束日期选择
                choiceDate();
                break;
            case R.id.tv_end_time:
                // 结束时间选择
                choiceTime();
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

    private void choiceDate() {
        // 日期选择对话框
        new DateDialog.Builder(getContext())
                .setTitle(getString(R.string.date_title))
                // 确定按钮文本
                .setConfirm(getString(R.string.common_confirm))
                // 设置 null 表示不显示取消按钮
                .setCancel(getString(R.string.common_cancel))
                // 设置日期
                //.setDate("2018-12-31")
                //.setDate("20181231")
                //.setDate(1546263036137)
                // 设置年份
                //.setYear(2018)
                // 设置月份
                //.setMonth(2)
                // 设置天数
                //.setDay(20)
                // 不选择天数
                //.setIgnoreDay()
                .setListener(new DateDialog.OnListener() {
                    @Override
                    public void onSelected(BaseDialog dialog, int year, int month, int day) {
                        toast(year + getString(R.string.common_year) + month + getString(R.string.common_month) + day + getString(R.string.common_day));

                        // 如果不指定时分秒则默认为现在的时间
                        Calendar calendar = Calendar.getInstance();
                        calendar.set(Calendar.YEAR, year);
                        // 月份从零开始，所以需要减 1
                        calendar.set(Calendar.MONTH, month - 1);
                        calendar.set(Calendar.DAY_OF_MONTH, day);
                        toast("时间戳：" + calendar.getTimeInMillis());
                        //toast(new SimpleDateFormat("yyyy年MM月dd日 kk:mm:ss").format(calendar.getTime()));
                    }

                    @Override
                    public void onCancel(BaseDialog dialog) {
                        toast("取消了");
                    }
                })
                .show();
    }

    private void choiceTime() {
        // 时间选择对话框
        new TimeDialog.Builder(getContext())
                .setTitle(getString(R.string.time_title))
                // 确定按钮文本
                .setConfirm(getString(R.string.common_confirm))
                // 设置 null 表示不显示取消按钮
                .setCancel(getString(R.string.common_cancel))
                // 设置时间
                //.setTime("23:59:59")
                //.setTime("235959")
                // 设置小时
                //.setHour(23)
                // 设置分钟
                //.setMinute(59)
                // 设置秒数
                //.setSecond(59)
                // 不选择秒数
                //.setIgnoreSecond()
                .setListener(new TimeDialog.OnListener() {

                    @Override
                    public void onSelected(BaseDialog dialog, int hour, int minute, int second) {
                        toast(hour + getString(R.string.common_hour) + minute + getString(R.string.common_minute) + second + getString(R.string.common_second));

                        // 如果不指定年月日则默认为今天的日期
                        Calendar calendar = Calendar.getInstance();
                        calendar.set(Calendar.HOUR_OF_DAY, hour);
                        calendar.set(Calendar.MINUTE, minute);
                        calendar.set(Calendar.SECOND, second);
                        toast("时间戳：" + calendar.getTimeInMillis());
                        //toast(new SimpleDateFormat("yyyy年MM月dd日 kk:mm:ss").format(calendar.getTime()));
                    }

                    @Override
                    public void onCancel(BaseDialog dialog) {
                        toast("取消了");
                    }
                })
                .show();

    }
}