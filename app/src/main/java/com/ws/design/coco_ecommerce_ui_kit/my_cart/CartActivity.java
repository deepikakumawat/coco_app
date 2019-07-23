package com.ws.design.coco_ecommerce_ui_kit.my_cart;

import android.content.Intent;
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
import com.ws.design.coco_ecommerce_ui_kit.checkout.CheckoutActivity;
import com.ws.design.coco_ecommerce_ui_kit.product_details.AddToCartResponse;
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

    String rupees[] = {"$329", "$450", "$90"};
    String phoneName[] = {"Iphone X | 128 GB, Black | 6 GB ram |\n" +
            "12000 mAh battry", "Mi Mix 2 (Black, 128 GB) 6GB Ram \n" +
            "with 5000 mAh…", "Food container, set of 3 container,\n" +
            "yellow color"};
    Integer phoneImage[] = {R.drawable.mobile1, R.drawable.mobile2, R.drawable.mobile1};
    private CartPresenter cartPresenter;
    private CartListAdapter cartListAdapter;
    private int removeCorssPostion = -1;
    private ImageView imgBack;
    private TextView txtEmptyCart;
    private TextView txtCheckout;
    private TextView txtTotalPrice;
    private String totalPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        cartPresenter = new CartPresenter(this);

        if (Util.isDeviceOnline(this)) {
            cartPresenter.getCartList(CocoPreferences.getUserId());

        }else{
            showCenteredToast(this, getString(R.string.network_connection));

        }


        txtTotalPrice = findViewById(R.id.txtTotalPrice);
        txtCheckout = findViewById(R.id.txtCheckout);
        imgBack = findViewById(R.id.imgBack);
        txtEmptyCart = findViewById(R.id.txtEmptyCart);
        imgBack.setOnClickListener(this);
        txtCheckout.setOnClickListener(this);
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
            totalPrice = cartListResponse.getmCartlistData().getmTotalPrice();
            txtTotalPrice.setText(!TextUtils.isEmpty(totalPrice) ? totalPrice : "-");
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

            if (!removeCartOneByOneResponse.getmData().getmProductData().isEmpty()) {
                productDataArrayList.clear();
                productDataArrayList.addAll(removeCartOneByOneResponse.getmData().getmProductData());

                if (cartListAdapter != null) {
                    cartListAdapter.notifyDataSetChanged();
                } else {
                    cartListAdapter = new CartListAdapter(this, productDataArrayList, CartActivity.this);
                    rvCart.setAdapter(cartListAdapter);
                }
            }
            totalPrice = removeCartOneByOneResponse.getmData().getmTotalPrice();
            txtTotalPrice.setText(!TextUtils.isEmpty(totalPrice) ? totalPrice : "-");


        } else {
            showCenteredToast(this, removeCartOneByOneResponse.getmMessage());
        }
    }

    @Override
    public void removeCartByCross(RemoveCartByCrossResponse removeCartByCrossResponse) {
        if (!TextUtils.isEmpty(removeCartByCrossResponse.getmStatus()) && ("1".equalsIgnoreCase(removeCartByCrossResponse.getmStatus()))) {
            showCenteredToast(this, removeCartByCrossResponse.getmMessage());

            if (removeCorssPostion == -1) {
                if (!removeCartByCrossResponse.getmData().getmProductData().isEmpty()) {
                    productDataArrayList.clear();
                    productDataArrayList.addAll(removeCartByCrossResponse.getmData().getmProductData());

                    if (cartListAdapter != null) {
                        cartListAdapter.notifyDataSetChanged();
                    } else {
                        cartListAdapter = new CartListAdapter(this, productDataArrayList, CartActivity.this);
                        rvCart.setAdapter(cartListAdapter);
                    }
                }
                totalPrice = removeCartByCrossResponse.getmData().getmTotalPrice();
                txtTotalPrice.setText(!TextUtils.isEmpty(totalPrice) ? totalPrice : "-");
            }else {
                productDataArrayList.remove(removeCorssPostion);
                cartListAdapter.notifyDataSetChanged();
            }

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
                case R.id.txtCheckout:
                    Intent intent = new Intent(CartActivity.this, CheckoutActivity.class);
                    intent.putExtra("cartList", productDataArrayList);
                    intent.putExtra("totalPrice", totalPrice);
                    startActivity(intent);
                    break;
                case R.id.lyIncrement:
                    productData = ((CartListResponse.ProductData) view.getTag());
                    TextView txtIncDec = (TextView) view.getTag(R.id.lyIncrement);
                    if (productData != null) {
                        int count = Integer.parseInt(String.valueOf(txtIncDec.getText()));
                        count++;
//                        txtIncDec.setText(String.valueOf(count));

                        if (Util.isDeviceOnline(this)) {
                            cartPresenter.addToCart(CocoPreferences.getUserId(), productData.getmProductId(), "1");

                        } else {
                            showCenteredToast(this, getString(R.string.network_connection));

                        }


                    }
                    break;
                case R.id.lyDecrement:
                    productData = ((CartListResponse.ProductData) view.getTag());
                    txtIncDec = (TextView) view.getTag(R.id.lyDecrement);
                    if (productData != null) {
                        int count = Integer.parseInt(String.valueOf(txtIncDec.getText()));
                        if (count > 1) {
                            count--;
                            cartPresenter.removeCartOneByOne(CocoPreferences.getUserId(),productData.getmProductId(),"1");
                        }else{
                            cartPresenter.removeCartByCross(productData.getmCartId());
                        }
//                        txtIncDec.setText(String.valueOf(count));

                    }
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
            showCenteredToast(this, addToCartResponse.getmMessage());

            if (!addToCartResponse.getmData().getmProductData().isEmpty()) {
                productDataArrayList.clear();
                productDataArrayList.addAll(addToCartResponse.getmData().getmProductData());

                if (cartListAdapter != null) {
                    cartListAdapter.notifyDataSetChanged();
                } else {
                    cartListAdapter = new CartListAdapter(this, productDataArrayList, CartActivity.this);
                    rvCart.setAdapter(cartListAdapter);
                }
            }
            totalPrice = addToCartResponse.getmData().getmTotalPrice();
            txtTotalPrice.setText(!TextUtils.isEmpty(totalPrice) ? totalPrice : "-");


        } else {
            showCenteredToast(this, addToCartResponse.getmMessage());
        }
    }
}
