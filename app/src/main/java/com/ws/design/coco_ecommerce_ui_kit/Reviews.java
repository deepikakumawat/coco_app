package com.ws.design.coco_ecommerce_ui_kit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.wolfsoft2.coco_ecommerce_ui_kit.R;

import java.util.ArrayList;

import Adapter.ReviewAdapter;
import Model.ReviewModel;

public class Reviews extends AppCompatActivity {
    TextView title;


    private ReviewAdapter reviewAdapter;
    private RecyclerView recyclerview;
    private ArrayList<ReviewModel> reviewModelArrayList;


    String   great[]={"Great Diwali Gift",
            "Great Diwali Gift",
            "Great Diwali Gift",
            "Great Diwali Gift",
            "Great Diwali Gift",
            "Great Diwali Gift",
            "Great Diwali Gift",
            "Great Diwali Gift"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        title = findViewById(R.id.title);
        title.setText("Reviews");


        recyclerview = findViewById(R.id.recycler6);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(Reviews.this);
        recyclerview.setLayoutManager(layoutManager);
        recyclerview.setItemAnimator(new DefaultItemAnimator());

        reviewModelArrayList = new ArrayList<>();

        for (int i = 0; i < great.length; i++) {
            ReviewModel view = new ReviewModel(great[i]);
            reviewModelArrayList.add(view);
        }
        reviewAdapter = new ReviewAdapter(Reviews.this,reviewModelArrayList);
        recyclerview.setAdapter(reviewAdapter);

    }
}
