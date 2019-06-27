package com.ws.design.coco_ecommerce_ui_kit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.wolfsoft2.coco_ecommerce_ui_kit.R;

import java.util.ArrayList;

import Adapter.Cocoproductlistgrid9Adapter;
import Model.Cocoproductlistgrid9Model;

public class Cocoproductlistgrid9 extends AppCompatActivity {

    TextView title;

    private Cocoproductlistgrid9Adapter cocoproductlistgrid9Adapter;
    private RecyclerView recyclerview;
    private ArrayList<Cocoproductlistgrid9Model> cocoproductlistgrid9ModelArrayList;


    String   apple[]={"Apple","Apple","Apple","Apple","Apple","Apple","Apple","Apple"};





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cocoproductlistgrid9);

        title=findViewById(R.id.title);
        title.setText("Search : Care");

        recyclerview = findViewById(R.id.recycler3);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(Cocoproductlistgrid9.this);
        recyclerview.setLayoutManager(layoutManager);
        recyclerview.setItemAnimator(new DefaultItemAnimator());


        cocoproductlistgrid9ModelArrayList = new ArrayList<>();

        for (int i = 0; i < apple.length; i++) {
            Cocoproductlistgrid9Model view = new Cocoproductlistgrid9Model(apple[i]);
            cocoproductlistgrid9ModelArrayList.add(view);
        }
        cocoproductlistgrid9Adapter = new Cocoproductlistgrid9Adapter(Cocoproductlistgrid9.this,cocoproductlistgrid9ModelArrayList);
        recyclerview.setAdapter(cocoproductlistgrid9Adapter);


    }
}
