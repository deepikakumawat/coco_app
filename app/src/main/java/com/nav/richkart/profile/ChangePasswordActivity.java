package com.nav.richkart.profile;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.nav.richkart.R;

public class ChangePasswordActivity extends AppCompatActivity implements View.OnClickListener {


    private ImageView imgBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_change);
        imgBack = findViewById(R.id.imgBack);
        imgBack.setOnClickListener(this);



    }

    @Override
    public void onClick(View view) {
        try {
            int vId = view.getId();
            switch (vId) {

                case R.id.imgBack:
                    finish();
                    break;

                default:
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
