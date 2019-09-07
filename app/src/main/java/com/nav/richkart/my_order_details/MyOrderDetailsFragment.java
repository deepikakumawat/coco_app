package com.nav.richkart.my_order_details;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nav.richkart.R;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.nav.richkart.base_fragment.BaseFragment;
import com.nav.richkart.product_details.ProductDetailFragment;
import com.nav.richkart.utility.Util;

import java.util.ArrayList;

import com.nav.richkart.fragment.FragmentManagerUtils;

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
    private TextView txtOrderId;
    private TextView txtOrderDate;
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
    private View mView;


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
        txtOrderDate = view.findViewById(R.id.txtOrderDate);
        txtPrice = view.findViewById(R.id.txtPrice);

        txtSenderName = view.findViewById(R.id.txtSenderName);
        txtSenderContact = view.findViewById(R.id.txtSenderContact);
        txtSenderAddress = view.findViewById(R.id.txtSenderAddress);
        txtSenderEmail = view.findViewById(R.id.txtSenderEmail);

        txtReceiverName = view.findViewById(R.id.txtReceiverName);

        txtReceiverEmail = view.findViewById(R.id.txtReceiverEmail);

        txtDeliveryContact = view.findViewById(R.id.txtDeliveryContact);
        txtDeliveryAddress = view.findViewById(R.id.txtDeliveryAddress);
        txtDeliveryPincode = view.findViewById(R.id.txtDeliveryPincode);
        txtDeliveryCity = view.findViewById(R.id.txtDeliveryCity);
        txtDeliveryState = view.findViewById(R.id.txtDeliveryState);
        txtDeliveryLandmark = view.findViewById(R.id.txtDeliveryLandmark);


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
            txtOrderDate.setText("Date: " + orderDetailsReponse.getmOrderDetail().getmCreatedDate());

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
