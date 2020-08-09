package com.sam.rental.ui.fragment;

import android.app.Activity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hjq.http.EasyHttp;
import com.hjq.http.listener.HttpCallback;
import com.sam.base.BaseFragment;
import com.sam.rental.R;
import com.sam.rental.adapter.FansAdapter;
import com.sam.rental.bean.DataCreate;
import com.sam.rental.bean.FansBean;
import com.sam.rental.common.MyFragment;
import com.sam.rental.http.model.HttpData;
import com.sam.rental.http.net.RetrofitClient;
import com.sam.rental.http.request.GetFansApi;
import com.sam.rental.http.response.LoginBean;
import com.sam.rental.ui.activity.HomeActivity;
import com.sam.rental.ui.adapter.CommPagerAdapter;

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
