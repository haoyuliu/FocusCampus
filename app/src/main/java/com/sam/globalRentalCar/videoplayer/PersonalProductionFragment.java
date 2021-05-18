package com.sam.globalRentalCar.videoplayer;

import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sam.globalRentalCar.R;
import com.sam.globalRentalCar.action.StatusAction;
import com.sam.globalRentalCar.bean.UserProductionOrLoveBean;
import com.sam.globalRentalCar.common.MyFragment;
import com.sam.globalRentalCar.http.net.RetrofitClient;
import com.sam.globalRentalCar.ui.activity.HomeActivity;
import com.sam.globalRentalCar.widget.HintLayout;

import java.util.List;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 个人作品的Fragment,如果当前用户是个人，需要提供删除视频的功能。
 */
public class PersonalProductionFragment extends MyFragment<HomeActivity> implements StatusAction {
    private static final String TAG = "PersonalProductionFragment";

    @BindView(R.id.recycle_personal_production)
    RecyclerView mPersonalProoductionRecyclerView;

    @BindView(R.id.hl_status_hint)
    HintLayout mHintLayout;

    String userId;
    String page = "1";

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
        if (arguments != null) {
            userId = arguments.getString("userId");
            getData(userId, page);
        }
    }

    private void getData(String userId, String page) {
        showLoading();
        RetrofitClient.getRetrofitService().getPersonalProduction(userId, page)
                .enqueue(new Callback<UserProductionOrLoveBean>() {
                    @Override
                    public void onResponse(Call<UserProductionOrLoveBean> call, Response<UserProductionOrLoveBean> response) {
                        UserProductionOrLoveBean userProductionOrLoveBean = response.body();
                        if (userProductionOrLoveBean.getCode().equals("200")) {
                            List<UserProductionOrLoveBean.DataBean> dataBeanList = userProductionOrLoveBean.getData();
                            if (dataBeanList.size() == 0) {
                                showEmpty();
                            } else {
                                showComplete();
                                fansAdapter = new PersonLoveGridVideoAdapter(dataBeanList);
                                mPersonalProoductionRecyclerView.setAdapter(fansAdapter);
                            }
                        } else {
                            toast("获取数据失败");
                            showError(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    getData(userId, page);
                                }
                            });
                        }

                    }

                    @Override
                    public void onFailure(Call<UserProductionOrLoveBean> call, Throwable t) {
                        toast("获取数据失败");
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
