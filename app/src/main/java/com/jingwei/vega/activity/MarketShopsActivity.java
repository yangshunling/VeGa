package com.jingwei.vega.activity;

import android.content.Intent;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jingwei.vega.Constants;
import com.jingwei.vega.R;
import com.jingwei.vega.adapter.MarkShopPicAdapter;
import com.jingwei.vega.base.BaseActivity;
import com.jingwei.vega.moudle.bean.MarketListBean;
import com.jingwei.vega.moudle.bean.MarketShopListBean;
import com.jingwei.vega.refresh.DefaultFooter;
import com.jingwei.vega.refresh.DefaultHeader;
import com.jingwei.vega.refresh.SpringView;
import com.jingwei.vega.rxhttp.retrofit.ParamBuilder;
import com.jingwei.vega.rxhttp.retrofit.ServiceAPI;
import com.jingwei.vega.rxhttp.rxjava.RxResultFunc;
import com.jingwei.vega.rxhttp.rxjava.RxSubscriber;
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
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by cxc on 2018/12/3.
 * 市场店铺界面
 */
public class MarketShopsActivity extends BaseActivity {

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

    @BindView(R.id.btn_turn_to_top)
    Button mBtnTurnToTop;//滑到顶部按钮

    @BindView(R.id.et_content)
    EditText mEtContent;

    private EasyPopup mCirclePop;

    private MarketShopsAdapter mMarketShopsAdapter;
    private List<MarketShopListBean.PageListBean.ListBean> mBeanList = new ArrayList<>();

    //选择市场商铺dialog的适配
    private DialogMarketShopsAdapter mDialogMarketShopsAdapter;

    //用户选中的市场id
    private int marketId;
    //市场列表
    private List<MarketListBean.ListBeanX.ListBean> mMarketList;

    private int pager = 1;

    private String msg = "";

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
        marketId = getIntent().getExtras().getInt("marketId", 0);
        mMarketList = (List<MarketListBean.ListBeanX.ListBean>) getIntent().getExtras().getSerializable("marketList");

        mSpring.setHeader(new DefaultHeader(MarketShopsActivity.this));
        mSpring.setFooter(new DefaultFooter(MarketShopsActivity.this));
        mMarketShopsAdapter = new MarketShopsAdapter(R.layout.item_market_shops_recycle, mBeanList);
        mMarketShopsAdapter.setEmptyView(getEmptyView());
        mRvMarketShops.setAdapter(mMarketShopsAdapter);
        mRvMarketShops.setLayoutManager(new LinearLayoutManager(MarketShopsActivity.this));
    }

    @Override
    public void initData() {

        getMarkerShopList();

        setListener();
    }

    private void setListener() {

        mEtContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MarketShopsActivity.this, SearchActivity.class);
                intent.putExtra("name", mEtContent.getText().toString().trim());
                startActivityForResult(intent, Constants.MARKSHOP);
            }
        });

        mMarketShopsAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(MarketShopsActivity.this, ShopActivity.class);
                intent.putExtra("shopId", mBeanList.get(position).getId());
                startActivity(intent);
            }
        });

        mMarketShopsAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.bt_is_love://当前是已关注状态，点击取消关注
                        updateLoveShopState(mBeanList.get(position).getId(), position);
                        break;
                    case R.id.bt_love://当前是未关注状态，点击关注
                        updateLoveShopState(mBeanList.get(position).getId(), position);
                        break;
                }
            }
        });

        //上拉下拉
        mSpring.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                pager = 1;
                getMarkerShopList();
            }

            @Override
            public void onLoadmore() {
                pager++;
                getMarkerShopList();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constants.MARKSHOP) {
            if (data != null) {
                msg = data.getStringExtra("content");
                mEtContent.setText(msg);
                pager = 1;
                getMarkerShopList();
            }
        }
    }

    //获取市场商铺列表
    private void getMarkerShopList() {
        ServiceAPI.Retrofit().postMarketShopList(ParamBuilder.newParams()
                .addParam("name", msg)
                .addParam("marketId", marketId + "")
                .addParam("pageNumber", pager + "")
                .bulidParam())
                .map(new RxResultFunc<MarketShopListBean>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<MarketShopListBean>(MarketShopsActivity.this, true) {
                    @Override
                    public void onNext(MarketShopListBean bean) {
                        if (bean.getPageList().getList().size() != 0) {
                            if (pager == 1) {
                                mBeanList = bean.getPageList().getList();
                                mMarketShopsAdapter.replaceData(mBeanList);
                                //将recyclerView滑到顶部
                                mRvMarketShops.scrollToPosition(0);
                                LinearLayoutManager mLayoutManager = (LinearLayoutManager) mRvMarketShops.getLayoutManager();
                                mLayoutManager.scrollToPositionWithOffset(0, 0);
                            } else {
                                mBeanList.addAll(bean.getPageList().getList());
                                mMarketShopsAdapter.replaceData(mBeanList);
                            }
                        } else {
                            if (pager > 1) {
                                showToast(getResources().getString(R.string.no_more_date));
                            } else {
                                mBeanList.clear();
                                mMarketShopsAdapter.replaceData(mBeanList);
                            }
                        }
                        mSpring.onFinishFreshAndLoad();
                    }
                });
    }

    @OnClick({R.id.rl_choose_market_shops,R.id.btn_turn_to_top})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_choose_market_shops:
                showMarketShops();
                break;

            case R.id.btn_turn_to_top:
                mRvMarketShops.scrollToPosition(0);
                LinearLayoutManager mLayoutManager = (LinearLayoutManager) mRvMarketShops.getLayoutManager();
                mLayoutManager.scrollToPositionWithOffset(0, 0);
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

        mDialogMarketShopsAdapter = new DialogMarketShopsAdapter(R.layout.item_dialog_market_shops, mMarketList);
        //设置当前数据为空时，显示空的图片界面
        mDialogMarketShopsAdapter.setEmptyView(getEmptyView());
        mRv.setAdapter(mDialogMarketShopsAdapter);
        mRv.setLayoutManager(new LinearLayoutManager(MarketShopsActivity.this, LinearLayoutManager.HORIZONTAL, false));

        mDialogMarketShopsAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                marketId = mMarketList.get(position).getId();
                pager = 1;
                getMarkerShopList();
                mCirclePop.dismiss();
            }
        });

        //设置透明罩子可见
        mTran.setVisibility(View.VISIBLE);
        mIvChooseMarketShops.setBackground(getResources().getDrawable(R.drawable.icon_arrow_up));

        //设置popupWindow的位置
        mCirclePop.showAtAnchorView(mRlChooseMarketShops, YGravity.BELOW, XGravity.LEFT, 0, 0);
    }

    /**
     * 更改商铺关注状态，并进行局部刷新
     *
     * @param id       商铺id
     * @param position 商铺列表id
     */
    private void updateLoveShopState(int id, final int position) {
        ServiceAPI.Retrofit().updateLoveShopState(id + "")
                .map(new RxResultFunc<Object>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<Object>(MarketShopsActivity.this, mBeanList.get(position).isIsLove() ? "正在取消关注..." : "正在关注...") {
                    @Override
                    public void onNext(Object message) {
                        MarketShopListBean.PageListBean.ListBean bean = mBeanList.get(position);
                        bean.setIsLove(!bean.isIsLove());
                        //刷新局部RecyclerView
                        mMarketShopsAdapter.getData().set(position, bean);
                        mBeanList.set(position, bean);
                        mMarketShopsAdapter.notifyItemChanged(position, "com");
                    }
                });
    }

    //顶部popupWindow适配
    public class DialogMarketShopsAdapter extends BaseQuickAdapter<MarketListBean.ListBeanX.ListBean, BaseViewHolder> {
        public DialogMarketShopsAdapter(int layoutResId, List data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, MarketListBean.ListBeanX.ListBean item) {
            helper.setText(R.id.tv_market_shops_list_item, item.getName());

            GlideUtil.setImage(MarketShopsActivity.this, Constants.IMAGEHOST + item.getPic(), (ImageView) helper.getView(R.id.iv_market_shops_list_item));
        }
    }

    //详情显示适配
    public class MarketShopsAdapter extends BaseQuickAdapter<MarketShopListBean.PageListBean.ListBean, BaseViewHolder> {
        public MarketShopsAdapter(int layoutResId, List data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, MarketShopListBean.PageListBean.ListBean item) {
            GlideUtil.setCircleImage(MarketShopsActivity.this, Constants.IMAGEHOST + item.getHeadImg(), (ImageView) helper.getView(R.id.iv_image));
            helper.setText(R.id.tv_name, item.getName());
            helper.setText(R.id.tv_content, item.getRemark());

            List<String> imageList = new ArrayList<>();
            if (item.getImages() != null && item.getImages().size() > 0) {
                for (int i = 0; i < item.getImages().size(); i++) {
                    imageList.add(Constants.IMAGEHOST + item.getImages().get(i).getPath());
                }
            }
            CustomGridView gridView = helper.getView(R.id.image_list);
            gridView.setAdapter(new MarkShopPicAdapter(MarketShopsActivity.this, imageList));

            //是否已关注
            if (item.isIsLove()) {
                helper.setGone(R.id.bt_is_love, true);
                helper.setGone(R.id.bt_love, false);
            } else {
                helper.setGone(R.id.bt_is_love, false);
                helper.setGone(R.id.bt_love, true);
            }

            //取消关注点击事件
            helper.addOnClickListener(R.id.bt_is_love);

            //关注点击事件
            helper.addOnClickListener(R.id.bt_love);
        }
    }

}
