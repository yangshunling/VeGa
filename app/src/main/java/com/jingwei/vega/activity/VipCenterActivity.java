package com.jingwei.vega.activity;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jingwei.vega.Constants;
import com.jingwei.vega.R;
import com.jingwei.vega.base.BaseActivity;
import com.jingwei.vega.moudle.bean.UserInfoBean;
import com.jingwei.vega.moudle.bean.VipBean;
import com.jingwei.vega.rxhttp.retrofit.ServiceAPI;
import com.jingwei.vega.rxhttp.rxjava.RxResultFunc;
import com.jingwei.vega.rxhttp.rxjava.RxSubscriber;
import com.jingwei.vega.utils.DisplayUtil;
import com.jingwei.vega.utils.GlideUtil;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

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
    
    @BindView(R.id.rv_vip_type)
    RecyclerView mRvVipType;

    private UserInfoBean userInfoBean;

    private MyAdapter mMyAdapter;
    private List<VipBean.MemberGradeListBean> vipMemberTypeBean = new ArrayList<>();

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

        mMyAdapter = new MyAdapter(R.layout.item_vip_remark, vipMemberTypeBean);
        mMyAdapter.setEmptyView(getEmptyView());
        mRvVipType.setAdapter(mMyAdapter);
        mRvVipType.setLayoutManager(new LinearLayoutManager(VipCenterActivity.this));
        mRvVipType.addItemDecoration(new HorizontalDividerItemDecoration.Builder(VipCenterActivity.this)
                .color(ContextCompat.getColor(VipCenterActivity.this, R.color.gray2))
                .size(DisplayUtil.dp2px(VipCenterActivity.this, 0))
                .build());
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

                        vipMemberTypeBean = bean.getMemberGradeList();

                        mMyAdapter.replaceData(vipMemberTypeBean);
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

    public class MyAdapter extends BaseQuickAdapter<VipBean.MemberGradeListBean, BaseViewHolder> {
        public MyAdapter(int layoutResId, List data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, VipBean.MemberGradeListBean item) {
            helper.setText(R.id.tv_des,item.getAmount()+"元·"+item.getName());
        }
    }
}
