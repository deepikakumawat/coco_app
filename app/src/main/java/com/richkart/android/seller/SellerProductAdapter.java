package com.richkart.android.seller;

import android.content.Context;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.richkart.android.R;

import com.richkart.android.product_details.project_details_response.ProductDetailsSimilier;
import com.richkart.android.utility.Constant;

import java.util.ArrayList;


public class SellerProductAdapter extends RecyclerView.Adapter<SellerProductAdapter.ViewHolder> implements View.OnClickListener {
    private Context context;
    private ArrayList<ProductDetailsSimilier> productDataArrayList;
    private SellerProductFragment sellerProductFragment;


    public SellerProductAdapter(Context context, ArrayList<ProductDetailsSimilier> productDataArrayList, SellerProductFragment sellerProductFragment) {
        this.context = context;
        this.productDataArrayList = productDataArrayList;
        this.sellerProductFragment = sellerProductFragment;

    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_items_product_by_category, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        ProductDetailsSimilier productData = productDataArrayList.get(position);
        if (productData != null) {
            holder.txtProductName.setText(!TextUtils.isEmpty(productData.getmProductName()) ? productData.getmProductName() : "-");
            holder.txtProductSalePrice.setText(!TextUtils.isEmpty(productData.getmSalePrice()) ?context.getString(R.string.Rs)+ productData.getmSalePrice() : "-");


            holder.txtProductPrice.setText(!TextUtils.isEmpty(productData.getmPrice()) ?context.getString(R.string.Rs)+ productData.getmPrice() : "-");
            holder.txtProductPrice.setPaintFlags(holder.txtProductPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

            String thumbnail = Constant.MEDIA_THUMBNAIL_BASE_URL + productData.getmProductImg();
            Glide.with(context).load(thumbnail).placeholder(R.drawable.richkart).into(holder.imgProduct);

            holder.rbProductRating.setRating(!TextUtils.isEmpty(productData.getmAvgRating()) ? Float.parseFloat(productData.getmAvgRating()) : 0);

            holder.txtProductDesc.setText(!TextUtils.isEmpty(productData.getmProductShortDetails()) ? Html.fromHtml(productData.getmProductShortDetails()) : "-");


            holder.lyAddToCart.setTag(productData);
            holder.lyAddToCart.setTag(R.id.lyAddToCart, position);

            holder.lyAddToCart.setOnClickListener(sellerProductFragment);

            holder.lyProduct.setTag(productData);
            holder.lyProduct.setTag(R.id.lyProduct,position);
            holder.lyProduct.setOnClickListener(sellerProductFragment);


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

        TextView txtProductSalePrice,txtProductPrice, txtProductName,txtProductDesc;
        ImageView imgProduct;
        LinearLayout lyAddToCart;
        LinearLayout linear;
        private LinearLayout lyProduct;
        private RatingBar rbProductRating;


        public ViewHolder(View view) {
            super(view);

            rbProductRating =  view.findViewById(R.id.rbProductRating);
            imgProduct =  view.findViewById(R.id.imgProduct);
            txtProductName =  view.findViewById(R.id.txtProductName);
            txtProductDesc =  view.findViewById(R.id.txtProductDesc);
            txtProductSalePrice =  view.findViewById(R.id.txtProductSalePrice);
            txtProductPrice =  view.findViewById(R.id.txtProductPrice);
            linear =  view.findViewById(R.id.linear);
            lyProduct =  view.findViewById(R.id.lyProduct);
            lyAddToCart =  view.findViewById(R.id.lyAddToCart);


        }
    }
}
