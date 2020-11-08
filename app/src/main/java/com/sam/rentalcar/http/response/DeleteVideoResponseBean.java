package com.sam.rentalcar.http.response;

/**
 * author:sam
 * time:2020/11/02
 * desc:  删除视频返回数据
 * version:1.0
 */
public class DeleteVideoResponseBean {


    /**
     * success : true
     * data : 删除成功
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
