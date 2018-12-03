package com.jingwei.vega.activity;

import com.jingwei.vega.R;
import com.jingwei.vega.base.BaseActivity;

/**
 * 设置
 */
public class SettingActivity extends BaseActivity {

    @Override
    public int getContentView() {
        return R.layout.activity_setting;
    }

    @Override
    public void initTitleBar() {
        getTitleBar().setLeftImage(R.drawable.icon_back).setTitleText("设置");
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
    }
}
