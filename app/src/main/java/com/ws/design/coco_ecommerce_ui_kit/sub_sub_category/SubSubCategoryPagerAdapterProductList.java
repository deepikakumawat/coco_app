package com.ws.design.coco_ecommerce_ui_kit.sub_sub_category;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


import java.util.ArrayList;


public class SubSubCategoryPagerAdapterProductList extends FragmentPagerAdapter {

    int mNoOfTabs;
    private ArrayList<SubSubCategoriesResponse.MainSubCategoriesData> mainSubCategoriesDataArrayList;

    public SubSubCategoryPagerAdapterProductList(FragmentManager fm, int NumberOfTabs, ArrayList<SubSubCategoriesResponse.MainSubCategoriesData> mainSubCategoriesDataArrayList) {
        super(fm);
        this.mNoOfTabs = NumberOfTabs;
        this.mainSubCategoriesDataArrayList = mainSubCategoriesDataArrayList;
    }

    @Override
    public Fragment getItem(int position) {
        return SubSubCategoryProductListFragment.newInstance(mainSubCategoriesDataArrayList.get(position).getmCatId(),position);

    }

    @Override
    public int getCount() {
        return mNoOfTabs;

    }
}

