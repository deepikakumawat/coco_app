package com.nav.richkart.product_details;

import android.content.Context;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.nav.richkart.R;
import com.nav.richkart.product_details.project_details_response.ProductDetailsSimilier;
import com.nav.richkart.utility.Constant;

import java.util.ArrayList;


public class ProductDetailsRecentViewsProductsAdapter extends RecyclerView.Adapter<ProductDetailsRecentViewsProductsAdapter.ViewHolder> {
    private Context context;
    private ArrayList<ProductDetailsSimilier> productDetailsRecentViewsArrayList;
    private ProductDetailFragment productDetailFragment;


    public ProductDetailsRecentViewsProductsAdapter(Context context, ArrayList<ProductDetailsSimilier> productDetailsRecentViewsArrayList, ProductDetailFragment productDetailFragment) {
        this.context = context;
        this.productDetailsRecentViewsArrayList = productDetailsRecentViewsArrayList;
        this.productDetailFragment = productDetailFragment;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_home_top_rated_products, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        ProductDetailsSimilier productData = productDetailsRecentViewsArrayList.get(position);
        if (productData != null) {

            holder.txtProductName.setText(productData.getmProductName());

            holder. txtRating .setText(!TextUtils.isEmpty(productData.getmAvgRating()) ? productData.getmAvgRating() : "0");

            holder.txtSalesProductPrice.setText(TextUtils.isEmpty(productData.getmSalePrice()) ? "-" : context.getString(R.string.Rs) + productData.getmSalePrice());


            if (!TextUtils.isEmpty(productData.getmPrice())) {
                holder.txtProductPrice.setText(context.getString(R.string.Rs) + productData.getmPrice());
                holder.txtProductPrice.setPaintFlags(holder.txtProductPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            } else {
                holder.txtProductPrice.setText("-");
            }


            String thumbnail = Constant.MEDIA_THUMBNAIL_BASE_URL + productData.getmProductImg();
            Glide.with(context).load(thumbnail).placeholder(R.drawable.richkart).into(holder.imgProduct);


            holder.lyProduct.setTag(productData);
            holder.lyProduct.setTag(R.id.lyProduct,position);
            holder.lyProduct.setOnClickListener(productDetailFragment);

            setTxtDiscout(productData,holder);

        }

    }


    @Override
    public int getItemCount() {
        return productDetailsRecentViewsArrayList.size();
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgProduct;
        private TextView txtProductName;
        private TextView txtProductType;
        private TextView txtProductPrice;
        private TextView txtRating;
        private LinearLayout lyProduct;

        private TextView txtSalesProductPrice;
        private TextView txtDiscout;



        public ViewHolder(View view) {
            super(view);

            txtRating = view.findViewById(R.id.txtRating);
            imgProduct = view.findViewById(R.id.imgProduct);
            txtProductName = view.findViewById(R.id.txtProductName);
            txtProductType = view.findViewById(R.id.txtProductType);
            lyProduct = view.findViewById(R.id.lyProduct);
            txtSalesProductPrice = view.findViewById(R.id.txtSalesProductPrice);
            txtDiscout = view.findViewById(R.id.txtDiscout);

            txtProductPrice = view.findViewById(R.id.txtProductPrice);



        }
    }

    private void setTxtDiscout(ProductDetailsSimilier productData, ViewHolder holder) {

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
