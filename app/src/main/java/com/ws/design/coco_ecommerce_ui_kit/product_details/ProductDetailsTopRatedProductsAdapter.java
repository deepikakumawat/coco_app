package com.ws.design.coco_ecommerce_ui_kit.product_details;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.wolfsoft2.coco_ecommerce_ui_kit.R;
import com.ws.design.coco_ecommerce_ui_kit.product_details.project_details_response.ProductDetailsSimilier;
import com.ws.design.coco_ecommerce_ui_kit.utility.Constant;

import java.util.ArrayList;


public class ProductDetailsTopRatedProductsAdapter extends RecyclerView.Adapter<ProductDetailsTopRatedProductsAdapter.ViewHolder> {
    private Context context;
    private ArrayList<ProductDetailsSimilier> productDetailsSimilierArrayList;
    private ProductDetailFragment productDetailFragment;


    public ProductDetailsTopRatedProductsAdapter(Context context, ArrayList<ProductDetailsSimilier> productDetailsSimilierArrayList, ProductDetailFragment productDetailFragment) {
        this.context = context;
        this.productDetailsSimilierArrayList = productDetailsSimilierArrayList;
        this.productDetailFragment = productDetailFragment;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_home_top_rated_products, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        ProductDetailsSimilier productData = productDetailsSimilierArrayList.get(position);
        if (productData != null) {

            holder.txtProductName.setText(productData.getmProductName());

            holder. txtProductPrice .setText(context.getString(R.string.rs1)+productData.getmPrice());
            holder. txtRating .setText(!TextUtils.isEmpty(productData.getmAvgRating()) ? productData.getmAvgRating() : "5");



            String thumbnail = Constant.THUMBNAIL_BASE_URL + productData.getmProductImg();
            Glide.with(context).load(thumbnail).placeholder(R.drawable.richkart).dontAnimate().into(holder.imgProduct);


            holder.lyProduct.setTag(productData);
            holder.lyProduct.setTag(R.id.lyProduct,position);
            holder.lyProduct.setOnClickListener(productDetailFragment);

        }

    }


    @Override
    public int getItemCount() {
        return productDetailsSimilierArrayList.size();
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgProduct;
        private TextView txtProductName;
        private TextView txtProductType;
        private TextView txtProductPrice;
        private TextView txtRating;
        private LinearLayout lyProduct;





        public ViewHolder(View view) {
            super(view);

            txtRating = view.findViewById(R.id.txtRating);
            imgProduct = view.findViewById(R.id.imgProduct);
            txtProductName = view.findViewById(R.id.txtProductName);
            txtProductType = view.findViewById(R.id.txtProductType);
            txtProductPrice = view.findViewById(R.id.txtProductPrice);
            lyProduct = view.findViewById(R.id.lyProduct);




        }
    }
}
