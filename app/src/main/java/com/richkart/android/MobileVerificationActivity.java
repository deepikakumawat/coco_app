package com.richkart.android;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.richkart.android.shared_preference.CocoPreferences;
import com.richkart.android.signup.GetOTPResponse;
import com.richkart.android.signup.SignUpPresenter;
import com.richkart.android.signup.SignUpResponse;
import com.richkart.android.signup.SignUpView;
import com.richkart.android.utility.Util;

import static com.richkart.android.utility.Util.showCenteredToast;

public class MobileVerificationActivity extends AppCompatActivity implements View.OnClickListener, SignUpView {

    private TextView txtTitle;
    private EditText etOTP;
    private TextView txtPhone;
    private TextView txtEditPhone;
    private TextView txtVerify;
    private TextView txtResend;
    private String email;
    private String fName;
    private String lName;
    private String phone;
    private String password;
    private String gender;
    private String confirmPassword;
    private String VCToken;
    private SignUpPresenter signUpPresenter;
    private RelativeLayout ryParent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobile_verification);

        signUpPresenter = new SignUpPresenter(this);

        Intent intent = getIntent();
        if (intent != null) {
            Bundle bundle = intent.getBundleExtra("signupdata");
            if (bundle != null) {

                email = bundle.getString("email");
                fName = bundle.getString("fName");
                lName = bundle.getString("lName");
                phone = bundle.getString("phone");
                gender = bundle.getString("gender");
                password = bundle.getString("password");
                confirmPassword = bundle.getString("confirmPassword");
                VCToken = bundle.getString("VCToken");
            }
        }

        init();

    }

    private void init() {



        ryParent = findViewById(R.id.ryParent);
        etOTP = findViewById(R.id.etOTP);
        txtPhone = findViewById(R.id.txtPhone);
        txtEditPhone = findViewById(R.id.txtEditPhone);
        txtVerify = findViewById(R.id.txtVerify);
        txtResend = findViewById(R.id.txtResend);
        txtVerify.setOnClickListener(this);
        txtResend.setOnClickListener(this);
        txtEditPhone.setOnClickListener(this);

        txtPhone.setText(phone);


    }

    @Override
    public void onClick(View view) {
        try {
            int vId = view.getId();
            switch (vId) {
                case R.id.txtVerify:
                    String otp = etOTP.getText().toString();

                    if (isValid(otp)) {
                        signUpPresenter.doSignUp(email, fName, lName, phone, otp, VCToken, password,gender, confirmPassword);

                    }

                    break;
                case R.id.txtResend:
                    if (Util.isDeviceOnline(this)) {
                        signUpPresenter.getOtp(phone);

                    } else {
//                        showCenteredToast(ryParent, this, getString(R.string.network_connection), "");
                        Util.showNoInternetDialog(this);
                    }
                    break;
                case R.id.txtEditPhone:
                    finish();
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
        showCenteredToast(ryParent, this, appErrorMessage, "");
    }

    @Override
    public void onSignSuccess(SignUpResponse signUpResponse) {

        if (!TextUtils.isEmpty(signUpResponse.getMStatus()) && ("1".equalsIgnoreCase(signUpResponse.getMStatus()))) {



            CocoPreferences.setUserId(TextUtils.isEmpty(signUpResponse.getMLoginData().getMId()) ? "" : signUpResponse.getMLoginData().getMId());
            CocoPreferences.setUserEmail(TextUtils.isEmpty(signUpResponse.getMLoginData().getMEmail()) ? "" : signUpResponse.getMLoginData().getMEmail());
            CocoPreferences.setUserPhone(TextUtils.isEmpty(signUpResponse.getMLoginData().getMMobileNo()) ? "" : signUpResponse.getMLoginData().getMMobileNo());
            CocoPreferences.setFirstName(TextUtils.isEmpty(signUpResponse.getMLoginData().getMName()) ? "" : signUpResponse.getMLoginData().getMName());
            CocoPreferences.setFirstName(TextUtils.isEmpty(signUpResponse.getMLoginData().getMGender()) ? "" : signUpResponse.getMLoginData().getMGender());
            CocoPreferences.setLastName(TextUtils.isEmpty(signUpResponse.getMLoginData().getMLastName()) ? "" : signUpResponse.getMLoginData().getMLastName());
            CocoPreferences.savePreferencese();


            Intent intent = new Intent(MobileVerificationActivity.this, DrawerActivity.class);
            startActivity(intent);
            finish();

        } else {
            showCenteredToast(ryParent, this, signUpResponse.getMessage(), "");
        }
    }


    @Override
    public void getOTP(GetOTPResponse getOTPResponse) {
        try {
            showCenteredToast(ryParent, this, getOTPResponse.getMessage(), "");

            if (getOTPResponse.getMOTPData() != null) {
                if (!TextUtils.isEmpty(getOTPResponse.getMOTPData().getMVcToken())) {
                    VCToken = getOTPResponse.getMOTPData().getMVcToken();

                } else {
                    showCenteredToast(ryParent, this, getString(R.string.no_vc_token_found), "");

                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean isValid(String otp) {
        boolean validation_detials_flag = false;
        if (Util.isDeviceOnline(this)) {
            if (TextUtils.isEmpty(otp)) {
                showCenteredToast(ryParent, this, getString(R.string.invalid_otp), "");
                etOTP.requestFocus();
            } else {
                validation_detials_flag = true;
            }
        } else {
            Util.showNoInternetDialog(this);
        }
        return validation_detials_flag;
    }

}
