package com.sam.rental.ui.activity;

import com.rental.sam.R;
import com.rental.sam.base.BaseActivity;
import com.rental.sam.fragment.RecommendFragment;

/**
 * description 视频全屏播放页
 */
public class PlayListActivity extends BaseActivity {
    public static int initPos;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_play_list;
    }

    @Override
    protected void init() {
        getSupportFragmentManager().beginTransaction().add(R.id.framelayout, new RecommendFragment()).commit();
    }
}
