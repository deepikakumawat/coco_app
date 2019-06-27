package com.ws.design.coco_ecommerce_ui_kit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.wolfsoft2.coco_ecommerce_ui_kit.R;

import java.util.ArrayList;

import Adapter.Coco5RecyAdapter;
import Model.Cocoproduct5Model;

public class Cocoproductlistgrid5 extends AppCompatActivity {

    TextView title;

    private Coco5RecyAdapter coco5RecyAdapter;
    private RecyclerView recyclerview;
    private ArrayList<Cocoproduct5Model> cocoproduct5ModelArrayList;

    Integer  mobile[]={R.drawable.img,R.drawable.img,R.drawable.img,R.drawable.img,R.drawable.img,R.drawable.img,R.drawable.img,R.drawable.img};
    Integer  star[]={R.drawable.ic_star,R.drawable.ic_star,R.drawable.ic_star,R.drawable.ic_star,R.drawable.ic_star,R.drawable.ic_star,R.drawable.ic_star,R.drawable.ic_star};
    String   modelname[]={"Apple iPhone XS Max (Space Grey,\n256 GB)","Apple iPhone XS Max (Space Grey,\n256 GB)",
            "Apple iPhone XS Max (Space Grey,\n256 GB)","Apple iPhone XS Max (Space Grey,\n256 GB)","Apple iPhone XS Max (Space Grey,\n256 GB)","Apple iPhone XS Max (Space Grey,\n256 GB)"
            ,"Apple iPhone XS Max (Space Grey,\n256 GB)","Apple iPhone XS Max (Space Grey,\n256 GB)"};
    String   txtrating[]={"4.5","4.5","4.5","4.5","4.5","4.5","4.5","4.5"};
    String   rating[]={"764 Ratings & 105 Reviews","764 Ratings & 105 Reviews","764 Ratings & 105 Reviews","764 Ratings & 105 Reviews","764 Ratings & 105 Reviews","764 Ratings & 105 Reviews","764 Ratings & 105 Reviews","764 Ratings & 105 Reviews"};




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cocoproductlistgrid5);

        title=findViewById(R.id.title);
        title.setText("Search : Care");

        recyclerview = findViewById(R.id.recycler1);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(Cocoproductlistgrid5.this);
        recyclerview.setLayoutManager(layoutManager);
        recyclerview.setItemAnimator(new DefaultItemAnimator());

        cocoproduct5ModelArrayList = new ArrayList<>();

        for (int i = 0; i < mobile.length; i++) {
            Cocoproduct5Model view = new Cocoproduct5Model(mobile[i],star[i],modelname[i],txtrating[i],rating[i]);
            cocoproduct5ModelArrayList.add(view);
        }
        coco5RecyAdapter = new Coco5RecyAdapter(Cocoproductlistgrid5.this,cocoproduct5ModelArrayList);
        recyclerview.setAdapter(coco5RecyAdapter);





    }
}
