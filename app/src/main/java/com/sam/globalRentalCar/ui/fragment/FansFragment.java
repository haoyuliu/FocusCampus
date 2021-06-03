package com.sam.globalRentalCar.ui.fragment;

import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sam.globalRentalCar.R;
import com.sam.globalRentalCar.action.StatusAction;
import com.sam.globalRentalCar.adapter.FansAdapter;
import com.sam.globalRentalCar.bean.FansBean;
import com.sam.globalRentalCar.common.MyFragment;
import com.sam.globalRentalCar.http.net.RetrofitClient;
import com.sam.globalRentalCar.ui.activity.HomeActivity;
import com.sam.globalRentalCar.utils.SPUtils;
import com.sam.globalRentalCar.widget.HintLayout;

import java.net.HttpURLConnection;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 粉丝列表的Fragment
 */
public class FansFragment extends MyFragment<HomeActivity> implements StatusAction {
    @BindView(R.id.recycle_fans)
    RecyclerView mRecyclerView;

    @BindView(R.id.hl_status_hint)
    HintLayout mHintLayout;

    private String userId;

    private String page = "1";

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
        userId = SPUtils.getInstance(getContext()).getString("UserId");
        getData(userId, page);
    }

    private void getData(String userId, String page) {
        showLoading();
        RetrofitClient.getRetrofitService().getFans(userId, page)
                .enqueue(new Callback<FansBean>() {
                    @Override
                    public void onResponse(Call<FansBean> call, Response<FansBean> response) {
                        if (response.code() == HttpURLConnection.HTTP_OK) {
                            if (response.body().getData().size() == 0) {
                                showEmpty();
                            } else {
                                showComplete();
                                FansAdapter fansAdapter = new FansAdapter(getContext(), response.body().getData());
                                mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                                mRecyclerView.setAdapter(fansAdapter);
                            }
                        } else {
                            showError(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    getData(userId, page);
                                }
                            });
                        }

                    }

                    @Override
                    public void onFailure(Call<FansBean> call, Throwable t) {
                        showError(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                getData(userId, page);
                            }
                        });
                    }
                });

    }

    @Override
    public HintLayout getHintLayout() {
        return mHintLayout;
    }
}
