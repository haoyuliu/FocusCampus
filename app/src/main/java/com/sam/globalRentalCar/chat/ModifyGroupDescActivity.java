package com.sam.globalRentalCar.chat;

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
 * 修改群组描述信息
 */
public class ModifyGroupDescActivity extends MyActivity {

    @BindView(R.id.modify_group_desc)
    EditText mEditTextGroupDesc;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_modify_group_desc;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    public void onRightClick(View v) {
        String trim = mEditTextGroupDesc.getText().toString().trim();
    }

}
