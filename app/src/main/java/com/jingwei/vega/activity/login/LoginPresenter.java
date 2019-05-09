package com.jingwei.vega.activity.login;

import android.content.Context;
import android.content.Intent;

import com.jingwei.vega.activity.MainActivity;
import com.jingwei.vega.moudle.bean.BannerListBean;
import com.jingwei.vega.mvp.BasePresenterImpl;
import com.jingwei.vega.rxhttp.retrofit.ParamBuilder;
import com.jingwei.vega.rxhttp.retrofit.ServiceAPI;
import com.jingwei.vega.rxhttp.rxjava.RxHelper;
import com.jingwei.vega.rxhttp.rxjava.RxSubscriber;
import com.jingwei.vega.utils.PreferencesUtil;

import java.util.Map;

import okhttp3.RequestBody;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class LoginPresenter extends BasePresenterImpl<LoginContract.View> implements LoginContract.Presenter{

    @Override
    public void login(Context context,String phone, String password) {
        Map<String, RequestBody> stringRequestBodyMap = ParamBuilder.newBody()
                .addBody("mobile", phone)
                .addBody("password", password)
                .bulidBody();
        RxHelper.observer(ServiceAPI.Retrofit().userLogin(stringRequestBodyMap))
                .subscribe(new RxSubscriber<Object>(context, "正在登录...") {
                    @Override
                    public void onNext(Object token) {
                        mView.onLogin();
                    }
                });
    }

    @Override
    public void getBanner(Context context) {
        RxHelper.observer(ServiceAPI.Retrofit().getBanner())
                .subscribe(new RxSubscriber<BannerListBean>(context, "正在初始化数据...") {
                    @Override
                    public void onNext(BannerListBean bean) {
                        mView.onBanner(bean);
                    }
                });
    }
}
