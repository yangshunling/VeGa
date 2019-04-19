package com.jingwei.vega.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.gigamole.infinitecycleviewpager.HorizontalInfiniteCycleViewPager;
import com.jingwei.vega.Constants;
import com.jingwei.vega.R;
import com.jingwei.vega.activity.GoodsLibActivity;
import com.jingwei.vega.activity.MarketShopsActivity;
import com.jingwei.vega.activity.SearchActivity;
import com.jingwei.vega.adapter.BannerListAdapter;
import com.jingwei.vega.adapter.HomeListAdapter;
import com.jingwei.vega.base.BaseFragment;
import com.jingwei.vega.moudle.bean.BannerListBean;
import com.jingwei.vega.moudle.bean.MarketListBean;
import com.jingwei.vega.refresh.DefaultHeader;
import com.jingwei.vega.refresh.SpringView;
import com.jingwei.vega.rxhttp.retrofit.ServiceAPI;
import com.jingwei.vega.rxhttp.rxjava.RxResultFunc;
import com.jingwei.vega.rxhttp.rxjava.RxSubscriber;
import com.jingwei.vega.utils.ListViewUtil;
import com.jingwei.vega.utils.PreferencesUtil;

import java.io.Serializable;
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
    @BindView(R.id.spring)
    SpringView mSpring;
    @BindView(R.id.cv_goods_lib)
    CardView mCvGoodsLib;

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
        mSpring.setHeader(new DefaultHeader(getActivity()));
    }

    @Override
    public void initData() {
        initBanner();
        getBannerList();
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
                        if (mBannerList.size() > 0) {
                            PreferencesUtil.saveBannerList(getActivity(), mBannerList);
                        }
                        mSpring.onFinishFreshAndLoad();
                    }
                });
    }

    private void initBanner() {
        mBannerList = PreferencesUtil.getBannerList(getActivity());
        mBannerListAdapter = new BannerListAdapter(getActivity(), mBannerList);
        mRlBanner.setAdapter(mBannerListAdapter);
        mRlBanner.setInterpolator(new LinearInterpolator());
        startSchedule();
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
                        if (mListAdapter == null) {
                            initMarketList();
                        } else {
                            mListAdapter.notifyDataSetChanged();
                        }
                        mSpring.onFinishFreshAndLoad();
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
                intent.putExtra("name", mEtContent.getText().toString().trim());
                startActivityForResult(intent, Constants.HOMEFRAGMENT);
            }
        });

        mHomeList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), MarketShopsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("marketId", mMarketList.get(position).getId());
                bundle.putSerializable("marketList", (Serializable) mMarketList);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        mCvGoodsLib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), GoodsLibActivity.class);
                intent.putExtra("tag", "");
                startActivity(intent);
            }
        });

        mSpring.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                getBannerList();
                getMarketList();
            }

            @Override
            public void onLoadmore() {

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
                Intent intent = new Intent(getActivity(), GoodsLibActivity.class);
                intent.putExtra("tag", msg);
                startActivity(intent);
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        timer.cancel();
    }

    @Override
    public void onClick(View v) {

    }
}
