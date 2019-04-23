package com.jingwei.vega.activity;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jingwei.vega.Constants;
import com.jingwei.vega.R;
import com.jingwei.vega.base.BaseActivity;
import com.jingwei.vega.moudle.bean.UploadSearchPicBean;
import com.jingwei.vega.moudle.bean.UploadSearchResultBean;
import com.jingwei.vega.refresh.DefaultFooter;
import com.jingwei.vega.refresh.DefaultHeader;
import com.jingwei.vega.refresh.SpringView;
import com.jingwei.vega.rxhttp.retrofit.ParamBuilder;
import com.jingwei.vega.rxhttp.retrofit.ServiceAPI;
import com.jingwei.vega.rxhttp.rxjava.RxResultFunc;
import com.jingwei.vega.rxhttp.rxjava.RxSubscriber;
import com.jingwei.vega.utils.GlideUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


//以图搜图结果展示界面
public class SearchPicActivity extends BaseActivity {
    //图片本地地址
    private String picPath = "";

    //服务器返回图片地址
    private String picServerPath = "";

    private Integer pager = 1;

    private MyAdapter mMyAdapter;
    private List<UploadSearchResultBean.ListBean> mBeanList = new ArrayList<>();

    @BindView(R.id.rv_search_list)
    RecyclerView mRvList;
    @BindView(R.id.search_spring)
    SpringView mSpring;
    @BindView(R.id.iv_search_arrow_top)
    ImageView mIvArrowTop;

    @Override
    public int getContentView() {
        return R.layout.activity_search_pic;
    }

    @Override
    public void initTitleBar() {
        getTitleBar().setLeftImage(R.drawable.icon_back).setTitleText("搜索结果");
    }

    @Override
    public void initView() {
        picPath = getIntent().getStringExtra("picPath");

        mSpring.setHeader(new DefaultHeader(SearchPicActivity.this));
        mSpring.setFooter(new DefaultFooter(SearchPicActivity.this));
        mMyAdapter = new MyAdapter(R.layout.item_goods_lib_recycle, mBeanList);
        mRvList.setAdapter(mMyAdapter);
        mRvList.setLayoutManager(new GridLayoutManager(SearchPicActivity.this, 2));

        setListener();

    }

    private void setListener() {
        mSpring.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                pager = 1;
                uploadSearchResult();
            }

            @Override
            public void onLoadmore() {
                pager ++;
                uploadSearchResult();
            }
        });

        mMyAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(SearchPicActivity.this, ShopProductDetailActivity.class);
                intent.putExtra("id", mBeanList.get(position).getId() + "");
                startActivity(intent);
            }
        });

        mMyAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(SearchPicActivity.this, ShopProductDetailActivity.class);
                intent.putExtra("id", mBeanList.get(position).getId() + "");
                startActivity(intent);
            }
        });

        mIvArrowTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRvList.scrollToPosition(0);
            }
        });
    }

    @Override
    public void initData() {
        uploadSearchPic();
    }

    /**
     * 用户本地地址，通过该地址获取服务端返回的图片地址
     */
    private void uploadSearchPic() {
        ServiceAPI.Retrofit().uploadSearchPic(ParamBuilder.newBody()
                .addBody("file", new File(picPath))
                .bulidBody())
                .map(new RxResultFunc<UploadSearchPicBean>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<UploadSearchPicBean>(SearchPicActivity.this, true) {
                    @Override
                    public void onNext(UploadSearchPicBean bean) {
                        picServerPath = bean.getFilePath();
                        //获取以图搜图结果
                        uploadSearchResult();
                    }
                });
    }

    /**
     * 根据服务器返回的图片地址获取搜索结果
     */
    private void uploadSearchResult() {
        ServiceAPI.Retrofit().uploadSearchResult(ParamBuilder.newBody()
                .addBody("filePath", new File(picServerPath))
                .addBody("pageNumber",pager+"")
                .bulidBody())
                .map(new RxResultFunc<UploadSearchResultBean>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<UploadSearchResultBean>(SearchPicActivity.this, true) {
                    @Override
                    public void onNext(UploadSearchResultBean bean) {
                        if (bean.getList().size() != 0) {
                            if (pager == 1) {
                                mBeanList = bean.getList();
                            } else {
                                mBeanList.addAll(bean.getList());
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

    public class MyAdapter extends BaseQuickAdapter<UploadSearchResultBean.ListBean, BaseViewHolder> {
        public MyAdapter(int layoutResId, List data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, UploadSearchResultBean.ListBean item) {
            GlideUtil.setRoundImage(SearchPicActivity.this, Constants.IMAGEHOST + item.getIconImage(), 15, (ImageView) helper.getView(R.id.iv_goods_lib));
            helper.setText(R.id.tv_goods_lib_introduce, item.getName());
            helper.setText(R.id.tv_goods_lib_price, "¥" + item.getPrice());
        }
    }
}
