package com.sam.rental.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.sam.rental.R;
import com.sam.rental.bean.UserProductionOrLoveBean;
import com.sam.rental.bean.VideoBean;
import com.sam.rental.ui.activity.PlayListActivity;
import com.sam.rental.ui.adapter.BaseRvAdapter;
import com.sam.rental.ui.adapter.BaseRvViewHolder;
import com.sam.rental.widget.IconFontTextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * description
 */
public class GridVideoAdapter extends BaseRvAdapter<UserProductionOrLoveBean.DataBean, GridVideoAdapter.GridVideoViewHolder> {

    public GridVideoAdapter(Context context, List<UserProductionOrLoveBean.DataBean> data) {
        super(context, data);
    }

    @Override
    protected void onBindData(GridVideoViewHolder holder, UserProductionOrLoveBean.DataBean videoBean, int position) {
        Glide.with(holder.itemView.getContext()).load(videoBean.getVideoImageUrl()).into(holder.ivCover);

        holder.itemView.setOnClickListener(v -> {
            PlayListActivity.initPos = position;
            context.startActivity(new Intent(context, PlayListActivity.class));
        });
    }

    @NonNull
    @Override
    public GridVideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_gridvideo, parent, false);
        return new GridVideoViewHolder(view);
    }

    public class GridVideoViewHolder extends BaseRvViewHolder {
        @BindView(R.id.iv_cover)
        ImageView ivCover;


        public GridVideoViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
