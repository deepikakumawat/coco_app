package com.ws.design.coco_ecommerce_ui_kit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wolfsoft2.coco_ecommerce_ui_kit.R;
import com.ws.design.coco_ecommerce_ui_kit.login.LoginActivity;

public class MyAccountActivity extends AppCompatActivity implements View.OnClickListener {

    TextView title,edit,test,edit_txt;
    private ImageView imgBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);

        title = (TextView)findViewById(R.id.title);
        edit_txt = (TextView)findViewById(R.id.edit_txt);
        edit_txt.setVisibility(View.GONE);
        edit = (TextView)findViewById(R.id.edit);
        imgBack = (ImageView)findViewById(R.id.imgBack);
        edit.setVisibility(View.VISIBLE);
        title.setVisibility(View.VISIBLE);

        imgBack.setOnClickListener(this);

        findViewById(R.id.btn_logout).setOnClickListener(view -> startActivity(new Intent(MyAccountActivity.this, LoginActivity.class)));
    }

    @Override
    public void onClick(View view) {
        try{
            int vId = view.getId();
            switch (vId){
                case R.id.imgBack:
                    finish();
                    break;
                    default:
                        break;
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
