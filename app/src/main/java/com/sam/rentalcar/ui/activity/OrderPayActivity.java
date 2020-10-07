package com.sam.rentalcar.ui.activity;

import android.view.View;
import android.widget.Button;

import com.sam.base.BaseDialog;
import com.sam.rentalcar.R;
import com.sam.rentalcar.common.MyActivity;
import com.sam.rentalcar.ui.dialog.PayPasswordDialog;

import butterknife.BindView;

/**
 * 订单支付界面
 */
public class OrderPayActivity extends MyActivity {
    @BindView(R.id.but_pay)
    Button mButtonPay;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_order_pay;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        mButtonPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 支付密码输入对话框
                new PayPasswordDialog.Builder(OrderPayActivity.this)
                        .setTitle(getString(R.string.pay_title))
                        .setSubTitle("订单支付")
                        .setMoney("￥ 4852.00")
                        //.setAutoDismiss(false) // 设置点击按钮后不关闭对话框
                        .setListener(new PayPasswordDialog.OnListener() {

                            @Override
                            public void onCompleted(BaseDialog dialog, String password) {
                                toast(password);
                            }

                            @Override
                            public void onCancel(BaseDialog dialog) {
                                toast("取消支付");
                            }
                        })
                        .show();
            }
        });
    }
}
