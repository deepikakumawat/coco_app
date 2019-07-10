package com.ws.design.coco_ecommerce_ui_kit.my_cart;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wolfsoft2.coco_ecommerce_ui_kit.R;
import com.ws.design.coco_ecommerce_ui_kit.shared_preference.CocoPreferences;
import com.ws.design.coco_ecommerce_ui_kit.utility.Util;

import java.util.ArrayList;



import static com.ws.design.coco_ecommerce_ui_kit.utility.Util.dismissProDialog;
import static com.ws.design.coco_ecommerce_ui_kit.utility.Util.showCenteredToast;
import static com.ws.design.coco_ecommerce_ui_kit.utility.Util.showProDialog;

public class CartActivity extends AppCompatActivity implements CartView, View.OnClickListener {

    private ArrayList<CartListResponse.ProductData> productDataArrayList = new ArrayList<>();
    private RecyclerView rvCart;
//    private RecycleAdapterCocoCart mAdapter;

    String rupees[]={"$329","$450","$90"};
    String phoneName[]={"Iphone X | 128 GB, Black | 6 GB ram |\n" +
            "12000 mAh battry","Mi Mix 2 (Black, 128 GB) 6GB Ram \n" +
            "with 5000 mAh…","Food container, set of 3 container,\n" +
            "yellow color"};
    Integer phoneImage[]={R.drawable.mobile1, R.drawable.mobile2, R.drawable.mobile1};
    private CartPresenter cartPresenter;
    private CartListAdapter cartListAdapter;
    private int removeCorssPostion = -1;
    private ImageView imgBack;
    private TextView txtEmptyCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coco_cart1);

        cartPresenter = new CartPresenter(this);

        if (Util.isDeviceOnline(this)) {
            cartPresenter.getCartList(CocoPreferences.getUserId());

        }else{
            showCenteredToast(this, getString(R.string.network_connection));

        }


        imgBack = findViewById(R.id.imgBack);
        txtEmptyCart = findViewById(R.id.txtEmptyCart);
        imgBack.setOnClickListener(this);
        txtEmptyCart.setOnClickListener(this);
        rvCart = (RecyclerView)findViewById(R.id.rvCart);
       /* navigationModelClasses = new ArrayList<>();
        for (int i = 0; i < rupees.length; i++) {
            CocoCartModel1 beanClassForRecyclerView_contacts = new CocoCartModel1(phoneName[i],rupees[i],phoneImage[i]);

            navigationModelClasses.add(beanClassForRecyclerView_contacts);
        }
        mAdapter = new RecycleAdapterCocoCart(CartActivity.this,navigationModelClasses);
*/
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(CartActivity.this);
        rvCart.setLayoutManager(mLayoutManager);
        rvCart.setItemAnimator(new DefaultItemAnimator());
//        rvCart.setAdapter(mAdapter);

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

                cartListAdapter = new CartListAdapter(this, productDataArrayList, CartActivity.this);
                rvCart.setAdapter(cartListAdapter);
            }
        }

    }

    @Override
    public void emptyCart(EmptyCartResponse emptyCartResponse) {
        if (!TextUtils.isEmpty(emptyCartResponse.getmStatus()) && ("1".equalsIgnoreCase(emptyCartResponse.getmStatus()))) {
            showCenteredToast(this, emptyCartResponse.getmMessage());
            productDataArrayList.clear();
            cartListAdapter.notifyDataSetChanged();

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
            productDataArrayList.remove(removeCorssPostion);
            cartListAdapter.notifyDataSetChanged();

        } else {
            showCenteredToast(this, removeCartByCrossResponse.getmMessage());
        }
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
}
