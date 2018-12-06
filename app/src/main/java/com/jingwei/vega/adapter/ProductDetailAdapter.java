package com.jingwei.vega.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.jingwei.vega.R;
import com.jingwei.vega.utils.GlideUtil;

import java.util.List;

/**
 * Created by Anonymous on 2018/5/21.
 */

public class ProductDetailAdapter extends BaseAdapter {

    private Context context;
    private List<String> mBeanList;

    public ProductDetailAdapter(Context context, List<String> mBeanList) {
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
                    R.layout.item_product_detail_recycle, null);

            viewHolder.mIvPic = (ImageView) convertView.findViewById(R.id.iv_pic);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        //初始化
        GlideUtil.setImage(context, mBeanList.get(position), viewHolder.mIvPic);
        return convertView;
    }

    static class ViewHolder {
        ImageView mIvPic;
    }
}
