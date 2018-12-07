package com.jingwei.vega.activity;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.EditText;

import com.flyco.tablayout.SlidingTabLayout;
import com.jingwei.vega.Constants;
import com.jingwei.vega.R;
import com.jingwei.vega.adapter.ViewPagerAdapter;
import com.jingwei.vega.base.BaseActivity;
import com.jingwei.vega.fragment.GoodsLibAllFragment;
import com.jingwei.vega.fragment.GoodsLibLatestFragment;
import com.jingwei.vega.fragment.GoodsLibSentimentFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

public class GoodsListActivity extends BaseActivity {

    @BindView(R.id.tablayout)
    SlidingTabLayout mTablayout;
    @BindView(R.id.viewpager)
    ViewPager mViewpager;
    @BindView(R.id.et_content)
    EditText mEtContent;

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
        hintTitleBar();
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
        mTablayout.setViewPager(mViewpager, mTitles, GoodsListActivity.this, mFragments);
    }

    @Override
    public void initData() {

    }

    @OnClick(R.id.et_content)
    public void onViewClicked() {
        Intent intent = new Intent(GoodsListActivity.this, SearchActivity.class);
        startActivityForResult(intent, Constants.GOODSLIBACTIVITY);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constants.GOODSLIBACTIVITY) {
            if (data != null) {
                String msg = data.getStringExtra("content");
                mEtContent.setText(msg);
                showToast(msg);
            }
        }
    }
}