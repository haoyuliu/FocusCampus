package com.sam.rental.ui.activity;

import android.view.View;
import android.widget.Button;

import com.sam.rental.R;
import com.sam.rental.common.MyActivity;

import butterknife.BindView;

/**
 * 订单确认界面
 */
public class OrderConfirmationActivity extends MyActivity {

    @BindView(R.id.confirm_order)
    Button mButtonConfirmOrder;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_order_confirmation;
    }

    @Override
    protected void initView() {
        mButtonConfirmOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(OrderPayActivity.class);
            }
        });
    }

    @Override
    protected void initData() {

    }
}
