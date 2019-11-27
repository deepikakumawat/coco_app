package com.richkart.android.checkout;

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
import android.widget.TextView;
import android.widget.Toast;

import com.richkart.android.R;
import com.razorpay.Checkout;
import com.richkart.android.address.AddAddressActivity;
import com.richkart.android.address.AddUpdateAddressResponse;
import com.richkart.android.address.AddressListActivity;
import com.richkart.android.address.AddressListResponse;
import com.richkart.android.base_fragment.BaseFragment;
import com.richkart.android.interfaces.IPaymentListener;
import com.richkart.android.my_cart.CartListResponse;
import com.richkart.android.shared_preference.CocoPreferences;
import com.richkart.android.shipping.ShippingFragment;
import com.richkart.android.utility.Constant;
import com.richkart.android.utility.Util;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Random;

import com.richkart.android.fragment.FragmentManagerUtils;

import static com.richkart.android.utility.Util.dismissProDialog;
import static com.richkart.android.utility.Util.showCenteredToast;
import static com.richkart.android.utility.Util.showProDialog;

public class CheckoutFragment extends BaseFragment implements CheckoutView, View.OnClickListener {


    private CheckoutListAdapter checkoutListAdapter;
    private RecyclerView rvCart;
    private ArrayList<CartListResponse.ProductData> productDataArrayList = new ArrayList<>();
    private TextView txtConfirmPlaceOrder;
    private TextView txtTitle;
    private TextView txtAddress1;
    private TextView txtAddress2;
    private TextView txtLandmark;
    private TextView txtCity;
    private TextView txtState;
    private TextView txtCountry;
    private TextView txtZipcode;
    private TextView btnChange;
    private TextView btnAddAddress;
    private static final int ADDRESSLIST_ACTION = 101;
    private static final int ADD_ADDRESS_ACTION = 104;


    private CheckoutPresenter checkoutPresenter;
    private AddressListResponse.AddressData addressData = null;
    private ArrayList<AddUpdateAddressResponse.AddUpdateAddressData> addUpdateAddressData = null;
    private String totalPrice;
    private TextView txtTotalPrice;
    private TextView txtSubTotalPrice;

    private String totalRazorPrice;
    private String orderStatus = "";
    private int selectedValue;
    private LinearLayout lyParent;
    private View mView;
    private Dialog enterCaptchaDialog;
    private Button btnConfirmCaptcha;
    private TextView etxtEnterTheCharacters;
    private ImageView imgReload;
    private TextView txtCaptcha;
    private String paymentMethod = "Card";
    private RadioGroup rgPayment;
    private RadioButton rbPOD;
    private RadioButton rbPayOnline;
    private String paymentOption = "";


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
//            showCenteredToast(lyParent, getActivity(), getString(R.string.network_connection),"");
            Util.showNoInternetDialog(getActivity());
        }

        init();


        if (!productDataArrayList.isEmpty()) {

            checkoutListAdapter = new CheckoutListAdapter(getActivity(), productDataArrayList, CheckoutFragment.this);
            rvCart.setAdapter(checkoutListAdapter);
        }

        txtTotalPrice.setText(!TextUtils.isEmpty(totalPrice) ? totalPrice : "-");
        txtSubTotalPrice.setText(!TextUtils.isEmpty(totalPrice) ? totalPrice : "-");


    }

    private void init() {
        lyParent = mView.findViewById(R.id.lyParent);
        rgPayment = mView.findViewById(R.id.rgPayment);
        rbPayOnline = mView.findViewById(R.id.rbPayOnline);
        rbPOD = mView.findViewById(R.id.rbPOD);
        txtTotalPrice = mView.findViewById(R.id.txtTotalPrice);
        txtSubTotalPrice = mView.findViewById(R.id.txtSubTotalPrice);
        rvCart = mView.findViewById(R.id.rvCart);
        txtConfirmPlaceOrder = mView.findViewById(R.id.txtConfirmPlaceOrder);
        txtTitle = mView.findViewById(R.id.txtTitle);
        btnChange = mView.findViewById(R.id.btnChange);
        btnAddAddress = mView.findViewById(R.id.btnAddAddress);
        txtConfirmPlaceOrder.setOnClickListener(this);
        btnAddAddress.setOnClickListener(this);
        btnChange.setOnClickListener(this);

        txtAddress1 = mView.findViewById(R.id.txtAddress1);
        txtAddress2 = mView.findViewById(R.id.txtAddress2);
        txtLandmark = mView.findViewById(R.id.txtLandmark);
        txtCity = mView.findViewById(R.id.txtCity);
        txtState = mView.findViewById(R.id.txtState);
        txtCountry = mView.findViewById(R.id.txtCountry);
        txtZipcode = mView.findViewById(R.id.txtZipcode);


        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rvCart.setLayoutManager(layoutManager);


        rgPayment.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (checkedId == R.id.rbPOD) {

                    paymentOption = rbPOD.getText().toString();
                    rbPOD.setChecked(true);

                } else {
                    paymentOption = rbPayOnline.getText().toString();
                    rbPayOnline.setChecked(true);

                }

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
                        if (!TextUtils.isEmpty(paymentOption)) {
                            if (paymentOption.equalsIgnoreCase(getString(R.string.pay_on_delivery))) {
                                paymentMethod = "COD";

//                                enterCaptcha();
                            } else if (rbPayOnline.isChecked()) {
                                paymentMethod = "Card";

                            }

                            goToSuccessScreen();

                        } else {
                            showCenteredToast(lyParent, getActivity(), "Please select payment mode.", "");

                        }

                    } else {
                        showCenteredToast(lyParent, getActivity(), "You haven't added address. Please add address first", "");
                    }


                    break;
                case R.id.btnChange:
                    intent = new Intent(getActivity(), AddressListActivity.class);
                    intent.putExtra("selectedValue", selectedValue);
                    intent.putExtra("screen", "Checkout");
                    startActivityForResult(intent, ADDRESSLIST_ACTION);
                    break;
                case R.id.btnAddAddress:
                    intent = new Intent(getActivity(), AddAddressActivity.class);
                    startActivityForResult(intent, ADD_ADDRESS_ACTION);
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
                            paymentMethod = "COD";
                            goToSuccessScreen();
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
        if (addressData == null) {
            btnAddAddress.setVisibility(View.VISIBLE);

            btnChange.setVisibility(View.GONE);
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
                btnAddAddress.setVisibility(View.VISIBLE);

                btnChange.setVisibility(View.GONE);
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
//                        showCenteredToast(lyParent, getActivity(), getString(R.string.network_connection),"");
                        Util.showNoInternetDialog(getActivity());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setAddress(AddressListResponse.AddressData addressData) {
        btnAddAddress.setVisibility(View.GONE);

        btnChange.setVisibility(View.VISIBLE);
        txtAddress1.setVisibility(View.VISIBLE);
        txtAddress2.setVisibility(View.VISIBLE);
        txtLandmark.setVisibility(View.VISIBLE);
        txtCity.setVisibility(View.VISIBLE);
        txtState.setVisibility(View.VISIBLE);
        txtCountry.setVisibility(View.VISIBLE);
        txtZipcode.setVisibility(View.VISIBLE);


        txtAddress1.setText(TextUtils.isEmpty(addressData.getmAddress1()) ? "-" :  addressData.getmAddress1());
        txtAddress2.setText(TextUtils.isEmpty(addressData.getmAddress2()) ? "-" : addressData.getmAddress2());
        txtLandmark.setText(TextUtils.isEmpty(addressData.getmLandmark()) ? "-" : addressData.getmLandmark());
        txtCity.setText(TextUtils.isEmpty(addressData.getmCity()) ? "-" : addressData.getmCity() + ",");
        txtState.setText(TextUtils.isEmpty(addressData.getmState()) ? "-" : addressData.getmState()+ ",");
        txtCountry.setText(TextUtils.isEmpty(addressData.getmCountry()) ? "-" : addressData.getmCountry());
        txtZipcode.setText(TextUtils.isEmpty(addressData.getmZipcode()) ? "-" : addressData.getmZipcode());
    }




    @Override
    protected boolean isSearchIconVisible() {
        return false;
    }

    @Override
    protected boolean isCartIconVisible() {
        return false;
    }

    private void goToSuccessScreen() {


        Bundle bundle = new Bundle();
        bundle.putString("totalPrice", totalPrice);
        bundle.putString("quantity", productDataArrayList.size() + "");
        bundle.putString("method", paymentMethod);
        bundle.putSerializable("addressData", addressData);


        ShippingFragment successFragment = new ShippingFragment();
        successFragment.setArguments(bundle);

        FragmentManagerUtils.replaceFragmentInRoot(getActivity().getSupportFragmentManager(), successFragment, "ShippingFragment", true, false);


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

}
