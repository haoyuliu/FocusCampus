package com.sam.rental.ui.fragment;

import android.os.CountDownTimer;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.sam.base.BaseFragment;
import com.sam.rental.R;
import com.sam.rental.adapter.GridVideoAdapter;
import com.sam.rental.bean.DataCreate;
import com.sam.rental.common.MyFragment;
import com.sam.rental.ui.activity.HomeActivity;

import butterknife.BindView;

/**
 * description 附近的人fragment
 */
public class CurrentLocationFragment extends MyFragment<HomeActivity> {

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_current_location;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }
}
