package com.jingwei.vega.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;

import com.jingwei.vega.R;
import com.jingwei.vega.base.BaseFragment;
import com.jingwei.vega.utils.GlideUtil;
import com.jingwei.vega.view.cardgallery.CardGalleryAdapter;
import com.jingwei.vega.view.cardgallery.CardScaleHelper;
import com.jingwei.vega.view.cardgallery.SpeedRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class HomeFargment extends BaseFragment {

    private SpeedRecyclerView mRecyclerView;
    private List<String> mBeanList = new ArrayList<>();
    private CardScaleHelper mCardScaleHelper = null;

    @Override
    public int getContentView() {
        return R.layout.fragment_home;
    }

    @Override
    public void initView(View rootView) {
        mRecyclerView = (SpeedRecyclerView) rootView.findViewById(R.id.recyclerView);
    }

    @Override
    public void initData() {
        mBeanList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1544256191&di=63fa160f4e838ddd1a46c9033f464af9&imgtype=jpg&er=1&src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F0136db56a8337a32f875520fcda8ec.jpg");
        mBeanList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1543661471451&di=71e9833a2611a25613c44eb954402e3f&imgtype=0&src=http%3A%2F%2F03img.mopimg.cn%2Fmobile%2F20180109%2F20180109170328_dd813f33a2c0ffa72ac02326fe24dbc9_9.jpeg");
        mBeanList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1544256262&di=d8219c847bf9fb14faef076a67ff093c&imgtype=jpg&er=1&src=http%3A%2F%2F5b0988e595225.cdn.sohucs.com%2Fq_70%2Cc_zoom%2Cw_640%2Fimages%2F20180705%2F4251e5bdadf8438995093c1009dbd3b9.jpeg");
        initRecyclerView();
    }

    private void initRecyclerView() {
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(new CardGalleryAdapter(getActivity(), mBeanList));
        // mRecyclerView绑定scale效果
        mCardScaleHelper = new CardScaleHelper();
        mCardScaleHelper.setCurrentItemPos(2);
        mCardScaleHelper.attachToRecyclerView(mRecyclerView);
    }

    @Override
    protected void setListener() {

    }

    @Override
    public void onClick(View v) {

    }
}
