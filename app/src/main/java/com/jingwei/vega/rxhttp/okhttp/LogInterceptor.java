package com.jingwei.vega.rxhttp.okhttp;

import com.orhanobut.logger.Logger;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;

/**
 * OkHttp 日志拦截器
 */

public class LogInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        //请求体
        Request request = chain.request();
        //返回参数
        Response response = chain.proceed(chain.request());
        MediaType mediaType = response.body().contentType();
        String content = response.body().string();
        if ("POST".equals(request.method())) {
            StringBuilder sb = new StringBuilder();
            if (request.body() instanceof FormBody) {
                FormBody body = (FormBody) request.body();
                for (int i = 0; i < body.size(); i++) {
                    sb.append(body.encodedName(i) + "=" + body.encodedValue(i) + "\n");
                }
                sb.delete(sb.length() - 1, sb.length());
                Logger.i("请求参数体:\n" + sb.toString());
            }
        }
        Logger.v("请求地址:\n" + response.request().url().toString() + "\n" + "返回参数:\n" + content);
        Logger.json(content);
        return response.newBuilder()
                .body(okhttp3.ResponseBody.create(mediaType, content))
                .build();
    }
}