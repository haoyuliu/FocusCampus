package com.sam.rentalcar.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.google.android.exoplayer2.extractor.VorbisUtil;
import com.sam.rentalcar.R;
import com.sam.rentalcar.http.response.GetCarListResponseBean;
import com.sam.rentalcar.ui.activity.ChoiceCarActivity;
import com.sam.rentalcar.ui.activity.OrderConfirmationActivity;
import com.sam.rentalcar.ui.adapter.BaseRvAdapter;
import com.sam.rentalcar.ui.adapter.BaseRvViewHolder;

import java.util.List;

import butterknife.BindView;

/**
 * 获取租车列表适配器
 * description
 */
public class GetCarListAdapter extends BaseRvAdapter<GetCarListResponseBean.DataBean, GetCarListAdapter.FansViewHolder> {


    private ChoiceCarOnclickListener mChoiceCarOnclickListener;


    public GetCarListAdapter(ChoiceCarActivity context, List<GetCarListResponseBean.DataBean> data) {
        super(context, data);
    }

    @Override
    protected void onBindData(FansViewHolder holder, GetCarListResponseBean.DataBean userBean, int position) {
        Glide.with(holder.itemView.getContext()).load(userBean.getCarPicture()).into(holder.ivCar);
        holder.tvCarName.setText(userBean.getCarName());
        holder.tvCarPrice.setText(userBean.getPrice() + "");
        holder.tvCarType.setText(userBean.getVolume() + " " + userBean.getTransmission() + " | " + userBean.getBodyType() + userBean.getSeat());
        holder.mButton.setOnClickListener(v -> {
            //订单确认
            mChoiceCarOnclickListener.onChoiceClick();
        });
    }

    @NonNull
    @Override
    public FansViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_choice_car, parent, false);
        return new FansViewHolder(view);
    }

    public class FansViewHolder extends BaseRvViewHolder {
        @BindView(R.id.iv_car)
        ImageView ivCar;
        @BindView(R.id.car_name)
        TextView tvCarName;
        @BindView(R.id.car_type)
        TextView tvCarType;
        @BindView(R.id.car_price)
        TextView tvCarPrice;
        @BindView(R.id.choice_car)
        TextView mButton;


        public FansViewHolder(View itemView) {
            super(itemView);
        }
    }


    public void setChoiceCarOnclickListener(ChoiceCarOnclickListener choiceCarOnclickListener) {
        this.mChoiceCarOnclickListener = choiceCarOnclickListener;
    }

    public interface ChoiceCarOnclickListener {
        void onChoiceClick();
    }
}
