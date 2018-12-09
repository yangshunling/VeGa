package com.jingwei.vega.rxhttp.rxjava;

/**
 * Created by liukun on 16/3/5.
 */
public class RxMoudle<T> {


    /**
     * success : TRUE
     * code : Y00-000200
     * message : SUCCESS
     * data : {}
     * succeed : true
     * failed : false
     */

    private String success;
    private String code;
    private String message;
    private T data;
    private boolean succeed;
    private boolean failed;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isSucceed() {
        return succeed;
    }

    public void setSucceed(boolean succeed) {
        this.succeed = succeed;
    }

    public boolean isFailed() {
        return failed;
    }

    public void setFailed(boolean failed) {
        this.failed = failed;
    }
}
