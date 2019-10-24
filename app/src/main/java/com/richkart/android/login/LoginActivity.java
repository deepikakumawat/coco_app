package com.richkart.android.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.richkart.android.R;
import com.richkart.android.DrawerActivity;
import com.richkart.android.shared_preference.CocoPreferences;
import com.richkart.android.signup.SignupActivity;
import com.richkart.android.utility.Util;

import static com.richkart.android.utility.Util.showCenteredToast;

public class LoginActivity extends AppCompatActivity implements LoginView, View.OnClickListener {

    //    ProgressBar progressBar;
    private EditText etEmail, etPassword;
    private Button btn_login;
    private TextView txtFwdPassword;
    private ConstraintLayout clParent;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        LoginPresenter presenter = new LoginPresenter(this);
//        progressBar = findViewById(R.id.progress);
        findViewById(R.id.btn_register).setOnClickListener(view -> startActivity(new Intent(LoginActivity.this, SignupActivity.class)));
        findViewById(R.id.btn_login).setOnClickListener(view -> {

            String emailId = etEmail.getText().toString().trim();
            String password = etPassword.getText().toString().trim();
            try {
                if (isValid(emailId, password)) {
                    presenter.Login(emailId, password);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });


        init();
    }

    private String maskUserId(String userId) {
        String maskedUserId;
        if (userId.length() >= 3) {
            maskedUserId = userId.substring(0, 3) + "*****";
        } else if (userId.length() == 1) {
            maskedUserId = userId + "*******";
        } else {
            maskedUserId = userId + "******";
        }
        return maskedUserId;
    }


    private boolean isValid(String email, String password) throws Exception {
        boolean validation_detials_flag = false;
        if (Util.isDeviceOnline(this)) {
            if (TextUtils.isEmpty(email)) {
                showCenteredToast(clParent,this, getString(R.string.email_validation_message),"");
                etEmail.requestFocus();
            } else if (TextUtils.isEmpty(password)) {
                showCenteredToast(clParent,this, getString(R.string.invalid_password),"");
                etPassword.requestFocus();
            } else {
                if (!Util.isEmailValid(email)) {
                    showCenteredToast(clParent,this, getString(R.string.invalid_email),"");
                    etEmail.requestFocus();
                } else {
                    validation_detials_flag = true;
                }

            }
        } else {
//            showCenteredToast(clParent,this, getString(R.string.network_connection),"");
            Util.showNoInternetDialog(this);
        }
        return validation_detials_flag;
    }


    @Override
    public void showWait() {
//        progressBar.setVisibility(View.VISIBLE);
        Util.showProDialog(this);
    }


    @Override
    public void removeWait() {
//        progressBar.setVisibility(View.GONE);
        Util.dismissProDialog();
    }


    @Override
    public void onFailure(String appErrorMessage) {
        Util.showCenteredToast(clParent,this, appErrorMessage,"");
    }


    private void init() {
        clParent =  findViewById(R.id.clParent);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);
        btn_login = (Button) findViewById(R.id.btn_login);
        txtFwdPassword = (TextView) findViewById(R.id.txtFwdPassword);
        txtFwdPassword.setOnClickListener(this);

    }

    @Override
    public void onLoginSuccess(LoginResponse loginResponse) {

        try {

            if (loginResponse != null) {

                if (!TextUtils.isEmpty(loginResponse.getMStatus()) && ("1".equalsIgnoreCase(loginResponse.getMStatus()))) {


                    CocoPreferences.setUserId(TextUtils.isEmpty(loginResponse.getLoginData().getMId()) ? "" : loginResponse.getLoginData().getMId());
                    CocoPreferences.setUserEmail(TextUtils.isEmpty(loginResponse.getLoginData().getMEmail()) ? "": loginResponse.getLoginData().getMEmail());
                    CocoPreferences.setUserPhone(TextUtils.isEmpty(loginResponse.getLoginData().getMMobileNo())? "" : loginResponse.getLoginData().getMMobileNo());
                    CocoPreferences.setFirstName(TextUtils.isEmpty(loginResponse.getLoginData().getMName())? "" :loginResponse.getLoginData().getMName());
                    CocoPreferences.setLastName(TextUtils.isEmpty(loginResponse.getLoginData().getMLastName()) ? "" : loginResponse.getLoginData().getMLastName());
                    CocoPreferences.setProfilePic(TextUtils.isEmpty(loginResponse.getLoginData().getMProfilePic()) ? "" : loginResponse.getLoginData().getMProfilePic());
                    CocoPreferences.savePreferencese();


                    Intent intent = new Intent(LoginActivity.this, DrawerActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    showCenteredToast(clParent,this, loginResponse.getMessage(),"");
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void forgotPassword(ForgotPasswordResponse forgotPasswordResponse) {
        // nothing
    }

    @Override
    public void onClick(View view) {
        try {
            int vId = view.getId();
            switch (vId) {
                case R.id.txtFwdPassword:
                    Intent intent = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
                    startActivity(intent);

                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
