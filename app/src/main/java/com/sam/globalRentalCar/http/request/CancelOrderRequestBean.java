package com.sam.globalRentalCar.http.request;

/**
 * author:sam
 * time:2020/08/09
 * desc: 取消订单
 * version:1.0
 */
public class CancelOrderRequestBean {

    /**
     * orderCode : string
     * payWay : 0
     */

    private String orderCode;
    private int payWay;

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public int getPayWay() {
        return payWay;
    }

    public void setPayWay(int payWay) {
        this.payWay = payWay;
    }
}
