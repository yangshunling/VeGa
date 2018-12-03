package com.jingwei.vega.activity;


import android.widget.TextView;

import com.jingwei.vega.R;
import com.jingwei.vega.adapter.DynamicImageAdapter;
import com.jingwei.vega.base.BaseActivity;
import com.jingwei.vega.moudle.bean.DynamicBean;
import com.jingwei.vega.view.CustomGridView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 下载记录详情
 */
public class DownloadRecordDetailActivity extends BaseActivity {

    @BindView(R.id.tv_content)
    TextView mTvContent;

    @BindView(R.id.image_list)
    CustomGridView gridView;

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

    }

    @Override
    public void initData() {

        DynamicBean bean = new DynamicBean();
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

        mTvContent.setText(bean.getContent());
        gridView.setAdapter(new DynamicImageAdapter(DownloadRecordDetailActivity.this, bean.getUrlList()));
    }
}
