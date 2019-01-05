package com.jingwei.vega.activity;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.widget.TextView;

import com.jingwei.vega.R;
import com.jingwei.vega.base.BaseActivity;
import com.jingwei.vega.moudle.bean.VegaInfoBean;
import com.jingwei.vega.rxhttp.retrofit.ServiceAPI;
import com.jingwei.vega.rxhttp.rxjava.RxResultFunc;
import com.jingwei.vega.rxhttp.rxjava.RxSubscriber;

import butterknife.BindView;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 关于我们界面
 */
public class AboutUsActivity extends BaseActivity {

    @BindView(R.id.tv_introduce_info)
    TextView mTvIntroduceInfo;

    @BindView(R.id.tv_phone)
    TextView mTvPhone;

    @BindView(R.id.tv_version)
    TextView mTvVersion;

    @BindView(R.id.tv_version_bottom)
    TextView mTvVersionBottom;

    private VegaInfoBean mVegaInfoBean;

    @Override
    public int getContentView() {
        return R.layout.activity_about_us;
    }

    @Override
    public void initTitleBar() {
        getTitleBar().setLeftImage(R.drawable.icon_back).setTitleText("关于VeGar");
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        ServiceAPI.Retrofit().getVegaInfo()
                .map(new RxResultFunc<VegaInfoBean>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<VegaInfoBean>(AboutUsActivity.this) {
                    @Override
                    public void onNext(VegaInfoBean bean) {
                        mVegaInfoBean = bean;

                        setInfo();
                    }
                });
    }

    private void setInfo() {
        mTvPhone.setText(mVegaInfoBean.getTel());
        mTvIntroduceInfo.setText(mVegaInfoBean.getRemark());

        mTvVersion.setText(getVersion());
        mTvVersionBottom.setText(getResources().getString(R.string.app_name)+" "+getVersion());
    }

    public String getVersion() {
        try {
            PackageManager manager = this.getPackageManager();
            PackageInfo info = manager.getPackageInfo(this.getPackageName(), 0);
            String version = info.versionName;
            return  version;
        } catch (Exception e){
            e.printStackTrace();
            return "1.0";
        }
    }
}
