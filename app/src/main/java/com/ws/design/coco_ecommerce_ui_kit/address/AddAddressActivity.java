package com.ws.design.coco_ecommerce_ui_kit.address;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.wolfsoft2.coco_ecommerce_ui_kit.R;
import com.ws.design.coco_ecommerce_ui_kit.shared_preference.CocoPreferences;
import com.ws.design.coco_ecommerce_ui_kit.utility.Constant;
import com.ws.design.coco_ecommerce_ui_kit.utility.Util;

import java.io.Serializable;

import static com.ws.design.coco_ecommerce_ui_kit.utility.Util.dismissProDialog;
import static com.ws.design.coco_ecommerce_ui_kit.utility.Util.isValidMobile;
import static com.ws.design.coco_ecommerce_ui_kit.utility.Util.showCenteredToast;
import static com.ws.design.coco_ecommerce_ui_kit.utility.Util.showProDialog;

public class AddAddressActivity extends AppCompatActivity implements AddressListView, View.OnClickListener {
    TextView txtTitle, txt2;
    private AddressPresenter addressPresenter;
    private EditText txtPrimaryAddress;
    private EditText txtArea;
    private EditText txtCity;
    private EditText txtState;
    private EditText txtPhone;
    private EditText txtCountry;
    private ImageView imgBack;
    private TextView txtLandmark;
    private TextView txtZipcode;
    private LinearLayout lyParent;
    private AddressListResponse.AddressData addressData;
    private String id = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_address);
        addressPresenter = new AddressPresenter(this);

        Intent intent = getIntent();
        if (intent != null) {
            Bundle bundle = intent.getBundleExtra("addressData");
            if (bundle != null) {
                if (bundle.containsKey("addressData")) {
                    addressData = (AddressListResponse.AddressData) bundle.getSerializable("addressData");

                }
            }
        }

        init();
        setPreviousData();
    }


    private void init() {
        lyParent = findViewById(R.id.lyParent);
        imgBack = findViewById(R.id.imgBack);
        txtTitle = findViewById(R.id.txtTitle);
        txtPrimaryAddress = findViewById(R.id.txtPrimaryAddress);
        txtArea = findViewById(R.id.txtArea);
        txtLandmark = findViewById(R.id.txtLandmark);
        txtCity = findViewById(R.id.txtCity);
        txtState = findViewById(R.id.txtState);
        txtPhone = findViewById(R.id.txtPhone);
        txtCountry = findViewById(R.id.txtCountry);
        txtZipcode = findViewById(R.id.txtZipcode);
        txt2 = findViewById(R.id.txt2);
        txtTitle.setText("Add New Address");
        txt2.setVisibility(View.VISIBLE);
        txt2.setOnClickListener(this);
        imgBack.setOnClickListener(this);
    }

    private void setPreviousData() {
        if (addressData != null) {
            id = addressData.getmId();

            txtPrimaryAddress.setText(addressData.getmAddress1());
            txtArea.setText(addressData.getmAddress2());
            txtLandmark.setText(addressData.getmLandmark());
            txtCity.setText(addressData.getmCity());
            txtState.setText(addressData.getmState());
            txtPhone.setText(addressData.getmPhone());
            txtCountry.setText(addressData.getmCountry());
            txtZipcode.setText(addressData.getmZipcode());

        }
    }


    @Override
    public void onClick(View view) {

        try {
            int vId = view.getId();
            switch (vId) {
                case R.id.txt2:
                    String primaryAddress = txtPrimaryAddress.getText().toString().trim();
                    String area = txtArea.getText().toString().trim();
                    String city = txtCity.getText().toString().trim();
                    String state = txtState.getText().toString().trim();
                    String country = txtCountry.getText().toString().trim();
                    String phone = txtPhone.getText().toString().trim();
                    String landmark = txtLandmark.getText().toString().trim();
                    String zipcode = txtZipcode.getText().toString().trim();

                    if (isValid(primaryAddress, area, landmark, city, state, country, phone, zipcode)) {
                        addressPresenter.addAddress(CocoPreferences.getFirstName() + " " + CocoPreferences.getLastName(),
                                CocoPreferences.getUserId(),
                                primaryAddress,
                                area,
                                landmark,
                                city,
                                state,
                                country,
                                phone,
                                id,
                                zipcode,
                                "1");
                    }

                    break;
                case R.id.imgBack:
                    finish();
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean isValid(String primaryAddress, String area, String landmark, String city, String state, String country, String phone, String zipcode) throws Exception {
        boolean validation_detials_flag = false;
        if (Util.isDeviceOnline(this)) {
            if (TextUtils.isEmpty(primaryAddress)) {
                showCenteredToast(lyParent, this, getString(R.string.primary_address), "");
                txtPrimaryAddress.requestFocus();
            } else if (TextUtils.isEmpty(area)) {
                showCenteredToast(lyParent, this, getString(R.string.area), "");
                txtArea.requestFocus();
            } else if (TextUtils.isEmpty(landmark)) {
                showCenteredToast(lyParent, this, getString(R.string.landmark), "");
                txtLandmark.requestFocus();
            } else if (TextUtils.isEmpty(city)) {
                showCenteredToast(lyParent, this, getString(R.string.city), "");
                txtCity.requestFocus();
            } else if (TextUtils.isEmpty(state)) {
                showCenteredToast(lyParent, this, getString(R.string.state), "");
                txtState.requestFocus();
            } else if (TextUtils.isEmpty(country)) {
                showCenteredToast(lyParent, this, getString(R.string.country), "");
                txtCountry.requestFocus();
            } else if (TextUtils.isEmpty(zipcode)) {
                showCenteredToast(lyParent, this, getString(R.string.zipcode_blank), "");
                txtZipcode.requestFocus();
            } else if (TextUtils.isEmpty(phone)) {
                showCenteredToast(lyParent, this, getString(R.string.phone), "");
                txtPhone.requestFocus();
            } else if (!isValidMobile(phone)) {
                showCenteredToast(lyParent, this, getString(R.string.mobile_number), "");
                txtPhone.requestFocus();
            } else {
                validation_detials_flag = true;
            }
        } else {
            showCenteredToast(lyParent, this, getString(R.string.network_connection), "");
        }
        return validation_detials_flag;
    }


    @Override
    public void showWait() {
        showProDialog(this);
    }

    @Override
    public void removeWait() {
        dismissProDialog();
    }

    @Override
    public void onFailure(String appErrorMessage) {
        showCenteredToast(lyParent, this, appErrorMessage, "");
    }

    @Override
    public void getAddressList(AddressListResponse addressListResponse) {
//do nothing
    }

    @Override
    public void deleteAddress(DeleteAddressResponse deleteAddressResponse) {
// do nothing
    }

    @Override
    public void addUpdateAddress(AddUpdateAddressResponse addUpdateAddressResponse) {
        if (!TextUtils.isEmpty(addUpdateAddressResponse.getmStatus()) && ("1".equalsIgnoreCase(addUpdateAddressResponse.getmStatus()))) {
            showCenteredToast(lyParent, this, addUpdateAddressResponse.getMessage(), Constant.API_SUCCESS);

            Intent data = new Intent();
            setResult(Activity.RESULT_OK, data);
            finish();


        } else {
            showCenteredToast(lyParent, this, addUpdateAddressResponse.getMessage(), "");
        }
    }



}
