package com.ws.design.coco_ecommerce_ui_kit.signup;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.wolfsoft2.coco_ecommerce_ui_kit.R;
import com.ws.design.coco_ecommerce_ui_kit.DrawerActivity;
import com.ws.design.coco_ecommerce_ui_kit.MobileVerificationActivity;
import com.ws.design.coco_ecommerce_ui_kit.shared_preference.CocoPreferences;
import com.ws.design.coco_ecommerce_ui_kit.utility.Util;

import static com.ws.design.coco_ecommerce_ui_kit.utility.Util.isEmailValid;
import static com.ws.design.coco_ecommerce_ui_kit.utility.Util.isValidMobile;
import static com.ws.design.coco_ecommerce_ui_kit.utility.Util.showCenteredToast;


public class SignupActivity extends AppCompatActivity implements  View.OnClickListener, SignUpView {


    private EditText etEmail;
    private TextView btnSignup;
    private EditText etLname;
    private EditText etFname;
    private EditText etConfirmPassword;
    private EditText etPassword;
    private EditText etPhone;
    private LinearLayout lyParent;
    private SignUpPresenter signUpPresenter;
    private String email;
    private String fName;
    private String lName;
    private String phone;
    private String password;
    private String confirmPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        signUpPresenter = new SignUpPresenter(this);




        init();



    }



    private boolean isValid(String email, String fName, String lName, String phone,  String password, String confirmPassword) throws Exception {
        boolean validation_detials_flag = false;
        if (Util.isDeviceOnline(this)) {
            if (TextUtils.isEmpty(email)) {
                showCenteredToast(lyParent, this, getString(R.string.email_validation_message), "");
                etEmail.requestFocus();
            } else if (TextUtils.isEmpty(fName)) {
                showCenteredToast(lyParent, this, getString(R.string.first_name_validation_message), "");
                etFname.requestFocus();
            } else if (TextUtils.isEmpty(lName)) {
                showCenteredToast(lyParent, this, getString(R.string.last_name_validation_message), "");
                etLname.requestFocus();
            } else if (TextUtils.isEmpty(phone)) {
                showCenteredToast(lyParent, this, getString(R.string.invalid_mobile_number), "");
                etPhone.requestFocus();
            }  else if (TextUtils.isEmpty(password)) {
                showCenteredToast(lyParent, this, getString(R.string.invalid_password), "");
                etPassword.requestFocus();
            } else if (TextUtils.isEmpty(confirmPassword)) {
                showCenteredToast(lyParent, this, getString(R.string.invalid_password), "");
                etConfirmPassword.requestFocus();
            } else if (!isEmailValid(email)) {
                showCenteredToast(lyParent, this, getString(R.string.invalid_email), "");
                etEmail.requestFocus();
            } else if (!isValidMobile(phone)) {
                showCenteredToast(lyParent, this, getString(R.string.mobile_number), "");
                etPhone.requestFocus();
            } else if (!confirmPassword.equalsIgnoreCase(password)) {
                showCenteredToast(lyParent, this, getString(R.string.password_confirm_password), "");
                etConfirmPassword.requestFocus();

            } else {
                validation_detials_flag = true;
            }
        } else {
            showCenteredToast(lyParent, this, getString(R.string.network_connection), "");
        }
        return validation_detials_flag;
    }



    private void init() {
        lyParent = findViewById(R.id.lyParent);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);
        etConfirmPassword = (EditText) findViewById(R.id.etConfirmPassword);
        etFname = (EditText) findViewById(R.id.etFname);
        etLname = (EditText) findViewById(R.id.etLname);
        etPhone = (EditText) findViewById(R.id.etPhone);
        btnSignup = (TextView) findViewById(R.id.btnSignup);
        btnSignup.setOnClickListener(this);


    }






    @Override
    public void onClick(View view) {
        try {
            int vId = view.getId();
            switch (vId) {


                case R.id.btnSignup:
                     email = etEmail.getText().toString().trim();
                     fName = etFname.getText().toString().trim();
                     lName = etLname.getText().toString().trim();
                    phone = etPhone.getText().toString().trim();
                     password = etPassword.getText().toString().trim();
                     confirmPassword = etConfirmPassword.getText().toString().trim();

                    try {
                        if (isValid(email, fName, lName, phone,  password, confirmPassword)) {

                            signUpPresenter.getOtp(phone);




                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }



                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        showCenteredToast(lyParent, this, appErrorMessage, "");
    }

    @Override
    public void onSignSuccess(SignUpResponse signUpResponse) {

       // do nothing
    }


    @Override
    public void getOTP(GetOTPResponse getOTPResponse) {
        try {
            showCenteredToast(lyParent, this, getOTPResponse.getMessage(), "");

            if (getOTPResponse.getmOTPData() != null) {
                if (!TextUtils.isEmpty(getOTPResponse.getmOTPData().getmVcToken())) {
                    String VCToken = getOTPResponse.getmOTPData().getmVcToken();

                    Intent intent = new Intent(SignupActivity.this, MobileVerificationActivity.class);

                    Bundle bundle = new Bundle();
                    bundle.putString("email",email);
                    bundle.putString("fName",fName);
                    bundle.putString("lName",lName);
                    bundle.putString("phone",phone);
                    bundle.putString("password",password);
                    bundle.putString("confirmPassword",confirmPassword);
                    bundle.putString("VCToken",VCToken);

                    intent.putExtra("signupdata",bundle);

                    startActivity(intent);

                } else {
                    showCenteredToast(lyParent, this, getString(R.string.no_vc_token_found), "");

                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
