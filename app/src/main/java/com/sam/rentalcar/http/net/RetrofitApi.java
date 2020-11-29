package com.sam.rentalcar.http.net;


import com.sam.rentalcar.bean.FansBean;
import com.sam.rentalcar.bean.UserProductionOrLoveBean;
import com.sam.rentalcar.bean.VideoListBean;
import com.sam.rentalcar.http.request.DeleteVideoRequestBean;
import com.sam.rentalcar.http.request.HomeVideoLikeRequestBean;
import com.sam.rentalcar.http.request.LoginRequestBean;
import com.sam.rentalcar.http.request.ModifyMessageRequestBean;
import com.sam.rentalcar.http.request.UpLoadVideoRequestBean;
import com.sam.rentalcar.http.request.VideoCommentRequestBean;
import com.sam.rentalcar.http.request.GetUpLoadImageRequestBean;
import com.sam.rentalcar.http.request.upLoadAfterRequestBean;
import com.sam.rentalcar.http.response.CommentListBean;
import com.sam.rentalcar.http.response.DeleteVideoResponseBean;
import com.sam.rentalcar.http.response.FollowResponseBean;
import com.sam.rentalcar.http.response.GetCarBrandListResponseBean;
import com.sam.rentalcar.http.response.GetCarListResponseBean;
import com.sam.rentalcar.http.response.GetCarTypeListResponseBean;
import com.sam.rentalcar.http.response.GetRentalCarHomeMessageResponseBean;
import com.sam.rentalcar.http.response.GetUserConfirmInfoResponseBean;
import com.sam.rentalcar.http.response.GetUserCouponListResponseBean;
import com.sam.rentalcar.http.response.GetUserHomePagerMessageResponseBean;
import com.sam.rentalcar.http.response.HomeVideoLikeResponseBean;
import com.sam.rentalcar.http.response.LoginBean;
import com.sam.rentalcar.http.response.ModifyMessageResponseBean;
import com.sam.rentalcar.http.response.UpLoadVideoResponseBean;
import com.sam.rentalcar.http.response.VerficationCodeBean;
import com.sam.rentalcar.http.response.VideoCommentResponseBean;
import com.sam.rentalcar.http.response.GetUpLoadImageResponseBean;
import com.sam.rentalcar.http.response.upLoadAfterResponseBean;

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
    Call<VideoListBean> loadHomeVideoListData(@Header("token") String header, @Query("pageIndex") int pageIndex, @Query("pageSize") int pageSize);

    /**
     * 获取首页我的关注的人的视频列表
     *
     * @return
     */
    @GET(NetApiConstants.GET_FOLLOWED_VIDEO_LIST)
    Call<VideoListBean> loadHomeFollowedVideoListData(@Header("token") String header, @Query("page") int page);

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
     * 点赞或取消点赞
     *
     * @param videoCommentRequestBean
     * @return
     */
    @POST(NetApiConstants.POST_VIDEO_LIKE)
    Call<HomeVideoLikeResponseBean> postVideoLike(@Body HomeVideoLikeRequestBean videoCommentRequestBean);

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
    @POST(NetApiConstants.IMAGE_PRE_UPLOAD)
    Call<GetUpLoadImageResponseBean> getUpLoadPictureParams(@Header("token") String token, @Body GetUpLoadImageRequestBean getUpLoadImageRequestBean);

    /**
     * 修改个人资料的接口
     *
     * @return
     */
    @POST(NetApiConstants.MODIFY_MESSAGE)
    Call<ModifyMessageResponseBean> modifyPersonalMessageParams(@Header("token") String token, @Body ModifyMessageRequestBean modifyMessageRequestBean);

    /**
     * 获取指定用户资料的接口
     *
     * @return
     */
    @GET(NetApiConstants.GET_USER_HOME_MESSAGE)
    Call<GetUserHomePagerMessageResponseBean> getPersonalHomeMessageParams(@Query("userId") String userId);

    /**
     * 获取车型列表的接口
     *
     * @return
     */
    @GET(NetApiConstants.GET_CAR_TYPE_LIST)
    Call<GetCarTypeListResponseBean> getCarTypeList();

    /**
     * 获取品牌列表的接口
     *
     * @return
     */
    @GET(NetApiConstants.GET_CAR_BRAND_LIST)
    Call<GetCarBrandListResponseBean> getCarBrandList();

    /**
     * 获取租车主页的接口
     *
     * @return
     */
    @GET(NetApiConstants.GET_RENTAL_CAR_HOME_MESSAGE)
    Call<GetRentalCarHomeMessageResponseBean> getRentalCarHomeMessage(@Query("cityCode") String cityCode);

    /**
     * 获取可用车辆列表的接口
     *
     * @return
     */
    @GET(NetApiConstants.GET_CAR_LIST)
    Call<GetCarListResponseBean> getCarList(@Query("brandIds") String brandIds, @Query("pickUpId") int pickUpId, @Query("startDate") String startDate, @Query("endDate") String endDate, @Query("carType") Integer carType, @Query("brandId") String brandId, @Query("carId") Long carId, @Query("order") Integer order);

    /**
     * 获取用户全部优惠券列表(包括可用非可用)
     *
     * @return
     */
    @GET(NetApiConstants.GET_USER_COUPON_LIST)
    Call<GetUserCouponListResponseBean> getUserCouponList(@Header("Header") String header);

    /**
     * 获取订单确认页信息
     *
     * @return
     */
    @GET(NetApiConstants.GET_USER_ORDER_CONFIRM_INFO)
    Call<GetUserConfirmInfoResponseBean> getUserOrderConfirmInfo(@Header("Header") String header, @Query("carId") int carId, @Query("days") int days);

    /**
     * 用户点击确认订单
     *
     * @return
     */
    @GET(NetApiConstants.GET_USER_CONFIRM_ORDER)
    Call<GetUserCouponListResponseBean> getUserConfirmOrder(@Header("Header") String header);

    /**
     * 获取订单列表
     *
     * @return
     */
    @GET(NetApiConstants.GET_USER_ORDER_MESSAGE)
    Call<GetUserCouponListResponseBean> getUserOrderInfo(@Query("userId") String userId);

    /**
     * 获取唤起支付sdk的信息
     *
     * @return
     */
    @GET(NetApiConstants.GET_USER_PAY_ORDER)
    Call<GetUserCouponListResponseBean> getUserPayOrder(@Query("userId") String userId);

    /**
     * 根据经纬度获取城市id
     *
     * @return
     */
    @GET(NetApiConstants.GET_ADDRESS_BY_LL)
    Call<GetUserCouponListResponseBean> getCityIdByLl(@Query("userId") String userId);

    /**
     * 根据城市编码获取车辆门店列表
     *
     * @return
     */
    @GET(NetApiConstants.GET_PICKUP_POINTLIST_BY_CITY)
    Call<GetUserCouponListResponseBean> getPickUpPointListByCity(@Query("userId") String userId);

    /**
     * 删除视频
     *
     * @return
     */
    @POST(NetApiConstants.POST_DELETE_VIDEO)
    Call<DeleteVideoResponseBean> deleteVideo(@Body DeleteVideoRequestBean deleteVideoRequestBean);

}
