package Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import fragment.CocoProductDetails4Fragment;


/**
 * Created by wolfsoft3 on 24/7/18.
 */

public class Details4TabAdapter extends FragmentPagerAdapter {
    int numoftabs;

    public Details4TabAdapter(FragmentManager fm, int mnumoftabs) {
        super(fm);
        this.numoftabs = mnumoftabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                CocoProductDetails4Fragment tab1 = new CocoProductDetails4Fragment();
                return tab1;

            case 1:
                CocoProductDetails4Fragment tab2 = new CocoProductDetails4Fragment();
                return tab2;

            case 2:
                CocoProductDetails4Fragment tab3 = new CocoProductDetails4Fragment();
                return tab3;


            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numoftabs;
    }
}
