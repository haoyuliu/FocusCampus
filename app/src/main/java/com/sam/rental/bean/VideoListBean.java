package com.sam.rental.bean;

import java.util.List;

/**
 * author:sam
 * time:2020/08/01
 * desc:
 * version:1.0
 */
public class VideoListBean {

    /**
     * success : true
     * data : [{"userId":368415507088539648,"videoId":null,"videoTitle":"视频测试1596288147","videoDescription":"0.0","videoUrl":"https://outin-8919d46ecbe211ea89ff00163e024c6a.oss-cn-shanghai.aliyuncs.com/sv/64e15ac-173aa30228f/64e15ac-173aa30228f.mp4","videoImageUrl":null,"videoLikeCount":null,"videoCommitCount":null,"videoType":null,"status":1,"createTime":"2020-08-01 21:51:11","id":2},{"userId":368415507088539648,"videoId":null,"videoTitle":"视频测试1596288180","videoDescription":"0.0","videoUrl":"https://outin-8919d46ecbe211ea89ff00163e024c6a.oss-cn-shanghai.aliyuncs.com/sv/2c33ba6c-173aa30a24d/2c33ba6c-173aa30a24d.mp4","videoImageUrl":null,"videoLikeCount":null,"videoCommitCount":null,"videoType":null,"status":1,"createTime":"2020-08-01 21:51:11","id":3},{"userId":368415507088539648,"videoId":null,"videoTitle":"视频测试1596288213","videoDescription":"0.0","videoUrl":"https://outin-8919d46ecbe211ea89ff00163e024c6a.oss-cn-shanghai.aliyuncs.com/sv/34861c1d-173aa312462/34861c1d-173aa312462.mp4","videoImageUrl":null,"videoLikeCount":null,"videoCommitCount":null,"videoType":null,"status":1,"createTime":"2020-08-01 21:51:11","id":4},{"userId":368415507088539648,"videoId":null,"videoTitle":"视频测试1596288221","videoDescription":"0.0","videoUrl":"https://outin-8919d46ecbe211ea89ff00163e024c6a.oss-cn-shanghai.aliyuncs.com/sv/3e60539e-173aa31414d/3e60539e-173aa31414d.mp4","videoImageUrl":null,"videoLikeCount":null,"videoCommitCount":null,"videoType":null,"status":1,"createTime":"2020-08-01 21:51:11","id":5},{"userId":368415507088539648,"videoId":null,"videoTitle":"视频测试1596288271","videoDescription":"0.0","videoUrl":"https://outin-8919d46ecbe211ea89ff00163e024c6a.oss-cn-shanghai.aliyuncs.com/sv/2f3eb9a7-173aa3206b4/2f3eb9a7-173aa3206b4.mp4","videoImageUrl":null,"videoLikeCount":null,"videoCommitCount":null,"videoType":null,"status":1,"createTime":"2020-08-01 21:51:11","id":6},{"userId":368415507088539648,"videoId":null,"videoTitle":"视频测试1596288294","videoDescription":"0.0","videoUrl":"https://outin-8919d46ecbe211ea89ff00163e024c6a.oss-cn-shanghai.aliyuncs.com/sv/262be57a-173aa32646b/262be57a-173aa32646b.mp4","videoImageUrl":null,"videoLikeCount":null,"videoCommitCount":null,"videoType":null,"status":1,"createTime":"2020-08-01 21:51:11","id":7},{"userId":368415507088539648,"videoId":null,"videoTitle":"视频测试1596288317","videoDescription":"0.0","videoUrl":"https://outin-8919d46ecbe211ea89ff00163e024c6a.oss-cn-shanghai.aliyuncs.com/sv/267a8b6a-173aa32b905/267a8b6a-173aa32b905.mp4","videoImageUrl":null,"videoLikeCount":null,"videoCommitCount":null,"videoType":null,"status":1,"createTime":"2020-08-01 21:51:11","id":8}]
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
         * userId : 368415507088539648
         * videoId : null
         * videoTitle : 视频测试1596288147
         * videoDescription : 0.0
         * videoUrl : https://outin-8919d46ecbe211ea89ff00163e024c6a.oss-cn-shanghai.aliyuncs.com/sv/64e15ac-173aa30228f/64e15ac-173aa30228f.mp4
         * videoImageUrl : null
         * videoLikeCount : null
         * videoCommitCount : null
         * videoType : null
         * status : 1
         * createTime : 2020-08-01 21:51:11
         * id : 2
         */

        private long userId;
        private Object videoId;
        private String videoTitle;
        private String videoDescription;
        private String videoUrl;
        private Object videoImageUrl;
        private Object videoLikeCount;
        private Object videoCommitCount;
        private Object videoType;
        private int status;
        private String createTime;
        private int id;

        public long getUserId() {
            return userId;
        }

        public void setUserId(long userId) {
            this.userId = userId;
        }

        public Object getVideoId() {
            return videoId;
        }

        public void setVideoId(Object videoId) {
            this.videoId = videoId;
        }

        public String getVideoTitle() {
            return videoTitle;
        }

        public void setVideoTitle(String videoTitle) {
            this.videoTitle = videoTitle;
        }

        public String getVideoDescription() {
            return videoDescription;
        }

        public void setVideoDescription(String videoDescription) {
            this.videoDescription = videoDescription;
        }

        public String getVideoUrl() {
            return videoUrl;
        }

        public void setVideoUrl(String videoUrl) {
            this.videoUrl = videoUrl;
        }

        public Object getVideoImageUrl() {
            return videoImageUrl;
        }

        public void setVideoImageUrl(Object videoImageUrl) {
            this.videoImageUrl = videoImageUrl;
        }

        public Object getVideoLikeCount() {
            return videoLikeCount;
        }

        public void setVideoLikeCount(Object videoLikeCount) {
            this.videoLikeCount = videoLikeCount;
        }

        public Object getVideoCommitCount() {
            return videoCommitCount;
        }

        public void setVideoCommitCount(Object videoCommitCount) {
            this.videoCommitCount = videoCommitCount;
        }

        public Object getVideoType() {
            return videoType;
        }

        public void setVideoType(Object videoType) {
            this.videoType = videoType;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}
