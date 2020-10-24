package com.sam.rentalcar.videoplayer;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.dueeeke.videoplayer.player.VideoView;
import com.dueeeke.videoplayer.util.L;
import com.sam.rentalcar.R;
import com.sam.rentalcar.bean.UserProductionOrLoveBean;
import com.sam.rentalcar.controller.TikTokController;
import com.sam.rentalcar.http.net.RetrofitClient;
import com.sam.rentalcar.http.response.CommentListBean;
import com.sam.rentalcar.widget.CommentDialog;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 点击个人主页或者查看其他用户头像
 * 短视频播放页, 使用RecyclerView实现
 */
@Deprecated
public class TikTokActivity extends BaseVideoViewActivity<VideoView> {

    private TikTokController mController;
    private int mCurPos;
    private RecyclerView mRecyclerView;
    private List<UserProductionOrLoveBean.DataBean> mVideoList = new ArrayList<>();

    private TikTokVideoViewAdapter mTikTokAdapter;

    private static final String KEY_INDEX = "index";
    private static final String KEY_LIST = "list";

    private int mIndex;

    public static void start(Context context, int index, List<UserProductionOrLoveBean.DataBean> data) {
        Intent intent = new Intent(context, TikTokActivity.class);
        intent.putExtra(KEY_INDEX, index);
        intent.putExtra(KEY_LIST, (Serializable) data);
        context.startActivity(intent);
    }

    public static void start(Context context, int index) {
        Intent i = new Intent(context, TikTokActivity.class);
        i.putExtra(KEY_INDEX, index);

        context.startActivity(i);
    }

    @Override
    protected int getTitleResId() {
        return R.string.str_tiktok_1;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_tiktok;
    }

    @Override
    protected void initView() {
        super.initView();
        setStatusBarTransparent();
        mVideoView = new VideoView(this);
        //以下只能二选一，看你的需求
        //mVideoView.setRenderViewFactory(TikTokRenderViewFactory.create());
        mVideoView.setScreenScaleType(VideoView.SCREEN_SCALE_CENTER_CROP);
        mVideoView.setLooping(true);
        mController = new TikTokController(this);
        mVideoView.setVideoController(mController);

        initRecyclerView();

        addData(null);
        Intent extras = getIntent();

        mIndex = extras.getIntExtra(KEY_INDEX, 0);
        mRecyclerView.scrollToPosition(mIndex);

        mTikTokAdapter.setItemOnClickInterface(new TikTokVideoViewAdapter.ItemCommentOnClickInterface() {
            @Override
            public void onItemClick(int position) {
                //评论
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
                                    commentDialog.show(getSupportFragmentManager(), "");
                                } else {
                                    Toast.makeText(TikTokActivity.this, "获取评论数据失败", Toast.LENGTH_SHORT).show();
                                }

                            }

                            @Override
                            public void onFailure(Call<CommentListBean> call, Throwable t) {
                                Toast.makeText(TikTokActivity.this, "获取评论数据失败", Toast.LENGTH_SHORT).show();
                            }
                        });


            }
        });
    }

    private void initRecyclerView() {
        mRecyclerView = findViewById(R.id.rv);

        mTikTokAdapter = new TikTokVideoViewAdapter(mVideoList);
        ViewPagerLayoutManager layoutManager = new ViewPagerLayoutManager(this, OrientationHelper.VERTICAL);

        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mTikTokAdapter);
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
                if (mCurPos == position) return;
                startPlay(position);
            }
        });
    }

    private void startPlay(int position) {
        View itemView = mRecyclerView.getChildAt(0);
        TikTokVideoViewAdapter.VideoHolder viewHolder = (TikTokVideoViewAdapter.VideoHolder) itemView.getTag();
        mVideoView.release();
        Utils.removeViewFormParent(mVideoView);
        UserProductionOrLoveBean.DataBean item = mVideoList.get(position);
        String playUrl = PreloadManager.getInstance(this).getPlayUrl(item.getVideoUrl());
        L.i("startPlay: " + "position: " + position + "  url: " + playUrl);
        mVideoView.setUrl(playUrl);
        mController.addControlComponent(viewHolder.mTikTokView, true);
        viewHolder.mPlayerContainer.addView(mVideoView, 0);
        mVideoView.start();
        mCurPos = position;
    }

    public void addData(View view) {
        List<UserProductionOrLoveBean.DataBean> dataBeanList = (List<UserProductionOrLoveBean.DataBean>) getIntent().getSerializableExtra(KEY_LIST);
        mVideoList.addAll(dataBeanList);
        mTikTokAdapter.notifyDataSetChanged();
    }
}
