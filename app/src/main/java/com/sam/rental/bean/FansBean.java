package com.sam.rental.bean;

import java.util.List;

/**
 * author:sam
 * time:2020/08/09
 * desc:
 * version:1.0
 */
public class FansBean {


    /**
     * success : true
     * data : [{"userId":368499958493609284,"nickName":"17600160000","headImg":"https://worldcar.oss-cn-beijing.aliyuncs.com/default.jpg?Expires=1594610492&OSSAccessKeyId=TMP.3KdwRbfSizxtnBKhKc7m3EL1HE28hdSWL4SWb6ZDk95jVQ6Wpd4boTiVNUYakjxWAkqaUxjnwctotoeUKjk6um1wQkX1QP&Signature=rgMUTJ5ySusgvQ%2BiHUbn%2FgYKxRE%3D&nsukey=Xl%2Fux%2BRF%2FbeKNylUotdObHWHd9suyR7oZNGAznnzgYtC9ZbB47sBt%2FRNpridMuqNfAGqrpQaAZnMPk3HoAsTGKDfENivzX%2B7nATzsEyXuEE0DJR7si0JvZ7XgZLwU1M%2F1QckfxvoL7FD5KWYF3gSOuHKRezXv2%2BK6COv8ivmBIk%3D","phone":"17600160000","userDesc":null,"hxuid":"hqyc17600160000","followed":1}]
     * msg : success
     * code : 200
     * traceId : null
     */

    private boolean success;
    private String msg;
    private String code;
    private Object traceId;
    private List<DataBean> data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * userId : 368499958493609284
         * nickName : 17600160000
         * headImg : https://worldcar.oss-cn-beijing.aliyuncs.com/default.jpg?Expires=1594610492&OSSAccessKeyId=TMP.3KdwRbfSizxtnBKhKc7m3EL1HE28hdSWL4SWb6ZDk95jVQ6Wpd4boTiVNUYakjxWAkqaUxjnwctotoeUKjk6um1wQkX1QP&Signature=rgMUTJ5ySusgvQ%2BiHUbn%2FgYKxRE%3D&nsukey=Xl%2Fux%2BRF%2FbeKNylUotdObHWHd9suyR7oZNGAznnzgYtC9ZbB47sBt%2FRNpridMuqNfAGqrpQaAZnMPk3HoAsTGKDfENivzX%2B7nATzsEyXuEE0DJR7si0JvZ7XgZLwU1M%2F1QckfxvoL7FD5KWYF3gSOuHKRezXv2%2BK6COv8ivmBIk%3D
         * phone : 17600160000
         * userDesc : null
         * hxuid : hqyc17600160000
         * followed : 1
         */

        private long userId;
        private String nickName;
        private String headImg;
        private String phone;
        private Object userDesc;
        private String hxuid;
        private int followed;

        public long getUserId() {
            return userId;
        }

        public void setUserId(long userId) {
            this.userId = userId;
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

        public Object getUserDesc() {
            return userDesc;
        }

        public void setUserDesc(Object userDesc) {
            this.userDesc = userDesc;
        }

        public String getHxuid() {
            return hxuid;
        }

        public void setHxuid(String hxuid) {
            this.hxuid = hxuid;
        }

        public int getFollowed() {
            return followed;
        }

        public void setFollowed(int followed) {
            this.followed = followed;
        }
    }
}
