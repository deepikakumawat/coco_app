package Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import fragment.PopularFragment;
import fragment.PopularListFragment;


/**
 * Created by wolfsoft5 on 7/4/18.
 */

public class CategoryPagerAdapterProductList extends FragmentPagerAdapter {

        int mNoOfTabs;
        String catId;

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
                                PopularListFragment tab1 = PopularListFragment.newInstance(catId);
                                return tab1;
                        case 1:
                                PopularListFragment tab2 = PopularListFragment.newInstance(catId);
                                return tab2;
                        case 2:
                                PopularListFragment tab3 = PopularListFragment.newInstance(catId);
                                return tab3;

                        case 3:
                                PopularListFragment tab4 = PopularListFragment.newInstance(catId);
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

