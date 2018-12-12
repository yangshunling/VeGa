package com.jingwei.vega.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.jingwei.vega.Constants;
import com.jingwei.vega.R;
import com.jingwei.vega.activity.MarketShopsActivity;
import com.jingwei.vega.moudle.bean.HomeBean;
import com.jingwei.vega.moudle.bean.MarketListBean;
import com.jingwei.vega.utils.GlideUtil;
import com.jingwei.vega.utils.ListViewUtil;

import java.util.List;

public class HomeListAdapter extends BaseAdapter {

    private Context context;
    private List<MarketListBean.ListBeanX.ListBean> mBeanList;

    public HomeListAdapter(Context context, List<MarketListBean.ListBeanX.ListBean> mBeanList) {
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
                    R.layout.item_home_list, null);

            viewHolder.mIvHomeListItem = (ImageView) convertView.findViewById(R.id.iv_home_list_item);
            viewHolder.mTvHomeListItem = (TextView) convertView.findViewById(R.id.tv_home_list_item);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        //初始化
        viewHolder.mTvHomeListItem.setText(mBeanList.get(position).getName());
        GlideUtil.setImage(context, Constants.IMAGEHOST + mBeanList.get(position).getPic(), viewHolder.mIvHomeListItem);
        return convertView;
    }

    static class ViewHolder {
        ImageView mIvHomeListItem;
        TextView mTvHomeListItem;
    }
}
