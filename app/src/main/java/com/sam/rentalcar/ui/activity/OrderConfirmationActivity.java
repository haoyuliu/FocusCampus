package com.sam.rentalcar.ui.activity;

import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.sam.rentalcar.R;
import com.sam.rentalcar.common.MyActivity;

import butterknife.BindView;

/**
 * 订单确认界面
 */
public class OrderConfirmationActivity extends MyActivity {

    @BindView(R.id.confirm_order)
    Button mButtonConfirmOrder;

    @BindView(R.id.order_checkbox)
    CheckBox mCheckBox;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_order_confirmation;
    }

    @Override
    protected void initView() {
        //默认设置不可点击
        mButtonConfirmOrder.setEnabled(false);
        mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mButtonConfirmOrder.setClickable(true);
                    mButtonConfirmOrder.setEnabled(true);
                } else {
                    mButtonConfirmOrder.setClickable(false);
                    mButtonConfirmOrder.setEnabled(false);
                }
            }
        });
        mButtonConfirmOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(IdentityAuthenticationActivity.class);
            }
        });
    }

    @Override
    protected void initData() {

    }
}
