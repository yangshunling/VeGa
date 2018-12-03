package com.jingwei.vega.activity;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.jingwei.vega.R;
import com.jingwei.vega.base.BaseActivity;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import butterknife.BindView;

public class SearchActivity extends BaseActivity {

    @BindView(R.id.id_flowlayout)
    TagFlowLayout mIdFlowlayout;

    private String[] mVals = new String[]{"Hello", "Android", "Weclome Hi ", "Button", "TextView", "Hello",
            "Android", "Weclome", "Button ImageView", "TextView", "Helloworld",
            "Android", "Weclome Hello", "Button Text", "TextView"};

    @Override
    public int getContentView() {
        return R.layout.activity_search;
    }

    @Override
    public void initTitleBar() {

    }

    @Override
    public void initView() {
        mIdFlowlayout.setAdapter(new TagAdapter<String>(mVals) {
            @Override
            public View getView(FlowLayout parent, int position, String strings) {
                TextView tv = (TextView) LayoutInflater.from(SearchActivity.this).inflate(R.layout.item_flow, mIdFlowlayout, false);
                tv.setText(strings);
                return tv;
            }
        });
        mIdFlowlayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                showToast(mVals[position]);
                return false;
            }
        });
    }

    @Override
    public void initData() {

    }
}
