package com.jingwei.vega.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jingwei.vega.R;
import com.jingwei.vega.adapter.DynamicImageAdapter;
import com.jingwei.vega.base.BaseActivity;
import com.jingwei.vega.moudle.bean.DynamicBean;
import com.jingwei.vega.moudle.bean.HomeBean;
import com.jingwei.vega.refresh.DefaultFooter;
import com.jingwei.vega.refresh.DefaultHeader;
import com.jingwei.vega.refresh.SpringView;
import com.jingwei.vega.utils.AppUtils;
import com.jingwei.vega.utils.GlideUtil;
import com.jingwei.vega.view.CustomGridView;
import com.zyyoona7.popup.EasyPopup;
import com.zyyoona7.popup.XGravity;
import com.zyyoona7.popup.YGravity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * Created by cxc on 2018/12/3.
 * 市场店铺界面
 */
public class MarketShopsActivity extends BaseActivity{

    @BindView(R.id.rl_choose_market_shops)
    RelativeLayout mRlChooseMarketShops;

    @BindView(R.id.iv_choose_market_shops)
    ImageView mIvChooseMarketShops;

    @BindView(R.id.rv_market_shops)
    RecyclerView mRvMarketShops;

    @BindView(R.id.spring)
    SpringView mSpring;

    @BindView(R.id.tv_tran)
    TextView mTran;//透明罩子

    private EasyPopup mCirclePop;

    private MarketShopsAdapter mMarketShopsAdapter;
    private List<DynamicBean> mBeanList = new ArrayList<>();

    //选择市场商铺dialog的适配
    private DialogMarketShopsAdapter mDialogMarketShopsAdapter;
    private List<HomeBean.CardBean> cb = new ArrayList<>();

    @Override
    public int getContentView() {
        return R.layout.activity_market_shops;
    }

    @Override
    public void initTitleBar() {
        getTitleBar().setTitleText("市场商铺");
    }

    @Override
    public void initView() {
        mSpring.setHeader(new DefaultHeader(MarketShopsActivity.this));
        mSpring.setFooter(new DefaultFooter(MarketShopsActivity.this));
        mMarketShopsAdapter = new MarketShopsAdapter(R.layout.item_market_shops_recycle, mBeanList);
        mMarketShopsAdapter.setEmptyView(getEmptyView());
        mRvMarketShops.setAdapter(mMarketShopsAdapter);
        mRvMarketShops.setLayoutManager(new LinearLayoutManager(MarketShopsActivity.this));
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

            bean.setUrlList(url);
            bean.setTime("2018/02/01 22:38");
            mBeanList.add(bean);
        }
        mMarketShopsAdapter.replaceData(mBeanList);

        mMarketShopsAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                startActivity(new Intent(MarketShopsActivity.this,ShopActivity.class));
            }
        });
    }

    @OnClick({R.id.rl_choose_market_shops})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_choose_market_shops:
                showMarketShops();
                break;
        }
    }

    //展示商铺选择
    private void showMarketShops() {
        if (mCirclePop == null) {
            mCirclePop = EasyPopup.create()
                    .setContentView(MarketShopsActivity.this, R.layout.dialog_top_market_shops)
                    .setAnimationStyle(R.style.TopDialog)
                    .setWidth(AppUtils.getScreenWidth(MarketShopsActivity.this))
                    .setFocusAndOutsideEnable(true)
                    .setOnDismissListener(new PopupWindow.OnDismissListener() {
                        @Override
                        public void onDismiss() {
                            mTran.setVisibility(View.GONE);
                            mIvChooseMarketShops.setBackground(getResources().getDrawable(R.drawable.icon_arrow_down));
                        }
                    })
                    .apply();
        }

        //Recycler适配
        RecyclerView mRv = mCirclePop.findViewById(R.id.dialog_rv_top_market_shops);

        cb.clear();

        for (int j = 0; j < 4; j++) {
            HomeBean.CardBean bean = new HomeBean.CardBean();
            bean.setUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1543834276504&di=558d8d0aae63b7992d2aa9f8ef851307&imgtype=0&src=http%3A%2F%2Ffile25.mafengwo.net%2FM00%2F66%2FD8%2FwKgB4lMcWvyAE9xYAAC8zPK6yMw35.jpeg");
            bean.setName("杭州"+j);
            cb.add(bean);
        }

        mDialogMarketShopsAdapter = new DialogMarketShopsAdapter(R.layout.item_dialog_market_shops, cb);
        //设置当前数据为空时，显示空的图片界面
        mDialogMarketShopsAdapter.setEmptyView(getEmptyView());
        mRv.setAdapter(mDialogMarketShopsAdapter);
        mRv.setLayoutManager(new LinearLayoutManager(MarketShopsActivity.this, LinearLayoutManager.HORIZONTAL, false));

        mDialogMarketShopsAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                showToast("你点击了："+cb.get(position).getName());
                mCirclePop.dismiss();
            }
        });

        //设置透明罩子可见
        mTran.setVisibility(View.VISIBLE);
        mIvChooseMarketShops.setBackground(getResources().getDrawable(R.drawable.icon_arrow_up));

        //设置popupWindow的位置
        mCirclePop.showAtAnchorView(mRlChooseMarketShops, YGravity.BELOW, XGravity.LEFT, 0, 0);
    }

    //顶部popupWindow适配
    public class DialogMarketShopsAdapter extends BaseQuickAdapter<HomeBean.CardBean, BaseViewHolder> {
        public DialogMarketShopsAdapter(int layoutResId, List data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, HomeBean.CardBean item) {
            helper.setText(R.id.tv_market_shops_list_item,item.getName());

            GlideUtil.setImage(MarketShopsActivity.this,item.getUrl(), (ImageView) helper.getView(R.id.iv_market_shops_list_item));
        }
    }

    //详情显示适配
    public class MarketShopsAdapter extends BaseQuickAdapter<DynamicBean, BaseViewHolder> {
        public MarketShopsAdapter(int layoutResId, List data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, DynamicBean item) {
            GlideUtil.setCircleImage(MarketShopsActivity.this, item.getImage(), (ImageView) helper.getView(R.id.iv_image));
            helper.setText(R.id.tv_name, item.getName());
            helper.setText(R.id.tv_content, item.getContent());
            CustomGridView gridView = helper.getView(R.id.image_list);
            gridView.setAdapter(new DynamicImageAdapter(MarketShopsActivity.this, item.getUrlList()));
        }
    }

}
