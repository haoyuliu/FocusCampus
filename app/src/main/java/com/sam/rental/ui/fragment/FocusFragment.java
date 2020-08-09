package com.sam.rental.ui.fragment;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sam.rental.R;
import com.sam.rental.adapter.FansAdapter;
import com.sam.rental.bean.DataCreate;
import com.sam.rental.bean.FansBean;
import com.sam.rental.common.MyFragment;
import com.sam.rental.http.net.RetrofitClient;
import com.sam.rental.ui.activity.HomeActivity;

import java.net.HttpURLConnection;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 关注好友的列表
 */
public class FocusFragment extends MyFragment<HomeActivity> {

    @BindView(R.id.recycle_focus)
    RecyclerView mRecyclerView;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_focus;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        String userId = "368499958493609284";
        String page = "1";
        getData(userId,page);
    }

    private void getData(String userId, String page) {
        RetrofitClient.getRetrofitService().getFocus(userId,page)
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
