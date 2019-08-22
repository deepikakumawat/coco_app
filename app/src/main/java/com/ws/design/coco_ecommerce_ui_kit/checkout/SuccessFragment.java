package com.ws.design.coco_ecommerce_ui_kit.checkout;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.wolfsoft2.coco_ecommerce_ui_kit.R;
import com.ws.design.coco_ecommerce_ui_kit.address.AddressListResponse;
import com.ws.design.coco_ecommerce_ui_kit.base_fragment.BaseFragment;
import com.ws.design.coco_ecommerce_ui_kit.home.HomeFragment;
import com.ws.design.coco_ecommerce_ui_kit.shared_preference.CocoPreferences;
import com.ws.design.coco_ecommerce_ui_kit.utility.Constant;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import fragment.FragmentManagerUtils;

public class SuccessFragment extends BaseFragment implements View.OnClickListener {


    private String totalPrice;
    private String orderStatus;
    private Button btnGoToHome;
    private AddressListResponse.AddressData addressData = null;
    private String orderId;
    private TextView txtOrderId;
    private ConstraintLayout clParent;
    private View mView;
    private ScrollView svOrderPlacedSuccessfully;
    private TextView txtCustomerName;
    private TextView txtQuantity;
    private TextView txtOrderDate;
    private TextView txtOrderAmount;
    private TextView txtOrderType;
    private ScrollView svOrderUnSuccess;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mView = inflater.inflate(R.layout.activity_order_success, container, false);

        return mView;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {


        Bundle bundle = getArguments();
        if (bundle != null) {
            totalPrice = bundle.getString("totalPrice");
            orderStatus = bundle.getString("orderStatus");
            orderId = bundle.getString("orderId");
            addressData = (AddressListResponse.AddressData) bundle.getSerializable("addressData");

        }

        svOrderPlacedSuccessfully = view.findViewById(R.id.svOrderPlacedSuccessfully);
        svOrderUnSuccess = view.findViewById(R.id.svOrderUnSuccess);
        txtCustomerName = view.findViewById(R.id.txtCustomerName);
        txtOrderDate = view.findViewById(R.id.txtOrderDate);
        txtOrderId = view.findViewById(R.id.txtOrderId);
        txtOrderAmount = view.findViewById(R.id.txtOrderAmount);
        txtOrderType = view.findViewById(R.id.txtOrderType);
        txtQuantity = view.findViewById(R.id.txtQuantity);


        clParent = view.findViewById(R.id.clParent);
        btnGoToHome = view.findViewById(R.id.btnGoToHome);
        btnGoToHome.setOnClickListener(this);


        if (!TextUtils.isEmpty(orderStatus)) {
            if (orderStatus.equalsIgnoreCase(Constant.ORDER_SUCCESS)) {
                paymentSuccess();
            } else {
                paymentFailed();
            }
        } else {
            paymentFailed();
        }

        setData();
    }

    private void paymentSuccess() {
        svOrderUnSuccess.setVisibility(View.GONE);
        svOrderPlacedSuccessfully.setVisibility(View.VISIBLE);
        txtOrderId.setVisibility(View.VISIBLE);
        txtOrderId.setText(getString(R.string.orderid) + orderId);

    }

    private void paymentFailed() {
        svOrderPlacedSuccessfully.setVisibility(View.GONE);
        svOrderUnSuccess.setVisibility(View.VISIBLE);
        txtOrderId.setVisibility(View.GONE);


    }

    private void setData() {
        txtOrderAmount.setText(!TextUtils.isEmpty(totalPrice) ? "Order Amount: " + getString(R.string.Rs) + totalPrice : "-");
        txtCustomerName.setText("Hello " + CocoPreferences.getFirstName());


        Date currentTime = Calendar.getInstance().getTime();
        SimpleDateFormat sdf = new SimpleDateFormat(Constant.DATE_TIME_FORMAT);
        String currentDateandTime = sdf.format(currentTime);
        txtOrderDate.setText("Order Date: " + currentDateandTime);
    }


    @Override
    public void onClick(View view) {
        try {
            int vId = view.getId();
            switch (vId) {
                case R.id.btnGoToHome:
                    FragmentManagerUtils.makeRootFragment(getActivity().getSupportFragmentManager(), new HomeFragment(), getActivity(), "HomeFragment");
                    break;

                default:
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected boolean isCartIconVisible() {
        return false;
    }

    @Override
    protected boolean isSearchIconVisible() {
        return false;
    }

    @Override
    protected String getActionbarTitle() {
        return getString(R.string.order_status);
    }
}
