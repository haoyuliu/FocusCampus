package com.sam.rental.http.net;

/**
 * 网络请求
 */
public class NetApiConstants {
    /**
     * baseurl
     */
    static final String BASE_URL = "http://39.106.49.27:28080/";
    /**
     * 获取验证码
     */
    public static final String VERFICATION_CODE = "user/getVerificationCode";
    /**
     * 登录
     */
    public static final String USER_LOGIN = "user/loginByVerifcationCode";
    /**
     * 获取好友列表
     */
    public static final String GET_FRIEND = "user/getFriends";
    /**
     * 获取视频列表
     */
    public static final String GET_VIDEO_LIST = "video/videoList";
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

}
