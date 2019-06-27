package com.ws.design.coco_ecommerce_ui_kit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.wolfsoft2.coco_ecommerce_ui_kit.R;

import java.util.ArrayList;

import Adapter.Cocoproductlistgrid7Adapter;
import Model.Cocoproductlistgrid7Model;

public class Cocoproductlistgrid7 extends AppCompatActivity {

    private Cocoproductlistgrid7Adapter cocoproductlistgrid7Adapter;
    private RecyclerView recyclerview;
    private ArrayList<Cocoproductlistgrid7Model> cocoproductlistgrid7ModelArrayList;


    String modelname[]={"Iphone 6S, 32GB (Golden and Silver)",
            "Iphone 6S, 32GB (Golden and Silver)",
            "Iphone 6S, 32GB (Golden and Silver)",
            "Iphone 6S, 32GB (Golden and Silver)",
            "Iphone 6S, 32GB (Golden and Silver)"};

    TextView search_mobile_text,title;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R .layout.activity_cocoproductlistgrid7);

        title=findViewById(R.id.title);
        title.setText("Search : Care");
//
//        search_mobile_text = findViewById(R.id.search_mobile_text);
//        search_mobile_text.setText("MOBILES");


        recyclerview = findViewById(R.id.recycler5);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(Cocoproductlistgrid7.this);
        recyclerview.setLayoutManager(layoutManager);
        recyclerview.setItemAnimator(new DefaultItemAnimator());

        cocoproductlistgrid7ModelArrayList = new ArrayList<>();

        for (int i = 0; i < modelname.length; i++) {
            Cocoproductlistgrid7Model view = new Cocoproductlistgrid7Model(modelname[i]);
            cocoproductlistgrid7ModelArrayList.add(view);
        }
        cocoproductlistgrid7Adapter = new Cocoproductlistgrid7Adapter(Cocoproductlistgrid7.this,cocoproductlistgrid7ModelArrayList);
        recyclerview.setAdapter(cocoproductlistgrid7Adapter);
    }
}
