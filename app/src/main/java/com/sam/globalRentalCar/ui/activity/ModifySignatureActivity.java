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
 * 修改签名
 */
public class ModifySignatureActivity extends MyActivity {

    @BindView(R.id.modify_signature)
    EditText mEditTextSinnature;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_modify_signature;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    public void onRightClick(View v) {
        String trim = mEditTextSinnature.getText().toString().trim();
        ModifyMessageRequestBean modifyMessageRequestBean = new ModifyMessageRequestBean();
        modifyMessageRequestBean.setUserDesc(trim);
        modifyMessageRequestBean.setUserId(SPUtils.getInstance(ModifySignatureActivity.this).getString("UserId"));
        modifyUserData(modifyMessageRequestBean);
    }

    private void modifyUserData(ModifyMessageRequestBean modifyMessageRequestBean) {

        RetrofitClient.getRetrofitService().modifyPersonalMessageParams(SPUtils.getInstance(ModifySignatureActivity.this).getString("token"), modifyMessageRequestBean)
                .enqueue(new Callback<ModifyMessageResponseBean>() {
                    @Override
                    public void onResponse(Call<ModifyMessageResponseBean> call, Response<ModifyMessageResponseBean> response) {
                        if (response.code() == HttpURLConnection.HTTP_OK) {
                            toast("修改成功");
                            SPUtils.getInstance(ModifySignatureActivity.this).put("userDesc", modifyMessageRequestBean.getUserDesc());
                        }

                    }

                    @Override
                    public void onFailure(Call<ModifyMessageResponseBean> call, Throwable t) {
                        toast("修改失败");
                    }
                });

    }
}
