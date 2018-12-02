package com.jingwei.vega.fragment;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jingwei.vega.R;
import com.jingwei.vega.base.BaseFragment;
import com.jingwei.vega.view.CustomLinearLayout;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;

public class MeFragment extends BaseFragment {

    @BindView(R.id.my_bg)
    ImageView mMyBg;
    @BindView(R.id.iv_setting)
    ImageView mIvSetting;
    @BindView(R.id.user_icon)
    ImageView mUserIcon;
    @BindView(R.id.tv_name)
    TextView mTvName;
    @BindView(R.id.iv_vip)
    ImageView mIvVip;
    @BindView(R.id.iv_unvip)
    ImageView mIvUnvip;
    @BindView(R.id.tv_phone)
    TextView mTvPhone;
    @BindView(R.id.ll_collection)
    CustomLinearLayout mLlCollection;
    @BindView(R.id.ll_download)
    CustomLinearLayout mLlDownload;
    @BindView(R.id.ll_vip)
    CustomLinearLayout mLlVip;
    @BindView(R.id.ll_about)
    CustomLinearLayout mLlAbout;
    Unbinder unbinder;

    @Override
    public int getContentView() {
        return R.layout.fragment_me;
    }

    @Override
    public void initView(View rootView) {

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

    @OnClick({R.id.iv_setting, R.id.ll_collection, R.id.ll_download, R.id.ll_vip, R.id.ll_about})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_setting:
                break;
            case R.id.ll_collection:
                break;
            case R.id.ll_download:
                break;
            case R.id.ll_vip:
                break;
            case R.id.ll_about:
                break;
        }
    }
}
