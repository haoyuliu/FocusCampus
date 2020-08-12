package com.sam.rental.http.response;

/**
 *    author : Android 轮子哥
 *    github : https://github.com/getActivity/AndroidProject
 *    time   : 2019/12/07
 *    desc   : 登录返回
 */
public class LoginBean {


    /**
     * success : true
     * data : {"userId":371321119245991940,"realName":"","nickName":"111","headImg":"https://worldcar.oss-cn-beijing.aliyuncs.com/default.jpg?Expires=1594610492&OSSAccessKeyId=TMP.3KdwRbfSizxtnBKhKc7m3EL1HE28hdSWL4SWb6ZDk95jVQ6Wpd4boTiVNUYakjxWAkqaUxjnwctotoeUKjk6um1wQkX1QP&Signature=rgMUTJ5ySusgvQ%2BiHUbn%2FgYKxRE%3D&nsukey=Xl%2Fux%2BRF%2FbeKNylUotdObHWHd9suyR7oZNGAznnzgYtC9ZbB47sBt%2FRNpridMuqNfAGqrpQaAZnMPk3HoAsTGKDfENivzX%2B7nATzsEyXuEE0DJR7si0JvZ7XgZLwU1M%2F1QckfxvoL7FD5KWYF3gSOuHKRezXv2%2BK6COv8ivmBIk%3D","phone":"111","token":"oW2zg/5qLfi9NB9ZQsalRQ==","hxuid":"hqyc111","hxpwd":"698d51a19d8a121ce581499d7b701668"}
     * msg : success
     * code : 200
     * traceId : null
     */

    private boolean success;
    private DataBean data;
    private String msg;
    private String code;
    private Object traceId;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Object getTraceId() {
        return traceId;
    }

    public void setTraceId(Object traceId) {
        this.traceId = traceId;
    }

    public static class DataBean {
        /**
         * userId : 371321119245991940
         * realName :
         * nickName : 111
         * headImg : https://worldcar.oss-cn-beijing.aliyuncs.com/default.jpg?Expires=1594610492&OSSAccessKeyId=TMP.3KdwRbfSizxtnBKhKc7m3EL1HE28hdSWL4SWb6ZDk95jVQ6Wpd4boTiVNUYakjxWAkqaUxjnwctotoeUKjk6um1wQkX1QP&Signature=rgMUTJ5ySusgvQ%2BiHUbn%2FgYKxRE%3D&nsukey=Xl%2Fux%2BRF%2FbeKNylUotdObHWHd9suyR7oZNGAznnzgYtC9ZbB47sBt%2FRNpridMuqNfAGqrpQaAZnMPk3HoAsTGKDfENivzX%2B7nATzsEyXuEE0DJR7si0JvZ7XgZLwU1M%2F1QckfxvoL7FD5KWYF3gSOuHKRezXv2%2BK6COv8ivmBIk%3D
         * phone : 111
         * token : oW2zg/5qLfi9NB9ZQsalRQ==
         * hxuid : hqyc111
         * hxpwd : 698d51a19d8a121ce581499d7b701668
         */

        private long userId;
        private String realName;
        private String nickName;
        private String headImg;
        private String phone;
        private String token;
        private String hxuid;
        private String hxpwd;

        public long getUserId() {
            return userId;
        }

        public void setUserId(long userId) {
            this.userId = userId;
        }

        public String getRealName() {
            return realName;
        }

        public void setRealName(String realName) {
            this.realName = realName;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getHeadImg() {
            return headImg;
        }

        public void setHeadImg(String headImg) {
            this.headImg = headImg;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getHxuid() {
            return hxuid;
        }

        public void setHxuid(String hxuid) {
            this.hxuid = hxuid;
        }

        public String getHxpwd() {
            return hxpwd;
        }

        public void setHxpwd(String hxpwd) {
            this.hxpwd = hxpwd;
        }
    }
}