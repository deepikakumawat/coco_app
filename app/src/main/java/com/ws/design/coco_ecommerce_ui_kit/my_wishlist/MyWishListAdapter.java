package com.ws.design.coco_ecommerce_ui_kit.my_wishlist;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.wolfsoft2.coco_ecommerce_ui_kit.R;
import com.ws.design.coco_ecommerce_ui_kit.utility.Constant;

import java.util.ArrayList;


public class MyWishListAdapter extends RecyclerView.Adapter<MyWishListAdapter.ViewHolder> {
    private Context context;
    private ArrayList<MyWishListResponse.ProductData> myWishProductDataArrayList;
    private MyWishlistActivity myWishlistActivity;


    public MyWishListAdapter(Context context, ArrayList<MyWishListResponse.ProductData> myWishProductDataArrayList, MyWishlistActivity myWishlistActivity) {
        this.context = context;
        this.myWishProductDataArrayList = myWishProductDataArrayList;
        this.myWishlistActivity = myWishlistActivity;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_my_wishlist, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        MyWishListResponse.ProductData productData = myWishProductDataArrayList.get(position);
        if (productData != null) {
            holder.txtProductName.setText(TextUtils.isEmpty(productData.getmProductName()) ? "-" :productData.getmProductName());
            holder.txtProductPrice.setText(TextUtils.isEmpty(productData.getmSalePrice()) ? "-" : productData.getmSalePrice());


            String thumbnail = Constant.THUMBNAIL_BASE_URL + productData.getmProductImg();
            Glide.with(context).load(thumbnail).placeholder(R.drawable.ac).dontAnimate().into(holder.imgProduct);




            holder.txtProductName.setTag(productData);
            holder.txtProductName.setTag(R.id.txtProductName,position);

            holder.txtProductName.setOnClickListener( myWishlistActivity);
        }

    }


    @Override
    public int getItemCount() {
        return myWishProductDataArrayList.size();
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {

        private TextView txtProductPrice;
        private TextView txtProductName;
        private ImageView imgProduct;




        public ViewHolder(View view) {
            super(view);

            txtProductPrice = view.findViewById(R.id.txtProductPrice);
            txtProductName = view.findViewById(R.id.txtProductName);
            imgProduct = view.findViewById(R.id.imgProduct);


        }
    }
}
