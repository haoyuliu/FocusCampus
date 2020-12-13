package com.sam.rentalcar.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.sam.rentalcar.R;
import com.sam.rentalcar.http.response.OrderListResponseBean;
import com.sam.rentalcar.ui.activity.OrderDetailActivity;

import java.util.List;

import butterknife.BindView;

/**
 * description 订单相关的适配器
 */
public class OrderAdapter extends BaseRvAdapter<OrderListResponseBean.DataBean, OrderAdapter.OrderViewHolder> {
    private int mStatus;

    public OrderAdapter(Context context, List<OrderListResponseBean.DataBean> data) {
        super(context, data);
    }

    @Override
    protected void onBindData(OrderViewHolder holder, OrderListResponseBean.DataBean userBean, int position) {
        //0表示未支付，1表示进行中，2表示已完成,3表示已取消
        if (mStatus == 0) {
            holder.tvOrderStatus.setText("待付款");
        } else if (mStatus == 1) {
            holder.tvOrderStatus.setText("进行中");
        } else if (mStatus == 2) {
            holder.tvOrderStatus.setText("已完成");
        } else if (mStatus == 3) {
            holder.tvOrderStatus.setText("已取消");
        }
        holder.tvOrderId.setText("订单编号：" + userBean.getId());
        holder.tvOrderCarName.setText("订单车型:" + userBean.getName());
        holder.tvOrderCreateTime.setText("用车时间:" + userBean.getCreateTime());
        holder.tvOrderPayMoney.setText("订单金额:￥" + userBean.getMoney());


        holder.llOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 进入订单详情页面
                Intent orderDetailIntent = new Intent(holder.itemView.getContext(), OrderDetailActivity.class);

                holder.itemView.getContext().startActivity(orderDetailIntent);
            }
        });
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_order, parent, false);
        return new OrderViewHolder(view);
    }

    public void setStatus(int status) {
        this.mStatus = status;
    }

    public class OrderViewHolder extends BaseRvViewHolder {

        @BindView(R.id.order_status)
        TextView tvOrderStatus;
        @BindView(R.id.order_id)
        TextView tvOrderId;
        @BindView(R.id.order_car_name)
        TextView tvOrderCarName;
        @BindView(R.id.order_create_tiem)
        TextView tvOrderCreateTime;
        @BindView(R.id.order_pay_money)
        TextView tvOrderPayMoney;
        @BindView(R.id.ll_order)
        LinearLayout llOrder;

        public OrderViewHolder(View itemView) {
            super(itemView);
        }
    }
}
