package com.nav.richkart.product_details;

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
import com.nav.richkart.R;
import com.nav.richkart.product_details.project_details_response.ProductBroughtData;
import com.nav.richkart.utility.Constant;


import java.util.ArrayList;


public class ProductDetailsBroughtDataAdapter extends RecyclerView.Adapter<ProductDetailsBroughtDataAdapter.ViewHolder> {
    private Context context;
    private ArrayList<ProductBroughtData> productBroughtDataArrayList;
    private ProductDetailFragment productDetailFragment;


    public ProductDetailsBroughtDataAdapter(Context context, ArrayList<ProductBroughtData> productBroughtDataArrayList, ProductDetailFragment productDetailFragment) {
        this.context = context;
        this.productBroughtDataArrayList = productBroughtDataArrayList;
        this.productDetailFragment = productDetailFragment;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cocoproductlistgrid7, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        ProductBroughtData productBroughtData = productBroughtDataArrayList.get(position);
        if (productBroughtData != null) {

            holder.txtProductName.setText(productBroughtData.getmProductName());

            holder. txtSalesProductPrice .setText(context.getString(R.string.rs1)+productBroughtData.getmSalePrice());
            holder. txtProductPrice .setText(context.getString(R.string.rs1)+productBroughtData.getmPrice());



            String thumbnail = Constant.MEDIA_THUMBNAIL_BASE_URL + productBroughtData.getmProductImg();
            Glide.with(context).load(thumbnail).placeholder(R.drawable.richkart).into(holder.imgProduct);



            holder.imgAddToCart.setTag(productBroughtData);
            holder.imgAddToCart.setTag(R.id.imgAddToCart,position);
            holder.imgAddToCart.setOnClickListener(productDetailFragment);

            holder.lyBroughtProduct.setTag(productBroughtData);
            holder.lyBroughtProduct.setTag(R.id.lyBroughtProduct,position);
            holder.lyBroughtProduct.setOnClickListener(productDetailFragment);



        }

    }


    @Override
    public int getItemCount() {
        return productBroughtDataArrayList.size();
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgProduct;
        private TextView txtProductName;
        private TextView txtProductType;
        private TextView txtProductPrice;
        private TextView txtSalesProductPrice;
        private ImageView imgAddToCart;
        private LinearLayout lyBroughtProduct;





        public ViewHolder(View view) {
            super(view);

            txtSalesProductPrice = view.findViewById(R.id.txtSalesProductPrice);
            imgProduct = view.findViewById(R.id.imgProduct);
            txtProductName = view.findViewById(R.id.txtProductName);
            txtProductType = view.findViewById(R.id.txtProductType);
            txtProductPrice = view.findViewById(R.id.txtProductPrice);
            imgAddToCart = view.findViewById(R.id.imgAddToCart);
            lyBroughtProduct = view.findViewById(R.id.lyBroughtProduct);



        }
    }
}