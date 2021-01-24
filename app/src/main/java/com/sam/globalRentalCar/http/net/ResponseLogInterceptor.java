package com.sam.globalRentalCar.http.net;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import com.google.android.exoplayer2.util.Log;
import com.google.gson.Gson;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.sam.globalRentalCar.common.MyApplication;
import com.sam.globalRentalCar.helper.ActivityStackManager;
import com.sam.globalRentalCar.ui.activity.LoginActivity;
import com.sam.globalRentalCar.ui.activity.SettingActivity;
import com.sam.globalRentalCar.utils.RxBus;
import com.sam.globalRentalCar.utils.SPUtils;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * author:sam
 * time:2020/09/14
 * desc:
 * version:1.0
 */
public class ResponseLogInterceptor implements Interceptor {
    private static final String TAG = "ResponseLogInterceptor";

    private Handler mHander = new Handler(Looper.myLooper());

    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {
        Response response = chain.proceed(chain.request());

        Log.d(TAG, "code ---->:" + response.code());
        Log.d(TAG, "message ---->:" + response.message());
        Log.d(TAG, "protocol ---->:" + response.protocol());

        if (response.body() != null && response.body().contentType() != null) {
            MediaType mediaType = response.body().contentType();
            String string = response.body().string();
            Log.d(TAG, "mediaType  ---->:" + mediaType.toString());
            Log.d(TAG, "response ---->:" + string);
            Gson gson = new Gson();
            BaseResponse baseResponse = gson.fromJson(string, BaseResponse.class);
            String code = baseResponse.getCode();
            if (code.equals("401")) {
                Log.d(TAG, "response ---->:" + code);
                Log.d(TAG, "response ---->:发送Token失效的消息");
                loginOut();
            }
            ResponseBody responseBody = ResponseBody.create(mediaType, string);
            return response.newBuilder().body(responseBody).build();
        } else {
            return response;
        }
    }

    private void loginOut() {
        // 退出环信
        EMClient.getInstance().logout(true, new EMCallBack() {
            @Override
            public void onSuccess() {
                mHander.post(new Runnable() {
                    @Override
                    public void run() {
                        //主线程
                        SPUtils.getInstance(MyApplication.getInstance().getApplicationContext()).clear();
                        Intent intent = new Intent(MyApplication.getInstance().getApplicationContext(), LoginActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        MyApplication.getContext().startActivity(intent);
                        // 进行内存优化，销毁除登录页之外的所有界面
                        ActivityStackManager.getInstance().finishAllActivities(LoginActivity.class);
                        Toast.makeText(MyApplication.getInstance().getApplicationContext(), "请重新登录！", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onError(int i, String s) {
                mHander.post(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MyApplication.getInstance().getApplicationContext(), "退出失败！", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onProgress(int i, String s) {

            }
        });

    }


}
