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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.wolfsoft2.coco_ecommerce_ui_kit.R;
import com.ws.design.coco_ecommerce_ui_kit.checkout.CheckoutActivity;
import com.ws.design.coco_ecommerce_ui_kit.product_details.AddToCartResponse;
import com.ws.design.coco_ecommerce_ui_kit.product_details.ProductDetailFragment;
import com.ws.design.coco_ecommerce_ui_kit.shared_preference.CocoPreferences;
import com.ws.design.coco_ecommerce_ui_kit.utility.Util;

import java.util.ArrayList;


import fragment.FragmentManagerUtils;

import static com.ws.design.coco_ecommerce_ui_kit.utility.Util.dismissProDialog;
import static com.ws.design.coco_ecommerce_ui_kit.utility.Util.showCenteredToast;
import static com.ws.design.coco_ecommerce_ui_kit.utility.Util.showProDialog;

public class CartActivity extends AppCompatActivity implements CartView, View.OnClickListener {

    private ArrayList<CartListResponse.ProductData> productDataArrayList = new ArrayList<>();
    private RecyclerView rvCart;
    private CartPresenter cartPresenter;
    private CartListAdapter cartListAdapter;
    private int removeCorssPostion = -1;
    private ImageView imgBack;
    private TextView txtEmptyCart;
    private TextView txtCheckout;
    private TextView txtTotalPrice;
    private String totalPrice;
    private TextView txtNoDataFound;
    private LinearLayout lyBottomView;
    private LinearLayout lyCart;
    private TextView txtContinueShopping;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        cartPresenter = new CartPresenter(this);

        if (Util.isDeviceOnline(this)) {
            cartPresenter.getCartList(CocoPreferences.getUserId());
        } else {
            showCenteredToast(this, getString(R.string.network_connection));

        }

        init();

    }

    private void init() {
        txtContinueShopping = findViewById(R.id.txtContinueShopping);
        lyCart = findViewById(R.id.lyCart);
        lyBottomView = findViewById(R.id.lyBottomView);
        txtNoDataFound = findViewById(R.id.txtNoDataFound);
        txtTotalPrice = findViewById(R.id.txtTotalPrice);
        txtCheckout = findViewById(R.id.txtCheckout);
        imgBack = findViewById(R.id.imgBack);
        txtEmptyCart = findViewById(R.id.txtEmptyCart);
        imgBack.setOnClickListener(this);
        txtCheckout.setOnClickListener(this);
        txtEmptyCart.setOnClickListener(this);
        txtContinueShopping.setOnClickListener(this);
        rvCart = findViewById(R.id.rvCart);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(CartActivity.this);
        rvCart.setLayoutManager(mLayoutManager);
        rvCart.setItemAnimator(new DefaultItemAnimator());
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
        noDataFound();
    }

    @Override
    public void getCartList(CartListResponse cartListResponse) {
        if (cartListResponse != null) {
            if (!cartListResponse.getmCartlistData().getmProductData().isEmpty()) {
                productDataArrayList.clear();
                productDataArrayList.addAll(cartListResponse.getmCartlistData().getmProductData());
                totalPrice = cartListResponse.getmCartlistData().getmTotalPrice();

                setAdapter(productDataArrayList, totalPrice);

            } else {
                noDataFound();
            }

        }

    }

    @Override
    public void emptyCart(EmptyCartResponse emptyCartResponse) {
        if (!TextUtils.isEmpty(emptyCartResponse.getmStatus()) && ("1".equalsIgnoreCase(emptyCartResponse.getmStatus()))) {
            showCenteredToast(this, emptyCartResponse.getmMessage());
            productDataArrayList.clear();
            cartListAdapter.notifyDataSetChanged();

            noDataFound();


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
                totalPrice = removeCartOneByOneResponse.getmData().getmTotalPrice();

                setAdapter(productDataArrayList, totalPrice);


            } else {
                noDataFound();
            }


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

                    totalPrice = removeCartByCrossResponse.getmData().getmTotalPrice();

                    setAdapter(productDataArrayList, totalPrice);

                } else {
                    noDataFound();
                }

            } else {
                productDataArrayList.remove(removeCorssPostion);
                cartListAdapter.notifyDataSetChanged();
                if (removeCartByCrossResponse.getmData().getmProductData().isEmpty()) {
                    noDataFound();

                }
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

                    } else {
                        showCenteredToast(this, getString(R.string.network_connection));

                    }
                    break;

                case R.id.imgCross:
                    productData = ((CartListResponse.ProductData) view.getTag());
                    removeCorssPostion = (int) view.getTag(R.id.imgCross);
                    if (productData != null) {
                        if (Util.isDeviceOnline(this)) {
                            cartPresenter.removeCartByCross(productData.getmCartId());
                        } else {
                            showCenteredToast(this, getString(R.string.network_connection));
                        }

                    }
                    break;
                case R.id.lyCartProduct:
                    productData = ((CartListResponse.ProductData) view.getTag());
                    if (productData != null) {

                        Bundle bundle = new Bundle();
                        bundle.putString("productSlug", productData.getmProductSlug());
                        bundle.putString("productId", productData.getmProductId());
                        bundle.putString("productQty", productData.getmQuantity());

                        ProductDetailFragment productDetailFragment = new ProductDetailFragment();
                        productDetailFragment.setArguments(bundle);

                        FragmentManagerUtils.replaceFragmentInRoot(getSupportFragmentManager(), productDetailFragment, "ProductDetailFragment", true, false);

                    }
                    break;
                case R.id.txtContinueShopping:
                case R.id.imgBack:
                    finish();
                    break;
                case R.id.txtCheckout:

                    if (!productDataArrayList.isEmpty()) {
                        Intent intent = new Intent(CartActivity.this, CheckoutActivity.class);
                        intent.putExtra("cartList", productDataArrayList);
                        intent.putExtra("totalPrice", totalPrice);
                        startActivity(intent);
                    } else {
                        showCenteredToast(this, getString(R.string.your_cart_empty));
                    }


                    break;
                case R.id.lyIncrement:
                    productData = ((CartListResponse.ProductData) view.getTag());
                    TextView txtIncDec = (TextView) view.getTag(R.id.lyIncrement);
                    if (productData != null) {
                        int count = Integer.parseInt(String.valueOf(txtIncDec.getText()));
                        count++;

                        if (Util.isDeviceOnline(this)) {
                            cartPresenter.addToCart(CocoPreferences.getUserId(), productData.getmProductId(), String.valueOf(count));

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
                            cartPresenter.removeCartOneByOne(CocoPreferences.getUserId(), productData.getmProductId(), String.valueOf(count));
                        } else {
                            cartPresenter.removeCartByCross(productData.getmCartId());
                        }
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

            }
            totalPrice = addToCartResponse.getmData().getmTotalPrice();

            setAdapter(productDataArrayList, totalPrice);


        } else {
            showCenteredToast(this, addToCartResponse.getmMessage());
        }
    }

    private void setAdapter(ArrayList<CartListResponse.ProductData> productDataArrayList, String totalPrice) {

        txtNoDataFound.setVisibility(View.GONE);
        txtContinueShopping.setVisibility(View.GONE);
        lyCart.setVisibility(View.VISIBLE);
        lyBottomView.setVisibility(View.VISIBLE);

        if (cartListAdapter != null) {
            cartListAdapter.notifyDataSetChanged();
        } else {
            cartListAdapter = new CartListAdapter(this, productDataArrayList, CartActivity.this);
            rvCart.setAdapter(cartListAdapter);
        }
        txtTotalPrice.setText(!TextUtils.isEmpty(totalPrice) ? getString(R.string.Rs) + totalPrice : "-");

    }

    private void noDataFound() {
        txtNoDataFound.setVisibility(View.VISIBLE);
        txtContinueShopping.setVisibility(View.VISIBLE);
        lyCart.setVisibility(View.GONE);
        lyBottomView.setVisibility(View.GONE);
    }
}
