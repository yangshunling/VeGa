package com.jingwei.vega.view.cardgallery;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jingwei.vega.R;
import com.jingwei.vega.utils.GlideUtil;

import java.util.List;

/**
 * Created by jameson on 8/30/16.
 */
public class CardGalleryAdapter extends RecyclerView.Adapter<CardGalleryAdapter.ViewHolder> {

    private Context mContext;
    private List<String> mList;
    private CardAdapterHelper mCardAdapterHelper = new CardAdapterHelper();

    public CardGalleryAdapter(Context mContext, List<String> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card_gallery, parent, false);
        mCardAdapterHelper.onCreateViewHolder(parent, itemView);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        mCardAdapterHelper.onBindViewHolder(holder.itemView, position, getItemCount());
        GlideUtil.setImage(mContext,mList.get(position),holder.mImageView);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final ImageView mImageView;

        public ViewHolder(final View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.imageView);
        }

    }

}
