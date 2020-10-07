package com.sam.rentalcar.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.dueeeke.videoplayer.player.VideoView;
import com.sam.rentalcar.R;
import com.sam.rentalcar.bean.VideoListBean;

import java.util.List;

/**
 * create by libo
 * create on 2020-05-20
 * description
 */
public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.ViewHolder> {


    private final List<VideoListBean.DataBean> videos;

    public VideoAdapter(List<VideoListBean.DataBean> data) {
        this.videos = data;
    }

    @NonNull
    @Override
    public VideoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_recommend, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoAdapter.ViewHolder holder, int position) {
        VideoListBean.DataBean dataBean = videos.get(position);
        holder.mTikTokView.setUrl(dataBean.getVideoUrl());
    }


    @Override
    public int getItemCount() {
        return videos.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public VideoView mTikTokView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mTikTokView = itemView.findViewById(R.id.tiktok_View);
        }
    }
}
