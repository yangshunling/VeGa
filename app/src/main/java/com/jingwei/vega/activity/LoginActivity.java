package com.jingwei.vega.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.came.viewbguilib.ButtonBgUi;
import com.jingwei.vega.R;
import com.jingwei.vega.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 用户登录
 */
public class LoginActivity extends BaseActivity {

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

    @Override
    public int getContentView() {
        return R.layout.activity_login;
    }

    @Override
    public void initTitleBar() {
        hintTitleBar();
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.bt_login, R.id.tv_regist, R.id.tv_forget})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_login:
                startActivity(new Intent(this, MainActivity.class));
                break;
            case R.id.tv_regist:
                startActivity(new Intent(this, RegistActivity.class));
                break;
            case R.id.tv_forget:
                startActivity(new Intent(this, ForgetPwdActivity.class));
                break;
        }
    }
}
