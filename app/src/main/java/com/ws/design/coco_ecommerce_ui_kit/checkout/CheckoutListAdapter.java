package com.ws.design.coco_ecommerce_ui_kit.checkout;

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
import com.ws.design.coco_ecommerce_ui_kit.my_cart.CartListResponse;
import com.ws.design.coco_ecommerce_ui_kit.utility.Constant;

import java.util.ArrayList;


public class CheckoutListAdapter extends RecyclerView.Adapter<CheckoutListAdapter.ViewHolder> implements View.OnClickListener {
    private Context context;
    private ArrayList<CartListResponse.ProductData> productDataArrayList;
    private CheckoutFragment checkoutFragment;


    public CheckoutListAdapter(Context context, ArrayList<CartListResponse.ProductData> productDataArrayList, CheckoutFragment checkoutFragment) {
        this.context = context;
        this.productDataArrayList = productDataArrayList;
        this.checkoutFragment = checkoutFragment;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_checkout, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        CartListResponse.ProductData productData = productDataArrayList.get(position);
        if (productData != null) {
            holder.txtProductName.setText(TextUtils.isEmpty(productData.getmProductName()) ? "-" :productData.getmProductName());
            holder.txtProductPrice.setText(TextUtils.isEmpty(productData.getmSalePrice()) ? "-" :productData.getmSalePrice());

            String thumbnail = Constant.MEDIA_THUMBNAIL_BASE_URL + productData.getmProductImg();
            Glide.with(context).load(thumbnail).placeholder(R.drawable.richkart).into(holder.imgProduct);


            holder.txtIncDec.setText(TextUtils.isEmpty(productData.getmQuantity()) ? "-" :"Quantity: "+ productData.getmQuantity());




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

        private TextView txtProductPrice;
        private TextView txtIncDec;
        private ImageView imgProduct;





        public ViewHolder(View view) {
            super(view);

            txtIncDec = view.findViewById(R.id.txtIncDec);
            imgProduct = view.findViewById(R.id.imgProduct);
            txtProductName = view.findViewById(R.id.txtProductName);

            txtProductPrice = view.findViewById(R.id.txtProductPrice);


        }
    }
}
