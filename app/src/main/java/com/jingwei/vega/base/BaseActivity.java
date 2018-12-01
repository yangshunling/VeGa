package com.jingwei.vega.base;

import android.Manifest;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
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

    public void setTransparent() {
        ImmersionBar.with(this).fitsSystemWindows(false).transparentBar().init();
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

}
