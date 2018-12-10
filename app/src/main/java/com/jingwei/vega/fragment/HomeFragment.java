package com.jingwei.vega.fragment;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.EditText;
import android.widget.ListView;

import com.gigamole.infinitecycleviewpager.HorizontalInfiniteCycleViewPager;
import com.jingwei.vega.Constants;
import com.jingwei.vega.R;
import com.jingwei.vega.activity.SearchActivity;
import com.jingwei.vega.adapter.BannerListAdapter;
import com.jingwei.vega.adapter.HomeListAdapter;
import com.jingwei.vega.base.BaseFragment;
import com.jingwei.vega.moudle.bean.BannerListBean;
import com.jingwei.vega.moudle.bean.HomeBean;
import com.jingwei.vega.moudle.bean.MarketListBean;
import com.jingwei.vega.rxhttp.retrofit.ServiceAPI;
import com.jingwei.vega.rxhttp.rxjava.RxResultFunc;
import com.jingwei.vega.rxhttp.rxjava.RxSubscriber;
import com.jingwei.vega.utils.ListViewUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class HomeFragment extends BaseFragment {

    @BindView(R.id.home_list)
    ListView mHomeList;
    @BindView(R.id.rl_banner)
    HorizontalInfiniteCycleViewPager mRlBanner;
    @BindView(R.id.et_content)
    EditText mEtContent;

    private List<BannerListBean.ListBean> mBannerList = new ArrayList<>();
    private BannerListAdapter mBannerListAdapter;
    private List<MarketListBean.ListBeanX.ListBean> mMarketList = new ArrayList<>();
    private HomeListAdapter mListAdapter;
    private Timer timer = new Timer();

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            mRlBanner.setCurrentItem(mRlBanner.getRealItem() + 1);
        }
    };

    @Override
    public int getContentView() {
        return R.layout.fragment_home;
    }

    @Override
    public void initView(View rootView) {
        initMarketList();
        initBanner();
    }

    @Override
    public void initData() {
        //轮播图
        getBannerList();
        //市场列表
        getMarketList();
    }

    private void getBannerList() {
        ServiceAPI.Retrofit().getBanner()
                .map(new RxResultFunc<BannerListBean>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<BannerListBean>(getActivity()) {
                    @Override
                    public void onNext(BannerListBean bean) {
                        mBannerList = bean.getList();
                        initBanner();
                        startSchedule();
                    }
                });
    }

    private void initBanner() {
        mBannerListAdapter = new BannerListAdapter(getActivity(), mBannerList);
        mRlBanner.setAdapter(mBannerListAdapter);
        mRlBanner.setInterpolator(new LinearInterpolator());
        mRlBanner.requestFocus();
        mRlBanner.setFocusableInTouchMode(true);
    }

    private void getMarketList() {
        ServiceAPI.Retrofit().getMarketList()
                .map(new RxResultFunc<MarketListBean>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<MarketListBean>(getActivity()) {
                    @Override
                    public void onNext(MarketListBean bean) {
                        mMarketList = bean.getList().getList();
                        initMarketList();
                    }
                });
    }

    private void initMarketList() {
        //列表
        mListAdapter = new HomeListAdapter(getActivity(), mMarketList);
        mHomeList.setAdapter(mListAdapter);
        ListViewUtil.setListViewHeightBasedOnChildren(mHomeList);
    }


    private void startSchedule() {
        timer.schedule(new TimerTask() {
            public void run() {
                mHandler.sendEmptyMessage(0);
            }
        }, 3000, 3000);
    }

    @Override
    protected void setListener() {
        mEtContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SearchActivity.class);
                intent.putExtra("name", Constants.DEMOACTIVITY);
                startActivityForResult(intent, Constants.HOMEFRAGMENT);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constants.HOMEFRAGMENT) {
            if (data != null) {
                String msg = data.getStringExtra("content");
                mEtContent.setText(msg);
                showToast(msg);
            }
        }
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        timer.cancel();
    }
}
