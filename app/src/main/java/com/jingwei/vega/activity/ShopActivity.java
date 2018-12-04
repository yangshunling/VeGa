package com.jingwei.vega.activity;

import com.jingwei.vega.R;
import com.jingwei.vega.base.BaseActivity;

/**
 * Created by cxc on 2018/12/4.
 * 商铺界面
 */
public class ShopActivity extends BaseActivity{

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

    }

    @Override
    public void initData() {

    }
}
