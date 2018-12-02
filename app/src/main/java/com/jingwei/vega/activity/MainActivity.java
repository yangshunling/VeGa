package com.jingwei.vega.activity;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.jingwei.vega.R;
import com.jingwei.vega.base.BaseActivity;
import com.jingwei.vega.fragment.ClassificationFragment;
import com.jingwei.vega.fragment.FocusFragment;
import com.jingwei.vega.fragment.HomeFargment;
import com.jingwei.vega.fragment.MeFragment;
import com.jingwei.vega.view.TitleBar;

import butterknife.BindView;

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

    private HomeFargment mHomeFragment;
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
        mTitleBar = getTitleBar();
        mTitleBar.setLeftImage(R.drawable.icon_back).setTitleText(titles[0]);
        hintTitleBar();
    }

    @Override
    public void initData() {
        initView();
        initNavBar();
        initFragment();
    }

    private void initView() {
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
        mHomeFragment = new HomeFargment();
        mClassificationFragment = new ClassificationFragment();
        mFocusFragment = new FocusFragment();
        mMeFragment = new MeFragment();
        //添加Fragment
        transaction.add(R.id.main_content, mClassificationFragment);
        transaction.add(R.id.main_content, mFocusFragment);
        transaction.add(R.id.main_content, mMeFragment);
        transaction.add(R.id.main_content, mHomeFragment);
        transaction.commit();
    }

    @Override
    public void onTabSelected(int position) {
        switch (position) {
            case 0:
                hintTitleBar();
                transaction = manager.beginTransaction();
                hintFragment(transaction);
                transaction.show(mHomeFragment);
                transaction.commit();
                break;
            case 1:
                showTitleBar();
                initTitle(1);
                transaction = manager.beginTransaction();
                hintFragment(transaction);
                transaction.show(mClassificationFragment);
                transaction.commit();
                break;
            case 2:
                hintTitleBar();
                transaction = manager.beginTransaction();
                hintFragment(transaction);
                transaction.show(mFocusFragment);
                transaction.commit();
                break;
            case 3:
                showTitleBar();
                initTitle(3);
                transaction = manager.beginTransaction();
                hintFragment(transaction);
                transaction.show(mMeFragment);
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
            transaction.hide(mClassificationFragment);
        }
        if (mMeFragment != null) {
            transaction.hide(mMeFragment);
        }
    }
}
