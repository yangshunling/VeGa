package com.jingwei.vega.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.jingwei.vega.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * BaseActivity基类
 */
public abstract class BaseFragment extends Fragment implements View.OnClickListener {

    private Unbinder unbinder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return LayoutInflater.from(getActivity()).inflate(getContentView(), null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
        initView(view);
        setListener();
        initData();
    }

    /**
     * 获取布局文件
     *
     * @return
     */
    public abstract int getContentView();

    /**
     * 初始化View
     */
    public abstract void initView(View rootView);

    /**
     * 设置View的点击事件
     */
    protected abstract void setListener();

    /**
     * 初始化数据（控件的赋值）
     */
    public abstract void initData();

    /**
     * 返回空布局
     */
    public View getEmptyView() {
        return LayoutInflater.from(getActivity()).inflate(R.layout.layout_empty_view, null);
    }

    /**
     * 显示Toast
     *
     * @param msg
     */
    public void showToast(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
