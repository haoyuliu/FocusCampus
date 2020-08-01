package com.sam.rental.ui.fragment;


import com.dueeeke.videoplayer.player.VideoView;
import com.sam.rental.R;
import com.sam.rental.common.MyFragment;
import com.sam.rental.ui.activity.HomeActivity;

import butterknife.BindView;

/**
 * 推荐播放页
 */
public class RecommendFragment extends MyFragment<HomeActivity> {
    @BindView(R.id.recommend_player)
    VideoView mVideoview;


    public static RecommendFragment newInstance() {
        return new RecommendFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_recommend;
    }

    @Override
    protected void initView() {
        mVideoview.setUrl("https://outin-8919d46ecbe211ea89ff00163e024c6a.oss-cn-shanghai.aliyuncs.com/sv/64e15ac-173aa30228f/64e15ac-173aa30228f.mp4");
        mVideoview.start(); //开始播放，不调用则不自动播放

    }

    @Override
    protected void initData() {

    }
}
