package com.sam.rental.ui.fragment;

import com.sam.rental.R;
import com.sam.rental.common.MyFragment;
import com.sam.rental.ui.activity.CopyActivity;

/**
 *    desc   : 我的页面用户赞过的作品页面
 */
public final class MinePraiseFragment extends MyFragment<CopyActivity> {


    public static MinePraiseFragment newInstance() {
        return new MinePraiseFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_praise_mine;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }
}