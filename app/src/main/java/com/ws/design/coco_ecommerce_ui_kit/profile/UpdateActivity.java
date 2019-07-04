package com.ws.design.coco_ecommerce_ui_kit.profile;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wolfsoft2.coco_ecommerce_ui_kit.R;
import com.ws.design.coco_ecommerce_ui_kit.shared_preference.CocoPreferences;
import com.ws.design.coco_ecommerce_ui_kit.utility.Util;

import static com.ws.design.coco_ecommerce_ui_kit.utility.Util.showCenteredToast;

public class UpdateActivity extends AppCompatActivity implements UpdateView , View.OnClickListener{


    private UpdateProfilePresenter updateProfilePresenter;
    private EditText etEmail;
    private TextView btnUpdate;
    private EditText etLname;
    private EditText etFname;
    private EditText etConfirmPassword;
    private EditText etPassword;
    private EditText etPhone;

    TextView title,edit,test,edit_txt;
    private ImageView imgBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        updateProfilePresenter = new UpdateProfilePresenter(this);
        setContentView(R.layout.activity_update_profile);

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
                showCenteredToast(this, getString(R.string.first_name_validation_message));
                etFname.requestFocus();
            } else if (TextUtils.isEmpty(lName)) {
                showCenteredToast(this, getString(R.string.last_name_validation_message));
                etLname.requestFocus();
            } else if (TextUtils.isEmpty(phone)) {
                showCenteredToast(this, getString(R.string.invalid_mobile_number));
                etPhone.requestFocus();
            }  else {
                validation_detials_flag = true;
            }
        } else {
            showCenteredToast(this, getString(R.string.network_connection));
        }
        return validation_detials_flag;
    }


    private void init() {

        title = (TextView)findViewById(R.id.title);
        edit_txt = (TextView)findViewById(R.id.edit_txt);
        edit_txt.setVisibility(View.GONE);
        edit = (TextView)findViewById(R.id.edit);
        imgBack = (ImageView)findViewById(R.id.imgBack);
        edit.setVisibility(View.GONE);
        title.setVisibility(View.VISIBLE);
        title.setText("Profile");
        imgBack.setOnClickListener(this);


        etEmail = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);
        etConfirmPassword = (EditText) findViewById(R.id.etConfirmPassword);
        etFname = (EditText) findViewById(R.id.etFname);
        etLname = (EditText) findViewById(R.id.etLname);
        etPhone = (EditText) findViewById(R.id.etPhone);
        btnUpdate = (TextView) findViewById(R.id.btnUpdate);

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
        showCenteredToast(this, appErrorMessage);
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

            showCenteredToast(this,updateProfileResponse.getMessage());
        }else {
            showCenteredToast(this,updateProfileResponse.getMessage());
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
