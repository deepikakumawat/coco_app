package com.ws.design.coco_ecommerce_ui_kit.home;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.wolfsoft2.coco_ecommerce_ui_kit.R;
import com.ws.design.coco_ecommerce_ui_kit.home.home_response.DealProducts;
import com.ws.design.coco_ecommerce_ui_kit.utility.Constant;

import java.util.ArrayList;


public class HomeLikeProductsAdapter extends RecyclerView.Adapter<HomeLikeProductsAdapter.ViewHolder> {
    private Context context;
    private ArrayList<DealProducts> dealProductsArrayList;
    private HomeFragment homeFragment;


    public HomeLikeProductsAdapter(Context context, ArrayList<DealProducts> dealProductsArrayList, HomeFragment homeFragment) {
        this.context = context;
        this.dealProductsArrayList = dealProductsArrayList;
        this.homeFragment = homeFragment;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_like_products, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        DealProducts productData = dealProductsArrayList.get(position);
        if (productData != null) {

            holder.txtProductName.setText(productData.getmProductName());

            holder. txtProductPrice .setText(context.getString(R.string.rs1)+productData.getmSalePrice());



            String thumbnail = Constant.MEDIA_THUMBNAIL_BASE_URL + productData.getmProductImg();
            Glide.with(context).load(thumbnail).placeholder(R.drawable.richkart).into(holder.imgProduct);


            holder.lyLikeProduct.setTag(productData);
            holder.lyLikeProduct.setTag(R.id.lyLikeProduct,position);
            holder.lyLikeProduct.setOnClickListener(homeFragment);




        }

    }


    @Override
    public int getItemCount() {
        return dealProductsArrayList.size();
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgProduct;
        private TextView txtProductName;
        private TextView txtProductType;
        private TextView txtProductPrice;
        private LinearLayout lyLikeProduct;





        public ViewHolder(View view) {
            super(view);

            imgProduct = view.findViewById(R.id.imgProduct);
            txtProductName = view.findViewById(R.id.txtProductName);
            txtProductType = view.findViewById(R.id.txtProductType);
            txtProductPrice = view.findViewById(R.id.txtProductPrice);
            lyLikeProduct = view.findViewById(R.id.lyLikeProduct);



        }
    }
}
