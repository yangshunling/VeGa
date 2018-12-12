package com.jingwei.vega.rxhttp.retrofit;

import com.jingwei.vega.moudle.bean.BannerListBean;
import com.jingwei.vega.moudle.bean.CategoryByOneBean;
import com.jingwei.vega.moudle.bean.CategoryByTwoBean;
import com.jingwei.vega.moudle.bean.GoodsLibBean;
import com.jingwei.vega.moudle.bean.MarketListBean;
import com.jingwei.vega.moudle.bean.MarketShopListBean;
import com.jingwei.vega.rxhttp.rxjava.RxMoudle;

import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;
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

    /**
     * 市场列表
     */
    @GET("index/market")
    Observable<RxMoudle<MarketListBean>> getMarketList();

    /**
     * 商品库列表
     */
    @GET("product/list")
    Observable<RxMoudle<GoodsLibBean>> getGoodsLibList(@QueryMap Map<String, String> params);

    /**
     * 商品一级分类
     */
    @GET("category/root_list")
    Observable<RxMoudle<CategoryByOneBean>> getCategoryByOne();

    /**
     * 商品二三级分类
     */
    @GET("category/{parentId}/list")
    Observable<RxMoudle<CategoryByTwoBean>> getCategoryByTwo(@Path("parentId") String parentId);

    /********************************* chengxc******************************/

    /**
     * 市场商铺列表
     */
    @GET("supplier/list")
    Observable<RxMoudle<MarketShopListBean>> postMarketShopList(@QueryMap Map<String, String> params);





}
