package com.gumilang.rezza.challengeapps.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gumilang.rezza.challengeapps.R;
import com.gumilang.rezza.challengeapps.holder.LoveHolder;

import java.util.List;

/**
 * Created by rezza on 18/11/17.
 */

public class LoveAdapter extends RecyclerView.Adapter<LoveAdapter.ViewHolder> {

    private List<LoveHolder> mList;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.love_adapter, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        LoveHolder data = mList.get(position);
        holder.txvw_title_11.setText(data.title);
        holder.txvw_price_12.setText(data.price);
        holder.imvw_pic_10.setImageResource(R.drawable.ic_noimage);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public LoveAdapter(List<LoveHolder> pList){
        mList = pList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txvw_title_11, txvw_price_12;
        public ImageView imvw_pic_10;

        public ViewHolder(View view) {
            super(view);
            imvw_pic_10 = (ImageView) view.findViewById(R.id.imvw_pic_10);
            txvw_title_11 = (TextView) view.findViewById(R.id.txvw_title_11);
            txvw_price_12 = (TextView) view.findViewById(R.id.txvw_price_12);
        }
    }
}
