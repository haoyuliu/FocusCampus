package com.sam.rentalcar.videoplayer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.sam.rentalcar.R;
import com.sam.rentalcar.bean.UserProductionOrLoveBean;

import java.util.List;

/**
 * author:sam
 * time:2020/10/24
 * desc: 个人喜欢页面和个人作品页面的适配器
 * version:1.0
 */
public class PersonLoveGridVideoAdapter extends RecyclerView.Adapter<PersonLoveGridVideoAdapter.TikTokListViewHolder> {

    public List<UserProductionOrLoveBean.DataBean> data;

    private int mId;

    public PersonLoveGridVideoAdapter(List<UserProductionOrLoveBean.DataBean> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public TikTokListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tiktok_list, parent, false);
        return new TikTokListViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TikTokListViewHolder holder, int position) {
        UserProductionOrLoveBean.DataBean item = data.get(position);
        holder.mTitle.setText(item.getVideoTitle());
        Glide.with(holder.mThumb.getContext())
                .load(item.getVideoImageUrl())
                .into(holder.mThumb);

        holder.mPosition = position;
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    public void setImpl(int id) {
        mId = id;
    }

    public class TikTokListViewHolder extends RecyclerView.ViewHolder {

        public ImageView mThumb;
        public TextView mTitle;

        public int mPosition;

        public TikTokListViewHolder(@NonNull View itemView) {
            super(itemView);
            mThumb = itemView.findViewById(R.id.iv_thumb);
            mTitle = itemView.findViewById(R.id.tv_title);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TikTokActivity.start(itemView.getContext(), mPosition,data);
                }
            });
        }
    }
}
