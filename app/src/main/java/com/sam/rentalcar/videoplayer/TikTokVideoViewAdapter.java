package com.sam.rentalcar.videoplayer;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.sam.base.BaseDialog;
import com.sam.rentalcar.R;
import com.sam.rentalcar.bean.UserProductionOrLoveBean;
import com.sam.rentalcar.http.net.RetrofitClient;
import com.sam.rentalcar.http.request.DeleteVideoRequestBean;
import com.sam.rentalcar.http.response.DeleteVideoResponseBean;
import com.sam.rentalcar.ui.dialog.MessageDialog;
import com.sam.rentalcar.utils.SPUtils;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * author:sam
 * time:2020/10/24
 * desc: 个人喜欢和个人作品视频播放页的适配器
 * version:1.0
 */
public class TikTokVideoViewAdapter extends RecyclerView.Adapter<TikTokVideoViewAdapter.VideoHolder> {
    private static final String TAG = "TikTokAdapter";
    private List<UserProductionOrLoveBean.DataBean> videos;

    private ItemCommentOnClickInterface itemOnClickInterface;


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
        String userId = SPUtils.getInstance(holder.thumb.getContext()).getString("UserId");
        String itemUserId = String.valueOf(item.getUserId());
        if (itemUserId.equals(userId) && !TextUtils.isEmpty(userId)) {
            holder.mImageViewDelete.setVisibility(View.VISIBLE);
            holder.mImageViewDelete.setOnClickListener(v -> {
                // 删除个人作品
                new MessageDialog.Builder(holder.thumb.getContext())
                        // 标题可以不用填写
                        .setTitle("删除视频")
                        // 内容必须要填写
                        .setMessage("是否删除此视频")
                        // 确定按钮文本
                        .setConfirm("确定")
                        // 设置 null 表示不显示取消按钮
                        .setCancel("取消")
                        // 设置点击按钮后不关闭对话框
                        //.setAutoDismiss(false)
                        .setListener(new MessageDialog.OnListener() {

                            @Override
                            public void onConfirm(BaseDialog dialog) {
                                DeleteVideoRequestBean deleteVideoRequestBean = new DeleteVideoRequestBean();
                                deleteVideoRequestBean.setVideoId(item.getVideoId());
                                deleteVideoRequestBean.setUserId(userId);
                                RetrofitClient.getRetrofitService().deleteVideo(deleteVideoRequestBean).enqueue(new Callback<DeleteVideoResponseBean>() {
                                    @Override
                                    public void onResponse(Call<DeleteVideoResponseBean> call, Response<DeleteVideoResponseBean> response) {
                                        DeleteVideoResponseBean videoResponseBean = response.body();
                                        if (videoResponseBean.getCode().equals("200")) {
                                            videos.remove(item.getVideoId());
                                            notifyDataSetChanged();
                                            Toast.makeText(holder.thumb.getContext(), "删除成功", Toast.LENGTH_SHORT).show();
                                        } else {
                                            Toast.makeText(holder.thumb.getContext(), "删除失败", Toast.LENGTH_SHORT).show();
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<DeleteVideoResponseBean> call, Throwable t) {
                                        Toast.makeText(holder.thumb.getContext(), "删除失败", Toast.LENGTH_SHORT).show();

                                    }
                                });
                            }

                            @Override
                            public void onCancel(BaseDialog dialog) {
                            }
                        })
                        .show();
            });
        }
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

        TextView userComment = holder.mTikTokView.findViewById(R.id.tv_comment_count);
        userComment.setText(item.getVideoCommitCount());

        TextView userLike = holder.mTikTokView.findViewById(R.id.tv_like_count);
        userLike.setText(item.getVideoLikeCount());

        ImageView userCommentImageView = holder.mTikTokView.findViewById(R.id.iv_comment);
        userCommentImageView.setOnClickListener(v -> {
            itemOnClickInterface.onItemClick(position);
        });

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
        private ImageView mImageViewDelete;

        VideoHolder(View itemView) {
            super(itemView);
            mTikTokView = itemView.findViewById(R.id.tiktok_view);
            thumb = mTikTokView.findViewById(R.id.iv_thumb);
            mPlayerContainer = itemView.findViewById(R.id.container);
            mImageViewDelete = itemView.findViewById(R.id.iv_delete);
            itemView.setTag(this);
        }
    }

    /**
     * 评论点击的回调接口
     */
    public interface ItemCommentOnClickInterface {
        void onItemClick(int position);
    }

    /**
     * 定义回调方法
     */

    public void setItemOnClickInterface(ItemCommentOnClickInterface itemOnClickInterface) {
        this.itemOnClickInterface = itemOnClickInterface;
    }
}
