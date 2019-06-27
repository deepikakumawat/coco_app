package com.ws.design.coco_ecommerce_ui_kit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.wolfsoft2.coco_ecommerce_ui_kit.R;

import java.util.ArrayList;

import Adapter.HomeBrandAdapter;
import Adapter.HomeGrideAdapter;
import Model.HomeBrandModel;
import Model.HomeGrideModel;


public class CocoEcommerceHomeActivity extends AppCompatActivity {
//recycle1
    Integer image1[] = {R.drawable.brandimg1, R.drawable.brandimg2,R.drawable.brandimg3,R.drawable.brandimg4,
            R.drawable.brandimg5,R.drawable.brandimg6,R.drawable.brandimg7,R.drawable.viewall};

    String tv1[] = {"Puma","D&G","Ray ban","NIKE","Adidas","Levis","D&G","View All"};

    private RecyclerView recyclerView;
    private HomeBrandAdapter coco_ecommerce_home1_adapter;
    private ArrayList<HomeBrandModel> coco_ecommerce_home1_model;

//recycle2
    Integer mobi[] = {R.drawable.img1,R.drawable.img3,R.drawable.img1,R.drawable.img3,
            R.drawable.img1,R.drawable.img3,R.drawable.img1,R.drawable.img3};

    private RecyclerView recyclerView1;
    private HomeGrideAdapter coco_ecommerce_home2_adapter;
    private ArrayList<HomeGrideModel> coco_ecommerce_home2_models;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coco_ecommerce_home);
//recycle1
        recyclerView = findViewById(R.id.brand_recy);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(CocoEcommerceHomeActivity.this,4);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setNestedScrollingEnabled(false);

        coco_ecommerce_home1_model = new ArrayList<>();

        for (int i = 0; i < image1.length; i++) {
            HomeBrandModel ab = new HomeBrandModel(image1[i],tv1[i]);
            coco_ecommerce_home1_model.add(ab);
        }
        coco_ecommerce_home1_adapter = new HomeBrandAdapter(CocoEcommerceHomeActivity.this, coco_ecommerce_home1_model);
        recyclerView.setAdapter(coco_ecommerce_home1_adapter);

        //recycle2
        recyclerView1 = findViewById(R.id.mobile_recy);
        RecyclerView.LayoutManager layoutManager1 = new GridLayoutManager(CocoEcommerceHomeActivity.this,2);
        recyclerView1.setLayoutManager(layoutManager1);
        recyclerView1.setItemAnimator(new DefaultItemAnimator());
        recyclerView1.setNestedScrollingEnabled(false);

        coco_ecommerce_home2_models = new ArrayList<>();

        for (int i = 0; i < mobi.length; i++) {
            HomeGrideModel ab1 = new HomeGrideModel(mobi[i]);
            coco_ecommerce_home2_models.add(ab1);
        }
        coco_ecommerce_home2_adapter = new HomeGrideAdapter(CocoEcommerceHomeActivity.this, coco_ecommerce_home2_models);
        recyclerView1.setAdapter(coco_ecommerce_home2_adapter);
    }
}
