package com.sam.globalRentalCar.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.sam.globalRentalCar.R;
import com.sam.globalRentalCar.bean.FansBean;
import com.sam.globalRentalCar.common.BaseRvAdapter;
import com.sam.globalRentalCar.common.BaseRvViewHolder;

import java.util.List;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * description
 */
public class FocusAdapter extends BaseRvAdapter<FansBean.DataBean, FocusAdapter.FocusViewHolder> {

    public FocusAdapter(Context context, List<FansBean.DataBean> data) {
        super(context, data);
    }

    @Override
    protected void onBindData(FocusViewHolder holder, FansBean.DataBean userBean, int position) {
        //holder.ivHead.setImageResource(userBean.getHeadImg());
        Glide.with(holder.itemView.getContext()).load(userBean.getHeadImg()).into(holder.ivHead);
        holder.tvNickname.setText(userBean.getNickName());
        holder.tvFocus.setText(userBean.getFollowed() == 1 ? "已关注" : "关注");

        holder.tvFocus.setOnClickListener(v -> {
            if (userBean.getFollowed() != 1) {
                holder.tvFocus.setText("已关注");
                holder.tvFocus.setBackgroundResource(R.drawable.shape_round_halfwhite);
            } else {
                holder.tvFocus.setText("关注");
                holder.tvFocus.setBackgroundResource(R.drawable.shape_round_red);
            }
        });
    }

    @NonNull
    @Override
    public FocusViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_focus, parent, false);
        return new FocusViewHolder(view);
    }

    public class FocusViewHolder extends BaseRvViewHolder {
        @BindView(R.id.iv_focus_head)
        CircleImageView ivHead;
        @BindView(R.id.tv_focus_nickname)
        TextView tvNickname;
        @BindView(R.id.tv_focus_focus)
        TextView tvFocus;

        public FocusViewHolder(View itemView) {
            super(itemView);
        }
    }
}
