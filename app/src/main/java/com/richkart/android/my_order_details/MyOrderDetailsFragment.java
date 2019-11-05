package com.richkart.android.my_order_details;


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
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.richkart.android.R;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.richkart.android.base_fragment.BaseFragment;
import com.richkart.android.checkout.CheckoutFragment;
import com.richkart.android.my_order.TimelineTestFragment;
import com.richkart.android.product_details.AddToCartResponse;
import com.richkart.android.product_details.ProductDetailFragment;
import com.richkart.android.shared_preference.CocoPreferences;
import com.richkart.android.utility.Constant;
import com.richkart.android.utility.Util;

import java.util.ArrayList;

import com.richkart.android.fragment.FragmentManagerUtils;

import static com.richkart.android.utility.Util.showCenteredToast;

public class MyOrderDetailsFragment extends BaseFragment implements OrderDetailsView, View.OnClickListener {

    private MyOrderDetailsAdapter myOrderAdapter;
    private RecyclerView rvMyOrder;
    private ArrayList<OrderProduct> myOrderDataArrayList = new ArrayList<OrderProduct>();


    private OrderDetailsPresenter myOrderPresenter;

    private ShimmerFrameLayout mShimmerViewContainer;
    private TextView txtNoDataFound;
    private RelativeLayout ryParent;
    private String orderId;
    private TextView txtDeliveryLandmark;
    private TextView txtDeliveryState;
    private TextView txtDeliveryLandmarkB;
    private TextView txtDeliveryStateB;
    private TextView txtOrderId;
    private TextView txtPAYMENTId;
    private TextView txtOrderDate;
    private TextView txtPrice;
    private TextView txtPriceTotal;
    private TextView txtPriceTotalB;
    private TextView txtSenderName;
    private TextView txtSenderContact;
    private TextView txtSenderAddress;
    private TextView txtSenderEmail;
    private TextView txtDeliveryContact;
    private TextView txtDeliveryAddress;
    private TextView txtDeliveryPincode;
    private TextView txtDeliveryContactB;
    private TextView txtDeliveryAddressB;
    private TextView txtDeliveryPincodeB;
    private TextView txtDeliveryCityB;
    private TextView txtDeliveryCity;
    private LinearLayout lyOrderDetails;
    private LinearLayout lyProductDetails;
    private View mView;
    private TextView txtTrackShipment;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mView = inflater.inflate(R.layout.fragment_my_order_details, container, false);

        Bundle bundle = getArguments();
        if (bundle != null) {
            orderId = bundle.getString("orderId");

        }


        return mView;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {


        myOrderPresenter = new OrderDetailsPresenter(this);


        lyOrderDetails = view.findViewById(R.id.lyOrderDetails);
        lyProductDetails = view.findViewById(R.id.lyProductDetails);

        txtOrderId = view.findViewById(R.id.txtOrderId);
        txtPAYMENTId = view.findViewById(R.id.txtPAYMENTId);
        txtOrderDate = view.findViewById(R.id.txtOrderDate);
        txtPrice = view.findViewById(R.id.txtPrice);
        txtPriceTotal = view.findViewById(R.id.txtPriceTotal);
        txtPriceTotalB = view.findViewById(R.id.txtPriceTotalB);

        txtSenderName = view.findViewById(R.id.txtSenderName);
        txtSenderContact = view.findViewById(R.id.txtSenderContact);
        txtSenderAddress = view.findViewById(R.id.txtSenderAddress);
        txtSenderEmail = view.findViewById(R.id.txtSenderEmail);

        txtDeliveryContact = view.findViewById(R.id.txtDeliveryContact);
        txtDeliveryAddress = view.findViewById(R.id.txtDeliveryAddress);
        txtDeliveryPincode = view.findViewById(R.id.txtDeliveryPincode);
        txtDeliveryCity = view.findViewById(R.id.txtDeliveryCity);
        txtDeliveryState = view.findViewById(R.id.txtDeliveryState);
        txtDeliveryLandmark = view.findViewById(R.id.txtDeliveryLandmark);

        txtDeliveryContactB = view.findViewById(R.id.txtDeliveryContactB);
        txtDeliveryAddressB = view.findViewById(R.id.txtDeliveryAddressB);
        txtDeliveryPincodeB = view.findViewById(R.id.txtDeliveryPincodeB);
        txtDeliveryCityB = view.findViewById(R.id.txtDeliveryCityB);
        txtDeliveryStateB = view.findViewById(R.id.txtDeliveryStateB);
        txtDeliveryLandmarkB = view.findViewById(R.id.txtDeliveryLandmarkB);

        txtTrackShipment = view.findViewById(R.id.txtTrackShipment);
        txtTrackShipment.setOnClickListener(this);


        ryParent = view.findViewById(R.id.ryParent);
        mShimmerViewContainer = view.findViewById(R.id.shimmer_view_container);

        rvMyOrder = (RecyclerView) view.findViewById(R.id.rvMyOrder);
        txtNoDataFound = view.findViewById(R.id.txtNoDataFound);


        if (Util.isDeviceOnline(getActivity())) {

            myOrderPresenter.getOrderDetails(orderId);

        } else {
            Util.showNoInternetDialog(getActivity());
        }

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
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


                myOrderAdapter = new MyOrderDetailsAdapter(getActivity(), myOrderDataArrayList, MyOrderDetailsFragment.this);
                rvMyOrder.setAdapter(myOrderAdapter);

            } else if (myOrderDetailsResponse.getmOrderDetailsData() == null && !myOrderDetailsResponse.getmOrderDetailsData().getmProduct().isEmpty()) {

                myOrderDataArrayList.clear();
                myOrderDataArrayList.addAll(myOrderDetailsResponse.getmOrderDetailsData().getmProduct());

                lyProductDetails.setVisibility(View.VISIBLE);
                txtNoDataFound.setVisibility(View.GONE);
                lyOrderDetails.setVisibility(View.GONE);


                myOrderAdapter = new MyOrderDetailsAdapter(getActivity(), myOrderDataArrayList, MyOrderDetailsFragment.this);
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
                case R.id.lyOrderDetails:
                    OrderProduct orderProduct = ((OrderProduct) view.getTag());

                    if (orderProduct != null) {

                        Bundle bundle = new Bundle();
                        bundle.putString("productSlug", orderProduct.getmProductSlug());
                        bundle.putString("productId", orderProduct.getmProductId());
                        bundle.putString("productQty", orderProduct.getmProductQty());

                        ProductDetailFragment productDetailFragment = new ProductDetailFragment();
                        productDetailFragment.setArguments(bundle);

                        FragmentManagerUtils.replaceFragmentInRoot(getActivity().getSupportFragmentManager(), productDetailFragment, "ProductDetailFragment", true, false);

                    }
                    break;
                case R.id.txtTrackShipment:
                    FragmentManagerUtils.replaceFragmentInRoot(getActivity().getSupportFragmentManager(), new TimelineTestFragment(), "TimelineTestFragment", true, false);

                    break;

                case R.id.btnCheckout:
                    OrderProduct orderProduct1 = ((OrderProduct) view.getTag());
                    onClickViewButNow(orderProduct1.getmProductId());
                    break;

                default:
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addToCart(AddToCartResponse addToCartResponse) {
        if (!TextUtils.isEmpty(addToCartResponse.getmStatus()) && ("1".equalsIgnoreCase(addToCartResponse.getmStatus()))) {
            showCenteredToast(ryParent, getActivity(), addToCartResponse.getmMessage(), Constant.API_SUCCESS);

          {

                Bundle bundle = new Bundle();
                bundle.putSerializable("cartList", addToCartResponse.getmData().getmProductData());
                bundle.putString("totalPrice", addToCartResponse.getmData().getmTotalPrice());

                CheckoutFragment checkoutFragment = new CheckoutFragment();
                checkoutFragment.setArguments(bundle);

                FragmentManagerUtils.replaceFragmentInRoot(getActivity().getSupportFragmentManager(), checkoutFragment, "CheckoutFragment", true, false);


            }

        } else {
            showCenteredToast(ryParent, getActivity(), addToCartResponse.getmMessage(), "");
        }
    }
    private void onClickViewButNow(String productId) {
        if (Util.isDeviceOnline(getActivity())) {
            myOrderPresenter.addToCart(CocoPreferences.getUserId(), productId, "1", "");

        } else {
//            showCenteredToast(ryParent, getActivity(), getString(R.string.network_connection), "");
            Util.showNoInternetDialog(getActivity());

        }
    }

    private void setData(MyOrderDetailsResponse.OrderDetailsReponse orderDetailsReponse) {

        try {
            txtOrderId.setText(getString(R.string.orderid) + orderDetailsReponse.getmOrderDetail().getmOrderId());
            txtPrice.setText(getString(R.string.Rs) + getString(R.string.amount) + orderDetailsReponse.getmOrderDetail().getmAmount());
            txtPriceTotal.setText(getString(R.string.Rs) + orderDetailsReponse.getmOrderDetail().getmAmount());
            txtPriceTotalB.setText(getString(R.string.Rs) + orderDetailsReponse.getmOrderDetail().getmAmount());
            txtPAYMENTId.setText("PAYMENT ID :" + orderDetailsReponse.getmOrderDetail().getmPaymentId());

            txtSenderName.setText(getString(R.string.name) + orderDetailsReponse.getmOrderDetail().getmSenderName() + " " + orderDetailsReponse.getmOrderDetail().getmSenderLastName());
            txtSenderContact.setText(getString(R.string.contact) + orderDetailsReponse.getmOrderDetail().getmSenderContact());
            txtSenderAddress.setText(getString(R.string.address) + orderDetailsReponse.getmOrderDetail().getmSenderAddress());
            txtSenderEmail.setText(getString(R.string.email) + orderDetailsReponse.getmOrderDetail().getmSenderEmail());
            txtOrderDate.setText("Date: " + orderDetailsReponse.getmOrderDetail().getmCreatedDate());

            txtDeliveryContact.setText(getString(R.string.contact) + orderDetailsReponse.getmOrderDetail().getmDeliveryContact());
            txtDeliveryAddress.setText(getString(R.string.address) + orderDetailsReponse.getmOrderDetail().getmDeliveryAddress());
            txtDeliveryPincode.setText(getString(R.string.pincode) + orderDetailsReponse.getmOrderDetail().getmDeliveryPincode());
            txtDeliveryCity.setText(getString(R.string.deliver_city) + orderDetailsReponse.getmOrderDetail().getmDeliveryCity());
            txtDeliveryState.setText(getString(R.string.delivery_state) + orderDetailsReponse.getmOrderDetail().getmDeliveryState());
            txtDeliveryLandmark.setText(getString(R.string.delivery_landmark) + orderDetailsReponse.getmOrderDetail().getmDeliveryLandmark());

            txtDeliveryContactB.setText(getString(R.string.contact) + orderDetailsReponse.getmOrderDetail().getmDeliveryContact());
            txtDeliveryAddressB.setText(getString(R.string.address) + orderDetailsReponse.getmOrderDetail().getmDeliveryAddress());
            txtDeliveryPincodeB.setText(getString(R.string.pincode) + orderDetailsReponse.getmOrderDetail().getmDeliveryPincode());
            txtDeliveryCityB.setText(getString(R.string.deliver_city) + orderDetailsReponse.getmOrderDetail().getmDeliveryCity());
            txtDeliveryStateB.setText(getString(R.string.delivery_state) + orderDetailsReponse.getmOrderDetail().getmDeliveryState());
            txtDeliveryLandmarkB.setText(getString(R.string.delivery_landmark) + orderDetailsReponse.getmOrderDetail().getmDeliveryLandmark());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
