package com.ws.design.coco_ecommerce_ui_kit.signup;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.TextView;

import com.example.wolfsoft2.coco_ecommerce_ui_kit.R;
import com.ws.design.coco_ecommerce_ui_kit.DrawerActivity;
import com.ws.design.coco_ecommerce_ui_kit.shared_preference.CocoPreferences;
import com.ws.design.coco_ecommerce_ui_kit.utility.Util;

import static com.ws.design.coco_ecommerce_ui_kit.utility.Util.isEmailValid;
import static com.ws.design.coco_ecommerce_ui_kit.utility.Util.isValidMobile;
import static com.ws.design.coco_ecommerce_ui_kit.utility.Util.showCenteredToast;

public class SignupActivity extends AppCompatActivity implements SignUpView {


    private SignUpPresenter signUpPresenter;
    private EditText etEmail;
    private TextView btnSignup;
    private EditText etLname;
    private EditText etFname;
    private EditText etConfirmPassword;
    private EditText etPassword;
    private EditText etPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        signUpPresenter = new SignUpPresenter(this);
        setContentView(R.layout.activity_signup);

        init();

        btnSignup.setOnClickListener(view -> {

            String email = etEmail.getText().toString().trim();
            String fName = etFname.getText().toString().trim();
            String lName = etLname.getText().toString().trim();
            String phone = etPhone.getText().toString().trim();
            String password = etPassword.getText().toString().trim();
            String confirmPassword = etConfirmPassword.getText().toString().trim();

            try {
                if (isValid(email, fName, lName, phone, password, confirmPassword)) {
                    signUpPresenter.doSignUp(email, fName, lName, phone, password, confirmPassword);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });


    }

    private boolean isValid(String email, String fName, String lName, String phone, String password, String confirmPassword) throws Exception {
        boolean validation_detials_flag = false;
        if (Util.isDeviceOnline(this)) {
            if (TextUtils.isEmpty(email)) {
                showCenteredToast(this, getString(R.string.email_validation_message));
                etEmail.requestFocus();
            } else if (TextUtils.isEmpty(fName)) {
                showCenteredToast(this, getString(R.string.first_name_validation_message));
                etFname.requestFocus();
            } else if (TextUtils.isEmpty(lName)) {
                showCenteredToast(this, getString(R.string.last_name_validation_message));
                etLname.requestFocus();
            } else if (TextUtils.isEmpty(phone)) {
                showCenteredToast(this, getString(R.string.invalid_mobile_number));
                etPhone.requestFocus();
            } else if (TextUtils.isEmpty(password)) {
                showCenteredToast(this, getString(R.string.invalid_password));
                etPassword.requestFocus();
            } else if (TextUtils.isEmpty(confirmPassword)) {
                showCenteredToast(this, getString(R.string.invalid_password));
                etConfirmPassword.requestFocus();
            } else if (!isEmailValid(email)) {
                showCenteredToast(this, getString(R.string.invalid_email));
                etEmail.requestFocus();
            } else if (!isValidMobile(phone)) {
                showCenteredToast(this, getString(R.string.mobile_number));
                etPhone.requestFocus();
            } else if (!confirmPassword.equalsIgnoreCase(password)) {
                showCenteredToast(this, getString(R.string.password_confirm_password));
                etConfirmPassword.requestFocus();

            } else {
                validation_detials_flag = true;
            }
        } else {
            showCenteredToast(this, getString(R.string.network_connection));
        }
        return validation_detials_flag;
    }


    private void init() {
        etEmail = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);
        etConfirmPassword = (EditText) findViewById(R.id.etConfirmPassword);
        etFname = (EditText) findViewById(R.id.etFname);
        etLname = (EditText) findViewById(R.id.etLname);
        etPhone = (EditText) findViewById(R.id.etPhone);
        btnSignup = (TextView) findViewById(R.id.btnSignup);
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
        showCenteredToast(this, appErrorMessage);
    }

    @Override
    public void onSignSuccess(SignUpResponse signUpResponse) {

        if (!TextUtils.isEmpty(signUpResponse.getmStatus()) && ("1".equalsIgnoreCase(signUpResponse.getmStatus()))) {

            CocoPreferences.setUserId(TextUtils.isEmpty(signUpResponse.getmLoginData().getmId()) ? "" : signUpResponse.getmLoginData().getmId());
            CocoPreferences.setUserEmail(TextUtils.isEmpty(signUpResponse.getmLoginData().getmEmail()) ? "": signUpResponse.getmLoginData().getmEmail());
            CocoPreferences.setUserPhone(TextUtils.isEmpty(signUpResponse.getmLoginData().getmMobileNo())? "" : signUpResponse.getmLoginData().getmMobileNo());
            CocoPreferences.setFirstName(TextUtils.isEmpty(signUpResponse.getmLoginData().getmName())? "" :signUpResponse.getmLoginData().getmName());
            CocoPreferences.setLastName(TextUtils.isEmpty(signUpResponse.getmLoginData().getmLastName()) ? "" : signUpResponse.getmLoginData().getmLastName());
            CocoPreferences.savePreferencese();


            Intent intent = new Intent(SignupActivity.this, DrawerActivity.class);
            startActivity(intent);
            finish();

        }else {
            showCenteredToast(this,signUpResponse.getMessage());
        }
    }
}
