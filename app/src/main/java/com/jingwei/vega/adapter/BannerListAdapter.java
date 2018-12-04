package com.jingwei.vega.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.jingwei.vega.R;
import com.jingwei.vega.utils.GlideUtil;

import java.util.List;

public class BannerListAdapter extends PagerAdapter {

    private Context mContext;
    private List<String> mBannerList;

    public BannerListAdapter(Context mContext,List<String> mBannerList) {
        this.mContext = mContext;
        this.mBannerList = mBannerList;
    }

    @Override
    public int getCount() {
        return mBannerList.size();
    }

    @Override
    public int getItemPosition(final Object object) {
        return POSITION_NONE;
    }

    @Override
    public Object instantiateItem(final ViewGroup container, final int position) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_banner,null);
        ImageView imageView = view.findViewById(R.id.iv_item_banner);
        GlideUtil.setImage(mContext,mBannerList.get(position),imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, position+"->"+mBannerList.get(position), Toast.LENGTH_SHORT).show();
            }
        });
        container.addView(view);
        return view;
    }

    @Override
    public boolean isViewFromObject(final View view, final Object object) {
        return view.equals(object);
    }

    @Override
    public void destroyItem(final ViewGroup container, final int position, final Object object) {
        container.removeView((View) object);
    }
}
