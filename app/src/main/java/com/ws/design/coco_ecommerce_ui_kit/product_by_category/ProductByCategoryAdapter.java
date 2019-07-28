package com.ws.design.coco_ecommerce_ui_kit.product_by_category;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.wolfsoft2.coco_ecommerce_ui_kit.R;
import com.ws.design.coco_ecommerce_ui_kit.home.home_response.ProductData;
import com.ws.design.coco_ecommerce_ui_kit.utility.Constant;

import java.util.List;

import fragment.PopularListFragment;


public class ProductByCategoryAdapter extends RecyclerView.Adapter<ProductByCategoryAdapter.MyViewHolder> {
    private Context context;
    private List<ProductData> productDataByCategoryList;
    private PopularListFragment mpopularListFragment;

    int myPos = 0;

    public ProductByCategoryAdapter(Context context, List<ProductData> productDataByCategoryList, PopularListFragment popularListFragment) {
        this.productDataByCategoryList = productDataByCategoryList;
        mpopularListFragment = popularListFragment;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_items_product_by_category, parent, false);


        return new MyViewHolder(itemView);


    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        ProductData productData = productDataByCategoryList.get(position);

        if (productData != null) {

            holder.txtProductName.setText(!TextUtils.isEmpty(productData.getmProductName()) ? productData.getmProductName() : "-");
            holder.txtProductSalePrice.setText(!TextUtils.isEmpty(productData.getmSalePrice()) ?context.getString(R.string.Rs)+ productData.getmSalePrice() : "-");

            String thumbnail = Constant.MEDIA_THUMBNAIL_BASE_URL + productData.getmProductImg();
            Glide.with(context).load(thumbnail).placeholder(R.drawable.richkart).into(holder.imgProduct);

            holder.rbProductRating.setRating(!TextUtils.isEmpty(productData.getmAvgRating()) ? Float.parseFloat(productData.getmAvgRating()) : 0);



            holder.lyProduct.setTag(productData);
            holder.lyProduct.setTag(R.id.lyProduct, position);
            holder.lyProduct.setOnClickListener(mpopularListFragment);

            holder.lyAddToCart.setTag(productData);
            holder.lyAddToCart.setTag(R.id.lyAddToCart, position);
            holder.lyAddToCart.setOnClickListener(mpopularListFragment);
        }



    }

    @Override
    public int getItemCount() {
        return productDataByCategoryList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {


        TextView txtProductSalePrice, offer, txtProductName;
        ImageView imgProduct;
        LinearLayout lyAddToCart;
        LinearLayout linear;
        private LinearLayout lyProduct;
        private RatingBar rbProductRating;


        public MyViewHolder(View view) {
            super(view);

            rbProductRating =  view.findViewById(R.id.rbProductRating);
            imgProduct =  view.findViewById(R.id.imgProduct);
            txtProductName =  view.findViewById(R.id.txtProductName);
            txtProductSalePrice =  view.findViewById(R.id.txtProductSalePrice);
            offer =  view.findViewById(R.id.offer);
            linear =  view.findViewById(R.id.linear);
            lyProduct =  view.findViewById(R.id.lyProduct);
            lyAddToCart =  view.findViewById(R.id.lyAddToCart);

        }

    }

}


