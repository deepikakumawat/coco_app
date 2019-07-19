package com.ws.design.coco_ecommerce_ui_kit.product_details;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.wolfsoft2.coco_ecommerce_ui_kit.R;
import com.ws.design.coco_ecommerce_ui_kit.product_details.project_details_response.ProductDetailsResponse;
import com.ws.design.coco_ecommerce_ui_kit.product_details.project_details_response.ProductDetailsSimilier;
import com.ws.design.coco_ecommerce_ui_kit.utility.Constant;

import java.util.ArrayList;

public class ProductDetailsViewPager extends PagerAdapter {
    private LayoutInflater layoutInflater;
    private Context context;

    private ArrayList<String> productDetailsImagesArrayList;
    
    
    public ProductDetailsViewPager(Context context,ArrayList<String> productDetailsImagesArrayList) {

        this.context = context;
        this.productDetailsImagesArrayList = productDetailsImagesArrayList;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.viewpager_product_details, container, false);
        ImageView imgProduct = view.findViewById(R.id.imgProduct);

        String images = productDetailsImagesArrayList.get(position);

        Glide.with(context).load(images).placeholder(R.drawable.ac).into(imgProduct);

        container.addView(view);

        return view;
    }

    @Override
    public int getCount() {
        return productDetailsImagesArrayList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        View view = (View) object;
        container.removeView(view);
    }
}