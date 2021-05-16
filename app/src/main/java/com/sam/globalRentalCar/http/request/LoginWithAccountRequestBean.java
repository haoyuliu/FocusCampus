package com.sam.globalRentalCar.http.request;

/**
 * 账号密码登录的请求
 */
public class LoginWithAccountRequestBean {


    /**
     * account : 15065157316
     * ipAddress : string
     * pwd : 111
     * verifcationCode :
     */

    private String account;
    private String ipAddress;
    private String pwd;
    private String verifcationCode;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getVerifcationCode() {
        return verifcationCode;
    }

    public void setVerifcationCode(String verifcationCode) {
        this.verifcationCode = verifcationCode;
    }
}
