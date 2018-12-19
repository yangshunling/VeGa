package com.jingwei.vega.activity;


import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jingwei.vega.Constants;
import com.jingwei.vega.R;
import com.jingwei.vega.base.BaseActivity;
import com.jingwei.vega.moudle.bean.DownloadProductBean;
import com.jingwei.vega.refresh.DefaultFooter;
import com.jingwei.vega.refresh.DefaultHeader;
import com.jingwei.vega.refresh.SpringView;
import com.jingwei.vega.utils.DisplayUtil;
import com.jingwei.vega.utils.GlideUtil;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 下载记录
 */
public class DownloadRecordActivity extends BaseActivity {

    @BindView(R.id.rv_list)
    RecyclerView mRvList;
    @BindView(R.id.spring)
    SpringView mSpring;
    @BindView(R.id.et_content)
    EditText mEtContent;


    private MyAdapter mMyAdapter;
    private List<DownloadProductBean> mBeanList = new ArrayList<>();

    private int pager = 1;

    private String msg = "";

    @Override
    public int getContentView() {
        return R.layout.activity_download_record;
    }

    @Override
    public void initTitleBar() {
        getTitleBar().setLeftImage(R.drawable.icon_back).setTitleText("下载记录");
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        mSpring.setHeader(new DefaultHeader(DownloadRecordActivity.this));
        mSpring.setFooter(new DefaultFooter(DownloadRecordActivity.this));
        mMyAdapter = new MyAdapter(R.layout.item_download_product_recycle, mBeanList);
        mMyAdapter.setEmptyView(getEmptyView());
        mRvList.setAdapter(mMyAdapter);
        mRvList.setLayoutManager(new LinearLayoutManager(DownloadRecordActivity.this));
        mRvList.addItemDecoration(new HorizontalDividerItemDecoration.Builder(DownloadRecordActivity.this)
                .color(ContextCompat.getColor(DownloadRecordActivity.this, R.color.gray2))
                .size(DisplayUtil.dp2px(DownloadRecordActivity.this, 0.5f))
                .build());

        //测试数据
        for (int i = 0; i < 20; i++) {
            DownloadProductBean bean = new DownloadProductBean();
            bean.setImage("http://img18.3lian.com/d/file/201709/21/d8768c389b316e95ef29276c53a1e964.jpg");
            bean.setName("连体裤");
            bean.setShop("喵喵品牌店");
            bean.setType("森系女装");
            mBeanList.add(bean);
        }
        mMyAdapter.replaceData(mBeanList);


        setListener();
    }

    private void setListener() {
        mEtContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DownloadRecordActivity.this, SearchActivity.class);
                intent.putExtra("name", mEtContent.getText().toString().trim());
                startActivityForResult(intent, Constants.MYCOLLECT);
            }
        });


        mSpring.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {

            }

            @Override
            public void onLoadmore() {

            }
        });

        mMyAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                startActivity(new Intent(DownloadRecordActivity.this,DownloadRecordDetailActivity.class));
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constants.MYCOLLECT) {
            if (data != null) {
                msg = data.getStringExtra("content");
                mEtContent.setText(msg);
                pager = 1;
//                getMyCollectList();
            }
        }
    }

    public class MyAdapter extends BaseQuickAdapter<DownloadProductBean, BaseViewHolder> {
        public MyAdapter(int layoutResId, List data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, DownloadProductBean item) {
            GlideUtil.setImage(DownloadRecordActivity.this, item.getImage(), (ImageView) helper.getView(R.id.iv_image));
            helper.setText(R.id.tv_name,item.getName());
            helper.setText(R.id.tv_shop,item.getShop());
            helper.setText(R.id.tv_type,item.getType());
        }
    }
}
