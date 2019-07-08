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
import com.ws.design.coco_ecommerce_ui_kit.home.home_response.ProductData;
import com.ws.design.coco_ecommerce_ui_kit.utility.Constant;

import java.util.ArrayList;


public class HomeTopRatedProductsAdapter extends RecyclerView.Adapter<HomeTopRatedProductsAdapter.ViewHolder> {
    private Context context;
    private ArrayList<ProductData> productDataArrayList;
    private HomeActivity homeActivity;


    public HomeTopRatedProductsAdapter(Context context, ArrayList<ProductData> productDataArrayList, HomeActivity homeActivity) {
        this.context = context;
        this.productDataArrayList = productDataArrayList;
        this.homeActivity = homeActivity;

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

            holder. txtProductPrice .setText(productData.getmPrice());



            String thumbnail = Constant.THUMBNAIL_BASE_URL + productData.getmProImgUrl();
            Glide.with(context).load(thumbnail).placeholder(R.drawable.ac).dontAnimate().into(holder.imgProduct);


            holder.lyProduct.setTag(productData);
            holder.lyProduct.setTag(R.id.lyProduct,position);
            holder.lyProduct.setOnClickListener( homeActivity);




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
        private LinearLayout lyProduct;





        public ViewHolder(View view) {
            super(view);

            imgProduct = view.findViewById(R.id.imgProduct);
            txtProductName = view.findViewById(R.id.txtProductName);
            txtProductType = view.findViewById(R.id.txtProductType);
            txtProductPrice = view.findViewById(R.id.txtProductPrice);
            lyProduct = view.findViewById(R.id.lyProduct);



        }
    }
}
