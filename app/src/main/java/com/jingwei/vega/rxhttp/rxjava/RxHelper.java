package com.jingwei.vega.rxhttp.rxjava;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @Description: VeGa
 * @Author: Anonymous
 * @Time: 2019-05-09 14:52
 */
public class RxHelper {

    public static <T>Observable<T> observer(Observable<RxMoudle<T>> observable){
        return observable
                .map(new RxResultFunc<>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
