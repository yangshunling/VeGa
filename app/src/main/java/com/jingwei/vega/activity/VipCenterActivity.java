package com.jingwei.vega.activity;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jingwei.vega.Constants;
import com.jingwei.vega.R;
import com.jingwei.vega.base.BaseActivity;
import com.jingwei.vega.moudle.bean.UserInfoBean;
import com.jingwei.vega.moudle.bean.VipBean;
import com.jingwei.vega.rxhttp.retrofit.ServiceAPI;
import com.jingwei.vega.rxhttp.rxjava.RxResultFunc;
import com.jingwei.vega.rxhttp.rxjava.RxSubscriber;
import com.jingwei.vega.utils.GlideUtil;

import butterknife.BindView;
import butterknife.OnClick;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 会员中心
 */
public class VipCenterActivity extends BaseActivity {

    @BindView(R.id.iv_left_finish)
    ImageView mIvLeftFinsh;

    @BindView(R.id.user_icon)
    ImageView mUserIcon;

    @BindView(R.id.tv_name)
    TextView mTvName;

    @BindView(R.id.tv_date_title)
    TextView mTvDateTitle;

    @BindView(R.id.tv_date)
    TextView mTvDate;

    @BindView(R.id.tv_remark)
    TextView mTvRemark;

    private UserInfoBean userInfoBean;

    private VipBean mVipBean;

    @Override
    public int getContentView() {
        return R.layout.activity_vip_center;
    }

    @Override
    public void initTitleBar() {
        hintTitleBar();
        //沉浸式图片
        setTransparent();
    }

    @Override
    public void initView() {
        userInfoBean = (UserInfoBean) getIntent().getSerializableExtra("userInfo");

        mTvName.setText(userInfoBean.getNickName());
        if(!TextUtils.isEmpty(userInfoBean.getHeadImg())){
            GlideUtil.setImage(VipCenterActivity.this, Constants.IMAGEHOST+userInfoBean.getHeadImg(),mUserIcon);
        }
        if(TextUtils.isEmpty(userInfoBean.getMemberTag()) || TextUtils.isEmpty(userInfoBean.getEndAt())){
            //有一个为空时不显示
            mTvDateTitle.setText("");
            mTvDate.setText("");
        }else {
            mTvDateTitle.setText(userInfoBean.getMemberTag() + "：");
            mTvDate.setText("至" + userInfoBean.getEndAt());
        }
    }

    @Override
    public void initData() {
        ServiceAPI.Retrofit().getVipList()
                .map(new RxResultFunc<VipBean>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<VipBean>(VipCenterActivity.this) {
                    @Override
                    public void onNext(VipBean bean) {
                        mVipBean = bean;
                        mTvRemark.setText(mVipBean.getRemark());
                    }
                });
    }

    @OnClick({R.id.iv_left_finish})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_left_finish:
                finish();
                break;
        }
    }
}
