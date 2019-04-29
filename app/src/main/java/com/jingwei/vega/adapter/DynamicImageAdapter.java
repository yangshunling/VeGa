package com.jingwei.vega.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.jingwei.vega.Constants;
import com.jingwei.vega.R;
import com.jingwei.vega.moudle.bean.DynamicBean;
import com.jingwei.vega.utils.GlideUtil;
import com.jingwei.vega.utils.TextUtil;

import java.util.List;

/**
 * Created by Anonymous on 2018/5/21.
 */

public class DynamicImageAdapter extends BaseAdapter {

    private Context context;
    private List<DynamicBean.PageListBean.ListBean.ProductBean.PicturesBean> mBeanList;

    public DynamicImageAdapter(Context context, List<DynamicBean.PageListBean.ListBean.ProductBean.PicturesBean> mBeanList) {
        this.context = context;
        this.mBeanList = mBeanList;
    }

    @Override
    public int getCount() {
        return mBeanList.size();
    }

    @Override
    public Object getItem(int position) {
        return mBeanList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(
                    R.layout.item_dynamic_image_gride, null);

            viewHolder.mIvDynamicImage = (ImageView) convertView.findViewById(R.id.iv_dynamic_image);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        //初始化
        if( !TextUtils.isEmpty( mBeanList.get(position).getPath())){
            viewHolder.mIvDynamicImage.setVisibility(View.VISIBLE);

            GlideUtil.setImage(context, Constants.IMAGEHOST + mBeanList.get(position).getPath(), viewHolder.mIvDynamicImage);
        } else {
            viewHolder.mIvDynamicImage.setVisibility(View.GONE);
        }
        return convertView;
    }

    static class ViewHolder {
        ImageView mIvDynamicImage;
    }
}
