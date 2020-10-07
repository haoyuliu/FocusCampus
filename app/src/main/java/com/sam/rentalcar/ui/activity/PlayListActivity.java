package com.sam.rentalcar.ui.activity;

import com.sam.base.BaseActivity;
import com.sam.rentalcar.R;
import com.sam.rentalcar.ui.fragment.RecommendFragment;

/**
 * description 视频全屏播放页
 */
public class PlayListActivity extends BaseActivity {
    public static int initPos;
    private RecommendFragment mRecommendFragment;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_play_list;
    }

    @Override
    protected void initView() {
        if (mRecommendFragment ==null) {
            mRecommendFragment = new RecommendFragment();
        }
        getSupportFragmentManager().beginTransaction().add(R.id.framelayout, mRecommendFragment).commit();
    }

    @Override
    protected void initData() {

    }


}
