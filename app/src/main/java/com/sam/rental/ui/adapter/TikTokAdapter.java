package com.sam.rental.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
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
import com.sam.rental.R;
import com.sam.rental.bean.VideoListBean;
import com.sam.rental.ui.activity.PersonalDataActivity;
import com.sam.rental.ui.activity.PersonalHomeActivity;
import com.sam.rental.widget.CommentDialog;
import com.sam.rental.widget.TikTokView;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

@Deprecated
public class TikTokAdapter extends RecyclerView.Adapter<TikTokAdapter.VideoHolder> {

    private List<VideoListBean.DataBean> videos;
    private ItemCommentOnClickInterface itemOnClickInterface;

    public TikTokAdapter(List<VideoListBean.DataBean> videos) {
        this.videos = videos;
    }

    private static final String TAG = "TikTokAdapter";

    @Override
    public VideoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder: ");
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tik_tok, parent, false);
        return new VideoHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final VideoHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: " + position);
        VideoListBean.DataBean item = videos.get(position);
        Glide.with(holder.thumb.getContext())
                .load(item.getVideoImageUrl())
                .placeholder(android.R.color.white)
                .into(holder.thumb);
        holder.mTitleTextView.setText(item.getVideoDescription());
        holder.mHomeUserTextView.setText(item.getUserId() + "");
        holder.mPosition = position;
        // PreloadManager.getInstance(holder.itemView.getContext()).addPreloadTask(item.videoDownloadUrl, position);
        holder.mCircleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.thumb.getContext().startActivity(new Intent(holder.thumb.getContext(), PersonalHomeActivity.class));
            }
        });
        holder.mCommontImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemOnClickInterface.onItemClick(v);
            }
        });
    }
    //回调接口
    public interface ItemCommentOnClickInterface {
        void onItemClick(View view);
    }

    //定义回调方法
    public void setItemOnClickInterface(ItemCommentOnClickInterface itemOnClickInterface) {
        this.itemOnClickInterface = itemOnClickInterface;
    }

    @Override
    public void onViewDetachedFromWindow(@NonNull VideoHolder holder) {
        super.onViewDetachedFromWindow(holder);
        VideoListBean.DataBean item = videos.get(holder.mPosition);
        //PreloadManager.getInstance(holder.itemView.getContext()).removePreloadTask(item.videoDownloadUrl);
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
        public TextView mTitleTextView;
        public CircleImageView mCircleImageView;
        public TextView mHomeUserTextView;
        public ImageView mCommontImageView;

        VideoHolder(View itemView) {
            super(itemView);
            mTikTokView = itemView.findViewById(R.id.tiktok_View);
            thumb = mTikTokView.findViewById(R.id.iv_thumb);
            mPlayerContainer = itemView.findViewById(R.id.container);
            mTitleTextView = itemView.findViewById(R.id.tv_title);
            mCircleImageView = itemView.findViewById(R.id.home_user_image);
            mHomeUserTextView = itemView.findViewById(R.id.home_user_name);
            mCommontImageView = itemView.findViewById(R.id.iv_comment);
            itemView.setTag(this);
        }
    }
}