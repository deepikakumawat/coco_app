package com.richkart.android.my_cart;

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
import com.richkart.android.R;
import com.richkart.android.utility.Constant;

import java.util.ArrayList;


public class CartListAdapter extends RecyclerView.Adapter<CartListAdapter.ViewHolder> implements View.OnClickListener {
    private Context context;
    private ArrayList<CartListResponse.ProductData> productDataArrayList;
    private CartFragment cartFragment;


    public CartListAdapter(Context context, ArrayList<CartListResponse.ProductData> productDataArrayList, CartFragment cartFragment) {
        this.context = context;
        this.productDataArrayList = productDataArrayList;
        this.cartFragment = cartFragment;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_cart, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        CartListResponse.ProductData productData = productDataArrayList.get(position);
        if (productData != null) {
            holder.txtProductName.setText(TextUtils.isEmpty(productData.getmProductName()) ? "-" : productData.getmProductName());
            holder.txtSalesProductPrice.setText(TextUtils.isEmpty(productData.getmSalePrice()) ? "-" : context.getString(R.string.Rs) + productData.getmSalePrice());


            if (!TextUtils.isEmpty(productData.getmPrice())) {
                holder.txtProductPrice.setText(context.getString(R.string.Rs) + productData.getmPrice());
                holder.txtProductPrice.setPaintFlags(holder.txtProductPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            } else {
                holder.txtProductPrice.setText("-");
            }


            String thumbnail = Constant.MEDIA_THUMBNAIL_BASE_URL + productData.getmProductImg();
            Glide.with(context).load(thumbnail).placeholder(R.drawable.richkart).into(holder.imgProduct);

            holder.txtIncDec.setText(TextUtils.isEmpty(productData.getmQuantity()) ? "-" : productData.getmQuantity());


            holder.lyRemove.setTag(productData);
            holder.lyRemove.setTag(R.id.lyRemove, position);
            holder.lyRemove.setOnClickListener(cartFragment);


            holder.lyIncrement.setTag(productData);
            holder.lyIncrement.setTag(R.id.lyIncrement, holder.txtIncDec);
            holder.lyIncrement.setOnClickListener(cartFragment);


            holder.lyDecrement.setTag(productData);
            holder.lyDecrement.setTag(R.id.lyDecrement, holder.txtIncDec);
            holder.lyDecrement.setOnClickListener(cartFragment);

            holder.txtProductName.setTag(productData);
            holder.txtProductName.setTag(R.id.txtProductName, position);
            holder.txtProductName.setOnClickListener(cartFragment);

            holder.lyMovewToWishlist.setTag(productData);
            holder.lyMovewToWishlist.setTag(R.id.lyMovewToWishlist, position);
            holder.lyMovewToWishlist.setOnClickListener(cartFragment);


            setAttribute(productData.getmAttributes(), holder.lyColorView, holder.lyColorTop);
            setTxtDiscout(productData, holder);


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
        private TextView txtSalesProductPrice;
        private TextView txtProductPrice;
        private TextView txtDiscout;
        private LinearLayout lyRemove;
        private TextView txtIncDec;
        private ImageView imgProduct;
        private LinearLayout lyCartProduct;
        private LinearLayout lyColorTop;
        private LinearLayout lyColorView;
        private LinearLayout lyMovewToWishlist;


        public ViewHolder(View view) {
            super(view);

            txtIncDec = view.findViewById(R.id.txtIncDec);
            imgProduct = view.findViewById(R.id.imgProduct);
            txtProductName = view.findViewById(R.id.txtProductName);
            lyDecrement = view.findViewById(R.id.lyDecrement);
            lyIncrement = view.findViewById(R.id.lyIncrement);
            txtSalesProductPrice = view.findViewById(R.id.txtSalesProductPrice);
            txtProductPrice = view.findViewById(R.id.txtProductPrice);
            txtDiscout = view.findViewById(R.id.txtDiscout);

            lyRemove = view.findViewById(R.id.lyRemove);
            lyMovewToWishlist = view.findViewById(R.id.lyMovewToWishlist);
            lyColorView = view.findViewById(R.id.lyColorView);
            lyCartProduct = view.findViewById(R.id.lyCartProduct);
            lyColorTop = view.findViewById(R.id.lyColorTop);


        }
    }


    private void setAttribute(ArrayList<CartListResponse.AttributesData> attributesDataArrayList, LinearLayout lyColorView, LinearLayout lyColorTop) {

        try {

            if (!attributesDataArrayList.isEmpty()) {

                lyColorTop.setVisibility(View.VISIBLE);
                lyColorView.removeAllViews();


                LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);


                View view;
                for (int i = 0; i < attributesDataArrayList.size(); i++) {
                    view = layoutInflater.inflate(R.layout.attribtue_cart_layout, lyColorView, false);

                    TextView txtAttributeType = view.findViewById(R.id.txtAttributeType);
                    TextView txtAttributeName = view.findViewById(R.id.txtAttributeName);

                    txtAttributeType.setText(attributesDataArrayList.get(i).getmAttributeType());


                    txtAttributeName.setVisibility(View.VISIBLE);

                    txtAttributeName.setText(attributesDataArrayList.get(i).getmAttributeName());


                    lyColorView.addView(view);

                }
            } else {
                lyColorTop.setVisibility(View.GONE);
            }


        } catch (Exception e) {
            e.printStackTrace();
            lyColorTop.setVisibility(View.GONE);

        }

    }


    private void setTxtDiscout(CartListResponse.ProductData productData, ViewHolder holder) {

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
