package com.jingwei.vega.fragment;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jingwei.vega.Constants;
import com.jingwei.vega.R;
import com.jingwei.vega.activity.GoodsListActivity;
import com.jingwei.vega.activity.SearchActivity;
import com.jingwei.vega.adapter.ClassificationImageAdapter;
import com.jingwei.vega.adapter.ClassificationListAdapter;
import com.jingwei.vega.base.BaseFragment;
import com.jingwei.vega.moudle.bean.BannerListBean;
import com.jingwei.vega.moudle.bean.CategoryByOneBean;
import com.jingwei.vega.moudle.bean.CategoryByTwoBean;
import com.jingwei.vega.moudle.bean.ClassificationLeftBean;
import com.jingwei.vega.moudle.bean.ClassificationRightBean;
import com.jingwei.vega.rxhttp.retrofit.ServiceAPI;
import com.jingwei.vega.rxhttp.rxjava.RxResultFunc;
import com.jingwei.vega.rxhttp.rxjava.RxSubscriber;
import com.jingwei.vega.view.CustomGridView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ClassificationFragment extends BaseFragment {

    @BindView(R.id.lv_left)
    ListView mLvLeft;
    @BindView(R.id.rv_right)
    RecyclerView mRvRight;
    @BindView(R.id.et_content)
    EditText mEtContent;

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
        //recycleview
        mMyAdapter = new MyAdapter(R.layout.item_classification_recycle, mRightList);
        mMyAdapter.setEmptyView(getEmptyView());
        mRvRight.setAdapter(mMyAdapter);
        mRvRight.setLayoutManager(new LinearLayoutManager(getActivity()));
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
                            mLeftList.get(0).setTag(true);
                            getCategoryByTwo(mLeftList.get(0).getId());
                            mLeftListAdapter = new ClassificationListAdapter(getActivity(), mLeftList);
                            mLvLeft.setAdapter(mLeftListAdapter);
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

    @OnClick(R.id.et_content)
    public void onViewClicked() {
        Intent intent = new Intent(getActivity(), SearchActivity.class);
        startActivityForResult(intent, Constants.CLASSIFICATIONFRAGMENT);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constants.CLASSIFICATIONFRAGMENT) {
            if (data != null) {
                String msg = data.getStringExtra("content");
                mEtContent.setText(msg);
                showToast(msg);
            }
        }
    }

    public class MyAdapter extends BaseQuickAdapter<CategoryByTwoBean.ListBean, BaseViewHolder> {
        public MyAdapter(int layoutResId, List data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, final CategoryByTwoBean.ListBean item) {
            helper.setText(R.id.tv_title, item.getName());
            CustomGridView gridView = helper.getView(R.id.right_image_list);
            gridView.setAdapter(new ClassificationImageAdapter(getActivity(), item.getSonList()));
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
