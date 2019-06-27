package com.ws.design.coco_ecommerce_ui_kit;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.wolfsoft2.coco_ecommerce_ui_kit.R;

import java.util.ArrayList;

import Adapter.CocoProductListGride3Adapterr;
import Model.CocoListGridmodel;

public class Cocoproductlist3Activity extends AppCompatActivity implements View.OnClickListener {

    TextView tv1,tv2,tv3,tv4,title;

    //recycle1
    Integer mobi[] = {R.drawable.img1,R.drawable.img1,R.drawable.img1,R.drawable.img1,R.drawable.img1,R.drawable.img1,R.drawable.img1,R.drawable.img1};

    private RecyclerView recyclerView;
    private CocoProductListGride3Adapterr coco_product_list_gride_adapter;
    private ArrayList<CocoListGridmodel> coco_product_list_gride_models;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cocoproductlist3);

        title = findViewById(R.id.title);
        title.setText("Search : Care");

        recyclerView = findViewById(R.id.recy_list3);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(Cocoproductlist3Activity.this,2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setFocusableInTouchMode(false);


        coco_product_list_gride_models = new ArrayList<>();

        for (int i = 0; i < mobi.length; i++) {
            CocoListGridmodel ab = new CocoListGridmodel(mobi[i]);
            coco_product_list_gride_models.add(ab);
        }
        coco_product_list_gride_adapter = new CocoProductListGride3Adapterr(Cocoproductlist3Activity.this, coco_product_list_gride_models);
        recyclerView.setAdapter(coco_product_list_gride_adapter);


        tv1 = findViewById(R.id.tv1);
        tv2 = findViewById(R.id.tv2);
        tv3 = findViewById(R.id.tv3);
        tv4 = findViewById(R.id.tv4);

        tv1.setOnClickListener(this);
        tv2.setOnClickListener(this);
        tv3.setOnClickListener(this);
        tv4.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.tv1:
                tv1.setTextColor(Color.parseColor("#d44334"));
                tv2.setTextColor(Color.parseColor("#808080"));
                tv3.setTextColor(Color.parseColor("#808080"));
                tv4.setTextColor(Color.parseColor("#808080"));
                break;

            case R.id.tv2:
                tv1.setTextColor(Color.parseColor("#808080"));
                tv2.setTextColor(Color.parseColor("#d44334"));
                tv3.setTextColor(Color.parseColor("#808080"));
                tv4.setTextColor(Color.parseColor("#808080"));
                break;
            case R.id.tv3:
                tv1.setTextColor(Color.parseColor("#808080"));
                tv2.setTextColor(Color.parseColor("#808080"));
                tv3.setTextColor(Color.parseColor("#d44334"));
                tv4.setTextColor(Color.parseColor("#808080"));
                break;
            case R.id.tv4:
                tv1.setTextColor(Color.parseColor("#808080"));
                tv2.setTextColor(Color.parseColor("#808080"));
                tv3.setTextColor(Color.parseColor("#808080"));
                tv4.setTextColor(Color.parseColor("#d44334"));
                break;
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
