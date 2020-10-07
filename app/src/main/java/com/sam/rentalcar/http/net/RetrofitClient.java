package com.sam.rentalcar.http.net;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 这个类主要是创建Retorfit的实例
 */
public class RetrofitClient {
    // 单例
    private static com.sam.rentalcar.http.net.RetrofitApi retrofitInstance;

    // 如果需要设置其他信息，可以通过okhttpclient 进行设置
    static {
        // 这个地方添加日志打印的拦截，便于查看请求和响应的数据
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC))
                .addNetworkInterceptor(new com.sam.rentalcar.http.net.RequestLoggerInterceptor())
                .addInterceptor(new com.sam.rentalcar.http.net.ResponseLogInterceptor())
                .build();
        //Builder设计模式
        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                // 接口主路径
                .baseUrl(com.sam.rentalcar.http.net.NetApiConstants.BASE_URL)
                //添加转换解析工厂
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        retrofitInstance = retrofit.create(com.sam.rentalcar.http.net.RetrofitApi.class);
    }

    public static com.sam.rentalcar.http.net.RetrofitApi getRetrofitService() {
        return retrofitInstance;
    }
}
