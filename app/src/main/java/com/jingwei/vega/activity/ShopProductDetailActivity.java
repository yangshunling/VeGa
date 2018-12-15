package com.jingwei.vega.activity;

import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.jingwei.vega.Constants;
import com.jingwei.vega.R;
import com.jingwei.vega.adapter.ProductDetailAdapter;
import com.jingwei.vega.base.BaseActivity;
import com.jingwei.vega.moudle.bean.ShopNewBean;
import com.jingwei.vega.moudle.bean.ShopProductDetailBean;
import com.jingwei.vega.rxhttp.retrofit.ServiceAPI;
import com.jingwei.vega.rxhttp.rxjava.RxResultFunc;
import com.jingwei.vega.rxhttp.rxjava.RxSubscriber;
import com.jingwei.vega.utils.ListViewUtil;
import com.jingwei.vega.view.GlideImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by cxc on 2018/12/5.
 * 商品详情界面
 */

public class ShopProductDetailActivity extends BaseActivity {

    @BindView(R.id.banner)
    Banner mBanner;

    @BindView(R.id.lv_product_detail)
    ListView mListView;

    @BindView(R.id.iv_iscollect)
    ImageView mIvIscollect;//收藏按钮

    @BindView(R.id.tv_product_name)
    TextView mTvProductName;//商品名称

    @BindView(R.id.tv_product_price)
    TextView mTvProductPrice;//商品价格

    @BindView(R.id.tv_shop_name)
    TextView mTvShopName;//商铺名称

    private String pid = "";

    private ProductDetailAdapter mProductDetailAdapter;

    private ShopProductDetailBean mShopProductDetailBean;

    //轮播图
    private List<String> bannerPics = new ArrayList<>();

    //商品详情土
    private List<String> productDetailPics = new ArrayList<>();

    @Override
    public int getContentView() {
        return R.layout.activity_shop_product_detail;
    }

    @Override
    public void initTitleBar() {
        hintTitleBar();
        //沉浸式图片
        setTransparent();
    }

    @Override
    public void initView() {
        //抢占焦点
        mBanner.requestFocus();
        mBanner.setFocusableInTouchMode(true);
        pid = getIntent().getStringExtra("id");
    }

    @Override
    public void initData() {
        ServiceAPI.Retrofit().getShopProductDetail(pid + "")
                .map(new RxResultFunc<ShopProductDetailBean>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<ShopProductDetailBean>(ShopProductDetailActivity.this,true) {
                    @Override
                    public void onNext(ShopProductDetailBean bean) {
                        mShopProductDetailBean = bean;

                        if (mShopProductDetailBean.getDetail().getIconImageList().size() > 0) {
                            for (int i = 0; i < mShopProductDetailBean.getDetail().getIconImageList().size(); i++) {
                                bannerPics.add(Constants.IMAGEHOST+mShopProductDetailBean.getDetail().getIconImageList().get(i).getPath());
                            }
                        }

                        if(mShopProductDetailBean.getDetail().getPicturesList().size()>0){
                            for (int i = 0; i < mShopProductDetailBean.getDetail().getPicturesList().size(); i++) {
                                productDetailPics.add(Constants.IMAGEHOST+mShopProductDetailBean.getDetail().getPicturesList().get(i).getPath());
                            }
                        }

                        updateView();

                        initBanner();

                        initListView();
                    }
                });
    }

    //其余控件显示
    private void updateView() {
        //收藏
        mIvIscollect.setBackground(mShopProductDetailBean.getDetail().isIsCollect()?
                getResources().getDrawable(R.drawable.icon_iscollect):getResources().getDrawable(R.drawable.icon_uncollect));

        //商品名称
        mTvProductName.setText(mShopProductDetailBean.getDetail().getName());

        //商品价格
        mTvProductPrice.setText("￥"+mShopProductDetailBean.getDetail().getPrice());

        //商铺名称
        mTvShopName.setText(mShopProductDetailBean.getDetail().getSupplierName());
    }

    private void initBanner() {
        //设置banner样式
        mBanner.setBannerStyle(BannerConfig.NUM_INDICATOR);
        //设置图片加载器
        mBanner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        mBanner.setImages(bannerPics);
        //设置banner动画效果
        mBanner.setBannerAnimation(Transformer.DepthPage);
        //设置自动轮播，默认为true
        mBanner.isAutoPlay(true);
        //设置轮播时间
        mBanner.setDelayTime(2500);
        //设置指示器位置（当banner模式中有指示器时）
        mBanner.setIndicatorGravity(BannerConfig.RIGHT);
        //banner设置方法全部调用完毕时最后调用
        mBanner.start();
    }

    private void initListView() {
        mProductDetailAdapter = new ProductDetailAdapter(ShopProductDetailActivity.this, productDetailPics);
        mListView.setAdapter(mProductDetailAdapter);
        ListViewUtil.setListViewHeightBasedOnChildren(mListView);
    }
}
