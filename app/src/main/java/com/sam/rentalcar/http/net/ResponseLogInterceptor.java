package com.sam.rentalcar.http.net;

import android.content.Context;
import android.widget.Toast;

import com.google.android.exoplayer2.util.Log;
import com.google.gson.Gson;
import com.sam.rentalcar.utils.RxBus;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
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
                RxBus.getDefault().post(BaseResponse.class);
            }
            ResponseBody responseBody = ResponseBody.create(mediaType, string);
            return response.newBuilder().body(responseBody).build();
        } else {
            return response;
        }
    }
}
