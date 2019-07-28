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
    private static final String TAG = "GoogleActivity";
    private static final int RC_SIGN_IN = 9001;
    private LinearLayout lyParent;

    // [START declare_auth]
//    private FirebaseAuth mAuth;
    // [END declare_auth]

//    private GoogleSignInClient mGoogleSignInClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        signUpPresenter = new SignUpPresenter(this);
        setContentView(R.layout.activity_signup);

        // [START config_signin]
        // Configure Google Sign In
       /* GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("AIzaSyDbNunswocOrMepo2lZGO4PCQlTAs7v_kU")
                .requestEmail()
                .build();*/
        // [END config_signin]

//        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        // [START initialize_auth]
        // Initialize Firebase Auth
//        mAuth = FirebaseAuth.getInstance();
        // [END initialize_auth]

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

    // [START on_start_check_user]
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
//        FirebaseUser currentUser = mAuth.getCurrentUser();
//        updateUI(currentUser);
    }
    // [END on_start_check_user]


    private boolean isValid(String email, String fName, String lName, String phone, String password, String confirmPassword) throws Exception {
        boolean validation_detials_flag = false;
        if (Util.isDeviceOnline(this)) {
            if (TextUtils.isEmpty(email)) {
                showCenteredToast(lyParent,this, getString(R.string.email_validation_message));
                etEmail.requestFocus();
            } else if (TextUtils.isEmpty(fName)) {
                showCenteredToast(lyParent,this, getString(R.string.first_name_validation_message));
                etFname.requestFocus();
            } else if (TextUtils.isEmpty(lName)) {
                showCenteredToast(lyParent,this, getString(R.string.last_name_validation_message));
                etLname.requestFocus();
            } else if (TextUtils.isEmpty(phone)) {
                showCenteredToast(lyParent,this, getString(R.string.invalid_mobile_number));
                etPhone.requestFocus();
            } else if (TextUtils.isEmpty(password)) {
                showCenteredToast(lyParent,this, getString(R.string.invalid_password));
                etPassword.requestFocus();
            } else if (TextUtils.isEmpty(confirmPassword)) {
                showCenteredToast(lyParent,this, getString(R.string.invalid_password));
                etConfirmPassword.requestFocus();
            } else if (!isEmailValid(email)) {
                showCenteredToast(lyParent,this, getString(R.string.invalid_email));
                etEmail.requestFocus();
            } else if (!isValidMobile(phone)) {
                showCenteredToast(lyParent,this, getString(R.string.mobile_number));
                etPhone.requestFocus();
            } else if (!confirmPassword.equalsIgnoreCase(password)) {
                showCenteredToast(lyParent,this, getString(R.string.password_confirm_password));
                etConfirmPassword.requestFocus();

            } else {
                validation_detials_flag = true;
            }
        } else {
            showCenteredToast(lyParent,this, getString(R.string.network_connection));
        }
        return validation_detials_flag;
    }


    private void init() {
        lyParent =  findViewById(R.id.lyParent);
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
        showCenteredToast(lyParent,this, appErrorMessage);
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
            showCenteredToast(lyParent,this,signUpResponse.getMessage());
        }
    }

    @Override
    public void onClick(View view) {
        try{
            int vId = view.getId();
            switch (vId){
                case R.id.btnGoogle:
                   /* Intent signInIntent = mGoogleSignInClient.getSignInIntent();
                    startActivityForResult(signInIntent, RC_SIGN_IN);*/
                    break;
                    default:
                        break;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

  /*  @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e);
                // [START_EXCLUDE]
//                updateUI(null);
                // [END_EXCLUDE]
            }
        }
    }*/

 /*   private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            // Signed in successfully, show authenticated UI.
//            updateUI(account);
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w("TAG", "signInResult:failed code=" + e.getStatusCode());
//            updateUI(null);
        }
    }*/

    // [START auth_with_google]
  /*  private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Log.d(TAG, "firebaseAuthWithGoogle:" + acct.getId());
        // [START_EXCLUDE silent]
//        showProgressDialog();
        // [END_EXCLUDE]

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
//                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Snackbar.make(findViewById(R.id.txtSignup), "Authentication Failed.", Snackbar.LENGTH_SHORT).show();
//                            updateUI(null);
                        }

                        // [START_EXCLUDE]
//                        hideProgressDialog();
                        // [END_EXCLUDE]
                    }
                });
    }
    // [END auth_with_google]

    private void signOut() {
        // Firebase sign out
        mAuth.signOut();

        // Google sign out
        mGoogleSignInClient.signOut().addOnCompleteListener(this,
                new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
//                        updateUI(null);
                    }
                });
    }

    private void revokeAccess() {
        // Firebase sign out
        mAuth.signOut();

        // Google revoke access
        mGoogleSignInClient.revokeAccess().addOnCompleteListener(this,
                new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
//                        updateUI(null);
                    }
                });
    }*/
}
