package com.jingwei.vega.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jingwei.vega.R;
import com.jingwei.vega.base.BaseFragment;
import com.jingwei.vega.moudle.bean.GoodsLibBean;
import com.jingwei.vega.refresh.DefaultFooter;
import com.jingwei.vega.refresh.DefaultHeader;
import com.jingwei.vega.refresh.SpringView;
import com.jingwei.vega.utils.GlideUtil;
import com.jingwei.vega.view.GridDivider;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class GoodsLibAllFragment extends BaseFragment {

    @BindView(R.id.rv_list)
    RecyclerView mRvList;
    @BindView(R.id.spring)
    SpringView mSpring;

    private MyAdapter mMyAdapter;
    private List<GoodsLibBean> mBeanList = new ArrayList<>();

    @Override
    public int getContentView() {
        return R.layout.fragment_goods_lib_all;
    }

    @Override
    public void initView(View rootView) {
        mSpring.setHeader(new DefaultHeader(getActivity()));
        mSpring.setFooter(new DefaultFooter(getActivity()));
        mMyAdapter = new MyAdapter(R.layout.item_goods_lib_recycle, mBeanList);
        mMyAdapter.setEmptyView(getEmptyView());
        mRvList.setAdapter(mMyAdapter);
        mRvList.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        mRvList.addItemDecoration(new GridDivider(getActivity(),20,getResources().getColor(R.color.white)));
    }

    @Override
    protected void setListener() {
        mSpring.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {

            }

            @Override
            public void onLoadmore() {

            }
        });
    }

    @Override
    public void initData() {
        //测试数据
        for (int i = 0; i < 30; i++) {
            GoodsLibBean bean = new GoodsLibBean();
            bean.setImage("http://img18.3lian.com/d/file/201709/21/d8768c389b316e95ef29276c53a1e964.jpg");
            bean.setIntroduce("【新品】防风外套男绿色宽松秋冬防风外套男绿色防风外套冬防风外冬防风外冬防风外冬防风外");
            bean.setPrice("15000.00");
            mBeanList.add(bean);
        }
        mMyAdapter.replaceData(mBeanList);
    }

    @Override
    public void onClick(View v) {

    }

    public class MyAdapter extends BaseQuickAdapter<GoodsLibBean, BaseViewHolder> {
        public MyAdapter(int layoutResId, List data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, GoodsLibBean item) {
            GlideUtil.setRoundImage(getActivity(), item.getImage(), 10, (ImageView) helper.getView(R.id.iv_goods_lib));
            helper.setText(R.id.tv_goods_lib_introduce, item.getIntroduce());
            helper.setText(R.id.tv_goods_lib_price, item.getPrice());
        }
    }
}
