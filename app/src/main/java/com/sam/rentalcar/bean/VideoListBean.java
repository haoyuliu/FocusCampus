package com.sam.rentalcar.bean;

import java.io.Serializable;
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
     * data : [{"userId":368415507088539650,"nickName":"15065157316","headImg":"https://worldcar.oss-cn-beijing.aliyuncs.com/default.jpg?Expires=1594610492&OSSAccessKeyId=TMP.3KdwRbfSizxtnBKhKc7m3EL1HE28hdSWL4SWb6ZDk95jVQ6Wpd4boTiVNUYakjxWAkqaUxjnwctotoeUKjk6um1wQkX1QP&Signature=rgMUTJ5ySusgvQ%2BiHUbn%2FgYKxRE%3D&nsukey=Xl%2Fux%2BRF%2FbeKNylUotdObHWHd9suyR7oZNGAznnzgYtC9ZbB47sBt%2FRNpridMuqNfAGqrpQaAZnMPk3HoAsTGKDfENivzX%2B7nATzsEyXuEE0DJR7si0JvZ7XgZLwU1M%2F1QckfxvoL7FD5KWYF3gSOuHKRezXv2%2BK6COv8ivmBIk%3D","hxuid":"hqyc15065157316","videoId":"2","videoTitle":"视频测试1596288294","videoDescription":"0.0","videoUrl":"https://outin-8919d46ecbe211ea89ff00163e024c6a.oss-cn-shanghai.aliyuncs.com/sv/262be57a-173aa32646b/262be57a-173aa32646b.mp4","videoImageUrl":null,"videoLikeCount":6,"videoCommitCount":10,"videoType":null,"status":null,"createTime":null,"id":0,"blike":false,"bfollow":false},{"userId":368415507088539650,"nickName":"15065157316","headImg":"https://worldcar.oss-cn-beijing.aliyuncs.com/default.jpg?Expires=1594610492&OSSAccessKeyId=TMP.3KdwRbfSizxtnBKhKc7m3EL1HE28hdSWL4SWb6ZDk95jVQ6Wpd4boTiVNUYakjxWAkqaUxjnwctotoeUKjk6um1wQkX1QP&Signature=rgMUTJ5ySusgvQ%2BiHUbn%2FgYKxRE%3D&nsukey=Xl%2Fux%2BRF%2FbeKNylUotdObHWHd9suyR7oZNGAznnzgYtC9ZbB47sBt%2FRNpridMuqNfAGqrpQaAZnMPk3HoAsTGKDfENivzX%2B7nATzsEyXuEE0DJR7si0JvZ7XgZLwU1M%2F1QckfxvoL7FD5KWYF3gSOuHKRezXv2%2BK6COv8ivmBIk%3D","hxuid":"hqyc15065157316","videoId":"3","videoTitle":"视频测试1596288294","videoDescription":"0.0","videoUrl":"https://outin-8919d46ecbe211ea89ff00163e024c6a.oss-cn-shanghai.aliyuncs.com/sv/262be57a-173aa32646b/262be57a-173aa32646b.mp4","videoImageUrl":null,"videoLikeCount":6,"videoCommitCount":10,"videoType":null,"status":null,"createTime":null,"id":0,"blike":false,"bfollow":false},{"userId":368415507088539650,"nickName":"15065157316","headImg":"https://worldcar.oss-cn-beijing.aliyuncs.com/default.jpg?Expires=1594610492&OSSAccessKeyId=TMP.3KdwRbfSizxtnBKhKc7m3EL1HE28hdSWL4SWb6ZDk95jVQ6Wpd4boTiVNUYakjxWAkqaUxjnwctotoeUKjk6um1wQkX1QP&Signature=rgMUTJ5ySusgvQ%2BiHUbn%2FgYKxRE%3D&nsukey=Xl%2Fux%2BRF%2FbeKNylUotdObHWHd9suyR7oZNGAznnzgYtC9ZbB47sBt%2FRNpridMuqNfAGqrpQaAZnMPk3HoAsTGKDfENivzX%2B7nATzsEyXuEE0DJR7si0JvZ7XgZLwU1M%2F1QckfxvoL7FD5KWYF3gSOuHKRezXv2%2BK6COv8ivmBIk%3D","hxuid":"hqyc15065157316","videoId":"4","videoTitle":"视频测试1596288294","videoDescription":"0.0","videoUrl":"https://outin-8919d46ecbe211ea89ff00163e024c6a.oss-cn-shanghai.aliyuncs.com/sv/262be57a-173aa32646b/262be57a-173aa32646b.mp4","videoImageUrl":null,"videoLikeCount":6,"videoCommitCount":10,"videoType":null,"status":null,"createTime":null,"id":0,"blike":false,"bfollow":false},{"userId":368415507088539650,"nickName":"15065157316","headImg":"https://worldcar.oss-cn-beijing.aliyuncs.com/default.jpg?Expires=1594610492&OSSAccessKeyId=TMP.3KdwRbfSizxtnBKhKc7m3EL1HE28hdSWL4SWb6ZDk95jVQ6Wpd4boTiVNUYakjxWAkqaUxjnwctotoeUKjk6um1wQkX1QP&Signature=rgMUTJ5ySusgvQ%2BiHUbn%2FgYKxRE%3D&nsukey=Xl%2Fux%2BRF%2FbeKNylUotdObHWHd9suyR7oZNGAznnzgYtC9ZbB47sBt%2FRNpridMuqNfAGqrpQaAZnMPk3HoAsTGKDfENivzX%2B7nATzsEyXuEE0DJR7si0JvZ7XgZLwU1M%2F1QckfxvoL7FD5KWYF3gSOuHKRezXv2%2BK6COv8ivmBIk%3D","hxuid":"hqyc15065157316","videoId":"5","videoTitle":"视频测试1596288294","videoDescription":"0.0","videoUrl":"https://outin-8919d46ecbe211ea89ff00163e024c6a.oss-cn-shanghai.aliyuncs.com/sv/262be57a-173aa32646b/262be57a-173aa32646b.mp4","videoImageUrl":null,"videoLikeCount":6,"videoCommitCount":10,"videoType":null,"status":null,"createTime":null,"id":0,"blike":false,"bfollow":false},{"userId":368415507088539650,"nickName":"15065157316","headImg":"https://worldcar.oss-cn-beijing.aliyuncs.com/default.jpg?Expires=1594610492&OSSAccessKeyId=TMP.3KdwRbfSizxtnBKhKc7m3EL1HE28hdSWL4SWb6ZDk95jVQ6Wpd4boTiVNUYakjxWAkqaUxjnwctotoeUKjk6um1wQkX1QP&Signature=rgMUTJ5ySusgvQ%2BiHUbn%2FgYKxRE%3D&nsukey=Xl%2Fux%2BRF%2FbeKNylUotdObHWHd9suyR7oZNGAznnzgYtC9ZbB47sBt%2FRNpridMuqNfAGqrpQaAZnMPk3HoAsTGKDfENivzX%2B7nATzsEyXuEE0DJR7si0JvZ7XgZLwU1M%2F1QckfxvoL7FD5KWYF3gSOuHKRezXv2%2BK6COv8ivmBIk%3D","hxuid":"hqyc15065157316","videoId":"6","videoTitle":"视频测试1596288294","videoDescription":"0.0","videoUrl":"https://outin-8919d46ecbe211ea89ff00163e024c6a.oss-cn-shanghai.aliyuncs.com/sv/262be57a-173aa32646b/262be57a-173aa32646b.mp4","videoImageUrl":null,"videoLikeCount":6,"videoCommitCount":10,"videoType":null,"status":null,"createTime":null,"id":0,"blike":false,"bfollow":false},{"userId":368415507088539650,"nickName":"15065157316","headImg":"https://worldcar.oss-cn-beijing.aliyuncs.com/default.jpg?Expires=1594610492&OSSAccessKeyId=TMP.3KdwRbfSizxtnBKhKc7m3EL1HE28hdSWL4SWb6ZDk95jVQ6Wpd4boTiVNUYakjxWAkqaUxjnwctotoeUKjk6um1wQkX1QP&Signature=rgMUTJ5ySusgvQ%2BiHUbn%2FgYKxRE%3D&nsukey=Xl%2Fux%2BRF%2FbeKNylUotdObHWHd9suyR7oZNGAznnzgYtC9ZbB47sBt%2FRNpridMuqNfAGqrpQaAZnMPk3HoAsTGKDfENivzX%2B7nATzsEyXuEE0DJR7si0JvZ7XgZLwU1M%2F1QckfxvoL7FD5KWYF3gSOuHKRezXv2%2BK6COv8ivmBIk%3D","hxuid":"hqyc15065157316","videoId":"7","videoTitle":"视频测试1596288294","videoDescription":"0.0","videoUrl":"https://outin-8919d46ecbe211ea89ff00163e024c6a.oss-cn-shanghai.aliyuncs.com/sv/262be57a-173aa32646b/262be57a-173aa32646b.mp4","videoImageUrl":null,"videoLikeCount":6,"videoCommitCount":10,"videoType":null,"status":null,"createTime":null,"id":0,"blike":false,"bfollow":false},{"userId":368415507088539650,"nickName":"15065157316","headImg":"https://worldcar.oss-cn-beijing.aliyuncs.com/default.jpg?Expires=1594610492&OSSAccessKeyId=TMP.3KdwRbfSizxtnBKhKc7m3EL1HE28hdSWL4SWb6ZDk95jVQ6Wpd4boTiVNUYakjxWAkqaUxjnwctotoeUKjk6um1wQkX1QP&Signature=rgMUTJ5ySusgvQ%2BiHUbn%2FgYKxRE%3D&nsukey=Xl%2Fux%2BRF%2FbeKNylUotdObHWHd9suyR7oZNGAznnzgYtC9ZbB47sBt%2FRNpridMuqNfAGqrpQaAZnMPk3HoAsTGKDfENivzX%2B7nATzsEyXuEE0DJR7si0JvZ7XgZLwU1M%2F1QckfxvoL7FD5KWYF3gSOuHKRezXv2%2BK6COv8ivmBIk%3D","hxuid":"hqyc15065157316","videoId":"8","videoTitle":"视频测试1596288294","videoDescription":"0.0","videoUrl":"https://outin-8919d46ecbe211ea89ff00163e024c6a.oss-cn-shanghai.aliyuncs.com/sv/262be57a-173aa32646b/262be57a-173aa32646b.mp4","videoImageUrl":null,"videoLikeCount":6,"videoCommitCount":10,"videoType":null,"status":null,"createTime":null,"id":0,"blike":false,"bfollow":false},{"userId":368415507088539650,"nickName":"15065157316","headImg":"https://worldcar.oss-cn-beijing.aliyuncs.com/default.jpg?Expires=1594610492&OSSAccessKeyId=TMP.3KdwRbfSizxtnBKhKc7m3EL1HE28hdSWL4SWb6ZDk95jVQ6Wpd4boTiVNUYakjxWAkqaUxjnwctotoeUKjk6um1wQkX1QP&Signature=rgMUTJ5ySusgvQ%2BiHUbn%2FgYKxRE%3D&nsukey=Xl%2Fux%2BRF%2FbeKNylUotdObHWHd9suyR7oZNGAznnzgYtC9ZbB47sBt%2FRNpridMuqNfAGqrpQaAZnMPk3HoAsTGKDfENivzX%2B7nATzsEyXuEE0DJR7si0JvZ7XgZLwU1M%2F1QckfxvoL7FD5KWYF3gSOuHKRezXv2%2BK6COv8ivmBIk%3D","hxuid":"hqyc15065157316","videoId":"9","videoTitle":"视频测试1596288294","videoDescription":"0.0","videoUrl":"https://outin-8919d46ecbe211ea89ff00163e024c6a.oss-cn-shanghai.aliyuncs.com/sv/262be57a-173aa32646b/262be57a-173aa32646b.mp4","videoImageUrl":null,"videoLikeCount":6,"videoCommitCount":10,"videoType":null,"status":null,"createTime":null,"id":0,"blike":false,"bfollow":false},{"userId":368415507088539650,"nickName":"15065157316","headImg":"https://worldcar.oss-cn-beijing.aliyuncs.com/default.jpg?Expires=1594610492&OSSAccessKeyId=TMP.3KdwRbfSizxtnBKhKc7m3EL1HE28hdSWL4SWb6ZDk95jVQ6Wpd4boTiVNUYakjxWAkqaUxjnwctotoeUKjk6um1wQkX1QP&Signature=rgMUTJ5ySusgvQ%2BiHUbn%2FgYKxRE%3D&nsukey=Xl%2Fux%2BRF%2FbeKNylUotdObHWHd9suyR7oZNGAznnzgYtC9ZbB47sBt%2FRNpridMuqNfAGqrpQaAZnMPk3HoAsTGKDfENivzX%2B7nATzsEyXuEE0DJR7si0JvZ7XgZLwU1M%2F1QckfxvoL7FD5KWYF3gSOuHKRezXv2%2BK6COv8ivmBIk%3D","hxuid":"hqyc15065157316","videoId":"10","videoTitle":"视频测试1596288294","videoDescription":"0.0","videoUrl":"https://outin-8919d46ecbe211ea89ff00163e024c6a.oss-cn-shanghai.aliyuncs.com/sv/262be57a-173aa32646b/262be57a-173aa32646b.mp4","videoImageUrl":null,"videoLikeCount":6,"videoCommitCount":10,"videoType":null,"status":null,"createTime":null,"id":0,"blike":false,"bfollow":false},{"userId":368415507088539650,"nickName":"15065157316","headImg":"https://worldcar.oss-cn-beijing.aliyuncs.com/default.jpg?Expires=1594610492&OSSAccessKeyId=TMP.3KdwRbfSizxtnBKhKc7m3EL1HE28hdSWL4SWb6ZDk95jVQ6Wpd4boTiVNUYakjxWAkqaUxjnwctotoeUKjk6um1wQkX1QP&Signature=rgMUTJ5ySusgvQ%2BiHUbn%2FgYKxRE%3D&nsukey=Xl%2Fux%2BRF%2FbeKNylUotdObHWHd9suyR7oZNGAznnzgYtC9ZbB47sBt%2FRNpridMuqNfAGqrpQaAZnMPk3HoAsTGKDfENivzX%2B7nATzsEyXuEE0DJR7si0JvZ7XgZLwU1M%2F1QckfxvoL7FD5KWYF3gSOuHKRezXv2%2BK6COv8ivmBIk%3D","hxuid":"hqyc15065157316","videoId":"11","videoTitle":"视频测试1596288294","videoDescription":"0.0","videoUrl":"https://outin-8919d46ecbe211ea89ff00163e024c6a.oss-cn-shanghai.aliyuncs.com/sv/262be57a-173aa32646b/262be57a-173aa32646b.mp4","videoImageUrl":null,"videoLikeCount":6,"videoCommitCount":10,"videoType":null,"status":null,"createTime":null,"id":0,"blike":false,"bfollow":false}]
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

    @Override
    public String toString() {
        return "VideoListBean{" +
                "success=" + success +
                ", msg='" + msg + '\'' +
                ", code='" + code + '\'' +
                ", traceId=" + traceId +
                ", data=" + data +
                '}';
    }

    public static class DataBean {
        /**
         * userId : 368415507088539650
         * nickName : 15065157316
         * headImg : https://worldcar.oss-cn-beijing.aliyuncs.com/default.jpg?Expires=1594610492&OSSAccessKeyId=TMP.3KdwRbfSizxtnBKhKc7m3EL1HE28hdSWL4SWb6ZDk95jVQ6Wpd4boTiVNUYakjxWAkqaUxjnwctotoeUKjk6um1wQkX1QP&Signature=rgMUTJ5ySusgvQ%2BiHUbn%2FgYKxRE%3D&nsukey=Xl%2Fux%2BRF%2FbeKNylUotdObHWHd9suyR7oZNGAznnzgYtC9ZbB47sBt%2FRNpridMuqNfAGqrpQaAZnMPk3HoAsTGKDfENivzX%2B7nATzsEyXuEE0DJR7si0JvZ7XgZLwU1M%2F1QckfxvoL7FD5KWYF3gSOuHKRezXv2%2BK6COv8ivmBIk%3D
         * hxuid : hqyc15065157316
         * videoId : 2
         * videoTitle : 视频测试1596288294
         * videoDescription : 0.0
         * videoUrl : https://outin-8919d46ecbe211ea89ff00163e024c6a.oss-cn-shanghai.aliyuncs.com/sv/262be57a-173aa32646b/262be57a-173aa32646b.mp4
         * videoImageUrl : null
         * videoLikeCount : 6
         * videoCommitCount : 10
         * videoType : null
         * status : null
         * createTime : null
         * id : 0
         * blike : false
         * bfollow : false
         */

        private long userId;
        private String nickName;
        private String headImg;
        private String hxuid;
        private String videoId;
        private String videoTitle;
        private String videoDescription;
        private String videoUrl;
        private Object videoImageUrl;
        private int videoLikeCount;
        private int videoCommitCount;
        private Object videoType;
        private Object status;
        private Object createTime;
        private int id;
        private boolean blike;
        private boolean bfollow;

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

        public String getHxuid() {
            return hxuid;
        }

        public void setHxuid(String hxuid) {
            this.hxuid = hxuid;
        }

        public String getVideoId() {
            return videoId;
        }

        public void setVideoId(String videoId) {
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

        public int getVideoLikeCount() {
            return videoLikeCount;
        }

        public void setVideoLikeCount(int videoLikeCount) {
            this.videoLikeCount = videoLikeCount;
        }

        public int getVideoCommitCount() {
            return videoCommitCount;
        }

        public void setVideoCommitCount(int videoCommitCount) {
            this.videoCommitCount = videoCommitCount;
        }

        public Object getVideoType() {
            return videoType;
        }

        public void setVideoType(Object videoType) {
            this.videoType = videoType;
        }

        public Object getStatus() {
            return status;
        }

        public void setStatus(Object status) {
            this.status = status;
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

        public boolean isBfollow() {
            return bfollow;
        }

        public void setBfollow(boolean bfollow) {
            this.bfollow = bfollow;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "userId=" + userId +
                    ", nickName='" + nickName + '\'' +
                    ", headImg='" + headImg + '\'' +
                    ", hxuid='" + hxuid + '\'' +
                    ", videoId='" + videoId + '\'' +
                    ", videoTitle='" + videoTitle + '\'' +
                    ", videoDescription='" + videoDescription + '\'' +
                    ", videoUrl='" + videoUrl + '\'' +
                    ", videoImageUrl=" + videoImageUrl +
                    ", videoLikeCount=" + videoLikeCount +
                    ", videoCommitCount=" + videoCommitCount +
                    ", videoType=" + videoType +
                    ", status=" + status +
                    ", createTime=" + createTime +
                    ", id=" + id +
                    ", blike=" + blike +
                    ", bfollow=" + bfollow +
                    '}';
        }
    }
}
