package com.jingwei.vega.rxhttp.rxjava;

/**
 * Created by liukun on 16/3/5.
 */
public class RxMoudle<T> {

    private String message;
    private int code;
    private T data;

    public String getMassage() {
        return message;
    }

    public void setMassage(String massage) {
        this.message = massage;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
