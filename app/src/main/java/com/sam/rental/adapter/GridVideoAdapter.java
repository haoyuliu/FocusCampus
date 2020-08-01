package com.sam.rental.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import com.sam.rental.R;
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
public class GridVideoAdapter extends BaseRvAdapter<VideoBean, GridVideoAdapter.GridVideoViewHolder> {

    public GridVideoAdapter(Context context, List<VideoBean> datas) {
        super(context, datas);
    }

    @Override
    protected void onBindData(GridVideoViewHolder holder, VideoBean videoBean, int position) {
        holder.ivCover.setBackgroundResource(videoBean.getCoverRes());
        holder.tvContent.setText(videoBean.getContent());
        holder.tvDistance.setText(videoBean.getDistance() + " km");
        holder.ivHead.setImageResource(videoBean.getUserBean().getHead());

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
        @BindView(R.id.tv_content)
        TextView tvContent;
        @BindView(R.id.tv_distance)
        IconFontTextView tvDistance;
        @BindView(R.id.iv_head)
        ImageView ivHead;

        public GridVideoViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
