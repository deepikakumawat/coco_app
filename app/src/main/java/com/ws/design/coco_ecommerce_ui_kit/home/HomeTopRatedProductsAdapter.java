package com.ws.design.coco_ecommerce_ui_kit.home;

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
import com.ws.design.coco_ecommerce_ui_kit.home.home_response.ProductData;
import com.ws.design.coco_ecommerce_ui_kit.utility.Constant;

import java.util.ArrayList;


public class HomeTopRatedProductsAdapter extends RecyclerView.Adapter<HomeTopRatedProductsAdapter.ViewHolder> {
    private Context context;
    private ArrayList<ProductData> productDataArrayList;
    private HomeFragment homeFragment;


    public HomeTopRatedProductsAdapter(Context context, ArrayList<ProductData> productDataArrayList, HomeFragment homeFragment) {
        this.context = context;
        this.productDataArrayList = productDataArrayList;
        this.homeFragment = homeFragment;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_home_top_rated_products, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        ProductData productData = productDataArrayList.get(position);
        if (productData != null) {

            holder.txtProductName.setText(productData.getmProductName());
            holder. txtProductPrice .setText(context.getString(R.string.rs1)+productData.getmPrice());

            holder. txtRating .setText(!TextUtils.isEmpty(productData.getmAvgRating()) ? productData.getmAvgRating() : "0");


            String thumbnail = Constant.MEDIA_THUMBNAIL_BASE_URL + productData.getmProImgUrl();
            Glide.with(context).load(thumbnail).placeholder(R.drawable.richkart).dontAnimate().into(holder.imgProduct);

            holder.lyProduct.setTag(productData);
            holder.lyProduct.setTag(R.id.lyProduct,position);
            holder.lyProduct.setOnClickListener(homeFragment);

        }

    }


    @Override
    public int getItemCount() {
        return productDataArrayList.size();
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

            imgProduct = view.findViewById(R.id.imgProduct);
            txtProductName = view.findViewById(R.id.txtProductName);
            txtProductType = view.findViewById(R.id.txtProductType);
            txtProductPrice = view.findViewById(R.id.txtProductPrice);
            txtRating = view.findViewById(R.id.txtRating);
            lyProduct = view.findViewById(R.id.lyProduct);



        }
    }
}
