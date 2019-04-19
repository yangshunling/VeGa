package com.jingwei.vega.activity;

import android.content.Intent;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jingwei.vega.Constants;
import com.jingwei.vega.R;
import com.jingwei.vega.base.BaseActivity;
import com.jingwei.vega.moudle.bean.MyCollectProductsBean;
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

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 我的收藏界面
 */
public class MyCollectActivity extends BaseActivity {

    @BindView(R.id.rv_list)
    RecyclerView mRvList;
    @BindView(R.id.spring)
    SpringView mSpring;
    @BindView(R.id.et_content)
    EditText mEtContent;

    private MyAdapter mMyAdapter;
    private List<MyCollectProductsBean.PageListBean.ListBean> mBeanList = new ArrayList<>();

    private int pager = 1;

    private String msg = "";

    @Override
    public int getContentView() {
        return R.layout.activity_my_collect;
    }

    @Override
    public void initTitleBar() {
        getTitleBar().setLeftImage(R.drawable.icon_back).setTitleText("我的收藏");
    }

    @Override
    public void initView() {
        mSpring.setHeader(new DefaultHeader(MyCollectActivity.this));
        mSpring.setFooter(new DefaultFooter(MyCollectActivity.this));
        mMyAdapter = new MyAdapter(R.layout.item_product_recycle, mBeanList);
        mMyAdapter.setEmptyView(getEmptyView());
        mRvList.setAdapter(mMyAdapter);
        mRvList.setLayoutManager(new LinearLayoutManager(MyCollectActivity.this));
        mRvList.addItemDecoration(new HorizontalDividerItemDecoration.Builder(MyCollectActivity.this)
                .color(ContextCompat.getColor(MyCollectActivity.this, R.color.gray2))
                .size(DisplayUtil.dp2px(MyCollectActivity.this, 0.5f))
                .build());
    }

    @Override
    public void initData() {
        getMyCollectList();

        setListener();
    }

    private void getMyCollectList() {
        ServiceAPI.Retrofit().getMyCollectList(ParamBuilder.newParams()
                .addParam("productName", msg)
                .addParam("pageNumber", pager + "")
                .bulidParam())
                .map(new RxResultFunc<MyCollectProductsBean>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<MyCollectProductsBean>(MyCollectActivity.this, true) {
                    @Override
                    public void onNext(MyCollectProductsBean myCollectProductsBean) {
                        if (myCollectProductsBean.getPageList().getList().size() != 0) {
                            if (pager == 1) {
                                mBeanList = myCollectProductsBean.getPageList().getList();
                            } else {
                                mBeanList.addAll(myCollectProductsBean.getPageList().getList());
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
                Intent intent = new Intent(MyCollectActivity.this, SearchActivity.class);
                intent.putExtra("name", mEtContent.getText().toString().trim());
                startActivityForResult(intent, Constants.MYCOLLECT);
            }
        });

        mSpring.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                pager = 1;
                getMyCollectList();
            }

            @Override
            public void onLoadmore() {
                pager++;
                getMyCollectList();
            }
        });
        mMyAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.iv_delete:
                        deleteMyCollect(mBeanList.get(position).getId(), position);
                        break;
                }
            }
        });

        mMyAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(MyCollectActivity.this, ShopProductDetailActivity.class);
                intent.putExtra("id", mBeanList.get(position).getProductId() + "");
                startActivity(intent);
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
                getMyCollectList();
            }
        }
    }

    /**
     * 取消收藏
     *
     * @param id       收藏的id，不是商品id
     * @param position 列表中所在位置
     */
    private void deleteMyCollect(int id, final int position) {
        ServiceAPI.Retrofit().deleteSaveProductState(id + "")
                .map(new RxResultFunc<Object>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<Object>(MyCollectActivity.this, "正在取消收藏...") {
                    @Override
                    public void onNext(Object message) {
                        mBeanList.remove(mBeanList.get(position));
                        mMyAdapter.replaceData(mBeanList);
                    }
                });
    }

    public class MyAdapter extends BaseQuickAdapter<MyCollectProductsBean.PageListBean.ListBean, BaseViewHolder> {
        public MyAdapter(int layoutResId, List data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, MyCollectProductsBean.PageListBean.ListBean item) {
            GlideUtil.setImage(MyCollectActivity.this, Constants.IMAGEHOST + item.getIconImage().get(0).getPath(), (ImageView) helper.getView(R.id.iv_image));
            helper.setText(R.id.tv_name, item.getName());
            helper.setText(R.id.tv_des, item.getRemark());

            DecimalFormat df = new DecimalFormat("0.00");
            helper.setText(R.id.tv_price, "￥" + df.format(item.getPrice()));

            //取消收藏按钮
            helper.addOnClickListener(R.id.iv_delete);
        }
    }
}
