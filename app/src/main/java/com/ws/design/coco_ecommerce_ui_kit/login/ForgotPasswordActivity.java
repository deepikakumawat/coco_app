package com.ws.design.coco_ecommerce_ui_kit.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

import com.example.wolfsoft2.coco_ecommerce_ui_kit.R;
import com.ws.design.coco_ecommerce_ui_kit.DrawerActivity;
import com.ws.design.coco_ecommerce_ui_kit.shared_preference.CocoPreferences;
import com.ws.design.coco_ecommerce_ui_kit.utility.Util;

import static com.ws.design.coco_ecommerce_ui_kit.utility.Util.showCenteredToast;

public class ForgotPasswordActivity extends AppCompatActivity implements LoginView {

    private EditText etEmail;
    private Button btnSend;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
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
                showCenteredToast(this, getString(R.string.email_validation_message));
                etEmail.requestFocus();
            }  else {
                if (!Util.isEmailValid(email)) {
                    showCenteredToast(this, getString(R.string.invalid_email));
                    etEmail.requestFocus();
                } else {
                    validation_detials_flag = true;
                }

            }
        } else {
            showCenteredToast(this, getString(R.string.network_connection));
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
        Util.showCenteredToast(this, appErrorMessage);
    }


    private void init() {
        etEmail = (EditText) findViewById(R.id.etEmail);

        btnSend = (Button) findViewById(R.id.btnSend);


    }

    @Override
    public void onLoginSuccess(LoginResponse loginResponse) {

        try {

            if (loginResponse != null) {

                if (!TextUtils.isEmpty(loginResponse.getmStatus()) && ("1".equalsIgnoreCase(loginResponse.getmStatus()))) {


                    CocoPreferences.setUserId(loginResponse.getmLoginData().getmId());
                    CocoPreferences.setUserEmail(loginResponse.getmLoginData().getmEmail());
                    CocoPreferences.setUserPhone(loginResponse.getmLoginData().getmMobileNo());
                    CocoPreferences.setFirstName(loginResponse.getmLoginData().getmName());
                    CocoPreferences.setLastName(loginResponse.getmLoginData().getmLastName());
                    CocoPreferences.savePreferencese();


                    Intent intent = new Intent(ForgotPasswordActivity.this, DrawerActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    showCenteredToast(this, loginResponse.getMessage());
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

                showCenteredToast(this,forgotPasswordResponse.getmForgotPasswordData().getMessage());
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
