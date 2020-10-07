package com.sam.rentalcar.http.response;

import java.util.List;

/**
 * author:sam
 * time:2020/09/20
 * desc: 车型列表返回数据的实体类
 * version:1.0
 */
public class GetCarBrandListResponseBean {

    /**
     * success : true
     * data : [{"id":1,"name":"大众","remark":""},{"id":2,"name":"丰田","remark":""},{"id":3,"name":"本田","remark":""},{"id":4,"name":"比亚迪","remark":""}]
     * msg : success
     * code : 200
     * traceId : null
     */

    private boolean success;
    private String msg;
    private String code;
    private Object traceId;
    private List<DataBean> data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 1
         * name : 大众
         * remark :
         */

        private int id;
        private String name;
        private String remark;

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

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }
    }
}
