package com.jingwei.vega.fragment;

import android.content.Intent;
import android.net.Uri;
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
import com.jingwei.vega.moudle.bean.DynamicBean;
import com.jingwei.vega.refresh.DefaultFooter;
import com.jingwei.vega.refresh.DefaultHeader;
import com.jingwei.vega.refresh.SpringView;
import com.jingwei.vega.rxhttp.okhttp.DownloadUtil;
import com.jingwei.vega.utils.DisplayUtil;
import com.jingwei.vega.utils.GlideUtil;
import com.jingwei.vega.view.CustomGridView;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;

public class FocusDynamicFragment extends BaseFragment {

    @BindView(R.id.rv_list)
    RecyclerView mRvList;
    @BindView(R.id.spring)
    SpringView mSpring;

    private MyAdapter mMyAdapter;
    private List<DynamicBean> mBeanList = new ArrayList<>();

    @Override
    public int getContentView() {
        return R.layout.fragment_focus_dynamic;
    }

    @Override
    public void initView(View rootView) {
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

            }

            @Override
            public void onLoadmore() {

            }
        });

        mMyAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                showToast("图片正在保存...");
                downloadImage(position);
            }
        });
    }

    private void downloadImage(int position) {
        String fileUrl = mBeanList.get(position).getUrlList().get(0);
        String filePath = Constants.IMAGEPATH;
        String fileName = System.currentTimeMillis() + ".jpg";
        DownloadUtil.get().download(fileUrl, filePath, fileName, new DownloadUtil.OnDownloadListener() {
            @Override
            public void onDownloadSuccess(File file) {
                showToast("图片保存成功!");
                Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                Uri uri = Uri.fromFile(file);
                intent.setData(uri);
                getActivity().sendBroadcast(intent);
                Log.v("TAG", "图库同步完成");
            }

            @Override
            public void onDownloading(int progress) {
                Log.v("TAG", "进度：" + progress);
            }

            @Override
            public void onDownloadFailed(Exception e) {
                showToast("下载失败：" + e.getMessage());
            }
        });
    }

    @Override
    public void initData() {
        //测试数据
        for (int i = 0; i < 20; i++) {
            DynamicBean bean = new DynamicBean();
            bean.setImage("http://img18.3lian.com/d/file/201709/21/d8768c389b316e95ef29276c53a1e964.jpg");
            bean.setName("咕咕店铺");
            bean.setContent("由于这是付费推广，而且不能保证有交易，所以在宝贝选择上、价格控制、悬挂频道和方式都是很有讲究的。");

            List<String> url = new ArrayList<>();
            url.add("http://life.southmoney.com/tuwen/UploadFiles_6871/201801/20180129110733180.jpg");
            url.add("http://imgsrc.baidu.com/imgad/pic/item/37d12f2eb9389b50768d956e8e35e5dde7116e9f.jpg");
            url.add("http://image.fvideo.cn/uploadfile/2015/06/04/img28567642935153.jpg");
            url.add("http://pic1.win4000.com/tj/2018-09-27/5baca186a6da0.jpg");
            url.add("http://imgsrc.baidu.com/imgad/pic/item/5366d0160924ab18dcb75d0a3efae6cd7b890b6d.jpg");
            url.add("http://imgsrc.baidu.com/imgad/pic/item/03087bf40ad162d96101d0211adfa9ec8a13cd24.jpg");
            url.add("http://n.sinaimg.cn/sinacn00/453/w593h660/20181012/0fac-hkrzvkw6887569.jpg");
            url.add("http://pic.51yuansu.com/pic3/cover/01/26/61/59230a63c4f9f_610.jpg");
            url.add("http://image1.miss-no1.com/uploadfile/2016/02/29/img59104395993189.jpg");

            bean.setUrlList(url);
            bean.setTime("2018/02/01 22:38");
            mBeanList.add(bean);
        }
        mMyAdapter.replaceData(mBeanList);
    }

    @Override
    public void onClick(View v) {

    }

    public class MyAdapter extends BaseQuickAdapter<DynamicBean, BaseViewHolder> {
        public MyAdapter(int layoutResId, List data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, DynamicBean item) {
            GlideUtil.setImage(getActivity(), item.getImage(), (ImageView) helper.getView(R.id.iv_image));
            helper.setText(R.id.tv_name, item.getName());
            helper.setText(R.id.tv_content, item.getContent());
            CustomGridView gridView = helper.getView(R.id.image_list);
            gridView.setAdapter(new DynamicImageAdapter(getActivity(), item.getUrlList()));
            helper.setText(R.id.tv_time, item.getTime());
            //点击事件
            helper.addOnClickListener(R.id.bt_save);
        }
    }
}
