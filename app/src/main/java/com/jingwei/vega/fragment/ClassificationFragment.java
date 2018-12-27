package com.jingwei.vega.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jingwei.vega.Constants;
import com.jingwei.vega.R;
import com.jingwei.vega.activity.GoodsListActivity;
import com.jingwei.vega.adapter.ClassificationImageAdapter;
import com.jingwei.vega.adapter.ClassificationListAdapter;
import com.jingwei.vega.base.BaseFragment;
import com.jingwei.vega.moudle.bean.CategoryBannerBean;
import com.jingwei.vega.moudle.bean.CategoryByOneBean;
import com.jingwei.vega.moudle.bean.CategoryByTwoBean;
import com.jingwei.vega.rxhttp.retrofit.ServiceAPI;
import com.jingwei.vega.rxhttp.rxjava.RxResultFunc;
import com.jingwei.vega.rxhttp.rxjava.RxSubscriber;
import com.jingwei.vega.utils.GlideUtil;
import com.jingwei.vega.utils.ListViewUtil;
import com.jingwei.vega.view.CustomGridView;
import com.jingwei.vega.view.GlideImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ClassificationFragment extends BaseFragment {

    @BindView(R.id.lv_left)
    ListView mLvLeft;
    @BindView(R.id.rv_right)
    RecyclerView mRvRight;
    @BindView(R.id.banner)
    ImageView mBanner;

    private ClassificationListAdapter mLeftListAdapter;
    private List<CategoryByOneBean.ListBean> mLeftList = new ArrayList<>();

    private MyAdapter mMyAdapter;
    private List<CategoryByTwoBean.ListBean> mRightList = new ArrayList<>();

    @Override
    public int getContentView() {
        return R.layout.fragment_classification;
    }

    @Override
    public void initView(View rootView) {
        getCategoryByOne();
        getBanner();
        //recycleview
        mMyAdapter = new MyAdapter(R.layout.item_classification_recycle, mRightList);
        mMyAdapter.setEmptyView(getEmptyView());
        mRvRight.setAdapter(mMyAdapter);
        mRvRight.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    private void getBanner() {
        ServiceAPI.Retrofit().getCategoryBanner()
                .map(new RxResultFunc<CategoryBannerBean>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<CategoryBannerBean>(getActivity()) {
                    @Override
                    public void onNext(CategoryBannerBean bean) {
                        if (bean.getList() != null && bean.getList().size() > 0) {
                            mBanner.setVisibility(View.VISIBLE);
                            GlideUtil.setImage(getActivity(), Constants.IMAGEHOST + bean.getList().get(0).getPath(), mBanner);
                        } else {
                            mBanner.setVisibility(View.GONE);
                        }
                    }
                });
    }

    @Override
    protected void setListener() {
        mLvLeft.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                for (int i = 0; i < mLeftList.size(); i++) {
                    mLeftList.get(i).setTag(false);
                }
                mLeftList.get(position).setTag(true);
                mLeftListAdapter.notifyDataSetChanged();
                getCategoryByTwo(mLeftList.get(position).getId());
            }
        });
    }

    @Override
    public void initData() {

    }

    private void getCategoryByOne() {
        ServiceAPI.Retrofit().getCategoryByOne()
                .map(new RxResultFunc<CategoryByOneBean>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<CategoryByOneBean>(getActivity()) {
                    @Override
                    public void onNext(CategoryByOneBean bean) {
                        mLeftList = bean.getList();
                        if (mLeftList.size() != 0) {
                            mLeftListAdapter = new ClassificationListAdapter(getActivity(), mLeftList);
                            mLvLeft.setAdapter(mLeftListAdapter);
                            ListViewUtil.setListViewHeightBasedOnChildren(mLvLeft);
                            mLeftList.get(0).setTag(true);
                            getCategoryByTwo(mLeftList.get(0).getId());
                        }
                    }
                });
    }

    private void getCategoryByTwo(int id) {
        ServiceAPI.Retrofit().getCategoryByTwo(id + "")
                .map(new RxResultFunc<CategoryByTwoBean>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<CategoryByTwoBean>(getActivity()) {
                    @Override
                    public void onNext(CategoryByTwoBean bean) {
                        mRightList = bean.getList();
                        mMyAdapter.replaceData(mRightList);
                    }
                });
    }

    @Override
    public void onClick(View v) {

    }

    public class MyAdapter extends BaseQuickAdapter<CategoryByTwoBean.ListBean, BaseViewHolder> {
        public MyAdapter(int layoutResId, List data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, final CategoryByTwoBean.ListBean item) {
            helper.setText(R.id.tv_title, item.getName());
            CustomGridView gridView = helper.getView(R.id.right_image_list);
            if (item.getSonList()!=null){
                gridView.setAdapter(new ClassificationImageAdapter(getActivity(), item.getSonList()));
            }
            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(getActivity(), GoodsListActivity.class);
                    intent.putExtra("id", item.getId() + "");
                    startActivity(intent);
                }
            });
        }
    }
}
