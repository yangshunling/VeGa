package com.jingwei.vega.fragment;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jingwei.vega.Constants;
import com.jingwei.vega.R;
import com.jingwei.vega.activity.ShopProductDetailActivity;
import com.jingwei.vega.base.BaseFragment;
import com.jingwei.vega.moudle.LibSearchMsgEvent;
import com.jingwei.vega.moudle.bean.GoodsLibBean;
import com.jingwei.vega.refresh.DefaultFooter;
import com.jingwei.vega.refresh.DefaultHeader;
import com.jingwei.vega.refresh.SpringView;
import com.jingwei.vega.rxhttp.retrofit.ParamBuilder;
import com.jingwei.vega.rxhttp.retrofit.ServiceAPI;
import com.jingwei.vega.rxhttp.rxjava.RxResultFunc;
import com.jingwei.vega.rxhttp.rxjava.RxSubscriber;
import com.jingwei.vega.utils.GlideUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class GoodsListLatestFragment extends BaseFragment {

    @BindView(R.id.rv_list)
    RecyclerView mRvList;
    @BindView(R.id.spring)
    SpringView mSpring;
    @BindView(R.id.iv_arrow_top)
    ImageView mIvArrowTop;

    private Integer pager = 1;

    private String id = "";

    private MyAdapter mMyAdapter;
    private List<GoodsLibBean.PageListBean.ListBean> mBeanList = new ArrayList<>();

    @Override
    public int getContentView() {
        return R.layout.fragment_goods_lib_all;
    }

    @Override
    public void initView(View rootView) {
        id = getArguments().getString("id");
        EventBus.getDefault().register(this);
        mSpring.setHeader(new DefaultHeader(getActivity()));
        mSpring.setFooter(new DefaultFooter(getActivity()));
        mMyAdapter = new MyAdapter(R.layout.item_goods_lib_recycle, mBeanList);
        mMyAdapter.setEmptyView(getEmptyView());
        mRvList.setAdapter(mMyAdapter);
        mRvList.setLayoutManager(new GridLayoutManager(getActivity(), 2));
    }

    @Override
    protected void setListener() {
        mSpring.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                onRefreshData("");
            }

            @Override
            public void onLoadmore() {
                onLoadmoreData("", pager++);
            }
        });

        mMyAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(getActivity(), ShopProductDetailActivity.class);
                intent.putExtra("id", mBeanList.get(position).getId());
                startActivity(intent);
            }
        });

        mIvArrowTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRvList.scrollToPosition(0);
            }
        });
    }

    @Override
    public void initData() {
        onRefreshData("");
    }

    private void onRefreshData(String searchName) {
        ServiceAPI.Retrofit().getGoodsLibList(ParamBuilder.newParams()
                .addParam("name", searchName)
                .addParam("tagId", id)
                .addParam("sortBy", "")
                .addParam("pageNumber", "1")
                .bulidParam())
                .map(new RxResultFunc<GoodsLibBean>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<GoodsLibBean>(getActivity()) {
                    @Override
                    public void onNext(GoodsLibBean bean) {
                        mBeanList = bean.getPageList().getList();
                        mMyAdapter.replaceData(mBeanList);
                        mSpring.onFinishFreshAndLoad();
                    }
                });
    }

    private void onLoadmoreData(String searchName, Integer pager) {
        ServiceAPI.Retrofit().getGoodsLibList(ParamBuilder.newParams()
                .addParam("name", searchName)
                .addParam("tagId", id)
                .addParam("sortBy", "")
                .addParam("pageNumber", pager + "")
                .bulidParam())
                .map(new RxResultFunc<GoodsLibBean>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<GoodsLibBean>(getActivity()) {
                    @Override
                    public void onNext(GoodsLibBean bean) {
                        mBeanList.addAll(bean.getPageList().getList());
                        mMyAdapter.replaceData(mBeanList);
                        mSpring.onFinishFreshAndLoad();
                        if (mBeanList == null || mBeanList.size() == 0) {
                            showToast("没有更多数据");
                        }
                    }
                });
    }

    @Override
    public void onClick(View v) {

    }

    @Subscribe(threadMode = ThreadMode.MainThread)
    public void setQuestionEvent(LibSearchMsgEvent event) {
        onRefreshData(event.getContent());
    }

    public class MyAdapter extends BaseQuickAdapter<GoodsLibBean.PageListBean.ListBean, BaseViewHolder> {
        public MyAdapter(int layoutResId, List data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, GoodsLibBean.PageListBean.ListBean item) {
            GlideUtil.setImage(getActivity(), Constants.IMAGEHOST + item.getIconImage(), (ImageView) helper.getView(R.id.iv_goods_lib));
            helper.setText(R.id.tv_goods_lib_introduce, item.getName());
            helper.setText(R.id.tv_goods_lib_price, "¥" + item.getPrice());
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
