package com.sam.rentalcar.ui.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.sdk.android.vod.upload.common.utils.StringUtil;
import com.dueeeke.videoplayer.player.VideoView;
import com.hjq.toast.ToastUtils;
import com.sam.rentalcar.BaseLazyFragment;
import com.sam.rentalcar.R;
import com.sam.rentalcar.adapter.ViewPagerLayoutManager;
import com.sam.rentalcar.bean.VideoListBean;
import com.sam.rentalcar.common.MyFragment;
import com.sam.rentalcar.controller.TikTokController;
import com.sam.rentalcar.http.net.RetrofitClient;
import com.sam.rentalcar.http.response.CommentListBean;
import com.sam.rentalcar.ui.activity.HomeActivity;
import com.sam.rentalcar.ui.activity.LoginActivity;
import com.sam.rentalcar.ui.adapter.TikTokAdapter;
import com.sam.rentalcar.utils.SPUtils;
import com.sam.rentalcar.utils.Utils;
import com.sam.rentalcar.widget.CommentDialog;
import com.sam.rentalcar.widget.viewpagerlayoutmanager.OnViewPagerListener;

import java.net.HttpURLConnection;
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
    private int page = 1;
    private int pageSize = 20;
    private TikTokController mController;
    private int mCurPos;
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
        initView();
        return view;
    }


    private void initView() {
        Log.d(TAG, "InitView");
        mVideoView = new VideoView(getContext());
        mVideoView.setScreenScaleType(VideoView.SCREEN_SCALE_CENTER_CROP);
        mVideoView.setLooping(true);
        mController = new TikTokController(getContext());
        mVideoView.setVideoController(mController);
        // 获取数据
        //getVideoData(page);
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
        // mVideoView.start();
        mCurPos = position;
    }


    private void getVideoData(int page) {

        RetrofitClient.getRetrofitService().loadHomeFollowedVideoListData(SPUtils.getInstance(getContext()).getString("token"),
                page).enqueue(new Callback<VideoListBean>() {
            @Override
            public void onResponse(Call<VideoListBean> call, Response<VideoListBean> response) {


                VideoListBean videoListBean = response.body();
                Log.d(TAG, response.code() + "" + response.body());
                if (videoListBean.getCode().equals("401")) {
                    //startActivity(LoginActivity.class);
                    return;
                }
                if (videoListBean.getCode().equals("200")) {
                    mVideoList = response.body().getData();
                    if (mVideoList.size() == 0) {
                        Toast.makeText(getContext(), "暂时没有数据", Toast.LENGTH_SHORT).show();
                    }
                    mTikTokAdapter = new TikTokAdapter(mVideoList);
                    ViewPagerLayoutManager layoutManager = new ViewPagerLayoutManager(getContext(), OrientationHelper.VERTICAL);

                    mRecyclerView.setLayoutManager(layoutManager);
                    mRecyclerView.setAdapter(mTikTokAdapter);
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
                } else {
                    Toast.makeText(getContext(), "获取评论数据失败", Toast.LENGTH_SHORT).show();
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
        getVideoData(page);
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
