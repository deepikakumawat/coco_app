package com.ws.design.coco_ecommerce_ui_kit.my_cart;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.content.res.AppCompatResources;
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
import java.util.Arrays;
import java.util.List;


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
            holder.txtProductPrice.setText(TextUtils.isEmpty(productData.getmSalePrice()) ? "-" : context.getString(R.string.Rs) + productData.getmSalePrice());

            String thumbnail = Constant.MEDIA_THUMBNAIL_BASE_URL + productData.getmProductImg();
            Glide.with(context).load(thumbnail).placeholder(R.drawable.richkart).into(holder.imgProduct);

            holder.txtIncDec.setText(TextUtils.isEmpty(productData.getmQuantity()) ? "-" : productData.getmQuantity());


            holder.imgCross.setTag(productData);
            holder.imgCross.setTag(R.id.imgCross, position);
            holder.imgCross.setOnClickListener(cartFragment);


            holder.lyIncrement.setTag(productData);
            holder.lyIncrement.setTag(R.id.lyIncrement, holder.txtIncDec);
            holder.lyIncrement.setOnClickListener(cartFragment);


            holder.lyDecrement.setTag(productData);
            holder.lyDecrement.setTag(R.id.lyDecrement, holder.txtIncDec);
            holder.lyDecrement.setOnClickListener(cartFragment);

            holder.txtProductName.setTag(productData);
            holder.txtProductName.setTag(R.id.txtProductName, position);
            holder.txtProductName.setOnClickListener(cartFragment);



            setAttribute(productData.getmAttributes(), holder.lyColorView, holder.lyColorTop);


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
        private ImageView imgCross;
        private TextView txtIncDec;
        private ImageView imgProduct;
        private LinearLayout lyCartProduct;
        private LinearLayout lyColorTop;
        private LinearLayout lyColorView;


        public ViewHolder(View view) {
            super(view);

            txtIncDec = view.findViewById(R.id.txtIncDec);
            imgProduct = view.findViewById(R.id.imgProduct);
            txtProductName = view.findViewById(R.id.txtProductName);
            lyDecrement = view.findViewById(R.id.lyDecrement);
            lyIncrement = view.findViewById(R.id.lyIncrement);
            txtProductPrice = view.findViewById(R.id.txtProductPrice);

            imgCross = view.findViewById(R.id.imgCross);
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
                    LinearLayout lyColorCode = view.findViewById(R.id.lyColorCode);

                    txtAttributeType.setText(attributesDataArrayList.get(i).getmAttributeType());


                    if (attributesDataArrayList.get(i).getmAttributeType().equalsIgnoreCase("color")) {

                        txtAttributeName.setVisibility(View.GONE);
                        lyColorCode.setVisibility(View.VISIBLE);

                        Drawable unwrappedDrawable = AppCompatResources.getDrawable(context, R.drawable.black_circle);
                        Drawable wrappedDrawable = DrawableCompat.wrap(unwrappedDrawable);
                        DrawableCompat.setTint(wrappedDrawable, Color.parseColor("#" + attributesDataArrayList.get(i).getmAttributeRelatedData()));

                        final int sdk = android.os.Build.VERSION.SDK_INT;
                        if (sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                            lyColorCode.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.black_circle));
                        } else {
                            lyColorCode.setBackground(ContextCompat.getDrawable(context, R.drawable.black_circle));
                        }

                    } else {
                        lyColorCode.setVisibility(View.GONE);
                        txtAttributeName.setVisibility(View.VISIBLE);

                        txtAttributeName.setText(attributesDataArrayList.get(i).getmAttributeName());

                    }

                    lyColorView.addView(view);

                }
            }else{
                lyColorTop.setVisibility(View.GONE);
            }



        } catch (Exception e) {
            e.printStackTrace();
            lyColorTop.setVisibility(View.GONE);

        }

    }



}
