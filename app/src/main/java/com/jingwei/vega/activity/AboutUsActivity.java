package com.jingwei.vega.activity;

import android.widget.TextView;

import com.jingwei.vega.R;
import com.jingwei.vega.base.BaseActivity;

import butterknife.BindView;

/**
 * 关于我们界面
 */
public class AboutUsActivity extends BaseActivity {

    @BindView(R.id.tv_introduce)
    TextView mTvIntroduce;

    @Override
    public int getContentView() {
        return R.layout.activity_about_us;
    }

    @Override
    public void initTitleBar() {
        getTitleBar().setLeftImage(R.drawable.icon_back).setTitleText("关于我们");
    }

    @Override
    public void initData() {
        mTvIntroduce.setText("商品描述活动描述商品描述活动描述商品描述活动描述商品描述活动描述商品描述活动描述商品描述活动" +
                "描述商品描述活动描述商品描述活动描述商品描述活动描述商品描述活动描述商品描述活动描述商品描述活动描述商品描" +
                "述活动描述商品描述活动描述商品描述活动描述商品描述活动描述商品描述活动描述商品描述活动描述商品描述活动描述商" +
                "品描述活动描述商品描述活动描述商品描述活动描述商品描述活动描述商品描述活动描述");
    }
}
