package com.sam.rental.http.net;


import com.sam.rental.bean.FansBean;
import com.sam.rental.bean.UserProductionOrLoveBean;
import com.sam.rental.bean.VideoListBean;
import com.sam.rental.http.request.LoginRequestBean;
import com.sam.rental.http.request.UpLoadVideoRequestBean;
import com.sam.rental.http.request.VideoCommentRequestBean;
import com.sam.rental.http.request.GetUpLoadImageRequestBean;
import com.sam.rental.http.request.upLoadAfterRequestBean;
import com.sam.rental.http.response.CommentListBean;
import com.sam.rental.http.response.FollowResponseBean;
import com.sam.rental.http.response.LoginBean;
import com.sam.rental.http.response.UpLoadVideoResponseBean;
import com.sam.rental.http.response.VerficationCodeBean;
import com.sam.rental.http.response.VideoCommentResponseBean;
import com.sam.rental.http.response.GetUpLoadImageResponseBean;
import com.sam.rental.http.response.upLoadAfterResponseBean;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
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
     * 关注用户
     */
    @GET(NetApiConstants.FOCUS_USER)
    Call<FollowResponseBean> FocusUser(@Header("token") String header, @Query("userId") String userId, @Query("follow") String follow);

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

    /**
     * 获取个人作品的评论列表
     */
    @GET(NetApiConstants.GET_COMMENT_LIST)
    Call<CommentListBean> getCommentList(@Query("videoId") String videoId, @Query("pageIndex") String pageIndex, @Query("pageSize") String pageSize);

    /**
     * 发表视频评论
     *
     * @param videoCommentRequestBean
     * @return
     */
    @POST(NetApiConstants.POST_VIDEO_COMMON)
    Call<VideoCommentResponseBean> postVideoCommon(@Body VideoCommentRequestBean videoCommentRequestBean);

    /**
     * 获取上传视频的参数信息
     *
     * @return
     */
    @POST(NetApiConstants.PRE_UPLOAD)
    Call<UpLoadVideoResponseBean> getUpLoadVideoParams(@Header("token") String token, @Body UpLoadVideoRequestBean upLoadVideoRequestBean);
    /**
     * 视频上传成功后
     *
     * @return
     */
    @POST(NetApiConstants.AFTER_UPLOAD)
    Call<upLoadAfterResponseBean> UpLoadVideoAfter(@Header("token") String token, @Body upLoadAfterRequestBean upLoadAfterRequestBean);
    /**
     * 获取图片上传所需要的参数
     *
     * @return
     */
    @POST(NetApiConstants.AFTER_UPLOAD)
    Call<GetUpLoadImageResponseBean> getUpLoadPictureParams(@Header("token") String token, @Body GetUpLoadImageRequestBean getUpLoadImageRequestBean);


}
