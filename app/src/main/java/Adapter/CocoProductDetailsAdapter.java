package Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import fragment.Details3Fragment;

public class CocoProductDetailsAdapter extends FragmentPagerAdapter {


    int mNoOfTabs;

    public CocoProductDetailsAdapter(FragmentManager fm) {
        super(fm);


    }

    @Override
    public Fragment getItem(int i) {
        switch (i) {

            case 0:
                Details3Fragment tab1 = new Details3Fragment();
                return tab1;
            case 1:
                Details3Fragment tab2 = new Details3Fragment();
                return tab2;
            case 2:
                Details3Fragment tab3 = new Details3Fragment();
                return tab3;

            case 3:
                Details3Fragment tab4 = new Details3Fragment();
                return tab4;
            case 4:
                Details3Fragment tab5 = new Details3Fragment();
                return tab5;
            case 5:
                Details3Fragment tab6 = new Details3Fragment();
                return tab6;
            case 6:
                Details3Fragment tab7 = new Details3Fragment();
                return tab7;


            default:
                return null;

        }
    }

    @Override
    public int getCount() {
        return 7;
    }
}
