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
import com.ws.design.coco_ecommerce_ui_kit.product_details.project_details_response.ProductBroughtData;


import java.util.ArrayList;


public class ProductDetailsBroughtDataAdapter extends RecyclerView.Adapter<ProductDetailsBroughtDataAdapter.ViewHolder> {
    private Context context;
    private ArrayList<ProductBroughtData> productBroughtDataArrayList;
    private ProductDetailFragment productDetailFragment;


    public ProductDetailsBroughtDataAdapter(Context context, ArrayList<ProductBroughtData> productBroughtDataArrayList, ProductDetailFragment productDetailFragment) {
        this.context = context;
        this.productBroughtDataArrayList = productBroughtDataArrayList;
        this.productDetailFragment = productDetailFragment;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_bought_products, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        ProductBroughtData productBroughtData = productBroughtDataArrayList.get(position);
        if (productBroughtData != null) {

            holder.txtProductName.setText(productBroughtData.getmProductName());

            holder. txtProductPrice .setText(context.getString(R.string.rs1)+productBroughtData.getmPrice());
            holder. txtRating .setText(!TextUtils.isEmpty(productBroughtData.getmAvgRating()) ? productBroughtData.getmAvgRating() : "5");



            String thumbnail =  productBroughtData.getmProductImg();
            Glide.with(context).load(thumbnail).placeholder(R.drawable.richkart).dontAnimate().into(holder.imgProduct);



            holder.imgAddToCart.setTag(productBroughtData);
            holder.imgAddToCart.setTag(R.id.imgAddToCart,position);
            holder.imgAddToCart.setOnClickListener(productDetailFragment);

            holder.lyBroughtProduct.setTag(productBroughtData);
            holder.lyBroughtProduct.setTag(R.id.lyBroughtProduct,position);
            holder.lyBroughtProduct.setOnClickListener(productDetailFragment);



        }

    }


    @Override
    public int getItemCount() {
        return productBroughtDataArrayList.size();
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgProduct;
        private TextView txtProductName;
        private TextView txtProductType;
        private TextView txtProductPrice;
        private TextView txtRating;
        private ImageView imgAddToCart;
        private LinearLayout lyBroughtProduct;





        public ViewHolder(View view) {
            super(view);

            txtRating = view.findViewById(R.id.txtRating);
            imgProduct = view.findViewById(R.id.imgProduct);
            txtProductName = view.findViewById(R.id.txtProductName);
            txtProductType = view.findViewById(R.id.txtProductType);
            txtProductPrice = view.findViewById(R.id.txtProductPrice);
            imgAddToCart = view.findViewById(R.id.imgAddToCart);
            lyBroughtProduct = view.findViewById(R.id.lyBroughtProduct);



        }
    }
}
