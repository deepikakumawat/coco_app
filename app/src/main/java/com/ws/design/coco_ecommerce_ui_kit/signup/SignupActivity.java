package com.ws.design.coco_ecommerce_ui_kit.signup;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.wolfsoft2.coco_ecommerce_ui_kit.R;
/*import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;*/
import com.ws.design.coco_ecommerce_ui_kit.DrawerActivity;
import com.ws.design.coco_ecommerce_ui_kit.profile.ProfileActivity;
import com.ws.design.coco_ecommerce_ui_kit.shared_preference.CocoPreferences;
import com.ws.design.coco_ecommerce_ui_kit.utility.Util;

import static com.ws.design.coco_ecommerce_ui_kit.utility.Util.isEmailValid;
import static com.ws.design.coco_ecommerce_ui_kit.utility.Util.isValidMobile;
import static com.ws.design.coco_ecommerce_ui_kit.utility.Util.showCenteredToast;

public class SignupActivity extends AppCompatActivity implements SignUpView, View.OnClickListener {


    private SignUpPresenter signUpPresenter;
    private EditText etEmail;
    private TextView btnSignup;
    private EditText etLname;
    private EditText etFname;
    private EditText etConfirmPassword;
    private EditText etPassword;
    private EditText etPhone;
    private TextView btnGoogle;
//    private GoogleSignInClient googleSignInClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        signUpPresenter = new SignUpPresenter(this);
        setContentView(R.layout.activity_signup);

     /*   GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.client_id))
                .requestEmail()
                .build();
        googleSignInClient = GoogleSignIn.getClient(this, gso);*/


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
        btnGoogle = (TextView) findViewById(R.id.btnGoogle);
        btnGoogle.setOnClickListener(this);


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

    @Override
    public void onClick(View view) {
        try{
            int vId = view.getId();
            switch (vId){
                case R.id.btnGoogle:
                  /*  Intent signInIntent = googleSignInClient.getSignInIntent();
                    startActivityForResult(signInIntent, 101);*/
                    break;
                    default:
                        break;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
       try{
//           if (resultCode == Activity.RESULT_OK)
             /*  switch (requestCode) {
                   case 101:
                       try {
                           // The Task returned from this call is always completed, no need to attach
                           // a listener.
                           Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
                           GoogleSignInAccount account = task.getResult(ApiException.class);
                           onLoggedIn(account);
                       } catch (ApiException e) {
                           // The ApiException status code indicates the detailed failure reason.
                           Log.w("TAG", "signInResult:failed code=" + e.getStatusCode());
                       }
                       break;
               }
*/
       }catch (Exception e){
           e.printStackTrace();
       }
    }

  /*  private void onLoggedIn(GoogleSignInAccount googleSignInAccount) {
      Log.d("name", googleSignInAccount.getDisplayName());
      Log.d("email", googleSignInAccount.getEmail());

    }*/
}
