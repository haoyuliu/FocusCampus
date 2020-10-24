package com.sam.rentalcar.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.hjq.bar.TitleBar;
import com.sam.rentalcar.R;
import com.sam.rentalcar.bean.UserProductionOrLoveBean;
import com.sam.rentalcar.bean.VideoBean;
import com.sam.rentalcar.ui.activity.PlayListActivity;
import com.sam.rentalcar.ui.adapter.BaseRvAdapter;
import com.sam.rentalcar.ui.adapter.BaseRvViewHolder;
import com.sam.rentalcar.videoplayer.TikTokActivity;
import com.sam.rentalcar.videoplayer.TiktokBean;
import com.sam.rentalcar.widget.IconFontTextView;

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

        holder.mPosition = position;
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
        public int mPosition;

        public GridVideoViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(v -> {
                TikTokActivity.start(itemView.getContext(), mPosition);
            });
        }
    }
}
