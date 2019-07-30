package com.ws.design.coco_ecommerce_ui_kit.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.wolfsoft2.coco_ecommerce_ui_kit.R;
import com.ws.design.coco_ecommerce_ui_kit.DrawerActivity;
import com.ws.design.coco_ecommerce_ui_kit.shared_preference.CocoPreferences;
import com.ws.design.coco_ecommerce_ui_kit.utility.Util;

import static com.ws.design.coco_ecommerce_ui_kit.utility.Util.showCenteredToast;

public class ForgotPasswordActivity extends AppCompatActivity implements LoginView, View.OnClickListener {

    private EditText etEmail;
    private Button btnSend;
    private TextView txtTitle;
    private ImageView imgBack;
    private LinearLayout lyConfirmationMail;
    private LinearLayout lyParent;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        lyParent = findViewById(R.id.lyParent);
        txtTitle = findViewById(R.id.txtTitle);
        imgBack = findViewById(R.id.imgBack);
        imgBack.setOnClickListener(this);
        txtTitle.setText("Forgot Password");

        LoginPresenter presenter = new LoginPresenter(this);
        findViewById(R.id.btnSend).setOnClickListener(view -> {

            String emailId = etEmail.getText().toString().trim();
            try {
                if (isValid(emailId)) {
                    presenter.forgotPassword(emailId);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });


        init();
    }



    private boolean isValid(String email) throws Exception {
        boolean validation_detials_flag = false;
        if (Util.isDeviceOnline(this)) {
            if (TextUtils.isEmpty(email)) {
                showCenteredToast(lyParent,this, getString(R.string.email_validation_message),"");
                etEmail.requestFocus();
            }  else {
                if (!Util.isEmailValid(email)) {
                    showCenteredToast(lyParent,this, getString(R.string.invalid_email),"");
                    etEmail.requestFocus();
                } else {
                    validation_detials_flag = true;
                }

            }
        } else {
            showCenteredToast(lyParent,this, getString(R.string.network_connection),"");
        }
        return validation_detials_flag;
    }


    @Override
    public void showWait() {
        Util.showProDialog(this);
    }


    @Override
    public void removeWait() {
        Util.dismissProDialog();
    }


    @Override
    public void onFailure(String appErrorMessage) {
        Util.showCenteredToast(lyParent,this, appErrorMessage,"");
        lyConfirmationMail.setVisibility(View.GONE);
    }


    private void init() {
        etEmail = (EditText) findViewById(R.id.etEmail);

        btnSend = (Button) findViewById(R.id.btnSend);
        lyConfirmationMail = (LinearLayout) findViewById(R.id.lyConfirmationMail);


    }

    @Override
    public void onLoginSuccess(LoginResponse loginResponse) {

        try {

            if (loginResponse != null) {

                if (!TextUtils.isEmpty(loginResponse.getMStatus()) && ("1".equalsIgnoreCase(loginResponse.getMStatus()))) {


                    CocoPreferences.setUserId(loginResponse.getLoginData().getMId());
                    CocoPreferences.setUserEmail(loginResponse.getLoginData().getMEmail());
                    CocoPreferences.setUserPhone(loginResponse.getLoginData().getMMobileNo());
                    CocoPreferences.setFirstName(loginResponse.getLoginData().getMName());
                    CocoPreferences.setLastName(loginResponse.getLoginData().getMLastName());
                    CocoPreferences.savePreferencese();


                    Intent intent = new Intent(ForgotPasswordActivity.this, DrawerActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    showCenteredToast(lyParent,this, loginResponse.getMessage(),"");
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void forgotPassword(ForgotPasswordResponse forgotPasswordResponse) {
        try{

            if (forgotPasswordResponse!=null) {

                lyConfirmationMail.setVisibility(View.VISIBLE);
                etEmail.setText("");
            }else{
                lyConfirmationMail.setVisibility(View.GONE);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
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
