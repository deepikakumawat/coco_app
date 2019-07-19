package com.ws.design.coco_ecommerce_ui_kit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.wolfsoft2.coco_ecommerce_ui_kit.R;

import java.util.ArrayList;

import Adapter.CocoProductListGrideAdapter;
import Model.CocoProductListGridmodel;


public class ProductByCategoryActivity1 extends AppCompatActivity implements View.OnClickListener {


    private TextView tv1, tv2, tv3, tv4, title;

    Integer mobi[] = {R.drawable.img1, R.drawable.img1, R.drawable.img1, R.drawable.img1, R.drawable.img1, R.drawable.img1, R.drawable.img1, R.drawable.img1};

    private RecyclerView rvProducts;
    private CocoProductListGrideAdapter coco_product_list_gride_adapter;
    private ArrayList<CocoProductListGridmodel> coco_product_list_gride_models;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_product_by_category1);

        title = findViewById(R.id.title);
        title.setText("Search : Care");

        tv1 = findViewById(R.id.tv1);

        tv2 = findViewById(R.id.tv2);
        tv3 = findViewById(R.id.tv3);
        tv4 = findViewById(R.id.tv4);

        tv1.setOnClickListener(this);
        tv2.setOnClickListener(this);
        tv3.setOnClickListener(this);
        tv4.setOnClickListener(this);
        rvProducts = findViewById(R.id.rvProducts);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(ProductByCategoryActivity1.this, 2);
        rvProducts.setLayoutManager(layoutManager);
        rvProducts.setItemAnimator(new DefaultItemAnimator());
        rvProducts.setNestedScrollingEnabled(false);
        rvProducts.setFocusableInTouchMode(false);

        coco_product_list_gride_models = new ArrayList<>();

        for (int i = 0; i < mobi.length; i++) {
            CocoProductListGridmodel ab = new CocoProductListGridmodel(mobi[i]);
            coco_product_list_gride_models.add(ab);
        }
        coco_product_list_gride_adapter = new CocoProductListGrideAdapter(ProductByCategoryActivity1.this, coco_product_list_gride_models);
        rvProducts.setAdapter(coco_product_list_gride_adapter);

    }

    @Override
    public void onClick(View v) {


    }


}
