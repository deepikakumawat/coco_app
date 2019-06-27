package Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import fragment.Viewpager1;
import fragment.Viewpager2;
import fragment.Viewpager3;
import fragment.Viewpager_product_details;
import fragment.Viewpager_product_details2;
import fragment.Viewpager_product_details3;
import fragment.Viewpager_product_details4;
import xyz.santeri.wvp.WrappingFragmentPagerAdapter;

/**
 * Created by wolfsoft4 on 13/12/18.
 */

public class ViewpagerProductDetailsAdapter extends FragmentStatePagerAdapter {

    public ViewpagerProductDetailsAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                Viewpager_product_details tab1 = new Viewpager_product_details();
                return tab1;

            case 1:
                Viewpager_product_details2 tab2 = new Viewpager_product_details2();

                return tab2;

            case 2:
                Viewpager_product_details3 tab3 = new Viewpager_product_details3();
                return tab3;

            case 3:
                Viewpager_product_details4 tab4 = new Viewpager_product_details4();
                return tab4;


            default:
                return null;

        }
    }

    @Override
    public int getCount() {
        return 4;
    }
}
