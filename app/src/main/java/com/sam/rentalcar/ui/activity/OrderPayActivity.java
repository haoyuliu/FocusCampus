package com.sam.rentalcar.ui.activity;

import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.alipay.sdk.app.PayTask;
import com.sam.base.BaseDialog;
import com.sam.rentalcar.R;
import com.sam.rentalcar.common.MyActivity;
import com.sam.rentalcar.pay.AliPayResultStatus;
import com.sam.rentalcar.pay.PayResult;
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

    Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what) {
                case 10001:
                    PayResult payResult = new PayResult((String) msg.obj);
                    String rs = payResult.getResultStatus();
                    String r = payResult.getResult();
                    switch (rs) {
                        case AliPayResultStatus.PAY_SUCCESS:
                            Toast.makeText(OrderPayActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                            //通知接口支付成功
                            //getPresenter().alipayVerify(new VerifyBody(InfoUtils.getUserId(), rs, r, result.getExtraParam()));
                            break;
                        case AliPayResultStatus.PAY_PROCESSING:
                        case AliPayResultStatus.PAY_UNKNOWN:
                            //支付可能成功，要接口去查询,可暂时判断为成功或者支付结果未知提示用户
                            Toast.makeText(OrderPayActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                            //getPresenter().alipayVerify(new VerifyBody(InfoUtils.getUserId(), rs, r, result.getExtraParam()));
                            break;
                        default:
                            Toast.makeText(OrderPayActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
                            //通知接口支付失败，取消订单
                            //getPresenter().orderCancel(new CancelBody(result.getExtraParam()));
                    }
                    break;
            }


        }
    };

    @Override
    protected void initData() {
        mButtonPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//这个是我项目接口返回的支付宝订单信息，支付宝订单是不管包名，签名的，
                // 兄弟们可以拿去你项目用，看是否能正常调起支付宝，能调起就代表集成成功了。
                // 注意：“但就不要实际支付了，避免其他人也用时提示已经支付过了”
                final String orderInfo = "alipay_sdk=alipay-sdk-java-4.5.0.ALL&app_id=2017062007529139&biz_content=%7B%22body%22%3A%22%E8%B4%AD%E4%B9%B0%E4%BA%86%E9%87%91%E5%B8%81%E7%9A%84%E8%B4%B9%E7%94%A8%22%2C%22out_trade_no%22%3A%22c41b8bc484414a908096bfa154addfbe%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%2C%22subject%22%3A%22%E9%87%91%E5%B8%81400%E4%B8%AA%22%2C%22timeout_express%22%3A%2230m%22%2C%22total_amount%22%3A%220.03%22%7D&charset=utf-8&format=json&method=alipay.trade.app.pay&notify_url=http%3A%2F%2F118.178.238.32%3A7074%2FpayAsyncNotify%2FgoldAlipay&sign=cLKHO69X4xEXwlOz7IP7c1tiQERlP1S7yk8UAk1g1WDlNchxjj1YnK01KvsYNSx1Vpj%2BT%2F98LkK6areKcNj4qlPBuG0g3l%2BK%2BpH5n1k7Ejj%2BrPw9VdlSXxeUNlw8eBhvf2yqHTpLT5v0GN62KulllXvX7skDB3Q6n8SDls3kJ6ZJVWDJDNWefRVLfwCg13kK5Uzv0tNifEqr4ii%2Bv1e27TA4ceoT0EHHg0pASmhSCVAj8g3QnpJZNXSL5mnh4WVyq6ka37IL1PVTYJwreN2iNFVfRHUPg4yf16qbjVTN7Y%2FuJpx2X95%2Fn7SpIXcaPoyvkdzHM2CEkxsqqE%2Fcs8w5lA%3D%3D&sign_type=RSA2&timestamp=2020-09-20+20%3A35%3A43&version=1.0";

                Runnable payRunnable = new Runnable() {

                    @Override
                    public void run() {
                        PayTask alipay = new PayTask(OrderPayActivity.this);
                        //支付宝demo是用payV2的，用pay简单点
                        //用户在商户app内部点击付款，是否需要一个loading做为在钱包唤起之前的过渡，这个值设置为true
                        String result = alipay.pay(orderInfo, true);
                        Message msg = new Message();
                        msg.what = 10001;
                        msg.obj = result;
                        handler.sendMessage(msg);
                    }
                };
                // 必须异步调用
                Thread payThread = new Thread(payRunnable);
                payThread.start();
            }
        });
    }
}
