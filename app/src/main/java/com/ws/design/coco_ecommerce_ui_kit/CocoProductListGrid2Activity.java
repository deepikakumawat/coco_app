package com.ws.design.coco_ecommerce_ui_kit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.wolfsoft2.coco_ecommerce_ui_kit.R;

import java.util.ArrayList;

import Adapter.ProductListGridAdapter;
import Model.ProductListGridModel;



public class CocoProductListGrid2Activity extends AppCompatActivity {

    TextView title;

    //recycle1
    Integer mobi[] = {R.drawable.img1, R.drawable.img1,R.drawable.img3,R.drawable.img1,R.drawable.img1,R.drawable.img3,R.drawable.img1,R.drawable.img1,R.drawable.img3,R.drawable.img1};

    private RecyclerView recyclerView;
    private ProductListGridAdapter coco_product_list_gride_adapter;
    private ArrayList<ProductListGridModel> coco_product_list_gride_models;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coco_product_list_grid2);

        title = findViewById(R.id.title);
        title.setText("Search : Care");

        recyclerView = findViewById(R.id.gride);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(CocoProductListGrid2Activity.this,2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setFocusableInTouchMode(false);

        coco_product_list_gride_models = new ArrayList<>();

        for (int i = 0; i < mobi.length; i++) {
            ProductListGridModel ab = new ProductListGridModel(mobi[i]);
            coco_product_list_gride_models.add(ab);
        }
        coco_product_list_gride_adapter = new ProductListGridAdapter(CocoProductListGrid2Activity.this, coco_product_list_gride_models);
        recyclerView.setAdapter(coco_product_list_gride_adapter);
    }
}
