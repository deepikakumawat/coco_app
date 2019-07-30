package com.ws.design.coco_ecommerce_ui_kit.address;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.wolfsoft2.coco_ecommerce_ui_kit.R;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.ws.design.coco_ecommerce_ui_kit.DrawerActivity;
import com.ws.design.coco_ecommerce_ui_kit.login.LoginActivity;
import com.ws.design.coco_ecommerce_ui_kit.shared_preference.CocoPreferences;
import com.ws.design.coco_ecommerce_ui_kit.utility.Constant;
import com.ws.design.coco_ecommerce_ui_kit.utility.Util;

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
    private ImageView imgBack;
    private LinearLayout linear;
    private static final int ADD_ADDRESS_ACTION = 101;
    int selectedValue = 0;
    private String screen ="";
    private RelativeLayout ryParent;
    private ShimmerFrameLayout mShimmerViewContainer;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_list);

        addressPresenter = new AddressPresenter(this);

        Intent intent = getIntent();
        if (intent != null) {
            selectedValue = intent.getIntExtra("selectedValue", 0);

            if (intent.hasExtra("screen")) {
                screen = intent.getStringExtra("screen");

            }


        }

        init();

    }

    private void init() {
        mShimmerViewContainer = findViewById(R.id.shimmer_view_container);
        ryParent = findViewById(R.id.ryParent);
        title = findViewById(R.id.title);
        rvMyAddress = findViewById(R.id.rvMyOrder);
        imgBack = findViewById(R.id.imgBack);
        linear = findViewById(R.id.linear);
        imgBack.setOnClickListener(this);
        linear.setOnClickListener(this);
        title.setText("Address List");

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvMyAddress.setLayoutManager(layoutManager);

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (addressDataArrayList.isEmpty()) {
            if (Util.isDeviceOnline(this)) {

                if (!TextUtils.isEmpty(CocoPreferences.getUserId())) {
                    addressPresenter.addressList(CocoPreferences.getUserId());

                } else {
                    Intent intent = new Intent(AddressListActivity.this, LoginActivity.class);
                    startActivity(intent);
                }


            } else {
                showCenteredToast(ryParent,this, getString(R.string.network_connection),"");

            }

        }
    }

    @Override
    public void showWait() {
        mShimmerViewContainer.setVisibility(View.VISIBLE);
        mShimmerViewContainer.startShimmerAnimation();
    }

    @Override
    public void removeWait() {
        mShimmerViewContainer.stopShimmerAnimation();
        mShimmerViewContainer.setVisibility(View.GONE);
    }

    @Override
    public void onFailure(String appErrorMessage) {
        showCenteredToast(ryParent,this, appErrorMessage,"");
    }

    @Override
    public void getAddressList(AddressListResponse addressListResponse) {
        if (addressListResponse != null) {
            if (!addressListResponse.getmAddressData().isEmpty()) {
                addressDataArrayList.clear();

                ArrayList<AddressListResponse.AddressData> dataArrayList = new ArrayList<>();

                for (int i = 0; i < addressListResponse.getmAddressData().size(); i++) {

                    if (i == selectedValue) {
                        addressListResponse.getmAddressData().get(i).setmSelecetdAddress(true);

                    } else {
                        addressListResponse.getmAddressData().get(i).setmSelecetdAddress(false);

                    }

                    if (!TextUtils.isEmpty(screen) && screen.equalsIgnoreCase("Checkout")) {
                        addressListResponse.getmAddressData().get(i).setDeleteButtonVisible(false);

                    }else{
                        addressListResponse.getmAddressData().get(i).setDeleteButtonVisible(true);
                    }

                    dataArrayList.add(addressListResponse.getmAddressData().get(i));
                }

                addressDataArrayList.addAll(dataArrayList);

                addressAdapter = new AddressListAdapter(this, addressDataArrayList,  AddressListActivity.this);
                rvMyAddress.setAdapter(addressAdapter);
            }
        }

    }


    @Override
    public void deleteAddress(DeleteAddressResponse deleteAddressResponse) {
        if (!TextUtils.isEmpty(deleteAddressResponse.getmStatus()) && ("1".equalsIgnoreCase(deleteAddressResponse.getmStatus()))) {
            showCenteredToast(ryParent,this, deleteAddressResponse.getMessage(), Constant.API_SUCCESS);
            if (addressAdapter != null) {
                addressDataArrayList.remove(deletedPosition);
                addressAdapter.notifyDataSetChanged();
            }
        } else {
            showCenteredToast(ryParent,this, deleteAddressResponse.getMessage(),"");
        }
    }

    @Override
    public void addUpdateAddress(AddUpdateAddressResponse addUpdateAddressResponse) {
        // do nothing
    }


    @Override
    public void onClick(View view) {

        try {
            int vId = view.getId();
            switch (vId) {
                case R.id.btnDelete:
                    AddressListResponse.AddressData addressData = ((AddressListResponse.AddressData) view.getTag());
                    deletedPosition = (int) view.getTag(R.id.btnDelete);
                    if (addressData != null) {
                        if (Util.isDeviceOnline(this)) {
                            addressPresenter.deteleAddress(addressData.getmId());
                        } else {
                            showCenteredToast(ryParent,this, getString(R.string.network_connection),"");

                        }
                    }
                    break;
                case R.id.imgBack:
                    finish();
                    break;
                case R.id.linear:
                    if (!TextUtils.isEmpty(CocoPreferences.getUserId())) {
                        Intent intent = new Intent(AddressListActivity.this, AddAddressActivity.class);
                        startActivityForResult(intent, ADD_ADDRESS_ACTION);
                    } else {
                        Intent intent = new Intent(AddressListActivity.this, LoginActivity.class);
                        startActivity(intent);
                    }

                    break;
                case R.id.lyAddress:
                    addressData = ((AddressListResponse.AddressData) view.getTag());
                    selectedValue = ((int) view.getTag(R.id.lyAddress));

                    if (addressData != null) {

                        addressData.setmSelecetdAddress(true);
                        if (addressAdapter != null) {
                            addressAdapter.notifyDataSetChanged();
                        }

                        Intent data = new Intent();
                        data.putExtra("addressData", addressData);
                        data.putExtra("selectedValue", selectedValue);
                        setResult(Activity.RESULT_OK, data);
                        finish();

                    }
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_ADDRESS_ACTION) {
            if (resultCode == Activity.RESULT_OK) {

                if (Util.isDeviceOnline(this)) {

                    if (!TextUtils.isEmpty(CocoPreferences.getUserId())) {
                        addressPresenter.addressList(CocoPreferences.getUserId());

                    } else {

                        Intent intent = new Intent(AddressListActivity.this, LoginActivity.class);
                        startActivity(intent);
                    }


                } else {
                    showCenteredToast(ryParent,this, getString(R.string.network_connection),"");

                }
            }
        }
    }
}
