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


public class CocoProductListGrid10Activity extends AppCompatActivity implements View.OnClickListener
{


    private TextView tv1,tv2,tv3,tv4,title;

    Integer mobi[] = {R.drawable.img1, R.drawable.img1,R.drawable.img1,R.drawable.img1,R.drawable.img1, R.drawable.img1,R.drawable.img1,R.drawable.img1};

    private RecyclerView recyclerView;
    private CocoProductListGrideAdapter coco_product_list_gride_adapter;
    private ArrayList<CocoProductListGridmodel> coco_product_list_gride_models;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coco_product_list_grid10);

        title=findViewById(R.id.title);
        title.setText("Search : Care");

        tv1 = findViewById(R.id.tv1);

        tv2 = findViewById(R.id.tv2);
        tv3 = findViewById(R.id.tv3);
        tv4 = findViewById(R.id.tv4);

        tv1.setOnClickListener(this);
        tv2.setOnClickListener(this);
        tv3.setOnClickListener(this);
        tv4.setOnClickListener(this);
        recyclerView = findViewById(R.id.recy_list3);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(CocoProductListGrid10Activity.this,2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setFocusableInTouchMode(false);

        coco_product_list_gride_models = new ArrayList<>();

        for (int i = 0; i < mobi.length; i++) {
            CocoProductListGridmodel ab = new CocoProductListGridmodel(mobi[i]);
            coco_product_list_gride_models.add(ab);
        }
        coco_product_list_gride_adapter = new CocoProductListGrideAdapter(CocoProductListGrid10Activity.this, coco_product_list_gride_models);
        recyclerView.setAdapter(coco_product_list_gride_adapter);

    }

    @Override
    public void onClick(View v) {



    }
}
