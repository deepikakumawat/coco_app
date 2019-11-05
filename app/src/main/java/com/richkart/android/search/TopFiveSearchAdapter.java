package com.richkart.android.search;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.richkart.android.R;

import java.util.List;


public class TopFiveSearchAdapter extends RecyclerView.Adapter<TopFiveSearchAdapter.ViewHolder> implements View.OnClickListener {
    private Context context;
    private List<String> productDataArrayList;
    private SearchFragment searchFragment;




    public TopFiveSearchAdapter(Context context, List<String> productDataArrayList, SearchFragment searchFragment) {
        this.context = context;
        this.productDataArrayList = productDataArrayList;
        this.searchFragment = searchFragment;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_top_five_search, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        String productData = productDataArrayList.get(position);
        if (productData != null) {
            holder.txtSearchName.setText(TextUtils.isEmpty(productData) ? "-" : productData);

            holder.txtSearchName.setTag(productData);
            holder.txtSearchName.setOnClickListener(searchFragment);





        }

    }


    @Override
    public int getItemCount() {
        return productDataArrayList.size();
    }

    @Override
    public void onClick(View view) {

    }

    protected class ViewHolder extends RecyclerView.ViewHolder {

        private TextView txtSearchName;




        public ViewHolder(View view) {
            super(view);

            txtSearchName = view.findViewById(R.id.txtSearchName);



        }
    }
}
