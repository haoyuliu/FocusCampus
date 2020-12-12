package com.sam.rentalcar.http.net;

import com.google.android.exoplayer2.util.Log;
import com.sam.rentalcar.common.MyApplication;
import com.sam.rentalcar.utils.SPUtils;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * author:sam
 * time:2020/09/14
 * desc:
 * version:1.0
 */
public class RequestLoggerInterceptor implements Interceptor {
    private static final String TAG = "RequestLoggerInterceptor";

    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {
        Request request = chain.request();
        String token = SPUtils.getInstance(MyApplication.getInstance()).getString("token");
        Log.d(TAG, "本地token ---->: " + token);
        Log.d(TAG, "url ---->: " + request.url());
        Log.d(TAG, "method ---->: " + request.method());
        Log.d(TAG, "headers ---->: " + request.headers());
        Log.d(TAG, "body ---->: " + request.body());
        Log.d(TAG, "code ---->: " + request.newBuilder().addHeader("token", token));
      /*  if (StringUtils.isEmpty(token)) {
            Request originalRequest = chain.request();
            return chain.proceed(originalRequest);
        } else {
            Request originalRequest = chain.request();
            Request updateRequest = originalRequest.newBuilder().header("token", token).build();
            Log.d(TAG, "Header ---->" + originalRequest.headers());
            return chain.proceed(updateRequest);
        }*/
        return chain.proceed(request);
    }
}
