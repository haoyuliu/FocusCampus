package com.sam.globalRentalCar.videoplayer;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.alibaba.sdk.android.vod.upload.common.utils.StringUtil;
import com.bumptech.glide.Glide;
import com.sam.globalRentalCar.R;
import com.sam.globalRentalCar.bean.VideoListBean;
import com.sam.globalRentalCar.http.net.RetrofitClient;
import com.sam.globalRentalCar.http.request.HomeVideoLikeRequestBean;
import com.sam.globalRentalCar.http.response.FollowResponseBean;
import com.sam.globalRentalCar.http.response.HomeVideoLikeResponseBean;
import com.sam.globalRentalCar.ui.activity.LoginActivity;
import com.sam.globalRentalCar.ui.activity.PersonalHomeActivity;
import com.sam.globalRentalCar.utils.SPUtils;
import com.sam.globalRentalCar.widget.CircleImageView;
import com.sam.globalRentalCar.widget.IconFontTextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;
import static android.view.animation.Animation.INFINITE;

/**
 * 获取推荐或者个人作品的适配是
 */
@Deprecated
public class TikTokAdapter extends RecyclerView.Adapter<TikTokAdapter.VideoHolder> {

    private List<VideoListBean.DataBean> videos;
    private ItemCommentOnClickInterface itemOnClickInterface;

    private boolean isClick = false;

    public void setVideos(List<VideoListBean.DataBean> videos) {
        this.videos = videos;
    }

    public TikTokAdapter() {
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
        Log.d(TAG, "onBindViewHolder: " + position + videos.toString());
        VideoListBean.DataBean item = videos.get(position);
        Glide.with(holder.thumb.getContext())
                .load(item.getVideoImageUrl())
                .placeholder(android.R.color.white)
                .into(holder.thumb);
        holder.mTitleTextView.setText(item.getVideoTitle() + "");
        holder.mHomeUserTextView.setText(item.getNickName() + "");
        holder.mLikeCount.setText(item.getVideoLikeCount() + "");
        holder.mCommentCount.setText(item.getVideoCommitCount() + "");
        Glide.with(holder.thumb.getContext()).load(item.getHeadImg()).into(holder.mCircleImageView);
        Glide.with(holder.thumb.getContext()).load(item.getHeadImg()).into(holder.mCircleBottomView);
        holder.mFollowImageView.setVisibility(item.isBfollow() ? INVISIBLE : VISIBLE);
        holder.mPosition = position;
        //默认进来判断点赞状态
        boolean blike = item.isBlike();
        if (blike) {
            // 默认是非点赞状态
            holder.mLottieAnimationView.setVisibility(VISIBLE);
            holder.homeIconFont.setTextColor(holder.thumb.getContext().getResources().getColor(R.color.color_FF0041));
        } else {
            holder.mLottieAnimationView.setVisibility(INVISIBLE);
            holder.homeIconFont.setTextColor(holder.thumb.getContext().getResources().getColor(R.color.white));
        }
        // PreloadManager.getInstance(holder.itemView.getContext()).addPreloadTask(item.videoDownloadUrl, position);
        // 头像的点击事件
        holder.mCircleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onBindViewHolderItem " + videos.get(position).toString());
                Intent intent = new Intent(holder.thumb.getContext(), PersonalHomeActivity.class);
                intent.putExtra("userId", item.getUserId() + "");
                intent.putExtra("HeadImage", item.getHeadImg());
                intent.putExtra("NickName", item.getNickName());
                intent.putExtra("isFoucs", item.isBfollow());
                intent.putExtra("huid", item.getHxuid());
                Log.d(TAG, "id" + videos.get(position).getId() + "HeadImage" + item.getHeadImg() + "NickName" + item.getNickName());
                holder.thumb.getContext().startActivity(intent);
            }
        });
        //评论的点击事件
        holder.mCommontImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemOnClickInterface.onItemClick(position);
            }
        });
        // 点赞的点击事件
        holder.homeRlLke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 关注的点击事件
                String token = SPUtils.getInstance(holder.thumb.getContext()).getString("token");
                if (StringUtil.isEmpty(token)) {
                    Intent intent = new Intent(holder.thumb.getContext(), LoginActivity.class);
                    holder.thumb.getContext().startActivity(intent);
                    return;
                }
                boolean blike = videos.get(position).isBlike();
                if (blike) {
                    //Glide.with(holder.thumb.getContext()).load(R.mipmap.white_xin).into(holder.mImageViewXin);
                    //取消点赞
                    holder.mLottieAnimationView.setVisibility(INVISIBLE);
                    holder.homeIconFont.setTextColor(holder.thumb.getContext().getResources().getColor(R.color.white));
                    likeViewPostLike(blike, position);
                } else {
                    //Glide.with(holder.thumb.getContext()).load(R.mipmap.red_xin).into(holder.mImageViewXin);
                    //点赞
                    holder.mLottieAnimationView.setVisibility(VISIBLE);
                    holder.mLottieAnimationView.playAnimation();
                    holder.homeIconFont.setTextColor(holder.thumb.getContext().getResources().getColor(R.color.color_FF0041));
                    likeViewPostLike(blike, position);
                }
            }
        });
        holder.mLottieAnimationView.setAnimation("like.json");
        holder.mFollowImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 关注的点击事件
                String token = SPUtils.getInstance(holder.thumb.getContext()).getString("token");
                if (StringUtil.isEmpty(token)) {
                    Intent intent = new Intent(holder.thumb.getContext(), LoginActivity.class);
                    holder.thumb.getContext().startActivity(intent);
                    return;
                }
                holder.mFollowImageView.setVisibility(View.GONE);
                RetrofitClient.getRetrofitService().FocusUser(token, videos.get(position).getUserId() + "", 0 + "").enqueue(new Callback<FollowResponseBean>() {
                    @Override
                    public void onResponse(Call<FollowResponseBean> call, Response<FollowResponseBean> response) {
                        FollowResponseBean followResponseBean = response.body();
                        if (followResponseBean.getCode().equals("200")) {
                            holder.mFollowImageView.setVisibility(View.GONE);
                        } else {
                            holder.mFollowImageView.setVisibility(VISIBLE);
                        }

                    }

                    @Override
                    public void onFailure(Call<FollowResponseBean> call, Throwable t) {
                        holder.mFollowImageView.setVisibility(VISIBLE);
                    }
                });
            }
        });
        holder.mImageViewShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //分享
            }
        });

        // 图标动态展示
        setRotateAnim(holder.mRecordLayout);
    }

    /**
     * 循环旋转动画
     *
     * @param recordLayout
     */
    private void setRotateAnim(RelativeLayout recordLayout) {
        RotateAnimation rotateAnimation = new RotateAnimation(0, 359,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation.setRepeatCount(INFINITE);
        rotateAnimation.setDuration(8000);
        rotateAnimation.setInterpolator(new LinearInterpolator());
        recordLayout.startAnimation(rotateAnimation);
    }

    private void likeViewPostLike(boolean blike, int position) {
        HomeVideoLikeRequestBean homeVideoLikeRequestBean = new HomeVideoLikeRequestBean();
        homeVideoLikeRequestBean.setBlike(blike);
        homeVideoLikeRequestBean.setVideoId(videos.get(position).getVideoId() + "");
        //homeVideoLikeRequestBean.setUserId();
        RetrofitClient.getRetrofitService().postVideoLike(homeVideoLikeRequestBean).enqueue(new Callback<HomeVideoLikeResponseBean>() {
            @Override
            public void onResponse(Call<HomeVideoLikeResponseBean> call, Response<HomeVideoLikeResponseBean> response) {
                HomeVideoLikeResponseBean homeVideoLikeResponseBean = response.body();

            }

            @Override
            public void onFailure(Call<HomeVideoLikeResponseBean> call, Throwable t) {

            }
        });
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

    @Override
    public void onViewDetachedFromWindow(@NonNull VideoHolder holder) {
        super.onViewDetachedFromWindow(holder);
        //VideoListBean.DataBean item = videos.get(holder.mPosition);
        //PreloadManager.getInstance(holder.itemView.getContext()).removePreloadTask(item.videoDownloadUrl);
    }

    @Override
    public int getItemCount() {
        if (videos.size() > 0 && videos != null) {
            return videos.size();
        } else {
            return 0;
        }
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
        public TextView mLikeCount;
        public TextView mCommentCount;
        public ImageView mFollowImageView;
        //public ImageView mImageViewXin;
        public RelativeLayout homeRlLke;
        public IconFontTextView homeIconFont;
        public LottieAnimationView mLottieAnimationView;
        public ImageView mImageViewShare;
        public RelativeLayout mRecordLayout;
        public CircleImageView mCircleBottomView;

        VideoHolder(View itemView) {
            super(itemView);
            mTikTokView = itemView.findViewById(R.id.tiktok_view);
            thumb = mTikTokView.findViewById(R.id.iv_thumb);
            mPlayerContainer = itemView.findViewById(R.id.container);
            mTitleTextView = itemView.findViewById(R.id.tv_title);
            mCircleImageView = itemView.findViewById(R.id.home_user_image);
            mHomeUserTextView = itemView.findViewById(R.id.home_user_name);
            mCommontImageView = itemView.findViewById(R.id.iv_comment);
            mLikeCount = itemView.findViewById(R.id.tv_like_count);
            mCommentCount = itemView.findViewById(R.id.tv_comment_count);
            mFollowImageView = itemView.findViewById(R.id.iv_focus);
            // mImageViewXin = itemView.findViewById(R.id.xin);
            homeRlLke = itemView.findViewById(R.id.home_rl_like);
            homeIconFont = itemView.findViewById(R.id.home_iv_like);
            mLottieAnimationView = itemView.findViewById(R.id.home_lottie_anim);
            mImageViewShare = itemView.findViewById(R.id.iv_video_share);
            mRecordLayout = itemView.findViewById(R.id.rl_record_view);
            mCircleBottomView = itemView.findViewById(R.id.iv_head_anim_bottom);
            itemView.setTag(this);
        }
    }
}