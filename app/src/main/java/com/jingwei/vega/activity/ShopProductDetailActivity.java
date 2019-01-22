package com.jingwei.vega.activity;

import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jingwei.vega.Constants;
import com.jingwei.vega.R;
import com.jingwei.vega.adapter.ProductDetailAdapter;
import com.jingwei.vega.base.BaseActivity;
import com.jingwei.vega.moudle.bean.ShopNewBean;
import com.jingwei.vega.moudle.bean.ShopProductDetailBean;
import com.jingwei.vega.rxhttp.okhttp.DownloadUtil;
import com.jingwei.vega.rxhttp.retrofit.ParamBuilder;
import com.jingwei.vega.rxhttp.retrofit.ServiceAPI;
import com.jingwei.vega.rxhttp.rxjava.RxResultFunc;
import com.jingwei.vega.rxhttp.rxjava.RxSubscriber;
import com.jingwei.vega.utils.GlideUtil;
import com.jingwei.vega.utils.ListViewUtil;
import com.jingwei.vega.utils.TextUtil;
import com.jingwei.vega.view.GlideImageLoader;
import com.jingwei.vega.view.ProgressDialogUtil;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by cxc on 2018/12/5.
 * 商品详情界面
 */

public class ShopProductDetailActivity extends BaseActivity {

    @BindView(R.id.banner)
    Banner mBanner;

    @BindView(R.id.iv_left_finish)
    ImageView mIvLeftFinish;

    @BindView(R.id.rv_product_detail)
    RecyclerView mRecyclerView;

    @BindView(R.id.iv_iscollect)
    ImageView mIvIscollect;//收藏按钮

    @BindView(R.id.tv_product_price)
    TextView mTvProductPrice;//商品价格

    @BindView(R.id.iv_shop_icon)
    ImageView mIvShopIcon;//商品图标

    @BindView(R.id.tv_shop_name)
    TextView mTvShopName;//商铺名称

    @BindView(R.id.iv_image)
    ImageView mIvImage;//店铺图标

    @BindView(R.id.tv_remark)
    TextView mTvRemark;//商品介绍

    private String pid = "";

    private ShopProductDetailBean mShopProductDetailBean;

    //轮播图
    private List<String> bannerPics = new ArrayList<>();

    //商品详情图
    private List<String> productDetailPics = new ArrayList<>();

    private ShopProductDetailAdapter mShopProductDetailAdapter;

    private DecimalFormat df = new DecimalFormat("0.0");

    private ProgressDialog mBar;
    private int mImgCount = 0;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    mBar.setMessage("正在保存：第 " + msg.obj + "/" + productDetailPics.size() + " 张");
                    break;
                case 1:
                    mBar.setMessage("正在保存：第 0/" + productDetailPics.size() + " 张");
                    mBar.dismiss();
                    showToast("图片保存成功");
                    mImgCount = 0;
                    break;
                case 2:
                    mBar.dismiss();
                    showToast("图片保存失败");
                    mImgCount = 0;
                    break;
            }
        }
    };

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

        mShopProductDetailAdapter = new ShopProductDetailAdapter(R.layout.item_product_detail_image, productDetailPics);
        mShopProductDetailAdapter.setEmptyView(getEmptyView());
        mRecyclerView.setAdapter(mShopProductDetailAdapter);
        mRecyclerView.setLayoutManager(new GridLayoutManager(ShopProductDetailActivity.this, 3));

        mBar = ProgressDialogUtil.creatProgressBarDialog(ShopProductDetailActivity.this);
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
                            //只显示一张
                            bannerPics.add(Constants.IMAGEHOST+mShopProductDetailBean.getDetail().getIconImageList().get(0).getPath());
                        }

                        if(mShopProductDetailBean.getDetail().getPicturesList().size()>0){
                            for (int i = 0; i < mShopProductDetailBean.getDetail().getPicturesList().size(); i++) {
                                productDetailPics.add(Constants.IMAGEHOST+mShopProductDetailBean.getDetail().getPicturesList().get(i).getPath());
                            }
                        }
                        updateView();

                        initBanner();

                        //刷新显示
                        mShopProductDetailAdapter.replaceData(productDetailPics);
                    }
                });
    }

    //其余控件显示
    private void updateView() {
        //收藏
        mIvIscollect.setBackground(mShopProductDetailBean.getDetail().isIsCollect()?
                getResources().getDrawable(R.drawable.icon_iscollect):getResources().getDrawable(R.drawable.icon_uncollect));

        //商品价格
        mTvProductPrice.setText("￥"+df.format(mShopProductDetailBean.getDetail().getPrice()));

        //商铺名称
        mTvShopName.setText(mShopProductDetailBean.getDetail().getSupplierName());

        //店铺图标
        GlideUtil.setCircleImage(ShopProductDetailActivity.this,
                Constants.IMAGEHOST+mShopProductDetailBean.getDetail().getSupplierLogo(),mIvImage);

        //商品介绍
        mTvRemark.setText(mShopProductDetailBean.getDetail().getRemark());
    }

    private void initBanner() {
        //设置banner样式
//        mBanner.setBannerStyle(BannerConfig.NUM_INDICATOR);
        mBanner.setBannerStyle(BannerConfig.CENTER);
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

    @OnClick({R.id.iv_left_finish,R.id.iv_iscollect,R.id.bt_save,R.id.bt_copy,R.id.iv_shop_icon,R.id.tv_shop_name,R.id.iv_image})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_left_finish:
                finish();
                break;

            case R.id.iv_iscollect://更改当前收藏状态
                updateSaveProductState();
                break;

            case R.id.bt_save://存图
                //下载
                ServiceAPI.Retrofit().dowload(ParamBuilder.newBody()
                        .addBody("productId", mShopProductDetailBean.getDetail().getId() + "")
                        .bulidBody())
                        .map(new RxResultFunc<Object>())
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new RxSubscriber<Object>(ShopProductDetailActivity.this) {
                            @Override
                            public void onNext(Object bean) {

                            }
                        });
                //存图
                if (productDetailPics != null && productDetailPics.size() != 0) {
                    mBar.setMessage("正在保存：第 0/" + productDetailPics.size() + " 张");
                    mBar.show();
                    for (int i = 0; i < productDetailPics.size(); i++) {
                        downloadImage(i);
                    }
                }

                break;

            case R.id.bt_copy://文案复制
                ClipboardManager cm = (ClipboardManager) ShopProductDetailActivity.this.getSystemService(Context.CLIPBOARD_SERVICE);
                // 创建普通字符型ClipData
                ClipData mClipData = ClipData.newPlainText("Label", mShopProductDetailBean.getDetail().getRemark());
                // 将ClipData内容放到系统剪贴板里。
                cm.setPrimaryClip(mClipData);
                showToast("复制成功");
                break;

            case R.id.iv_shop_icon:
                finish();
                break;

            case R.id.tv_shop_name:
                finish();
                break;

            case R.id.iv_image:
                finish();
                break;
        }
    }

    private void downloadImage(int index) {

        String fileUrl = productDetailPics.get(index);
        String filePath = Constants.IMAGEPATH;
        String fileName = System.currentTimeMillis() + ".jpg";

        DownloadUtil.get().download(fileUrl, filePath, fileName, new DownloadUtil.OnDownloadListener() {
            @Override
            public void onDownloadSuccess(File file) {
                Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                Uri uri = Uri.fromFile(file);
                intent.setData(uri);
                ShopProductDetailActivity.this.sendBroadcast(intent);
                //计数
                mImgCount++;
                mHandler.sendMessage(mHandler.obtainMessage(0, mImgCount));
                if (mImgCount == productDetailPics.size()) {
                    mHandler.sendMessage(mHandler.obtainMessage(1, "图片保存成功"));
                }
            }

            @Override
            public void onDownloading(int progress) {
                Log.v("TAG", "进度：" + progress);
            }

            @Override
            public void onDownloadFailed(Exception e) {
                mHandler.sendMessage(mHandler.obtainMessage(2, "下载失败：" + e.getMessage()));
            }
        });
    }


    private void updateSaveProductState() {
        ServiceAPI.Retrofit().updateSaveProductState(mShopProductDetailBean.getDetail().getId() + "")
                .map(new RxResultFunc<Object>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<Object>(ShopProductDetailActivity.this,mShopProductDetailBean.getDetail().isCollect()?"正在取消收藏...":"正在收藏...") {
                    @Override
                    public void onNext(Object message) {
                        mShopProductDetailBean.getDetail().setCollect(!mShopProductDetailBean.getDetail().isCollect());
                        mIvIscollect.setBackground(mShopProductDetailBean.getDetail().isIsCollect()?
                                getResources().getDrawable(R.drawable.icon_iscollect):getResources().getDrawable(R.drawable.icon_uncollect));
                    }
                });
    }

    //商铺详情图片展示
    public class ShopProductDetailAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

        public ShopProductDetailAdapter(int layoutResId, List data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {
            GlideUtil.setImage(ShopProductDetailActivity.this,item, (ImageView) helper.getView(R.id.iv_product_detail_image));
        }
    }
}
