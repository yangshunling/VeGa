package com.jingwei.vega.activity;

import android.content.Intent;
import android.view.View;

import com.came.viewbguilib.ButtonBgUi;
import com.jingwei.vega.R;
import com.jingwei.vega.base.BaseActivity;
import com.jingwei.vega.moudle.bean.UserInfoBean;
import com.jingwei.vega.view.CustomLinearLayout;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 设置
 */
public class SettingActivity extends BaseActivity {

    private UserInfoBean userInfoBean;

    @BindView(R.id.cll_name)
    CustomLinearLayout mCllName;

    @BindView(R.id.cll_sex)
    CustomLinearLayout mCllSex;

    @BindView(R.id.cll_birth)
    CustomLinearLayout mCllBirth;

    @BindView(R.id.cll_update_password)
    CustomLinearLayout mCllUpdatePassword;

    @BindView(R.id.cll_clear)
    CustomLinearLayout mCllClear;

    @BindView(R.id.btn_confirm)
    ButtonBgUi mBtnConfirm;

    @Override
    public int getContentView() {
        return R.layout.activity_setting;
    }

    @Override
    public void initTitleBar() {
        getTitleBar().setLeftImage(R.drawable.icon_back).setTitleText("设置");
    }

    @Override
    public void initView() {
        userInfoBean = (UserInfoBean) getIntent().getSerializableExtra("userInfo");
    }

    @Override
    public void initData() {
        mCllName.setRightText(userInfoBean.getNickName());
        mCllSex.setRightText(1 == userInfoBean.getSex()?"男":"女");
        mCllBirth.setRightText(userInfoBean.getBirth());
    }

    @OnClick({R.id.cll_update_password, R.id.cll_clear, R.id.btn_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.cll_update_password:
                Intent intent = new Intent(SettingActivity.this,UpdatePwdActivity.class);
                intent.putExtra("phone",userInfoBean.getMobile());
                startActivity(intent);
                break;
            case R.id.cll_clear:
                break;
            case R.id.btn_confirm:
                break;
        }
    }
}
