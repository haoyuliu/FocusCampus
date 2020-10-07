package com.sam.rentalcar.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.sam.rentalcar.R;
import com.sam.rentalcar.common.MyActivity;

/**
 * 上传证件页面
 */
public class UpLoadCertificatesActivity extends MyActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_up_load_certificates;
    }

    @Override
    protected void initView() {
        findViewById(R.id.submit_verify).setOnClickListener(new View.OnClickListener() {
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
