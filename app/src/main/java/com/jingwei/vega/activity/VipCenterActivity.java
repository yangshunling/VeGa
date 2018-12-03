package com.jingwei.vega.activity;

import android.view.View;
import android.widget.ImageView;

import com.jingwei.vega.R;
import com.jingwei.vega.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 会员中心
 */
public class VipCenterActivity extends BaseActivity {

    @BindView(R.id.iv_left_finish)
    ImageView mIvLeftFinsh;

    @Override
    public int getContentView() {
        return R.layout.activity_vip_center;
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

    @OnClick({R.id.iv_left_finish})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_left_finish:
                finish();
                break;
        }
    }
}
