package com.sam.globalRentalCar.ui.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.dueeeke.videoplayer.player.VideoView;
import com.sam.globalRentalCar.common.BaseLazyFragment;
import com.sam.globalRentalCar.R;
import com.sam.globalRentalCar.bean.VideoListBean;
import com.sam.globalRentalCar.controller.TikTokController;
import com.sam.globalRentalCar.http.net.RetrofitClient;
import com.sam.globalRentalCar.http.response.CommentListBean;
import com.sam.globalRentalCar.videoplayer.PreloadManager;
import com.sam.globalRentalCar.videoplayer.TikTokAdapter;
import com.sam.globalRentalCar.utils.SPUtils;
import com.sam.globalRentalCar.videoplayer.Utils;
import com.sam.globalRentalCar.videoplayer.ViewPagerLayoutManager;
import com.sam.globalRentalCar.widget.CommentDialog;
import com.sam.globalRentalCar.videoplayer.OnViewPagerListener;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 首页中的关注的人fragment
 */
public class CurrentLocationFragment extends BaseLazyFragment {
    public static final String TAG = "CurrentLocationFragment";
    private int pageIndex = 1;
    private int pageSize = 20;
    private TikTokController mController;
    private int mCurPos;
    private RefreshLayout mCurrentRefreshLayout;
    private RecyclerView mRecyclerView;
    private List<VideoListBean.DataBean> mVideoList = new ArrayList<>();
    private TikTokAdapter mTikTokAdapter;

    private static final String KEY_INDEX = "index";
    private int mIndex;
    // 播放器
    private VideoView mVideoView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_current_location, null);
        mRecyclerView = view.findViewById(R.id.follow_rv);
        mCurrentRefreshLayout = view.findViewById(R.id.current_smart);
        mTikTokAdapter = new TikTokAdapter();
        initView();
        initData();
        return view;
    }

    private void initData() {
        mCurrentRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                // 下拉刷新
                pageIndex = 1;
                mVideoList.clear();
                getVideoData(true);
                Log.d("RecommendFragment", "onRefreshPageIndex----->" + pageIndex);
            }
        });
        mCurrentRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                // 上拉加载更多
                pageIndex++;
                getVideoData(false);
                Log.d("RecommendFragment", "onLoadMorePageIndex----->" + pageIndex);
            }
        });
    }


    private void initView() {
        Log.d(TAG, "InitView");
        mVideoView = new VideoView(getContext());
        mVideoView.setScreenScaleType(VideoView.SCREEN_SCALE_CENTER_CROP);
        mVideoView.setLooping(true);
        mController = new TikTokController(getContext());
        mVideoView.setVideoController(mController);
        mTikTokAdapter = new TikTokAdapter();
        mRecyclerView.setAdapter(mTikTokAdapter);
        // 获取数据
        getVideoData(true);
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

    private void getVideoData(boolean isRefresh) {
        RetrofitClient.getRetrofitService().loadHomeFollowedVideoListData(SPUtils.getInstance(getContext()).getString("token"),
                pageIndex).enqueue(new Callback<VideoListBean>() {
            @Override
            public void onResponse(Call<VideoListBean> call, Response<VideoListBean> response) {
                if (isRefresh) {
                    // 如果是刷新
                    mCurrentRefreshLayout.finishRefresh(true);
                } else {
                    // 上拉加载
                    mCurrentRefreshLayout.finishLoadMore(true);
                }
                VideoListBean videoListBean = response.body();
                Log.d(TAG, response.code() + "" + response.body());
                if (videoListBean.getCode().equals("200")) {
                    List<VideoListBean.DataBean> responseList = response.body().getData();
                    if (responseList.size() == 0) {
                        if (isRefresh) {
                            Toast.makeText(getContext(), "暂时没有数据", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getContext(), "没有更多数据", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        if (isRefresh) {
                            mVideoList = responseList;
                        } else {
                            mVideoList.addAll(responseList);
                        }
                        mRecyclerView.setOnFlingListener(null);
                        ViewPagerLayoutManager layoutManager = new ViewPagerLayoutManager(getContext(), OrientationHelper.VERTICAL);
                        mRecyclerView.setLayoutManager(layoutManager);
                        mTikTokAdapter.setVideos(mVideoList);
                        mTikTokAdapter.notifyDataSetChanged();
                        Log.d(TAG, response.code() + "" + response.body().getData().toString());
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
                                                    Toast.makeText(getContext(), "获取评论数据失败", Toast.LENGTH_SHORT).show();
                                                }

                                            }

                                            @Override
                                            public void onFailure(Call<CommentListBean> call, Throwable t) {
                                                Toast.makeText(getContext(), "获取评论数据失败", Toast.LENGTH_SHORT).show();
                                            }
                                        });
                            }
                        });
                    }
                }
            }

            @Override
            public void onFailure(Call<VideoListBean> call, Throwable t) {
                Toast.makeText(getContext(), "获取评论数据失败", Toast.LENGTH_SHORT).show();
            }
        });

    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        setVideoViewState(isVisibleToUser);
    }

    @Override
    public void fetchData() {
        Log.d(TAG, "getVideoData");
        // 请求网络数据
        getVideoData(false);
    }

    public void setVideoViewState(boolean isVisibleToUser) {
        if (mVideoView == null) {
            return;
        }
        if (isVisibleToUser) {
            mVideoView.start();
            //  toast("关注播放");
        } else {
            mVideoView.pause();
            // toast("关注暂停");
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
