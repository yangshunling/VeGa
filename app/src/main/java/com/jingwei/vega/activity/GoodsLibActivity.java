package com.jingwei.vega.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.View;
import android.widget.EditText;

import com.flyco.tablayout.SlidingTabLayout;
import com.jingwei.vega.Constants;
import com.jingwei.vega.R;
import com.jingwei.vega.adapter.ViewPagerAdapter;
import com.jingwei.vega.base.BaseActivity;
import com.jingwei.vega.callback.PermissionsCallback;
import com.jingwei.vega.fragment.GoodsLibAllFragment;
import com.jingwei.vega.fragment.GoodsLibLatestFragment;
import com.jingwei.vega.fragment.GoodsLibSentimentFragment;
import com.jingwei.vega.moudle.LibSearchMsgEvent;
import com.jingwei.vega.utils.TextUtil;
import com.luck.picture.lib.PictureSelectionModel;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;

public class GoodsLibActivity extends BaseActivity {

    @BindView(R.id.tablayout)
    SlidingTabLayout mTablayout;
    @BindView(R.id.viewpager)
    ViewPager mViewpager;
    @BindView(R.id.et_content)
    EditText mEtContent;

    private GoodsLibAllFragment mAllFragment;
    private GoodsLibLatestFragment mLatestFragment;
    private GoodsLibSentimentFragment mSentimentFragment;
    private ArrayList<Fragment> mFragments = new ArrayList<>();

    private final String[] mTitles = {
            "全部", "最新", "人气"};
    private ViewPagerAdapter mAdapter;

    private String msg = "";
    private String brandId = "";

    @Override
    public int getContentView() {
        return R.layout.activity_goods_lib;
    }

    @Override
    public void initTitleBar() {
        getTitleBar().setLeftImage(R.drawable.icon_back)
                .setTitleText("商品库");
    }

    @Override
    public void initView() {
        msg = getIntent().getStringExtra("tag");
        brandId = getIntent().getStringExtra("brandId");
        mEtContent.setText(msg);

        if (!TextUtil.isEmpty(msg)) {
            hintTitleBar();
        }

        Bundle bundle = new Bundle();
        bundle.putString("tag", msg);
        bundle.putString("brandId", brandId);

        mAllFragment = new GoodsLibAllFragment();
        mAllFragment.setArguments(bundle);
        mLatestFragment = new GoodsLibLatestFragment();
        mLatestFragment.setArguments(bundle);
        mSentimentFragment = new GoodsLibSentimentFragment();
        mSentimentFragment.setArguments(bundle);

        mFragments.add(mAllFragment);
        mFragments.add(mLatestFragment);
        mFragments.add(mSentimentFragment);

        mAdapter = new ViewPagerAdapter(getSupportFragmentManager(), mFragments, mTitles);
        mViewpager.setAdapter(mAdapter);
        mViewpager.setOffscreenPageLimit(2);
        mTablayout.setViewPager(mViewpager, mTitles, GoodsLibActivity.this, mFragments);
    }

    @Override
    public void initData() {
        mViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    @OnClick({R.id.et_content,R.id.iv_camera})
    public void onViewClicked(View view) {
        switch (view.getId()){
            case R.id.et_content:
                Intent intent = new Intent(GoodsLibActivity.this, SearchActivity.class);
                intent.putExtra("name", mEtContent.getText().toString().trim());
                startActivityForResult(intent, Constants.GOODSLIBACTIVITY);
                break;

            case R.id.iv_camera:
                requestPermission();
                break;
        }
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
        if (requestCode == Constants.GOODSLIBACTIVITY) {
            if (data != null) {
                msg = data.getStringExtra("content");
                mEtContent.setText(msg);
                EventBus.getDefault().post(new LibSearchMsgEvent(msg));
            }
        }//以图搜图
        else if(requestCode == PictureConfig.CHOOSE_REQUEST){
            //获取选中的图片
            String picPath = PictureSelector.obtainMultipleResult(data).get(0).getCompressPath();
            //上传图片获取服务器返回的图片地址，然后将此地址通过商品的搜索接口获取以图搜图的最终搜索结果
            Intent intent = new Intent(GoodsLibActivity.this,SearchPicActivity.class);
            intent.putExtra("picPath",picPath);
            startActivity(intent);
        }
    }
}
