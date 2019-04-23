package com.jingwei.vega.activity;


import android.content.Intent;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jingwei.vega.Constants;
import com.jingwei.vega.R;
import com.jingwei.vega.base.BaseActivity;
import com.jingwei.vega.callback.PermissionsCallback;
import com.jingwei.vega.moudle.bean.DownloadRecordBean;
import com.jingwei.vega.moudle.bean.UploadSearchPicBean;
import com.jingwei.vega.refresh.DefaultFooter;
import com.jingwei.vega.refresh.DefaultHeader;
import com.jingwei.vega.refresh.SpringView;
import com.jingwei.vega.rxhttp.retrofit.ParamBuilder;
import com.jingwei.vega.rxhttp.retrofit.ServiceAPI;
import com.jingwei.vega.rxhttp.rxjava.RxResultFunc;
import com.jingwei.vega.rxhttp.rxjava.RxSubscriber;
import com.jingwei.vega.utils.DisplayUtil;
import com.jingwei.vega.utils.GlideUtil;
import com.luck.picture.lib.PictureSelectionModel;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 下载记录
 */
public class DownloadRecordActivity extends BaseActivity {

    @BindView(R.id.rv_list)
    RecyclerView mRvList;
    @BindView(R.id.spring)
    SpringView mSpring;
    @BindView(R.id.et_content)
    EditText mEtContent;
    @BindView(R.id.iv_camera)
    ImageView mIvCamera;

    private MyAdapter mMyAdapter;
    private List<DownloadRecordBean.PageListBean.ListBean> mBeanList = new ArrayList<>();
    private DownloadRecordBean mDownloadRecordBean;

    private int pager = 1;

    private String msg = "";

    @Override
    public int getContentView() {
        return R.layout.activity_download_record;
    }

    @Override
    public void initTitleBar() {
        getTitleBar().setLeftImage(R.drawable.icon_back).setTitleText("下载记录");
    }

    @Override
    public void initView() {
        mSpring.setHeader(new DefaultHeader(DownloadRecordActivity.this));
        mSpring.setFooter(new DefaultFooter(DownloadRecordActivity.this));
        mMyAdapter = new MyAdapter(R.layout.item_download_product_recycle, mBeanList);
        mMyAdapter.setEmptyView(getEmptyView());
        mRvList.setAdapter(mMyAdapter);
        mRvList.setLayoutManager(new LinearLayoutManager(DownloadRecordActivity.this));
        mRvList.addItemDecoration(new HorizontalDividerItemDecoration.Builder(DownloadRecordActivity.this)
                .color(ContextCompat.getColor(DownloadRecordActivity.this, R.color.gray2))
                .size(DisplayUtil.dp2px(DownloadRecordActivity.this, 0.5f))
                .build());
    }

    @Override
    public void initData() {
        getRefreshInfo();
        setListener();
    }

    private void getRefreshInfo() {
        ServiceAPI.Retrofit().getDownloadRecord(ParamBuilder.newParams()
                .addParam("productName",msg)
                .addParam("pager", pager + "")
                .bulidParam())
                .map(new RxResultFunc<DownloadRecordBean>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<DownloadRecordBean>(DownloadRecordActivity.this, true) {
                    @Override
                    public void onNext(DownloadRecordBean bean) {
                        mDownloadRecordBean = bean;
                        if (bean.getPageList().getList().size() != 0) {
                            if (pager == 1) {
                                mBeanList = bean.getPageList().getList();
                            } else {
                                mBeanList.addAll(bean.getPageList().getList());
                            }
                            mMyAdapter.replaceData(mBeanList);
                        } else {
                            if (pager > 1) {
                                showToast(getResources().getString(R.string.no_more_date));
                            } else {
                                mBeanList.clear();
                                mMyAdapter.replaceData(mBeanList);
                            }
                        }
                        mSpring.onFinishFreshAndLoad();
                    }
                });
    }

    private void setListener() {
        mEtContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DownloadRecordActivity.this, SearchActivity.class);
                intent.putExtra("name", mEtContent.getText().toString().trim());
                startActivityForResult(intent, Constants.MYCOLLECT);
            }
        });

        mIvCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestPermission();
            }
        });

        mSpring.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                getRefreshInfo();
            }

            @Override
            public void onLoadmore() {
                getRefreshInfo();
            }
        });

        mMyAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(DownloadRecordActivity.this,DownloadRecordDetailActivity.class);
                intent.putExtra("id",mBeanList.get(position).getId());
                startActivity(intent);
            }
        });

        mMyAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()){
                    case R.id.ll_shop:
                        Intent intent = new Intent(DownloadRecordActivity.this,ShopActivity.class);
                        intent.putExtra("shopId",mBeanList.get(position).getShopId());
                        startActivity(intent);
                        break;
                }
            }
        });
    }

    /**
     * 动态权限
     */
    private void requestPermission() {
        requestPermissions(new PermissionsCallback() {
            @Override
            public void onAccept() {
                OpenCamera();
            }

            @Override
            public void onDenied() {

            }
        });
    }

    /**
     * 选择照片
     */
    private void OpenCamera() {
        PictureSelectionModel selectionModel = PictureSelector.create(this)
                .openGallery(PictureMimeType.ofImage())//全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
                .maxSelectNum(1)// 最大图片选择数量 int
                .minSelectNum(1)// 最小选择数量 int
                .imageSpanCount(3)// 每行显示个数 int
                .previewImage(true)// 是否可预览图片 true or false
                .previewVideo(true)// 是否可预览视频 true or false
                .enablePreviewAudio(true) // 是否可播放音频 true or false
                .isCamera(true)// 是否显示拍照按钮 true or false
                .isZoomAnim(true)// 图片列表点击 缩放效果 默认true
                .sizeMultiplier(0.7f)// glide 加载图片大小 0~1之间 如设置 .glideOverride()无效
                .enableCrop(true)// 是否裁剪 true or false
                .imageFormat(PictureMimeType.PNG)// 拍照保存图片格式后缀,默认jpeg
                .compress(true)// 是否压缩 true or false
                .withAspectRatio(1, 1)// int 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
                .hideBottomControls(true)// 是否显示uCrop工具栏，默认不显示 true or false
                .isGif(true)// 是否显示gif图片 true or false
                .freeStyleCropEnabled(true)// 裁剪框是否可拖拽 true or false
                .circleDimmedLayer(false)// 是否圆形裁剪 true or false
                .showCropFrame(true)// 是否显示裁剪矩形边框 圆形裁剪时建议设为false   true or false
                .showCropGrid(true)// 是否显示裁剪矩形网格 圆形裁剪时建议设为false    true or false
                .openClickSound(true)// 是否开启点击声音 true or false
                .previewEggs(true)// 预览图片时 是否增强左右滑动图片体验(图片滑动一半即可看到上一张是否选中) true or false
                .minimumCompressSize(100)// 小于100kb的图片不压缩
                .rotateEnabled(true) // 裁剪是否可旋转图片 true or false
                .scaleEnabled(true)// 裁剪是否可放大缩小图片 true or false
                .isDragFrame(true);// 是否可拖动裁剪框(固定)

        //打开相册
        selectionModel.selectionMode(PictureConfig.SINGLE)
                .freeStyleCropEnabled(false)
                .forResult(PictureConfig.CHOOSE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constants.MYCOLLECT) {
            if (data != null) {
                msg = data.getStringExtra("content");
                mEtContent.setText(msg);
                pager = 1;
                getRefreshInfo();
            }
        } //以图搜图
        else if(requestCode == PictureConfig.CHOOSE_REQUEST){
            //获取选中的图片
            String picPath = PictureSelector.obtainMultipleResult(data).get(0).getCompressPath();
            //上传图片获取服务器返回的图片地址，然后将此地址通过商品的搜索接口获取以图搜图的最终搜索结果
            Intent intent = new Intent(DownloadRecordActivity.this,SearchPicActivity.class);
            intent.putExtra("picPath",picPath);
            startActivity(intent);
        }
    }

    public class MyAdapter extends BaseQuickAdapter<DownloadRecordBean.PageListBean.ListBean, BaseViewHolder> {
        public MyAdapter(int layoutResId, List data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, DownloadRecordBean.PageListBean.ListBean item) {
            GlideUtil.setImage(DownloadRecordActivity.this, Constants.IMAGEHOST+item.getMainPic().getPath(), (ImageView) helper.getView(R.id.iv_image));
            helper.setText(R.id.tv_name, item.getProductName());
            helper.setText(R.id.tv_shop, item.getSupplierName());
            helper.setText(R.id.tv_type, item.getMainProducts());

            //增加点击事件
            helper.addOnClickListener(R.id.ll_shop);
        }
    }
}
