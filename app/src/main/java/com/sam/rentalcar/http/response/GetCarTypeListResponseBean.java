package com.sam.rentalcar.http.response;

import java.util.List;

/**
 * author:sam
 * time:2020/09/20
 * desc: 车型列表返回数据的实体类
 * version:1.0
 */
public class GetCarTypeListResponseBean {


    /**
     * success : true
     * data : [{"id":1,"typeName":"热销","remark":""},{"id":2,"typeName":"经济型","remark":""},{"id":3,"typeName":"舒适型","remark":""},{"id":4,"typeName":"suv","remark":""},{"id":5,"typeName":"商务车","remark":""},{"id":6,"typeName":"豪华车 ","remark":""},{"id":7,"typeName":"跑车","remark":""},{"id":8,"typeName":"小巴士","remark":""}]
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
         * typeName : 热销
         * remark :
         */

        private int id;
        private String typeName;
        private String remark;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTypeName() {
            return typeName;
        }

        public void setTypeName(String typeName) {
            this.typeName = typeName;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }
    }
}
