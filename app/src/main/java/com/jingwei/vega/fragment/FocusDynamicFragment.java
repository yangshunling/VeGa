package com.jingwei.vega.fragment;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jingwei.vega.Constants;
import com.jingwei.vega.R;
import com.jingwei.vega.adapter.DynamicImageAdapter;
import com.jingwei.vega.base.BaseFragment;
import com.jingwei.vega.moudle.FocusSearchMsgEvent;
import com.jingwei.vega.moudle.LibSearchMsgEvent;
import com.jingwei.vega.moudle.bean.BannerListBean;
import com.jingwei.vega.moudle.bean.DynamicBean;
import com.jingwei.vega.refresh.DefaultFooter;
import com.jingwei.vega.refresh.DefaultHeader;
import com.jingwei.vega.refresh.SpringView;
import com.jingwei.vega.rxhttp.okhttp.DownloadUtil;
import com.jingwei.vega.rxhttp.retrofit.ParamBuilder;
import com.jingwei.vega.rxhttp.retrofit.ServiceAPI;
import com.jingwei.vega.rxhttp.rxjava.RxResultFunc;
import com.jingwei.vega.rxhttp.rxjava.RxSubscriber;
import com.jingwei.vega.utils.DisplayUtil;
import com.jingwei.vega.utils.GlideUtil;
import com.jingwei.vega.view.CustomGridView;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class FocusDynamicFragment extends BaseFragment {

    @BindView(R.id.rv_list)
    RecyclerView mRvList;
    @BindView(R.id.spring)
    SpringView mSpring;

    private Integer pager = 1;

    private MyAdapter mMyAdapter;
    private List<DynamicBean.PageListBean.ListBean> mBeanList = new ArrayList<>();

    private int mImgCount = 0;
    private List<DynamicBean.PageListBean.ListBean.ProductBean.PicturesBean> imgList;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    showToast(msg.obj + "");
                    break;
                case 2:
                    showToast(msg.obj + "");
                    break;
            }
        }
    };

    private String msg = "";

    @Override
    public int getContentView() {
        return R.layout.fragment_focus_dynamic;
    }

    @Override
    public void initView(View rootView) {
        EventBus.getDefault().register(this);
        mSpring.setHeader(new DefaultHeader(getActivity()));
        mSpring.setFooter(new DefaultFooter(getActivity()));
        mMyAdapter = new MyAdapter(R.layout.item_dynamic_recycle, mBeanList);
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
                switch (view.getId()) {
                    case R.id.bt_save:
                        imgList = mBeanList.get(position).getProduct().getPictures();
                        if (imgList != null && imgList.size() != 0) {
                            showToast("图片正在保存...");
                            for (int i = 0; i < imgList.size(); i++) {
                                downloadImage(i);
                            }
                        }
                        break;
                    case R.id.bt_copy:
                        ClipboardManager cm = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
                        // 创建普通字符型ClipData
                        ClipData mClipData = ClipData.newPlainText("Label", mBeanList.get(position).getProduct().getProductDesc());
                        // 将ClipData内容放到系统剪贴板里。
                        cm.setPrimaryClip(mClipData);
                        showToast("复制成功");
                        break;
                }
            }
        });
    }

    private void downloadImage(int index) {

        String fileUrl = Constants.IMAGEHOST + imgList.get(index).getPath();
        String filePath = Constants.IMAGEPATH;
        String fileName = System.currentTimeMillis() + ".jpg";

        DownloadUtil.get().download(fileUrl, filePath, fileName, new DownloadUtil.OnDownloadListener() {
            @Override
            public void onDownloadSuccess(File file) {
                Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                Uri uri = Uri.fromFile(file);
                intent.setData(uri);
                getActivity().sendBroadcast(intent);
                //计数
                mImgCount++;
                if (mImgCount == imgList.size()) {
                    mHandler.sendMessage(mHandler.obtainMessage(0, "图片保存成功"));
                }
            }

            @Override
            public void onDownloading(int progress) {
                Log.v("TAG", "进度：" + progress);
            }

            @Override
            public void onDownloadFailed(Exception e) {
                mHandler.sendMessage(mHandler.obtainMessage(2, "下载失败：" + e.getMessage()));
            }
        });
    }

    @Override
    public void initData() {
        getRefresh();
    }

    private void getLoadmore() {
        pager += 1;
        ServiceAPI.Retrofit().getDynamicList(ParamBuilder.newParams()
                .addParam("name", msg)
                .addParam("pageNumber", pager + "")
                .bulidParam())
                .map(new RxResultFunc<DynamicBean>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<DynamicBean>(getActivity()) {
                    @Override
                    public void onNext(DynamicBean bean) {
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
        ServiceAPI.Retrofit().getDynamicList(ParamBuilder.newParams()
                .addParam("name", msg)
                .addParam("pageNumber", pager + "")
                .bulidParam())
                .map(new RxResultFunc<DynamicBean>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<DynamicBean>(getActivity()) {
                    @Override
                    public void onNext(DynamicBean bean) {
                        mBeanList = bean.getPageList().getList();
                        mSpring.onFinishFreshAndLoad();
                        mMyAdapter.replaceData(mBeanList);
                    }
                });
    }

    @Override
    public void onClick(View v) {

    }

    public class MyAdapter extends BaseQuickAdapter<DynamicBean.PageListBean.ListBean, BaseViewHolder> {
        public MyAdapter(int layoutResId, List data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, DynamicBean.PageListBean.ListBean item) {
            GlideUtil.setImage(getActivity(), item.getHeadImg(), (ImageView) helper.getView(R.id.iv_image));
            helper.setText(R.id.tv_name, item.getName());
            helper.setText(R.id.tv_content, item.getProduct().getProductDesc());
            CustomGridView gridView = helper.getView(R.id.image_list);
            gridView.setAdapter(new DynamicImageAdapter(getActivity(), item.getProduct().getPictures()));
            helper.setText(R.id.tv_time, item.getCreatedAt());
            //点击事件
            helper.addOnClickListener(R.id.bt_save);
            helper.addOnClickListener(R.id.bt_copy);
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
