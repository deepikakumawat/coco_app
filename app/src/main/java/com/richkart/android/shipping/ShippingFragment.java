package com.richkart.android.shipping;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.razorpay.Checkout;
import com.richkart.android.R;
import com.richkart.android.address.AddAddressActivity;
import com.richkart.android.address.AddUpdateAddressResponse;
import com.richkart.android.address.AddressListActivity;
import com.richkart.android.address.AddressListResponse;
import com.richkart.android.base_fragment.BaseFragment;
import com.richkart.android.checkout.CheckoutPaymentResponse;
import com.richkart.android.checkout.SuccessFragment;
import com.richkart.android.fragment.FragmentManagerUtils;
import com.richkart.android.interfaces.IPaymentListener;
import com.richkart.android.my_cart.CartListResponse;
import com.richkart.android.shared_preference.CocoPreferences;
import com.richkart.android.utility.Constant;
import com.richkart.android.utility.Util;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.richkart.android.utility.Util.dismissProDialog;
import static com.richkart.android.utility.Util.showCenteredToast;
import static com.richkart.android.utility.Util.showProDialog;

public class ShippingFragment extends BaseFragment implements ShipmentView, View.OnClickListener, IPaymentListener {


    private ShipmentListAdapter checkoutListAdapter;
    private TextView txtConfirmPlaceOrder;


    private ShipmentPresenter shipmentPresenter;
    private AddressListResponse.AddressData addressData = null;
    private String totalPrice;
    private TextView txtTotalPrice;
    private RelativeLayout lyParent;
    private String method;
    private String orderStatus = "";
    private View mView;
    private List<ShippingListResponse> shippingListResponses = null;
    private Dialog enterCaptchaDialog;
    private Button btnConfirmCaptcha;
    private TextView etxtEnterTheCharacters;
    private ImageView imgReload;
    private TextView txtCaptcha;
    private LinearLayout lyShipping;
    private RadioButton selectedRadioButton = null;
    private TextView txtAddress1;
    private TextView txtAddress2;
    private TextView txtLandmark;
    private TextView txtCity;
    private TextView txtState;
    private TextView txtCountry;
    private TextView txtZipcode;
    private String shipType;
    private String shipBy;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mView = inflater.inflate(R.layout.shipment_frag, container, false);

        return mView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        shipmentPresenter = new ShipmentPresenter(this);

        Bundle bundle = getArguments();
        totalPrice = bundle.getString("totalPrice");
        method = bundle.getString("method");
        addressData = (AddressListResponse.AddressData) bundle.getSerializable("addressData");
        if (Util.isDeviceOnline(getActivity())) {
            String state = "";
            if (addressData != null) {
                if (!TextUtils.isEmpty(addressData.getmState())) {
                    state = addressData.getmState().substring(0, 2);

                }
            }


            shipmentPresenter.shippingList(CocoPreferences.getUserId(), addressData.getmAddress1() + "" + addressData.getmAddress2(), addressData.getmZipcode(), state, addressData.getmCity());
        } else {
            Util.showNoInternetDialog(getActivity());
        }

        init();


    }

    private void init() {
        lyParent = mView.findViewById(R.id.lyParent);
        txtTotalPrice = mView.findViewById(R.id.txtTotalPrice);
        txtConfirmPlaceOrder = mView.findViewById(R.id.txtConfirmPlaceOrder);
        lyShipping = mView.findViewById(R.id.lyShipping);
        txtConfirmPlaceOrder.setOnClickListener(this);
        txtTotalPrice.setText("Total price: " + totalPrice);

        txtAddress1 = mView.findViewById(R.id.txtAddress1);
        txtAddress2 = mView.findViewById(R.id.txtAddress2);
        txtLandmark = mView.findViewById(R.id.txtLandmark);
        txtCity = mView.findViewById(R.id.txtCity);
        txtState = mView.findViewById(R.id.txtState);
        txtCountry = mView.findViewById(R.id.txtCountry);
        txtZipcode = mView.findViewById(R.id.txtZipcode);


        if (addressData != null) {
            setAddress(addressData);
        }

    }


    private void setAddress(AddressListResponse.AddressData addressData) {


        txtAddress1.setText(TextUtils.isEmpty(addressData.getmAddress1()) ? "-" : addressData.getmAddress1() + ",");
        txtAddress2.setText(TextUtils.isEmpty(addressData.getmAddress2()) ? "-" : addressData.getmAddress2());
        txtLandmark.setText(TextUtils.isEmpty(addressData.getmLandmark()) ? "-" : addressData.getmLandmark());
        txtCity.setText(TextUtils.isEmpty(addressData.getmCity()) ? "-" : addressData.getmCity() + ",");
        txtState.setText(TextUtils.isEmpty(addressData.getmState()) ? "-" : addressData.getmState() + ",");
        txtCountry.setText(TextUtils.isEmpty(addressData.getmCountry()) ? "-" : addressData.getmCountry());
        txtZipcode.setText(TextUtils.isEmpty(addressData.getmZipcode()) ? "-" : addressData.getmZipcode());
    }


    @Override
    public void onClick(View view) {
        Intent intent;
        try {
            int vId = view.getId();
            switch (vId) {

                case R.id.txtConfirmPlaceOrder:
                    if (!TextUtils.isEmpty(shipType)) {
                        if (!method.equalsIgnoreCase("Card")) {
                            enterCaptcha();

                        } else {
                            startPayment();

                        }
                    } else {
                        showCenteredToast(lyParent, getActivity(), getString(R.string.please_select_ship_type), "");
                    }
                    break;
                case R.id.imgReload:
                    int randomNumber = getRandomNumber();
                    txtCaptcha.setText(randomNumber + "");
                    break;
                case R.id.btnConfirmCaptcha:
                    String randomCptcha = txtCaptcha.getText().toString();
                    String enteredCaptcha = etxtEnterTheCharacters.getText().toString();
                    if (!TextUtils.isEmpty(enteredCaptcha) && !TextUtils.isEmpty(randomCptcha)) {
                        if (enteredCaptcha.equalsIgnoreCase(randomCptcha)) {
                            enterCaptchaDialog.cancel();
                            method = "COD";
                            callCheckoutPaymentApi("cashondelivery");
                        } else {
                            showCenteredToast(lyParent, getActivity(), getString(R.string.entered_characters_not_match), "");
                        }

                    } else {
                        showCenteredToast(lyParent, getActivity(), getString(R.string.please_enter_characters), "");
                    }
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void enterCaptcha() {
        try {
            enterCaptchaDialog = new Dialog(getActivity());
            enterCaptchaDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            enterCaptchaDialog.setContentView(R.layout.dialog_captcha);
            txtCaptcha = enterCaptchaDialog.findViewById(R.id.txtCaptcha);
            imgReload = enterCaptchaDialog.findViewById(R.id.imgReload);
            etxtEnterTheCharacters = enterCaptchaDialog.findViewById(R.id.etxtEnterTheCharacters);
            btnConfirmCaptcha = enterCaptchaDialog.findViewById(R.id.btnConfirmCaptcha);
            TextView txtReturnToPaymentOption = enterCaptchaDialog.findViewById(R.id.txtReturnToPaymentOption);

            imgReload.setOnClickListener(this);
            btnConfirmCaptcha.setOnClickListener(this);

            int randomNumber = getRandomNumber();
            txtCaptcha.setText(randomNumber + "");

            txtReturnToPaymentOption.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    enterCaptchaDialog.cancel();
                }
            });
            Window window = enterCaptchaDialog.getWindow();
            window.setGravity(Gravity.CENTER);
            window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            window.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            enterCaptchaDialog.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private int getRandomNumber() {
        final int min = 100;
        final int max = 999;
        final int random = new Random().nextInt((max - min) + 1) + min;
        return random;
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
        showCenteredToast(lyParent, getActivity(), appErrorMessage, "");

        if (addressData != null && TextUtils.isEmpty(orderStatus)) {
            goToSuccessScreen(Constant.ORDER_FAIL, "");
        }

    }


    // Payment Gatewayy
    public void startPayment() {
        /*
          You need to pass current activity in order to let Razorpay create ShippingFragment
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
            options.put("amount", totalPrice);

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

                shipmentPresenter.getCheckoutPayment(CocoPreferences.getUserId(),
                        razorpayPaymentID,
                        "0",
                        totalPrice,
                        "1",
                        CocoPreferences.getFirstName(),
                        CocoPreferences.getLastName(),
                        CocoPreferences.getUserEmail(),
                        addressData.getmLandmark(),
                        CocoPreferences.getUserPhone(),
                        addressData.getmAddress1() + " " + addressData.getmAddress2(),
                        addressData.getmZipcode(),
                        addressData.getmState(),
                        addressData.getmCity(),
                        CocoPreferences.getFirstName(),
                        CocoPreferences.getLastName(),
                        CocoPreferences.getUserEmail(),
                        addressData.getmLandmark(),
                        CocoPreferences.getUserPhone(),
                        addressData.getmAddress1() + " " + addressData.getmAddress2(),
                        addressData.getmZipcode(),
                        addressData.getmState(),
                        addressData.getmCity(), shipType, shipBy
                );


            }


        } else {
            Util.showNoInternetDialog(getActivity());
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


            goToSuccessScreen(Constant.ORDER_FAIL, "");

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
                showCenteredToast(lyParent, getActivity(), checkoutPaymentResponse.getMessage(), "");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void getShippingList(List<ShippingListResponse> addressListResponse) {
        if (!addressListResponse.isEmpty()) {
            shippingListResponses = addressListResponse;

        }

        setLyImages(shippingListResponses);

    }

    private void setLyImages(List<ShippingListResponse> addressListResponse) {


        lyShipping.removeAllViews();

        LayoutInflater layoutInflater = getLayoutInflater();
        View view;
        for (int i = 0; i < addressListResponse.size(); i++) {
            view = layoutInflater.inflate(R.layout.list_item_shipping, lyShipping, false);
            TextView txtProductSalePrice = view.findViewById(R.id.txtProductSalePrice);
            RadioButton rbShippingName = view.findViewById(R.id.rbShippingName);


            rbShippingName.setChecked(addressListResponse.get(i).isSelected());

            txtProductSalePrice.setText(addressListResponse.get(i).getmShipAmount());
            rbShippingName.setText(addressListResponse.get(i).getmShipTitle() + "\n" + addressListResponse.get(i).getMshipBy());


            rbShippingName.setTag(i);
            rbShippingName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Set unchecked all other elements in the list, so to display only one selected radio button at a time
                    for (ShippingListResponse model : addressListResponse)
                        model.setSelected(false);

                    // Set "checked" the model associated to the clicked radio button
                    addressListResponse.get(((int) view.getTag())).setSelected(true);

                    // If current view (RadioButton) differs from previous selected radio button, then uncheck selectedRadioButton
                    if (null != selectedRadioButton && !view.equals(selectedRadioButton))
                        selectedRadioButton.setChecked(false);

                    // Replace the previous selected radio button with the current (clicked) one, and "check" it
                    selectedRadioButton = (RadioButton) view;
                    selectedRadioButton.setChecked(true);

                    shipType = addressListResponse.get(((int) view.getTag())).getmShipType();
                    shipBy = addressListResponse.get(((int) view.getTag())).getMshipBy();

                    Double shipping = Double.parseDouble(addressListResponse.get(((int) view.getTag())).getmShipAmount());
                    txtTotalPrice.setText("Total price: " + (Double.parseDouble(totalPrice) + shipping));

                    totalPrice = txtTotalPrice.getText().toString();

                }
            });
            lyShipping.addView(view);

        }
    }


    @Override
    protected boolean isSearchIconVisible() {
        return false;
    }

    @Override
    protected boolean isCartIconVisible() {
        return false;
    }

    private void goToSuccessScreen(String orderStatus, String orderId) {


        Bundle bundle = new Bundle();
        bundle.putString("totalPrice", totalPrice);
        bundle.putString("quantity", getArguments().getString("quantity") + "");
        bundle.putString("method", method);
        bundle.putString("orderStatus", orderStatus);
        bundle.putSerializable("addressData", addressData);
        bundle.putSerializable("orderId", orderId);

        SuccessFragment successFragment = new SuccessFragment();
        successFragment.setArguments(bundle);

        FragmentManagerUtils.replaceFragmentInRoot(getActivity().getSupportFragmentManager(), successFragment, "SuccessFragment", true, false);


    }

}
