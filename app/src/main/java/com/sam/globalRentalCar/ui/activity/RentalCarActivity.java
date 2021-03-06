package com.sam.globalRentalCar.ui.activity;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.google.android.exoplayer2.source.MediaSourceEventListener;
import com.hyphenate.easeui.ui.EaseBaiduMapActivity;
import com.sam.base.BaseDialog;
import com.sam.globalRentalCar.R;
import com.sam.globalRentalCar.adapter.HomeRentalCarLowPriceAdapter;
import com.sam.globalRentalCar.aop.SingleClick;
import com.sam.globalRentalCar.common.MyActivity;
import com.sam.globalRentalCar.constant.Constant;
import com.sam.globalRentalCar.http.net.RetrofitClient;
import com.sam.globalRentalCar.http.response.GetRentalCarHomeMessageResponseBean;
import com.sam.globalRentalCar.http.response.GetUserCouponListResponseBean;
import com.sam.globalRentalCar.ui.dialog.DateDialog;
import com.sam.globalRentalCar.ui.dialog.TimeDialog;
import com.sam.widget.layout.SettingBar;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 租车页面
 */
public class RentalCarActivity extends MyActivity {

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

    @BindView(R.id.tv_days)
    TextView mTextViewDays;

    @BindView(R.id.rental_car_order)
    SettingBar orderSettingBar;

    private long startTime;
    private long endTime;
    private int gapCount;
    // 开始时间
    private String defaultStartYears;
    private String defaultMonth;
    private String defaultDay;
    private String defaultHour;
    private String defaultMinute;
    // 结束时间
    private String defaultEndYears;
    private String endMonth;
    private String endDay;
    private String endHour;
    private String endMinute;

    // 定位
    public LocationClient mLocationClient;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_rental_car;
    }

    @Override
    protected void initView() {
        getCityData();
        // 获取默认的年月日天
        SimpleDateFormat alldate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat yearDate = new SimpleDateFormat("yyyy");
        SimpleDateFormat monthDate = new SimpleDateFormat("MM");
        SimpleDateFormat dayDate = new SimpleDateFormat("dd");
        SimpleDateFormat hourDate = new SimpleDateFormat("HH");
        SimpleDateFormat minuteData = new SimpleDateFormat("mm");
        defaultStartYears = yearDate.format(new Date());
        defaultMonth = monthDate.format(new Date());
        defaultDay = dayDate.format(new Date());
        defaultHour = hourDate.format(new Date());
        defaultMinute = minuteData.format(new Date());

        //设置默认的时间
        mStartDateTextView.setText(defaultMonth + getString(R.string.common_month) + defaultDay + getString(R.string.common_day));
        mEndDateTextView.setText(defaultMonth + getString(R.string.common_month) + defaultDay + getString(R.string.common_day));
        mStartTimeTextView.setText(defaultHour + getString(R.string.common_hour) + defaultMinute + getString(R.string.common_minute));
        mEndTimeTextView.setText(defaultHour + getString(R.string.common_hour) + defaultMinute + getString(R.string.common_minute));
        // 设置低价的布局管理器
        mLowPriceRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        setOnClickListener(R.id.but_choice_car, R.id.rental_car_order, R.id.tv_start_date, R.id.tv_start_time, R.id.tv_end_date, R.id.tv_end_time);
    }

    private void getCityData() {
        // 根据经纬度获取当前城市信息
        mLocationClient = new LocationClient(getApplicationContext());
        mLocationClient.registerLocationListener(new MyLocationListener());
        LocationClientOption locationClientOption = new LocationClientOption();
        locationClientOption.setScanSpan(5000);
        mLocationClient.setLocOption(locationClientOption);
        mLocationClient.start();
    }

    @Override
    protected void initData() {
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
                                HomeRentalCarLowPriceAdapter homeRentalCarLowPriceAdapter = new HomeRentalCarLowPriceAdapter(RentalCarActivity.this, lowPriceCar);
                                mLowPriceRecyclerView.setAdapter(homeRentalCarLowPriceAdapter);
                                orderSettingBar.setLeftText("全部订单:  " + rentalCarHomeMessageResponseBean.getData().getMyOrderCount());
                            } else {
                                toast("暂无数据");
                            }
                        } else {
                            toast("获取数据失败");
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
                // 先判断时间是否合理
                // 将用户选择的时间，取车城市，取车地点携带到下一个页面
                Intent intent = new Intent(RentalCarActivity.this, ChoiceCarActivity.class);
                intent.putExtra(Constant.GET_CAR_DEFAULT_MONTH, defaultMonth);
                intent.putExtra(Constant.GET_CAR_DEFAULT_DAY, defaultDay);
                intent.putExtra(Constant.GET_CAR_DEFAULT_HOUR, defaultHour);
                intent.putExtra(Constant.GET_CAR_DEFAULT_MIN, defaultMinute);

                intent.putExtra(Constant.GET_CAR_END_MONTH, endMonth);
                intent.putExtra(Constant.GET_CAR_END_DAY, endDay);
                intent.putExtra(Constant.GET_CAR_END_HOUR, endHour);
                intent.putExtra(Constant.GET_CAR_END_MIN, endMinute);
                intent.putExtra(Constant.GET_CAR_DAYS, gapCount);
                startActivity(intent);
                break;
            case R.id.rental_car_order:
                //订单
                startActivity(RentalCarOrderActivity.class);
                break;
            case R.id.tv_start_date:
                // 开始日期选择
                choiceStartDate();
                break;
            case R.id.tv_start_time:
                // 开始时间选择
                choiceStartTime();
                break;
            case R.id.tv_end_date:
                // 结束日期选择
                choiceEndDate();
                break;
            case R.id.tv_end_time:
                // 结束时间选择
                choiceEndTime();
                break;
            default:
                break;

        }
    }

    private void choiceStartDate() {
        // 日期选择对话框
        new DateDialog.Builder(RentalCarActivity.this)
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
                        //toast(year + getString(R.string.common_year) + month + getString(R.string.common_month) + day + getString(R.string.common_day));
                        defaultStartYears = year + "";
                        defaultMonth = month + "";
                        defaultDay = day + "";
                        mStartDateTextView.setText(defaultMonth + getString(R.string.common_month) + defaultDay + getString(R.string.common_day));

                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        Date date_start = null;
                        Date date_end = null;
                        try {
                            date_start = sdf.parse(defaultStartYears + "-" + defaultMonth + "-" + defaultDay);
                            date_end = sdf.parse(defaultStartYears + "-" + endMonth + "-" + endDay);
                        } catch (java.text.ParseException e) {
                            e.printStackTrace();
                        }
                        gapCount = getGapCount(date_start, date_end);
                        mTextViewDays.setText(gapCount + "天");
                        // 如果不指定时分秒则默认为现在的时间
                        Calendar calendar = Calendar.getInstance();
                        calendar.set(Calendar.YEAR, year);
                        // 月份从零开始，所以需要减 1
                        calendar.set(Calendar.MONTH, month - 1);
                        calendar.set(Calendar.DAY_OF_MONTH, day);
                        //toast("时间戳：" + calendar.getTimeInMillis());
                        //toast(new SimpleDateFormat("yyyy年MM月dd日 kk:mm:ss").format(calendar.getTime()));
                    }

                    @Override
                    public void onCancel(BaseDialog dialog) {
                        toast("取消了");
                    }
                })
                .show();
    }

    private void choiceEndDate() {
        // 日期选择对话框
        new DateDialog.Builder(RentalCarActivity.this)
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
                        endMonth = month + "";
                        endDay = day + "";
                        //toast(year + getString(R.string.common_year) + month + getString(R.string.common_month) + day + getString(R.string.common_day));
                        mEndDateTextView.setText(endMonth + getString(R.string.common_month) + endDay + getString(R.string.common_day));
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        Date date_start = null;
                        Date date_end = null;
                        try {
                            date_start = sdf.parse(defaultStartYears + "-" + defaultMonth + "-" + defaultDay);
                            date_end = sdf.parse(defaultStartYears + "-" + endMonth + "-" + endDay);
                        } catch (java.text.ParseException e) {
                            e.printStackTrace();
                        }
                        int gapCount = getGapCount(date_start, date_end);
                        mTextViewDays.setText(gapCount + "天");
                        // 如果不指定时分秒则默认为现在的时间
                        Calendar calendar = Calendar.getInstance();
                        calendar.set(Calendar.YEAR, year);
                        // 月份从零开始，所以需要减 1
                        calendar.set(Calendar.MONTH, month - 1);
                        calendar.set(Calendar.DAY_OF_MONTH, day);
                        //toast("时间戳：" + calendar.getTimeInMillis());
                        //toast(new SimpleDateFormat("yyyy年MM月dd日 kk:mm:ss").format(calendar.getTime()));
                    }

                    @Override
                    public void onCancel(BaseDialog dialog) {
                        toast("取消了");
                    }
                })
                .show();
    }

    private void choiceStartTime() {
        // 时间选择对话框
        new TimeDialog.Builder(RentalCarActivity.this)
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
                        defaultHour = hour + "";
                        defaultMinute = minute + "";
                        // toast(hour + getString(R.string.common_hour) + minute + getString(R.string.common_minute) + second + getString(R.string.common_second));
                        mStartTimeTextView.setText(defaultHour + getString(R.string.common_hour) + defaultMinute + getString(R.string.common_minute));
                        // 如果不指定年月日则默认为今天的日期
                        Calendar calendar = Calendar.getInstance();
                        calendar.set(Calendar.HOUR_OF_DAY, hour);
                        calendar.set(Calendar.MINUTE, minute);
                        calendar.set(Calendar.SECOND, second);
                        //toast("时间戳：" + calendar.getTimeInMillis());
                        //toast(new SimpleDateFormat("yyyy年MM月dd日 kk:mm:ss").format(calendar.getTime()));
                    }

                    @Override
                    public void onCancel(BaseDialog dialog) {
                        toast("取消了");
                    }
                })
                .show();

    }

    private void choiceEndTime() {
        // 时间选择对话框
        new TimeDialog.Builder(RentalCarActivity.this)
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
                        // toast(hour + getString(R.string.common_hour) + minute + getString(R.string.common_minute) + second + getString(R.string.common_second));
                        endHour = hour + "";
                        endMinute = minute + "";
                        mEndTimeTextView.setText(endHour + getString(R.string.common_hour) + endMinute + getString(R.string.common_minute));
                        // 如果不指定年月日则默认为今天的日期
                        Calendar calendar = Calendar.getInstance();
                        calendar.set(Calendar.HOUR_OF_DAY, hour);
                        calendar.set(Calendar.MINUTE, minute);
                        calendar.set(Calendar.SECOND, second);
                        //toast("时间戳：" + calendar.getTimeInMillis());
                        //toast(new SimpleDateFormat("yyyy年MM月dd日 kk:mm:ss").format(calendar.getTime()));
                    }

                    @Override
                    public void onCancel(BaseDialog dialog) {
                        toast("取消了");
                    }
                })
                .show();

    }

    public class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation bdLocation) {
            String altitude = bdLocation.getAltitude() + "";
            String longitude = bdLocation.getLongitude() + "";
            Log.d("RentalCarActivity", "altitude---->" + altitude);
            Log.d("RentalCarActivity", "longitude---->" + longitude);
            //根据金纬度获取城市id
            RetrofitClient.getRetrofitService().getCityIdByLl(longitude, altitude).enqueue(new Callback<GetUserCouponListResponseBean>() {
                @Override
                public void onResponse(Call<GetUserCouponListResponseBean> call, Response<GetUserCouponListResponseBean> response) {

                }

                @Override
                public void onFailure(Call<GetUserCouponListResponseBean> call, Throwable t) {

                }
            });

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLocationClient.stop();
    }

    /**
     * 获取两个日期之间的间隔天数
     *
     * @return
     */
    public static int getGapCount(Date startDate, Date endDate) {
        Calendar fromCalendar = Calendar.getInstance();
        fromCalendar.setTime(startDate);
        fromCalendar.set(Calendar.HOUR_OF_DAY, 0);
        fromCalendar.set(Calendar.MINUTE, 0);
        fromCalendar.set(Calendar.SECOND, 0);
        fromCalendar.set(Calendar.MILLISECOND, 0);

        Calendar toCalendar = Calendar.getInstance();
        toCalendar.setTime(endDate);
        toCalendar.set(Calendar.HOUR_OF_DAY, 0);
        toCalendar.set(Calendar.MINUTE, 0);
        toCalendar.set(Calendar.SECOND, 0);
        toCalendar.set(Calendar.MILLISECOND, 0);

        return (int) ((toCalendar.getTime().getTime() - fromCalendar.getTime().getTime()) / (1000 * 60 * 60 * 24));
    }
}