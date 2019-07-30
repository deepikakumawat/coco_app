package com.ws.design.coco_ecommerce_ui_kit.checkout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wolfsoft2.coco_ecommerce_ui_kit.R;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;
import com.ws.design.coco_ecommerce_ui_kit.address.AddAddressActivity;
import com.ws.design.coco_ecommerce_ui_kit.address.AddUpdateAddressResponse;
import com.ws.design.coco_ecommerce_ui_kit.address.AddressListActivity;
import com.ws.design.coco_ecommerce_ui_kit.address.AddressListResponse;
import com.ws.design.coco_ecommerce_ui_kit.base_fragment.BaseFragment;
import com.ws.design.coco_ecommerce_ui_kit.my_cart.CartListResponse;
import com.ws.design.coco_ecommerce_ui_kit.shared_preference.CocoPreferences;
import com.ws.design.coco_ecommerce_ui_kit.utility.Constant;
import com.ws.design.coco_ecommerce_ui_kit.utility.Util;

import org.json.JSONObject;

import java.util.ArrayList;

import fragment.FragmentManagerUtils;

import static com.ws.design.coco_ecommerce_ui_kit.utility.Util.dismissProDialog;
import static com.ws.design.coco_ecommerce_ui_kit.utility.Util.showCenteredToast;
import static com.ws.design.coco_ecommerce_ui_kit.utility.Util.showProDialog;

public class CheckoutFragment extends BaseFragment implements CheckoutView, View.OnClickListener, PaymentResultListener {

    RadioButton button1, button2;
    LinearLayout radio1, radio2;

    private CheckoutListAdapter checkoutListAdapter;
    private RecyclerView rvCart;
    private ArrayList<CartListResponse.ProductData> productDataArrayList = new ArrayList<>();
    private ArrayList<PaymentMethodData> paymentMethodDataArrayList = new ArrayList<>();
    private TextView txtConfirmPlaceOrder;
    private TextView txtTitle;
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
    private RecyclerView rvPaymentType;
    private PaymentMethodAdapter paymentMethodAdapter;
    private LinearLayout lyParent;
    private View mView;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mView = inflater.inflate(R.layout.fragment_checkout, container, false);

        return mView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        checkoutPresenter = new CheckoutPresenter(this);

        Bundle bundle = getArguments();
        if (bundle != null) {
            productDataArrayList = (ArrayList<CartListResponse.ProductData>) bundle.getSerializable("cartList");
            totalPrice = bundle.getString("totalPrice");

            try {
                totalRazorPrice = totalPrice + "00";
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }

        }

        if (Util.isDeviceOnline(getActivity())) {
            checkoutPresenter.addressList(CocoPreferences.getUserId());
        } else {
            showCenteredToast(lyParent, getActivity(), getString(R.string.network_connection),"");
        }

        init();


        if (!productDataArrayList.isEmpty()) {

            checkoutListAdapter = new CheckoutListAdapter(getActivity(), productDataArrayList, CheckoutFragment.this);
            rvCart.setAdapter(checkoutListAdapter);
        }

        txtTotalPrice.setText(!TextUtils.isEmpty(totalPrice) ? totalPrice : "-");


    }

    private void init() {
        lyParent = mView.findViewById(R.id.lyParent);
        rvPaymentType = mView.findViewById(R.id.rvPaymentType);
        txtTotalPrice = mView.findViewById(R.id.txtTotalPrice);
        rvCart = mView.findViewById(R.id.rvCart);
        txtConfirmPlaceOrder = mView.findViewById(R.id.txtConfirmPlaceOrder);
        txtTitle = mView.findViewById(R.id.txtTitle);
        txtChange = mView.findViewById(R.id.txtChange);
        txtAddAddress = mView.findViewById(R.id.txtAddAddress);
        txtConfirmPlaceOrder.setOnClickListener(this);
        txtAddAddress.setOnClickListener(this);
        txtChange.setOnClickListener(this);

        txtAddress1 = mView.findViewById(R.id.txtAddress1);
        txtAddress2 = mView.findViewById(R.id.txtAddress2);
        txtLandmark = mView.findViewById(R.id.txtLandmark);
        txtCity = mView.findViewById(R.id.txtCity);
        txtState = mView.findViewById(R.id.txtState);
        txtCountry = mView.findViewById(R.id.txtCountry);
        txtZipcode = mView.findViewById(R.id.txtZipcode);


        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rvCart.setLayoutManager(layoutManager);

        LinearLayoutManager layoutManagerPaymentMethod = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rvPaymentType.setLayoutManager(layoutManagerPaymentMethod);


        paymentMethodDataArrayList.add(new PaymentMethodData(false, getString(R.string.cash_on_delivery)));
        paymentMethodAdapter = new PaymentMethodAdapter(getActivity(), paymentMethodDataArrayList, CheckoutFragment.this);
        rvPaymentType.setAdapter(paymentMethodAdapter);



        button1 = mView.findViewById(R.id.button1);
        button2 = mView.findViewById(R.id.button2);
        radio1 = mView.findViewById(R.id.radio1);
        radio2 = mView.findViewById(R.id.radio2);

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

                case R.id.txtConfirmPlaceOrder:

                    if (addressData != null) {

                        startPayment();

                    } else {
                        showCenteredToast(lyParent, getActivity(), "You haven't added address. Please add address first","");
                    }


                    break;
                case R.id.txtChange:
                    intent = new Intent(getActivity(), AddressListActivity.class);
                    intent.putExtra("selectedValue", selectedValue);
                    intent.putExtra("screen", "Checkout");
                    startActivityForResult(intent, ADDRESSLIST_ACTION);
                    break;
                case R.id.txtAddAddress:
                    intent = new Intent(getActivity(), AddAddressActivity.class);
                    startActivityForResult(intent, ADD_ADDRESS_ACTION);
                    break;
                case R.id.lyPaymentType:
                    PaymentMethodData paymentMethodData = ((PaymentMethodData) view.getTag());
                    if (paymentMethodData != null) {
                        if (paymentMethodAdapter != null) {
                            if (paymentMethodData.isSelectedPayment()) {
                                paymentMethodData.setSelectedPayment(false);
                            } else {
                                paymentMethodData.setSelectedPayment(true);
                            }
                            paymentMethodAdapter.notifyDataSetChanged();

                            callCheckoutPaymentApi("cashondelivery");
                        }
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
    public void showWait() {
        showProDialog(getActivity());
    }

    @Override
    public void removeWait() {
        dismissProDialog();
    }

    @Override
    public void onFailure(String appErrorMessage) {
        showCenteredToast(lyParent, getActivity(), appErrorMessage,"");
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

        if (addressData != null && TextUtils.isEmpty(orderStatus)) {


            goToSuccessScreen(Constant.ORDER_FAIL,"");
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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
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

                    if (Util.isDeviceOnline(getActivity())) {
                        checkoutPresenter.addressList(CocoPreferences.getUserId());
                    } else {
                        showCenteredToast(lyParent, getActivity(), getString(R.string.network_connection),"");
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
          You need to pass current activity in order to let Razorpay create CheckoutFragment
         */
//        final Activity activity = this;
        final Activity activity = getActivity();

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


            callCheckoutPaymentApi(razorpayPaymentID);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void callCheckoutPaymentApi(String razorpayPaymentID) {
        if (Util.isDeviceOnline(getActivity())) {
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
            showCenteredToast(lyParent, getActivity(), getString(R.string.network_connection),"");

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



            goToSuccessScreen(Constant.ORDER_FAIL,"");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void getCheckoutPayment(CheckoutPaymentResponse checkoutPaymentResponse) {
        try {
            if (!TextUtils.isEmpty(checkoutPaymentResponse.getmStatus()) && ("1".equalsIgnoreCase(checkoutPaymentResponse.getmStatus()))) {


                String orderId = checkoutPaymentResponse.getmData().getmOrderId();

                orderStatus = Constant.ORDER_SUCCESS;

               goToSuccessScreen(orderStatus, orderId);


            } else {
                showCenteredToast(lyParent, getActivity(), checkoutPaymentResponse.getMessage(),"");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected String getActionbarTitle() {
        return getString(R.string.checkout);
    }

    @Override
    protected boolean isSearchIconVisible() {
        return false;
    }

    @Override
    protected boolean isCartIconVisible() {
        return false;
    }

    private void goToSuccessScreen(String orderStatus, String orderId){


        Bundle bundle = new Bundle();
        bundle.putString("totalPrice", totalPrice);
        bundle.putString("orderStatus", orderStatus);
        bundle.putSerializable("addressData",addressData);
        bundle.putSerializable("orderId",orderId);

        SuccessFragment successFragment = new SuccessFragment();
        successFragment.setArguments(bundle);

        FragmentManagerUtils.replaceFragmentInRoot(getActivity().getSupportFragmentManager(), successFragment, "SuccessFragment", true, false);


    }
}
