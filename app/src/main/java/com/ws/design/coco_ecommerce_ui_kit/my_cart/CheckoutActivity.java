package com.ws.design.coco_ecommerce_ui_kit.my_cart;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.wolfsoft2.coco_ecommerce_ui_kit.R;
import com.ws.design.coco_ecommerce_ui_kit.shared_preference.CocoPreferences;
import com.ws.design.coco_ecommerce_ui_kit.utility.Util;

import java.util.ArrayList;

import static com.ws.design.coco_ecommerce_ui_kit.utility.Util.dismissProDialog;
import static com.ws.design.coco_ecommerce_ui_kit.utility.Util.showCenteredToast;
import static com.ws.design.coco_ecommerce_ui_kit.utility.Util.showProDialog;

public class CheckoutActivity extends AppCompatActivity implements CartView, View.OnClickListener {

    //    private RecyclerView recyclerView;

//    private ArrayList<CocoCartModel2> navigationModelClasses1;
//    private RecyclerView recyclerView1;
//    private RecycleAdapterCocoCart1 bAdapter;

    RadioButton button1, button2;
    LinearLayout radio1, radio2;


    private RecyclerView recyclerView2;
    //    private RecycleAdapterCocoCart2 cAdapter;
    private CheckoutListAdapter checkoutListAdapter;

    String rupees[] = {"$329", "$450"};
    String phoneName[] = {"Iphone X | 128 GB, Black | 6 GB ram |\n" +
            "12000 mAh battry", "Mi Mix 2 (Black, 128 GB) 6GB Ram \n" +
            "with 5000 mAh…"};
    Integer phoneImage[] = {R.drawable.mobile1, R.drawable.mobile2};


    String deliverType[] = {"Express Delivery", "Normal Delivery"};
    String noOfDays[] = {"1 day Delivery", "Upto 5 Business Days"};
    String amount[] = {"+$9.00", ""};


    String paymentType[] = {"Credit / Debit Card", "Cash On Delivery", "PAYTM", "Google Wallet"};
    Integer logoImage[] = {R.drawable.credit, R.drawable.cash, R.drawable.paytm, R.drawable.googlewallet};

    private CartPresenter cartPresenter;
    private RecyclerView rvCart;
    private ArrayList<CartListResponse.ProductData> productDataArrayList = new ArrayList<>();
    private TextView txtEmptyCart;
    private int removeOnByOnePostion = -1;
    private int removeCorssPostion = -1;
    private ImageView imgBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        cartPresenter = new CartPresenter(this);
        rvCart = findViewById(R.id.rvCart);
        imgBack = findViewById(R.id.imgBack);
        txtEmptyCart = findViewById(R.id.txtEmptyCart);
        txtEmptyCart.setOnClickListener(this);

        if (Util.isDeviceOnline(this)) {
            cartPresenter.getCartList(CocoPreferences.getUserId());

        }else{
            showCenteredToast(this, getString(R.string.network_connection));

        }


        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvCart.setLayoutManager(layoutManager);

        imgBack.setOnClickListener(this);


    /*    navigationModelClasses = new ArrayList<>();
        for (int i = 0; i < rupees.length; i++) {
            CocoCartModel1 beanClassForRecyclerView_contacts = new CocoCartModel1(phoneName[i], rupees[i], phoneImage[i]);

            navigationModelClasses.add(beanClassForRecyclerView_contacts);
        }
        mAdapter = new RecycleAdapterCocoCart0(CheckoutActivity.this, navigationModelClasses);*/

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(CheckoutActivity.this);
      /*  recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
*/

        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        radio1 = findViewById(R.id.radio1);
        radio2 = findViewById(R.id.radio2);

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


//
//        recyclerView1 = (RecyclerView)findViewById(R.id.recycler2);
//
//        navigationModelClasses1 = new ArrayList<>();
//
//
//
//        for (int i = 0; i < deliverType.length; i++) {
//            CocoCartModel2 beanClassForRecyclerView_contacts = new CocoCartModel2(deliverType[i],noOfDays[i],amount[i]);
//
//            navigationModelClasses1.add(beanClassForRecyclerView_contacts);
//        }
//
//
//        bAdapter = new RecycleAdapterCocoCart1(CheckoutActivity.this,navigationModelClasses1);
//
//        RecyclerView.LayoutManager bLayoutManager = new LinearLayoutManager(CheckoutActivity.this);
//        recyclerView1.setLayoutManager(bLayoutManager);
//        recyclerView1.setItemAnimator(new DefaultItemAnimator());
//        recyclerView1.setAdapter(bAdapter);


     /*   recyclerView2 = (RecyclerView) findViewById(R.id.recycler3);
        navigationModelClasses2 = new ArrayList<>();
        for (int i = 0; i < paymentType.length; i++) {
            CocoCartModel3 beanClassForRecyclerView_contacts = new CocoCartModel3(paymentType[i], logoImage[i]);

            navigationModelClasses2.add(beanClassForRecyclerView_contacts);
        }*/
      /*  cAdapter = new RecycleAdapterCocoCart2(CheckoutActivity.this,navigationModelClasses2);

        RecyclerView.LayoutManager cLayoutManager = new LinearLayoutManager(CheckoutActivity.this);
        recyclerView2.setLayoutManager(cLayoutManager);
        recyclerView2.setItemAnimator(new DefaultItemAnimator());
        recyclerView2.setAdapter(cAdapter);

*/

    }

    @Override
    public void onClick(View view) {
        CartListResponse.ProductData productData;
        try {
            int vId = view.getId();
            switch (vId) {
                case R.id.txtEmptyCart:
                    if (Util.isDeviceOnline(this)) {
                        cartPresenter.emptyCart(CocoPreferences.getUserId());

                    }else{
                        showCenteredToast(this, getString(R.string.network_connection));

                    }                    break;
                case R.id.txtOneByOne:
                    productData = ((CartListResponse.ProductData) view.getTag());
                    removeOnByOnePostion = (int) view.getTag(R.id.txtOneByOne);
                    if (productData != null) {

                        if (Util.isDeviceOnline(this)) {
                            cartPresenter.removeCartOneByOne(CocoPreferences.getUserId(), productData.getmProductId(), productData.getmQuantity());

                        }else{
                            showCenteredToast(this, getString(R.string.network_connection));

                        }


                    }
                    break;
                case R.id.txtCross:
                    productData = ((CartListResponse.ProductData) view.getTag());
                    removeCorssPostion = (int) view.getTag(R.id.txtCross);
                    if (productData != null) {

                        if (Util.isDeviceOnline(this)) {

                            cartPresenter.removeCartByCross(productData.getmCartId());

                        }else{
                            showCenteredToast(this, getString(R.string.network_connection));

                        }
                    }
                    break;
                case R.id.imgBack:
                    finish();
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
    public void getCartList(CartListResponse cartListResponse) {
        if (cartListResponse != null) {
            if (!cartListResponse.getmCartlistData().getmProductData().isEmpty()) {
                productDataArrayList.clear();
                productDataArrayList.addAll(cartListResponse.getmCartlistData().getmProductData());

                checkoutListAdapter = new CheckoutListAdapter(this, productDataArrayList, CheckoutActivity.this);
                rvCart.setAdapter(checkoutListAdapter);
            }
        }

    }

    @Override
    public void emptyCart(EmptyCartResponse emptyCartResponse) {
        if (!TextUtils.isEmpty(emptyCartResponse.getmStatus()) && ("1".equalsIgnoreCase(emptyCartResponse.getmStatus()))) {
            showCenteredToast(this, emptyCartResponse.getmMessage());

        } else {
            showCenteredToast(this, emptyCartResponse.getmMessage());
        }
    }

    @Override
    public void removeCartOneByOne(RemoveCartOneByOneResponse removeCartOneByOneResponse) {
        if (!TextUtils.isEmpty(removeCartOneByOneResponse.getmStatus()) && ("1".equalsIgnoreCase(removeCartOneByOneResponse.getmStatus()))) {
            showCenteredToast(this, removeCartOneByOneResponse.getmMessage());

        } else {
            showCenteredToast(this, removeCartOneByOneResponse.getmMessage());
        }
    }

    @Override
    public void removeCartByCross(RemoveCartByCrossResponse removeCartByCrossResponse) {
        if (!TextUtils.isEmpty(removeCartByCrossResponse.getmStatus()) && ("1".equalsIgnoreCase(removeCartByCrossResponse.getmStatus()))) {
            showCenteredToast(this, removeCartByCrossResponse.getmMessage());

        } else {
            showCenteredToast(this, removeCartByCrossResponse.getmMessage());
        }
    }

}
