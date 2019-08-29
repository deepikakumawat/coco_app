package com.ws.design.coco_ecommerce_ui_kit.sub_sub_category;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.wolfsoft2.coco_ecommerce_ui_kit.R;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.ws.design.coco_ecommerce_ui_kit.base_fragment.BaseFragment;
import com.ws.design.coco_ecommerce_ui_kit.home.HomeBannerAdapter;
import com.ws.design.coco_ecommerce_ui_kit.home.HomeFragment;
import com.ws.design.coco_ecommerce_ui_kit.home.home_response.Banner;
import com.ws.design.coco_ecommerce_ui_kit.product_by_category.ProductListByCategoryFragment;
import com.ws.design.coco_ecommerce_ui_kit.utility.Util;

import java.util.ArrayList;

import fragment.FragmentManagerUtils;

import static com.ws.design.coco_ecommerce_ui_kit.utility.Util.showCenteredToast;

public class SubCategoryFragment extends BaseFragment implements SubCatProductByCategoryView, View.OnClickListener {

    private TextView title;

    private ArrayList<SubSubCategoriesResponse.MainSubCategoriesData> mainSubCategoriesDataArrayList = new ArrayList<>();

    private SubSubCategoryAdapter subSubCategoryAdapter;
    private View mView;
    private SubCateProductByCategoryPresenter subCateProductByCategoryPresenter;
    private RelativeLayout ryParent;
    private RecyclerView rvMainCategory;
    private ShimmerFrameLayout mShimmerViewContainer;
    private String catId;
    private String filterAttributes;
    private String catName;
    private ArrayList<Banner> bannerArrayList = new ArrayList<>();
    private HomeBannerAdapter homeBannerAdapter;
    private RecyclerView rvBanner;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mView = inflater.inflate(R.layout.fragment_category, container, false);

        Bundle bundle = getArguments();
        if (bundle != null) {
            catId = bundle.getString("catId");
            catName = bundle.getString("catName");
        }

        return mView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        subCateProductByCategoryPresenter = new SubCateProductByCategoryPresenter(this);

        ryParent = mView.findViewById(R.id.ryParent);
        rvMainCategory = mView.findViewById(R.id.rvMainCategory);
        mShimmerViewContainer = mView.findViewById(R.id.shimmer_view_container);
        rvBanner = mView.findViewById(R.id.rvBanner);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        rvBanner.setLayoutManager(mLayoutManager);
        rvBanner.setLayoutManager(mLayoutManager);
        rvBanner.setItemAnimator(new DefaultItemAnimator());

        rvMainCategory.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        rvMainCategory.setFocusableInTouchMode(false);
        rvMainCategory.setNestedScrollingEnabled(false);

        if (Util.isDeviceOnline(getActivity())) {
            subCateProductByCategoryPresenter.getSubCateProductByCat(catId, filterAttributes);

        } else {
            Util.showNoInternetDialog(getActivity());
        }


    }


    @Override
    protected String getActionbarTitle() {
        return catName;
    }

    @Override
    protected boolean isCartIconVisible() {
        return true;
    }

    @Override
    protected boolean isSearchIconVisible() {
        return true;
    }

    @Override
    public void showWait() {
        mShimmerViewContainer.setVisibility(View.VISIBLE);
        mShimmerViewContainer.startShimmerAnimation();
    }

    @Override
    public void removeWait() {
        mShimmerViewContainer.stopShimmerAnimation();
        mShimmerViewContainer.setVisibility(View.GONE);
    }

    @Override
    public void onFailure(String appErrorMessage) {
        showCenteredToast(ryParent, getActivity(), appErrorMessage, "");

    }

    @Override
    public void getSubCatProductByCategory(SubSubCategoriesResponse subSubCategoriesResponse) {
        if (subSubCategoriesResponse != null) {

            mainSubCategoriesDataArrayList.clear();

            mainSubCategoriesDataArrayList.addAll(subSubCategoriesResponse.getmData().getmMainSubCategories());

            subSubCategoryAdapter = new SubSubCategoryAdapter(getActivity(), mainSubCategoriesDataArrayList, SubCategoryFragment.this);
            rvMainCategory.setAdapter(subSubCategoryAdapter);


            if (subSubCategoriesResponse.getmData().getmBanner() != null) {
                bannerArrayList.clear();
                bannerArrayList.addAll(subSubCategoriesResponse.getmData().getmBanner());
                homeBannerAdapter = new HomeBannerAdapter(getActivity(), bannerArrayList, SubCategoryFragment.this);
                rvBanner.setAdapter(homeBannerAdapter);
            }

        }

    }


    @Override
    public void onClick(View view) {
        try {
            int vId = view.getId();
            switch (vId) {
                case R.id.lySubCategory:

                    SubSubCategoriesResponse.MainSubCategoriesData subCategoriesData = (SubSubCategoriesResponse.MainSubCategoriesData) view.getTag();

                    if (subCategoriesData != null) {
                        Bundle bundle = new Bundle();
                        bundle.putString("catId", subCategoriesData.getmCatId());


                        ProductListByCategoryFragment productListByCategoryFragment = new ProductListByCategoryFragment();
                        productListByCategoryFragment.setArguments(bundle);

                        FragmentManagerUtils.replaceFragmentInRoot(getActivity().getSupportFragmentManager(), productListByCategoryFragment, "ProductListByCategoryFragment", true, false);


                    }

                    break;
                default:
                    break;

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
