package com.ws.design.coco_ecommerce_ui_kit.my_cart;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.wolfsoft2.coco_ecommerce_ui_kit.R;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.ws.design.coco_ecommerce_ui_kit.base_fragment.BaseFragment;
import com.ws.design.coco_ecommerce_ui_kit.checkout.CheckoutFragment;
import com.ws.design.coco_ecommerce_ui_kit.departments.DepartmentFragment;
import com.ws.design.coco_ecommerce_ui_kit.product_details.AddToCartResponse;
import com.ws.design.coco_ecommerce_ui_kit.product_details.AddToWishListResponse;
import com.ws.design.coco_ecommerce_ui_kit.product_details.ProductDetailFragment;
import com.ws.design.coco_ecommerce_ui_kit.shared_preference.CocoPreferences;
import com.ws.design.coco_ecommerce_ui_kit.utility.Constant;
import com.ws.design.coco_ecommerce_ui_kit.utility.Util;

import java.util.ArrayList;


import fragment.FragmentManagerUtils;


import static com.ws.design.coco_ecommerce_ui_kit.utility.Util.dismissProDialog;
import static com.ws.design.coco_ecommerce_ui_kit.utility.Util.showCenteredToast;
import static com.ws.design.coco_ecommerce_ui_kit.utility.Util.showProDialog;

public class CartFragment extends BaseFragment implements CartView, View.OnClickListener {

    private ArrayList<CartListResponse.ProductData> productDataArrayList = new ArrayList<>();
    private RecyclerView rvCart;
    private CartPresenter cartPresenter;
    private CartListAdapter cartListAdapter;
    private int removeCorssPostion = -1;
    private TextView txtEmptyCart;
    private Button btnCheckout;
    private TextView txtTotalPrice;
    private String totalPrice;
    private LinearLayout lyBottomView;
    private View mView;
    private ScrollView svEmptyCartView;
    private ShimmerFrameLayout mShimmerViewContainer;
    private RelativeLayout ryParent;
    private Button btnAddSomething;
    boolean isShimmerShow = true;
    private String moveWishListCatId;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mView = inflater.inflate(R.layout.fragment_cart, container, false);


        return mView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        cartPresenter = new CartPresenter(this);


        init();


    }

    private void init() {
        ryParent = mView.findViewById(R.id.ryParent);
        mShimmerViewContainer = mView.findViewById(R.id.shimmer_view_container);
        lyBottomView = mView.findViewById(R.id.lyBottomView);
        svEmptyCartView = mView.findViewById(R.id.svEmptyCartView);
        txtTotalPrice = mView.findViewById(R.id.txtTotalPrice);
        btnCheckout = mView.findViewById(R.id.btnCheckout);
        txtEmptyCart = mView.findViewById(R.id.txtEmptyCart);
        btnAddSomething = mView.findViewById(R.id.btnAddSomething);
        rvCart = mView.findViewById(R.id.rvCart);
        btnCheckout.setOnClickListener(this);
        txtEmptyCart.setOnClickListener(this);
        btnAddSomething.setOnClickListener(this);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        rvCart.setLayoutManager(mLayoutManager);
        rvCart.setItemAnimator(new DefaultItemAnimator());

        callApi();

    }

    private void callApi() {
        if (Util.isDeviceOnline(getActivity())) {
            cartPresenter.getCartList(CocoPreferences.getUserId());
        } else {
            Util.showNoInternetDialog(getActivity());
        }
    }

    @Override
    public void showWait() {

        if (isShimmerShow) {
            mShimmerViewContainer.setVisibility(View.VISIBLE);
            mShimmerViewContainer.startShimmerAnimation();
        } else {
            showProDialog(getActivity());

        }
    }

    @Override
    public void removeWait() {
        if (isShimmerShow) {
            mShimmerViewContainer.stopShimmerAnimation();
            mShimmerViewContainer.setVisibility(View.GONE);
        } else {
            dismissProDialog();
        }

    }

    @Override
    public void onFailure(String appErrorMessage) {

        if (!TextUtils.isEmpty(moveWishListCatId)) {
            showCenteredToast(ryParent, getActivity(), appErrorMessage, "");

        } else {
            noDataFound();
        }

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
            showCenteredToast(ryParent, getActivity(), emptyCartResponse.getmMessage(), Constant.API_SUCCESS);
            productDataArrayList.clear();
            cartListAdapter.notifyDataSetChanged();

            noDataFound();


        } else {
            showCenteredToast(ryParent, getActivity(), emptyCartResponse.getmMessage(), "");
        }
    }

    @Override
    public void removeCartOneByOne(RemoveCartOneByOneResponse removeCartOneByOneResponse) {
        if (!TextUtils.isEmpty(removeCartOneByOneResponse.getmStatus()) && ("1".equalsIgnoreCase(removeCartOneByOneResponse.getmStatus()))) {
            showCenteredToast(ryParent, getActivity(), removeCartOneByOneResponse.getmMessage(), Constant.API_SUCCESS);

            if (!removeCartOneByOneResponse.getmData().getmProductData().isEmpty()) {


                productDataArrayList.clear();
                productDataArrayList.addAll(removeCartOneByOneResponse.getmData().getmProductData());
                totalPrice = removeCartOneByOneResponse.getmData().getmTotalPrice();

                setAdapter(productDataArrayList, totalPrice);


            } else {
                noDataFound();
            }


        } else {
            showCenteredToast(ryParent, getActivity(), removeCartOneByOneResponse.getmMessage(), "");
        }
    }

    @Override
    public void removeCartByCross(RemoveCartByCrossResponse removeCartByCrossResponse) {
        if (!TextUtils.isEmpty(removeCartByCrossResponse.getmStatus()) && ("1".equalsIgnoreCase(removeCartByCrossResponse.getmStatus()))) {
            showCenteredToast(ryParent, getActivity(), removeCartByCrossResponse.getmMessage(), Constant.API_SUCCESS);

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
                totalPrice = removeCartByCrossResponse.getmData().getmTotalPrice();
                setAdapter(productDataArrayList, totalPrice);
                if (removeCartByCrossResponse.getmData().getmProductData().isEmpty()) {
                    noDataFound();

                }
            }

        } else {
            showCenteredToast(ryParent, getActivity(), removeCartByCrossResponse.getmMessage(), "");
        }
    }

    @Override
    public void onClick(View view) {
        CartListResponse.ProductData productData;
        try {
            int vId = view.getId();
            switch (vId) {
                case R.id.txtEmptyCart:
                    if (Util.isDeviceOnline(getActivity())) {
                        cartPresenter.emptyCart(CocoPreferences.getUserId());

                    } else {
                        Util.showNoInternetDialog(getActivity());
                    }
                    break;

                case R.id.lyRemove:
                    productData = ((CartListResponse.ProductData) view.getTag());
                    removeCorssPostion = (int) view.getTag(R.id.lyRemove);
                    if (productData != null) {
                        if (Util.isDeviceOnline(getActivity())) {
                            isShimmerShow = false;
                            cartPresenter.removeCartByCross(productData.getmCartId());
                        } else {
                            Util.showNoInternetDialog(getActivity());
                        }

                    }
                    break;
                case R.id.lyMovewToWishlist:
                    productData = ((CartListResponse.ProductData) view.getTag());
                    removeCorssPostion = (int) view.getTag(R.id.lyMovewToWishlist);
                    if (productData != null) {
                        if (Util.isDeviceOnline(getActivity())) {
                            isShimmerShow = false;
                            moveWishListCatId = productData.getmCartId();
                            cartPresenter.addToWishList(CocoPreferences.getUserId(), productData.getmProductId());
                        } else {
                            Util.showNoInternetDialog(getActivity());
                        }

                    }
                    break;
                case R.id.txtProductName:
                    productData = ((CartListResponse.ProductData) view.getTag());
                    if (productData != null) {

                        Bundle bundle = new Bundle();
                        bundle.putString("productSlug", productData.getmProductSlug());
                        bundle.putString("productId", productData.getmProductId());
                        bundle.putString("productQty", productData.getmQuantity());

                        ProductDetailFragment productDetailFragment = new ProductDetailFragment();
                        productDetailFragment.setArguments(bundle);

                        FragmentManagerUtils.replaceFragmentInRoot(getActivity().getSupportFragmentManager(), productDetailFragment, "ProductDetailFragment", true, false);

                    }
                    break;
                case R.id.btnAddSomething:
                    FragmentManagerUtils.replaceFragmentInRoot(getActivity().getSupportFragmentManager(), new DepartmentFragment(), "DepartmentFragment", true, false);
                    break;
                case R.id.btnCheckout:

                    if (!productDataArrayList.isEmpty()) {


                        Bundle bundle = new Bundle();
                        bundle.putSerializable("cartList", productDataArrayList);
                        bundle.putString("totalPrice", totalPrice);

                        CheckoutFragment checkoutFragment = new CheckoutFragment();
                        checkoutFragment.setArguments(bundle);

                        FragmentManagerUtils.replaceFragmentInRoot(getActivity().getSupportFragmentManager(), checkoutFragment, "CheckoutFragment", true, false);


                    } else {
                        showCenteredToast(ryParent, getActivity(), getString(R.string.your_cart_empty), "");
                    }


                    break;
                case R.id.lyIncrement:
                    productData = ((CartListResponse.ProductData) view.getTag());
                    TextView txtIncDec = (TextView) view.getTag(R.id.lyIncrement);
                    if (productData != null) {
                        int count = Integer.parseInt(String.valueOf(txtIncDec.getText()));
                        count++;

                        if (Util.isDeviceOnline(getActivity())) {
                            isShimmerShow = false;
                            cartPresenter.addToCart(CocoPreferences.getUserId(), productData.getmProductId(), String.valueOf(count));

                        } else {
                            Util.showNoInternetDialog(getActivity());
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

                            if (Util.isDeviceOnline(getActivity())) {
                                isShimmerShow = false;
                                cartPresenter.removeCartOneByOne(CocoPreferences.getUserId(), productData.getmProductId(), String.valueOf(count));

                            } else {
                                Util.showNoInternetDialog(getActivity());
                            }
                        } else {
                            if (Util.isDeviceOnline(getActivity())) {
                                isShimmerShow = false;
                                cartPresenter.removeCartByCross(productData.getmCartId());

                            } else {
                                Util.showNoInternetDialog(getActivity());
                            }
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
    public void addToWishList(AddToWishListResponse addToWishListResponse) {
        if (!TextUtils.isEmpty(addToWishListResponse.getmStatus()) && ("1".equalsIgnoreCase(addToWishListResponse.getmStatus()))) {
            showCenteredToast(ryParent, getActivity(), addToWishListResponse.getmMessage(), Constant.API_SUCCESS);

            if (!TextUtils.isEmpty(moveWishListCatId)) {
                cartPresenter.removeCartByCross(moveWishListCatId);

            }


        } else {
            showCenteredToast(ryParent, getActivity(), addToWishListResponse.getmMessage(), "");
        }
    }


    @Override
    public void addToCart(AddToCartResponse addToCartResponse) {
        if (!TextUtils.isEmpty(addToCartResponse.getmStatus()) && ("1".equalsIgnoreCase(addToCartResponse.getmStatus()))) {
            showCenteredToast(ryParent, getActivity(), addToCartResponse.getmMessage(), Constant.API_SUCCESS);

            if (!addToCartResponse.getmData().getmProductData().isEmpty()) {
                productDataArrayList.clear();
                productDataArrayList.addAll(addToCartResponse.getmData().getmProductData());

            }
            totalPrice = addToCartResponse.getmData().getmTotalPrice();

            setAdapter(productDataArrayList, totalPrice);


        } else {
            showCenteredToast(ryParent, getActivity(), addToCartResponse.getmMessage(), "");
        }
    }

    private void setAdapter(ArrayList<CartListResponse.ProductData> productDataArrayList, String totalPrice) {

        rvCart.setVisibility(View.VISIBLE);
        lyBottomView.setVisibility(View.VISIBLE);
        svEmptyCartView.setVisibility(View.GONE);


        if (cartListAdapter != null) {
            cartListAdapter.notifyDataSetChanged();
        } else {
            cartListAdapter = new CartListAdapter(getActivity(), productDataArrayList, CartFragment.this);
            rvCart.setAdapter(cartListAdapter);
        }
        txtTotalPrice.setText(!TextUtils.isEmpty(totalPrice) ? getString(R.string.Rs) + totalPrice : "-");

    }


    private void noDataFound() {
        svEmptyCartView.setVisibility(View.VISIBLE);
        rvCart.setVisibility(View.GONE);
        lyBottomView.setVisibility(View.GONE);
    }


    @Override
    protected boolean isCartIconVisible() {
        return false;
    }

    @Override
    protected boolean isSearchIconVisible() {
        return false;
    }

    @Override
    protected String getActionbarTitle() {
        return getString(R.string.my_cart);
    }
}
