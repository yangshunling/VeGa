package com.jingwei.vega.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.jingwei.vega.R;
import com.jingwei.vega.base.BaseActivity;
import com.jingwei.vega.utils.PreferencesUtil;
import com.jingwei.vega.utils.SoftKeyboardUtils;
import com.jingwei.vega.utils.TextUtil;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class SearchActivity extends BaseActivity {

    @BindView(R.id.id_flowlayout)
    TagFlowLayout mIdFlowlayout;
    @BindView(R.id.et_content)
    EditText mEtContent;
    @BindView(R.id.tv_search_cancel)
    TextView mTvSearchCancel;
    @BindView(R.id.iv_clean_record)
    ImageView mIvCleanRecord;
    @BindView(R.id.iv_clean_content)
    ImageView mIvCleanContent;

    private Integer name;
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
        name = getIntent().getIntExtra("name", -1);
        initAdapter();
    }

    private void initAdapter() {
        mIdFlowlayout.setAdapter(new TagAdapter<String>(mRecordList) {
            @Override
            public View getView(FlowLayout parent, int position, String strings) {
                TextView tv = (TextView) LayoutInflater.from(SearchActivity.this).inflate(R.layout.item_flow, mIdFlowlayout, false);
                tv.setText(strings);
                return tv;
            }
        });
    }

    @Override
    public void initData() {

        mIdFlowlayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                content = mRecordList.get(position);
                returnData();
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
    }

    private void saveRecord() {
        content = mEtContent.getText().toString().trim();
        if (!TextUtil.isEmpty(content)) {
            PreferencesUtil.saveSearchRecord(SearchActivity.this, content);
        }
    }

    private void returnData() {
        Intent intent = new Intent();
        intent.putExtra("content", content);
        setResult(100, intent);
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        SoftKeyboardUtils.showSoftKeyboard(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SoftKeyboardUtils.hideSoftKeyboard(this);
    }

    @OnClick({R.id.iv_clean_content, R.id.tv_search_cancel, R.id.iv_clean_record})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_clean_content:
                mEtContent.setText("");
                break;
            case R.id.tv_search_cancel:
                finish();
                break;
            case R.id.iv_clean_record:
                AlertDialog.Builder builder = new AlertDialog.Builder(SearchActivity.this);
                builder.setTitle("删除记录");
                builder.setMessage("确定要删除全部的历史记录吗？");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        PreferencesUtil.cleanSearchRecord(SearchActivity.this);
                        mRecordList.clear();
                        initAdapter();
                        dialog.dismiss();
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).create().show();
                break;
        }
    }
}
