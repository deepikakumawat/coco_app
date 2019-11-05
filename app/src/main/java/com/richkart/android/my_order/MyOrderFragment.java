package com.richkart.android.my_order;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.richkart.android.R;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.richkart.android.base_fragment.BaseFragment;
import com.richkart.android.my_order_details.MyOrderDetailsFragment;
import com.richkart.android.shared_preference.CocoPreferences;
import com.richkart.android.utility.Constant;
import com.richkart.android.utility.MarshMallowPermissions;
import com.richkart.android.utility.Util;

import java.util.ArrayList;
import java.util.List;

import com.richkart.android.fragment.FragmentManagerUtils;

import static com.richkart.android.utility.Util.showCenteredToast;

public class MyOrderFragment extends BaseFragment implements MyOrderView, View.OnClickListener, AdapterView.OnItemSelectedListener {

    private MyOrderAdapter myOrderAdapter;
    private RecyclerView rvMyOrder;
    private ArrayList<MyOrderResponse.MyOrderData> myOrderDataArrayList = new ArrayList<>();
    private MarshMallowPermissions marshMallowPermissions = new MarshMallowPermissions(getActivity());
    private int cancelOrderPosition;
    private Dialog addContactDialog;
    private EditText etxtReason;
    private MyOrderPresenter myOrderPresenter;
    private Spinner spReason;
    private String cancelReason = "Choose Reason";
    private LinearLayout lyReason;
    private ShimmerFrameLayout mShimmerViewContainer;
    private TextView txtNoDataFound;
    private RelativeLayout ryParent;
    private View mView;
    private LinearLayout lyTopStatement;
    private ImageView imgClose;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mView = inflater.inflate(R.layout.fragment_my_order, container, false);


        return mView;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        myOrderPresenter = new MyOrderPresenter(this);

        ryParent = view.findViewById(R.id.ryParent);
        mShimmerViewContainer = view.findViewById(R.id.shimmer_view_container);

        imgClose = view.findViewById(R.id.imgClose);
        lyTopStatement = view.findViewById(R.id.lyTopStatement);
        rvMyOrder = (RecyclerView) view.findViewById(R.id.rvMyOrder);
        txtNoDataFound = view.findViewById(R.id.txtNoDataFound);
        imgClose.setOnClickListener(this);


        if (Util.isDeviceOnline(getActivity())) {

            myOrderPresenter.myOrder(CocoPreferences.getUserId());

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
    }

    @Override
    public void removeWait() {
        mShimmerViewContainer.stopShimmerAnimation();
        mShimmerViewContainer.setVisibility(View.GONE);
    }

    @Override
    public void onFailure(String appErrorMessage) {
        nodataFound(appErrorMessage);


    }

    private void nodataFound(String appErrorMessage) {
        txtNoDataFound.setVisibility(View.VISIBLE);
        txtNoDataFound.setText(appErrorMessage);
        rvMyOrder.setVisibility(View.GONE);
    }

    @Override
    public void onMyOrderList(MyOrderResponse myOrderResponse) {

        if (myOrderResponse != null) {
            if (!myOrderResponse.getmAddressData().isEmpty()) {
                myOrderDataArrayList.clear();
                myOrderDataArrayList.addAll(myOrderResponse.getmAddressData());

                rvMyOrder.setVisibility(View.VISIBLE);
                txtNoDataFound.setVisibility(View.GONE);

                myOrderAdapter = new MyOrderAdapter(getActivity(), myOrderDataArrayList, MyOrderFragment.this);
                rvMyOrder.setAdapter(myOrderAdapter);
            }
        }


    }

    @Override
    public void cancelOrder(CancelOrderResponse cancelOrderResponse) {
        if (!TextUtils.isEmpty(cancelOrderResponse.getmStatus()) && ("1".equalsIgnoreCase(cancelOrderResponse.getmStatus()))) {
            showCenteredToast(ryParent, getActivity(), cancelOrderResponse.getmMessage(), Constant.API_SUCCESS);
            if (myOrderAdapter != null) {
                myOrderDataArrayList.remove(cancelOrderPosition);
                myOrderAdapter.notifyDataSetChanged();

                if (myOrderDataArrayList.isEmpty()) {
                    nodataFound(getString(R.string.no_data_found));
                }
            }
        } else {
            showCenteredToast(ryParent, getActivity(), cancelOrderResponse.getmData(), "");
        }
    }


    @Override
    public void onClick(View view) {
        try {
            int vId = view.getId();
            switch (vId) {

                case R.id.txtSupport:
                    MyOrderResponse.MyOrderData myOrderData = ((MyOrderResponse.MyOrderData) view.getTag());
//                    removePosstion = (int) view.getTag(R.id.txtProductName);

                    if (myOrderData != null) {

                        if (marshMallowPermissions.checkPermissionForPhone()) {
                            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "18005726067"));
                            if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                                // TODO: Consider calling
                                //    ActivityCompat#requestPermissions
                                // here to request the missing permissions, and then overriding
                                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                //                                          int[] grantResults)
                                // to handle the case where the user grants the permission. See the documentation
                                // for ActivityCompat#requestPermissions for more details.
                                return;
                            }
                            startActivity(intent);
                        } else {
                            marshMallowPermissions.requestPermissionForPhone(ryParent);
                        }


                    }
                    break;
                case R.id.txtCancelOrder:
                    myOrderData = ((MyOrderResponse.MyOrderData) view.getTag());
                    cancelOrderPosition = (int) view.getTag(R.id.txtCancelOrder);

                    if (myOrderData != null) {
                        cancelOrder(myOrderData);
                    }
                    break;
                case R.id.txtSubmit:

                    myOrderData = ((MyOrderResponse.MyOrderData) view.getTag());
                    if (lyReason.getVisibility() == View.VISIBLE) {
                        cancelReason = etxtReason.getText().toString().trim();
                    }

                    if (!TextUtils.isEmpty(cancelReason) && !cancelReason.equalsIgnoreCase("Choose Reason")) {

                        if (Util.isDeviceOnline(getActivity())) {
                            myOrderPresenter.cancelOrder(myOrderData.getmOrderId(), cancelReason);
                            addContactDialog.dismiss();
                            Util.hideKeyBoardMethod(getActivity(), view);
                        } else {
                            Util.showNoInternetDialog(getActivity());
                        }


                    } else {
                        showCenteredToast(ryParent, getActivity(), getString(R.string.order_cancel_reason), "");

                    }


                    break;

                case R.id.lyOrder:
                    myOrderData = ((MyOrderResponse.MyOrderData) view.getTag());

                    if (myOrderData != null) {


                        Bundle bundle = new Bundle();
                        bundle.putString("orderId", myOrderData.getmOrderId());

                        MyOrderDetailsFragment myOrderDetailsFragment = new MyOrderDetailsFragment();
                        myOrderDetailsFragment.setArguments(bundle);

                        FragmentManagerUtils.replaceFragmentInRoot(getActivity().getSupportFragmentManager(), myOrderDetailsFragment, "MyOrderDetailsFragment", true, false);

                    }
                    break;
                case R.id.txtTrack:
                    FragmentManagerUtils.replaceFragmentInRoot(getActivity().getSupportFragmentManager(), new TimelineTestFragment(), "TimelineTestFragment", true, false);

                    break;
                case R.id.imgClose:
                    lyTopStatement.setVisibility(View.GONE);
                    break;

                default:
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void cancelOrder(MyOrderResponse.MyOrderData myOrderData) {
        try {
            addContactDialog = new Dialog(getActivity());
            addContactDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            addContactDialog.setContentView(R.layout.dialog_cancel_order);
            spReason = addContactDialog.findViewById(R.id.spReason);
            etxtReason = addContactDialog.findViewById(R.id.etxtReason);
            lyReason = addContactDialog.findViewById(R.id.lyReason);
            TextView txtSubmit = addContactDialog.findViewById(R.id.txtSubmit);
            TextView txtCancel = addContactDialog.findViewById(R.id.txtCancel);
            lyReason.setVisibility(View.GONE);

            loadReasonData();
            spReason.setOnItemSelectedListener(this);

            txtSubmit.setOnClickListener(this);
            txtSubmit.setTag(myOrderData);
            txtCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    addContactDialog.cancel();
                }
            });
            Window window = addContactDialog.getWindow();
            window.setGravity(Gravity.CENTER);
            window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            window.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            addContactDialog.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void loadReasonData() {
        List<String> reasonList = new ArrayList<>();
        reasonList.add("Choose Reason");
        reasonList.add("Price Too High");
        reasonList.add("Buying From Other Places");
        reasonList.add("Order by Mistake");
        reasonList.add("Information Not Complete");
        reasonList.add("Delay in Shipping");
        reasonList.add("Support Not Perfect");
        reasonList.add("Other");

        ArrayAdapter aa = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, reasonList);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spReason.setAdapter(aa);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        try {
            cancelReason = (String) parent.getItemAtPosition(position);

            if (cancelReason.equalsIgnoreCase("other")) {
                lyReason.setVisibility(View.VISIBLE);
            } else {
                lyReason.setVisibility(View.GONE);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

}
