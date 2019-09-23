package com.nav.richkart.deals.seller;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
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

import com.facebook.shimmer.ShimmerFrameLayout;
import com.nav.richkart.R;
import com.nav.richkart.base_fragment.BaseFragment;
import com.nav.richkart.fragment.FragmentManagerUtils;
import com.nav.richkart.home.HomeFragment;
import com.nav.richkart.product_details.AddToCartResponse;
import com.nav.richkart.product_details.ProductDetailFragment;
import com.nav.richkart.product_details.project_details_response.ProductDetailsSimilier;
import com.nav.richkart.shared_preference.CocoPreferences;
import com.nav.richkart.utility.Constant;
import com.nav.richkart.utility.Util;

import java.util.ArrayList;

import static com.nav.richkart.utility.Util.dismissProDialog;
import static com.nav.richkart.utility.Util.showCenteredToast;
import static com.nav.richkart.utility.Util.showProDialog;

public class DealsProductFragment extends BaseFragment implements DealsView, View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {


    private DealsProductAdapter dealsProductAdapter;
    private RecyclerView rvSellerProduct;
    private ArrayList<ProductDetailsSimilier> sellerProductArrayList = new ArrayList<>();
    private DealsPresenter sellerPresenter;

    private View mView;
    private ShimmerFrameLayout mShimmerViewContainer;
    private RelativeLayout ryParent;
    private boolean isShimmerShow = true;
    private String sellerName;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mView = inflater.inflate(R.layout.deals, container, false);

        return mView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        sellerPresenter = new DealsPresenter(this);


        ryParent = view.findViewById(R.id.ryParent);

        rvSellerProduct = view.findViewById(R.id.rvSellerProduct);
        mShimmerViewContainer = mView.findViewById(R.id.shimmer_view_container);


        callAPI();


        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(),2);
        rvSellerProduct.setLayoutManager(layoutManager);
        rvSellerProduct.setItemAnimator(new DefaultItemAnimator());
        rvSellerProduct.setNestedScrollingEnabled(false);
        rvSellerProduct.setFocusableInTouchMode(false);


    }

    private void callAPI() {
        if (Util.isDeviceOnline(getActivity())) {
            sellerPresenter.getSellerProduct();
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
        showCenteredToast(ryParent, getActivity(), appErrorMessage, "");
    }



    @Override
    public void getSellerProduct(DealsResponse dealsResponse) {
        if (dealsResponse != null) {


                if (!dealsResponse.getmSellerProducts().isEmpty()) {

                    sellerProductArrayList.clear();
                    sellerProductArrayList.addAll(dealsResponse.getmSellerProducts());

                    dealsProductAdapter = new DealsProductAdapter(getActivity(), sellerProductArrayList, DealsProductFragment.this);
                    rvSellerProduct.setAdapter(dealsProductAdapter);


                } else {

                }



        }
    }

    @Override
    public void addToCart(AddToCartResponse addToCartResponse) {
        if (!TextUtils.isEmpty(addToCartResponse.getmStatus()) && ("1".equalsIgnoreCase(addToCartResponse.getmStatus()))) {
            showCenteredToast(ryParent, getActivity(), addToCartResponse.getmMessage(), Constant.API_SUCCESS);


        } else {
            showCenteredToast(ryParent, getActivity(), addToCartResponse.getmMessage(), "");
        }
    }


    @Override
    public void onClick(View view) {
        try {
            int vId = view.getId();
            switch (vId) {
                case R.id.lyAddToCart:
                    ProductDetailsSimilier sellerProductData = ((ProductDetailsSimilier) view.getTag());

                    if (sellerProductData != null) {

                        if (Util.isDeviceOnline(getActivity())) {
                            isShimmerShow = false;
                            sellerPresenter.addToCart(CocoPreferences.getUserId(), sellerProductData.getmProductId(), "1");

                        } else {
                            Util.showNoInternetDialog(getActivity());
                        }

                    }
                    break;
                case R.id.btnGoToHome:
                    FragmentManagerUtils.replaceFragmentInRoot(getActivity().getSupportFragmentManager(), new HomeFragment(), "HomeFragment", true, false);

                    break;

                case R.id.lyProduct:

                    ProductDetailsSimilier   productData = (ProductDetailsSimilier) view.getTag();
//                    removeCorssPostion = (int) view.getTag(R.id.txtCross);
                    if (productData != null) {

                        Bundle  bundle = new Bundle();
                        bundle.putString("productSlug", productData.getmProductSlug());
                        bundle.putString("productId", productData.getmProductId());
                        bundle.putString("productQty", productData.getmProductQty());

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

    @Override
    protected String getActionbarTitle() {
        if (!TextUtils.isEmpty(sellerName)) {
            return sellerName;

        }
        return getString(R.string.products);

    }

    @Override
    protected boolean isSearchIconVisible() {
        return true;
    }

    @Override
    protected boolean isCartIconVisible() {
        return true;
    }

    @Override
    public void onRefresh() {
        sellerProductArrayList.clear();
        if (dealsProductAdapter != null) {
            dealsProductAdapter.notifyDataSetChanged();
        }
        callAPI();
    }
}
