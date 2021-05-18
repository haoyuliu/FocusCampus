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
 * 个人喜欢的Fragment
 */
public class PersonalLoveFragment extends MyFragment<HomeActivity> implements StatusAction {
    @BindView(R.id.recycle_personal_love)
    RecyclerView mPersonalLoveRecyclerView;

    @BindView(R.id.hl_status_hint)
    HintLayout mHintLayout;

    String userId;
    String page = "1";

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_personal_love;
    }

    @Override
    protected void initView() {
        mPersonalLoveRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
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
        RetrofitClient.getRetrofitService().getPersonalLove(userId, page)
                .enqueue(new Callback<UserProductionOrLoveBean>() {
                    @Override
                    public void onResponse(Call<UserProductionOrLoveBean> call, Response<UserProductionOrLoveBean> response) {
                        UserProductionOrLoveBean productionOrLoveBean = response.body();
                        if (productionOrLoveBean.getCode().equals("200")) {
                            List<UserProductionOrLoveBean.DataBean> dataBeanList = productionOrLoveBean.getData();
                            if (dataBeanList.size() == 0) {
                                showEmpty();
                            } else {
                                showComplete();
                                PersonLoveGridVideoAdapter fansAdapter = new PersonLoveGridVideoAdapter(dataBeanList);
                                mPersonalLoveRecyclerView.setAdapter(fansAdapter);
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
                        toast("网络错误");
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
