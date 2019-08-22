package com.ws.design.coco_ecommerce_ui_kit.my_order;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.wolfsoft2.coco_ecommerce_ui_kit.R;
import com.ws.design.coco_ecommerce_ui_kit.login.LoginActivity;

public class LoginAlertOnMyOrderActivity extends AppCompatActivity implements  View.OnClickListener {


    private Button btnLogin;
    private TextView txtContinueShopping;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_alert_on_my_order);


        init();
    }

    private void init() {
        btnLogin = findViewById(R.id.btnLogin);
        txtContinueShopping = findViewById(R.id.txtContinueShopping);
        btnLogin.setOnClickListener(this);
        txtContinueShopping.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {
        try {
            int vId = view.getId();
            switch (vId) {
                case R.id.btnLogin:
                    Intent intent = new Intent(LoginAlertOnMyOrderActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                    break;
                case R.id.txtContinueShopping:
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
