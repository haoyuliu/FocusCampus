package com.sam.rental.ui.fragment;

import android.os.Bundle;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sam.rental.R;
import com.sam.rental.adapter.GridVideoAdapter;
import com.sam.rental.bean.UserProductionOrLoveBean;
import com.sam.rental.common.MyFragment;
import com.sam.rental.http.net.RetrofitClient;
import com.sam.rental.ui.activity.HomeActivity;

import java.net.HttpURLConnection;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 个人作品的Fragment
 */
public class PersonalProductionFragment extends MyFragment<HomeActivity> {
    @BindView(R.id.recycle_personal_production)
    RecyclerView mPersonalProoductionRecyclerView;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_personal_production;
    }

    @Override
    protected void initView() {
        mPersonalProoductionRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
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
                        if (response.code() == HttpURLConnection.HTTP_OK) {
                            GridVideoAdapter fansAdapter = new GridVideoAdapter(getContext(), response.body().getData());
                            mPersonalProoductionRecyclerView.setAdapter(fansAdapter);
                        }

                    }

                    @Override
                    public void onFailure(Call<UserProductionOrLoveBean> call, Throwable t) {

                    }

                });

    }
}
