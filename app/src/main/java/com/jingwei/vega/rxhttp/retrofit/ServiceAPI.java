package com.jingwei.vega.rxhttp.retrofit;

import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;
import com.jingwei.vega.Constants;
import com.jingwei.vega.base.MyApplication;
import com.jingwei.vega.rxhttp.https.SSLSocketClient;
import com.jingwei.vega.rxhttp.okhttp.LogInterceptor;
import com.jingwei.vega.rxhttp.okhttp.TokenInterceptor;
import com.jingwei.vega.utils.PreferencesUtil;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/*
 * APIService接口放置
 */

public class ServiceAPI {

    private static RetrofitAPI retrofitAPI;


    /**
     * 创建Retrofit
     *
     * @return
     */
    public static RetrofitAPI Retrofit() {
        if (retrofitAPI == null) {
            retrofitAPI = new Retrofit.Builder()
                    .baseUrl(Constants.HOST)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .client(getOkhttpClient())
                    .build()
                    .create(RetrofitAPI.class);
        }
        return retrofitAPI;
    }

    /**
     * 基本配置
     *
     * @return
     */
    public static OkHttpClient getOkhttpClient() {
        return new OkHttpClient().newBuilder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .addInterceptor(new LogInterceptor())
                .sslSocketFactory(SSLSocketClient.getSSLSocketFactory(),SSLSocketClient.getTrustManager())
                .hostnameVerifier(SSLSocketClient.getHostnameVerifier())
                .cookieJar(new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(MyApplication.getContext())))
                .build();
    }

    /**
     * 下载文件配置
     *
     * @return
     */
    public static OkHttpClient getOkhttpClientVerifier() {
        return new OkHttpClient().newBuilder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .sslSocketFactory(SSLSocketClient.getSSLSocketFactory(),SSLSocketClient.getTrustManager())
                .hostnameVerifier(SSLSocketClient.getHostnameVerifier())
                .build();
    }
}
