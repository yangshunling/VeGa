package com.jingwei.vega.activity;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.jingwei.vega.R;
import com.jingwei.vega.base.BaseActivity;
import com.jingwei.vega.moudle.SearchRecordEvent;
import com.jingwei.vega.utils.PreferencesUtil;
import com.jingwei.vega.utils.TextUtil;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import de.greenrobot.event.EventBus;

public class SearchActivity extends BaseActivity {

    @BindView(R.id.id_flowlayout)
    TagFlowLayout mIdFlowlayout;
    @BindView(R.id.et_content)
    EditText mEtContent;
    @BindView(R.id.tv_search_cancel)
    TextView mTvSearchCancel;
    @BindView(R.id.iv_clean_record)
    ImageView mIvCleanRecord;

    private String tag = "";
    private String content = "";

    private List<String> mRecordList = new ArrayList<>();

    @Override
    public int getContentView() {
        return R.layout.activity_search;
    }

    @Override
    public void initTitleBar() {
        hintTitleBar();
    }

    @Override
    public void initView() {
        mEtContent.setFocusable(true);
        mEtContent.setFocusableInTouchMode(true);
        mEtContent.requestFocus();
        mRecordList = PreferencesUtil.getSearchRecordList(this);
        tag = getIntent().getStringExtra("tag");
    }

    @Override
    public void initData() {

        mIdFlowlayout.setAdapter(new TagAdapter<String>(mRecordList) {
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
                showToast(mRecordList.get(position));
                return false;
            }
        });

        mEtContent.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if ((actionId == 0 || actionId == 3) && event != null) {
                    saveRecord();
                    returnData();
                }
                return false;
            }
        });

        mTvSearchCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEtContent.setText("");
            }
        });
    }

    private void saveRecord() {
        content = mEtContent.getText().toString().trim();
        if (!TextUtil.isEmpty(content)) {
            PreferencesUtil.saveSearchRecord(SearchActivity.this, content);
        }
    }

    private void returnData() {
        if (tag.equals("home")) {
            EventBus.getDefault().post(new SearchRecordEvent(content));
        }
    }

}
