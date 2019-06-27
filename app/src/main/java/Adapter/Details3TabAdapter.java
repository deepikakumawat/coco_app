package Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import fragment.CocoProductDetails3Fragment;


/**
 * Created by wolfsoft3 on 24/7/18.
 */

public class Details3TabAdapter extends FragmentPagerAdapter {
    int numoftabs;

    public Details3TabAdapter(FragmentManager fm, int mnumoftabs) {
        super(fm);
        this.numoftabs = mnumoftabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                CocoProductDetails3Fragment tab1 = new CocoProductDetails3Fragment();
                return tab1;

            case 1:
                CocoProductDetails3Fragment tab2 = new CocoProductDetails3Fragment();
                return tab2;

            case 2:
                CocoProductDetails3Fragment tab3 = new CocoProductDetails3Fragment();
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
