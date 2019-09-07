package com.ws.design.coco_ecommerce_ui_kit.sub_sub_category;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.example.wolfsoft2.coco_ecommerce_ui_kit.R;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.ws.design.coco_ecommerce_ui_kit.home.HomeFragment;
import com.ws.design.coco_ecommerce_ui_kit.home.home_response.ProductData;
import com.ws.design.coco_ecommerce_ui_kit.product_by_category.ProductByCategoryAdapter;
import com.ws.design.coco_ecommerce_ui_kit.product_by_category.ProductByCategoryPresenter;
import com.ws.design.coco_ecommerce_ui_kit.product_by_category.ProductByCategoryRequest;
import com.ws.design.coco_ecommerce_ui_kit.product_by_category.ProductByCategoryResponse;
import com.ws.design.coco_ecommerce_ui_kit.product_by_category.ProductByCategoryView;
import com.ws.design.coco_ecommerce_ui_kit.product_by_category.ProductListByCategoryFragment;
import com.ws.design.coco_ecommerce_ui_kit.product_details.AddToCartResponse;
import com.ws.design.coco_ecommerce_ui_kit.product_details.ProductDetailFragment;
import com.ws.design.coco_ecommerce_ui_kit.shared_preference.CocoPreferences;
import com.ws.design.coco_ecommerce_ui_kit.utility.Constant;
import com.ws.design.coco_ecommerce_ui_kit.utility.Util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import fragment.FragmentManagerUtils;

import static com.ws.design.coco_ecommerce_ui_kit.utility.Util.dismissProDialog;
import static com.ws.design.coco_ecommerce_ui_kit.utility.Util.showCenteredToast;
import static com.ws.design.coco_ecommerce_ui_kit.utility.Util.showProDialog;


public class SubSubCategoryProductListFragment extends Fragment implements View.OnClickListener, ProductByCategoryView {

    private ArrayList<ProductData> productGridModellClasses;
    private RecyclerView recyclerview;
    private ProductByCategoryAdapter mAdapter2;
    private ProductByCategoryPresenter productByCategoryPresenter;
    private String catId;
    private ShimmerFrameLayout mShimmerViewContainer;
    private RelativeLayout ryParent;
    private int tabPostion;
    boolean isShimmerShow = true;
    private ProductByCategoryRequest productByCategoryRequest;
    private ScrollView svNotFound;
    private Button btnGoToHome;


    public static SubSubCategoryProductListFragment newInstance(String catId, int tabPostion) {
        SubSubCategoryProductListFragment fragment = new SubSubCategoryProductListFragment();
        Bundle bundle = new Bundle();
        bundle.putString("catId", catId);
        bundle.putInt("tabPostion", tabPostion);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_popular_list, container, false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        productByCategoryPresenter = new ProductByCategoryPresenter(this);

        if (getArguments() != null) {
            catId = getArguments().getString("catId");
            tabPostion = getArguments().getInt("tabPostion", 0);

        }


        productByCategoryRequest = ((ProductListByCategoryFragment) getParentFragment()).getSearchFilter();
        productByCategoryRequest.setCateId(catId);


        ryParent = view.findViewById(R.id.ryParent);
        recyclerview = view.findViewById(R.id.recyclerview);
        svNotFound = view.findViewById(R.id.svNotFound);
        btnGoToHome = view.findViewById(R.id.btnGoToHome);
        btnGoToHome.setOnClickListener(this);


        mShimmerViewContainer = view.findViewById(R.id.shimmer_view_container);

        productGridModellClasses = new ArrayList<>();

        callProductByCategoryAPI();

    }

    private void callProductByCategoryAPI() {
        if (Util.isDeviceOnline(getActivity())) {

            productByCategoryPresenter.getProductByCat(productByCategoryRequest);


        } else {
//            showCenteredToast(ryParent, getActivity(), getString(R.string.network_connection), "");
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
        nodataFound(appErrorMessage);
    }


    @Override
    public void getProductByCategory(ProductByCategoryResponse productByCategoryResponse) {
        if (productByCategoryResponse != null) {
            if (productByCategoryResponse.getmData() != null) {
                if (!productByCategoryResponse.getmData().getmProduct().isEmpty()) {
                    productGridModellClasses.clear();
                    productGridModellClasses.addAll(productByCategoryResponse.getmData().getmProduct());

                    recyclerview.setVisibility(View.VISIBLE);
                    svNotFound.setVisibility(View.GONE);

                    if (tabPostion == 0) {
                        sortByRating(productGridModellClasses);

                    } else if (tabPostion == 1) {
                        sortByLowPrice(productGridModellClasses);
                    } else if (tabPostion == 2) {
                        sortByHighPrice(productGridModellClasses);
                    }

                    ((ProductListByCategoryFragment) getParentFragment()).getProductByCategory(productByCategoryResponse.getmData().getmProductAttributeData());


                    mAdapter2 = new ProductByCategoryAdapter(getActivity(), productGridModellClasses, SubSubCategoryProductListFragment.this);
                    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
                    recyclerview.setLayoutManager(mLayoutManager);
                    recyclerview.setItemAnimator(new DefaultItemAnimator());
                    recyclerview.setAdapter(mAdapter2);
                }
            }
        }

    }

    private void nodataFound(String appErrorMessage) {
        svNotFound.setVisibility(View.VISIBLE);
        recyclerview.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View view) {
        try {
            int vId = view.getId();
            switch (vId) {
                case R.id.lyProduct:

                    ProductData productData = (ProductData) view.getTag();
                    if (productData != null) {

                        Bundle bundle = new Bundle();
                        bundle.putString("productSlug", productData.getmProductSlug());
                        bundle.putString("productId", productData.getmProductId());
                        bundle.putString("productQty", productData.getmProductQty());

                        ProductDetailFragment productDetailFragment = new ProductDetailFragment();
                        productDetailFragment.setArguments(bundle);

                        FragmentManagerUtils.replaceFragmentInRoot(getActivity().getSupportFragmentManager(), productDetailFragment, "ProductDetailFragment", true, false);

                    }

                    break;
                case R.id.lyAddToCart:


                    productData = ((ProductData) view.getTag());

                    if (productData != null) {

                        if (Util.isDeviceOnline(getActivity())) {
                            isShimmerShow = false;
                            productByCategoryPresenter.addToCart(CocoPreferences.getUserId(), productData.getmProductId(), "1", "");

                        } else {
//                            showCenteredToast(ryParent, getActivity(), getString(R.string.network_connection), "");
                            Util.showNoInternetDialog(getActivity());
                        }

                    }
                    break;

                case R.id.btnGoToHome:
                    FragmentManagerUtils.replaceFragmentInRoot(getActivity().getSupportFragmentManager(), new HomeFragment(), "HomeFragment", true, false);

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
            showCenteredToast(ryParent, getActivity(), addToCartResponse.getmMessage(), Constant.API_SUCCESS);


        } else {
            showCenteredToast(ryParent, getActivity(), addToCartResponse.getmMessage(), "");
        }
    }


    private void sortByRating(ArrayList<ProductData> productDataArrayList) {
        try {
            Collections.sort(productDataArrayList, new Comparator<ProductData>() {
                @Override
                public int compare(ProductData productData1, ProductData productData2) {

                    float rating1 = 0;
                    float rating2 = 0;

                    if (!TextUtils.isEmpty(productData1.getmAvgRating())) {
                        rating1 = Float.parseFloat(productData1.getmAvgRating());

                    }

                    if (!TextUtils.isEmpty(productData2.getmAvgRating())) {
                        rating2 = Float.parseFloat(productData2.getmAvgRating());

                    }

                    return (int) (rating2 - rating1);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void sortByLowPrice(ArrayList<ProductData> productDataArrayList) {
        Collections.sort(productDataArrayList, new Comparator<ProductData>() {
            @Override
            public int compare(ProductData productData1, ProductData productData2) {
                int salesPrice2 = Integer.parseInt(productData2.getmSalePrice());
                int salesPrice1 = Integer.parseInt(productData1.getmSalePrice());
                return Integer.compare(salesPrice1, salesPrice2);
            }
        });
    }

    private void sortByHighPrice(ArrayList<ProductData> productDataArrayList) {
        Collections.sort(productDataArrayList, new Comparator<ProductData>() {
            @Override
            public int compare(ProductData productData1, ProductData productData2) {
                int salesPrice2 = Integer.parseInt(productData2.getmSalePrice());
                int salesPrice1 = Integer.parseInt(productData1.getmSalePrice());
                return Integer.compare(salesPrice2, salesPrice1);
            }
        });
    }


}
