package com.nav.richkart.product_by_category;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


public class CategoryPagerAdapterProductList extends FragmentPagerAdapter {

        int mNoOfTabs;
        String catId;
        String[] filterAttribues;

        public CategoryPagerAdapterProductList(FragmentManager fm, int NumberOfTabs, String catId)

        {
                super(fm);
                this.mNoOfTabs = NumberOfTabs;
                this.catId = catId;
        }

        @Override
        public Fragment getItem(int position) {
                switch (position) {

                        case 0:
                                PopularListFragment tab1 = PopularListFragment.newInstance(catId, 0);
                                return tab1;
                        case 1:
                                PopularListFragment tab2 = PopularListFragment.newInstance(catId, 1);
                                return tab2;
                        case 2:
                                PopularListFragment tab3 = PopularListFragment.newInstance(catId, 2);
                                return tab3;

                        case 3:
                                PopularListFragment tab4 = PopularListFragment.newInstance(catId, 3);
                                return tab4;


                        default:
                                return null;

                }
        }

        @Override
        public int getCount() {
                return mNoOfTabs;

        }
}

