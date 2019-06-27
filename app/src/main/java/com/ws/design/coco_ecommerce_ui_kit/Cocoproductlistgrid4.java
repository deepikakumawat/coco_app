package com.ws.design.coco_ecommerce_ui_kit;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.wolfsoft2.coco_ecommerce_ui_kit.R;

import java.util.ArrayList;

import Adapter.Coco5RecyAdapter;
import Model.Cocoproduct5Model;

public class Cocoproductlistgrid4 extends AppCompatActivity implements View.OnClickListener {

    private TextView tv1,tv2,tv3,tv4,title;

    private Coco5RecyAdapter coco5RecyAdapter;
    private RecyclerView recyclerview;
    private ArrayList<Cocoproduct5Model> cocoproduct5ModelArrayList;

    Integer  mobile[]={R.drawable.img, R.drawable.img,R.drawable.img,R.drawable.img,R.drawable.img};
    Integer  star[]={R.drawable.ic_star,R.drawable.ic_star,R.drawable.ic_star,R.drawable.ic_star,R.drawable.ic_star};
    String   modelname[]={"Apple iPhone XS Max (Space Grey,\n256 GB)","Apple iPhone XS Max (Space Grey,\n256 GB)",
            "Apple iPhone XS Max (Space Grey,\n256 GB)","Apple iPhone XS Max (Space Grey,\n256 GB)","Apple iPhone XS Max (Space Grey,\n256 GB)",};
    String   txtrating[]={"4.5","4.5","4.5","4.5","4.5"};
    String   rating[]={"764 Ratings & 105 Reviews","764 Ratings & 105 Reviews","764 Ratings & 105 Reviews","764 Ratings & 105 Reviews","764 Ratings & 105 Reviews"};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cocoproductlistgrid4);

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

        recyclerview = findViewById(R.id.recycler1);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(Cocoproductlistgrid4.this);
        recyclerview.setLayoutManager(layoutManager);
        recyclerview.setItemAnimator(new DefaultItemAnimator());
        recyclerview.setNestedScrollingEnabled(false);
        recyclerview.setFocusableInTouchMode(false);

        cocoproduct5ModelArrayList = new ArrayList<>();

        for (int i = 0; i < mobile.length; i++) {
            Cocoproduct5Model view = new Cocoproduct5Model(mobile[i],star[i],modelname[i],txtrating[i],rating[i]);
            cocoproduct5ModelArrayList.add(view);
        }
        coco5RecyAdapter = new Coco5RecyAdapter(Cocoproductlistgrid4.this,cocoproduct5ModelArrayList);
        recyclerview.setAdapter(coco5RecyAdapter);


    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){

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
}
