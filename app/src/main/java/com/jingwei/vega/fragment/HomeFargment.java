package com.jingwei.vega.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jingwei.vega.R;
import com.jingwei.vega.adapter.HomeListAdapter;
import com.jingwei.vega.base.BaseFragment;
import com.jingwei.vega.moudle.bean.FocusBean;
import com.jingwei.vega.moudle.bean.HomeBean;
import com.jingwei.vega.utils.GlideUtil;
import com.jingwei.vega.view.cardgallery.CardGalleryAdapter;
import com.jingwei.vega.view.cardgallery.CardScaleHelper;
import com.jingwei.vega.view.cardgallery.SpeedRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class HomeFargment extends BaseFragment {

    @BindView(R.id.recyclerView)
    SpeedRecyclerView mRecyclerView;
    @BindView(R.id.rv_list)
    RecyclerView mRvList;

    private List<String> mStringList = new ArrayList<>();
    private CardScaleHelper mCardScaleHelper = null;

    private MyAdapter mMyAdapter;
    private List<HomeBean> mBeanList = new ArrayList<>();

    @Override
    public int getContentView() {
        return R.layout.fragment_home;
    }

    @Override
    public void initView(View rootView) {
    }

    @Override
    public void initData() {
        //轮播图测试数据
        mStringList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1544256191&di=63fa160f4e838ddd1a46c9033f464af9&imgtype=jpg&er=1&src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F0136db56a8337a32f875520fcda8ec.jpg");
        mStringList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1543661471451&di=71e9833a2611a25613c44eb954402e3f&imgtype=0&src=http%3A%2F%2F03img.mopimg.cn%2Fmobile%2F20180109%2F20180109170328_dd813f33a2c0ffa72ac02326fe24dbc9_9.jpeg");
        mStringList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1544256262&di=d8219c847bf9fb14faef076a67ff093c&imgtype=jpg&er=1&src=http%3A%2F%2F5b0988e595225.cdn.sohucs.com%2Fq_70%2Cc_zoom%2Cw_640%2Fimages%2F20180705%2F4251e5bdadf8438995093c1009dbd3b9.jpeg");
        initRecyclerView();
        //列表测试数据
        for (int i = 0; i < 3; i++) {
            HomeBean homeBean = new HomeBean();
            homeBean.setTitle("市场推荐");
            List<HomeBean.CardBean> cb = new ArrayList<>();
            for (int j = 0; j < 4; j++) {
                HomeBean.CardBean bean = new HomeBean.CardBean();
                bean.setUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1543834276504&di=558d8d0aae63b7992d2aa9f8ef851307&imgtype=0&src=http%3A%2F%2Ffile25.mafengwo.net%2FM00%2F66%2FD8%2FwKgB4lMcWvyAE9xYAAC8zPK6yMw35.jpeg");
                bean.setName("杭州"+j);
                cb.add(bean);
            }
            homeBean.setCardBeans(cb);
            mBeanList.add(homeBean);
        }
        mMyAdapter.replaceData(mBeanList);
    }

    private void initRecyclerView() {
        //轮播
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(new CardGalleryAdapter(getActivity(), mStringList));
        //mRecyclerView绑定scale效果
        mCardScaleHelper = new CardScaleHelper();
        mCardScaleHelper.setCurrentItemPos(2);
        mCardScaleHelper.attachToRecyclerView(mRecyclerView);
        //列表
        mMyAdapter = new MyAdapter(R.layout.item_home_recycle, mBeanList);
        mMyAdapter.setEmptyView(getEmptyView());
        mRvList.setAdapter(mMyAdapter);
        mRvList.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    protected void setListener() {

    }

    @Override
    public void onClick(View v) {

    }

    public class MyAdapter extends BaseQuickAdapter<HomeBean, BaseViewHolder> {
        public MyAdapter(int layoutResId, List data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, HomeBean item) {
            helper.setText(R.id.tv_home_title,item.getTitle());
            ListView listView = helper.getView(R.id.lv_home_list);
            listView.setAdapter(new HomeListAdapter(getActivity(),item.getCardBeans()));
        }
    }
}
