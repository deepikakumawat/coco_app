package com.richkart.android.sub_sub_category;

import android.os.Bundle;
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
        SubSubCategoryProductListFragment subSubCategoryProductListFragment = new SubSubCategoryProductListFragment();
        Bundle bundle = new Bundle();
        bundle.putString("catId", mainSubCategoriesDataArrayList.get(position).getmCatId());
        bundle.putInt("tabPostion", position);
        subSubCategoryProductListFragment.setArguments(bundle);
        return subSubCategoryProductListFragment;

    }

    @Override
    public int getCount() {
        return mNoOfTabs;

    }
}

