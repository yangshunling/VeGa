package com.jingwei.vega.rxhttp.retrofit;

import com.jingwei.vega.moudle.bean.BannerListBean;
import com.jingwei.vega.rxhttp.rxjava.RxMoudle;

import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;
import rx.Observable;

/**
 * Retrofit API接口
 */

public interface RetrofitAPI {

    /********************************* Anonymous******************************/

    /**
     * 用户登录
     */
    @Multipart
    @POST("user/login")
    Observable<RxMoudle<Object>> userLogin(@PartMap Map<String, RequestBody> params);

    /**
     * 发验证码
     */
    @Multipart
    @POST("mobile/send")
    Observable<RxMoudle<Object>> sendCode(@PartMap Map<String, RequestBody> params);

    /**
     * 用户注册
     */
    @Multipart
    @POST("user/register")
    Observable<RxMoudle<Object>> userRegist(@PartMap Map<String, RequestBody> params);

    /**
     * 首页轮播图
     */
    @GET("index/adv")
    Observable<RxMoudle<BannerListBean>> getBanner();


    /********************************* chengxc******************************/






}
