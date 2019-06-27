package com.ws.design.coco_ecommerce_ui_kit;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.wolfsoft2.coco_ecommerce_ui_kit.R;

import Adapter.Details2TabAdapter;

public class CocoProductDetails2 extends AppCompatActivity {

    TextView search_mobile_text;
    TabLayout tab_layout;
    ViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coco_product_details2);
        tab_layout = (TabLayout) findViewById(R.id.tab_layout);

        search_mobile_text = findViewById(R.id.search_mobile_text);
        search_mobile_text.setVisibility(View.GONE);


        tab_layout.setTabMode(TabLayout.MODE_SCROLLABLE);
        tab_layout.addTab(tab_layout.newTab().setText("Details"));
        tab_layout.addTab(tab_layout.newTab().setText("Specifications"));
        tab_layout.addTab(tab_layout.newTab().setText("Reviews"));



        tab_layout.setTabGravity(TabLayout.GRAVITY_FILL);


        final ViewPager viewPager1 = (ViewPager) findViewById(R.id.pager);
        Details2TabAdapter adapter = new Details2TabAdapter(getSupportFragmentManager(), 3);
        viewPager1.setAdapter(adapter);
        viewPager1.setOffscreenPageLimit(1);
        viewPager1.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tab_layout));
        tab_layout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

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
