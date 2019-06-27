package com.ws.design.coco_ecommerce_ui_kit;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wolfsoft2.coco_ecommerce_ui_kit.R;


import Adapter.Cocoproductdetail5Adapter;
import Adapter.ViewpagerAdapter;
import xyz.santeri.wvp.WrappingViewPager;


public class Cocoproductdetail5 extends AppCompatActivity {

    private WrappingViewPager viewPager;

    private ViewpagerAdapter viewpagerAdapter;

    TextView title;


    WrappingViewPager viewPager1;
    TabLayout tabLayout1;


    //    LinearLayout image;
    ImageView image;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cocoproductdetail5);


//        image = findViewById(R.id.image);

        title = findViewById(R.id.title);
        title.setVisibility(View.GONE);


        viewPager = findViewById(R.id.viewpager1);

        viewpagerAdapter = new ViewpagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(viewpagerAdapter);






        viewPager1 = findViewById(R.id.viewpagercoco);
        tabLayout1 = findViewById(R.id.tablayout);

        tabLayout1.setTabGravity(TabLayout.GRAVITY_FILL);

        tabLayout1.addTab(tabLayout1.newTab().setText("Details"));
        tabLayout1.addTab(tabLayout1.newTab().setText("Specifiations"));
        tabLayout1.addTab(tabLayout1.newTab().setText("Reviews"));




        Cocoproductdetail5Adapter adapter = new Cocoproductdetail5Adapter(getSupportFragmentManager(), 3);
        viewPager1.setAdapter(adapter);
        viewPager1.setOffscreenPageLimit(3);
        viewPager1.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout1));
        tabLayout1.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager1.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }



    }










