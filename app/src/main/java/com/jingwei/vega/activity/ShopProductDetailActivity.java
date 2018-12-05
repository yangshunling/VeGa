package com.jingwei.vega.activity;

import com.jingwei.vega.R;
import com.jingwei.vega.base.BaseActivity;

/**
 * Created by cxc on 2018/12/5.
 * 商品详情界面
 */

public class ShopProductDetailActivity extends BaseActivity{

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

    }

    @Override
    public void initData() {

    }
}
