package com.jingwei.vega.activity;

import android.widget.ListView;

import com.jingwei.vega.R;
import com.jingwei.vega.adapter.ProductDetailAdapter;
import com.jingwei.vega.base.BaseActivity;
import com.jingwei.vega.utils.ListViewUtil;
import com.jingwei.vega.view.GlideImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by cxc on 2018/12/5.
 * 商品详情界面
 */

public class ShopProductDetailActivity extends BaseActivity{

    @BindView(R.id.banner)
    Banner mBanner;

    @BindView(R.id.lv_product_detail)
    ListView mListView;

    private ProductDetailAdapter mProductDetailAdapter;

    private List<String> bigPicBannerList = new ArrayList<>();

    private List<String> productDetailList = new ArrayList<>();

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
    }

    @Override
    public void initData() {
        bigPicBannerList.add("http://life.southmoney.com/tuwen/UploadFiles_6871/201801/20180129110733180.jpg");
        bigPicBannerList.add("http://imgsrc.baidu.com/imgad/pic/item/37d12f2eb9389b50768d956e8e35e5dde7116e9f.jpg");
        bigPicBannerList.add("http://image.fvideo.cn/uploadfile/2015/06/04/img28567642935153.jpg");
        bigPicBannerList.add("http://pic1.win4000.com/tj/2018-09-27/5baca186a6da0.jpg");
        bigPicBannerList.add("http://imgsrc.baidu.com/imgad/pic/item/5366d0160924ab18dcb75d0a3efae6cd7b890b6d.jpg");
        bigPicBannerList.add("http://imgsrc.baidu.com/imgad/pic/item/03087bf40ad162d96101d0211adfa9ec8a13cd24.jpg");
        bigPicBannerList.add("http://n.sinaimg.cn/sinacn00/453/w593h660/20181012/0fac-hkrzvkw6887569.jpg");
        bigPicBannerList.add("http://pic.51yuansu.com/pic3/cover/01/26/61/59230a63c4f9f_610.jpg");
        bigPicBannerList.add("http://image1.miss-no1.com/uploadfile/2016/02/29/img59104395993189.jpg");

        productDetailList.add("http://life.southmoney.com/tuwen/UploadFiles_6871/201801/20180129110733180.jpg");
        productDetailList.add("http://imgsrc.baidu.com/imgad/pic/item/37d12f2eb9389b50768d956e8e35e5dde7116e9f.jpg");
        productDetailList.add("http://image.fvideo.cn/uploadfile/2015/06/04/img28567642935153.jpg");
        productDetailList.add("http://pic1.win4000.com/tj/2018-09-27/5baca186a6da0.jpg");
        productDetailList.add("http://imgsrc.baidu.com/imgad/pic/item/5366d0160924ab18dcb75d0a3efae6cd7b890b6d.jpg");
        productDetailList.add("http://imgsrc.baidu.com/imgad/pic/item/03087bf40ad162d96101d0211adfa9ec8a13cd24.jpg");
        productDetailList.add("http://n.sinaimg.cn/sinacn00/453/w593h660/20181012/0fac-hkrzvkw6887569.jpg");
        productDetailList.add("http://pic.51yuansu.com/pic3/cover/01/26/61/59230a63c4f9f_610.jpg");
        productDetailList.add("http://image1.miss-no1.com/uploadfile/2016/02/29/img59104395993189.jpg");

        initBanner();

        initListView();
    }

    private void initListView() {
        mProductDetailAdapter = new ProductDetailAdapter(ShopProductDetailActivity.this, productDetailList);
        mListView.setAdapter(mProductDetailAdapter);
        ListViewUtil.setListViewHeightBasedOnChildren(mListView);
    }

    private void initBanner() {
        //设置banner样式
        mBanner.setBannerStyle(BannerConfig.NUM_INDICATOR);
        //设置图片加载器
        mBanner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        mBanner.setImages(bigPicBannerList);
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
}
