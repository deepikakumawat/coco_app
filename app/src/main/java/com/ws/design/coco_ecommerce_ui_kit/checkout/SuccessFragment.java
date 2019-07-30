package com.ws.design.coco_ecommerce_ui_kit.checkout;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.ws.design.coco_ecommerce_ui_kit.base_fragment.BaseFragment;
import com.ws.design.coco_ecommerce_ui_kit.home.HomeFragment;
import com.ws.design.coco_ecommerce_ui_kit.my_cart.CartListResponse;
import com.ws.design.coco_ecommerce_ui_kit.shared_preference.CocoPreferences;
import com.ws.design.coco_ecommerce_ui_kit.utility.Constant;
import com.ws.design.coco_ecommerce_ui_kit.utility.Util;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import fragment.FragmentManagerUtils;

import static com.ws.design.coco_ecommerce_ui_kit.utility.Util.dismissProDialog;
import static com.ws.design.coco_ecommerce_ui_kit.utility.Util.showCenteredToast;
import static com.ws.design.coco_ecommerce_ui_kit.utility.Util.showProDialog;

public class SuccessFragment extends BaseFragment implements View.OnClickListener {


    private String totalPrice;
    private TextView txtTotalPrice;
    private ImageView imgOrderStatus;
    private String orderStatus;
    private TextView txtGoToHome;
    private TextView txtRetry;
    private String totalRazorPrice;
    private AddressListResponse.AddressData addressData = null;
    private TextView txtOrderStatus;
    private TextView txtDateTime;
    private String orderId;
    private TextView txtOrderId;
    private ConstraintLayout clParent;
    private View mView;


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


            try {
                totalRazorPrice = totalPrice + "00";
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

        clParent = view.findViewById(R.id.clParent);
        txtOrderId = view.findViewById(R.id.txtOrderId);
        txtDateTime = view.findViewById(R.id.txtDateTime);
        txtOrderStatus = view.findViewById(R.id.txtOrderStatus);
        txtRetry = view.findViewById(R.id.txtRetry);
        txtGoToHome = view.findViewById(R.id.txtGoToHome);
        imgOrderStatus = view.findViewById(R.id.imgOrderStatus);
        txtTotalPrice = view.findViewById(R.id.txtTotalPrice);

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
                    FragmentManagerUtils.makeRootFragment(getActivity().getSupportFragmentManager(), new HomeFragment(), getActivity(), "HomeFragment");
                    break;
                case R.id.txtRetry:
                    FragmentManagerUtils.popFragment(getFragmentManager());
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
