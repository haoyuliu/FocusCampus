package com.sam.rentalcar.ui.activity;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sam.rentalcar.R;
import com.sam.rentalcar.adapter.ConstellationAdapter;
import com.sam.rentalcar.adapter.GetCarListAdapter;
import com.sam.rentalcar.adapter.ListDropDownAdapter;
import com.sam.rentalcar.common.MyActivity;
import com.sam.rentalcar.http.net.RetrofitClient;
import com.sam.rentalcar.http.response.GetCarBrandListResponseBean;
import com.sam.rentalcar.http.response.GetCarListResponseBean;
import com.sam.rentalcar.http.response.GetCarTypeListResponseBean;
import com.sam.rentalcar.ui.adapter.ChoiceCarAdapter;
import com.sam.rentalcar.widget.DropDownMenu.DropDownMenu;

import org.w3c.dom.NamedNodeMap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 选车页面
 */
public class ChoiceCarActivity extends MyActivity {

    @BindView(R.id.dropDownMenu)
    DropDownMenu mDropDownMenu;
    private String headers[] = {"车型", "品牌", "价格"};
    private List<View> popupViews = new ArrayList<>();

    private ListDropDownAdapter mCarModelAdapter;
    private ListDropDownAdapter mCarPriceAdapter;
    private ConstellationAdapter mBrandAdapter;

    private List<String> carModelsList = new ArrayList<String>();
    private List<String> carBrandsList = new ArrayList<String>();
    private String carPrices[] = {"默认", "价格升序", "价格降序"};

    private RecyclerView mRecyclerView;
    private GetCarListAdapter mGetCarListAdapter;
    private int constellationPosition = 0;

    private String brandIds;
    private int pickUpId = 1;
    private String startDate;
    private String endDate;
    private int carType = 1;
    private String brandId;
    private long carId = 1;
    private int order = 0;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_choice_car;
    }

    @Override
    protected void initView() {
        //init 车型 menu
        ListView modelView = new ListView(this);
        mCarModelAdapter = new ListDropDownAdapter(this, carModelsList);
        modelView.setDividerHeight(0);
        modelView.setAdapter(mCarModelAdapter);

        //init 品牌 menu
        LinearLayout constellationView = (LinearLayout) getLayoutInflater().inflate(R.layout.custom_layout, null);
        ListView constellation = constellationView.findViewById(R.id.constellation);
        mBrandAdapter = new ConstellationAdapter(this, carBrandsList);
        constellation.setAdapter(mBrandAdapter);

        //init 价格 menu
        ListView priceView = new ListView(this);
        priceView.setDividerHeight(0);
        mCarPriceAdapter = new ListDropDownAdapter(this, Arrays.asList(carPrices));
        priceView.setAdapter(mCarPriceAdapter);

        //init popupViews
        popupViews.add(modelView);
        popupViews.add(constellationView);
        popupViews.add(priceView);


        //init context view
        LinearLayout contentView = (LinearLayout) getLayoutInflater().inflate(R.layout.choice_car_main, null);
        // ListView contentListView = constellationView.findViewById(R.id.constellation);
        // mBrandAdapter = new ConstellationAdapter(this, Arrays.asList(carBrands));
        //  contentListView.setAdapter(mBrandAdapter);

        //init dropdownview
        mDropDownMenu.setDropDownMenu(Arrays.asList(headers), popupViews, contentView);

        mRecyclerView = contentView.findViewById(R.id.car_list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(ChoiceCarActivity.this));
    }

    @Override
    protected void initData() {
        // 获取车型
        getCarTypeList();
        // 获取品牌
        getCarBrandList();
        // 获取选车页面数据
        getChoiceCarData(brandIds, pickUpId, startDate, endDate, carType, brandId, carId, order);
    }

    private void getChoiceCarData(String brandIds, int pickUpId, String startDate, String endDate, int carType, String brandId, long carId, int order) {
        RetrofitClient.getRetrofitService().getCarList(brandIds, pickUpId, startDate, endDate, carType, brandId, carId, order).enqueue(new Callback<GetCarListResponseBean>() {
            @Override
            public void onResponse(Call<GetCarListResponseBean> call, Response<GetCarListResponseBean> response) {
                GetCarListResponseBean getCarListResponseBean = response.body();
                if (getCarListResponseBean.getCode().equals("200")) {
                    if (getCarListResponseBean.getData().size() == 0) {
                        toast("暂无数据");
                    }
                    mGetCarListAdapter = new GetCarListAdapter(ChoiceCarActivity.this, getCarListResponseBean.getData());
                    mRecyclerView.setAdapter(mGetCarListAdapter);
                }
            }

            @Override
            public void onFailure(Call<GetCarListResponseBean> call, Throwable t) {
                toast("获取数据失败");
            }
        });
    }

    private void getCarBrandList() {
        RetrofitClient.getRetrofitService().getCarBrandList().enqueue(new Callback<GetCarBrandListResponseBean>() {
            @Override
            public void onResponse(Call<GetCarBrandListResponseBean> call, Response<GetCarBrandListResponseBean> response) {
                GetCarBrandListResponseBean carTypeListResponseBean = response.body();
                if (carTypeListResponseBean.getCode().equals("200")) {
                    List<GetCarBrandListResponseBean.DataBean> data = carTypeListResponseBean.getData();
                    for (GetCarBrandListResponseBean.DataBean dataBean : data) {
                        carBrandsList.add(dataBean.getName());
                    }
                }
            }

            @Override
            public void onFailure(Call<GetCarBrandListResponseBean> call, Throwable t) {
                toast("获取数据失败");
            }
        });
    }

    private void getCarTypeList() {
        RetrofitClient.getRetrofitService().getCarTypeList().enqueue(new Callback<GetCarTypeListResponseBean>() {
            @Override
            public void onResponse(Call<GetCarTypeListResponseBean> call, Response<GetCarTypeListResponseBean> response) {
                GetCarTypeListResponseBean carTypeListResponseBean = response.body();
                if (carTypeListResponseBean.getCode().equals("200")) {
                    List<GetCarTypeListResponseBean.DataBean> data = carTypeListResponseBean.getData();
                    for (GetCarTypeListResponseBean.DataBean dataBean : data) {
                        carModelsList.add(dataBean.getTypeName());
                    }
                }
            }

            @Override
            public void onFailure(Call<GetCarTypeListResponseBean> call, Throwable t) {
                toast("获取数据失败");
            }
        });
    }

    @Override
    public void onBackPressed() {
        //退出activity前关闭菜单
        if (mDropDownMenu.isShowing()) {
            mDropDownMenu.closeMenu();
        } else {
            super.onBackPressed();
        }
    }
}
