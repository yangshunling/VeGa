package com.jingwei.vega.rxhttp.rxjava;


import com.jingwei.vega.rxhttp.exception.RxApiException;

import rx.functions.Func1;

/**
 * Created by Anonymous on 2017/4/6.
 */

public class RxResultFunc<T> implements Func1<RxMoudle<T>, T> {

    @Override
    public T call(RxMoudle<T> httpResult) {
        if (httpResult.getCode() != 0) {
            throw new RxApiException(httpResult.getCode(),httpResult.getMassage());
        }
        return httpResult.getData();
    }
}

