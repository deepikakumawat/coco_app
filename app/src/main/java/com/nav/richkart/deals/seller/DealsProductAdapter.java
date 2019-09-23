package com.nav.richkart.deals.seller;

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
import com.nav.richkart.R;
import com.nav.richkart.product_details.project_details_response.ProductDetailsSimilier;
import com.nav.richkart.utility.Constant;

import java.util.ArrayList;


public class DealsProductAdapter extends RecyclerView.Adapter<DealsProductAdapter.ViewHolder> implements View.OnClickListener {
    private Context context;
    private ArrayList<ProductDetailsSimilier> productDataArrayList;
    private DealsProductFragment sellerProductFragment;


    public DealsProductAdapter(Context context, ArrayList<ProductDetailsSimilier> productDataArrayList, DealsProductFragment sellerProductFragment) {
        this.context = context;
        this.productDataArrayList = productDataArrayList;
        this.sellerProductFragment = sellerProductFragment;

    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tabgrid, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        ProductDetailsSimilier productData = productDataArrayList.get(position);
        if (productData != null) {
            holder.txtProductName.setText(!TextUtils.isEmpty(productData.getmProductName()) ? productData.getmProductName() : "-");
            holder.txtProductTime.setText(!TextUtils.isEmpty(productData.getmDealend()) ? productData.getmDealend() : "-");
            holder.txtProductSalePrice.setText(!TextUtils.isEmpty(productData.getmSalePrice()) ?context.getString(R.string.Rs)+ productData.getmSalePrice() : "-");

           String thumbnail = Constant.MEDIA_THUMBNAIL_BASE_URL + productData.getmProductImg();
            Glide.with(context).load(thumbnail).placeholder(R.drawable.richkart).into(holder.imgProduct);

            holder.rbProductRating.setRating(!TextUtils.isEmpty(productData.getmAvgRating()) ? Float.parseFloat(productData.getmAvgRating()) : 0);



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

        TextView txtProductSalePrice, txtProductName,txtProductTime;
        ImageView imgProduct;
        LinearLayout lyAddToCart;
        private LinearLayout lyProduct;
        private RatingBar rbProductRating;


        public ViewHolder(View view) {
            super(view);

            rbProductRating =  view.findViewById(R.id.rbProductRating);
            imgProduct =  view.findViewById(R.id.imgProduct);
            txtProductName =  view.findViewById(R.id.txtProductName);
            txtProductTime =  view.findViewById(R.id.txtProductTime);
            txtProductSalePrice =  view.findViewById(R.id.txtProductSalePrice);
            lyProduct =  view.findViewById(R.id.lyProduct);
            lyAddToCart =  view.findViewById(R.id.lyAddToCart);


        }
    }
}
