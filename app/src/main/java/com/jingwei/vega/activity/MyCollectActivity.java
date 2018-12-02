package com.jingwei.vega.activity;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jingwei.vega.R;
import com.jingwei.vega.base.BaseActivity;
import com.jingwei.vega.moudle.bean.ProductBean;
import com.jingwei.vega.refresh.DefaultFooter;
import com.jingwei.vega.refresh.DefaultHeader;
import com.jingwei.vega.refresh.SpringView;
import com.jingwei.vega.utils.DisplayUtil;
import com.jingwei.vega.utils.GlideUtil;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 我的收藏界面
 */
public class MyCollectActivity extends BaseActivity {

    @BindView(R.id.rv_list)
    RecyclerView mRvList;
    @BindView(R.id.spring)
    SpringView mSpring;

    private MyAdapter mMyAdapter;
    private List<ProductBean> mBeanList = new ArrayList<>();

    @Override
    public int getContentView() {
        return R.layout.activity_my_collect;
    }

    @Override
    public void initTitleBar() {
        getTitleBar().setLeftImage(R.drawable.icon_back).setTitleText("我的收藏");
    }

    @Override
    public void initData() {
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

        //测试数据
        for (int i = 0; i < 20; i++) {
            ProductBean bean = new ProductBean();
            bean.setImage("http://img18.3lian.com/d/file/201709/21/d8768c389b316e95ef29276c53a1e964.jpg");
            bean.setName("连体裤");
            bean.setDes("商品描述活动描述商品描述活动描述商品描述活动描述商品描述活动描述商品描述活动描述商品描述活动描述");
            bean.setPrice("1150.00");
            mBeanList.add(bean);
        }
        mMyAdapter.replaceData(mBeanList);


        setListener();
    }

    private void setListener() {
        mSpring.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {

            }

            @Override
            public void onLoadmore() {

            }
        });
    }

    public class MyAdapter extends BaseQuickAdapter<ProductBean, BaseViewHolder> {
        public MyAdapter(int layoutResId, List data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, ProductBean item) {
            GlideUtil.setImage(MyCollectActivity.this, item.getImage(), (ImageView) helper.getView(R.id.iv_image));
            helper.setText(R.id.tv_name,item.getName());
            helper.setText(R.id.tv_des,item.getDes());

            DecimalFormat df = new DecimalFormat("0.00");
            helper.setText(R.id.tv_price,"￥"+df.format(Double.parseDouble(item.getPrice())));
        }
    }
}
