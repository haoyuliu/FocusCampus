package com.sam.rental.ui.fragment;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sam.base.BaseFragment;
import com.sam.rental.R;
import com.sam.rental.adapter.FansAdapter;
import com.sam.rental.bean.DataCreate;
import com.sam.rental.common.MyFragment;
import com.sam.rental.ui.activity.HomeActivity;

import butterknife.BindView;

public class FansFragment extends MyFragment<HomeActivity> {
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;
    private FansAdapter fansAdapter;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_fans;
    }

    @Override
    protected void initView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        fansAdapter = new FansAdapter(getContext(), DataCreate.userList);
        recyclerView.setAdapter(fansAdapter);
    }

    @Override
    protected void initData() {

    }
}
