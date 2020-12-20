package com.sam.globalRentalCar.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.sam.globalRentalCar.R;
import com.sam.globalRentalCar.bean.VideoBean;
import com.sam.globalRentalCar.common.BaseRvAdapter;
import com.sam.globalRentalCar.common.BaseRvViewHolder;

import java.util.List;

import butterknife.BindView;

/**
 * description
 */
public class PrivateLetterAdapter extends BaseRvAdapter<VideoBean.UserBean, PrivateLetterAdapter.PrivateLetterViewHolder> {

    public PrivateLetterAdapter(Context context, List<VideoBean.UserBean> datas) {
        super(context, datas);
    }

    @Override
    protected void onBindData(PrivateLetterViewHolder holder, VideoBean.UserBean userBean, int position) {
        holder.ivHead.setImageResource(userBean.getHead());
        holder.tvName.setText(userBean.getNickName());
    }

    @NonNull
    @Override
    public PrivateLetterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_private_letter, parent, false);
        return new PrivateLetterViewHolder(view);
    }

    public class PrivateLetterViewHolder extends BaseRvViewHolder {
        @BindView(R.id.iv_head)
        ImageView ivHead;
        @BindView(R.id.tv_nickname)
        TextView tvName;

        public PrivateLetterViewHolder(View itemView) {
            super(itemView);
        }
    }
}
