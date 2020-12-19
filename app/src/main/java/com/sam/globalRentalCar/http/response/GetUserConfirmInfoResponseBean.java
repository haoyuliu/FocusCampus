package com.sam.globalRentalCar.http.response;

import java.util.List;

/**
 * author:sam
 * time:2020/10/18
 * desc: 获取订单确认页的信息
 * version:1.0
 */
public class GetUserConfirmInfoResponseBean {

    /**
     * success : true
     * data : {"basicCost":[{"costName":"车辆租赁费","costMoney":350},{"costName":"基础服务费","costMoney":300}],"vipCost":[{"costName":"尊享服务费","costMoney":100}]}
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
        private List<BasicCostBean> basicCost;
        private List<VipCostBean> vipCost;

        public List<BasicCostBean> getBasicCost() {
            return basicCost;
        }

        public void setBasicCost(List<BasicCostBean> basicCost) {
            this.basicCost = basicCost;
        }

        public List<VipCostBean> getVipCost() {
            return vipCost;
        }

        public void setVipCost(List<VipCostBean> vipCost) {
            this.vipCost = vipCost;
        }

        public static class BasicCostBean {
            /**
             * costName : 车辆租赁费
             * costMoney : 350
             */

            private String costName;
            private int costMoney;

            public String getCostName() {
                return costName;
            }

            public void setCostName(String costName) {
                this.costName = costName;
            }

            public int getCostMoney() {
                return costMoney;
            }

            public void setCostMoney(int costMoney) {
                this.costMoney = costMoney;
            }
        }

        public static class VipCostBean {
            /**
             * costName : 尊享服务费
             * costMoney : 100
             */

            private String costName;
            private int costMoney;

            public String getCostName() {
                return costName;
            }

            public void setCostName(String costName) {
                this.costName = costName;
            }

            public int getCostMoney() {
                return costMoney;
            }

            public void setCostMoney(int costMoney) {
                this.costMoney = costMoney;
            }
        }
    }
}
