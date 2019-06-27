package com.ws.design.coco_ecommerce_ui_kit;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.wolfsoft2.coco_ecommerce_ui_kit.R;

public class Coco_Search_Activity extends AppCompatActivity implements View.OnClickListener {


    TextView title;
    LinearLayout linear1,linear2,linear3,linear4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coco__search);

        title = (TextView)findViewById(R.id.edit);
        title.setVisibility(View.GONE);

        linear1 = findViewById(R.id.linear1);
        linear2 = findViewById(R.id.linear2);
        linear3 = findViewById(R.id.linear3);
        linear4 = findViewById(R.id.linear4);

        linear1.setOnClickListener(this);
        linear2.setOnClickListener(this);
        linear3.setOnClickListener(this);
        linear4.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.linear1:
                linear1.setBackgroundResource(R.drawable.gradient_circle);
                linear2.setBackgroundColor(Color.parseColor("#00000000"));
                linear3.setBackgroundColor(Color.parseColor("#00000000"));
                linear4.setBackgroundColor(Color.parseColor("#00000000"));
                break;

            case R.id.linear2:
                linear2.setBackgroundResource(R.drawable.gradient_circle);
                linear1.setBackgroundColor(Color.parseColor("#00000000"));
                linear3.setBackgroundColor(Color.parseColor("#00000000"));
                linear4.setBackgroundColor(Color.parseColor("#00000000"));
                break;

            case R.id.linear3:
                linear3.setBackgroundResource(R.drawable.gradient_circle);
                linear2.setBackgroundColor(Color.parseColor("#00000000"));
                linear1.setBackgroundColor(Color.parseColor("#00000000"));
                linear4.setBackgroundColor(Color.parseColor("#00000000"));
                break;

            case R.id.linear4:
                linear4.setBackgroundResource(R.drawable.gradient_circle);
                linear2.setBackgroundColor(Color.parseColor("#00000000"));
                linear3.setBackgroundColor(Color.parseColor("#00000000"));
                linear1.setBackgroundColor(Color.parseColor("#00000000"));
                break;

        }
    }
}
