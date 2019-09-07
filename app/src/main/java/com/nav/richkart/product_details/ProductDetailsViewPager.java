package com.nav.richkart.product_details;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.nav.richkart.R;

import java.util.ArrayList;

public class ProductDetailsViewPager extends PagerAdapter {
    private LayoutInflater layoutInflater;
    private Context context;

    private ArrayList<String> productDetailsImagesArrayList;
    private ProductDetailFragment productDetailFragment;
    
    
    public ProductDetailsViewPager(Context context, ArrayList<String> productDetailsImagesArrayList, ProductDetailFragment productDetailFragment) {

        this.context = context;
        this.productDetailsImagesArrayList = productDetailsImagesArrayList;
        this.productDetailFragment = productDetailFragment;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.viewpager_product_details, container, false);
        ImageView imgProduct = view.findViewById(R.id.imgProduct);

        String images = productDetailsImagesArrayList.get(position);

        Glide.with(context).load(images).placeholder(R.drawable.richkart).into(imgProduct);

        imgProduct.setOnClickListener(productDetailFragment);


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