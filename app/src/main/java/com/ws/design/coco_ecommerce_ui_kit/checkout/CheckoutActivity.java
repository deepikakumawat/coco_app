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
import com.ws.design.coco_ecommerce_ui_kit.utility.Constant;
import com.ws.design.coco_ecommerce_ui_kit.utility.Util;

import org.json.JSONObject;

import java.util.ArrayList;

import static com.ws.design.coco_ecommerce_ui_kit.utility.Util.dismissProDialog;
import static com.ws.design.coco_ecommerce_ui_kit.utility.Util.showCenteredToast;
import static com.ws.design.coco_ecommerce_ui_kit.utility.Util.showProDialog;

public class CheckoutActivity extends AppCompatActivity implements CheckoutView, View.OnClickListener, PaymentResultListener {

    RadioButton button1, button2;
    LinearLayout radio1, radio2;

    private CheckoutListAdapter checkoutListAdapter;
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
    private String orderStatus = "";
    private int selectedValue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        checkoutPresenter = new CheckoutPresenter(this);

        Intent intent = getIntent();
        if (intent != null) {
            productDataArrayList = (ArrayList<CartListResponse.ProductData>) intent.getSerializableExtra("cartList");
            totalPrice = intent.getStringExtra("totalPrice");

            try {
                totalRazorPrice = totalPrice + "00";
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }

        }

        if (Util.isDeviceOnline(this)) {
            checkoutPresenter.addressList(CocoPreferences.getUserId());
        } else {
            showCenteredToast(this, getString(R.string.network_connection));
        }

        init();


        if (!productDataArrayList.isEmpty()) {

            checkoutListAdapter = new CheckoutListAdapter(this, productDataArrayList, CheckoutActivity.this);
            rvCart.setAdapter(checkoutListAdapter);
        }

        txtTotalPrice.setText(!TextUtils.isEmpty(totalPrice) ? totalPrice : "-");


    }

    private void init() {
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

                        startPayment();

                    } else {
                        showCenteredToast(this, "You haven't added address. Please add address first");
                    }


                    break;
                case R.id.txtChange:
                    intent = new Intent(CheckoutActivity.this, AddressListActivity.class);
                    intent.putExtra("selectedValue", selectedValue);
                    intent.putExtra("screen", "Checkout");
                    startActivityForResult(intent, ADDRESSLIST_ACTION);
                    break;
                case R.id.txtAddAddress:
                    intent = new Intent(CheckoutActivity.this, AddAddressActivity.class);
                    startActivityForResult(intent, ADD_ADDRESS_ACTION);
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

        if (addressData!=null && TextUtils.isEmpty(orderStatus)) {
            Intent intent = new Intent(CheckoutActivity.this, SuccessActivity.class);
            intent.putExtra("totalPrice", totalPrice);
            intent.putExtra("orderStatus", Constant.ORDER_FAIL);
            intent.putExtra("addressData", addressData);

            startActivity(intent);
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
                        selectedValue = data.getIntExtra("selectedValue", 0);

                        if (addressData != null) {
                            setAddress(addressData);
                        }
                    }

                }
            } else if (requestCode == ADD_ADDRESS_ACTION) {
                if (resultCode == Activity.RESULT_OK) {

                    if (Util.isDeviceOnline(this)) {
                        checkoutPresenter.addressList(CocoPreferences.getUserId());
                    } else {
                        showCenteredToast(this, getString(R.string.network_connection));
                    }
                }
            }
        } catch (Exception e) {
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


            } else {
                showCenteredToast(this, getString(R.string.network_connection));

            }


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

            Intent intent = new Intent(CheckoutActivity.this, SuccessActivity.class);
            intent.putExtra("totalPrice", totalPrice);
            intent.putExtra("orderStatus", Constant.ORDER_FAIL);
            intent.putExtra("addressData", addressData);

            startActivity(intent);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void getCheckoutPayment(CheckoutPaymentResponse checkoutPaymentResponse) {
        if (!TextUtils.isEmpty(checkoutPaymentResponse.getmStatus()) && ("1".equalsIgnoreCase(checkoutPaymentResponse.getmStatus()))) {


            orderStatus = Constant.ORDER_SUCCESS;

            Intent intent = new Intent(CheckoutActivity.this, SuccessActivity.class);
            intent.putExtra("totalPrice", totalPrice);
            intent.putExtra("orderStatus", Constant.ORDER_SUCCESS);
            intent.putExtra("addressData", addressData);

            startActivity(intent);

        } else {
            showCenteredToast(this, checkoutPaymentResponse.getMessage());
        }
    }
}
