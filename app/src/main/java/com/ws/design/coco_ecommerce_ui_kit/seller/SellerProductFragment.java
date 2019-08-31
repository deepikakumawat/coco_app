package com.ws.design.coco_ecommerce_ui_kit.seller;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
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
import com.ws.design.coco_ecommerce_ui_kit.home.HomeFragment;
import com.ws.design.coco_ecommerce_ui_kit.home.home_response.ProductData;
import com.ws.design.coco_ecommerce_ui_kit.product_details.AddToCartResponse;
import com.ws.design.coco_ecommerce_ui_kit.product_details.ProductDetailFragment;
import com.ws.design.coco_ecommerce_ui_kit.product_details.project_details_response.ProductDetailsSimilier;
import com.ws.design.coco_ecommerce_ui_kit.shared_preference.CocoPreferences;
import com.ws.design.coco_ecommerce_ui_kit.utility.Constant;
import com.ws.design.coco_ecommerce_ui_kit.utility.Util;

import java.util.ArrayList;

import fragment.FragmentManagerUtils;

import static com.ws.design.coco_ecommerce_ui_kit.utility.Util.dismissProDialog;
import static com.ws.design.coco_ecommerce_ui_kit.utility.Util.showCenteredToast;
import static com.ws.design.coco_ecommerce_ui_kit.utility.Util.showProDialog;

public class SellerProductFragment extends BaseFragment implements SellerView, View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {


    private SellerProductAdapter sellerProductAdapter;
    private RecyclerView rvSellerProduct;
    private ArrayList<ProductDetailsSimilier> sellerProductArrayList = new ArrayList<>();
    private SellerPresenter sellerPresenter;

    private View mView;
    private String sellerId;
    private ScrollView svNotFound;
    private LinearLayout lySellerProduct;
    private ShimmerFrameLayout mShimmerViewContainer;
    private RelativeLayout ryParent;
    private boolean isShimmerShow = true;
    private String sellerName;
    private Button btnGoToHome;
    private SwipeRefreshLayout pullDownRefreshCall;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mView = inflater.inflate(R.layout.fragment_seller, container, false);

        return mView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        sellerPresenter = new SellerPresenter(this);
        Bundle bundle = getArguments();
        sellerId = bundle.getString("sellerId");
        sellerName = bundle.getString("sellerName");

        ryParent = view.findViewById(R.id.ryParent);
        pullDownRefreshCall = (SwipeRefreshLayout) view.findViewById(R.id.pullDownRefreshCall);

        lySellerProduct = view.findViewById(R.id.lySellerProduct);
        svNotFound = view.findViewById(R.id.svNotFound);
        rvSellerProduct = view.findViewById(R.id.rvSellerProduct);
        mShimmerViewContainer = mView.findViewById(R.id.shimmer_view_container);

        btnGoToHome = view.findViewById(R.id.btnGoToHome);
        btnGoToHome.setOnClickListener(this);

        pullDownRefreshCall.setOnRefreshListener(this);
        pullDownRefreshCall.setColorSchemeResources(R.color.red, R.color.red, R.color.red, R.color.red);


        callAPI();


        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rvSellerProduct.setLayoutManager(layoutManager);

    }

    private void callAPI() {
        if (Util.isDeviceOnline(getActivity())) {
            sellerPresenter.getSellerProduct(sellerId);
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

        setPullToRefreshFalse();

    }


    @Override
    public void onFailure(String appErrorMessage) {
        svNotFound.setVisibility(View.GONE);
        lySellerProduct.setVisibility(View.VISIBLE);
        showCenteredToast(ryParent, getActivity(), appErrorMessage, "");
    }

    private void setPullToRefreshFalse() {
        if (pullDownRefreshCall.isRefreshing()) {
            pullDownRefreshCall.setRefreshing(false);
        }
    }

    @Override
    public void getSellerProduct(SellerResponse sellerResponse) {
        if (sellerResponse != null) {

            if (sellerResponse.getmData() != null) {

                if (!sellerResponse.getmData().getmSellerProducts().isEmpty()) {

                    svNotFound.setVisibility(View.GONE);
                    lySellerProduct.setVisibility(View.VISIBLE);

                    sellerProductArrayList.clear();
                    sellerProductArrayList.addAll(sellerResponse.getmData().getmSellerProducts());

                    sellerProductAdapter = new SellerProductAdapter(getActivity(), sellerProductArrayList, SellerProductFragment.this);
                    rvSellerProduct.setAdapter(sellerProductAdapter);


                } else {
                    lySellerProduct.setVisibility(View.GONE);
                    svNotFound.setVisibility(View.VISIBLE);
                }
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
                case R.id.imgAddToCart:
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

                case R.id.ly_root:

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
        if (sellerProductAdapter != null) {
            sellerProductAdapter.notifyDataSetChanged();
        }
        callAPI();
    }
}
