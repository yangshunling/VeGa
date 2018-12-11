package com.jingwei.vega.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jingwei.vega.R;
import com.jingwei.vega.moudle.bean.CategoryByOneBean;
import com.jingwei.vega.moudle.bean.ClassificationLeftBean;

import java.util.List;

public class ClassificationListAdapter extends BaseAdapter {
    private Context context;
    private List<CategoryByOneBean.ListBean> mList;

    public ClassificationListAdapter(Context context, List<CategoryByOneBean.ListBean> mList) {
        this.context = context;
        this.mList = mList;
    }


    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(
                    R.layout.item_classification_list, null);

            viewHolder.mTvLeft = (TextView) convertView.findViewById(R.id.tv_left);
            viewHolder.mTvTag = (TextView) convertView.findViewById(R.id.tv_tag);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        if (mList.get(position).isTag())
            viewHolder.mTvTag.setVisibility(View.VISIBLE);
        else
            viewHolder.mTvTag.setVisibility(View.INVISIBLE);

        viewHolder.mTvLeft.setText(mList.get(position).getName());

        return convertView;
    }

    static class ViewHolder {
        TextView mTvLeft;
        TextView mTvTag;
    }
}