package com.ws.design.coco_ecommerce_ui_kit;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.wolfsoft2.coco_ecommerce_ui_kit.R;

import java.util.ArrayList;

import Adapter.ProductListGridAdapter;
import Model.ProductListGridModel;

public class CocoProductListGridwithTabActivity extends AppCompatActivity implements View.OnClickListener {

    LinearLayout li1, li2, li3, li4;
    ImageView img1, img2, img3, img4;
    View v1, v2, v3, v4;
    TextView title;

    Integer mobi[] = {R.drawable.img1, R.drawable.img3, R.drawable.img3, R.drawable.img1, R.drawable.img1, R.drawable.img3, R.drawable.img3, R.drawable.img1};

    private RecyclerView recyclerView;
    private ProductListGridAdapter coco_product_list_gride_adapter;
    private ArrayList<ProductListGridModel> coco_product_list_gride_models;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coco_product_list_gridwith_tab);

        title = findViewById(R.id.title);
        title.setText("MOBILES");

        recyclerView = findViewById(R.id.tablist);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(CocoProductListGridwithTabActivity.this, 2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());


        coco_product_list_gride_models = new ArrayList<>();

        for (int i = 0; i < mobi.length; i++) {
            ProductListGridModel ab = new ProductListGridModel(mobi[i]);
            coco_product_list_gride_models.add(ab);
        }
        coco_product_list_gride_adapter = new ProductListGridAdapter(CocoProductListGridwithTabActivity.this, coco_product_list_gride_models);
        recyclerView.setAdapter(coco_product_list_gride_adapter);

        li1 = findViewById(R.id.li1);
        li2 = findViewById(R.id.li2);
        li3 = findViewById(R.id.li3);
        li4 = findViewById(R.id.li4);

        img1 = findViewById(R.id.img1);
        img2 = findViewById(R.id.img2);
        img3 = findViewById(R.id.img3);
        img4 = findViewById(R.id.img4);

        v1 = findViewById(R.id.vl);
        v2 = findViewById(R.id.v2);
        v3 = findViewById(R.id.v3);
        v4 = findViewById(R.id.v4);

        li1.setOnClickListener(this);
        li2.setOnClickListener(this);
        li3.setOnClickListener(this);
        li4.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.li1:
                img1.setImageResource(R.drawable.ic_icon1_red);
                img2.setImageResource(R.drawable.ic_icon1_gray);
                img3.setImageResource(R.drawable.ic_icon2_gry);
                img4.setImageResource(R.drawable.ic_icon3_gray);

                v1.setBackgroundColor(Color.parseColor("#d44334"));
                v2.setBackgroundColor(Color.parseColor("#29a7a4a4"));
                v3.setBackgroundColor(Color.parseColor("#29a7a4a4"));
                v4.setBackgroundColor(Color.parseColor("#29a7a4a4"));

                v1.setVisibility(View.VISIBLE);
                v2.setVisibility(View.INVISIBLE);
                v3.setVisibility(View.INVISIBLE);
                v4.setVisibility(View.INVISIBLE);

                break;

            case R.id.li2:
                img1.setImageResource(R.drawable.ic_icon1_gray);
                img2.setImageResource(R.drawable.ic_icon1_red);
                img3.setImageResource(R.drawable.ic_icon2_gry);
                img4.setImageResource(R.drawable.ic_icon3_gray);

                v1.setBackgroundColor(Color.parseColor("#29a7a4a4"));
                v2.setBackgroundColor(Color.parseColor("#d44334"));
                v3.setBackgroundColor(Color.parseColor("#29a7a4a4"));
                v4.setBackgroundColor(Color.parseColor("#29a7a4a4"));

                v2.setVisibility(View.VISIBLE);
                v1.setVisibility(View.INVISIBLE);
                v3.setVisibility(View.INVISIBLE);
                v4.setVisibility(View.INVISIBLE);

                break;

            case R.id.li3:
                img1.setImageResource(R.drawable.ic_icon1_gray);
                img2.setImageResource(R.drawable.ic_icon1_gray);
                img3.setImageResource(R.drawable.ic_icon2_red);
                img4.setImageResource(R.drawable.ic_icon3_gray);

                v1.setBackgroundColor(Color.parseColor("#29a7a4a4"));
                v2.setBackgroundColor(Color.parseColor("#29a7a4a4"));
                v3.setBackgroundColor(Color.parseColor("#d44334"));
                v4.setBackgroundColor(Color.parseColor("#29a7a4a4"));

                v3.setVisibility(View.VISIBLE);
                v2.setVisibility(View.INVISIBLE);
                v1.setVisibility(View.INVISIBLE);
                v4.setVisibility(View.INVISIBLE);

                break;

            case R.id.li4:
                img1.setImageResource(R.drawable.ic_icon1_gray);
                img2.setImageResource(R.drawable.ic_icon1_gray);
                img3.setImageResource(R.drawable.ic_icon2_gry);
                img4.setImageResource(R.drawable.ic_icon3_red);

                v1.setBackgroundColor(Color.parseColor("#29a7a4a4"));
                v2.setBackgroundColor(Color.parseColor("#29a7a4a4"));
                v3.setBackgroundColor(Color.parseColor("#29a7a4a4"));
                v4.setBackgroundColor(Color.parseColor("#d44334"));

                v4.setVisibility(View.VISIBLE);
                v2.setVisibility(View.INVISIBLE);
                v3.setVisibility(View.INVISIBLE);
                v1.setVisibility(View.INVISIBLE);

                break;

        }
    }
}
