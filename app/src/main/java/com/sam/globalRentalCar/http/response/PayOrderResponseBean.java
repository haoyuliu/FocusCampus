package com.sam.globalRentalCar.http.response;

/**
 * author:sam
 * time:2020/12/13
 * desc: 获取调起支付宝支付的接口返回值
 * version:1.0
 */
public class PayOrderResponseBean {


    /**
     * success : true
     * data : alipay_sdk=alipay-easysdk-java-2.1.0&app_id=2021002111602830&biz_content=%7B%22out_trade_no%22%3A%22416907597011091456%22%2C%22total_amount%22%3A%22350.01%22%2C%22subject%22%3A%22%E7%A7%9F%E8%BD%A6%E8%AE%A2%E5%8D%95%22%7D&charset=UTF-8&format=json&method=alipay.trade.app.pay&notify_url=http%3A%2F%2F39.106.49.27%3A28080%2FpayCallback%2FaliCallback&sign=Pu3XzvYhpyy4xWbgKzazpKSCRZaKekcu4%2B4Kd8MiNYDgZErgX5Q%2Fveup%2B3kiCGY4wbQpOK4IfpktcgDOLhvOvCkOoowqJaoC536fdlM7UrCHHvoUT44geInhM1md3mtHp8ufwK9xZuXywFcALFOCOS0fwEx8%2FoBEjj99CMo4F8Zf1a3EqQiFT51Ky9FbtmhxdHhUYOe6O1eQGBe3LiKxlAH65jn%2FMjPpZ%2BgQJL23Mblo6R3Q2YliLjkh1fpBLXHu6JPdCzrCvMKFxaMlX%2BL7pxq6sXDZFhXILldyUpsEfCxWYcKtxlQ0KM6%2B15jFqxdwPZmKq7SpbV2Fa15mteTv7Q%3D%3D&sign_type=RSA2&timestamp=2020-12-13+10%3A42%3A12&version=1.0
     * msg : success
     * code : 200
     * traceId : null
     */

    private boolean success;
    private String data;
    private String msg;
    private String code;
    private Object traceId;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
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
}
