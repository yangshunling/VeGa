package com.jingwei.vega.rxhttp.rxjava;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.jingwei.vega.R;
import com.jingwei.vega.view.ProgressDialogUtil;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import rx.Subscriber;

/**
 * RxSubscriber的封装
 */

public abstract class RxSubscriber<T> extends Subscriber<T> {
    private Context mContext;
    private ProgressDialog mDialog;
    private boolean isShow = false;

    private static long toastTime;

    /**
     * 不带有进度条
     */
    public RxSubscriber(Context mContext) {
        this.mContext = mContext;
        if (mDialog == null) {
            mDialog = ProgressDialogUtil.creatProgressBarDialog(mContext);
        }
    }

    /**
     * 带有进度条
     */
    public RxSubscriber(Context mContext, boolean isShow) {
        this.mContext = mContext;
        this.isShow = isShow;
        if (mDialog == null) {
            mDialog = ProgressDialogUtil.creatProgressBarDialog(mContext);
        }
    }

    /**
     * 带有进度条，自定义title内容
     */
    public RxSubscriber(Context mContext, String msg) {
        this.mContext = mContext;
        this.isShow = true;
        if (mDialog == null) {
            mDialog = ProgressDialogUtil.creatProgressBarDialog(mContext, msg);
        }
    }

    /**
     * 显示进度条
     */
    @Override
    public void onStart() {
        if (isShow) {
            mDialog.show();
        }
    }

    /**
     * 关闭进度条
     */
    @Override
    public void onCompleted() {
        mDialog.dismiss();
    }

    /**
     * 网络请求发生错误的处理
     *
     * @param e
     */
    @Override
    public void onError(Throwable e) {
        //判断当前吐司时间与上一次吐司间隔，小于2秒则不做吐司
        if (e instanceof SocketTimeoutException) {
            Toast.makeText(mContext, R.string.timeout, Toast.LENGTH_SHORT).show();
        } else if (e instanceof ConnectException) {
            Toast.makeText(mContext, R.string.server, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_SHORT).show();
            Log.v("TAG", "灵异事件：" + e.getMessage());
        }
        mDialog.dismiss();
    }

    /**
     * onNext是对外实现的方法，所以一定是抽象类
     *
     * @param t
     */
    @Override
    public abstract void onNext(T t);
}
