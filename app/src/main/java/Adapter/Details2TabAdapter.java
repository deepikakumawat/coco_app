package Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import fragment.CocoProductDetails2Fragment;


/**
 * Created by wolfsoft3 on 24/7/18.
 */

public class Details2TabAdapter extends FragmentPagerAdapter {
    int numoftabs;

    public Details2TabAdapter(FragmentManager fm, int mnumoftabs) {
        super(fm);
        this.numoftabs = mnumoftabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                CocoProductDetails2Fragment tab1 = new CocoProductDetails2Fragment();
                return tab1;

            case 1:
                CocoProductDetails2Fragment tab2 = new CocoProductDetails2Fragment();
                return tab2;

            case 2:
                CocoProductDetails2Fragment tab3 = new CocoProductDetails2Fragment();
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
