package com.jingwei.vega.rxhttp.retrofit;

import com.jingwei.vega.moudle.bean.BannerListBean;
import com.jingwei.vega.moudle.bean.BrandListBean;
import com.jingwei.vega.moudle.bean.CategoryBannerBean;
import com.jingwei.vega.moudle.bean.CategoryByOneBean;
import com.jingwei.vega.moudle.bean.CategoryByTwoBean;
import com.jingwei.vega.moudle.bean.DownloadRecordBean;
import com.jingwei.vega.moudle.bean.DownloadRecordDetailBean;
import com.jingwei.vega.moudle.bean.DynamicBean;
import com.jingwei.vega.moudle.bean.FocusBean;
import com.jingwei.vega.moudle.bean.GoodsLibBean;
import com.jingwei.vega.moudle.bean.MarketListBean;
import com.jingwei.vega.moudle.bean.MarketShopListBean;
import com.jingwei.vega.moudle.bean.MyCollectProductsBean;
import com.jingwei.vega.moudle.bean.ShopDetailBean;
import com.jingwei.vega.moudle.bean.ShopNewBean;
import com.jingwei.vega.moudle.bean.ShopNewRecommendBean;
import com.jingwei.vega.moudle.bean.ShopProductDetailBean;
import com.jingwei.vega.moudle.bean.UserInfoBean;
import com.jingwei.vega.moudle.bean.VegaInfoBean;
import com.jingwei.vega.moudle.bean.VipBean;
import com.jingwei.vega.moudle.bean.WXPayInfoBean;
import com.jingwei.vega.rxhttp.rxjava.RxMoudle;

import java.util.List;
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
     * 品牌列表
     */
    @GET("brand/list")
    Observable<RxMoudle<BrandListBean>> getBrand(@QueryMap Map<String, String> params);

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
     * 商品分类轮播图
     */
    @GET("category/adv")
    Observable<RxMoudle<CategoryBannerBean>> getCategoryBanner();

    /**
     * 商品二三级分类
     */
    @GET("category/{parentId}/list")
    Observable<RxMoudle<CategoryByTwoBean>> getCategoryByTwo(@Path("parentId") String parentId);

    /**
     * 关注列表
     */
    @GET("love/list")
    Observable<RxMoudle<FocusBean>> getFocusList(@QueryMap Map<String, String> params);

    /**
     * 动态列表
     */
    @GET("love/dynamic")
    Observable<RxMoudle<DynamicBean>> getDynamicList(@QueryMap Map<String, String> params);

    /**
     * 下载接口
     */
    @Multipart
    @POST("download_history/save")
    Observable<RxMoudle<Object>> dowload(@PartMap Map<String, RequestBody> params);

    /**
     * 市场商铺列表
     */
    @GET("supplier/list")
    Observable<RxMoudle<MarketShopListBean>> postMarketShopList(@QueryMap Map<String, String> params);

    /**
     * 商铺关注或取消关注
     */
    @GET("love/add/{supplierId}")
    Observable<RxMoudle<Object>> updateLoveShopState(@Path("supplierId") String supplierId);

    /**
     * 商铺详情列表
     */
    @GET("supplier/detail/{id}")
    Observable<RxMoudle<ShopDetailBean>> getShopDetail(@Path("id") String shopId);

    /**
     * 商铺新品推荐
     */
    @GET("product/list")
    Observable<RxMoudle<ShopNewRecommendBean>> getShopNewRecommend(@QueryMap Map<String, String> params);

    /**
     * 商铺最新商品
     */
    @GET("product/list")
    Observable<RxMoudle<ShopNewBean>> getShopNew(@QueryMap Map<String, String> params);

    /**
     * 商品详情
     */
    @GET("product/detail/{id}")
    Observable<RxMoudle<ShopProductDetailBean>> getShopProductDetail(@Path("id") String productId);

    /**
     * 商品收藏或取消收藏
     */
    @GET("collect/{productId}/save")
    Observable<RxMoudle<Object>> updateSaveProductState(@Path("productId") String productId);

    /**
     * 获取我的个人信息
     */
    @GET("user/info")
    Observable<RxMoudle<UserInfoBean>> getUserInfo();

    /**
     * 我的收藏列表
     */
    @GET("collect/list")
    Observable<RxMoudle<MyCollectProductsBean>> getMyCollectList(@QueryMap Map<String, String> params);

    /**
     * 我的收藏列表取消收藏
     */
    @GET("collect/{id}/delete")
    Observable<RxMoudle<Object>> deleteSaveProductState(@Path("id") String id);

    /**
     * 关于vega
     */
    @GET("about/info")
    Observable<RxMoudle<VegaInfoBean>> getVegaInfo();

    /**
     * 用户忘记密码
     */
    @Multipart
    @POST("user/forget_password")
    Observable<RxMoudle<Object>> userResetPwd(@PartMap Map<String, RequestBody> params);

    /**
     * 用户修改密码
     */
    @Multipart
    @POST("user/update_password")
    Observable<RxMoudle<Object>> userUpdatePwd(@PartMap Map<String, RequestBody> params);

    /**
     * 修改用户信息
     */
    @Multipart
    @POST("user/update")
    Observable<RxMoudle<Object>> userUpdate(@PartMap Map<String, RequestBody> params);

    /**
     * 修改用户头像
     */
    @Multipart
    @POST("user/update_headimg")
    Observable<RxMoudle<Object>> userHeadIconUpdate(@PartMap Map<String, RequestBody> params);

    /**
     * 获取vip权益列表
     */
    @GET("user/vip_list")
    Observable<RxMoudle<VipBean>> getVipList();

    /**
     * 获取下载记录列表
     */
    @GET("download_history/list")
    Observable<RxMoudle<DownloadRecordBean>> getDownloadRecord(@QueryMap Map<String, String> params);

    /**
     * 获取下载记录详情
     */
    @GET("download_history/detail/{id}")
    Observable<RxMoudle<DownloadRecordDetailBean>> getDownloadRecordDetail(@Path("id") String id);

    /**
     * 获取微信支付信息
     */
    @Multipart
    @POST("download_history/detail/{id}")
    Observable<RxMoudle<WXPayInfoBean>> getWXPayInfo(@PartMap Map<String, RequestBody> params);
}
