package com.jingwei.vega.activity.login;

import android.content.Context;

import com.jingwei.vega.moudle.bean.BannerListBean;
import com.jingwei.vega.mvp.BasePresenter;
import com.jingwei.vega.mvp.BaseView;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class LoginContract {
    interface View extends BaseView {
        void onLogin();

        void onBanner(BannerListBean bean);
    }

    interface Presenter extends BasePresenter<View> {
        void login(Context context, String phone, String password);

        void getBanner(Context context);
    }
}
