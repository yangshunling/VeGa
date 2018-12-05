package com.jingwei.vega.activity;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jingwei.vega.R;
import com.jingwei.vega.base.BaseActivity;
import com.jingwei.vega.moudle.bean.GoodsLibBean;
import com.jingwei.vega.refresh.DefaultFooter;
import com.jingwei.vega.refresh.SpringView;
import com.jingwei.vega.utils.GlideUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by cxc on 2018/12/4.
 * 商铺界面
 */
public class ShopActivity extends BaseActivity{

    @BindView(R.id.iv_left_finish)
    ImageView mIvLeftFinish;

    @BindView(R.id.rv_new_recommend)
    RecyclerView mRvNewRecommend;//新品推荐

    @BindView(R.id.rv_new)
    RecyclerView mRvNew;//最新

    @BindView(R.id.spring)
    SpringView mSpring;

    @BindView(R.id.rl_shop)
    RelativeLayout mRlShop;

    //新品推荐
    private List<GoodsLibBean> mNewRecommendList = new ArrayList<>();
    private NewRecommendAdapter mNewRecommendAdapter;

    //最新
    private List<GoodsLibBean> mNewList = new ArrayList<>();
    private NewAdapter mNewAdapter;

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
        mRvNew.setLayoutManager(new GridLayoutManager(ShopActivity.this, 3));

        setListener();
    }

    private void setListener() {
        mSpring.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {

            }

            @Override
            public void onLoadmore() {

            }
        });

        mNewRecommendAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                startActivity(new Intent(ShopActivity.this, ShopProductDetailActivity.class));
            }
        });

        mNewAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                startActivity(new Intent(ShopActivity.this, ShopProductDetailActivity.class));
            }
        });
    }

    @Override
    public void initData() {
        //测试数据
        for (int i = 0; i < 10; i++) {
            GoodsLibBean bean = new GoodsLibBean();
            bean.setImage("http://img18.3lian.com/d/file/201709/21/d8768c389b316e95ef29276c53a1e964.jpg");
            bean.setIntroduce("【新品】防风外套男绿色宽松秋冬防风外套男绿色防风外套冬防风外冬防风外冬防风外冬防风外");
            bean.setPrice("￥15000.00");

            mNewRecommendList.add(bean);
            mNewList.add(bean);
        }
        mNewRecommendAdapter.replaceData(mNewRecommendList);
        mNewAdapter.replaceData(mNewList);
    }

    //新品推荐适配
    public class NewRecommendAdapter extends BaseQuickAdapter<GoodsLibBean, BaseViewHolder> {
        public NewRecommendAdapter(int layoutResId, List data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, GoodsLibBean item) {
            GlideUtil.setImage(ShopActivity.this, item.getImage(), (ImageView) helper.getView(R.id.iv_goods_lib));
            helper.setText(R.id.tv_goods_lib_introduce, item.getIntroduce());
            helper.setText(R.id.tv_goods_lib_price, item.getPrice());
        }
    }

    //最新适配
    public class NewAdapter extends BaseQuickAdapter<GoodsLibBean, BaseViewHolder> {
        public NewAdapter(int layoutResId, List data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, GoodsLibBean item) {
            GlideUtil.setImage(ShopActivity.this, item.getImage(), (ImageView) helper.getView(R.id.iv_goods_lib));
            helper.setText(R.id.tv_goods_lib_introduce, item.getIntroduce());
            helper.setText(R.id.tv_goods_lib_price, item.getPrice());
        }
    }
}
