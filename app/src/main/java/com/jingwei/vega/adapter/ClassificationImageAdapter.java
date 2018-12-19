package com.jingwei.vega.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jingwei.vega.Constants;
import com.jingwei.vega.R;
import com.jingwei.vega.moudle.bean.CategoryByTwoBean;
import com.jingwei.vega.moudle.bean.ClassificationRightBean;
import com.jingwei.vega.utils.GlideUtil;

import java.util.List;

/**
 * Created by Anonymous on 2018/5/21.
 */

public class ClassificationImageAdapter extends BaseAdapter {

    private Context context;
    private List<CategoryByTwoBean.ListBean.SonListBean> mBeanList;

    public ClassificationImageAdapter(Context context, List<CategoryByTwoBean.ListBean.SonListBean> mBeanList) {
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
                    R.layout.item_classification_image_gride, null);

            viewHolder.mIvClassificationImage = (ImageView) convertView.findViewById(R.id.iv_classification_image);
            viewHolder.mTvClassificationName = (TextView) convertView.findViewById(R.id.tv_classification_name);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        //初始化
        GlideUtil.setImage(context, Constants.IMAGEHOST + mBeanList.get(position).getIcon(), viewHolder.mIvClassificationImage);
        viewHolder.mTvClassificationName.setText(mBeanList.get(position).getName());
        return convertView;
    }

    static class ViewHolder {
        ImageView mIvClassificationImage;
        TextView mTvClassificationName;
    }
}
