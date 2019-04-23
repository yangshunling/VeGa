package com.jingwei.vega.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.gigamole.infinitecycleviewpager.HorizontalInfiniteCycleViewPager;
import com.jingwei.vega.Constants;
import com.jingwei.vega.R;
import com.jingwei.vega.activity.DownloadRecordActivity;
import com.jingwei.vega.activity.GoodsLibActivity;
import com.jingwei.vega.activity.MainActivity;
import com.jingwei.vega.activity.MarketShopsActivity;
import com.jingwei.vega.activity.SearchActivity;
import com.jingwei.vega.activity.SearchPicActivity;
import com.jingwei.vega.adapter.BannerListAdapter;
import com.jingwei.vega.adapter.BrandListAdapter;
import com.jingwei.vega.adapter.HomeListAdapter;
import com.jingwei.vega.base.BaseFragment;
import com.jingwei.vega.callback.PermissionsCallback;
import com.jingwei.vega.moudle.bean.BannerListBean;
import com.jingwei.vega.moudle.bean.BrandListBean;
import com.jingwei.vega.moudle.bean.MarketListBean;
import com.jingwei.vega.refresh.DefaultFooter;
import com.jingwei.vega.refresh.DefaultHeader;
import com.jingwei.vega.refresh.SpringView;
import com.jingwei.vega.rxhttp.retrofit.ParamBuilder;
import com.jingwei.vega.rxhttp.retrofit.ServiceAPI;
import com.jingwei.vega.rxhttp.rxjava.RxResultFunc;
import com.jingwei.vega.rxhttp.rxjava.RxSubscriber;
import com.jingwei.vega.utils.ListViewUtil;
import com.jingwei.vega.utils.PreferencesUtil;
import com.jingwei.vega.view.CustomGridView;
import com.luck.picture.lib.PictureSelectionModel;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
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
    @BindView(R.id.gv_brand)
    CustomGridView mGvBrand;
    @BindView(R.id.iv_camera)
    ImageView mIvCamera;

    private int pageNum = 1;

    private List<BannerListBean.ListBean> mBannerList = new ArrayList<>();
    private BannerListAdapter mBannerListAdapter;

    private List<MarketListBean.ListBeanX.ListBean> mMarketList = new ArrayList<>();
    private HomeListAdapter mListAdapter;

    private List<BrandListBean.ListBeanX.ListBean> mBrandList = new ArrayList<>();
    private BrandListAdapter mBrandListAdapter;

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
        mSpring.setFooter(new DefaultFooter(getActivity()));
    }

    @Override
    public void initData() {
        initBanner();
        getBannerList();
        getMarketList();
        getBrandList();
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

    private void getBrandList() {
        ServiceAPI.Retrofit().getBrand(ParamBuilder.newParams()
                .addParam("pageNumber", pageNum + "")
                .bulidParam())
                .map(new RxResultFunc<BrandListBean>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<BrandListBean>(getActivity()) {
                    @Override
                    public void onNext(BrandListBean bean) {
                        if (pageNum == 1) {
                            mBrandList = bean.getList().getList();
                            initBrandList();
                        } else {
                            mBrandList.addAll(bean.getList().getList());
                            mBrandListAdapter.notifyDataSetChanged();
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

    private void initBrandList() {
        //列表
        mBrandListAdapter = new BrandListAdapter(getActivity(), mBrandList);
        mGvBrand.setAdapter(mBrandListAdapter);
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

        mIvCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestPermission();
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
                intent.putExtra("brandId", "");
                startActivity(intent);
            }
        });

        mGvBrand.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), GoodsLibActivity.class);
                intent.putExtra("tag", "");
                intent.putExtra("brandId", mBrandList.get(position).getId() + "");
                startActivity(intent);
            }
        });

        mSpring.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                pageNum = 1;
                getBannerList();
                getMarketList();
                getBrandList();
            }

            @Override
            public void onLoadmore() {
                pageNum++;
                getBrandList();
            }
        });
    }

    /**
     * 动态权限
     */
    private void requestPermission() {
        ((MainActivity)getActivity()).requestPermissions(new PermissionsCallback() {
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
        }//以图搜图
        else if(requestCode == PictureConfig.CHOOSE_REQUEST){
            //获取选中的图片
            String picPath = PictureSelector.obtainMultipleResult(data).get(0).getCompressPath();
            //上传图片获取服务器返回的图片地址，然后将此地址通过商品的搜索接口获取以图搜图的最终搜索结果
            Intent intent = new Intent(getActivity(), SearchPicActivity.class);
            intent.putExtra("picPath",picPath);
            startActivity(intent);
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
