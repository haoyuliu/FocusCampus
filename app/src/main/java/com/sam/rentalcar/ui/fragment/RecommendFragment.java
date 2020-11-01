package com.sam.rentalcar.ui.fragment;


import android.util.Log;
import android.view.View;

import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.dueeeke.videoplayer.player.VideoView;
import com.sam.rentalcar.R;
import com.sam.rentalcar.bean.VideoListBean;
import com.sam.rentalcar.common.MyFragment;
import com.sam.rentalcar.controller.TikTokController;
import com.sam.rentalcar.http.net.RetrofitClient;
import com.sam.rentalcar.http.response.CommentListBean;
import com.sam.rentalcar.ui.activity.HomeActivity;
import com.sam.rentalcar.videoplayer.PreloadManager;
import com.sam.rentalcar.videoplayer.TikTokAdapter;
import com.sam.rentalcar.videoplayer.Utils;
import com.sam.rentalcar.videoplayer.ViewPagerLayoutManager;
import com.sam.rentalcar.widget.CommentDialog;
import com.sam.rentalcar.videoplayer.OnViewPagerListener;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 首页中的推荐页面
 */
public class RecommendFragment extends MyFragment<HomeActivity> {
    private int pageIndex = 1;
    private int pageSize = 50;
    private TikTokController mController;
    private int mCurPos;
    private RecyclerView mRecyclerView;
    private List<VideoListBean.DataBean> mVideoList = new ArrayList<>();
    private TikTokAdapter mTikTokAdapter;

    private static final String KEY_INDEX = "index";
    private int mIndex;
    // 播放器
    private VideoView mVideoView;

    public static RecommendFragment newInstance() {
        return new RecommendFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_recommend;
    }

    @Override
    protected void initView() {
        mVideoView = new VideoView(getContext());
        mVideoView.setScreenScaleType(VideoView.SCREEN_SCALE_CENTER_CROP);
        mVideoView.setLooping(true);
        mController = new TikTokController(getContext());
        mVideoView.setVideoController(mController);
        mRecyclerView = findViewById(R.id.rv);
        // 获取数据
        getVideoData(pageIndex, pageSize);
        mRecyclerView.scrollToPosition(mIndex);
    }


    private void startPlay(int position) {
        View itemView = mRecyclerView.getChildAt(0);
        TikTokAdapter.VideoHolder viewHolder = (TikTokAdapter.VideoHolder) itemView.getTag();
        mVideoView.release();
        Utils.removeViewFormParent(mVideoView);
        VideoListBean.DataBean item = mVideoList.get(position);
        String playUrl = PreloadManager.getInstance(getContext()).getPlayUrl(item.getVideoUrl());
        mVideoView.setUrl(playUrl);
        mController.addControlComponent(viewHolder.mTikTokView, true);
        viewHolder.mPlayerContainer.addView(mVideoView, 0);
        mVideoView.start();
        mCurPos = position;
    }

    @Override
    protected void initData() {

    }

    private void getVideoData(int pageIndex, int pageSize) {
        RetrofitClient.getRetrofitService().loadHomeVideoListData(pageIndex, pageSize).enqueue(new Callback<VideoListBean>() {
            @Override
            public void onResponse(Call<VideoListBean> call, Response<VideoListBean> response) {
                VideoListBean listBean = response.body();
                if (listBean.getCode().equals("200")) {
                    Log.d("RecommendFragment", response.body().getData().toString());
                    mVideoList = response.body().getData();
                    if (mVideoList.size() == 0) {
                        toast("暂时没有推荐内容");
                    }
                    mTikTokAdapter = new TikTokAdapter(mVideoList);
                    ViewPagerLayoutManager layoutManager = new ViewPagerLayoutManager(getContext(), OrientationHelper.VERTICAL);

                    mRecyclerView.setLayoutManager(layoutManager);
                    mRecyclerView.setAdapter(mTikTokAdapter);
                    Log.d("数据", response.code() + "" + response.body().getData().toString());
                    layoutManager.setOnViewPagerListener(new OnViewPagerListener() {
                        @Override
                        public void onInitComplete() {
                            //自动播放第index条
                            startPlay(mIndex);
                        }

                        @Override
                        public void onPageRelease(boolean isNext, int position) {
                            if (mCurPos == position) {
                                mVideoView.release();
                            }
                        }

                        @Override
                        public void onPageSelected(int position, boolean isBottom) {
                            if (mCurPos == position) {
                                return;
                            }
                            startPlay(position);
                        }
                    });
                    mTikTokAdapter.setItemOnClickInterface(new TikTokAdapter.ItemCommentOnClickInterface() {
                        @Override
                        public void onItemClick(int position) {
                            // 获取评论数据
                            RetrofitClient.getRetrofitService().getCommentList(mVideoList.get(position).getVideoId(), "1", "10")
                                    .enqueue(new Callback<CommentListBean>() {
                                        @Override
                                        public void onResponse(Call<CommentListBean> call, Response<CommentListBean> response) {
                                            CommentListBean commentListBean = response.body();

                                            if (commentListBean.getCode().equals("200")) {
                                                CommentDialog commentDialog = new CommentDialog();
                                                commentDialog.setData(response.body().getData());
                                                commentDialog.setVideoid(mVideoList.get(position).getVideoId());
                                                commentDialog.setUserid(mVideoList.get(position).getUserId());
                                                commentDialog.show(getChildFragmentManager(), "");
                                            } else {
                                                toast("获取评论数据失败");
                                            }

                                        }

                                        @Override
                                        public void onFailure(Call<CommentListBean> call, Throwable t) {
                                            toast("获取评论数据失败");
                                        }
                                    });


                        }
                    });
                }

            }

            @Override
            public void onFailure(Call<VideoListBean> call, Throwable t) {
                toast("获取视频数据失败");
            }
        });

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        setVideoViewState(isVisibleToUser);
    }

    public void setVideoViewState(boolean isVisibleToUser) {
        if (mVideoView == null)
            return;
        if (isVisibleToUser) {
            mVideoView.start();
            //toast("推荐播放");
        } else {
            mVideoView.pause();
            //toast("推荐暂停");
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) {
            mVideoView.pause();
            //toast("推荐暂停");
        } else {
            mVideoView.start();
            // toast("推荐播放");
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        mVideoView.pause();
        // toast("关注暂停");
    }
}
