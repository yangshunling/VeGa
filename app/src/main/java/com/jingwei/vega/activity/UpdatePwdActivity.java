package com.jingwei.vega.activity;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.came.viewbguilib.ButtonBgUi;
import com.jingwei.vega.R;
import com.jingwei.vega.base.BaseActivity;
import com.jingwei.vega.moudle.TokenLose;
import com.jingwei.vega.rxhttp.retrofit.ParamBuilder;
import com.jingwei.vega.rxhttp.retrofit.ServiceAPI;
import com.jingwei.vega.rxhttp.rxjava.RxResultFunc;
import com.jingwei.vega.rxhttp.rxjava.RxSubscriber;
import com.jingwei.vega.utils.TextUtil;
import com.jingwei.vega.view.VerifyCodeButton;

import butterknife.BindView;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 修改密码
 */
public class UpdatePwdActivity extends BaseActivity {

    @BindView(R.id.tv_phone)
    TextView mTvPhone;
    @BindView(R.id.btn_verify_code)
    VerifyCodeButton mBtnVerifyCode;
    @BindView(R.id.et_validation)
    EditText mEtValidation;
    @BindView(R.id.et_password)
    EditText mEtPassword;
    @BindView(R.id.et_confirm_password)
    EditText mEtConfirmPassword;
    @BindView(R.id.bt_reset)
    ButtonBgUi mBtReset;

    private String phone;

    @Override
    public int getContentView() {
        return R.layout.activity_update_pwd;
    }

    @Override
    public void initTitleBar() {
        getTitleBar().setLeftImage(R.drawable.icon_back).setTitleText("修改密码");
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        phone = getIntent().getStringExtra("phone");
        mTvPhone.setText(phone);
    }

    @OnClick({R.id.btn_verify_code, R.id.bt_reset})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_verify_code:
                validationPhone();
                break;
            case R.id.bt_reset:
                updatePwd();
                break;
        }
    }

    private void validationPhone() {
        String phone = mTvPhone.getText().toString().trim();
        if (TextUtil.isEmpty(phone))
            showToast("手机号不能为空");
        else
            sendPhoneCode();
    }

    private void sendPhoneCode() {
        ServiceAPI.Retrofit().sendCode(ParamBuilder.newBody()
                .addBody("mobile", phone)
                .addBody("type", "UPDATE_PASSWORD")
                .bulidBody())
                .map(new RxResultFunc<Object>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<Object>(UpdatePwdActivity.this, "正在发送...") {
                    @Override
                    public void onNext(Object message) {
                        mBtnVerifyCode.start();
                    }
                });
    }

    private void updatePwd() {
        String code = mEtValidation.getText().toString().trim();
        String password = mEtPassword.getText().toString().trim();
        String confirmPassword = mEtConfirmPassword.getText().toString().trim();
        if (TextUtil.isEmpty(code))
            showToast("验证码不能为空");
        else if (TextUtil.isEmpty(password))
            showToast("密码不能为空");
        else if (TextUtil.isEmpty(confirmPassword))
            showToast("确认密码不能为空");
        else if (!password.equals(confirmPassword))
            showToast("两次密码输入不一致");
        else
            ServiceAPI.Retrofit().userUpdatePwd(ParamBuilder.newBody()
                    .addBody("code", code)
                    .addBody("password", password)
                    .addBody("r_password", confirmPassword)
                    .bulidBody())
                    .map(new RxResultFunc<Object>())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new RxSubscriber<Object>(UpdatePwdActivity.this, "正在修改...") {
                        @Override
                        public void onNext(Object message) {
                            showToast("修改密码成功");
                            EventBus.getDefault().post(new TokenLose());
                            finish();
                        }
                    });
    }
}
