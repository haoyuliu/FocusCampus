package com.sam.rental.ui.activity;

import com.sam.base.BaseActivity;
import com.sam.rental.R;
import com.sam.rental.ui.fragment.RecommendFragment;

/**
 * description 视频全屏播放页
 */
public class PlayListActivity extends BaseActivity {
    public static int initPos;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_play_list;
    }

    @Override
    protected void initView() {
        getSupportFragmentManager().beginTransaction().add(R.id.framelayout, new RecommendFragment()).commit();
    }

    @Override
    protected void initData() {

    }
}
