package com.sam.rentalcar.http.request;

/**
 * author:sam
 * time:2020/11/02
 * desc: 删除视频请求的实体类
 * version:1.0
 */
public class DeleteVideoRequestBean {

    /**
     * blike : 0
     * content : string
     * createTime : 2020-11-02T13:29:53.470Z
     * id : 0
     * userId : 0
     * userImg : string
     * videoId : 0
     */

    private int blike;
    private String content;
    private String createTime;
    private int id;
    private int userId;
    private String userImg;
    private String videoId;

    public int getBlike() {
        return blike;
    }

    public void setBlike(int blike) {
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
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
