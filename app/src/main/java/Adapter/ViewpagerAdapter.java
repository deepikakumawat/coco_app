package Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import fragment.Details;
import fragment.Viewpager1;
import fragment.Viewpager2;
import fragment.Viewpager3;
import xyz.santeri.wvp.WrappingFragmentPagerAdapter;

/**
 * Created by wolfsoft4 on 13/12/18.
 */

public class ViewpagerAdapter extends WrappingFragmentPagerAdapter {

    public ViewpagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                Viewpager1 tab1 = new Viewpager1();
                return tab1;

            case 1:
                Viewpager2 tab2 = new Viewpager2();

                return tab2;

            case 2:
                Viewpager3 tab3 = new Viewpager3();
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
