package com.jingwei.vega.rxhttp.retrofit;

import com.jingwei.vega.rxhttp.rxjava.RxMoudle;

import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;
import rx.Observable;

/**
 * Retrofit API接口
 */

public interface RetrofitAPI {

    /**
     * 用户登录
     */
    @Multipart
    @POST("index/agency/login")
    Observable<RxMoudle<String>> userLogin(@PartMap Map<String, RequestBody> params);
}
