package com.ws.design.coco_ecommerce_ui_kit.product_details;

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

import com.example.wolfsoft2.coco_ecommerce_ui_kit.R;
import com.ws.design.coco_ecommerce_ui_kit.product_details.project_details_response.ProductAttributeData;

import java.util.ArrayList;
import java.util.List;


public class ProductDetailsColorAdapter extends RecyclerView.Adapter<ProductDetailsColorAdapter.ViewHolder> {
    private Context context;
    private List<String> productColorDataArrayList;
    private ProductDetailFragment productDetailFragment;


    public ProductDetailsColorAdapter(Context context, List<String> productColorDataArrayList, ProductDetailFragment productDetailFragment) {
        this.context = context;
        this.productColorDataArrayList = productColorDataArrayList;
        this.productDetailFragment = productDetailFragment;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.color_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        String productData = productColorDataArrayList.get(position);
        if (productData != null) {

          

            Drawable unwrappedDrawable = AppCompatResources.getDrawable(context, R.drawable.black_circle);
            Drawable wrappedDrawable = DrawableCompat.wrap(unwrappedDrawable);
            DrawableCompat.setTint(wrappedDrawable, Color.parseColor("#"+productData));
//            DrawableCompat.setTint(wrappedDrawable, Color.RED);

            final int sdk = android.os.Build.VERSION.SDK_INT;
            if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                holder.lyColor.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.black_circle) );
            } else {
                holder.lyColor.setBackground(ContextCompat.getDrawable(context, R.drawable.black_circle));
            }


        
        }

    }


    @Override
    public int getItemCount() {
        return productColorDataArrayList.size();
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imgSelectColor ;
        LinearLayout lyColor ;



        public ViewHolder(View view) {
            super(view);

            imgSelectColor = view.findViewById(R.id.imgSelectColor);
            lyColor = view.findViewById(R.id.lyColor);




        }
    }
}
