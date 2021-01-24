package com.sam.globalRentalCar.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.easeui.EaseConstant;
import com.sam.globalRentalCar.R;
import com.sam.globalRentalCar.chat.ChatActivity;
import com.sam.globalRentalCar.common.BaseRvAdapter;
import com.sam.globalRentalCar.common.BaseRvViewHolder;
import com.sam.globalRentalCar.http.response.FindUserListBean;

import java.util.List;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 查找好友的适配器
 */
public class FindUserAdapter extends BaseRvAdapter<FindUserListBean.DataBean.RecordsBean, FindUserAdapter.FansViewHolder> {


    public FindUserAdapter(Context context, List<FindUserListBean.DataBean.RecordsBean> data) {
        super(context, data);
    }

    @Override
    protected void onBindData(FansViewHolder holder, FindUserListBean.DataBean.RecordsBean userBean, int position) {
        //holder.ivHead.setImageResource(userBean.getHeadImg());
        Glide.with(holder.itemView.getContext()).load(userBean.getHeadImg()).into(holder.ivFindHead);
        holder.tvFindNickname.setText(userBean.getNickName() + "");
        holder.tvFindDesc.setText(userBean.getUserDesc() + "");
        //holder.tvFocus.setText(userBean.getFollowed() ? "已关注" : "关注");

        holder.itemView.setOnClickListener(v -> {
            Log.d("FindUserAdapter", "回话");
            Intent intent = new Intent(holder.itemView.getContext(), ChatActivity.class);
            // 设置参数，跳转到回话列表
            // 需要传递环信ID
            intent.putExtra(EaseConstant.EXTRA_USER_ID, userBean.getHxuid() + "");
            intent.putExtra(EaseConstant.EXTRA_CHAT_TYPE, EMMessage.ChatType.Chat);
            holder.itemView.getContext().startActivity(intent);
        });
    }

    @NonNull
    @Override
    public FansViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_find_user, parent, false);
        return new FansViewHolder(view);
    }

    public class FansViewHolder extends BaseRvViewHolder {
        @BindView(R.id.iv_find_head)
        CircleImageView ivFindHead;
        @BindView(R.id.tv_find_nickname)
        TextView tvFindNickname;
        @BindView(R.id.tv_nick_desc)
        TextView tvFindDesc;

        public FansViewHolder(View itemView) {
            super(itemView);
        }
    }
}
