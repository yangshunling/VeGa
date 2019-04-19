package com.jingwei.vega.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import androidx.core.content.ContextCompat;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jingwei.vega.R;
import com.jingwei.vega.utils.GlideUtil;


/**
 * 自定义组合控件
 */

public class CustomLinearLayout extends LinearLayout {

    //自定义属性
    private Drawable leftImageRes;
    private String leftText;
    private int leftTextColor;
    private String titleText;
    private int titleTextColor;
    private int titleGravity;
    private Drawable rightImageRes;
    private String rightText;
    private int rightTextColor;
    private boolean mHideArrow;
    //自定义控件
    private View rootView;
    private ImageView mLeftImage;
    private TextView mLeftText;
    private TextView mTitle;
    private ImageView mRightImage;
    private TextView mRightText;
    private ImageView mArrow;

    private Context mContext;

    public CustomLinearLayout(Context context) {
        super(context);
    }

    public CustomLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.CustomLinearLayout);
        leftImageRes = attributes.getDrawable(R.styleable.CustomLinearLayout_left_image);
        leftText = attributes.getString(R.styleable.CustomLinearLayout_left_text);
        leftTextColor = attributes.getColor(R.styleable.CustomLinearLayout_left_text_color, ContextCompat.getColor(context, R.color.black));
        titleText = attributes.getString(R.styleable.CustomLinearLayout_title);
        titleTextColor = attributes.getColor(R.styleable.CustomLinearLayout_title_text_color, ContextCompat.getColor(context, R.color.black));
        titleGravity = attributes.getInt(R.styleable.CustomLinearLayout_title_gravity, 1);
        rightImageRes = attributes.getDrawable(R.styleable.CustomLinearLayout_right_image);
        rightText = attributes.getString(R.styleable.CustomLinearLayout_right_text);
        rightTextColor = attributes.getColor(R.styleable.CustomLinearLayout_right_text_color, ContextCompat.getColor(context, R.color.black));
        mHideArrow = attributes.getBoolean(R.styleable.CustomLinearLayout_hide_arrow, false);
        //回收资源
        attributes.recycle();
        initView();
    }

    private void initView() {
        rootView = View.inflate(getContext(), R.layout.custom_linearlayout, this);
        mLeftImage = (ImageView) rootView.findViewById(R.id.left_image);
        mLeftText = (TextView) rootView.findViewById(R.id.left_text);
        mTitle = (TextView) rootView.findViewById(R.id.title);
        mRightText = (TextView) rootView.findViewById(R.id.right_text);
        mRightImage = (ImageView) rootView.findViewById(R.id.right_image);
        mArrow = (ImageView) rootView.findViewById(R.id.custom_arrow);

        mLeftImage.setImageDrawable(leftImageRes);
        mLeftImage.setVisibility(leftImageRes == null ? View.GONE : View.VISIBLE);
        mLeftText.setText(leftText);
        mLeftText.setTextColor(leftTextColor);
        mTitle.setText(titleText);
        mTitle.setTextColor(titleTextColor);
        mRightImage.setImageDrawable(rightImageRes);
        mRightImage.setVisibility(rightImageRes == null ? View.GONE : View.VISIBLE);
        mRightText.setText(rightText);
        mRightText.setTextColor(rightTextColor);

        if (titleGravity == 2) {
            mTitle.setGravity(Gravity.RIGHT);
        }
        if (mHideArrow) {
            mArrow.setVisibility(View.GONE);
        }
    }

    //自定义方法
    public void setLeftText(String text) {
        mLeftText.setText(text);
    }

    public void setTitleText(String text) {
        mTitle.setText(text);
    }

    public String getTitleText() {
        return mTitle.getText().toString().trim();
    }

    public <T> void setRightImage(T res) {
        GlideUtil.setCircleImage(mContext, res, mRightImage);
        mRightImage.setVisibility(View.VISIBLE);
    }

    public void setRightText(String text) {
        mRightText.setText(text);
    }

    public String getRightText() {
        return mRightText.getText().toString().trim();
    }

    public ImageView getRightImageView() {
        return mRightImage;
    }
}


