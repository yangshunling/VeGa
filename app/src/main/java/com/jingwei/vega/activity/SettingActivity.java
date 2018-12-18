package com.jingwei.vega.activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.DatePicker;

import com.afollestad.materialdialogs.MaterialDialog;
import com.came.viewbguilib.ButtonBgUi;
import com.jingwei.vega.R;
import com.jingwei.vega.base.BaseActivity;
import com.jingwei.vega.moudle.bean.UserInfoBean;
import com.jingwei.vega.rxhttp.retrofit.ParamBuilder;
import com.jingwei.vega.rxhttp.retrofit.ServiceAPI;
import com.jingwei.vega.rxhttp.rxjava.RxResultFunc;
import com.jingwei.vega.rxhttp.rxjava.RxSubscriber;
import com.jingwei.vega.utils.CacheUtil;
import com.jingwei.vega.utils.TextUtil;
import com.jingwei.vega.view.CustomLinearLayout;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.OnClick;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

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

    private String[] sexString = {"男","女"};
    private int currentSex;

    private int mYear;
    private int mMonth;
    private int mDay;

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

        currentSex = (1 == userInfoBean.getSex()?0:1);

        if(TextUtils.isEmpty(userInfoBean.getBirth())){
            Calendar ca = Calendar.getInstance();
            mYear = ca.get(Calendar.YEAR);
            mMonth = ca.get(Calendar.MONTH);
            mDay = ca.get(Calendar.DAY_OF_MONTH);
        }else{
            String dateString[] = userInfoBean.getBirth().split("-");
            mYear = Integer.parseInt(dateString[0]);
            mMonth = Integer.parseInt(dateString[1]);
            mDay = Integer.parseInt(dateString[2]);
        }

        try {
            String cacheSize = CacheUtil.getTotalCacheSize(SettingActivity.this);
            mCllClear.setRightText(cacheSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnClick({R.id.cll_name,R.id.cll_sex,R.id.cll_birth,R.id.cll_update_password, R.id.cll_clear, R.id.btn_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.cll_name:
                new MaterialDialog.Builder(this)
                        .title("修改姓名：")
                        .inputType(InputType.TYPE_CLASS_TEXT)
                        .input("输入新名称", null, new MaterialDialog.InputCallback() {
                            @Override
                            public void onInput(MaterialDialog dialog, CharSequence input) {
                                if(!TextUtils.isEmpty(input.toString())){
                                    mCllName.setRightText(input.toString());
                                }
                            }
                        })
                        .positiveText("确定")
                        .negativeText("取消")
                        .show();
                break;

            case R.id.cll_sex:
                new MaterialDialog.Builder(this)
                        .title("修改性别：")
                        .items(sexString)
                        .itemsCallbackSingleChoice(currentSex, new MaterialDialog.ListCallbackSingleChoice() {
                            @Override
                            public boolean onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                                mCllSex.setRightText(0 == which?"男":"女");
                                currentSex = which;
                                return true;
                            }
                        })
                        .show();
                break;

            case R.id.cll_birth:
                showDatePickDialog();
                break;

            case R.id.cll_update_password:
                Intent intent = new Intent(SettingActivity.this,UpdatePwdActivity.class);
                intent.putExtra("phone",userInfoBean.getMobile());
                startActivity(intent);
                break;
            case R.id.cll_clear:
                try {
                    CacheUtil.clearAllCache(SettingActivity.this);
                    String size = CacheUtil.getTotalCacheSize(SettingActivity.this);
                    mCllClear.setRightText(size);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.btn_confirm:
                submit();
                break;
        }
    }

    //生日选择，用android原生日期选择器
    private void showDatePickDialog() {
        new DatePickerDialog(SettingActivity.this, onDateSetListener, mYear, mMonth, mDay).show();
    }

    /**
     * 日期选择器对话框监听
     */
    private DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            mYear = year;
            mMonth = monthOfYear;
            mDay = dayOfMonth;
            String days;
            if (mMonth + 1 < 10) {
                if (mDay < 10) {
                    days = new StringBuffer().append(mYear).append("-").append("0").
                            append(mMonth + 1).append("-").append("0").append(mDay).toString();
                } else {
                    days = new StringBuffer().append(mYear).append("-").append("0").
                            append(mMonth + 1).append("-").append(mDay).toString();
                }

            } else {
                if (mDay < 10) {
                    days = new StringBuffer().append(mYear).append("-").
                            append(mMonth + 1).append("-").append("0").append(mDay).toString();
                } else {
                    days = new StringBuffer().append(mYear).append("-").
                            append(mMonth + 1).append("-").append(mDay).toString();
                }

            }
            mCllBirth.setRightText(days);
        }
    };

    //提交修改
    private void submit() {
        ServiceAPI.Retrofit().userUpdate(ParamBuilder.newBody()
                .addBody("nickName", mCllName.getRightText().toString().trim())
                .addBody("sex", (mCllSex.getRightText().toString().trim().equals("男")?1:2)+"")
                .addBody("birth", mCllBirth.getRightText().toString().trim())
                .bulidBody())
                .map(new RxResultFunc<Object>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<Object>(SettingActivity.this, "正在修改...") {
                    @Override
                    public void onNext(Object message) {
                        showToast("修改成功");
                        finish();
                    }
                });
    }
}
