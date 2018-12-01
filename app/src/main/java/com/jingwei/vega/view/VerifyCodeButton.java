package com.jingwei.vega.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.CountDownTimer;
import android.util.AttributeSet;
import android.widget.Button;

import com.jingwei.vega.R;

@SuppressLint("AppCompatCustomView")
public class VerifyCodeButton extends Button {
    private Context mContext;
    private int mClickedBackground;//点击后背景
    private int mBackground;//当前背景
    private String mCountdownownText;
    private int mCountdownTime = 60;
    private TimeCount mTimeCount;

    public VerifyCodeButton(Context context) {
        this(context, null);
    }

    public VerifyCodeButton(Context context, AttributeSet attrs) {
        this(context, attrs, android.R.attr.buttonStyle);
    }

    public VerifyCodeButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initAttrs(attrs);
        init();
    }

    private void initAttrs(AttributeSet attrs) {
        TypedArray typedArray = mContext.obtainStyledAttributes(attrs, R.styleable.VerifyCodeButton);
        mBackground = typedArray.getResourceId(R.styleable.VerifyCodeButton_android_background, mBackground);
        mClickedBackground = typedArray.getResourceId(R.styleable.VerifyCodeButton_clickedBackground, mClickedBackground);
        mCountdownTime = typedArray.getInt(R.styleable.VerifyCodeButton_countdownTime, mCountdownTime);
        mCountdownownText = typedArray.getString(R.styleable.VerifyCodeButton_countdownText);
        typedArray.recycle();
    }

    private void init() {
        setBackgroundResource(mBackground);
        mTimeCount = new TimeCount(mCountdownTime * 1000, 1000);
    }

    /**
     * 开始计时
     */
    public void start() {
        mTimeCount.start();
    }

    /**
     * 取消计时
     */
    public void cancle() {
        mTimeCount.cancel();
        setClickable(true);
        setText(mCountdownownText != null ? mCountdownownText : "");
        setBackgroundResource(mBackground);
    }

    class TimeCount extends CountDownTimer {

        /**
         * @param millisInFuture    总时间
         * @param countDownInterval 间隔时间
         */
        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        /**
         * @param millisUntilFinished 当前时间
         */
        @Override
        public void onTick(long millisUntilFinished) {
            setClickable(false);
            setText(String.valueOf(millisUntilFinished / 1000 + "s"));
            setBackgroundResource(mClickedBackground);
        }

        @Override
        public void onFinish() {
            setClickable(true);
            setText(mCountdownownText != null ? mCountdownownText : "");
            setBackgroundResource(mBackground);
        }
    }
}