package com.ws.design.coco_ecommerce_ui_kit.my_order_details;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.wolfsoft2.coco_ecommerce_ui_kit.R;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.ws.design.coco_ecommerce_ui_kit.utility.Util;

import java.util.ArrayList;

import static com.ws.design.coco_ecommerce_ui_kit.utility.Util.showCenteredToast;

public class MyOrderDetailsActivity extends AppCompatActivity implements OrderDetailsView, View.OnClickListener {

    TextView title;
    LinearLayout linearLayout;
    private MyOrderDetailsAdapter myOrderAdapter;
    private RecyclerView rvMyOrder;
    private ArrayList<OrderProduct> myOrderDataArrayList = new ArrayList<OrderProduct>();
    private ImageView imgBack;

    private OrderDetailsPresenter myOrderPresenter;

    private ShimmerFrameLayout mShimmerViewContainer;
    private TextView txtNoDataFound;
    private RelativeLayout ryParent;
    private String orderId;
    private TextView txtDeliveryLandmark;
    private TextView txtDeliveryState;
    private TextView txtOrderId;
    private TextView txtPrice;
    private TextView txtSenderName;
    private TextView txtSenderContact;
    private TextView txtSenderAddress;
    private TextView txtSenderEmail;
    private TextView txtReceiverEmail;
    private TextView txtReceiverName;
    private TextView txtDeliveryContact;
    private TextView txtDeliveryAddress;
    private TextView txtDeliveryPincode;
    private TextView txtDeliveryCity;
    private LinearLayout lyOrderDetails;
    private LinearLayout lyProductDetails;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order_details);

        Intent intent = getIntent();
        if (intent != null) {
            orderId = intent.getStringExtra("orderId");
        }

        myOrderPresenter = new OrderDetailsPresenter(this);


        lyOrderDetails = findViewById(R.id.lyOrderDetails);
        lyProductDetails = findViewById(R.id.lyProductDetails);

        txtOrderId = findViewById(R.id.txtOrderId);
        txtPrice = findViewById(R.id.txtPrice);

        txtSenderName = findViewById(R.id.txtSenderName);
        txtSenderContact = findViewById(R.id.txtSenderContact);
        txtSenderAddress = findViewById(R.id.txtSenderAddress);
        txtSenderEmail = findViewById(R.id.txtSenderEmail);

        txtReceiverName = findViewById(R.id.txtReceiverName);

        txtReceiverEmail = findViewById(R.id.txtReceiverEmail);

        txtDeliveryContact = findViewById(R.id.txtDeliveryContact);
        txtDeliveryAddress = findViewById(R.id.txtDeliveryAddress);
        txtDeliveryPincode = findViewById(R.id.txtDeliveryPincode);
        txtDeliveryCity = findViewById(R.id.txtDeliveryCity);
        txtDeliveryState = findViewById(R.id.txtDeliveryState);
        txtDeliveryLandmark = findViewById(R.id.txtDeliveryLandmark);


        ryParent = findViewById(R.id.ryParent);
        title = (TextView) findViewById(R.id.title);
        mShimmerViewContainer = findViewById(R.id.shimmer_view_container);

        linearLayout = (LinearLayout) findViewById(R.id.linear);
        rvMyOrder = (RecyclerView) findViewById(R.id.rvMyOrder);
        txtNoDataFound = findViewById(R.id.txtNoDataFound);

        imgBack = findViewById(R.id.imgBack);
        imgBack.setOnClickListener(this);

        title.setText(getString(R.string.order_details));
        linearLayout.setVisibility(View.GONE);


        if (Util.isDeviceOnline(this)) {

            myOrderPresenter.getOrderDetails(orderId);

        } else {
//            showCenteredToast(ryParent, this, getString(R.string.network_connection), "");
Util.showNoInternetDialog(this);
        }

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvMyOrder.setLayoutManager(layoutManager);


    }

    @Override
    public void showWait() {
        mShimmerViewContainer.setVisibility(View.VISIBLE);
        mShimmerViewContainer.startShimmerAnimation();
//        showProDialog(this);
    }

    @Override
    public void removeWait() {
//        dismissProDialog();
        mShimmerViewContainer.stopShimmerAnimation();
        mShimmerViewContainer.setVisibility(View.GONE);
    }

    @Override
    public void onFailure(String appErrorMessage) {
        nodataFound(appErrorMessage);


    }

    @Override
    public void getOrderDetails(MyOrderDetailsResponse myOrderDetailsResponse) {
        if (myOrderDetailsResponse != null) {
            if (myOrderDetailsResponse.getmOrderDetailsData() != null && !myOrderDetailsResponse.getmOrderDetailsData().getmProduct().isEmpty()) {

                myOrderDataArrayList.clear();
                myOrderDataArrayList.addAll(myOrderDetailsResponse.getmOrderDetailsData().getmProduct());

                lyProductDetails.setVisibility(View.VISIBLE);
                txtNoDataFound.setVisibility(View.GONE);
                lyOrderDetails.setVisibility(View.VISIBLE);

                setData(myOrderDetailsResponse.getmOrderDetailsData());


                myOrderAdapter = new MyOrderDetailsAdapter(this, myOrderDataArrayList, MyOrderDetailsActivity.this);
                rvMyOrder.setAdapter(myOrderAdapter);

            } else if (myOrderDetailsResponse.getmOrderDetailsData() == null && !myOrderDetailsResponse.getmOrderDetailsData().getmProduct().isEmpty()) {

                myOrderDataArrayList.clear();
                myOrderDataArrayList.addAll(myOrderDetailsResponse.getmOrderDetailsData().getmProduct());

                lyProductDetails.setVisibility(View.VISIBLE);
                txtNoDataFound.setVisibility(View.GONE);
                lyOrderDetails.setVisibility(View.GONE);


                myOrderAdapter = new MyOrderDetailsAdapter(this, myOrderDataArrayList, MyOrderDetailsActivity.this);
                rvMyOrder.setAdapter(myOrderAdapter);

            } else if (myOrderDetailsResponse.getmOrderDetailsData() != null && myOrderDetailsResponse.getmOrderDetailsData().getmProduct().isEmpty()) {


                lyProductDetails.setVisibility(View.GONE);
                txtNoDataFound.setVisibility(View.GONE);
                lyOrderDetails.setVisibility(View.VISIBLE);

                setData(myOrderDetailsResponse.getmOrderDetailsData());


            } else {
                nodataFound(getString(R.string.no_data_found));


            }

        }
    }

    private void nodataFound(String appErrorMessage) {
        txtNoDataFound.setVisibility(View.VISIBLE);
        txtNoDataFound.setText(appErrorMessage);
        lyProductDetails.setVisibility(View.GONE);
        lyOrderDetails.setVisibility(View.GONE);
    }


    @Override
    public void onClick(View view) {
        try {
            int vId = view.getId();
            switch (vId) {
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


    private void setData(MyOrderDetailsResponse.OrderDetailsReponse orderDetailsReponse) {

        try {
            txtOrderId.setText(getString(R.string.orderid) + orderDetailsReponse.getmOrderDetail().getmOrderId());
            txtPrice.setText(getString(R.string.Rs) + getString(R.string.amount) + orderDetailsReponse.getmOrderDetail().getmAmount());

            txtSenderName.setText(getString(R.string.name) + orderDetailsReponse.getmOrderDetail().getmSenderName() + " " + orderDetailsReponse.getmOrderDetail().getmSenderLastName());
            txtSenderContact.setText(getString(R.string.contact) + orderDetailsReponse.getmOrderDetail().getmSenderContact());
            txtSenderAddress.setText(getString(R.string.address) + orderDetailsReponse.getmOrderDetail().getmSenderAddress());
            txtSenderEmail.setText(getString(R.string.email) + orderDetailsReponse.getmOrderDetail().getmSenderEmail());

            txtReceiverName.setText(getString(R.string.name) + orderDetailsReponse.getmOrderDetail().getmRecieverName() + " " + orderDetailsReponse.getmOrderDetail().getmReciverLastName());
            txtReceiverEmail.setText(getString(R.string.email) + orderDetailsReponse.getmOrderDetail().getmReciverEmail());

            txtDeliveryContact.setText(getString(R.string.contact) + orderDetailsReponse.getmOrderDetail().getmDeliveryContact());
            txtDeliveryAddress.setText(getString(R.string.address) + orderDetailsReponse.getmOrderDetail().getmDeliveryAddress());
            txtDeliveryPincode.setText(getString(R.string.pincode) + orderDetailsReponse.getmOrderDetail().getmDeliveryPincode());
            txtDeliveryCity.setText(getString(R.string.deliver_city) + orderDetailsReponse.getmOrderDetail().getmDeliveryCity());
            txtDeliveryState.setText(getString(R.string.delivery_state) + orderDetailsReponse.getmOrderDetail().getmDeliveryState());
            txtDeliveryLandmark.setText(getString(R.string.delivery_landmark) + orderDetailsReponse.getmOrderDetail().getmDeliveryLandmark());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
