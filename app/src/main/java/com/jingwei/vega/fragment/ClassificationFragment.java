package com.jingwei.vega.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jingwei.vega.R;
import com.jingwei.vega.adapter.ClassificationImageAdapter;
import com.jingwei.vega.adapter.ClassificationListAdapter;
import com.jingwei.vega.base.BaseFragment;
import com.jingwei.vega.moudle.bean.ClassificationLeftBean;
import com.jingwei.vega.moudle.bean.ClassificationRightBean;
import com.jingwei.vega.view.CustomGridView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ClassificationFragment extends BaseFragment {
    @BindView(R.id.lv_left)
    ListView mLvLeft;
    @BindView(R.id.rv_right)
    RecyclerView mRvRight;

    private ClassificationListAdapter mLeftListAdapter;
    private List<ClassificationLeftBean> mLeftList = new ArrayList<>();

    private MyAdapter mMyAdapter;
    private List<ClassificationRightBean> mRightList = new ArrayList<>();

    @Override
    public int getContentView() {
        return R.layout.fragment_classification;
    }

    @Override
    public void initView(View rootView) {
        //listview
        mLeftListAdapter = new ClassificationListAdapter(getActivity(), mLeftList);
        mLvLeft.setAdapter(mLeftListAdapter);
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

                //右侧测试数据
                for (int i = 1; i < 5; i++) {
                    ClassificationRightBean bean = new ClassificationRightBean();
                    bean.setTitle("咕咕店铺" + i);

                    List<ClassificationRightBean.Bean> beans = new ArrayList<>();
                    for (int j = 1; j < 10; j++) {
                        ClassificationRightBean.Bean url = new ClassificationRightBean.Bean();
                        url.setName("短裤");
                        url.setUrl("http://life.southmoney.com/tuwen/UploadFiles_6871/201801/20180129110733180.jpg");
                        beans.add(url);
                    }
                    bean.setUrlList(beans);

                    mRightList.add(bean);
                }
                mMyAdapter.replaceData(mRightList);
            }
        });
    }

    @Override
    public void initData() {
        //左侧测试数据
        for (int i = 0; i < 30; i++) {
            if (i == 0) {
                ClassificationLeftBean classificationLeftBean = new ClassificationLeftBean();
                classificationLeftBean.setName("推荐分类");
                classificationLeftBean.setTag(true);
                mLeftList.add(classificationLeftBean);
            } else {
                ClassificationLeftBean classificationLeftBean = new ClassificationLeftBean();
                classificationLeftBean.setName("分类" + i);
                classificationLeftBean.setTag(false);
                mLeftList.add(classificationLeftBean);
            }
        }
        mLeftListAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {

    }

    public class MyAdapter extends BaseQuickAdapter<ClassificationRightBean, BaseViewHolder> {
        public MyAdapter(int layoutResId, List data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, ClassificationRightBean item) {

            helper.setText(R.id.tv_title, item.getTitle());
            CustomGridView gridView = helper.getView(R.id.right_image_list);
            gridView.setAdapter(new ClassificationImageAdapter(getActivity(), item.getUrlList()));
        }
    }
}
