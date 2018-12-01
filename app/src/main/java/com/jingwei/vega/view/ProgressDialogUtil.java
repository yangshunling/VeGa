package com.jingwei.vega.view;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * Loading进度条
 */

public class ProgressDialogUtil {

    private static ProgressDialog sDialog;

    /**
     * 创建ProgressBarDialog（默认）
     *
     * @param mContext
     * @return
     */
    public static ProgressDialog creatProgressBarDialog(Context mContext) {
        sDialog = new ProgressDialog(mContext);
        sDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);//设置风格为圆形进度条
        sDialog.setTitle("");
        sDialog.setMessage("数据请求中...");
        sDialog.setIndeterminate(false);
        sDialog.setCancelable(false);
        return sDialog;
    }

    /**
     * 创建ProgressBarDialog（自定义内容）
     *
     * @param mContext
     * @return
     */
    public static ProgressDialog creatProgressBarDialog(Context mContext, String msg) {
        sDialog = new ProgressDialog(mContext);
        sDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);//设置风格为圆形进度条
        sDialog.setTitle("");
        sDialog.setMessage(msg);
        sDialog.setIndeterminate(false);
        sDialog.setCancelable(false);
        return sDialog;
    }
}
