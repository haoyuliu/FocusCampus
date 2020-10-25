package com.sam.rentalcar.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.sam.rentalcar.R;
import com.sam.rentalcar.bean.FansBean;
import com.sam.rentalcar.bean.VideoBean;
import com.sam.rentalcar.ui.adapter.BaseRvAdapter;
import com.sam.rentalcar.ui.adapter.BaseRvViewHolder;

import java.util.List;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * description
 */
public class FansAdapter extends BaseRvAdapter<FansBean.DataBean, FansAdapter.FansViewHolder> {


    public FansAdapter(Context context, List<FansBean.DataBean> data) {
        super(context, data);
    }

    @Override
    protected void onBindData(FansViewHolder holder, FansBean.DataBean userBean, int position) {
        //holder.ivHead.setImageResource(userBean.getHeadImg());
        Glide.with(holder.itemView.getContext()).load(userBean.getHeadImg()).into(holder.ivHead);
        holder.tvNickname.setText(userBean.getNickName());
        //holder.tvFocus.setText(userBean.getFollowed() ? "已关注" : "关注");
/*
        holder.tvFocus.setOnClickListener(v -> {
            if (!userBean.isFocused()) {
                holder.tvFocus.setText("已关注");
                holder.tvFocus.setBackgroundResource(R.drawable.shape_round_halfwhite);
            } else {
                holder.tvFocus.setText("关注");
                holder.tvFocus.setBackgroundResource(R.drawable.shape_round_red);
            }

            userBean.setFocused(!userBean.isFocused());
        });*/
    }

    @NonNull
    @Override
    public FansViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_fans, parent, false);
        return new FansViewHolder(view);
    }

    public class FansViewHolder extends BaseRvViewHolder {
        @BindView(R.id.iv_head)
        CircleImageView ivHead;
        @BindView(R.id.tv_nickname)
        TextView tvNickname;
        @BindView(R.id.tv_focus)
        TextView tvFocus;

        public FansViewHolder(View itemView) {
            super(itemView);
        }
    }
}