package com.sam.rentalcar.http.response;

import java.util.List;

/**
 * author:sam
 * time:2020/09/20
 * desc: 获取用户所有优惠券列表的返回实体类
 * version:1.0
 */
public class GetUserCouponListResponseBean {


    /**
     * code : string
     * data : [{"beginTime":"2020-09-20T14:44:55.565Z","endTime":"2020-09-20T14:44:55.565Z","id":0,"name":"string","type":0,"usedAmount":0,"valid":0,"withAmount":0}]
     * msg : string
     * success : true
     * traceId : string
     */

    private String code;
    private String msg;
    private boolean success;
    private String traceId;
    private List<DataBean> data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * beginTime : 2020-09-20T14:44:55.565Z
         * endTime : 2020-09-20T14:44:55.565Z
         * id : 0
         * name : string
         * type : 0
         * usedAmount : 0
         * valid : 0
         * withAmount : 0
         */

        private String beginTime;
        private String endTime;
        private int id;
        private String name;
        private int type;
        private int usedAmount;
        private int valid;
        private int withAmount;

        public String getBeginTime() {
            return beginTime;
        }

        public void setBeginTime(String beginTime) {
            this.beginTime = beginTime;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getUsedAmount() {
            return usedAmount;
        }

        public void setUsedAmount(int usedAmount) {
            this.usedAmount = usedAmount;
        }

        public int getValid() {
            return valid;
        }

        public void setValid(int valid) {
            this.valid = valid;
        }

        public int getWithAmount() {
            return withAmount;
        }

        public void setWithAmount(int withAmount) {
            this.withAmount = withAmount;
        }
    }
}
