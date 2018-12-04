package com.jingwei.vega.activity;

import android.view.View;
import android.widget.EditText;

import com.came.viewbguilib.ButtonBgUi;
import com.jingwei.vega.R;
import com.jingwei.vega.base.BaseActivity;
import com.jingwei.vega.utils.TextUtil;
import com.jingwei.vega.view.VerifyCodeButton;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 用户注册
 */
public class RegistActivity extends BaseActivity {

    @BindView(R.id.et_phone)
    EditText mEtPhone;
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
        return R.layout.activity_regist;
    }

    @Override
    public void initTitleBar() {
        getTitleBar().setLeftImage(R.drawable.icon_back).setTitleText("注册");
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.btn_verify_code, R.id.bt_regist})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_verify_code:
                validationPhone();
                break;
            case R.id.bt_regist:
                break;
        }
    }

    private void validationPhone() {
        String phone = mEtPhone.getText().toString().trim();
        if (TextUtil.isEmpty(phone))
            showToast("手机号不能为空");
        else
            sendPhoneCode();
    }

    private void sendPhoneCode() {
        String phone = mEtPhone.getText().toString().trim();
        String code = mBtnVerifyCode.getText().toString().trim();
        String password = mEtPassword.getText().toString().trim();
        String confirmPassword = mEtConfirmPassword.getText().toString().trim();
        if (TextUtil.isEmpty(phone))
            showToast("手机号不能为空");
        else if (TextUtil.isEmpty(code))
            showToast("验证码不能为空");
        else if (TextUtil.isEmpty(password))
            showToast("密码不能为空");
        else if (TextUtil.isEmpty(confirmPassword))
            showToast("确认密码不能为空");
        else if (!password.equals(confirmPassword))
            showToast("两次密码不一致");
        else 
            regist();

    }

    private void regist() {

    }
}
