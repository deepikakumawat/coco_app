package Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import fragment.Details;
import fragment.Viewpager1;
import fragment.Viewpager2;
import fragment.Viewpager3;
import xyz.santeri.wvp.WrappingFragmentPagerAdapter;

/**
 * Created by wolfsoft4 on 13/12/18.
 */

public class Cocoproductdetail5Adapter extends WrappingFragmentPagerAdapter {

    public Cocoproductdetail5Adapter(FragmentManager fm, int i) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                Details tab1 = new Details();
                return tab1;

            case 1:
                Details tab2 = new Details();

                return tab2;

            case 2:
                Details tab3 = new Details();
                return tab3;


            default:
                return null;

        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}
