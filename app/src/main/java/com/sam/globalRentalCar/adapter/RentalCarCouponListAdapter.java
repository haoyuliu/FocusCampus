package com.sam.globalRentalCar.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.sam.globalRentalCar.R;
import com.sam.globalRentalCar.http.response.GetUserCouponListResponseBean;
import com.sam.globalRentalCar.ui.adapter.BaseRvAdapter;
import com.sam.globalRentalCar.ui.adapter.BaseRvViewHolder;

import java.util.List;

import butterknife.BindView;

/**
 * 优惠券适配器
 */
public class RentalCarCouponListAdapter extends BaseRvAdapter<GetUserCouponListResponseBean.DataBean, RentalCarCouponListAdapter.RentalCarViewHolder> {


    public RentalCarCouponListAdapter(Context context, List<GetUserCouponListResponseBean.DataBean> lowPriceCar) {
        super(context, lowPriceCar);
    }

    @Override
    protected void onBindData(RentalCarViewHolder holder, GetUserCouponListResponseBean.DataBean data, int position) {

        holder.itemTvHomeCarName.setText(data.getName());
        holder.itemTvHomeCarPrice.setText(data.getUsedAmount() + "/天");
    }


    @NonNull
    @Override
    public RentalCarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_rental_car_home, parent, false);
        return new RentalCarViewHolder(view);
    }

    public class RentalCarViewHolder extends BaseRvViewHolder {
        @BindView(R.id.item_rental_cat_image)
        ImageView itemIvHomeCar;
        @BindView(R.id.item_rental_cat_name)
        TextView itemTvHomeCarName;
        @BindView(R.id.item_rental_cat_price)
        TextView itemTvHomeCarPrice;

        public RentalCarViewHolder(View itemView) {
            super(itemView);
        }
    }
}
