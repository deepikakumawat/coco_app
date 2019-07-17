package com.ws.design.coco_ecommerce_ui_kit.checkout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.wolfsoft2.coco_ecommerce_ui_kit.R;
import com.ws.design.coco_ecommerce_ui_kit.address.AddressListActivity;
import com.ws.design.coco_ecommerce_ui_kit.address.AddressListAdapter;
import com.ws.design.coco_ecommerce_ui_kit.address.AddressListResponse;
import com.ws.design.coco_ecommerce_ui_kit.my_cart.CartListResponse;
import com.ws.design.coco_ecommerce_ui_kit.my_cart.CartPresenter;
import com.ws.design.coco_ecommerce_ui_kit.my_cart.CartView;
import com.ws.design.coco_ecommerce_ui_kit.my_cart.EmptyCartResponse;
import com.ws.design.coco_ecommerce_ui_kit.my_cart.RemoveCartByCrossResponse;
import com.ws.design.coco_ecommerce_ui_kit.my_cart.RemoveCartOneByOneResponse;
import com.ws.design.coco_ecommerce_ui_kit.payment_gateway.RazorPaymentActivity;
import com.ws.design.coco_ecommerce_ui_kit.shared_preference.CocoPreferences;
import com.ws.design.coco_ecommerce_ui_kit.utility.Util;

import java.util.ArrayList;

import static com.ws.design.coco_ecommerce_ui_kit.utility.Util.dismissProDialog;
import static com.ws.design.coco_ecommerce_ui_kit.utility.Util.showCenteredToast;
import static com.ws.design.coco_ecommerce_ui_kit.utility.Util.showProDialog;

public class CheckoutActivity extends AppCompatActivity implements CheckoutView, View.OnClickListener {

    //    private RecyclerView recyclerView;

//    private ArrayList<CocoCartModel2> navigationModelClasses1;
//    private RecyclerView recyclerView1;
//    private RecycleAdapterCocoCart1 bAdapter;

    RadioButton button1, button2;
    LinearLayout radio1, radio2;


    private RecyclerView recyclerView2;
    //    private RecycleAdapterCocoCart2 cAdapter;
    private CheckoutListAdapter checkoutListAdapter;

    String rupees[] = {"$329", "$450"};
    String phoneName[] = {"Iphone X | 128 GB, Black | 6 GB ram |\n" +
            "12000 mAh battry", "Mi Mix 2 (Black, 128 GB) 6GB Ram \n" +
            "with 5000 mAh…"};
    Integer phoneImage[] = {R.drawable.mobile1, R.drawable.mobile2};


    String deliverType[] = {"Express Delivery", "Normal Delivery"};
    String noOfDays[] = {"1 day Delivery", "Upto 5 Business Days"};
    String amount[] = {"+$9.00", ""};


    String paymentType[] = {"Credit / Debit Card", "Cash On Delivery", "PAYTM", "Google Wallet"};
    Integer logoImage[] = {R.drawable.credit, R.drawable.cash, R.drawable.paytm, R.drawable.googlewallet};

    private RecyclerView rvCart;
    private ArrayList<CartListResponse.ProductData> productDataArrayList = new ArrayList<>();
    private TextView txtEmptyCart;
    private TextView txtConfirmPlaceOrder;
    private TextView txtTitle;
    private int removeOnByOnePostion = -1;
    private int removeCorssPostion = -1;
    private ImageView imgBack;
    private TextView txtAddress1;
    private TextView txtAddress2;
    private TextView txtLandmark;
    private TextView txtCity;
    private TextView txtState;
    private TextView txtCountry;
    private TextView txtZipcode;
    private TextView txtChange;
    private static final int ADDRESSLIST_ACTION = 101;


    private CheckoutPresenter checkoutPresenter;
    AddressListResponse.AddressData addressData = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        checkoutPresenter = new CheckoutPresenter(this);

        Intent intent = getIntent();
        if (intent != null) {
            productDataArrayList = (ArrayList<CartListResponse.ProductData>) intent.getSerializableExtra("cartList");


        }

        checkoutPresenter.addressList(CocoPreferences.getUserId());


        rvCart = findViewById(R.id.rvCart);
        imgBack = findViewById(R.id.imgBack);
        txtConfirmPlaceOrder = findViewById(R.id.txtConfirmPlaceOrder);
        txtEmptyCart = findViewById(R.id.txtEmptyCart);
        txtTitle = findViewById(R.id.txtTitle);
        txtChange = findViewById(R.id.txtChange);
        txtEmptyCart.setOnClickListener(this);
        txtConfirmPlaceOrder.setOnClickListener(this);
        txtChange.setOnClickListener(this);
        txtTitle.setText("Checkout");

        txtAddress1 = findViewById(R.id.txtAddress1);
        txtAddress2 = findViewById(R.id.txtAddress2);
        txtLandmark = findViewById(R.id.txtLandmark);
        txtCity = findViewById(R.id.txtCity);
        txtState = findViewById(R.id.txtState);
        txtCountry = findViewById(R.id.txtCountry);
        txtZipcode = findViewById(R.id.txtZipcode);


        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvCart.setLayoutManager(layoutManager);

        imgBack.setOnClickListener(this);


    /*    navigationModelClasses = new ArrayList<>();
        for (int i = 0; i < rupees.length; i++) {
            CocoCartModel1 beanClassForRecyclerView_contacts = new CocoCartModel1(phoneName[i], rupees[i], phoneImage[i]);

            navigationModelClasses.add(beanClassForRecyclerView_contacts);
        }
        mAdapter = new RecycleAdapterCocoCart0(CheckoutActivity.this, navigationModelClasses);*/

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(CheckoutActivity.this);
      /*  recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
*/

        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        radio1 = findViewById(R.id.radio1);
        radio2 = findViewById(R.id.radio2);

        radio1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button1.isSelected();
                button2.setChecked(false);
                button1.setChecked(true);
                button1.setEnabled(false);

            }
        });

        radio2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button2.isSelected();
                button1.setChecked(false);
                button2.setChecked(true);
                button2.setEnabled(false);

            }
        });


//
//        recyclerView1 = (RecyclerView)findViewById(R.id.recycler2);
//
//        navigationModelClasses1 = new ArrayList<>();
//
//
//
//        for (int i = 0; i < deliverType.length; i++) {
//            CocoCartModel2 beanClassForRecyclerView_contacts = new CocoCartModel2(deliverType[i],noOfDays[i],amount[i]);
//
//            navigationModelClasses1.add(beanClassForRecyclerView_contacts);
//        }
//
//
//        bAdapter = new RecycleAdapterCocoCart1(CheckoutActivity.this,navigationModelClasses1);
//
//        RecyclerView.LayoutManager bLayoutManager = new LinearLayoutManager(CheckoutActivity.this);
//        recyclerView1.setLayoutManager(bLayoutManager);
//        recyclerView1.setItemAnimator(new DefaultItemAnimator());
//        recyclerView1.setAdapter(bAdapter);


     /*   recyclerView2 = (RecyclerView) findViewById(R.id.recycler3);
        navigationModelClasses2 = new ArrayList<>();
        for (int i = 0; i < paymentType.length; i++) {
            CocoCartModel3 beanClassForRecyclerView_contacts = new CocoCartModel3(paymentType[i], logoImage[i]);

            navigationModelClasses2.add(beanClassForRecyclerView_contacts);
        }*/
      /*  cAdapter = new RecycleAdapterCocoCart2(CheckoutActivity.this,navigationModelClasses2);

        RecyclerView.LayoutManager cLayoutManager = new LinearLayoutManager(CheckoutActivity.this);
        recyclerView2.setLayoutManager(cLayoutManager);
        recyclerView2.setItemAnimator(new DefaultItemAnimator());
        recyclerView2.setAdapter(cAdapter);

*/

        if (!productDataArrayList.isEmpty()) {

            checkoutListAdapter = new CheckoutListAdapter(this, productDataArrayList, CheckoutActivity.this);
            rvCart.setAdapter(checkoutListAdapter);
        }

    }

    @Override
    public void onClick(View view) {
        try {
            int vId = view.getId();
            switch (vId) {
                case R.id.imgBack:
                    finish();
                    break;
                case R.id.txtConfirmPlaceOrder:
                    Intent intent = new Intent(CheckoutActivity.this, RazorPaymentActivity.class);
                    intent.putExtra("addressData",addressData);
                    startActivity(intent);
                    break;
                case R.id.txtChange:
                    intent = new Intent(CheckoutActivity.this, AddressListActivity.class);
                    startActivityForResult(intent, ADDRESSLIST_ACTION);
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

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
                addressData = addressListResponse.getmAddressData().get(0);
                setAddress(addressData);
            }
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADDRESSLIST_ACTION) {
            if (resultCode == Activity.RESULT_OK) {
                if (data != null) {
                    addressData = (AddressListResponse.AddressData) data.getSerializableExtra("addressData");
                    setAddress(addressData);
                }

            }
        }
    }

    private void setAddress(AddressListResponse.AddressData addressData) {
        if (addressData != null) {
            txtAddress1.setText(TextUtils.isEmpty(addressData.getmAddress1()) ? "-" : addressData.getmAddress1());
            txtAddress2.setText(TextUtils.isEmpty(addressData.getmAddress2()) ? "-" : addressData.getmAddress2());
            txtLandmark.setText(TextUtils.isEmpty(addressData.getmLandmark()) ? "-" : addressData.getmLandmark());
            txtCity.setText(TextUtils.isEmpty(addressData.getmCity()) ? "-" : addressData.getmCity());
            txtState.setText(TextUtils.isEmpty(addressData.getmState()) ? "-" : addressData.getmState());
            txtCountry.setText(TextUtils.isEmpty(addressData.getmCountry()) ? "-" : addressData.getmCountry());
            txtZipcode.setText(TextUtils.isEmpty(addressData.getmZipcode()) ? "-" : addressData.getmZipcode());

        }
    }

}
