package com.sam.rental.http.request;

/**
 * author:sam
 * time:2020/08/26
 * desc: 获取图片上传所需要的参数
 * version:1.0
 */
public class GetUpLoadImageRequestBean {


    /**
     * imageExt : png
     */

    private String imageExt;

    public String getImageExt() {
        return imageExt;
    }

    public void setImageExt(String imageExt) {
        this.imageExt = imageExt;
    }
}
