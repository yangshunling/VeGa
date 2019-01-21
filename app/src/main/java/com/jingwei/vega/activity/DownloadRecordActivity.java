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
import com.jingwei.vega.moudle.bean.DownloadRecordBean;
import com.jingwei.vega.refresh.DefaultFooter;
import com.jingwei.vega.refresh.DefaultHeader;
import com.jingwei.vega.refresh.SpringView;
import com.jingwei.vega.rxhttp.retrofit.ParamBuilder;
import com.jingwei.vega.rxhttp.retrofit.ServiceAPI;
import com.jingwei.vega.rxhttp.rxjava.RxResultFunc;
import com.jingwei.vega.rxhttp.rxjava.RxSubscriber;
import com.jingwei.vega.utils.DisplayUtil;
import com.jingwei.vega.utils.GlideUtil;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

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
    private List<DownloadRecordBean.PageListBean.ListBean> mBeanList = new ArrayList<>();
    private DownloadRecordBean mDownloadRecordBean;

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
    }

    @Override
    public void initData() {
        getRefreshInfo();
        setListener();
    }

    private void getRefreshInfo() {
        ServiceAPI.Retrofit().getDownloadRecord(ParamBuilder.newParams()
                .addParam("productName",msg)
                .addParam("pager", pager + "")
                .bulidParam())
                .map(new RxResultFunc<DownloadRecordBean>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<DownloadRecordBean>(DownloadRecordActivity.this, true) {
                    @Override
                    public void onNext(DownloadRecordBean bean) {
                        mDownloadRecordBean = bean;
                        if (bean.getPageList().getList().size() != 0) {
                            if (pager == 1) {
                                mBeanList = bean.getPageList().getList();
                            } else {
                                mBeanList.addAll(bean.getPageList().getList());
                            }
                            mMyAdapter.replaceData(mBeanList);
                        } else {
                            if (pager > 1) {
                                showToast(getResources().getString(R.string.no_more_date));
                            } else {
                                mBeanList.clear();
                                mMyAdapter.replaceData(mBeanList);
                            }
                        }
                        mSpring.onFinishFreshAndLoad();
                    }
                });
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
                getRefreshInfo();
            }

            @Override
            public void onLoadmore() {
                getRefreshInfo();
            }
        });

        mMyAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(DownloadRecordActivity.this,DownloadRecordDetailActivity.class);
                intent.putExtra("id",mBeanList.get(position).getId());
                startActivity(intent);
            }
        });

        mMyAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()){
                    case R.id.ll_shop:
                        Intent intent = new Intent(DownloadRecordActivity.this,ShopActivity.class);
                        intent.putExtra("shopId",mBeanList.get(position).getShopId());
                        startActivity(intent);
                        break;
                }
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
                getRefreshInfo();
            }
        }
    }

    public class MyAdapter extends BaseQuickAdapter<DownloadRecordBean.PageListBean.ListBean, BaseViewHolder> {
        public MyAdapter(int layoutResId, List data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, DownloadRecordBean.PageListBean.ListBean item) {
            GlideUtil.setImage(DownloadRecordActivity.this, Constants.IMAGEHOST+item.getMainPic().getPath(), (ImageView) helper.getView(R.id.iv_image));
            helper.setText(R.id.tv_name, item.getProductName());
            helper.setText(R.id.tv_shop, item.getSupplierName());
            helper.setText(R.id.tv_type, item.getMainProducts());

            //增加点击事件
            helper.addOnClickListener(R.id.ll_shop);
        }
    }
}
