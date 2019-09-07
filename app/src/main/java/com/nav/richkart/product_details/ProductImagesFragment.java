package com.nav.richkart.product_details;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nav.richkart.R;
import com.nav.richkart.base_fragment.BaseFragment;

import java.util.ArrayList;


public class ProductImagesFragment extends BaseFragment implements View.OnClickListener{


    private ViewPager viewPager;
    private ProductImagesViewPager productImagesViewPager;


    private ArrayList<String> productDetailsImagesArrayList = new ArrayList<>();


    private View mView;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mView = inflater.inflate(R.layout.activity_product_images, container, false);


        Bundle bundle = getArguments();
        productDetailsImagesArrayList = (ArrayList<String>) bundle.getSerializable("productImages");


        return mView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {


        init(view);


    }

    private void init(View view) {

        viewPager = view.findViewById(R.id.viewpager_product_detail);


        productImagesViewPager = new ProductImagesViewPager(getActivity(), productDetailsImagesArrayList, ProductImagesFragment.this);
        viewPager.setAdapter(productImagesViewPager);

    }


    @Override
    public void onClick(View v) {
        try {
            int vId = v.getId();
            switch (v.getId()) {

                default:
                    break;

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    protected String getActionbarTitle() {
        return "Product Images";
    }

    @Override
    protected boolean isSearchIconVisible() {
        return false;
    }

    @Override
    protected boolean isCartIconVisible() {
        return false;
    }


}