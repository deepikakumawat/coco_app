package com.ws.design.coco_ecommerce_ui_kit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.wolfsoft2.coco_ecommerce_ui_kit.R;

import java.util.ArrayList;

import Adapter.Cocoproductlistgrid6Adapter;
import Model.Cocoproductlistgrid6Model;

public class Cocoproductlistgrid6 extends AppCompatActivity {

    TextView title;

    private Cocoproductlistgrid6Adapter cocoproductlistgrid6Adapter;
    private RecyclerView recyclerview;
    private ArrayList<Cocoproductlistgrid6Model> cocoproductlistgrid6ModelArrayList;




    String   company[]={"Apple","Apple","Apple","Apple","Apple","Apple","Apple","Apple","Apple","Apple"};





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cocoproductlistgrid6);

        title= findViewById(R.id.title);
        title.setText("Search : Care");

        recyclerview = findViewById(R.id.recycler2);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(Cocoproductlistgrid6.this);
        recyclerview.setLayoutManager(layoutManager);
        recyclerview.setItemAnimator(new DefaultItemAnimator());
        recyclerview.setNestedScrollingEnabled(false);
        recyclerview.setFocusableInTouchMode(false);

        cocoproductlistgrid6ModelArrayList = new ArrayList<>();

        for (int i = 0; i < company.length; i++) {
            Cocoproductlistgrid6Model view = new Cocoproductlistgrid6Model(company[i]);
            cocoproductlistgrid6ModelArrayList.add(view);
        }
        cocoproductlistgrid6Adapter = new Cocoproductlistgrid6Adapter(Cocoproductlistgrid6.this,cocoproductlistgrid6ModelArrayList);
        recyclerview.setAdapter(cocoproductlistgrid6Adapter);



    }
}
