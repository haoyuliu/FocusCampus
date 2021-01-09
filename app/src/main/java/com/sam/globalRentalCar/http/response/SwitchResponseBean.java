package com.sam.globalRentalCar.http.response;

/**
 * author:sam
 * time:2020/11/02
 * desc:  开关返回数据
 * version:1.0
 */
public class SwitchResponseBean {


    /**
     * code : string
     * data : true
     * msg : string
     * success : true
     * traceId : string
     */

    private String code;
    private boolean data;
    private String msg;
    private boolean success;
    private String traceId;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean isData() {
        return data;
    }

    public void setData(boolean data) {
        this.data = data;
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
}
