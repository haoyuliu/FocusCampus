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

    private boolean blike;
    private String content;
    private String createTime;
    private String id;
    private long userId;
    private String userImg;
    private String videoId;

    public boolean isBlike() {
        return blike;
    }

    public void setBlike(boolean blike) {
        this.blike = blike;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

        public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUserImg() {
        return userImg;
    }

    public void setUserImg(String userImg) {
        this.userImg = userImg;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }
}
