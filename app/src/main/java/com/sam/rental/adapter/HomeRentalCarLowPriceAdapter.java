package com.sam.rental.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.sam.rental.R;
import com.sam.rental.bean.FansBean;
import com.sam.rental.http.response.GetRentalCarHomeMessageResponseBean;
import com.sam.rental.ui.activity.LowPriceCarActivity;
import com.sam.rental.ui.adapter.BaseRvAdapter;
import com.sam.rental.ui.adapter.BaseRvViewHolder;

import java.util.List;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.Interceptor;

/**
 * description
 * 获取低价车型数据
 */
public class HomeRentalCarLowPriceAdapter extends BaseRvAdapter<GetRentalCarHomeMessageResponseBean.DataBean.LowPriceCarBean, HomeRentalCarLowPriceAdapter.RentalCarViewHolder> implements View.OnClickListener {


    public HomeRentalCarLowPriceAdapter(Context context, List<GetRentalCarHomeMessageResponseBean.DataBean.LowPriceCarBean> lowPriceCar) {
        super(context, lowPriceCar);
    }

    @Override
    protected void onBindData(RentalCarViewHolder holder, GetRentalCarHomeMessageResponseBean.DataBean.LowPriceCarBean data, int position) {
        Glide.with(holder.itemView.getContext()).load(data.getCarPicture()).into(holder.itemIvHomeCar);
        holder.itemTvHomeCarName.setText(data.getCarName());
        holder.itemTvHomeCarPrice.setText(data.getPrice() + "/天");
    }


    @NonNull
    @Override
    public RentalCarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_rental_car_home, parent, false);
        view.setOnClickListener(this);
        return new RentalCarViewHolder(view);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(context, LowPriceCarActivity.class);
        context.startActivity(intent);
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
