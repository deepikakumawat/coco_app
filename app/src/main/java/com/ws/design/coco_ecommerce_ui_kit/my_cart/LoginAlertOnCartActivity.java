package com.ws.design.coco_ecommerce_ui_kit.my_cart;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.wolfsoft2.coco_ecommerce_ui_kit.R;
import com.ws.design.coco_ecommerce_ui_kit.DrawerActivity;
import com.ws.design.coco_ecommerce_ui_kit.login.ForgotPasswordActivity;
import com.ws.design.coco_ecommerce_ui_kit.login.ForgotPasswordResponse;
import com.ws.design.coco_ecommerce_ui_kit.login.LoginActivity;
import com.ws.design.coco_ecommerce_ui_kit.login.LoginPresenter;
import com.ws.design.coco_ecommerce_ui_kit.login.LoginResponse;
import com.ws.design.coco_ecommerce_ui_kit.login.LoginView;
import com.ws.design.coco_ecommerce_ui_kit.shared_preference.CocoPreferences;
import com.ws.design.coco_ecommerce_ui_kit.signup.SignupActivity;
import com.ws.design.coco_ecommerce_ui_kit.utility.Util;

import static com.ws.design.coco_ecommerce_ui_kit.utility.Util.showCenteredToast;

public class LoginAlertOnCartActivity extends AppCompatActivity implements  View.OnClickListener {


    private Button btnLogin;
    private TextView txtContinueShopping;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_alert_on_cart);


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
                    Intent intent = new Intent(LoginAlertOnCartActivity.this, LoginActivity.class);
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
