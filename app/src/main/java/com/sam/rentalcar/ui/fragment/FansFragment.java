package com.sam.rentalcar.ui.fragment;

import android.app.Activity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hjq.http.EasyHttp;
import com.hjq.http.listener.HttpCallback;
import com.sam.base.BaseFragment;
import com.sam.rentalcar.R;
import com.sam.rentalcar.adapter.FansAdapter;
import com.sam.rentalcar.bean.DataCreate;
import com.sam.rentalcar.bean.FansBean;
import com.sam.rentalcar.common.MyFragment;
import com.sam.rentalcar.http.model.HttpData;
import com.sam.rentalcar.http.net.RetrofitClient;
import com.sam.rentalcar.http.request.GetFansApi;
import com.sam.rentalcar.http.response.LoginBean;
import com.sam.rentalcar.ui.activity.HomeActivity;
import com.sam.rentalcar.ui.adapter.CommPagerAdapter;

import java.net.HttpURLConnection;
import java.util.ArrayList;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FansFragment extends MyFragment<HomeActivity> {


    @BindView(R.id.recycle_fans)
    RecyclerView mRecyclerView;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_fans;
    }

    @Override
    protected void initView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    protected void initData() {
        String userId = "368499958493609284";
        String page = "1";
        getData(userId,page);

    }

    private void getData(String userId, String page) {
        RetrofitClient.getRetrofitService().getFans(userId,page)
                .enqueue(new Callback<FansBean>() {
                    @Override
                    public void onResponse(Call<FansBean> call, Response<FansBean> response) {
                        if (response.code()== HttpURLConnection.HTTP_OK) {
                            FansAdapter fansAdapter = new FansAdapter(getContext(),response.body().getData());
                            mRecyclerView.setAdapter(fansAdapter);
                        }

                    }

                    @Override
                    public void onFailure(Call<FansBean> call, Throwable t) {

                    }
                });

    }
}
