package com.jingwei.vega.rxhttp.rxjava;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Anonymous on 2017/8/22.
 */

public class RxObservable<T> extends Observable<T> implements Observable.Transformer<RxMoudle<T>, T> {
    protected RxObservable(OnSubscribe<T> f) {
        super(f);
    }

    @Override
    public Observable<T> call(Observable<RxMoudle<T>> rxMoudleObservable) {
        return rxMoudleObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new RxResultFunc<T>());
    }
}

