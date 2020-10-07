package com.sam.rentalcar.http.request;

/**
 * author:sam
 * time:2020/08/23
 * desc: 上传视频需要传递的参数的实体类
 * version:1.0
 */
public class UpLoadVideoRequestBean {


    /**
     * cateId : 22
     * coverURL : /storage/emulated/0/DCIM/Camera/VID_20200823_10541459.mp4
     * description : sss
     * fileName : sss.mp4
     * title : haha
     */

    private String cateId;
    private String coverURL;
    private String description;
    private String fileName;
    private String title;

    public String getCateId() {
        return cateId;
    }

    public void setCateId(String cateId) {
        this.cateId = cateId;
    }

    public String getCoverURL() {
        return coverURL;
    }

    public void setCoverURL(String coverURL) {
        this.coverURL = coverURL;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
