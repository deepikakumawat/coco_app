package com.nav.richkart.profile;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.nav.richkart.DrawerActivity;
import com.nav.richkart.MobileVerificationActivity;
import com.nav.richkart.R;
import com.nav.richkart.shared_preference.CocoPreferences;
import com.nav.richkart.utility.Constant;
import com.nav.richkart.utility.Util;

import static com.nav.richkart.utility.Util.isEmailValid;
import static com.nav.richkart.utility.Util.isValidMobile;
import static com.nav.richkart.utility.Util.showCenteredToast;

public class ChangePasswordActivity extends AppCompatActivity implements View.OnClickListener, UpdateView {


    private ImageView imgBack;
    private EditText etConfirmPassword;
    private EditText etOldPassword;
    private EditText etNewPassword;
    private Button btnSubmit;
    private UpdateProfilePresenter updateProfilePresenter;
    private LinearLayout lyParent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_change);

        updateProfilePresenter = new UpdateProfilePresenter(this);

        lyParent = findViewById(R.id.lyParent);
        imgBack = findViewById(R.id.imgBack);
        etOldPassword = findViewById(R.id.etOldPassword);
        etNewPassword = findViewById(R.id.etNewPassword);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);
        btnSubmit = findViewById(R.id.btnSubmit);

        imgBack.setOnClickListener(this);
        btnSubmit.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        try {
            int vId = view.getId();
            switch (vId) {

                case R.id.imgBack:
                    finish();
                    break;

                case R.id.btnSubmit:

                    String oldPass = etOldPassword.getText().toString().trim();
                    String password = etNewPassword.getText().toString().trim();
                    String confirmPassword = etConfirmPassword.getText().toString().trim();

                    try {
                        if (isValid(oldPass, password, confirmPassword)) {

                            updateProfilePresenter.changePassword(CocoPreferences.getUserId(), oldPass, password);


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


    private boolean isValid(String oldPassword, String password, String confirmPassword) throws Exception {
        boolean validation_detials_flag = false;
        if (Util.isDeviceOnline(this)) {
            if (TextUtils.isEmpty(oldPassword)) {
                showCenteredToast(lyParent, this, getString(R.string.invalid_old_password), "");
                etOldPassword.requestFocus();
            } else if (TextUtils.isEmpty(password)) {
                showCenteredToast(lyParent, this, getString(R.string.invalid_new_password), "");
                etNewPassword.requestFocus();
            } else if (TextUtils.isEmpty(confirmPassword)) {
                showCenteredToast(lyParent, this, getString(R.string.invalid_confirm_password), "");
                etConfirmPassword.requestFocus();
            } else if (!confirmPassword.equalsIgnoreCase(password)) {
                showCenteredToast(lyParent, this, getString(R.string.password_confirm_new_password), "");
                etConfirmPassword.requestFocus();

            } else {
                validation_detials_flag = true;
            }
        } else {
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
        showCenteredToast(lyParent, this, appErrorMessage, "");

    }

    @Override
    public void onUpdateProfileSuccess(UpdateProfileResponse updateProfileResponse) {
// do nothing
    }

    @Override
    public void changeProfileImages(ChangeProfileImageResponse changeProfileImageResponse) {
// do nothing
    }

    @Override
    public void changePassword(ChanePasswordResponse chanePasswordResponse) {
        if (!TextUtils.isEmpty(chanePasswordResponse.getmStatus()) && ("1".equalsIgnoreCase(chanePasswordResponse.getmStatus()))) {

            //showCenteredToast(lyParent, this, chanePasswordResponse.getMessage(), Constant.API_SUCCESS);
            finish();


        } else {
            showCenteredToast(lyParent, this, chanePasswordResponse.getMessage(), "");
        }
    }
}
