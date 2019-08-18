package com.ws.design.coco_ecommerce_ui_kit.my_order;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.wolfsoft2.coco_ecommerce_ui_kit.R;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.ws.design.coco_ecommerce_ui_kit.TimelineTest;
import com.ws.design.coco_ecommerce_ui_kit.my_order_details.MyOrderDetailsActivity;
import com.ws.design.coco_ecommerce_ui_kit.shared_preference.CocoPreferences;
import com.ws.design.coco_ecommerce_ui_kit.utility.Constant;
import com.ws.design.coco_ecommerce_ui_kit.utility.MarshMallowPermissions;
import com.ws.design.coco_ecommerce_ui_kit.utility.Util;

import java.util.ArrayList;
import java.util.List;

import static com.ws.design.coco_ecommerce_ui_kit.utility.Util.showCenteredToast;

public class MyOrderActivity extends AppCompatActivity implements MyOrderView, View.OnClickListener, AdapterView.OnItemSelectedListener {

    TextView title;
    LinearLayout linearLayout;
    MyOrderAdapter myOrderAdapter;
    private RecyclerView rvMyOrder;
    private ArrayList<MyOrderResponse.MyOrderData> myOrderDataArrayList = new ArrayList<>();
    private ImageView imgBack;
    private MarshMallowPermissions marshMallowPermissions = new MarshMallowPermissions(this);
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order);

        myOrderPresenter = new MyOrderPresenter(this);

        ryParent = findViewById(R.id.ryParent);
        title = (TextView) findViewById(R.id.title);
        mShimmerViewContainer = findViewById(R.id.shimmer_view_container);

        linearLayout = (LinearLayout) findViewById(R.id.linear);
        rvMyOrder = (RecyclerView) findViewById(R.id.rvMyOrder);
        txtNoDataFound = findViewById(R.id.txtNoDataFound);

        imgBack = findViewById(R.id.imgBack);
        imgBack.setOnClickListener(this);

        title.setText("My Orders");
        linearLayout.setVisibility(View.GONE);


        if (Util.isDeviceOnline(this)) {

            myOrderPresenter.myOrder(CocoPreferences.getUserId());

        } else {
//            showCenteredToast(ryParent,this, getString(R.string.network_connection),"");
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

                myOrderAdapter = new MyOrderAdapter(this, myOrderDataArrayList, MyOrderActivity.this);
                rvMyOrder.setAdapter(myOrderAdapter);
            }
        }


    }

    @Override
    public void cancelOrder(CancelOrderResponse cancelOrderResponse) {
        if (!TextUtils.isEmpty(cancelOrderResponse.getmStatus()) && ("1".equalsIgnoreCase(cancelOrderResponse.getmStatus()))) {
            showCenteredToast(ryParent,this, cancelOrderResponse.getmMessage(), Constant.API_SUCCESS);
            if (myOrderAdapter != null) {
                myOrderDataArrayList.remove(cancelOrderPosition);
                myOrderAdapter.notifyDataSetChanged();

                if (myOrderDataArrayList.isEmpty()) {
                    nodataFound(getString(R.string.no_data_found));
                }
            }
        } else {
            showCenteredToast(ryParent,this, cancelOrderResponse.getmData(),"");
        }
    }


    @Override
    public void onClick(View view) {
        try {
            int vId = view.getId();
            switch (vId) {
                case R.id.imgBack:
                    finish();
                    break;
                case R.id.txtSupport:
                    MyOrderResponse.MyOrderData myOrderData = ((MyOrderResponse.MyOrderData) view.getTag());
//                    removePosstion = (int) view.getTag(R.id.txtProductName);

                    if (myOrderData != null) {

                        if (marshMallowPermissions.checkPermissionForPhone()) {
                            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "18005726067"));
                            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
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

                        if (Util.isDeviceOnline(this)) {
                            myOrderPresenter.cancelOrder(myOrderData.getmOrderId(), cancelReason);
                            addContactDialog.dismiss();
                            Util.hideKeyBoardMethod(this, view);
                        }else{
                            Util.showNoInternetDialog(this);
                        }



                    } else {
                        showCenteredToast(ryParent,this, getString(R.string.order_cancel_reason),"");

                    }


                    break;

                case R.id.lyOrder:
                    myOrderData = ((MyOrderResponse.MyOrderData) view.getTag());

                    if (myOrderData != null) {

                        Intent intent = new Intent(MyOrderActivity.this, MyOrderDetailsActivity.class);
                        intent.putExtra("orderId",myOrderData.getmOrderId());
                        startActivity(intent);
                    }
                    break;
                case R.id.txtTrack:
                    Intent intent = new Intent(MyOrderActivity.this, TimelineTest.class);
                    startActivity(intent);
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
            addContactDialog = new Dialog(this);
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
        reasonList.add("Other");

        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, reasonList);
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
