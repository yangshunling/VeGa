package com.jingwei.vega.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.flyco.tablayout.SlidingTabLayout;
import com.jingwei.vega.Constants;
import com.jingwei.vega.R;
import com.jingwei.vega.activity.GoodsLibActivity;
import com.jingwei.vega.activity.SearchActivity;
import com.jingwei.vega.adapter.ViewPagerAdapter;
import com.jingwei.vega.base.BaseFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class FocusFragment extends BaseFragment {

    @BindView(R.id.tablayout)
    SlidingTabLayout mTablayout;
    @BindView(R.id.viewpager)
    ViewPager mViewpager;
    @BindView(R.id.et_content)
    EditText mEtContent;

    private FocusDynamicFragment mFocusDynamicFragment;
    private FocusFocusFragment mFocusFocusFragment;
    private ArrayList<Fragment> mFragments = new ArrayList<>();

    private final String[] mTitles = {
            "动态", "关注"};
    private ViewPagerAdapter mAdapter;
    private int TAG = 0;

    @Override
    public int getContentView() {
        return R.layout.fragment_focus;
    }

    @Override
    public void initView(View rootView) {
        mFocusDynamicFragment = new FocusDynamicFragment();
        mFocusFocusFragment = new FocusFocusFragment();
        mFragments.add(mFocusDynamicFragment);
        mFragments.add(mFocusFocusFragment);

        mAdapter = new ViewPagerAdapter(getFragmentManager(), mFragments, mTitles);
        mViewpager.setAdapter(mAdapter);
        mTablayout.setViewPager(mViewpager, mTitles, getActivity(), mFragments);
        mViewpager.setCurrentItem(TAG);
    }

    @Override
    protected void setListener() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {

    }

    @OnClick(R.id.et_content)
    public void onViewClicked() {
        Intent intent = new Intent(getActivity(), SearchActivity.class);
        startActivityForResult(intent, Constants.FOCUSFRAGMENT);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constants.FOCUSFRAGMENT) {
            String msg = data.getStringExtra("content");
            mEtContent.setText(msg);
            showToast(msg);
        }
    }
}
