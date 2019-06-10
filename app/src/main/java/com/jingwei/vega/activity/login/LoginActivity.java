package com.jingwei.vega.activity.login;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.came.viewbguilib.ButtonBgUi;
import com.jingwei.vega.R;
import com.jingwei.vega.activity.ForgetPwdActivity;
import com.jingwei.vega.activity.MainActivity;
import com.jingwei.vega.activity.RegistActivity;
import com.jingwei.vega.callback.PermissionsCallback;
import com.jingwei.vega.moudle.bean.BannerListBean;
import com.jingwei.vega.mvp.MVPBaseActivity;
import com.jingwei.vega.utils.PreferencesUtil;
import com.jingwei.vega.utils.TextUtil;
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 用户登录
 */
public class LoginActivity extends MVPBaseActivity<LoginContract.View, LoginPresenter> implements LoginContract.View, PermissionsCallback {

    @BindView(R.id.iv_logo)
    ImageView mIvLogo;
    @BindView(R.id.et_phone)
    EditText mEtPhone;
    @BindView(R.id.et_password)
    EditText mEtPassword;
    @BindView(R.id.bt_login)
    ButtonBgUi mBtLogin;
    @BindView(R.id.tv_regist)
    TextView mTvRegist;
    @BindView(R.id.tv_forget)
    TextView mTvForget;
    @BindView(R.id.login_contain)
    LinearLayout mLoginContain;
    @BindView(R.id.tv_version)
    TextView mTvVersion;
    @BindView(R.id.iv_login_bg)
    ImageView mIvLoginBg;
    @BindView(R.id.ll_login_bg)
    LinearLayout mLlLoginBg;

    private List<BannerListBean.ListBean> beanList = new ArrayList<>();

    @Override
    public int getContentView() {
        return R.layout.activity_login;
    }

    @Override
    public void initTitleBar() {
        hintTitleBar();
        if (PreferencesUtil.getLoginState(LoginActivity.this)) {
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        }
    }

    @Override
    public void initView() {
        requestPermissions(this);
    }

    @Override
    public void initData() {
    }

    @OnClick({R.id.bt_login, R.id.tv_regist, R.id.tv_forget})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_login:
                validationData();
                break;
            case R.id.tv_regist:
                startActivity(new Intent(this, RegistActivity.class));
                break;
            case R.id.tv_forget:
                startActivity(new Intent(this, ForgetPwdActivity.class));
                break;
        }
    }

    private void validationData() {
        String phone = mEtPhone.getText().toString().trim();
        String password = mEtPassword.getText().toString().trim();
        if (TextUtil.isEmpty(phone))
            showToast("手机号不能为空");
        else if (TextUtil.isEmpty(password))
            showToast("密码不能为空");
        else
            mPresenter.login(LoginActivity.this, phone, password);
    }

    @Override
    public void onAccept() {

    }

    @Override
    public void onDenied() {

    }

    @Override
    public void onLogin() {
        PreferencesUtil.saveLoginState(LoginActivity.this, true);
        mPresenter.getBanner(LoginActivity.this);
    }

    @Override
    public void onBanner(BannerListBean bean) {
        beanList = bean.getList();
        if (beanList.size() > 0) {
            PreferencesUtil.saveBannerList(LoginActivity.this, beanList);
        }
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
        finish();
    }
}
