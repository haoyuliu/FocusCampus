package com.sam.globalRentalCar.ui.adapter;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.ButterKnife;

/**
 * description Recycler ViewHolder基类
 */
public class BaseRvViewHolder extends RecyclerView.ViewHolder {

    public BaseRvViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
