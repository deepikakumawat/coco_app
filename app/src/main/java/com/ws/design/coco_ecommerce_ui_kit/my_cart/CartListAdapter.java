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


public class CartListAdapter extends RecyclerView.Adapter<CartListAdapter.ViewHolder> implements View.OnClickListener {
    private Context context;
    private ArrayList<CartListResponse.ProductData> productDataArrayList;
    private CartActivity cartActivity;


    public CartListAdapter(Context context, ArrayList<CartListResponse.ProductData> productDataArrayList, CartActivity cartActivity) {
        this.context = context;
        this.productDataArrayList = productDataArrayList;
        this.cartActivity = cartActivity;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_cart1, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        CartListResponse.ProductData productData = productDataArrayList.get(position);
        if (productData != null) {
            holder.txtProductName.setText(TextUtils.isEmpty(productData.getmProductName()) ? "-" : productData.getmProductName());
            holder.txtProductPrice.setText(TextUtils.isEmpty(productData.getmSalePrice()) ? "-" : productData.getmSalePrice());

            String thumbnail = Constant.MEDIA_THUMBNAIL_BASE_URL + productData.getmProductImg();
            Glide.with(context).load(thumbnail).dontAnimate().into(holder.imgProduct);

            holder.txtIncDec.setText(TextUtils.isEmpty(productData.getmQuantity()) ? "-" : productData.getmQuantity());


            holder.txtCross.setTag(productData);
            holder.txtCross.setTag(R.id.txtCross, position);
            holder.txtCross.setOnClickListener(cartActivity);


            holder.lyIncrement.setTag(productData);
            holder.lyIncrement.setTag(R.id.lyIncrement, holder.txtIncDec);
            holder.lyIncrement.setOnClickListener(cartActivity);


            holder.lyDecrement.setTag(productData);
            holder.lyDecrement.setTag(R.id.lyDecrement, holder.txtIncDec);
            holder.lyDecrement.setOnClickListener(cartActivity);

            holder.lyCartProduct.setTag(productData);
            holder.lyCartProduct.setTag(R.id.lyCartProduct, position);
            holder.lyCartProduct.setOnClickListener(cartActivity);

        /*    holder.lyIncrement.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int count= Integer.parseInt(String.valueOf(holder.txtIncDec.getText()));
                    count++;
                    holder.txtIncDec.setText(String.valueOf(count));
                }

            });*/

         /*   holder.lyDecrement.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int count= Integer.parseInt(String.valueOf(holder.txtIncDec.getText()));
                    if (count > 1)
                        count--;
                    holder.txtIncDec.setText(String.valueOf(count));


                }
            });
*/


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
        private TextView txtIncDec;
        private ImageView imgProduct;
        private LinearLayout lyCartProduct;


        public ViewHolder(View view) {
            super(view);

            txtIncDec = view.findViewById(R.id.txtIncDec);
            imgProduct = view.findViewById(R.id.imgProduct);
            txtProductName = view.findViewById(R.id.txtProductName);
            lyDecrement = view.findViewById(R.id.lyDecrement);
            lyIncrement = view.findViewById(R.id.lyIncrement);
            txtProductPrice = view.findViewById(R.id.txtProductPrice);

            txtCross = view.findViewById(R.id.txtCross);
            lyCartProduct = view.findViewById(R.id.lyCartProduct);


        }
    }
}
