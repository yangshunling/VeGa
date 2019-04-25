package com.jingwei.vega.moudle.bean;

import com.google.gson.annotations.SerializedName;

public class WXPayInfoBean {
    /**
     * package : Sign=WXPay
     * success : true
     * appid : wx06169c46c9a24a2c
     * sign : 2D5FB1A269E884062224283868175509
     * partnerid : 1530435321
     * prepayid : wx25194144121897ff9da9ca6c3981710957
     * noncestr : Nwky23Sp3tactoZp
     * timestamp : 1556192504
     */

    @SerializedName("package")
    private String packageX;
    private String success;
    private String appid;
    private String sign;
    private String partnerid;
    private String prepayid;
    private String noncestr;
    private String timestamp;

    public String getPackageX() {
        return packageX;
    }

    public void setPackageX(String packageX) {
        this.packageX = packageX;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getPartnerid() {
        return partnerid;
    }

    public void setPartnerid(String partnerid) {
        this.partnerid = partnerid;
    }

    public String getPrepayid() {
        return prepayid;
    }

    public void setPrepayid(String prepayid) {
        this.prepayid = prepayid;
    }

    public String getNoncestr() {
        return noncestr;
    }

    public void setNoncestr(String noncestr) {
        this.noncestr = noncestr;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
