package com.sam.rentalcar.http.net;

/**
 * author:sam
 * time:2020/12/19
 * desc:
 * version:1.0
 */
public class BaseResponse {

    /**
     * success : false
     * data : null
     * msg : 校验登录失败
     * code : 401
     * traceId : null
     */

    private boolean success;
    private Object data;
    private String msg;
    private String code;
    private Object traceId;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
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
