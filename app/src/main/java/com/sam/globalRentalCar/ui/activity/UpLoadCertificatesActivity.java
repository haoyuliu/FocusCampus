package com.sam.globalRentalCar.ui.activity;

import android.view.View;
import android.widget.ImageView;

import com.sam.globalRentalCar.R;
import com.sam.globalRentalCar.common.MyActivity;
import com.sam.globalRentalCar.http.glide.GlideApp;

import java.util.List;

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

        findViewById(R.id.choice_id_front).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //身份证
                PhotoActivity.start(getActivity(), new PhotoActivity.OnPhotoSelectListener() {
                    @Override
                    public void onSelected(List<String> data) {

                           String mAvatarUrl = data.get(0);
                            GlideApp.with(getActivity())
                                    .load(mAvatarUrl)
                                    .into((ImageView) findViewById(R.id.choice_id_front));

                    }

                    @Override
                    public void onCancel() {
                    }
                });
            }
        });
        findViewById(R.id.choice_id_reverse).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //身份证反面
                PhotoActivity.start(getActivity(), new PhotoActivity.OnPhotoSelectListener() {
                    @Override
                    public void onSelected(List<String> data) {

                        String mAvatarUrl = data.get(0);
                        GlideApp.with(getActivity())
                                .load(mAvatarUrl)
                                .into((ImageView) findViewById(R.id.choice_id_reverse));

                    }

                    @Override
                    public void onCancel() {
                    }
                });
            }
        });
        findViewById(R.id.choice_id_car_driver).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //驾驶证
                PhotoActivity.start(getActivity(), new PhotoActivity.OnPhotoSelectListener() {
                    @Override
                    public void onSelected(List<String> data) {

                        String mAvatarUrl = data.get(0);
                        GlideApp.with(getActivity())
                                .load(mAvatarUrl)
                                .into((ImageView) findViewById(R.id.choice_id_car_driver));

                    }

                    @Override
                    public void onCancel() {
                    }
                });
            }
        });

    }

    @Override
    protected void initData() {

    }
}
