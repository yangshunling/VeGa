package com.jingwei.vega.base;

import android.Manifest;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.gyf.barlibrary.ImmersionBar;
import com.jingwei.vega.R;
import com.jingwei.vega.callback.PermissionsCallback;
import com.jingwei.vega.view.TitleBar;

import java.util.List;

import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * BaseActivity基类
 */
public abstract class BaseActivity extends AppCompatActivity implements EasyPermissions.PermissionCallbacks {

    private LinearLayout mTitlebar;
    private FrameLayout mViewContent;
    private TitleBar mTitleBar;

    private String[] perms = {Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE};
    private PermissionsCallback mCallback;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        //初始化沉浸式
        initStatusBar();
        //解析子布局
        resolveView();
        //绑定插件
        bindPlugins();
        //初始化TitleBar
        initTitleBar();
        //初始化数据
        initData();
    }

    /**
     * 初始化沉浸式
     */
    public void initStatusBar() {
        ImmersionBar.with(this)
                .barColor(R.color.app_color)
                .statusBarDarkFont(true)
                .fitsSystemWindows(true)
                .init();
    }

    /**
     * 沉浸式图片
     */
    public void setTransparent() {
        ImmersionBar.with(this).fitsSystemWindows(false).transparentStatusBar().init();
    }

    /**
     * 初始化Fragment
     */
    public void resolveView() {
        mTitlebar = findViewById(R.id.titlebar);
        mViewContent = findViewById(R.id.viewContent);
        //将继承 TopBarBaseActivity 的布局解析到 FrameLayout 里面
        LayoutInflater.from(this).inflate(getContentView(), mViewContent);
    }

    /**
     * 绑定插件
     */
    public void bindPlugins() {
        ButterKnife.bind(this);
    }

    /**
     * 初始化TitleBar
     */
    public TitleBar getTitleBar() {
        mTitleBar = new TitleBar(BaseActivity.this);
        mTitleBar.setLeftImageListening(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        return mTitleBar;
    }

    /**
     * 显示TitleBar
     */
    public void showTitleBar() {
        mTitlebar.setVisibility(View.VISIBLE);
    }

    /**
     * 隐藏TitleBar
     */
    public void hintTitleBar() {
        mTitlebar.setVisibility(View.GONE);
    }

    /**
     * 返回空布局
     */
    public View getEmptyView() {
        return LayoutInflater.from(BaseActivity.this).inflate(R.layout.layout_empty_view, null);
    }

    /**
     * 获取布局文件
     *
     * @return
     */
    public abstract int getContentView();

    /**
     * 初始化TitleBar
     */
    public abstract void initTitleBar();

    /**
     * 初始化数据（控件的赋值）
     */
    public abstract void initData();

    /**
     * 动态权限接口定义
     *
     * @param callback
     */
    public void requestPermissions(PermissionsCallback callback) {
        mCallback = callback;
        if (EasyPermissions.hasPermissions(this, perms)) {
            mCallback.onAccept();
        } else {
            EasyPermissions.requestPermissions(this, "请同意以下权限!", 100, perms);
        }
    }

    /**
     * 动态权限回调
     *
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    /**
     * 权限接受回调
     *
     * @param requestCode
     * @param perms
     */
    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        mCallback.onAccept();
    }

    /**
     * 权限拒绝回调
     *
     * @param requestCode
     * @param perms
     */
    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        mCallback.onDenied();
    }

    /**
     * 显示Toast
     *
     * @param msg
     */
    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * 获取Eventbus的实例
     *
     * @return
     */
    public EventBus getEventBus() {
        return EventBus.getDefault();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ImmersionBar.with(this).destroy();
    }

    /**
     * 点击非输入框的空白区域自动隐藏软键盘
     * @param ev
     * @return
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (isShouldHideInput(v, ev)) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null) {
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
            return super.dispatchTouchEvent(ev);
        }
        // 必不可少，否则所有的组件都不会有TouchEvent了
        if (getWindow().superDispatchTouchEvent(ev)) {
            return true;
        }
        return onTouchEvent(ev);
    }

    /**
     * 点击非输入框的空白区域自动隐藏软键盘
     * @param v
     * @param event
     * @return
     */
    public boolean isShouldHideInput(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] leftTop = { 0, 0 };
            //获取输入框当前的location位置
            v.getLocationInWindow(leftTop);
            int left = leftTop[0];
            int top = leftTop[1];
            int bottom = top + v.getHeight();
            int right = left + v.getWidth();
            if (event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom) {
                // 点击的是输入框区域，保留点击EditText的事件
                return false;
            } else {
                return true;
            }
        }
        return false;
    }
}
