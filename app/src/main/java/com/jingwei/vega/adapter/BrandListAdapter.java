package com.jingwei.vega.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jingwei.vega.Constants;
import com.jingwei.vega.R;
import com.jingwei.vega.moudle.bean.BrandListBean;
import com.jingwei.vega.utils.GlideUtil;

import java.util.List;

public class BrandListAdapter extends BaseAdapter {

    private Context mContext;
    private List<BrandListBean.ListBeanX.ListBean> mBrandList;

    public BrandListAdapter(Context mContext, List<BrandListBean.ListBeanX.ListBean> mBannerList) {
        this.mContext = mContext;
        this.mBrandList = mBannerList;
    }

    @Override
    public int getCount() {
        return mBrandList.size();
    }

    @Override
    public Object getItem(int position) {
        return mBrandList.get(position);
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
            convertView = LayoutInflater.from(mContext).inflate(
                    R.layout.item_brand_list, null);

            viewHolder.mIvBrandListItem = (ImageView) convertView.findViewById(R.id.iv_brand_list_item);
            viewHolder.mTvBrandListItem = (TextView) convertView.findViewById(R.id.tv_brand_list_item);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        //初始化
        GlideUtil.setRoundImage(mContext, Constants.IMAGEHOST + mBrandList.get(position).getPic(), 15, viewHolder.mIvBrandListItem);
        viewHolder.mTvBrandListItem.setText(mBrandList.get(position).getName());
        return convertView;
    }

    static class ViewHolder {
        ImageView mIvBrandListItem;
        TextView mTvBrandListItem;
    }
}
