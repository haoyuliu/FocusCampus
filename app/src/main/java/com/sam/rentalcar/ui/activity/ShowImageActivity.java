package com.sam.rentalcar.ui.activity;

import android.widget.ImageView;

import com.sam.base.BaseActivity;
import com.sam.rentalcar.R;

import butterknife.BindView;

/**
 * 个人信息中点头像查看大图页面
 */
public class ShowImageActivity extends BaseActivity {
    @BindView(R.id.iv_head)
    ImageView ivHead;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_show_image;
    }

    @Override
    protected void initView() {
        ivHead.setOnClickListener(v -> finish());

        int headRes = getIntent().getIntExtra("res", 0);
        ivHead.setImageResource(headRes);
    }

    @Override
    protected void initData() {

    }
}
