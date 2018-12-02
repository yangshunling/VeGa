package com.jingwei.vega.fragment;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.jingwei.vega.R;
import com.jingwei.vega.adapter.ClassificationListAdapter;
import com.jingwei.vega.base.BaseFragment;
import com.jingwei.vega.moudle.bean.ClassificationLeftBean;
import com.jingwei.vega.refresh.DefaultFooter;
import com.jingwei.vega.refresh.DefaultHeader;
import com.jingwei.vega.refresh.SpringView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ClassificationFragment extends BaseFragment {
    @BindView(R.id.et_phone)
    EditText mEtPhone;
    @BindView(R.id.lv_left)
    ListView mLvLeft;
    @BindView(R.id.rv_right)
    RecyclerView mRvRight;

    private ClassificationListAdapter mLeftListAdapter;

    private List<ClassificationLeftBean> mLeftList = new ArrayList<>();

    @Override
    public int getContentView() {
        return R.layout.fragment_classification;
    }

    @Override
    public void initView(View rootView) {
        mLeftListAdapter = new ClassificationListAdapter(getActivity(), mLeftList);
        mLvLeft.setAdapter(mLeftListAdapter);
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
            }
        });
    }

    @Override
    public void initData() {
        //测试数据
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
}
