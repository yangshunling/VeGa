package com.jingwei.vega.activity;


import android.widget.TextView;

import com.jingwei.vega.Constants;
import com.jingwei.vega.R;
import com.jingwei.vega.adapter.DynamicImageAdapter;
import com.jingwei.vega.adapter.MarkShopPicAdapter;
import com.jingwei.vega.base.BaseActivity;
import com.jingwei.vega.moudle.bean.DownloadRecordDetailBean;
import com.jingwei.vega.moudle.bean.DynamicBean;
import com.jingwei.vega.rxhttp.retrofit.ServiceAPI;
import com.jingwei.vega.rxhttp.rxjava.RxResultFunc;
import com.jingwei.vega.rxhttp.rxjava.RxSubscriber;
import com.jingwei.vega.view.CustomGridView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 下载记录详情
 */
public class DownloadRecordDetailActivity extends BaseActivity {

    @BindView(R.id.tv_content)
    TextView mTvContent;

    @BindView(R.id.image_list)
    CustomGridView gridView;

    private int id;

    @Override
    public int getContentView() {
        return R.layout.activity_download_record_detail;
    }

    @Override
    public void initTitleBar() {
        getTitleBar().setLeftImage(R.drawable.icon_back).setTitleText("下载记录");
    }

    @Override
    public void initView() {
        id = getIntent().getIntExtra("id",0);
    }

    @Override
    public void initData() {
        ServiceAPI.Retrofit().getDownloadRecordDetail(id+"")
                .map(new RxResultFunc<DownloadRecordDetailBean>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<DownloadRecordDetailBean>(DownloadRecordDetailActivity.this, true) {
                    @Override
                    public void onNext(DownloadRecordDetailBean bean) {
                        mTvContent.setText(bean.getProductDesc());

                        List<String> imageList = new ArrayList<>();
                        if (bean.getPictures()!= null && bean.getPictures().size() > 0) {
                            for (int i = 0; i < bean.getPictures().size(); i++) {
                                imageList.add(Constants.IMAGEHOST + bean.getPictures().get(i).getPath());
                            }
                        }
                        gridView.setAdapter(new MarkShopPicAdapter(DownloadRecordDetailActivity.this, imageList));
                    }
                });
    }
}
