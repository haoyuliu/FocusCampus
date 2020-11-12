package com.sam.rentalcar.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;

import com.sam.rentalcar.R;
import com.sam.rentalcar.bean.ShareBean;
import com.sam.rentalcar.ui.adapter.BaseRvAdapter;
import com.sam.rentalcar.ui.adapter.BaseRvViewHolder;
import com.sam.widget.view.ScaleImageView;

import java.util.List;

import butterknife.BindView;

/**
 * description
 */
public class ShareAdapter extends BaseRvAdapter<ShareBean, ShareAdapter.ShareViewHolder> {

    public ShareAdapter(Context context, List<ShareBean> datas) {
        super(context, datas);
    }

    @Override
    protected void onBindData(ShareViewHolder holder, ShareBean shareBean, int position) {
       // holder.tvIcon.setText(shareBean.getIconRes());
        holder.tvText.setText(shareBean.getText());
        holder.viewBg.setBackgroundResource(shareBean.getBgRes());
    }

    @NonNull
    @Override
    public ShareViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_share, parent, false);
        return new ShareViewHolder(view);
    }

    public class ShareViewHolder extends BaseRvViewHolder {
       // @BindView(R.id.tv_icon)
        //TextView tvIcon;
        @BindView(R.id.tv_share_text)
        AppCompatTextView tvText;
        @BindView(R.id.iv_share_image)
        ScaleImageView viewBg;

        public ShareViewHolder(View itemView) {
            super(itemView);
        }
    }
}
