package com.sam.rental.http.net;


import com.sam.rental.bean.VerficationCodeBean;
import com.sam.rental.bean.VideoListBean;
import com.sam.rental.http.response.LoginBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * 网络请求的接口
 */
public interface RetrofitApi {
    /**
     * 获取验证码
     */
    @GET(NetApiConstants.VERFICATION_CODE)
    Call<VerficationCodeBean> loadVerficationCode();

    /**
     * 根据验证码登录
     *
     * @param url
     * @return
     */
    @GET
    Call<LoginBean> loadLogin(@Url String url);

    /**
     * 获取首页视频列表
     *
     * @return
     */
    @GET(NetApiConstants.GET_VIDEO_LIST)
    Call<VideoListBean> loadHomeVideoListData(@Query("pageIndex") String pageIndex, @Query("pageSize") String pageSize);

}
