package com.sam.globalRentalCar.http.request;

/**
 * desc   : 用户登录需要传递的参数
 */
public class LoginRequestBean {


    private String ip;

    private String phone;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getVerifcationCode() {
        return verifcationCode;
    }

    public void setVerifcationCode(String verifcationCode) {
        this.verifcationCode = verifcationCode;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    private String requestId;

    private String verifcationCode;

    private String userPwd;

}