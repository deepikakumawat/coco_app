package com.ws.design.coco_ecommerce_ui_kit.search;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.wolfsoft2.coco_ecommerce_ui_kit.R;
import com.ws.design.coco_ecommerce_ui_kit.product_details.project_details_response.ProductDetailsSimilier;
import com.ws.design.coco_ecommerce_ui_kit.seller.SellerProductFragment;
import com.ws.design.coco_ecommerce_ui_kit.utility.Constant;

import java.util.ArrayList;


public class SearchProductAdapter extends RecyclerView.Adapter<SearchProductAdapter.ViewHolder> implements View.OnClickListener {
    private Context context;
    private ArrayList<ProductDetailsSimilier> productDataArrayList;
    private SearchListFragment searchListFragment;




    public SearchProductAdapter(Context context, ArrayList<ProductDetailsSimilier> productDataArrayList, SearchListFragment searchListFragment) {
        this.context = context;
        this.productDataArrayList = productDataArrayList;
        this.searchListFragment = searchListFragment;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_seller_product, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        ProductDetailsSimilier productData = productDataArrayList.get(position);
        if (productData != null) {
            holder.txtProductName.setText(TextUtils.isEmpty(productData.getmProductName()) ? "-" : productData.getmProductName());
            holder.txtProductSalePrice.setText(TextUtils.isEmpty(productData.getmSalePrice()) ? "-" : context.getString(R.string.Rs) + productData.getmSalePrice());

            holder.txtProductDesc.setText(!TextUtils.isEmpty(productData.getmProductShortDetails()) ? Html.fromHtml(productData.getmProductShortDetails()) : "-");


            holder.rbProductRating.setRating(!TextUtils.isEmpty(productData.getmAvgRating()) ? Float.parseFloat(productData.getmAvgRating()) : 0);

            String thumbnail = Constant.MEDIA_THUMBNAIL_BASE_URL + productData.getmProductImg();
            Glide.with(context).load(thumbnail).placeholder(R.drawable.richkart).into(holder.imgProduct);


            holder.txtProductName.setTag(productData);
            holder.txtProductName.setOnClickListener(searchListFragment);




        /*    holder.imgAddToCart.setTag(productData);
            holder.imgAddToCart.setTag(R.id.imgAddToCart, position);
            holder.imgAddToCart.setOnClickListener(searchListFragment);*/


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

        private TextView txtProductName;

        private TextView txtProductSalePrice;
        private TextView txtProductDesc;
        private ImageView imgProduct;
        private ImageView imgAddToCart;
        private RatingBar rbProductRating;


        public ViewHolder(View view) {
            super(view);

            imgAddToCart = view.findViewById(R.id.imgAddToCart);
            imgProduct = view.findViewById(R.id.imgProduct);
            txtProductName = view.findViewById(R.id.txtProductName);
            rbProductRating = view.findViewById(R.id.rbProductRating);
            txtProductDesc = view.findViewById(R.id.txtProductDesc);

            txtProductSalePrice = view.findViewById(R.id.txtProductSalePrice);


        }
    }
}
