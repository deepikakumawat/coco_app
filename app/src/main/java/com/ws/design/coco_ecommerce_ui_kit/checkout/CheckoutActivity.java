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
import android.widget.Toast;

import com.example.wolfsoft2.coco_ecommerce_ui_kit.R;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;
import com.ws.design.coco_ecommerce_ui_kit.DrawerActivity;
import com.ws.design.coco_ecommerce_ui_kit.address.AddAddressActivity;
import com.ws.design.coco_ecommerce_ui_kit.address.AddUpdateAddressResponse;
import com.ws.design.coco_ecommerce_ui_kit.address.AddressListActivity;
import com.ws.design.coco_ecommerce_ui_kit.address.AddressListResponse;
import com.ws.design.coco_ecommerce_ui_kit.my_cart.CartListResponse;
import com.ws.design.coco_ecommerce_ui_kit.shared_preference.CocoPreferences;
import com.ws.design.coco_ecommerce_ui_kit.utility.Util;

import org.json.JSONObject;

import java.util.ArrayList;

import static com.ws.design.coco_ecommerce_ui_kit.utility.Util.dismissProDialog;
import static com.ws.design.coco_ecommerce_ui_kit.utility.Util.showCenteredToast;
import static com.ws.design.coco_ecommerce_ui_kit.utility.Util.showProDialog;

public class CheckoutActivity extends AppCompatActivity implements CheckoutView, View.OnClickListener , PaymentResultListener {

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
    private TextView txtConfirmPlaceOrder;
    private TextView txtTitle;
    private ImageView imgBack;
    private TextView txtAddress1;
    private TextView txtAddress2;
    private TextView txtLandmark;
    private TextView txtCity;
    private TextView txtState;
    private TextView txtCountry;
    private TextView txtZipcode;
    private TextView txtChange;
    private TextView txtAddAddress;
    private static final int ADDRESSLIST_ACTION = 101;
    private static final int ADD_ADDRESS_ACTION = 104;


    private CheckoutPresenter checkoutPresenter;
    private AddressListResponse.AddressData addressData = null;
    private ArrayList<AddUpdateAddressResponse.AddUpdateAddressData> addUpdateAddressData = null;
    private String totalPrice;
    private TextView txtTotalPrice;
    private String totalRazorPrice;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        checkoutPresenter = new CheckoutPresenter(this);

        Intent intent = getIntent();
        if (intent != null) {
            productDataArrayList = (ArrayList<CartListResponse.ProductData>) intent.getSerializableExtra("cartList");
            totalPrice =  intent.getStringExtra("totalPrice");

            try {
                totalRazorPrice = totalPrice + "00";
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }

        }

        checkoutPresenter.addressList(CocoPreferences.getUserId());


        txtTotalPrice = findViewById(R.id.txtTotalPrice);
        rvCart = findViewById(R.id.rvCart);
        imgBack = findViewById(R.id.imgBack);
        txtConfirmPlaceOrder = findViewById(R.id.txtConfirmPlaceOrder);
        txtTitle = findViewById(R.id.txtTitle);
        txtChange = findViewById(R.id.txtChange);
        txtAddAddress = findViewById(R.id.txtAddAddress);
        txtConfirmPlaceOrder.setOnClickListener(this);
        txtAddAddress.setOnClickListener(this);
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

        txtTotalPrice.setText(!TextUtils.isEmpty(totalPrice) ? totalPrice : "-");


    }

    @Override
    public void onClick(View view) {
        Intent intent;
        try {
            int vId = view.getId();
            switch (vId) {
                case R.id.imgBack:
                    finish();
                    break;
                case R.id.txtConfirmPlaceOrder:

                    if (addressData != null) {
                       /* intent = new Intent(CheckoutActivity.this, RazorPaymentActivity.class);
                        intent.putExtra("addressData", addressData);
                        intent.putExtra("totalPrice", totalPrice);
                        startActivity(intent);*/

                       startPayment();

                    }else{
                        showCenteredToast(this,"You haven't added address. Please add address first");
                    }


                    break;
                case R.id.txtChange:
                    intent = new Intent(CheckoutActivity.this, AddressListActivity.class);
                    startActivityForResult(intent, ADDRESSLIST_ACTION);
                    break;
                case R.id.txtAddAddress:
                    intent = new Intent(CheckoutActivity.this, AddAddressActivity.class);
                    startActivityForResult(intent,ADD_ADDRESS_ACTION);
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
        if (addressData == null) {
            txtAddAddress.setVisibility(View.VISIBLE);

            txtChange.setVisibility(View.GONE);
            txtAddress2.setVisibility(View.GONE);
            txtLandmark.setVisibility(View.GONE);
            txtCity.setVisibility(View.GONE);
            txtState.setVisibility(View.GONE);
            txtCountry.setVisibility(View.GONE);
            txtZipcode.setVisibility(View.GONE);

            txtAddress1.setVisibility(View.VISIBLE);
            txtAddress1.setText("You haven't added address.");
        }
    }

    @Override
    public void getAddressList(AddressListResponse addressListResponse) {
        if (addressListResponse != null) {
            if (!addressListResponse.getmAddressData().isEmpty()) {
                addressData = addressListResponse.getmAddressData().get(0);
                if (addressData != null) {
                    setAddress(addressData);
                }
            } else {
                txtAddAddress.setVisibility(View.VISIBLE);

                txtChange.setVisibility(View.GONE);
                txtAddress2.setVisibility(View.GONE);
                txtLandmark.setVisibility(View.GONE);
                txtCity.setVisibility(View.GONE);
                txtState.setVisibility(View.GONE);
                txtCountry.setVisibility(View.GONE);
                txtZipcode.setVisibility(View.GONE);

                txtAddress1.setVisibility(View.VISIBLE);
                txtAddress1.setText("You haven't added address.");

            }
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        try {

            if (requestCode == ADDRESSLIST_ACTION) {
                if (resultCode == Activity.RESULT_OK) {
                    if (data != null) {
                        addressData = (AddressListResponse.AddressData) data.getSerializableExtra("addressData");

                        if (addressData != null) {
                            setAddress(addressData);
                        }
                    }

                }
            } else if (requestCode == ADD_ADDRESS_ACTION) {
                if (resultCode == Activity.RESULT_OK) {
                  /*  if (data != null) {

                        addUpdateAddressData = (ArrayList<AddUpdateAddressResponse.AddUpdateAddressData>) data.getSerializableExtra("addressData");
                        if (!addUpdateAddressData.isEmpty()) {

                            addressData.setmAddress1(addUpdateAddressData.get(0).getmAddress1());
                            addressData.setmAddress2(addUpdateAddressData.get(0).getmAddress2());
                            addressData.setmLandmark(addUpdateAddressData.get(0).getmLandmark());
                            addressData.setmCity(addUpdateAddressData.get(0).getmCity());
                            addressData.setmState(addUpdateAddressData.get(0).getmState());
                            addressData.setmCountry(addUpdateAddressData.get(0).getmCountry());
                            addressData.setmZipcode(addUpdateAddressData.get(0).getmZipcode());

                            if (addressData != null) {
                                setAddress(addressData);
                            }

                        }

                    }*/

                  checkoutPresenter.addressList(CocoPreferences.getUserId());

                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void setAddress(AddressListResponse.AddressData addressData) {
        txtAddAddress.setVisibility(View.GONE);

        txtChange.setVisibility(View.VISIBLE);
        txtAddress1.setVisibility(View.VISIBLE);
        txtAddress2.setVisibility(View.VISIBLE);
        txtLandmark.setVisibility(View.VISIBLE);
        txtCity.setVisibility(View.VISIBLE);
        txtState.setVisibility(View.VISIBLE);
        txtCountry.setVisibility(View.VISIBLE);
        txtZipcode.setVisibility(View.VISIBLE);


        txtAddress1.setText(TextUtils.isEmpty(addressData.getmAddress1()) ? "-" : addressData.getmAddress1());
        txtAddress2.setText(TextUtils.isEmpty(addressData.getmAddress2()) ? "-" : addressData.getmAddress2());
        txtLandmark.setText(TextUtils.isEmpty(addressData.getmLandmark()) ? "-" : addressData.getmLandmark());
        txtCity.setText(TextUtils.isEmpty(addressData.getmCity()) ? "-" : addressData.getmCity());
        txtState.setText(TextUtils.isEmpty(addressData.getmState()) ? "-" : addressData.getmState());
        txtCountry.setText(TextUtils.isEmpty(addressData.getmCountry()) ? "-" : addressData.getmCountry());
        txtZipcode.setText(TextUtils.isEmpty(addressData.getmZipcode()) ? "-" : addressData.getmZipcode());
    }


    // Payment Gatewayy
    public void startPayment() {
        /*
          You need to pass current activity in order to let Razorpay create CheckoutActivity
         */
        final Activity activity = this;

        final Checkout co = new Checkout();

        try {
            JSONObject options = new JSONObject();
            options.put("name", "Razorpay Corp");
            options.put("description", "Demoing Charges");
            //You can omit the image option to fetch the image from dashboard
            options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.png");
            options.put("currency", "INR");
            options.put("amount", totalRazorPrice);

            JSONObject preFill = new JSONObject();
            preFill.put("email", CocoPreferences.getUserEmail());
            preFill.put("contact", CocoPreferences.getUserPhone());

            options.put("prefill", preFill);

            co.open(activity, options);
        } catch (Exception e) {
            Toast.makeText(activity, "Error in payment: " + e.getMessage(), Toast.LENGTH_SHORT)
                    .show();
            e.printStackTrace();
        }
    }

    /**
     * The name of the function has to be
     * onPaymentSuccess
     * Wrap your code in try catch, as shown, to ensure that this method runs correctly
     */
    @SuppressWarnings("unused")
    @Override
    public void onPaymentSuccess(String razorpayPaymentID) {
        try {

/*

            Intent intent = new Intent(RazorPaymentActivity.this, DrawerBaseActivity.class);
            intent.putExtra("test", getFirstName);
            startActivity(intent);
*/

            Toast.makeText(getApplicationContext(), "Successfully payment", Toast.LENGTH_LONG).show();



            if (Util.isDeviceOnline(this)) {
                if (addressData != null) {
                    checkoutPresenter.getCheckoutPayment(CocoPreferences.getUserId(),
                            razorpayPaymentID,
                            "0",
                            "201600",
                            "1",
                            CocoPreferences.getFirstName(),
                            CocoPreferences.getLastName(),
                            CocoPreferences.getUserEmail(),
                            "gurudwara",
                            CocoPreferences.getUserPhone(),
                            "Jaipur",
                            "30252012",
                            "raj",
                            "jai",
                            CocoPreferences.getFirstName(),
                            CocoPreferences.getLastName(),
                            CocoPreferences.getUserEmail(),
                            "gurudwara",
                            CocoPreferences.getUserPhone(),
                            "Jaipur",
                            "30252012",
                            "raj",
                            "jai"
                    );
                }


            }else{
                showCenteredToast(this, getString(R.string.network_connection));

            }




//            Toast.makeText(this, "Payment Successful: " + razorpayPaymentID, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * The name of the function has to be
     * onPaymentError
     * Wrap your code in try catch, as shown, to ensure that this method runs correctly
     */
    @SuppressWarnings("unused")
    @Override
    public void onPaymentError(int code, String response) {
        try {


            showCenteredToast(this,"Payment failed: " + code + " " + response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




    @Override
    public void getCheckoutPayment(CheckoutPaymentResponse checkoutPaymentResponse) {
        if (!TextUtils.isEmpty(checkoutPaymentResponse.getmStatus()) && ("1".equalsIgnoreCase(checkoutPaymentResponse.getmStatus()))) {
            showCenteredToast(this,checkoutPaymentResponse.getMessage());

            Intent intent = new Intent(CheckoutActivity.this, DrawerActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }else {
            showCenteredToast(this,checkoutPaymentResponse.getMessage());
        }
    }
}
