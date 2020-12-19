package com.sam.globalRentalCar.http.request;

/**
 * author:sam
 * time:2020/12/13
 * desc: 支付宝支付请求参数实体类
 * version:1.0
 */
public class PayOrderRequestBean {


    /**
     * orderCode : 416907597011091456
     * payWay : 1表示支付宝
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
