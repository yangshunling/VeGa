package com.jingwei.vega.fragment;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jingwei.vega.Constants;
import com.jingwei.vega.R;
import com.jingwei.vega.activity.AboutUsActivity;
import com.jingwei.vega.activity.DownloadRecordActivity;
import com.jingwei.vega.activity.MainActivity;
import com.jingwei.vega.activity.MyCollectActivity;
import com.jingwei.vega.activity.SettingActivity;
import com.jingwei.vega.activity.VipCenterActivity;
import com.jingwei.vega.base.BaseFragment;
import com.jingwei.vega.callback.PermissionsCallback;
import com.jingwei.vega.moudle.bean.UserInfoBean;
import com.jingwei.vega.rxhttp.retrofit.ParamBuilder;
import com.jingwei.vega.rxhttp.retrofit.ServiceAPI;
import com.jingwei.vega.rxhttp.rxjava.RxResultFunc;
import com.jingwei.vega.rxhttp.rxjava.RxSubscriber;
import com.jingwei.vega.utils.GlideUtil;
import com.jingwei.vega.view.CustomLinearLayout;
import com.luck.picture.lib.PictureSelectionModel;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;
import pub.devrel.easypermissions.EasyPermissions;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static android.app.Activity.RESULT_OK;

public class MeFragment extends BaseFragment{

    @BindView(R.id.my_bg)
    ImageView mMyBg;
    @BindView(R.id.iv_setting)
    ImageView mIvSetting;
    @BindView(R.id.user_icon)
    ImageView mUserIcon;
    @BindView(R.id.tv_name)
    TextView mTvName;
    @BindView(R.id.iv_vip)
    ImageView mIvVip;
    @BindView(R.id.tv_phone)
    TextView mTvPhone;
    @BindView(R.id.ll_collection)
    CustomLinearLayout mLlCollection;
    @BindView(R.id.ll_download)
    CustomLinearLayout mLlDownload;
    @BindView(R.id.ll_vip)
    CustomLinearLayout mLlVip;
    @BindView(R.id.ll_about)
    CustomLinearLayout mLlAbout;
    Unbinder unbinder;

    private UserInfoBean userInfoBean;
    private String headPath = "";

    @Override
    public int getContentView() {
        return R.layout.fragment_me;
    }

    @Override
    public void initView(View rootView) {

    }

    @Override
    protected void setListener() {

    }

    @Override
    public void initData() {
        getUserInfo();
    }

    private void getUserInfo() {
        ServiceAPI.Retrofit().getUserInfo()
                .map(new RxResultFunc<UserInfoBean>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<UserInfoBean>(getActivity()) {
                    @Override
                    public void onNext(UserInfoBean bean) {
                        userInfoBean = bean;

                        mTvName.setText(bean.getNickName());
                        mTvPhone.setText(bean.getMobile());
                        mIvVip.setBackground(bean.isIsMember()?getResources().getDrawable(R.drawable.icon_vip):getResources().getDrawable(R.drawable.icon_unvip));
                        if(!TextUtils.isEmpty(bean.getHeadImg())){
                            GlideUtil.setImage(getActivity(),Constants.IMAGEHOST+bean.getHeadImg(),mUserIcon);
                        }
                    }
                });
    }

    @Override
    public void onClick(View v) {

    }

    @OnClick({R.id.iv_setting, R.id.ll_collection, R.id.ll_download, R.id.ll_vip, R.id.ll_about,R.id.user_icon})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_setting:
                Intent intent = new Intent(getActivity(),SettingActivity.class);
                intent.putExtra("userInfo",userInfoBean);
                startActivity(intent);
                break;
            case R.id.ll_collection:
                startActivity(new Intent(getActivity(), MyCollectActivity.class));
                break;
            case R.id.ll_download:
                startActivity(new Intent(getActivity(), DownloadRecordActivity.class));
                break;
            case R.id.ll_vip:
                Intent intentVip = new Intent(getActivity(),VipCenterActivity.class);
                startActivity(intentVip);
                break;
            case R.id.ll_about:
                startActivity(new Intent(getActivity(), AboutUsActivity.class));
                break;
            case R.id.user_icon:
                requestPermission();
                break;
        }
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

    //选择头像结果回调
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    //获取选中的图片
                    headPath = PictureSelector.obtainMultipleResult(data).get(0).getCompressPath();
                    //头像上传
                    updataHead();
                    break;
            }
        }
    }

    /**
     * 修改头像
     */
    private void updataHead() {
        ServiceAPI.Retrofit().userHeadIconUpdate(ParamBuilder.newBody()
                .addBody("file", new File(headPath))
                .bulidBody())
                .map(new RxResultFunc<Object>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<Object>(getActivity(), "更新头像中...") {
                    @Override
                    public void onNext(Object bean) {
                        showToast("修改头像成功");
                        GlideUtil.setImage(getActivity(),headPath,mUserIcon);
                    }
                });
    }

    @Override
    public void onResume() {
        super.onResume();
        getUserInfo();
    }
}
