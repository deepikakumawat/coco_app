package com.nav.richkart.signup;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.nav.richkart.R;
import com.nav.richkart.MobileVerificationActivity;
import com.nav.richkart.utility.Util;

import static com.nav.richkart.utility.Util.isEmailValid;
import static com.nav.richkart.utility.Util.isValidMobile;
import static com.nav.richkart.utility.Util.showCenteredToast;


public class SignupActivity extends AppCompatActivity implements  View.OnClickListener, SignUpView {


    private EditText etEmail;
    private Button btnSignup;
    private EditText etLname;
    private EditText etFname;
    private EditText etConfirmPassword;
    private EditText etPassword;
    private EditText etPhone;
    private ConstraintLayout clParent;
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
                showCenteredToast(clParent, this, getString(R.string.email_validation_message), "");
                etEmail.requestFocus();
            } else if (TextUtils.isEmpty(fName)) {
                showCenteredToast(clParent, this, getString(R.string.first_name_validation_message), "");
                etFname.requestFocus();
            } else if (TextUtils.isEmpty(lName)) {
                showCenteredToast(clParent, this, getString(R.string.last_name_validation_message), "");
                etLname.requestFocus();
            } else if (TextUtils.isEmpty(phone)) {
                showCenteredToast(clParent, this, getString(R.string.invalid_mobile_number), "");
                etPhone.requestFocus();
            }  else if (TextUtils.isEmpty(password)) {
                showCenteredToast(clParent, this, getString(R.string.invalid_password), "");
                etPassword.requestFocus();
            } else if (TextUtils.isEmpty(confirmPassword)) {
                showCenteredToast(clParent, this, getString(R.string.invalid_password), "");
                etConfirmPassword.requestFocus();
            } else if (!isEmailValid(email)) {
                showCenteredToast(clParent, this, getString(R.string.invalid_email), "");
                etEmail.requestFocus();
            } else if (!isValidMobile(phone)) {
                showCenteredToast(clParent, this, getString(R.string.mobile_number), "");
                etPhone.requestFocus();
            } else if (!confirmPassword.equalsIgnoreCase(password)) {
                showCenteredToast(clParent, this, getString(R.string.password_confirm_password), "");
                etConfirmPassword.requestFocus();

            } else {
                validation_detials_flag = true;
            }
        } else {
//            showCenteredToast(clParent, this, getString(R.string.network_connection), "");
            Util.showNoInternetDialog(this);
        }
        return validation_detials_flag;
    }



    private void init() {
        clParent = findViewById(R.id.clParent);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);
        etConfirmPassword = (EditText) findViewById(R.id.etConfirmPassword);
        etFname = (EditText) findViewById(R.id.etFname);
        etLname = (EditText) findViewById(R.id.etLname);
        etPhone = (EditText) findViewById(R.id.etPhone);
        btnSignup =  findViewById(R.id.btnSignup);
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
        showCenteredToast(clParent, this, appErrorMessage, "");
    }

    @Override
    public void onSignSuccess(SignUpResponse signUpResponse) {

       // do nothing
    }


    @Override
    public void getOTP(GetOTPResponse getOTPResponse) {
        try {
            showCenteredToast(clParent, this, getOTPResponse.getMessage(), "");

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
                    showCenteredToast(clParent, this, getString(R.string.no_vc_token_found), "");

                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
