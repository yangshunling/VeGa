package com.jingwei.vega.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.jingwei.vega.R;
import com.jingwei.vega.activity.MarketShopsActivity;
import com.jingwei.vega.moudle.bean.HomeBean;
import com.jingwei.vega.moudle.bean.MarketListBean;
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

            viewHolder.mTvHomeTitle = (TextView) convertView.findViewById(R.id.tv_home_title);
            viewHolder.mLvHomeList = (ListView) convertView.findViewById(R.id.lv_home_list);

            viewHolder.mLvHomeList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(context,MarketShopsActivity.class);
                    context.startActivity(intent);
                }
            });

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        //初始化
        viewHolder.mTvHomeTitle.setText(mBeanList.get(position).getName());
//        viewHolder.mLvHomeList.setAdapter(new ItemHomeListAdapter(context,mBeanList.get(position).getCardBeans()));
        ListViewUtil.setListViewHeightBasedOnChildren(viewHolder.mLvHomeList);
        return convertView;
    }

    static class ViewHolder {
        TextView mTvHomeTitle;
        ListView mLvHomeList;
    }
}
