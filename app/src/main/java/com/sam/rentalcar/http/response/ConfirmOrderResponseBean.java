package com.sam.rentalcar.http.response;


/**
 * author:sam
 * time:2020/12/12
 * desc: 点击确认订单返回的数据实体类
 * version:1.0
 */
public class ConfirmOrderResponseBean {


    /**
     * success : true
     * data : {"id":416674548570783744,"carId":1,"userId":368415507088539648,"money":350.01,"createTime":"2020-12-12 19:15:53","modifyTime":null,"deleted":0,"createBy":368415507088539648,"editedBy":null,"remark":null,"status":0,"testFlag":0,"takePickupId":1,"backPickupId":1,"beginTime":null,"endTime":null,"couponId":1}
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
         * id : 416674548570783744
         * carId : 1
         * userId : 368415507088539648
         * money : 350.01
         * createTime : 2020-12-12 19:15:53
         * modifyTime : null
         * deleted : 0
         * createBy : 368415507088539648
         * editedBy : null
         * remark : null
         * status : 0
         * testFlag : 0
         * takePickupId : 1
         * backPickupId : 1
         * beginTime : null
         * endTime : null
         * couponId : 1
         */

        private long id;
        private int carId;
        private long userId;
        private double money;
        private String createTime;
        private Object modifyTime;
        private int deleted;
        private long createBy;
        private Object editedBy;
        private Object remark;
        private int status;
        private int testFlag;
        private int takePickupId;
        private int backPickupId;
        private Object beginTime;
        private Object endTime;
        private int couponId;

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

        public Object getModifyTime() {
            return modifyTime;
        }

        public void setModifyTime(Object modifyTime) {
            this.modifyTime = modifyTime;
        }

        public int getDeleted() {
            return deleted;
        }

        public void setDeleted(int deleted) {
            this.deleted = deleted;
        }

        public long getCreateBy() {
            return createBy;
        }

        public void setCreateBy(long createBy) {
            this.createBy = createBy;
        }

        public Object getEditedBy() {
            return editedBy;
        }

        public void setEditedBy(Object editedBy) {
            this.editedBy = editedBy;
        }

        public Object getRemark() {
            return remark;
        }

        public void setRemark(Object remark) {
            this.remark = remark;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getTestFlag() {
            return testFlag;
        }

        public void setTestFlag(int testFlag) {
            this.testFlag = testFlag;
        }

        public int getTakePickupId() {
            return takePickupId;
        }

        public void setTakePickupId(int takePickupId) {
            this.takePickupId = takePickupId;
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

        public int getCouponId() {
            return couponId;
        }

        public void setCouponId(int couponId) {
            this.couponId = couponId;
        }
    }
}
