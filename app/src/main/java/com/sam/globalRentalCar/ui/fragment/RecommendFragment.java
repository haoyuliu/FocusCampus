package com.sam.globalRentalCar.ui.fragment;


import android.content.Intent;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.sdk.android.vod.upload.common.utils.StringUtil;
import com.dueeeke.videoplayer.player.VideoView;
import com.sam.globalRentalCar.R;
import com.sam.globalRentalCar.action.StatusAction;
import com.sam.globalRentalCar.bean.VideoListBean;
import com.sam.globalRentalCar.common.MyFragment;
import com.sam.globalRentalCar.controller.TikTokController;
import com.sam.globalRentalCar.http.net.RetrofitClient;
import com.sam.globalRentalCar.http.response.CommentListBean;
import com.sam.globalRentalCar.ui.activity.HomeActivity;
import com.sam.globalRentalCar.ui.activity.LoginActivity;
import com.sam.globalRentalCar.utils.SPUtils;
import com.sam.globalRentalCar.videoplayer.OnViewPagerListener;
import com.sam.globalRentalCar.videoplayer.PreloadManager;
import com.sam.globalRentalCar.videoplayer.TikTokAdapter;
import com.sam.globalRentalCar.videoplayer.Utils;
import com.sam.globalRentalCar.videoplayer.ViewPagerLayoutManager;
import com.sam.globalRentalCar.widget.CommentDialog;
import com.sam.globalRentalCar.widget.HintLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 首页中的推荐页面
 */
public class RecommendFragment extends MyFragment<HomeActivity> implements StatusAction {
    private int pageIndex = 1;
    private int pageSize = 20;
    private TikTokController mController;
    private int mCurPos;
    private RefreshLayout mSmartRefreshLayout;
    private RecyclerView mRecyclerView;
    private List<VideoListBean.DataBean> mVideoList = new ArrayList<>();
    private TikTokAdapter mTikTokAdapter;

    private static final String KEY_INDEX = "index";
    private int mIndex;
    // 播放器
    private VideoView mVideoView;

    @BindView(R.id.hl_status_hint)
    HintLayout mHintLayout;

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
        mSmartRefreshLayout = findViewById(R.id.smart_refresh);
        mRecyclerView = findViewById(R.id.rv);
        mTikTokAdapter = new TikTokAdapter();
        mRecyclerView.setAdapter(mTikTokAdapter);
        // 获取数据,首次进来，肯定是刷新，为true
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

    @Override
    protected void initData() {
        mSmartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                // 下拉刷新
                pageIndex = 1;
                mVideoList.clear();
                getVideoData(true);
                Log.d("RecommendFragment", "onRefreshPageIndex----->" + pageIndex);
            }
        });
        mSmartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                // 上拉加载更多
                pageIndex++;
                getVideoData(false);
                Log.d("RecommendFragment", "onLoadMorePageIndex----->" + pageIndex);
            }
        });
    }

    private void getVideoData(boolean isRefresh) {
        showLoading();
        RetrofitClient.getRetrofitService().loadHomeVideoListData(SPUtils.getInstance(getContext()).getString("token"), pageIndex, pageSize).enqueue(new Callback<VideoListBean>() {
            @Override
            public void onResponse(Call<VideoListBean> call, Response<VideoListBean> response) {
                if (isRefresh) {
                    // 如果是刷新
                    mSmartRefreshLayout.finishRefresh(true);
                } else {
                    // 上拉加载
                    mSmartRefreshLayout.finishLoadMore(true);
                }
                VideoListBean listBean = response.body();
                if (listBean.getCode().equals("200")) {
                    Log.d("RecommendFragment", response.body().getData().toString());
                    List<VideoListBean.DataBean> responseList = response.body().getData();
                    if (responseList.size() == 0) {
                        if (isRefresh) {
                            toast("暂时没有推荐内容");
                        } else {
                            toast("没有更多数据");
                        }
                        showEmpty();
                    } else {
                        showComplete();
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
                                String token = SPUtils.getInstance(getContext()).getString("token");
                                if (StringUtil.isEmpty(token)) {
                                    Intent intent = new Intent(getContext(), LoginActivity.class);
                                    getContext().startActivity(intent);
                                    return;
                                } else {
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
                                                        String userId = SPUtils.getInstance(getActivity()).getString("UserId");
                                                        if (userId != null) {
                                                            commentDialog.setUserid(Long.valueOf(userId));
                                                        }
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
                            }
                        });
                    }
                }
            }

            @Override
            public void onFailure(Call<VideoListBean> call, Throwable t) {
                if (isRefresh) {
                    // 如果是刷新
                    mSmartRefreshLayout.finishRefresh(true);
                } else {
                    // 上拉加载
                    mSmartRefreshLayout.finishLoadMore(true);
                }
                toast("获取视频数据失败");
                showError(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getVideoData(true);
                    }
                });
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
        } else {
            mVideoView.pause();
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) {
            mVideoView.pause();
        } else {
            mVideoView.start();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        mVideoView.pause();
    }

    @Override
    public HintLayout getHintLayout() {
        return mHintLayout;
    }
}
