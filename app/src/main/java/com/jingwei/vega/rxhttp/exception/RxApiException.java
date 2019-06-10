package com.jingwei.vega.rxhttp.exception;


import android.util.Log;

import com.jingwei.vega.moudle.TokenLose;

import de.greenrobot.event.EventBus;

/**
 * Created by Anonymous on 17/3/10.
 */
public class RxApiException extends RuntimeException {

    public RxApiException(String resultCode, String message) {
        this(getRxExceptionMessage(resultCode, message));
    }

    public RxApiException(String detailMessage) {
        super(detailMessage);
    }

    /**
     * 对服务器传递过来的特殊信息进行特殊处理
     *
     * @param code
     * @return
     */
    private static String getRxExceptionMessage(String code, String message) {
        Log.v("vage", code+"");
        if (code.equals("Y00-000403")) {
            //Token过期，需要重新登录
            Log.v("vage", code+"---");
            EventBus.getDefault().post(new TokenLose());
        }
        return message;
    }
}

