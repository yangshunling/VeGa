package com.jingwei.vega.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.flyco.tablayout.SlidingTabLayout;
import com.jingwei.vega.R;
import com.jingwei.vega.adapter.ViewPagerAdapter;
import com.jingwei.vega.base.BaseFragment;

import java.util.ArrayList;

import butterknife.BindView;

public class FocusFragment extends BaseFragment {


    @BindView(R.id.tablayout)
    SlidingTabLayout mTablayout;
    @BindView(R.id.viewpager)
    ViewPager mViewpager;

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

}
