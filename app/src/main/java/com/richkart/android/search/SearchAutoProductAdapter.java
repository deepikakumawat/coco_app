package com.richkart.android.search;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.richkart.android.R;
import com.richkart.android.product_details.project_details_response.ProductDetailsSimilier;

import java.util.ArrayList;


public class SearchAutoProductAdapter extends RecyclerView.Adapter<SearchAutoProductAdapter.ViewHolder> implements View.OnClickListener {
    private Context context;
    private ArrayList<ProductDetailsSimilier> productDataArrayList;
    private SearchFragment searchFragment;




    public SearchAutoProductAdapter(Context context, ArrayList<ProductDetailsSimilier> productDataArrayList, SearchFragment searchFragment) {
        this.context = context;
        this.productDataArrayList = productDataArrayList;
        this.searchFragment = searchFragment;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_search_auto_product, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        ProductDetailsSimilier productData = productDataArrayList.get(position);
        if (productData != null) {
            holder.txtProductName.setText(TextUtils.isEmpty(productData.getmProductName()) ? "-" : productData.getmProductName());

            holder.txtProductName.setTag(productData);
            holder.txtProductName.setOnClickListener(searchFragment);





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

        private TextView txtProductName;




        public ViewHolder(View view) {
            super(view);

            txtProductName = view.findViewById(R.id.txtProductName);



        }
    }
}
