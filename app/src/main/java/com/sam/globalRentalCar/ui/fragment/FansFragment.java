package com.sam.globalRentalCar.ui.fragment;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sam.globalRentalCar.R;
import com.sam.globalRentalCar.adapter.FansAdapter;
import com.sam.globalRentalCar.bean.FansBean;
import com.sam.globalRentalCar.common.MyFragment;
import com.sam.globalRentalCar.http.net.RetrofitClient;
import com.sam.globalRentalCar.ui.activity.HomeActivity;
import com.sam.globalRentalCar.utils.SPUtils;

import java.net.HttpURLConnection;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 粉丝列表的Fragment
 */
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
        String userId = SPUtils.getInstance(getContext()).getString("UserId");
        String page = "1";
        getData(userId, page);
    }

    private void getData(String userId, String page) {
        RetrofitClient.getRetrofitService().getFans(userId, page)
                .enqueue(new Callback<FansBean>() {
                    @Override
                    public void onResponse(Call<FansBean> call, Response<FansBean> response) {
                        if (response.code() == HttpURLConnection.HTTP_OK) {
                            FansAdapter fansAdapter = new FansAdapter(getContext(), response.body().getData());
                            mRecyclerView.setAdapter(fansAdapter);
                        }

                    }

                    @Override
                    public void onFailure(Call<FansBean> call, Throwable t) {

                    }
                });

    }
}
