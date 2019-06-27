package com.ws.design.coco_ecommerce_ui_kit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.wolfsoft2.coco_ecommerce_ui_kit.R;

import java.util.ArrayList;

import Adapter.CocoProductListGrideAdapter;
import Model.CocoProductListGridmodel;

public class CocoecommerceHome2Activity extends AppCompatActivity {

    //recycle1

    Integer mobi[] = {R.drawable.img1,R.drawable.img1,R.drawable.img1,R.drawable.img1};

    private RecyclerView recyclerView;
    private CocoProductListGrideAdapter coco_product_list_gride_adapter;
    private ArrayList<CocoProductListGridmodel> coco_product_list_gride_models;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cocoecommerce_home2);

        recyclerView = findViewById(R.id.product);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(CocoecommerceHome2Activity.this,2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setNestedScrollingEnabled(false);

        coco_product_list_gride_models = new ArrayList<>();

        for (int i = 0; i < mobi.length; i++) {
            CocoProductListGridmodel ab = new CocoProductListGridmodel(mobi[i]);
            coco_product_list_gride_models.add(ab);
        }
        coco_product_list_gride_adapter = new CocoProductListGrideAdapter(CocoecommerceHome2Activity.this, coco_product_list_gride_models);
        recyclerView.setAdapter(coco_product_list_gride_adapter);
    }
}
