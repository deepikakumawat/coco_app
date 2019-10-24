package com.richkart.android.search;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.richkart.android.R;

import java.util.ArrayList;


public class TrendingAdapter extends RecyclerView.Adapter<TrendingAdapter.ViewHolder> implements View.OnClickListener {
    private Context context;
    private ArrayList<TrendingResponse.TrendingData> trendingDataArrayList;
    private SearchFragment searchFragment;




    public TrendingAdapter(Context context, ArrayList<TrendingResponse.TrendingData> trendingDataArrayList, SearchFragment searchFragment) {
        this.context = context;
        this.trendingDataArrayList = trendingDataArrayList;
        this.searchFragment = searchFragment;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_trendings, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        TrendingResponse.TrendingData productData = trendingDataArrayList.get(position);
        if (productData != null) {
            holder.txtTrending.setText(TextUtils.isEmpty(productData.getmName()) ? "-" : productData.getmName());


            holder.lyTop.setTag(productData);
            holder.lyTop.setOnClickListener(searchFragment);





        }

    }


    @Override
    public int getItemCount() {
        return trendingDataArrayList.size();
    }

    @Override
    public void onClick(View view) {

    }

    protected class ViewHolder extends RecyclerView.ViewHolder {

        private TextView txtTrending;
        private LinearLayout lyTop;




        public ViewHolder(View view) {
            super(view);


            txtTrending = view.findViewById(R.id.txtTrending);
            lyTop = view.findViewById(R.id.lyTop);


        }
    }
}
