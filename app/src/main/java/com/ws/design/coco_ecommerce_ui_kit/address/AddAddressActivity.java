package com.ws.design.coco_ecommerce_ui_kit.address;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.wolfsoft2.coco_ecommerce_ui_kit.R;

import static com.ws.design.coco_ecommerce_ui_kit.utility.Util.dismissProDialog;
import static com.ws.design.coco_ecommerce_ui_kit.utility.Util.showCenteredToast;
import static com.ws.design.coco_ecommerce_ui_kit.utility.Util.showProDialog;

public class AddAddressActivity extends AppCompatActivity implements AddressListView {
    TextView txt1, txt2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_address);
        txt1 = (TextView) findViewById(R.id.txt1);
        txt2 = (TextView) findViewById(R.id.txt2);
        txt1.setText("Add New Address");
        txt2.setVisibility(View.VISIBLE);
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

    }
}
