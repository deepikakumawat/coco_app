package com.nav.richkart.my_wishlist;

import android.content.Context;
import android.graphics.Paint;
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
import com.nav.richkart.R;
import com.nav.richkart.utility.Constant;

import java.util.ArrayList;


public class MyWishListAdapter extends RecyclerView.Adapter<MyWishListAdapter.ViewHolder> {
    private Context context;
    private ArrayList<MyWishListResponse.ProductData> myWishProductDataArrayList;
    private MyWishlistFragment myWishlistFragment;


    public MyWishListAdapter(Context context, ArrayList<MyWishListResponse.ProductData> myWishProductDataArrayList, MyWishlistFragment myWishlistFragment) {
        this.context = context;
        this.myWishProductDataArrayList = myWishProductDataArrayList;
        this.myWishlistFragment = myWishlistFragment;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_wishlist, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        MyWishListResponse.ProductData productData = myWishProductDataArrayList.get(position);
        if (productData != null) {
            holder.txtProductName.setText(TextUtils.isEmpty(productData.getmProductName()) ? "-" : productData.getmProductName());
            holder.txtSalesProductPrice.setText(TextUtils.isEmpty(productData.getmSalePrice()) ? "-" : context.getString(R.string.Rs) + productData.getmSalePrice());

            if (!TextUtils.isEmpty(productData.getmPrice())) {
                holder.txtProductPrice.setText(context.getString(R.string.Rs) + productData.getmPrice());
                holder.txtProductPrice.setPaintFlags(holder.txtProductPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            } else {
                holder.txtProductPrice.setText("-");
            }

            holder.txtIncDec.setText(TextUtils.isEmpty(productData.getmProductQty()) ? "-" : "Quantity: " + productData.getmProductQty());


            String thumbnail = Constant.MEDIA_THUMBNAIL_BASE_URL + productData.getmProductImg();
            Glide.with(context).load(thumbnail).placeholder(R.drawable.richkart).into(holder.imgProduct);

            holder.rbProductRating.setRating(!TextUtils.isEmpty(productData.getmAvgRating()) ? Float.parseFloat(productData.getmAvgRating()) : 0);


            holder.imgRemoveWishlist.setTag(productData);
            holder.imgRemoveWishlist.setTag(R.id.imgRemoveWishlist, position);
            holder.imgRemoveWishlist.setOnClickListener(myWishlistFragment);


            holder.lyProduct.setTag(productData);
            holder.lyProduct.setTag(R.id.lyProduct, position);
            holder.lyProduct.setOnClickListener(myWishlistFragment);

            setTxtDiscout(productData, holder);
        }

    }


    @Override
    public int getItemCount() {
        return myWishProductDataArrayList.size();
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {

        private TextView txtIncDec;

        private TextView txtProductPrice;
        private TextView txtProductName;
        private ImageView imgProduct;
        private ImageView imgRemoveWishlist;
        private LinearLayout lyProduct;
        private RatingBar rbProductRating;
        private TextView txtSalesProductPrice;
        private TextView txtDiscout;


        public ViewHolder(View view) {
            super(view);
            txtIncDec = view.findViewById(R.id.txtIncDec);
            txtProductPrice = view.findViewById(R.id.txtProductPrice);
            txtProductName = view.findViewById(R.id.txtProductName);
            imgRemoveWishlist = view.findViewById(R.id.imgRemoveWishlist);
            imgProduct = view.findViewById(R.id.imgProduct);
            lyProduct = view.findViewById(R.id.lyProduct);
            rbProductRating =  view.findViewById(R.id.rbProductRating);
            txtSalesProductPrice = view.findViewById(R.id.txtSalesProductPrice);
            txtDiscout = view.findViewById(R.id.txtDiscout);


        }
    }

    private void setTxtDiscout(MyWishListResponse.ProductData productData, ViewHolder holder) {

        try {

            if (!TextUtils.isEmpty(productData.getmPrice()) &&
                    !TextUtils.isEmpty(productData.getmSalePrice())) {

                double price = 0;
                double salesPrice = 0;

                price = Double.parseDouble(productData.getmPrice());
                salesPrice = Double.parseDouble(productData.getmSalePrice());

                double increases = price - salesPrice;
                double divide = increases / price;
                double dicount = divide * 100;

                int dis = (int) dicount;

                holder.txtDiscout.setText(dis + "% off");

            } else {
                holder.txtDiscout.setText("0%");

            }


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
