package com.sam.rentalcar.http.response;

/**
 * author:sam
 * time:2020/09/20
 * desc: 车型列表返回数据的实体类
 * version:1.0
 */
public class OrderDetailResponseBean {


    /**
     * success : true
     * data : {"id":416583266397716480,"carId":5,"name":"帝豪","userId":373603655418511360,"money":400,"createTime":"2020-12-12 13:13:10","remark":"utf8mb4_general_ci","orderStatus":6,"takePickupId":1,"takePickupName":"首都机场","backPickupId":1,"beginTime":null,"endTime":null,"couponId":null}
     * msg : success
     * code : 200
     * traceId : null
     */

    private boolean success;
    private DataBean data;
    private String msg;
    private String code;
    private Object traceId;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
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

    public static class DataBean {
        /**
         * id : 416583266397716480
         * carId : 5
         * name : 帝豪
         * userId : 373603655418511360
         * money : 400.0
         * createTime : 2020-12-12 13:13:10
         * remark : utf8mb4_general_ci
         * orderStatus : 6
         * takePickupId : 1
         * takePickupName : 首都机场
         * backPickupId : 1
         * beginTime : null
         * endTime : null
         * couponId : null
         */

        private long id;
        private int carId;
        private String name;
        private long userId;
        private double money;
        private String createTime;
        private String remark;
        private int orderStatus;
        private int takePickupId;
        private String takePickupName;
        private int backPickupId;
        private Object beginTime;
        private Object endTime;
        private Object couponId;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public int getCarId() {
            return carId;
        }

        public void setCarId(int carId) {
            this.carId = carId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public long getUserId() {
            return userId;
        }

        public void setUserId(long userId) {
            this.userId = userId;
        }

        public double getMoney() {
            return money;
        }

        public void setMoney(double money) {
            this.money = money;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public int getOrderStatus() {
            return orderStatus;
        }

        public void setOrderStatus(int orderStatus) {
            this.orderStatus = orderStatus;
        }

        public int getTakePickupId() {
            return takePickupId;
        }

        public void setTakePickupId(int takePickupId) {
            this.takePickupId = takePickupId;
        }

        public String getTakePickupName() {
            return takePickupName;
        }

        public void setTakePickupName(String takePickupName) {
            this.takePickupName = takePickupName;
        }

        public int getBackPickupId() {
            return backPickupId;
        }

        public void setBackPickupId(int backPickupId) {
            this.backPickupId = backPickupId;
        }

        public Object getBeginTime() {
            return beginTime;
        }

        public void setBeginTime(Object beginTime) {
            this.beginTime = beginTime;
        }

        public Object getEndTime() {
            return endTime;
        }

        public void setEndTime(Object endTime) {
            this.endTime = endTime;
        }

        public Object getCouponId() {
            return couponId;
        }

        public void setCouponId(Object couponId) {
            this.couponId = couponId;
        }
    }
}
