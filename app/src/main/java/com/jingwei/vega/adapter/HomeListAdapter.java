package com.jingwei.vega.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jingwei.vega.R;
import com.jingwei.vega.moudle.bean.HomeBean;
import com.jingwei.vega.utils.GlideUtil;

import java.util.List;

public class HomeListAdapter extends BaseAdapter {

    private Context context;
    private List<HomeBean.CardBean> mBeanList;

    public HomeListAdapter(Context context, List<HomeBean.CardBean> mBeanList) {
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

            viewHolder.url = (ImageView) convertView.findViewById(R.id.iv_home_list_item);
            viewHolder.title = (TextView) convertView.findViewById(R.id.tv_home_list_item);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        //初始化
        GlideUtil.setImage(context, mBeanList.get(position).getUrl(), viewHolder.url);
        viewHolder.title.setText(mBeanList.get(position).getName());
        return convertView;
    }

    static class ViewHolder {
        ImageView url;
        TextView title;
    }
}
