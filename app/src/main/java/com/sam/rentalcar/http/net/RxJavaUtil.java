package com.sam.rentalcar.http.net;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * author:sam
 * time:2020/03/24
 * desc: RxJava 工具类，执行线程调度工作
 * version:1.0
 */
public class RxJavaUtil {

    /**
     * 线程调度工作
     *
     * @param observable 被观察者
     * @param <T>        类型
     */
    public static <T> Observable toSubscribe(Observable<T> observable) {
        return observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

}
