package com.sam.rentalcar.videoplayer;

import android.os.Bundle;
import android.widget.Button;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sam.rentalcar.R;
import com.sam.rentalcar.adapter.GridVideoAdapter;
import com.sam.rentalcar.bean.UserProductionOrLoveBean;
import com.sam.rentalcar.common.MyFragment;
import com.sam.rentalcar.http.net.RetrofitClient;
import com.sam.rentalcar.ui.activity.HomeActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 个人作品的Fragment,如果当前用户是个人，需要提供删除视频的功能。
 */
public class PersonalProductionFragment extends MyFragment<HomeActivity> {
    private static final String TAG = "PersonalProductionFragment";

    @BindView(R.id.recycle_personal_production)
    RecyclerView mPersonalProoductionRecyclerView;

    private PersonLoveGridVideoAdapter fansAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_personal_production;
    }

    @Override
    protected void initView() {
        mPersonalProoductionRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        mPersonalProoductionRecyclerView.addItemDecoration(new MyDividerItemDecoration(getContext(), MyDividerItemDecoration.HORIZONTAL, R.drawable.border_shape_vidio));
    }

    @Override
    protected void initData() {
        Bundle arguments = this.getArguments();
        String userId;
        if (arguments != null) {
            userId = arguments.getString("userId");
            String page = "1";
            getData(userId, page);
        }
    }

    private void getData(String userId, String page) {
        RetrofitClient.getRetrofitService().getPersonalProduction(userId, page)
                .enqueue(new Callback<UserProductionOrLoveBean>() {
                    @Override
                    public void onResponse(Call<UserProductionOrLoveBean> call, Response<UserProductionOrLoveBean> response) {
                        UserProductionOrLoveBean userProductionOrLoveBean = response.body();
                        if (userProductionOrLoveBean.getCode().equals("200")) {
                            fansAdapter = new PersonLoveGridVideoAdapter(userProductionOrLoveBean.getData());
                            mPersonalProoductionRecyclerView.setAdapter(fansAdapter);
                        } else {
                            toast("获取数据失败");
                        }

                    }

                    @Override
                    public void onFailure(Call<UserProductionOrLoveBean> call, Throwable t) {
                        toast("获取数据失败");
                    }

                });

    }
}
