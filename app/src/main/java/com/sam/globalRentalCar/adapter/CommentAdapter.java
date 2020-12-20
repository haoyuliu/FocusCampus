package com.sam.globalRentalCar.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.sam.globalRentalCar.R;
import com.sam.globalRentalCar.http.response.CommentListBean;
import com.sam.globalRentalCar.common.BaseRvAdapter;
import com.sam.globalRentalCar.common.BaseRvViewHolder;
import com.sam.globalRentalCar.widget.CircleImageView;

import java.util.List;

import butterknife.BindView;

/**
 * description
 */
public class CommentAdapter extends BaseRvAdapter<CommentListBean.DataBean, CommentAdapter.CommentViewHolder> {

    public CommentAdapter(Context context, List<CommentListBean.DataBean> datas) {
        super(context, datas);
    }

    @Override
    protected void onBindData(CommentViewHolder holder, CommentListBean.DataBean commentBean, int position) {
        //holder.ivHead.setImageResource(commentBean.getUserImg());
        Glide.with(holder.itemView.getContext()).load(commentBean.getUserImg()).into(holder.ivHead);
        holder.tvNickname.setText(commentBean.getUserId() + "");
        holder.tvContent.setText(commentBean.getContent());
       // holder.tvLikecount.setText(commentBean.);

    }

    @NonNull
    @Override
    public CommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_comment, parent, false);
        return new CommentViewHolder(view);
    }

    public class CommentViewHolder extends BaseRvViewHolder {
        @BindView(R.id.iv_head)
        CircleImageView ivHead;
        @BindView(R.id.tv_nickname)
        TextView tvNickname;
        @BindView(R.id.tv_content)
        TextView tvContent;
        @BindView(R.id.tv_likecount)
        TextView tvLikecount;

        public CommentViewHolder(View itemView) {
            super(itemView);
        }
    }
}
