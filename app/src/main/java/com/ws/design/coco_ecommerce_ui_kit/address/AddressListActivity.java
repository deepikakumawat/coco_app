package com.ws.design.coco_ecommerce_ui_kit.address;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.wolfsoft2.coco_ecommerce_ui_kit.R;
import com.ws.design.coco_ecommerce_ui_kit.shared_preference.CocoPreferences;

import java.util.ArrayList;

import static com.ws.design.coco_ecommerce_ui_kit.utility.Util.dismissProDialog;
import static com.ws.design.coco_ecommerce_ui_kit.utility.Util.showCenteredToast;
import static com.ws.design.coco_ecommerce_ui_kit.utility.Util.showProDialog;

public class AddressListActivity extends AppCompatActivity implements AddressListView, View.OnClickListener {

    TextView title;
    LinearLayout linearLayout;
    AddressListAdapter addressAdapter;
    private RecyclerView rvMyAddress;
    private ArrayList<AddressListResponse.AddressData> addressDataArrayList = new ArrayList<>();
    private AddressPresenter addressPresenter;
    private int deletedPosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_list);

         addressPresenter = new AddressPresenter(this);

        title = (TextView) findViewById(R.id.title);
        linearLayout = (LinearLayout) findViewById(R.id.linear);
        rvMyAddress = (RecyclerView) findViewById(R.id.rvMyOrder);

        title.setText("Address List");
        linearLayout.setVisibility(View.GONE);


      addressPresenter.addressList(CocoPreferences.getUserId());

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvMyAddress.setLayoutManager(layoutManager);


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
        if (addressListResponse != null) {
            if (!addressListResponse.getmAddressData().isEmpty()) {
                addressDataArrayList.clear();
                addressDataArrayList.addAll(addressListResponse.getmAddressData());

                addressAdapter = new AddressListAdapter(this, addressDataArrayList, AddressListActivity.this);
                rvMyAddress.setAdapter(addressAdapter);
            }
        }

    }


    @Override
    public void deleteAddress(DeleteAddressResponse deleteAddressResponse) {
        if (!TextUtils.isEmpty(deleteAddressResponse.getmStatus()) && ("1".equalsIgnoreCase(deleteAddressResponse.getmStatus()))) {
            showCenteredToast(this,deleteAddressResponse.getMessage());
            if (addressAdapter != null) {
                addressDataArrayList.remove(deletedPosition);
                addressAdapter.notifyDataSetChanged();
            }
        }else {
            showCenteredToast(this,deleteAddressResponse.getMessage());
        }
    }

    @Override
    public void addUpdateAddress(AddUpdateAddressResponse addUpdateAddressResponse) {
        // do nothing
    }


    @Override
    public void onClick(View view) {

        try{
            int vId = view.getId();
            switch (vId){
                case R.id.btnDelete:
                    AddressListResponse.AddressData  addressData = ((AddressListResponse.AddressData) view.getTag());
                    deletedPosition=(int)view.getTag(R.id.btnDelete);

                    if (addressData != null) {

                        addressPresenter.deteleAddress(addressData.getmId());

                    }
                    break;
                    default:
                        break;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
