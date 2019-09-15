package com.nav.richkart.profile;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nav.richkart.R;
import com.nav.richkart.shared_preference.CocoPreferences;
import com.nav.richkart.utility.Constant;
import com.nav.richkart.utility.Util;

import static com.nav.richkart.utility.Util.showCenteredToast;

public class ProfileActivity extends AppCompatActivity implements UpdateView , View.OnClickListener{


    private UpdateProfilePresenter updateProfilePresenter;
    private EditText etEmail;
    private Button btnUpdate;
    private EditText etLname;
    private EditText etFname;
    private EditText etConfirmPassword;
    private EditText etPassword;
    private EditText etPhone;

    TextView test;
    private ImageView imgBack;
    private RelativeLayout ryParent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);
        updateProfilePresenter = new UpdateProfilePresenter(this);

        init();

        btnUpdate.setOnClickListener(view -> {

            String phone = etPhone.getText().toString().trim();
            String fName = etFname.getText().toString().trim();
            String lName = etLname.getText().toString().trim();
            String password = etPassword.getText().toString().trim();

            try {
                if (isValid(fName, lName, phone)) {
                    updateProfilePresenter.doUpdateProfile(CocoPreferences.getUserId(),fName, lName, phone, password);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });


    }

    private boolean isValid(String fName, String lName, String phone) throws Exception {
        boolean validation_detials_flag = false;
        if (Util.isDeviceOnline(this)) {
            if (TextUtils.isEmpty(fName)) {
                showCenteredToast(ryParent,this, getString(R.string.first_name_validation_message),"");
                etFname.requestFocus();
            } else if (TextUtils.isEmpty(lName)) {
                showCenteredToast(ryParent,this, getString(R.string.last_name_validation_message),"");
                etLname.requestFocus();
            } else if (TextUtils.isEmpty(phone)) {
                showCenteredToast(ryParent,this, getString(R.string.invalid_mobile_number),"");
                etPhone.requestFocus();
            }  else {
                validation_detials_flag = true;
            }
        } else {
//            showCenteredToast(ryParent,this, getString(R.string.network_connection),"");
            Util.showNoInternetDialog(this);
        }
        return validation_detials_flag;
    }


    private void init() {

        ryParent = findViewById(R.id.ryParent);

        imgBack = (ImageView)findViewById(R.id.imgBack);
        imgBack.setOnClickListener(this);


        etEmail = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);
        etConfirmPassword = (EditText) findViewById(R.id.etConfirmPassword);
        etFname = (EditText) findViewById(R.id.etFname);
        etLname = (EditText) findViewById(R.id.etLname);
        etPhone = (EditText) findViewById(R.id.etPhone);
        btnUpdate =  findViewById(R.id.btnUpdate);

        etEmail.setText(CocoPreferences.getUserEmail());
        etFname.setText(CocoPreferences.getFirstName());
        etLname.setText(CocoPreferences.getLastName());
        etPhone.setText(CocoPreferences.getUserPhone());
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
        showCenteredToast(ryParent,this, appErrorMessage,"");
    }

    @Override
    public void onUpdateProfileSuccess(UpdateProfileResponse updateProfileResponse) {
        if (!TextUtils.isEmpty(updateProfileResponse.getmStatus()) && ("1".equalsIgnoreCase(updateProfileResponse.getmStatus()))) {

            CocoPreferences.setUserId(updateProfileResponse.getmLoginData().getmId());
            CocoPreferences.setUserEmail(updateProfileResponse.getmLoginData().getmEmail());
            CocoPreferences.setUserPhone(updateProfileResponse.getmLoginData().getmMobileNo());
            CocoPreferences.setFirstName(updateProfileResponse.getmLoginData().getmName());
            CocoPreferences.setLastName(updateProfileResponse.getmLoginData().getmLastName());
            CocoPreferences.savePreferencese();

            showCenteredToast(ryParent,this,updateProfileResponse.getMessage(), Constant.API_SUCCESS);
        }else {
            showCenteredToast(ryParent,this,updateProfileResponse.getMessage(),"");
        }

    }

    @Override
    public void changeProfileImages(ChangeProfileImageResponse changeProfileImageResponse) {
        // do nothing
    }

    @Override
    public void changePassword(ChanePasswordResponse chanePasswordResponse) {
        // do nothing
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
