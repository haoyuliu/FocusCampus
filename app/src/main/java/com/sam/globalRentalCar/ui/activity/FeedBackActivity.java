package com.sam.globalRentalCar.ui.activity;

import android.view.View;
import android.widget.EditText;

import com.sam.globalRentalCar.R;
import com.sam.globalRentalCar.common.MyActivity;
import com.sam.globalRentalCar.http.net.RetrofitClient;
import com.sam.globalRentalCar.http.request.ModifyMessageRequestBean;
import com.sam.globalRentalCar.http.response.ModifyMessageResponseBean;
import com.sam.globalRentalCar.utils.SPUtils;

import java.net.HttpURLConnection;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 反馈界面
 */
public class FeedBackActivity extends MyActivity {

    @BindView(R.id.modify_feedback)
    EditText mEditTextFeedback;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_feedback;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    public void onRightClick(View v) {
        String trim = mEditTextFeedback.getText().toString().trim();
        if (trim.isEmpty()) {
            toast("请输入反馈内容");
        } else {
            toast("反馈成功");
            finish();
        }
    }
}
