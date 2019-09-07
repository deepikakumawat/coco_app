package com.nav.richkart.product_details;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nav.richkart.R;
import com.nav.richkart.product_details.project_details_response.ProductAttributeData;

import java.util.ArrayList;


public class ProductDetailsAttributeAdapter extends RecyclerView.Adapter<ProductDetailsAttributeAdapter.ViewHolder> {
    private Context context;
    private ArrayList<ProductAttributeData> productAttributeDataArrayList;
    private ProductDetailFragment productDetailFragment;


    public ProductDetailsAttributeAdapter(Context context, ArrayList<ProductAttributeData> productAttributeDataArrayList, ProductDetailFragment productDetailFragment) {
        this.context = context;
        this.productAttributeDataArrayList = productAttributeDataArrayList;
        this.productDetailFragment = productDetailFragment;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_include_product_details_attr, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        ProductAttributeData productData = productAttributeDataArrayList.get(position);
        if (productData != null) {

          
            holder. txtAttrType .setText(!TextUtils.isEmpty(productData.getmAttributeType()) ? productData.getmAttributeType() : "-");
            holder. txtAttrName .setText(!TextUtils.isEmpty(productData.getmAttributeName()) ? productData.getmAttributeName() : "-");



        
        }

    }


    @Override
    public int getItemCount() {
        return productAttributeDataArrayList.size();
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtAttrType ;
        TextView txtAttrName ;



        public ViewHolder(View view) {
            super(view);

            txtAttrType = view.findViewById(R.id.txtAttrType);
            txtAttrName = view.findViewById(R.id.txtAttrName);




        }
    }
}
