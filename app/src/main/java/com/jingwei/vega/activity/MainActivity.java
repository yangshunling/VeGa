package com.jingwei.vega.activity;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.jingwei.vega.R;
import com.jingwei.vega.base.BaseActivity;
import com.jingwei.vega.fragment.ClassificationFragment;
import com.jingwei.vega.fragment.FocusFragment;
import com.jingwei.vega.fragment.HomeFragment;
import com.jingwei.vega.fragment.MeFragment;
import com.jingwei.vega.moudle.TokenLose;
import com.jingwei.vega.utils.PreferencesUtil;
import com.jingwei.vega.view.TitleBar;

import butterknife.BindView;
import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;

/**
 * MainActivity
 */
public class MainActivity extends BaseActivity implements BottomNavigationBar.OnTabSelectedListener {

    @BindView(R.id.main_content)
    FrameLayout mMainContent;
    @BindView(R.id.nav_bar)
    BottomNavigationBar mNavBar;

    private TitleBar mTitleBar;
    private FragmentManager manager;
    private FragmentTransaction transaction;

    private HomeFragment mHomeFragment;
    private ClassificationFragment mClassificationFragment;
    private FocusFragment mFocusFragment;
    private MeFragment mMeFragment;

    private String[] titles = {"首页", "分类", "关注", "我的"};

    @Override
    public int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    public void initTitleBar() {
        hintTitleBar();
    }

    @Override
    public void initView() {
        EventBus.getDefault().register(this);

        initNavBar();
        initFragment();
    }

    @Override
    public void initData() {

    }


    private void initNavBar() {
        mNavBar.setTabSelectedListener(this);
        mNavBar.addItem(new BottomNavigationItem(R.drawable.nav_home, titles[0]))
                .addItem(new BottomNavigationItem(R.drawable.nav_classification, titles[1]))
                .addItem(new BottomNavigationItem(R.drawable.nav_focus, titles[2]))
                .addItem(new BottomNavigationItem(R.drawable.nav_me, titles[3]))
                .setFirstSelectedPosition(0)
                .initialise();
    }

    private void initFragment() {
        //实例化
        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        //创建Fragment
        mHomeFragment = new HomeFragment();
        transaction.add(R.id.main_content, mHomeFragment);
        transaction.commit();
    }

    @Override
    public void onTabSelected(int position) {
        initStatusBar();
        switch (position) {
            case 0:
                transaction = manager.beginTransaction();
                if (mHomeFragment==null){
                    mHomeFragment = new HomeFragment();
                    transaction.add(R.id.main_content, mHomeFragment);
                }else {
                    hintFragment(transaction);
                    transaction.show(mHomeFragment);
                }
                transaction.commit();
                break;
            case 1:
                transaction = manager.beginTransaction();
                if (mClassificationFragment==null){
                    mClassificationFragment = new ClassificationFragment();
                    transaction.add(R.id.main_content, mClassificationFragment);
                }else {
                    hintFragment(transaction);
                    transaction.show(mClassificationFragment);
                }
                transaction.commit();
                break;
            case 2:
                transaction = manager.beginTransaction();
                if (mFocusFragment==null){
                    mFocusFragment = new FocusFragment();
                    transaction.add(R.id.main_content, mFocusFragment);
                }else {
                    hintFragment(transaction);
                    transaction.show(mFocusFragment);
                }
                transaction.commit();
                break;
            case 3:
                setTransparent();
                transaction = manager.beginTransaction();
                if (mMeFragment==null){
                    mMeFragment = new MeFragment();
                    transaction.add(R.id.main_content, mMeFragment);
                }else {
                    hintFragment(transaction);
                    transaction.show(mMeFragment);
                }
                transaction.commit();
                break;
        }
    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }

    private void initTitle(int i) {
        mTitleBar.setTitleText(titles[i]);
    }

    private void hintFragment(FragmentTransaction transaction) {
        if (mHomeFragment != null) {
            transaction.hide(mHomeFragment);
        }
        if (mClassificationFragment != null) {
            transaction.hide(mClassificationFragment);
        }
        if (mFocusFragment != null) {
            transaction.hide(mFocusFragment);
        }
        if (mMeFragment != null) {
            transaction.hide(mMeFragment);
        }
    }

    @Subscribe(threadMode = ThreadMode.MainThread)
    public void tokenLose(TokenLose bean) {
        startActivity(new Intent(MainActivity.this, LoginActivity.class));
        PreferencesUtil.saveLoginState(MainActivity.this, false);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
