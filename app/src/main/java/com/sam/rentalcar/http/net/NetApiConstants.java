package com.sam.rentalcar.http.net;

/**
 * 网络请求
 */
public class NetApiConstants {
    /**
     * baseurl
     */
    public static final String BASE_URL = "http://39.106.49.27:28080";
    /**
     * 获取验证码
     */
    public static final String VERFICATION_CODE = "user/getVerificationCode";
    /**
     * 登录
     */
    public static final String USER_LOGIN = "/user/loginByVerifcationCode";
    /**
     * 获取好友列表
     */
    public static final String GET_FRIEND = "user/getFriends";
    /**
     * 获取视频列表
     */
    public static final String GET_VIDEO_LIST = "video/videoList";
    /**
     * 获取我的关注最近更新的视频列表
     */
    public static final String GET_FOLLOWED_VIDEO_LIST = "video/getFollowedVideoList";
    /**
     * 获取视频上传需要的参数
     */
    public static final String PRE_UPLOAD = "video/preUpload";
    /**
     * 保存视频，视频上传oss之后，将videoId 提交
     */
    public static final String AFTER_UPLOAD = "video/afterUpload";
    /**
     * 获取图片上传所需要的参数
     */
    public static final String IMAGE_PRE_UPLOAD = "image/preUpload";
    /**
     * 获取粉丝列表
     */
    public static final String GET_FANS = "user/getFans";
    /**
     * 获取关注列表
     */
    public static final String GET_FOCUS = "user/getFollowUser";
    /**
     * 获取用户作品的列表
     */
    public static final String GET_PERSONAL_PRODUCTION = "video/getMyVideoList";
    /**
     * 获取用户喜欢的作品列表
     */
    public static final String GET_PERSONAL_LOVE = "video/getCollectionVideoList";

    /**
     * 获取评论列表
     */
    public static final String GET_COMMENT_LIST = "video/videoCommonList";
    /**
     * 用户发表评论
     */
    public static final String POST_VIDEO_COMMON = "video/postVideoCommon";
    /**
     * 用户点赞，取消点赞
     */
    public static final String POST_VIDEO_LIKE = "video/postVideoLike";

    /**
     * 关注用户
     */
    public static final String FOCUS_USER = "user/followUser";
    /**
     * 获取图片上传所需要的参数
     */
    public static final String MODIFY_MESSAGE = "user/editUserInfo";
    /**
     * 获取指定用户个人资料
     */
    public static final String GET_USER_HOME_MESSAGE = "user/getUserHomePage";
    /**
     * 获取车型列表
     */
    public static final String GET_CAR_TYPE_LIST = "car/carTypeList";
    /**
     * 获取品牌列表
     */
    public static final String GET_CAR_BRAND_LIST = "car/getCarBrandList";
    /**
     * 获取租车主页信息
     */
    public static final String GET_RENTAL_CAR_HOME_MESSAGE = "car/getCarHomeInfo";
    /**
     * 获取可用车辆列表
     */
    public static final String GET_CAR_LIST = "car/getCarList";
    /**
     * 获取用户全部优惠券列表
     */
    public static final String GET_USER_COUPON_LIST = "coupon/getCouponList";
    /**
     * 获取订单确认页信息
     */
    public static final String GET_USER_ORDER_CONFIRM_INFO = "order/getConfirmInfo";
    /**
     * 获取用户点击确认订单(返回999提示用户进行认证)
     */
    public static final String GET_USER_CONFIRM_ORDER = "order/confirmOrder";
    /**
     * 获取订单列表
     */
    public static final String GET_USER_ORDER_MESSAGE = "order/list";
    /**
     * 获取唤起支付sdk的信息
     */
    public static final String GET_USER_PAY_ORDER = "order/payOrder";
    /**
     * 根据经纬度获取城市id
     */
    public static final String GET_ADDRESS_BY_LL = "pickUpPoint/getAddressByLL";
    /**
     * 根据城市编码获取车辆门店列表
     */
    public static final String GET_PICKUP_POINTLIST_BY_CITY = "pickUpPoint/getPickUpPointListByCity";

}
