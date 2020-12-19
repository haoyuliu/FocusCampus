package com.sam.globalRentalCar.ui.fragment;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sam.globalRentalCar.R;
import com.sam.globalRentalCar.adapter.WorkAdapter;
import com.sam.globalRentalCar.bean.DataCreate;
import com.sam.globalRentalCar.common.MyFragment;
import com.sam.globalRentalCar.ui.activity.HomeActivity;

import butterknife.BindView;

/**
 * description 个人作品fragment
 */
public class WorkFragment extends MyFragment<HomeActivity> {
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;
    private WorkAdapter workAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_work;
    }

    @Override
    protected void initView() {
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        workAdapter = new WorkAdapter(getActivity(), DataCreate.datas);
        recyclerView.setAdapter(workAdapter);
    }

    @Override
    protected void initData() {

    }
}
