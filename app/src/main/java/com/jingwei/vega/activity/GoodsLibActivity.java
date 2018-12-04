package com.jingwei.vega.activity;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.EditText;

import com.flyco.tablayout.SlidingTabLayout;
import com.jingwei.vega.R;
import com.jingwei.vega.adapter.ViewPagerAdapter;
import com.jingwei.vega.base.BaseActivity;
import com.jingwei.vega.fragment.GoodsLibAllFragment;
import com.jingwei.vega.fragment.GoodsLibLatestFragment;
import com.jingwei.vega.fragment.GoodsLibSentimentFragment;

import java.util.ArrayList;

import butterknife.BindView;

public class GoodsLibActivity extends BaseActivity {

    @BindView(R.id.et_phone)
    EditText mEtPhone;
    @BindView(R.id.tablayout)
    SlidingTabLayout mTablayout;
    @BindView(R.id.viewpager)
    ViewPager mViewpager;

    private GoodsLibAllFragment mAllFragment;
    private GoodsLibLatestFragment mLatestFragment;
    private GoodsLibSentimentFragment mSentimentFragment;
    private ArrayList<Fragment> mFragments = new ArrayList<>();

    private final String[] mTitles = {
            "全部", "最新", "人气"};
    private ViewPagerAdapter mAdapter;

    @Override
    public int getContentView() {
        return R.layout.activity_goods_lib;
    }

    @Override
    public void initTitleBar() {
        getTitleBar().setLeftImage(R.drawable.icon_back)
                .setTitleText("商品库");
    }

    @Override
    public void initView() {
        mAllFragment = new GoodsLibAllFragment();
        mLatestFragment = new GoodsLibLatestFragment();
        mSentimentFragment = new GoodsLibSentimentFragment();
        mFragments.add(mAllFragment);
        mFragments.add(mLatestFragment);
        mFragments.add(mSentimentFragment);

        mAdapter = new ViewPagerAdapter(getSupportFragmentManager(), mFragments, mTitles);
        mViewpager.setAdapter(mAdapter);
        mTablayout.setViewPager(mViewpager, mTitles, GoodsLibActivity.this, mFragments);
    }

    @Override
    public void initData() {

    }
}