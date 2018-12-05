package com.jingwei.vega.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.EditText;
import android.widget.ListView;

import com.gigamole.infinitecycleviewpager.HorizontalInfiniteCycleViewPager;
import com.jingwei.vega.R;
import com.jingwei.vega.activity.SearchActivity;
import com.jingwei.vega.adapter.BannerListAdapter;
import com.jingwei.vega.adapter.HomeListAdapter;
import com.jingwei.vega.base.BaseFragment;
import com.jingwei.vega.moudle.ScheduleEvent;
import com.jingwei.vega.moudle.SearchRecordEvent;
import com.jingwei.vega.moudle.bean.HomeBean;
import com.jingwei.vega.utils.ListViewUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;

public class HomeFargment extends BaseFragment {

    @BindView(R.id.home_list)
    ListView mHomeList;
    @BindView(R.id.rl_banner)
    HorizontalInfiniteCycleViewPager mRlBanner;
    @BindView(R.id.et_content)
    EditText mEtContent;

    private List<String> mBannerList = new ArrayList<>();
    private List<HomeBean> mBeanList = new ArrayList<>();
    private HomeListAdapter mListAdapter;
    private Timer timer = new Timer();

    private Integer pagerIndex = 0;

    @Override
    public int getContentView() {
        return R.layout.fragment_home;
    }

    @Override
    public void initView(View rootView) {
        EventBus.getDefault().register(this);
    }

    @Override
    public void initData() {
        //轮播图测试数据
        for (int i = 0; i < 2; i++) {
            mBannerList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1544256191&di=63fa160f4e838ddd1a46c9033f464af9&imgtype=jpg&er=1&src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F0136db56a8337a32f875520fcda8ec.jpg");
            mBannerList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1543834276504&di=558d8d0aae63b7992d2aa9f8ef851307&imgtype=0&src=http%3A%2F%2Ffile25.mafengwo.net%2FM00%2F66%2FD8%2FwKgB4lMcWvyAE9xYAAC8zPK6yMw35.jpeg");
        }
        initBanner();
        //列表测试数据
        for (int i = 0; i < 3; i++) {
            HomeBean homeBean = new HomeBean();
            homeBean.setTitle("市场推荐 //");
            List<HomeBean.CardBean> cb = new ArrayList<>();
            for (int j = 0; j < 4; j++) {
                HomeBean.CardBean bean = new HomeBean.CardBean();
                bean.setUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1543834276504&di=558d8d0aae63b7992d2aa9f8ef851307&imgtype=0&src=http%3A%2F%2Ffile25.mafengwo.net%2FM00%2F66%2FD8%2FwKgB4lMcWvyAE9xYAAC8zPK6yMw35.jpeg");
                bean.setName("杭州" + j);
                cb.add(bean);
            }
            homeBean.setCardBeans(cb);
            mBeanList.add(homeBean);
        }
        initListview();
    }

    private void initListview() {
        //列表
        mListAdapter = new HomeListAdapter(getActivity(), mBeanList);
        mHomeList.setAdapter(mListAdapter);
        ListViewUtil.setListViewHeightBasedOnChildren(mHomeList);
    }

    private void initBanner() {
        mRlBanner.setAdapter(new BannerListAdapter(getActivity(), mBannerList));
        mRlBanner.setInterpolator(new LinearInterpolator());
        mRlBanner.startAutoScroll(true);
//        startSchedule();
    }

    private void startSchedule() {
        timer.schedule(new TimerTask() {
            public void run() {
                pagerIndex++;
                if (pagerIndex == mBannerList.size()) {
                    pagerIndex = 0;
                }
                EventBus.getDefault().post(new ScheduleEvent(pagerIndex));
            }
        }, 3000, 3000);
    }

    @Subscribe(threadMode = ThreadMode.MainThread)
    public void scheduleTask(ScheduleEvent event) {
        mRlBanner.setCurrentItem(event.getIndex(), false);
    }

    @Subscribe(threadMode = ThreadMode.MainThread)
    public void searcheTask(SearchRecordEvent event) {
        mEtContent.setText(event.getContent());
        showToast(event.getContent());
    }

    @Override
    protected void setListener() {
        mEtContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),SearchActivity.class);
                intent.putExtra("tag","home");
                startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
