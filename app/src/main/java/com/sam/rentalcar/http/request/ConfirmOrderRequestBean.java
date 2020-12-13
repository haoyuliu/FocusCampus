package com.sam.rentalcar.http.request;

/**
 * author:sam
 * time:2020/12/12
 * desc: 订单详情确认请求数据实体类
 * version:1.0
 */
public class ConfirmOrderRequestBean {


    /**
     * backPickUpId : 1
     * beginDate : 2020-12-01 09:00:00
     * carId : 1
     * couponId : 1
     * endDate : 2020-12-02 09:00:00
     * takePickUpId : 1
     * vipService : 1
     */

    private int backPickUpId;
    private String beginDate;
    private int carId;
    private int couponId;
    private String endDate;
    private int takePickUpId;
    private int vipService;

    public int getBackPickUpId() {
        return backPickUpId;
    }

    public void setBackPickUpId(int backPickUpId) {
        this.backPickUpId = backPickUpId;
    }

    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public int getCouponId() {
        return couponId;
    }

    public void setCouponId(int couponId) {
        this.couponId = couponId;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public int getTakePickUpId() {
        return takePickUpId;
    }

    public void setTakePickUpId(int takePickUpId) {
        this.takePickUpId = takePickUpId;
    }

    public int getVipService() {
        return vipService;
    }

    public void setVipService(int vipService) {
        this.vipService = vipService;
    }
}
