package com.ws.design.coco_ecommerce_ui_kit.address;

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

import static com.ws.design.coco_ecommerce_ui_kit.utility.Util.dismissProDialog;
import static com.ws.design.coco_ecommerce_ui_kit.utility.Util.isValidMobile;
import static com.ws.design.coco_ecommerce_ui_kit.utility.Util.showCenteredToast;
import static com.ws.design.coco_ecommerce_ui_kit.utility.Util.showProDialog;

public class AddAddressActivity extends AppCompatActivity implements AddressListView , View.OnClickListener{
    TextView txtTitle, txt2;
    private AddressPresenter addressPresenter;
    private EditText txtPrimaryAddress;
    private EditText txtArea;
    private EditText txtCity;
    private EditText txtState;
    private EditText txtPhone;
    private EditText txtCountry;
    private ImageView imgBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_address);


        addressPresenter = new AddressPresenter(this);

        imgBack =  findViewById(R.id.imgBack);
        txtTitle = (TextView) findViewById(R.id.txtTitle);
        txtPrimaryAddress = findViewById(R.id.txtPrimaryAddress);
        txtArea = findViewById(R.id.txtArea);
        txtCity = findViewById(R.id.txtCity);
        txtState =  findViewById(R.id.txtState);
        txtPhone =  findViewById(R.id.txtPhone);
        txtCountry = findViewById(R.id.txtCountry);
        txt2 = (TextView) findViewById(R.id.txt2);
        txtTitle.setText("Add New Address");
        txt2.setVisibility(View.VISIBLE);
        txt2.setOnClickListener(this);
        imgBack.setOnClickListener(this);
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
        showCenteredToast(this, appErrorMessage);
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
            showCenteredToast(this,addUpdateAddressResponse.getMessage());

        }else {
            showCenteredToast(this,addUpdateAddressResponse.getMessage());
        }
    }

    private boolean isValid(String primaryAddress, String area, String city, String state, String country, String phone) throws Exception {
        boolean validation_detials_flag = false;
        if (Util.isDeviceOnline(this)) {
            if (TextUtils.isEmpty(primaryAddress)) {
                showCenteredToast(this, getString(R.string.primary_address));
                txtPrimaryAddress.requestFocus();
            } else if (TextUtils.isEmpty(area)) {
                showCenteredToast(this, getString(R.string.area));
                txtArea.requestFocus();
            } else if (TextUtils.isEmpty(city)) {
                showCenteredToast(this, getString(R.string.city));
                txtCity.requestFocus();
            }
            else if (TextUtils.isEmpty(state)) {
                showCenteredToast(this, getString(R.string.state));
                txtState.requestFocus();
            }
            else if (TextUtils.isEmpty(country)) {
                showCenteredToast(this, getString(R.string.country));
                txtCountry.requestFocus();
            }
            else if (TextUtils.isEmpty(phone)) {
                showCenteredToast(this, getString(R.string.phone));
                txtPhone.requestFocus();
            }else if (!isValidMobile(phone)) {
                showCenteredToast(this, getString(R.string.mobile_number));
                txtPhone.requestFocus();
            }else {
                validation_detials_flag = true;
            }
        } else {
            showCenteredToast(this, getString(R.string.network_connection));
        }
        return validation_detials_flag;
    }


    @Override
    public void onClick(View view) {

        try{
            int vId = view.getId();
            switch (vId){
                case R.id.txt2:
                    String primaryAddress = txtPrimaryAddress.getText().toString().trim();
                    String area = txtArea.getText().toString().trim();
                    String city = txtCity.getText().toString().trim();
                    String state = txtState.getText().toString().trim();
                    String country = txtCountry.getText().toString().trim();
                    String phone = txtPhone.getText().toString().trim();

                    if (isValid(primaryAddress,area,city,state,country,phone)) {
                        addressPresenter.addAddress(CocoPreferences.getFirstName() + " " + CocoPreferences.getLastName(), CocoPreferences.getUserId(), primaryAddress,area,city,state,country, phone,"");
                    }

                    break;
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
