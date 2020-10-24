package com.sam.rentalcar.bean;

import android.os.Parcelable;

import java.io.Serializable;
import java.util.List;

/**
 * author:sam
 * time:2020/08/09
 * desc:用户喜欢和用户作品的bean
 * version:1.0
 */
public class UserProductionOrLoveBean implements Serializable {

    /**
     * success : true
     * data : [{"userId":368415507088539650,"nickName":"15065157316","headImg":"https://worldcar.oss-cn-beijing.aliyuncs.com/default.jpg?Expires=1594610492&OSSAccessKeyId=TMP.3KdwRbfSizxtnBKhKc7m3EL1HE28hdSWL4SWb6ZDk95jVQ6Wpd4boTiVNUYakjxWAkqaUxjnwctotoeUKjk6um1wQkX1QP&Signature=rgMUTJ5ySusgvQ%2BiHUbn%2FgYKxRE%3D&nsukey=Xl%2Fux%2BRF%2FbeKNylUotdObHWHd9suyR7oZNGAznnzgYtC9ZbB47sBt%2FRNpridMuqNfAGqrpQaAZnMPk3HoAsTGKDfENivzX%2B7nATzsEyXuEE0DJR7si0JvZ7XgZLwU1M%2F1QckfxvoL7FD5KWYF3gSOuHKRezXv2%2BK6COv8ivmBIk%3D","hxuid":"hqyc15065157316","videoId":"11","videoTitle":"视频测试1596964164","videoDescription":"0.0","videoUrl":"https://outin-8919d46ecbe211ea89ff00163e024c6a.oss-cn-shanghai.aliyuncs.com/sv/13280790-173d27b5373/13280790-173d27b5373.mp4","videoImageUrl":null,"videoLikeCount":null,"videoCommitCount":null,"videoType":null,"status":null,"createTime":null,"id":0},{"userId":368415507088539650,"nickName":"15065157316","headImg":"https://worldcar.oss-cn-beijing.aliyuncs.com/default.jpg?Expires=1594610492&OSSAccessKeyId=TMP.3KdwRbfSizxtnBKhKc7m3EL1HE28hdSWL4SWb6ZDk95jVQ6Wpd4boTiVNUYakjxWAkqaUxjnwctotoeUKjk6um1wQkX1QP&Signature=rgMUTJ5ySusgvQ%2BiHUbn%2FgYKxRE%3D&nsukey=Xl%2Fux%2BRF%2FbeKNylUotdObHWHd9suyR7oZNGAznnzgYtC9ZbB47sBt%2FRNpridMuqNfAGqrpQaAZnMPk3HoAsTGKDfENivzX%2B7nATzsEyXuEE0DJR7si0JvZ7XgZLwU1M%2F1QckfxvoL7FD5KWYF3gSOuHKRezXv2%2BK6COv8ivmBIk%3D","hxuid":"hqyc15065157316","videoId":"12","videoTitle":"视频测试1596964182","videoDescription":"0.0","videoUrl":"https://outin-8919d46ecbe211ea89ff00163e024c6a.oss-cn-shanghai.aliyuncs.com/sv/122e9e23-173d27b989d/122e9e23-173d27b989d.mp4","videoImageUrl":null,"videoLikeCount":null,"videoCommitCount":null,"videoType":null,"status":null,"createTime":null,"id":0},{"userId":368415507088539650,"nickName":"15065157316","headImg":"https://worldcar.oss-cn-beijing.aliyuncs.com/default.jpg?Expires=1594610492&OSSAccessKeyId=TMP.3KdwRbfSizxtnBKhKc7m3EL1HE28hdSWL4SWb6ZDk95jVQ6Wpd4boTiVNUYakjxWAkqaUxjnwctotoeUKjk6um1wQkX1QP&Signature=rgMUTJ5ySusgvQ%2BiHUbn%2FgYKxRE%3D&nsukey=Xl%2Fux%2BRF%2FbeKNylUotdObHWHd9suyR7oZNGAznnzgYtC9ZbB47sBt%2FRNpridMuqNfAGqrpQaAZnMPk3HoAsTGKDfENivzX%2B7nATzsEyXuEE0DJR7si0JvZ7XgZLwU1M%2F1QckfxvoL7FD5KWYF3gSOuHKRezXv2%2BK6COv8ivmBIk%3D","hxuid":"hqyc15065157316","videoId":"2","videoTitle":"视频测试1596288147","videoDescription":"0.0","videoUrl":"https://outin-8919d46ecbe211ea89ff00163e024c6a.oss-cn-shanghai.aliyuncs.com/sv/64e15ac-173aa30228f/64e15ac-173aa30228f.mp4","videoImageUrl":null,"videoLikeCount":null,"videoCommitCount":null,"videoType":null,"status":null,"createTime":null,"id":0},{"userId":368415507088539650,"nickName":"15065157316","headImg":"https://worldcar.oss-cn-beijing.aliyuncs.com/default.jpg?Expires=1594610492&OSSAccessKeyId=TMP.3KdwRbfSizxtnBKhKc7m3EL1HE28hdSWL4SWb6ZDk95jVQ6Wpd4boTiVNUYakjxWAkqaUxjnwctotoeUKjk6um1wQkX1QP&Signature=rgMUTJ5ySusgvQ%2BiHUbn%2FgYKxRE%3D&nsukey=Xl%2Fux%2BRF%2FbeKNylUotdObHWHd9suyR7oZNGAznnzgYtC9ZbB47sBt%2FRNpridMuqNfAGqrpQaAZnMPk3HoAsTGKDfENivzX%2B7nATzsEyXuEE0DJR7si0JvZ7XgZLwU1M%2F1QckfxvoL7FD5KWYF3gSOuHKRezXv2%2BK6COv8ivmBIk%3D","hxuid":"hqyc15065157316","videoId":"13","videoTitle":"视频测试1596964199","videoDescription":"0.0","videoUrl":"https://outin-8919d46ecbe211ea89ff00163e024c6a.oss-cn-shanghai.aliyuncs.com/sv/fabd999-173d27bdbca/fabd999-173d27bdbca.mp4","videoImageUrl":null,"videoLikeCount":null,"videoCommitCount":null,"videoType":null,"status":null,"createTime":null,"id":0},{"userId":368415507088539650,"nickName":"15065157316","headImg":"https://worldcar.oss-cn-beijing.aliyuncs.com/default.jpg?Expires=1594610492&OSSAccessKeyId=TMP.3KdwRbfSizxtnBKhKc7m3EL1HE28hdSWL4SWb6ZDk95jVQ6Wpd4boTiVNUYakjxWAkqaUxjnwctotoeUKjk6um1wQkX1QP&Signature=rgMUTJ5ySusgvQ%2BiHUbn%2FgYKxRE%3D&nsukey=Xl%2Fux%2BRF%2FbeKNylUotdObHWHd9suyR7oZNGAznnzgYtC9ZbB47sBt%2FRNpridMuqNfAGqrpQaAZnMPk3HoAsTGKDfENivzX%2B7nATzsEyXuEE0DJR7si0JvZ7XgZLwU1M%2F1QckfxvoL7FD5KWYF3gSOuHKRezXv2%2BK6COv8ivmBIk%3D","hxuid":"hqyc15065157316","videoId":"3","videoTitle":"视频测试1596288180","videoDescription":"0.0","videoUrl":"https://outin-8919d46ecbe211ea89ff00163e024c6a.oss-cn-shanghai.aliyuncs.com/sv/2c33ba6c-173aa30a24d/2c33ba6c-173aa30a24d.mp4","videoImageUrl":null,"videoLikeCount":null,"videoCommitCount":null,"videoType":null,"status":null,"createTime":null,"id":0},{"userId":368415507088539650,"nickName":"15065157316","headImg":"https://worldcar.oss-cn-beijing.aliyuncs.com/default.jpg?Expires=1594610492&OSSAccessKeyId=TMP.3KdwRbfSizxtnBKhKc7m3EL1HE28hdSWL4SWb6ZDk95jVQ6Wpd4boTiVNUYakjxWAkqaUxjnwctotoeUKjk6um1wQkX1QP&Signature=rgMUTJ5ySusgvQ%2BiHUbn%2FgYKxRE%3D&nsukey=Xl%2Fux%2BRF%2FbeKNylUotdObHWHd9suyR7oZNGAznnzgYtC9ZbB47sBt%2FRNpridMuqNfAGqrpQaAZnMPk3HoAsTGKDfENivzX%2B7nATzsEyXuEE0DJR7si0JvZ7XgZLwU1M%2F1QckfxvoL7FD5KWYF3gSOuHKRezXv2%2BK6COv8ivmBIk%3D","hxuid":"hqyc15065157316","videoId":"14","videoTitle":"视频测试1596964222","videoDescription":"0.0","videoUrl":"https://outin-8919d46ecbe211ea89ff00163e024c6a.oss-cn-shanghai.aliyuncs.com/sv/521ae6c4-173d27c35f1/521ae6c4-173d27c35f1.mp4","videoImageUrl":null,"videoLikeCount":null,"videoCommitCount":null,"videoType":null,"status":null,"createTime":null,"id":0},{"userId":368415507088539650,"nickName":"15065157316","headImg":"https://worldcar.oss-cn-beijing.aliyuncs.com/default.jpg?Expires=1594610492&OSSAccessKeyId=TMP.3KdwRbfSizxtnBKhKc7m3EL1HE28hdSWL4SWb6ZDk95jVQ6Wpd4boTiVNUYakjxWAkqaUxjnwctotoeUKjk6um1wQkX1QP&Signature=rgMUTJ5ySusgvQ%2BiHUbn%2FgYKxRE%3D&nsukey=Xl%2Fux%2BRF%2FbeKNylUotdObHWHd9suyR7oZNGAznnzgYtC9ZbB47sBt%2FRNpridMuqNfAGqrpQaAZnMPk3HoAsTGKDfENivzX%2B7nATzsEyXuEE0DJR7si0JvZ7XgZLwU1M%2F1QckfxvoL7FD5KWYF3gSOuHKRezXv2%2BK6COv8ivmBIk%3D","hxuid":"hqyc15065157316","videoId":"4","videoTitle":"视频测试1596288213","videoDescription":"0.0","videoUrl":"https://outin-8919d46ecbe211ea89ff00163e024c6a.oss-cn-shanghai.aliyuncs.com/sv/34861c1d-173aa312462/34861c1d-173aa312462.mp4","videoImageUrl":null,"videoLikeCount":null,"videoCommitCount":null,"videoType":null,"status":null,"createTime":null,"id":0},{"userId":368415507088539650,"nickName":"15065157316","headImg":"https://worldcar.oss-cn-beijing.aliyuncs.com/default.jpg?Expires=1594610492&OSSAccessKeyId=TMP.3KdwRbfSizxtnBKhKc7m3EL1HE28hdSWL4SWb6ZDk95jVQ6Wpd4boTiVNUYakjxWAkqaUxjnwctotoeUKjk6um1wQkX1QP&Signature=rgMUTJ5ySusgvQ%2BiHUbn%2FgYKxRE%3D&nsukey=Xl%2Fux%2BRF%2FbeKNylUotdObHWHd9suyR7oZNGAznnzgYtC9ZbB47sBt%2FRNpridMuqNfAGqrpQaAZnMPk3HoAsTGKDfENivzX%2B7nATzsEyXuEE0DJR7si0JvZ7XgZLwU1M%2F1QckfxvoL7FD5KWYF3gSOuHKRezXv2%2BK6COv8ivmBIk%3D","hxuid":"hqyc15065157316","videoId":"15","videoTitle":"视频测试1596965517","videoDescription":"33.484","videoUrl":"https://outin-8919d46ecbe211ea89ff00163e024c6a.oss-cn-shanghai.aliyuncs.com/sv/36a42365-173d28ffb63/36a42365-173d28ffb63.mp4","videoImageUrl":"http://outin-8919d46ecbe211ea89ff00163e024c6a.oss-cn-shanghai.aliyuncs.com/29c6c122bfe44de786932701ebf4b150/snapshots/a6cf8e8138d84bb2905c898d82f7abbe-00002.jpg?Expires=1596969137&OSSAccessKeyId=LTAIxSaOfEzCnBOj&Signature=IbDvcFBSUQ4JEPitdC4noPskMGs%3D","videoLikeCount":null,"videoCommitCount":null,"videoType":null,"status":null,"createTime":null,"id":0},{"userId":368415507088539650,"nickName":"15065157316","headImg":"https://worldcar.oss-cn-beijing.aliyuncs.com/default.jpg?Expires=1594610492&OSSAccessKeyId=TMP.3KdwRbfSizxtnBKhKc7m3EL1HE28hdSWL4SWb6ZDk95jVQ6Wpd4boTiVNUYakjxWAkqaUxjnwctotoeUKjk6um1wQkX1QP&Signature=rgMUTJ5ySusgvQ%2BiHUbn%2FgYKxRE%3D&nsukey=Xl%2Fux%2BRF%2FbeKNylUotdObHWHd9suyR7oZNGAznnzgYtC9ZbB47sBt%2FRNpridMuqNfAGqrpQaAZnMPk3HoAsTGKDfENivzX%2B7nATzsEyXuEE0DJR7si0JvZ7XgZLwU1M%2F1QckfxvoL7FD5KWYF3gSOuHKRezXv2%2BK6COv8ivmBIk%3D","hxuid":"hqyc15065157316","videoId":"5","videoTitle":"视频测试1596288221","videoDescription":"0.0","videoUrl":"https://outin-8919d46ecbe211ea89ff00163e024c6a.oss-cn-shanghai.aliyuncs.com/sv/3e60539e-173aa31414d/3e60539e-173aa31414d.mp4","videoImageUrl":null,"videoLikeCount":null,"videoCommitCount":null,"videoType":null,"status":null,"createTime":null,"id":0},{"userId":368415507088539650,"nickName":"15065157316","headImg":"https://worldcar.oss-cn-beijing.aliyuncs.com/default.jpg?Expires=1594610492&OSSAccessKeyId=TMP.3KdwRbfSizxtnBKhKc7m3EL1HE28hdSWL4SWb6ZDk95jVQ6Wpd4boTiVNUYakjxWAkqaUxjnwctotoeUKjk6um1wQkX1QP&Signature=rgMUTJ5ySusgvQ%2BiHUbn%2FgYKxRE%3D&nsukey=Xl%2Fux%2BRF%2FbeKNylUotdObHWHd9suyR7oZNGAznnzgYtC9ZbB47sBt%2FRNpridMuqNfAGqrpQaAZnMPk3HoAsTGKDfENivzX%2B7nATzsEyXuEE0DJR7si0JvZ7XgZLwU1M%2F1QckfxvoL7FD5KWYF3gSOuHKRezXv2%2BK6COv8ivmBIk%3D","hxuid":"hqyc15065157316","videoId":"6","videoTitle":"视频测试1596288271","videoDescription":"0.0","videoUrl":"https://outin-8919d46ecbe211ea89ff00163e024c6a.oss-cn-shanghai.aliyuncs.com/sv/2f3eb9a7-173aa3206b4/2f3eb9a7-173aa3206b4.mp4","videoImageUrl":null,"videoLikeCount":null,"videoCommitCount":null,"videoType":null,"status":null,"createTime":null,"id":0}]
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

    public static class DataBean implements Serializable {
        /**
         * userId : 368415507088539650
         * nickName : 15065157316
         * headImg : https://worldcar.oss-cn-beijing.aliyuncs.com/default.jpg?Expires=1594610492&OSSAccessKeyId=TMP.3KdwRbfSizxtnBKhKc7m3EL1HE28hdSWL4SWb6ZDk95jVQ6Wpd4boTiVNUYakjxWAkqaUxjnwctotoeUKjk6um1wQkX1QP&Signature=rgMUTJ5ySusgvQ%2BiHUbn%2FgYKxRE%3D&nsukey=Xl%2Fux%2BRF%2FbeKNylUotdObHWHd9suyR7oZNGAznnzgYtC9ZbB47sBt%2FRNpridMuqNfAGqrpQaAZnMPk3HoAsTGKDfENivzX%2B7nATzsEyXuEE0DJR7si0JvZ7XgZLwU1M%2F1QckfxvoL7FD5KWYF3gSOuHKRezXv2%2BK6COv8ivmBIk%3D
         * hxuid : hqyc15065157316
         * videoId : 11
         * videoTitle : 视频测试1596964164
         * videoDescription : 0.0
         * videoUrl : https://outin-8919d46ecbe211ea89ff00163e024c6a.oss-cn-shanghai.aliyuncs.com/sv/13280790-173d27b5373/13280790-173d27b5373.mp4
         * videoImageUrl : null
         * videoLikeCount : null
         * videoCommitCount : null
         * videoType : null
         * status : null
         * createTime : null
         * id : 0
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
        private Object videoLikeCount;
        private Object videoCommitCount;
        private Object videoType;
        private Object status;
        private Object createTime;
        private int id;

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
    }
}
