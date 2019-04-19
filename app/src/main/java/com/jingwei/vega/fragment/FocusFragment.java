package com.jingwei.vega.fragment;

import android.content.Intent;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import android.view.View;
import android.widget.EditText;

import com.flyco.tablayout.SlidingTabLayout;
import com.jingwei.vega.Constants;
import com.jingwei.vega.R;
import com.jingwei.vega.activity.SearchActivity;
import com.jingwei.vega.adapter.ViewPagerAdapter;
import com.jingwei.vega.base.BaseFragment;
import com.jingwei.vega.moudle.FocusSearchMsgEvent;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;

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
        intent.putExtra("name", mEtContent.getText().toString().trim());
        startActivityForResult(intent, Constants.FOCUSFRAGMENT);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constants.FOCUSFRAGMENT) {
            if (data != null) {
                String msg = data.getStringExtra("content");
                mEtContent.setText(msg);
                EventBus.getDefault().post(new FocusSearchMsgEvent(msg));
            }
        }
    }
}
