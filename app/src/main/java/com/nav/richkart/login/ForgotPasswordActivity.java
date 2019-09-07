package com.nav.richkart.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.nav.richkart.R;
import com.nav.richkart.DrawerActivity;
import com.nav.richkart.shared_preference.CocoPreferences;
import com.nav.richkart.utility.Util;

import static com.nav.richkart.utility.Util.showCenteredToast;

public class ForgotPasswordActivity extends AppCompatActivity implements LoginView, View.OnClickListener {

    private EditText etEmail;
    private Button btnSend;
    private ImageView imgBack;
    private LinearLayout lyConfirmationMail;
    private RelativeLayout ryParent;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        ryParent = findViewById(R.id.ryParent);
        imgBack = findViewById(R.id.imgBack);
        imgBack.setOnClickListener(this);

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
                showCenteredToast(ryParent,this, getString(R.string.email_validation_message),"");
                etEmail.requestFocus();
            }  else {
                if (!Util.isEmailValid(email)) {
                    showCenteredToast(ryParent,this, getString(R.string.invalid_email),"");
                    etEmail.requestFocus();
                } else {
                    validation_detials_flag = true;
                }

            }
        } else {
//            showCenteredToast(ryParent,this, getString(R.string.network_connection),"");
            Util.showNoInternetDialog(this);
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
        Util.showCenteredToast(ryParent,this, appErrorMessage,"");
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
                    showCenteredToast(ryParent,this, loginResponse.getMessage(),"");
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
