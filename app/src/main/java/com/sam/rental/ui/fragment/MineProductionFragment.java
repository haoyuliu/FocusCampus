package com.sam.rental.ui.fragment;

import com.sam.rental.R;
import com.sam.rental.common.MyFragment;
import com.sam.rental.ui.activity.CopyActivity;

/**
 *    desc   : 我的页面用户作品
 */
public final class MineProductionFragment extends MyFragment<CopyActivity> {


    public static MineProductionFragment newInstance() {
        return new MineProductionFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mine_production;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }
}