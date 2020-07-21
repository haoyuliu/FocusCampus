package com.sam.rental.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import com.rental.sam.utils.NumUtils;
import com.sam.rental.R;
import com.sam.rental.bean.VideoBean;
import com.sam.rental.ui.activity.PlayListActivity;
import com.sam.rental.ui.adapter.BaseRvAdapter;
import com.sam.rental.ui.adapter.BaseRvViewHolder;

import java.util.List;
import butterknife.BindView;

/**
 * create by libo
 * create on 2020-05-21
 * description
 */
public class WorkAdapter extends BaseRvAdapter<VideoBean, WorkAdapter.WorkViewHolder> {

    public WorkAdapter(Context context, List<VideoBean> datas) {
        super(context, datas);
    }

    @Override
    protected void onBindData(WorkViewHolder holder, VideoBean videoBean, int position) {
        holder.ivCover.setImageResource(videoBean.getCoverRes());
        holder.tvLikeCount.setText(NumUtils.numberFilter(videoBean.getLikeCount()));

        holder.itemView.setOnClickListener(v -> {
            PlayListActivity.initPos = position;
            context.startActivity(new Intent(context, PlayListActivity.class));
        });
    }

    @NonNull
    @Override
    public WorkViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rooView = LayoutInflater.from(context).inflate(R.layout.item_work, parent, false);
        return new WorkViewHolder(rooView);
    }

    public class WorkViewHolder extends BaseRvViewHolder {
        @BindView(R.id.iv_cover)
        ImageView ivCover;
        @BindView(R.id.tv_likecount)
        TextView tvLikeCount;

        public WorkViewHolder(View itemView) {
            super(itemView);
        }
    }

}
