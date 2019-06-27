package com.ws.design.coco_ecommerce_ui_kit.my_order;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.wolfsoft2.coco_ecommerce_ui_kit.R;

import java.util.ArrayList;

import static com.ws.design.coco_ecommerce_ui_kit.utility.Util.dismissProDialog;
import static com.ws.design.coco_ecommerce_ui_kit.utility.Util.showCenteredToast;
import static com.ws.design.coco_ecommerce_ui_kit.utility.Util.showProDialog;

public class MyOrderActivity extends AppCompatActivity implements MyOrderView {

    TextView title;
    LinearLayout linearLayout;
    MyOrderAdapter myOrderAdapter;
    private RecyclerView rvMyOrder;
    private ArrayList<MyOrderResponse.MyOrderData> myOrderDataArrayList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order);

        MyOrderPresenter myOrderPresenter = new MyOrderPresenter(this);

        title = (TextView) findViewById(R.id.title);
        linearLayout = (LinearLayout) findViewById(R.id.linear);
        rvMyOrder = (RecyclerView) findViewById(R.id.rvMyOrder);

        title.setText("My Orders");
        linearLayout.setVisibility(View.GONE);


        myOrderPresenter.myOrder("87");

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

                myOrderAdapter = new MyOrderAdapter(this, myOrderDataArrayList);
                rvMyOrder.setAdapter(myOrderAdapter);
            }
        }



    }
}
