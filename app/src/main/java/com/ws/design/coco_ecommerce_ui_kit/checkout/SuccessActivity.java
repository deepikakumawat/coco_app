package com.ws.design.coco_ecommerce_ui_kit.checkout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static com.ws.design.coco_ecommerce_ui_kit.utility.Util.dismissProDialog;
import static com.ws.design.coco_ecommerce_ui_kit.utility.Util.showCenteredToast;
import static com.ws.design.coco_ecommerce_ui_kit.utility.Util.showProDialog;

public class SuccessActivity extends AppCompatActivity implements CheckoutView, View.OnClickListener, PaymentResultListener {


    private String totalPrice;
    private TextView txtTotalPrice;
    private ImageView imgOrderStatus;
    private String orderStatus;
    private TextView txtTitle;
    private TextView txtGoToHome;
    private TextView txtRetry;
    private String totalRazorPrice;
    private CheckoutPresenter checkoutPresenter;
    private AddressListResponse.AddressData addressData = null;
    private TextView txtOrderStatus;
    private TextView txtDateTime;
    private String orderId;
    private TextView txtOrderId;
    private ConstraintLayout clParent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_success);

        checkoutPresenter = new CheckoutPresenter(this);


        Intent intent = getIntent();
        if (intent != null) {
            totalPrice = intent.getStringExtra("totalPrice");
            orderStatus = intent.getStringExtra("orderStatus");
            orderId = intent.getStringExtra("orderId");
            addressData = (AddressListResponse.AddressData) intent.getSerializableExtra("addressData");


            try {
                totalRazorPrice = totalPrice + "00";
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

        clParent = findViewById(R.id.clParent);
        txtOrderId = findViewById(R.id.txtOrderId);
        txtDateTime = findViewById(R.id.txtDateTime);
        txtOrderStatus = findViewById(R.id.txtOrderStatus);
        txtRetry = findViewById(R.id.txtRetry);
        txtGoToHome = findViewById(R.id.txtGoToHome);
        imgOrderStatus = findViewById(R.id.imgOrderStatus);
        txtTotalPrice = findViewById(R.id.txtTotalPrice);
        txtTitle = findViewById(R.id.txtTitle);
        txtTitle.setText("Order Status");
        txtRetry.setOnClickListener(this);
        txtGoToHome.setOnClickListener(this);


        txtTotalPrice.setText(!TextUtils.isEmpty(totalPrice) ? getString(R.string.Rs) + totalPrice : "-");

        if (!TextUtils.isEmpty(orderStatus)) {
            if (orderStatus.equalsIgnoreCase(Constant.ORDER_SUCCESS)) {
                paymentSuccess();

            } else {
                paymentFailed();

            }
        } else {
            paymentFailed();
        }

        if (!TextUtils.isEmpty(orderId)) {
            txtOrderId.setVisibility(View.VISIBLE);

            txtOrderId.setText(getString(R.string.orderid) + orderId);
        } else {
            txtOrderId.setVisibility(View.GONE);

        }


        Date currentTime = Calendar.getInstance().getTime();
        SimpleDateFormat sdf = new SimpleDateFormat(Constant.DATE_TIME_FORMAT);
        String currentDateandTime = sdf.format(currentTime);
        txtDateTime.setText(getString(R.string.date_time) + currentDateandTime);

    }

    private void paymentSuccess() {
        txtRetry.setVisibility(View.GONE);
        txtGoToHome.setVisibility(View.VISIBLE);
        txtGoToHome.setText(getString(R.string.shop_more));
        imgOrderStatus.setImageResource(R.drawable.payment_sucess);
        txtOrderStatus.setText("Thank your order was successfully placed.");
    }

    private void paymentFailed() {
        txtRetry.setVisibility(View.VISIBLE);
        txtGoToHome.setVisibility(View.VISIBLE);
        txtGoToHome.setText(getString(R.string.go_to_home));
        imgOrderStatus.setImageResource(R.drawable.payment_fail);
        txtOrderStatus.setText("Oops! your order was not placed.");

    }


    @Override
    public void onClick(View view) {
        try {
            int vId = view.getId();
            switch (vId) {
                case R.id.txtGoToHome:
                    Intent intent = new Intent(SuccessActivity.this, DrawerActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    break;
                case R.id.txtRetry:
//                    startPayment();
                    finish();
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
        showCenteredToast(clParent,this, appErrorMessage);
       /* if (addressData == null) {
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
        }*/

        if (TextUtils.isEmpty(orderStatus)) {
            paymentFailed();
        }

    }

    @Override
    public void getAddressList(AddressListResponse addressListResponse) {
        // No use here
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
                showCenteredToast(clParent,this, getString(R.string.network_connection));

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


//            showCenteredToast(this,"Payment failed: " + code + " " + response);


            paymentFailed();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void getCheckoutPayment(CheckoutPaymentResponse checkoutPaymentResponse) {
        if (!TextUtils.isEmpty(checkoutPaymentResponse.getmStatus()) && ("1".equalsIgnoreCase(checkoutPaymentResponse.getmStatus()))) {
//            showCenteredToast(this,checkoutPaymentResponse.getMessage());

           /* Intent intent = new Intent(CheckoutActivity.this, DrawerActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);*/

            orderStatus = Constant.ORDER_SUCCESS;

            paymentSuccess();

        } else {
            showCenteredToast(clParent,this, checkoutPaymentResponse.getMessage());
        }
    }
}
