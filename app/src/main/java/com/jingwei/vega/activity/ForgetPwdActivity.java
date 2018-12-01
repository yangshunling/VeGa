package com.jingwei.vega.activity;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.came.viewbguilib.ButtonBgUi;
import com.jingwei.vega.R;
import com.jingwei.vega.base.BaseActivity;
import com.jingwei.vega.view.VerifyCodeButton;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 忘记密码
 */
public class ForgetPwdActivity extends BaseActivity {

    @BindView(R.id.tv_phone)
    TextView mEtPhone;
    @BindView(R.id.btn_verify_code)
    VerifyCodeButton mBtnVerifyCode;
    @BindView(R.id.et_validation)
    EditText mEtValidation;
    @BindView(R.id.et_password)
    EditText mEtPassword;
    @BindView(R.id.et_confirm_password)
    EditText mEtConfirmPassword;
    @BindView(R.id.bt_regist)
    ButtonBgUi mBtRegist;


    @Override
    public int getContentView() {
        return R.layout.activity_forget_pwd;
    }

    @Override
    public void initTitleBar() {
        getTitleBar().setLeftImage(R.drawable.icon_back).setTitleText("忘记密码");
    }

    @Override
    public void initData() {
        mEtPhone.setText("10086");
    }

    @OnClick({R.id.btn_verify_code, R.id.bt_regist})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_verify_code:
                break;
            case R.id.bt_regist:
                break;
        }
    }
}
