package com.sam.rental.ui.activity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sam.rental.R;
import com.sam.rental.common.MyActivity;
import com.sam.rental.ui.adapter.ChoiceCarAdapter;

import org.angmarch.views.NiceSpinner;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;

/**
 * 选车页面
 */
public class ChoiceCarActivity extends MyActivity {

    @BindView(R.id.spinner_car_model)
    NiceSpinner mNiceSpinnerCarModel;
    @BindView(R.id.spinner_car_brand)
    NiceSpinner mNiceSpinnerCarBrand;
    @BindView(R.id.spinner_car_price)
    NiceSpinner mNiceSpinnerCarPrice;

    @BindView(R.id.rv_choice_car)
    RecyclerView mRecyclerViewChoiceCar;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_choice_car;
    }

    @Override
    protected void initView() {
        List<String> dataModel = new LinkedList<>(Arrays.asList("车型", "轿车", "SUV", "商务车", "跑车"));
        mNiceSpinnerCarModel.attachDataSource(dataModel);
        List<String> dateBrand = new LinkedList<>(Arrays.asList("品牌", "大众", "奔驰", "宝马", "法拉利"));
        mNiceSpinnerCarBrand.attachDataSource(dateBrand);
        List<String> dataPrice = new LinkedList<>(Arrays.asList("价格", "2", "2", "3", "4"));
        mNiceSpinnerCarPrice.attachDataSource(dataPrice);
    }

    @Override
    protected void initData() {
        ChoiceCarAdapter choiceCarAdapter = new ChoiceCarAdapter();
        mRecyclerViewChoiceCar.setAdapter(choiceCarAdapter);
        mRecyclerViewChoiceCar.setLayoutManager(new LinearLayoutManager(ChoiceCarActivity.this));
    }
}
