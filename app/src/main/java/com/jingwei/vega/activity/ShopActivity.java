package com.jingwei.vega.activity;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.came.viewbguilib.ButtonBgUi;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jingwei.vega.Constants;
import com.jingwei.vega.R;
import com.jingwei.vega.base.BaseActivity;
import com.jingwei.vega.moudle.bean.GoodsLibBean;
import com.jingwei.vega.moudle.bean.ShopDetailBean;
import com.jingwei.vega.moudle.bean.ShopNewBean;
import com.jingwei.vega.moudle.bean.ShopNewRecommendBean;
import com.jingwei.vega.refresh.DefaultFooter;
import com.jingwei.vega.refresh.SpringView;
import com.jingwei.vega.rxhttp.retrofit.ParamBuilder;
import com.jingwei.vega.rxhttp.retrofit.ServiceAPI;
import com.jingwei.vega.rxhttp.rxjava.RxResultFunc;
import com.jingwei.vega.rxhttp.rxjava.RxSubscriber;
import com.jingwei.vega.utils.GlideUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by cxc on 2018/12/4.
 * 商铺界面
 */
public class ShopActivity extends BaseActivity {

    @BindView(R.id.iv_left_finish)
    ImageView mIvLeftFinish;

    /**
     * 商铺详情
     */
    @BindView(R.id.my_bg)
    ImageView mMyBg; //顶部背景图片
    @BindView(R.id.iv_image)
    ImageView mIvImage;//商铺logo
    @BindView(R.id.tv_name)
    TextView mTvName;//商铺名称
    @BindView(R.id.tv_xin)
    TextView mTvXin;//新品
    @BindView(R.id.tv_dian)
    TextView mTvDian;//店铺总商品
    @BindView(R.id.bt_save)
    ButtonBgUi mBtSave;//关注
    @BindView(R.id.bt_cancel)
    ButtonBgUi mBtCancel;//取消关注
    @BindView(R.id.tv_type)
    TextView mTvType;//主营类型
    @BindView(R.id.tv_content)
    TextView mTvContent;//描述

    @BindView(R.id.rv_new_recommend)
    RecyclerView mRvNewRecommend;//新品推荐

    @BindView(R.id.rv_new)
    RecyclerView mRvNew;//最新

    @BindView(R.id.spring)
    SpringView mSpring;

    @BindView(R.id.rl_shop)
    RelativeLayout mRlShop;

    @BindView(R.id.iv_change_price)
    ImageView mIvChangePrice;//价格排序

    private boolean isPriceUp = true;//true-降序  false-升序

    //新品推荐
    private List<ShopNewRecommendBean.PageListBean.ListBean> mNewRecommendList = new ArrayList<>();
    private NewRecommendAdapter mNewRecommendAdapter;

    //最新
    private List<ShopNewBean.PageListBean.ListBean> mNewList = new ArrayList<>();
    private NewAdapter mNewAdapter;

    private int shopId;

    private int pager = 1;
    private String sortBy = "new";//没有做排序时传new，价格升序->priceAsc|价格降序->priceDesc|人气->clickNumber

    @Override
    public int getContentView() {
        return R.layout.activity_shop;
    }

    @Override
    public void initTitleBar() {
        hintTitleBar();
        //沉浸式图片
        setTransparent();
    }

    @Override
    public void initView() {
        shopId = getIntent().getIntExtra("shopId", 0);

        //抢占焦点
        mRlShop.requestFocus();
        mRlShop.setFocusableInTouchMode(true);

//        mSpring.setHeader(new DefaultHeader(ShopActivity.this));
        mSpring.setFooter(new DefaultFooter(ShopActivity.this));

        mNewRecommendAdapter = new NewRecommendAdapter(R.layout.item_shop_detail, mNewRecommendList);
        mNewRecommendAdapter.setEmptyView(getEmptyView());
        mRvNewRecommend.setAdapter(mNewRecommendAdapter);
        mRvNewRecommend.setLayoutManager(new GridLayoutManager(ShopActivity.this, 3));

        mNewAdapter = new NewAdapter(R.layout.item_shop_detail, mNewList);
        mNewAdapter.setEmptyView(getEmptyView());
        mRvNew.setAdapter(mNewAdapter);
        mRvNew.setLayoutManager(new GridLayoutManager(ShopActivity.this, 2));
    }

    @Override
    public void initData() {
        getShopDetail();

        getNewRecommend();

        getNew();

        setListener();
    }

    /**
     * 获取商铺详情
     */
    private void getShopDetail() {
        ServiceAPI.Retrofit().getShopDetail(shopId + "")
                .map(new RxResultFunc<ShopDetailBean>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<ShopDetailBean>(ShopActivity.this) {
                    @Override
                    public void onNext(ShopDetailBean bean) {
                        //顶部背景图片
                        GlideUtil.setImage(ShopActivity.this, Constants.IMAGEHOST + bean.getDetail().getBackgroundPic(), mMyBg);
                        //商铺logo
                        GlideUtil.setCircleImage(ShopActivity.this, Constants.IMAGEHOST + bean.getDetail().getHeadImg(), mIvImage);
                        //商铺名称
                        mTvName.setText(bean.getDetail().getName());
                        //新品
                        mTvXin.setText(bean.getDetail().getNewProductNumber() + "");
                        //店铺总商品
                        mTvDian.setText(bean.getDetail().getProductNumber() + "");
                        //是否已关注
                        if (bean.getDetail().isIsLove()) {
                            mBtSave.setVisibility(View.GONE);
                            mBtCancel.setVisibility(View.VISIBLE);
                        } else {
                            mBtSave.setVisibility(View.VISIBLE);
                            mBtCancel.setVisibility(View.GONE);
                        }
                        //主营类型
                        mTvType.setText("主营类型：" + bean.getDetail().getMainProducts());
                        //描述
                        mTvContent.setText(bean.getDetail().getRemark());
                    }
                });
    }

    /**
     * 获取新品推荐
     */
    private void getNewRecommend() {
        ServiceAPI.Retrofit().getShopNewRecommend(ParamBuilder.newParams()
                .addParam("tagId", "9")//固定标签
                .addParam("supplierId", shopId + "")
                .bulidParam())
                .map(new RxResultFunc<ShopNewRecommendBean>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<ShopNewRecommendBean>(ShopActivity.this,true) {
                    @Override
                    public void onNext(ShopNewRecommendBean bean) {
                        mNewRecommendList.addAll(bean.getPageList().getList());
                        mNewRecommendAdapter.replaceData(mNewRecommendList);
                    }
                });

    }

    /**
     * 获取店铺产品
     */
    private void getNew(){
        ServiceAPI.Retrofit().getShopNew(ParamBuilder.newParams()
                .addParam("supplierId", shopId + "")
                .addParam("sortBy",sortBy)
                .addParam("pageNumber",pager+"")
                .bulidParam())
                .map(new RxResultFunc<ShopNewBean>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<ShopNewBean>(ShopActivity.this,true) {
                    @Override
                    public void onNext(ShopNewBean bean) {
                        if (bean.getPageList().getList().size() != 0) {
                            if (pager == 1) {
                                mNewList = bean.getPageList().getList();
                            } else {
                                mNewList.addAll(bean.getPageList().getList());
                            }
                            mNewAdapter.replaceData(mNewList);
                        } else {
                            if (pager > 1) {
                                showToast(getResources().getString(R.string.no_more_date));
                            }else{
                                mNewList.clear();
                                mNewAdapter.replaceData(mNewList);
                            }
                        }

                        if(isPriceUp){
                            mIvChangePrice.setBackground(getResources().getDrawable(R.drawable.price_arrow_down));
                        }else{
                            mIvChangePrice.setBackground(getResources().getDrawable(R.drawable.price_arrow_up));
                        }
                        mSpring.onFinishFreshAndLoad();
                    }
                });
    }

    private void setListener() {
        mSpring.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {

            }

            @Override
            public void onLoadmore() {
                pager++;
                getNew();
            }
        });

        mNewRecommendAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(ShopActivity.this,ShopProductDetailActivity.class);
                intent.putExtra("id",mNewRecommendList.get(position).getId()+"");
                startActivity(intent);
            }
        });

        mNewAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(ShopActivity.this,ShopProductDetailActivity.class);
                intent.putExtra("id",mNewList.get(position).getId()+"");
                startActivity(intent);
            }
        });
    }

    @OnClick({R.id.iv_change_price})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_change_price:
                //当前是降序排列(价格高的在上面)，更改为升序
                if(isPriceUp){
                    pager = 1;
                    isPriceUp = false;
                    sortBy = "priceAsc";

                    getNew();
                }else{
                    pager = 1;
                    isPriceUp = true;
                    sortBy = "priceDesc";

                    getNew();
                }
                break;
        }
    }

    //新品推荐适配
    public class NewRecommendAdapter extends BaseQuickAdapter<ShopNewRecommendBean.PageListBean.ListBean, BaseViewHolder> {
        public NewRecommendAdapter(int layoutResId, List data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, ShopNewRecommendBean.PageListBean.ListBean item) {
            GlideUtil.setImage(ShopActivity.this, Constants.IMAGEHOST+item.getIconImage(), (ImageView) helper.getView(R.id.iv_goods_lib));
            helper.setText(R.id.tv_goods_lib_introduce, item.getName());
            helper.setText(R.id.tv_goods_lib_price, "￥"+item.getPrice());
        }
    }

    //最新适配
    public class NewAdapter extends BaseQuickAdapter<ShopNewBean.PageListBean.ListBean, BaseViewHolder> {
        public NewAdapter(int layoutResId, List data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, ShopNewBean.PageListBean.ListBean item) {
            GlideUtil.setImage(ShopActivity.this, Constants.IMAGEHOST+item.getIconImage(), (ImageView) helper.getView(R.id.iv_goods_lib));
            helper.setText(R.id.tv_goods_lib_introduce, item.getName());
            helper.setText(R.id.tv_goods_lib_price, "￥"+item.getPrice());
        }
    }
}
