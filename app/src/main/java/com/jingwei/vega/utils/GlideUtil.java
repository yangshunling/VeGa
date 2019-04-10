package com.jingwei.vega.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;

import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * Glide图片加载工具类
 */
public class GlideUtil {

    /**
     * 加载图片
     *
     * @param context
     * @param resourceId
     * @param mImageView
     */
    static public <T> void setImage(Context context, T resourceId, ImageView mImageView) {
        Glide.with(context)
                .load(resourceId)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(mImageView);
    }

    /**
     * 加载图片（圆形）
     *
     * @param context
     * @param resourceId
     * @param mImageView
     */
    static public <T> void setCircleImage(Context context, T resourceId, ImageView mImageView) {
        Glide.with(context)
                .load(resourceId)
                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                .into(mImageView);

    }

    /**
     * 加载的图片（圆角）
     * coner自定义角度
     *
     * @param context
     * @param resourceId
     * @param coner      圆弧的弧度
     * @param mImageView
     */
    static public <T> void setRoundImage(Context context, T resourceId, int coner, ImageView mImageView) {
        Glide.with(context)
                .load(resourceId)
                .apply(RequestOptions.bitmapTransform(new RoundedCornersTransformation(coner, 0)))
                .into(mImageView);
    }

    /**
     * 高斯迷糊
     *
     * @param context
     * @param resourceId
     * @param mImageView
     */
    static public <T> void setBlurTransImage(Context context, T resourceId,ImageView mImageView) {
        Glide.with(context)
                .load(resourceId)
                .apply(RequestOptions.bitmapTransform(new BlurTransformation(15,4)))
                .into(mImageView);
    }
}
