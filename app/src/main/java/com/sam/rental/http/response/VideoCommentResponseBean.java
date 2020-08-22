package com.sam.rental.http.response;

/**
 * author:sam
 * time:2020/08/22
 * desc: 请求返回数据
 * version:1.0
 */
public class VideoCommentResponseBean {

    /**
     * success : true
     * data : {"userId":373603655418511360,"videoId":2,"content":"哈哈哈哈","userImg":"https://outin-8919d46ecbe211ea89ff00163e024c6a.oss-cn-shanghai.aliyuncs.com/image/default/AE78AF0B5C4943A1BE3A5FAD09DE6E82-6-2.png","createTime":null,"id":1,"blike":false}
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
         * userId : 373603655418511360
         * videoId : 2
         * content : 哈哈哈哈
         * userImg : https://outin-8919d46ecbe211ea89ff00163e024c6a.oss-cn-shanghai.aliyuncs.com/image/default/AE78AF0B5C4943A1BE3A5FAD09DE6E82-6-2.png
         * createTime : null
         * id : 1
         * blike : false
         */

        private long userId;
        private int videoId;
        private String content;
        private String userImg;
        private Object createTime;
        private int id;
        private boolean blike;

        public long getUserId() {
            return userId;
        }

        public void setUserId(long userId) {
            this.userId = userId;
        }

        public int getVideoId() {
            return videoId;
        }

        public void setVideoId(int videoId) {
            this.videoId = videoId;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getUserImg() {
            return userImg;
        }

        public void setUserImg(String userImg) {
            this.userImg = userImg;
        }

        public Object getCreateTime() {
            return createTime;
        }

        public void setCreateTime(Object createTime) {
            this.createTime = createTime;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public boolean isBlike() {
            return blike;
        }

        public void setBlike(boolean blike) {
            this.blike = blike;
        }
    }
}
