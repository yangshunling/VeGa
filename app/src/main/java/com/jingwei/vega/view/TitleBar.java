package com.jingwei.vega.view;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jingwei.vega.R;
import com.jingwei.vega.utils.TextUtil;

/**
 * TitleBar
 *
 * Created by Anonymous on 2017/8/23.
 */

public class TitleBar {

    private LinearLayout mTitlebar;
    private ImageView mIvLeft;
    private TextView mTvLeft;
    private TextView mTitleText;
    private TextView mTvRight;
    private ImageView mIvRight;

    /**
     * 初始化TitleBar
     *
     * @param context
     */
    public TitleBar(Activity context) {
        mTitlebar = (LinearLayout) context.findViewById(R.id.titlebar);
        mIvLeft = (ImageView) context.findViewById(R.id.iv_left);
        mTvLeft = (TextView) context.findViewById(R.id.text_left);
        mTitleText = (TextView) context.findViewById(R.id.title_text);
        mTvRight = (TextView) context.findViewById(R.id.text_right);
        mIvRight = (ImageView) context.findViewById(R.id.icon_right);
    }

    /**
     * 背景色
     */
    public TitleBar setTitleBarBg(int resId) {
        mTitlebar.setBackgroundColor(resId);
        return this;
    }

    /**
     * 左边标题栏图片
     */
    public TitleBar setLeftImage(int resId) {
        mIvLeft.setVisibility(resId > 0 ? View.VISIBLE : View.GONE);
        mIvLeft.setImageResource(resId);
        return this;
    }

    /**
     * 中间标题栏文字
     */
    public TitleBar setTitleText(String titleText) {
        mTitleText.setText(titleText);
        return this;
    }

    /**
     * 中间标题栏文字颜色
     */
    public TitleBar setTitleTextColor(int resId) {
        mTitleText.setTextColor(resId);
        return this;
    }

    /**
     * 右边标题栏文字
     */
    public TitleBar setRightText(String rightText) {
        mTvRight.setVisibility(TextUtil.isEmpty(rightText) ? View.GONE : View.VISIBLE);
        mTvRight.setText(rightText);
        return this;
    }

    /**
     * 右边标题栏文字颜色
     */
    public TitleBar setRightTextColor(int resId) {
        mTvRight.setTextColor(resId);
        return this;
    }

    /**
     * 右边标题栏图片
     */
    public TitleBar setRightImage(int resId) {
        mIvRight.setVisibility(resId > 0 ? View.VISIBLE : View.GONE);
        mIvRight.setImageResource(resId);
        return this;
    }

    /**
     * 左边标题栏图片的监听
     */
    public TitleBar setLeftImageListening(View.OnClickListener listener) {
        mIvLeft.setOnClickListener(listener);
        return this;
    }

    /**
     * 左边标题栏文字的监听
     */
    public TitleBar setLeftTextListening(View.OnClickListener listener) {
        mTvLeft.setOnClickListener(listener);
        return this;
    }

    /**
     * 中间标题栏图片的监听
     */
    public TitleBar setTitleListening(View.OnClickListener listener) {
        mTitleText.setOnClickListener(listener);
        return this;
    }

    /**
     * 右边标题栏图片的监听
     */
    public TitleBar setRightImageListening(View.OnClickListener listener) {
        mIvRight.setOnClickListener(listener);
        return this;
    }

    /**
     * 右边标题栏文字的监听
     */
    public TitleBar setRightTextListening(View.OnClickListener listener) {
        mTvRight.setOnClickListener(listener);
        return this;
    }

    /**
     * 获取相应的控件
     */
    public ImageView getRightImage() {
        return mIvRight;
    }
}
