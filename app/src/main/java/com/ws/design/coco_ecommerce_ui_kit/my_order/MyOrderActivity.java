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
import android.widget.Spinner;
import android.widget.TextView;

import com.example.wolfsoft2.coco_ecommerce_ui_kit.R;
import com.ws.design.coco_ecommerce_ui_kit.DrawerActivity;
import com.ws.design.coco_ecommerce_ui_kit.my_wishlist.MyWishListResponse;
import com.ws.design.coco_ecommerce_ui_kit.shared_preference.CocoPreferences;
import com.ws.design.coco_ecommerce_ui_kit.utility.MarshMallowPermissions;
import com.ws.design.coco_ecommerce_ui_kit.utility.Util;

import java.util.ArrayList;
import java.util.List;

import static com.ws.design.coco_ecommerce_ui_kit.utility.Util.dismissProDialog;
import static com.ws.design.coco_ecommerce_ui_kit.utility.Util.showCenteredToast;
import static com.ws.design.coco_ecommerce_ui_kit.utility.Util.showProDialog;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order);

        myOrderPresenter = new MyOrderPresenter(this);

        title = (TextView) findViewById(R.id.title);
        linearLayout = (LinearLayout) findViewById(R.id.linear);
        rvMyOrder = (RecyclerView) findViewById(R.id.rvMyOrder);
        imgBack = findViewById(R.id.imgBack);
        imgBack.setOnClickListener(this);

        title.setText("My Orders");
        linearLayout.setVisibility(View.GONE);


        if (Util.isDeviceOnline(this)) {

            myOrderPresenter.myOrder(CocoPreferences.getUserId());

        } else {
            showCenteredToast(this, getString(R.string.network_connection));

        }

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvMyOrder.setLayoutManager(layoutManager);


    }

    @Override
    public void showWait() {
        showProDialog(this);
    }

    @Override
    public void removeWait() {
        dismissProDialog();
    }

    @Override
    public void onFailure(String appErrorMessage) {
        showCenteredToast(this, appErrorMessage);
    }

    @Override
    public void onMyOrderList(MyOrderResponse myOrderResponse) {

        if (myOrderResponse != null) {
            if (!myOrderResponse.getmAddressData().isEmpty()) {
                myOrderDataArrayList.clear();
                myOrderDataArrayList.addAll(myOrderResponse.getmAddressData());

                myOrderAdapter = new MyOrderAdapter(this, myOrderDataArrayList, MyOrderActivity.this);
                rvMyOrder.setAdapter(myOrderAdapter);
            }
        }


    }

    @Override
    public void cancelOrder(CancelOrderResponse cancelOrderResponse) {
        if (!TextUtils.isEmpty(cancelOrderResponse.getmStatus()) && ("1".equalsIgnoreCase(cancelOrderResponse.getmStatus()))) {
            showCenteredToast(this, cancelOrderResponse.getmMessage());
            if (myOrderAdapter != null) {
                myOrderDataArrayList.remove(cancelOrderPosition);
                myOrderAdapter.notifyDataSetChanged();
            }
        } else {
            showCenteredToast(this, cancelOrderResponse.getmData());
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
                            marshMallowPermissions.requestPermissionForPhone();
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
                        myOrderPresenter.cancelOrder(myOrderData.getmOrderId(), cancelReason);
                        addContactDialog.dismiss();
                        Util.hideKeyBoardMethod(this, view);

                    } else {
                        showCenteredToast(this, getString(R.string.order_cancel_reason));

                    }


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
        reasonList.add("Change Address");
        reasonList.add("Product is not required anymore.");
        reasonList.add("Change Product");
        reasonList.add("Others");

        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, reasonList);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spReason.setAdapter(aa);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        try {
            cancelReason = (String) parent.getItemAtPosition(position);

            if (cancelReason.equalsIgnoreCase("others")) {
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
