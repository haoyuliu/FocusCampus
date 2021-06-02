package com.sam.globalRentalCar.http.request;

/**
 * author:sam
 * time:2020/08/22
 * desc:
 * version:1.0
 */
public class VideoCommentRequestBean {


    /**
     * blike : true
     * content : string
     * createTime : 2020-08-22T05:49:05.980Z
     * id : 0
     * userId : 0
     * userImg : string
     * videoId : 0
     */


    private String content;
    private long userId;
    private String videoId;


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

        public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }
}
