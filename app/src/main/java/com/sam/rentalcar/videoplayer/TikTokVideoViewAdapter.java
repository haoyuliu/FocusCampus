package com.sam.rentalcar.videoplayer;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.sam.rentalcar.R;
import com.sam.rentalcar.bean.UserProductionOrLoveBean;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * author:sam
 * time:2020/10/24
 * desc: 个人喜欢和个人作品视频播放页的适配器
 * version:1.0
 */
public class TikTokVideoViewAdapter extends RecyclerView.Adapter<TikTokVideoViewAdapter.VideoHolder> {
    private static final String TAG = "TikTokAdapter";
    private List<UserProductionOrLoveBean.DataBean> videos;

    public TikTokVideoViewAdapter(List<UserProductionOrLoveBean.DataBean> videos) {
        this.videos = videos;
    }


    @Override
    public VideoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder: ");
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tik_tok, parent, false);
        return new VideoHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final VideoHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: " + position);
        UserProductionOrLoveBean.DataBean item = videos.get(position);
        Glide.with(holder.thumb.getContext())
                .load(item.getVideoImageUrl())
                .placeholder(android.R.color.white)
                .into(holder.thumb);
        CircleImageView holderHeadImage = holder.mTikTokView.findViewById(R.id.home_user_image);
        Glide.with(holder.thumb.getContext())
                .load(item.getHeadImg())
                .placeholder(android.R.color.white)
                .into(holderHeadImage);
        TextView viewById = holder.mTikTokView.findViewById(R.id.tv_title);
        viewById.setText(item.getVideoTitle());

        TextView userName = holder.mTikTokView.findViewById(R.id.home_user_name);
        userName.setText(item.getNickName());
        holder.mPosition = position;
        PreloadManager.getInstance(holder.itemView.getContext()).addPreloadTask(item.getVideoUrl(), position);
    }

    @Override
    public void onViewDetachedFromWindow(@NonNull VideoHolder holder) {
        super.onViewDetachedFromWindow(holder);
        UserProductionOrLoveBean.DataBean item = videos.get(holder.mPosition);
        PreloadManager.getInstance(holder.itemView.getContext()).removePreloadTask(item.getVideoUrl());
    }

    @Override
    public int getItemCount() {
        return videos.size();
    }

    public static class VideoHolder extends RecyclerView.ViewHolder {

        private ImageView thumb;
        public TikTokView mTikTokView;
        public int mPosition;
        public FrameLayout mPlayerContainer;

        VideoHolder(View itemView) {
            super(itemView);
            mTikTokView = itemView.findViewById(R.id.tiktok_view);
            thumb = mTikTokView.findViewById(R.id.iv_thumb);
            mPlayerContainer = itemView.findViewById(R.id.container);
            itemView.setTag(this);
        }
    }
}
