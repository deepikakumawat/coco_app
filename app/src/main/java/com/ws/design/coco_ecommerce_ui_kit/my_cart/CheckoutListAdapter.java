package com.ws.design.coco_ecommerce_ui_kit.my_cart;

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
import com.ws.design.coco_ecommerce_ui_kit.utility.Constant;

import java.util.ArrayList;


public class CheckoutListAdapter extends RecyclerView.Adapter<CheckoutListAdapter.ViewHolder> implements View.OnClickListener {
    private Context context;
    private ArrayList<CartListResponse.ProductData> productDataArrayList;
    private CheckoutActivity checkoutActivity;


    public CheckoutListAdapter(Context context, ArrayList<CartListResponse.ProductData> productDataArrayList, CheckoutActivity checkoutActivity) {
        this.context = context;
        this.productDataArrayList = productDataArrayList;
        this.checkoutActivity = checkoutActivity;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_cart, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        CartListResponse.ProductData productData = productDataArrayList.get(position);
        if (productData != null) {
            holder.txtProductName.setText(TextUtils.isEmpty(productData.getmProductName()) ? "-" :productData.getmProductName());
            holder.txtProductPrice.setText(TextUtils.isEmpty(productData.getmSalePrice()) ? "-" :productData.getmSalePrice());

            String thumbnail = Constant.THUMBNAIL_BASE_URL + productData.getmProductImg();
            Glide.with(context).load(thumbnail).dontAnimate().into(holder.imgProduct);


            holder.txtCross.setTag(productData);
            holder.txtCross.setTag(R.id.txtCross,position);
            holder.txtCross.setOnClickListener(checkoutActivity);

            holder.txtOneByOne.setTag(productData);
            holder.txtOneByOne.setTag(R.id.txtOneByOne,position);
            holder.txtOneByOne.setOnClickListener(checkoutActivity);




            holder.lyIncrement.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int count= Integer.parseInt(String.valueOf(holder.txtIncDec.getText()));
                    count++;
                    holder.txtIncDec.setText(String.valueOf(count));
                }

            });

            holder.lyDecrement.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int count= Integer.parseInt(String.valueOf(holder.txtIncDec.getText()));
                    if (count > 1)
                        count--;
                    holder.txtIncDec.setText(String.valueOf(count));


                }
            });



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
        private LinearLayout lyDecrement;
        private LinearLayout lyIncrement;
        private TextView txtProductPrice;
        private TextView txtCross;
        private TextView txtOneByOne;
        private TextView txtIncDec;
        private ImageView imgProduct;





        public ViewHolder(View view) {
            super(view);

            txtIncDec = view.findViewById(R.id.txtIncDec);
            imgProduct = view.findViewById(R.id.imgProduct);
            txtProductName = view.findViewById(R.id.txtProductName);
            lyDecrement = view.findViewById(R.id.lyDecrement);
            lyIncrement = view.findViewById(R.id.lyIncrement);
            txtProductPrice = view.findViewById(R.id.txtProductPrice);
            txtOneByOne = view.findViewById(R.id.txtOneByOne);
            txtCross = view.findViewById(R.id.txtCross);


        }
    }
}
