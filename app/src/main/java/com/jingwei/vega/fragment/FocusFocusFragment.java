package com.jingwei.vega.fragment;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jingwei.vega.Constants;
import com.jingwei.vega.R;
import com.jingwei.vega.activity.ShopActivity;
import com.jingwei.vega.adapter.DynamicImageAdapter;
import com.jingwei.vega.base.BaseFragment;
import com.jingwei.vega.moudle.FocusSearchMsgEvent;
import com.jingwei.vega.moudle.LibSearchMsgEvent;
import com.jingwei.vega.moudle.bean.DynamicBean;
import com.jingwei.vega.moudle.bean.FocusBean;
import com.jingwei.vega.refresh.DefaultFooter;
import com.jingwei.vega.refresh.DefaultHeader;
import com.jingwei.vega.refresh.SpringView;
import com.jingwei.vega.rxhttp.retrofit.ParamBuilder;
import com.jingwei.vega.rxhttp.retrofit.ServiceAPI;
import com.jingwei.vega.rxhttp.rxjava.RxResultFunc;
import com.jingwei.vega.rxhttp.rxjava.RxSubscriber;
import com.jingwei.vega.utils.DisplayUtil;
import com.jingwei.vega.utils.GlideUtil;
import com.jingwei.vega.view.CustomGridView;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class FocusFocusFragment extends BaseFragment {

    @BindView(R.id.rv_list)
    RecyclerView mRvList;
    @BindView(R.id.spring)
    SpringView mSpring;

    private Integer pager = 1;

    private MyAdapter mMyAdapter;
    private List<FocusBean.PageListBean.ListBean> mBeanList = new ArrayList<>();

    private String msg = "";

    @Override
    public int getContentView() {
        return R.layout.fragment_focus_focus;
    }

    @Override
    public void initView(View rootView) {
        EventBus.getDefault().register(this);
        mSpring.setHeader(new DefaultHeader(getActivity()));
        mSpring.setFooter(new DefaultFooter(getActivity()));
        mMyAdapter = new MyAdapter(R.layout.item_focus_recycle, mBeanList);
        mMyAdapter.setEmptyView(getEmptyView());
        mRvList.setAdapter(mMyAdapter);
        mRvList.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRvList.addItemDecoration(new HorizontalDividerItemDecoration.Builder(getActivity())
                .color(ContextCompat.getColor(getActivity(), R.color.gray2))
                .size(DisplayUtil.dp2px(getActivity(), 0.5f))
                .build());
    }

    @Override
    protected void setListener() {
        mSpring.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                getRefresh();
            }

            @Override
            public void onLoadmore() {
                getLoadmore();
            }
        });

        mMyAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(getActivity(), ShopActivity.class);
                intent.putExtra("shopId", mBeanList.get(position).getId());
                startActivity(intent);
            }
        });
    }

    @Override
    public void initData() {
        getRefresh();
    }

    private void getLoadmore() {
        pager += 1;
        ServiceAPI.Retrofit().getFocusList(ParamBuilder.newParams()
                .addParam("name", msg)
                .addParam("pageNumber", pager + "")
                .bulidParam())
                .map(new RxResultFunc<FocusBean>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<FocusBean>(getActivity()) {
                    @Override
                    public void onNext(FocusBean bean) {
                        mBeanList.addAll(bean.getPageList().getList());
                        mMyAdapter.replaceData(mBeanList);
                        mSpring.onFinishFreshAndLoad();
                        if (mBeanList == null || mBeanList.size() == 0) {
                            showToast("没有更多数据");
                        }
                    }
                });
    }

    private void getRefresh() {
        pager = 1;
        ServiceAPI.Retrofit().getFocusList(ParamBuilder.newParams()
                .addParam("name", msg)
                .addParam("pageNumber", pager + "")
                .bulidParam())
                .map(new RxResultFunc<FocusBean>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<FocusBean>(getActivity()) {
                    @Override
                    public void onNext(FocusBean bean) {
                        mBeanList = bean.getPageList().getList();
                        mSpring.onFinishFreshAndLoad();
                        mMyAdapter.replaceData(mBeanList);
                    }
                });
    }

    @Override
    public void onClick(View v) {

    }

    public class MyAdapter extends BaseQuickAdapter<FocusBean.PageListBean.ListBean, BaseViewHolder> {
        public MyAdapter(int layoutResId, List data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, FocusBean.PageListBean.ListBean item) {
            GlideUtil.setCircleImage(getActivity(), Constants.IMAGEHOST + item.getHeadImg(), (ImageView) helper.getView(R.id.iv_image));
            helper.setText(R.id.tv_name, item.getName());
            helper.setText(R.id.tv_xin, item.getNewProductNumber() + "");
            helper.setText(R.id.tv_dian, item.getProductNumber() + "");
            helper.setText(R.id.tv_project, item.getMainProducts());
            helper.addOnClickListener(R.id.bt_save);
        }
    }

    @Subscribe(threadMode = ThreadMode.MainThread)
    public void setQuestionEvent(FocusSearchMsgEvent event) {
        msg = event.getContent();
        getRefresh();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
