package com.sam.rental.http.net;


import com.sam.rental.bean.FansBean;
import com.sam.rental.bean.UserProductionOrLoveBean;
import com.sam.rental.bean.VideoListBean;
import com.sam.rental.http.request.LoginRequestBean;
import com.sam.rental.http.response.LoginBean;
import com.sam.rental.http.response.VerficationCodeBean;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * 网络请求的接口
 */
public interface RetrofitApi {
    /**
     * 获取验证码
     */
    @GET(NetApiConstants.VERFICATION_CODE)
    Call<VerficationCodeBean> loadVerficationCode(@Query("phone") String phone);

    /**
     * 根据验证码登录
     *
     * @param loginRequestBean
     * @return
     */
    @POST(NetApiConstants.USER_LOGIN)
    Call<LoginBean> loadLogin(@Body LoginRequestBean loginRequestBean);

    /**
     * 获取首页视频列表
     *
     * @return
     */
    @GET(NetApiConstants.GET_VIDEO_LIST)
    Call<VideoListBean> loadHomeVideoListData(@Query("pageIndex") int pageIndex, @Query("pageSize") int pageSize);

    /**
     * 获取关注列表
     */
    @GET(NetApiConstants.GET_FOCUS)
    Call<FansBean> getFocus(@Query("userId") String userId, @Query("page") String page);

    /**
     * 获取粉丝列表
     */
    @GET(NetApiConstants.GET_FANS)
    Call<FansBean> getFans(@Query("userId") String userId, @Query("page") String page);

    /**
     * 获取个人作品列表
     */
    @GET(NetApiConstants.GET_PERSONAL_PRODUCTION)
    Call<UserProductionOrLoveBean> getPersonalProduction(@Query("userId") String userId, @Query("page") String page);

    /**
     * 获取个人喜欢作品列表
     */
    @GET(NetApiConstants.GET_PERSONAL_LOVE)
    Call<UserProductionOrLoveBean> getPersonalLove(@Query("userId") String userId, @Query("page") String page);

}
