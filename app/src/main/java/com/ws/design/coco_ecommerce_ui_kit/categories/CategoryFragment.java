package com.ws.design.coco_ecommerce_ui_kit.categories;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.wolfsoft2.coco_ecommerce_ui_kit.R;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.ws.design.coco_ecommerce_ui_kit.base_fragment.BaseFragment;
import com.ws.design.coco_ecommerce_ui_kit.home.home_response.Categories;
import com.ws.design.coco_ecommerce_ui_kit.interfaces.IFragmentListener;
import com.ws.design.coco_ecommerce_ui_kit.my_order.MyOrderActivity;
import com.ws.design.coco_ecommerce_ui_kit.my_order.MyOrderAdapter;
import com.ws.design.coco_ecommerce_ui_kit.product_by_category.ProductListByCategoryFragment;
import com.ws.design.coco_ecommerce_ui_kit.utility.Util;

import java.util.ArrayList;
import java.util.HashSet;

import fragment.FragmentManagerUtils;

import static com.ws.design.coco_ecommerce_ui_kit.utility.Util.showCenteredToast;

public class CategoryFragment extends BaseFragment implements CategoriesView, View.OnClickListener {

    TextView title;


    private ArrayList<CategoriesResponse.MainCategoriesData> mainCategoriesDataArrayList = new ArrayList<>();


    private MainCategoriesAdapter mainCategoriesAdapter;
    private View mView;
    private CategoriesPresenter categoriesPresenter;
    private RelativeLayout ryParent;
    private RecyclerView rvMainCategory;
    private ShimmerFrameLayout mShimmerViewContainer;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mView = inflater.inflate(R.layout.fragment_category, container, false);
        return mView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        categoriesPresenter = new CategoriesPresenter(this);

        ryParent = mView.findViewById(R.id.ryParent);
        rvMainCategory = mView.findViewById(R.id.rvMainCategory);
        mShimmerViewContainer = mView.findViewById(R.id.shimmer_view_container);


        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rvMainCategory.setLayoutManager(layoutManager);

        if (Util.isDeviceOnline(getActivity())) {
            categoriesPresenter.getCategories();

        } else {
            showCenteredToast(ryParent, getActivity(), getString(R.string.network_connection), "");

        }


    }


    @Override
    protected String getActionbarTitle() {
        return getString(R.string.departments);
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
    public void getCategories(CategoriesResponse categoriesResponse) {
        if (categoriesResponse != null) {

            mainCategoriesDataArrayList.clear();


            if (categoriesResponse.getmData() != null) {
                if (!categoriesResponse.getmData().getmMainCategories().isEmpty()) {

                    for(CategoriesResponse.MainCategoriesData mainCategoriesData : categoriesResponse.getmData().getmMainCategories()){

                        if (!mainCategoriesData.getmSubCategories().isEmpty()) {

                            for(CategoriesResponse.SubCategoriesData subCategoriesData : mainCategoriesData.getmSubCategories()){

                                if(subCategoriesData.getmProductCount()>0){

                                    mainCategoriesDataArrayList.add(mainCategoriesData);
                                }
                            }
                        }
                    }
                }
            }


            HashSet<CategoriesResponse.MainCategoriesData> hashSet = new HashSet<CategoriesResponse.MainCategoriesData>();
            hashSet.addAll(mainCategoriesDataArrayList);
            mainCategoriesDataArrayList.clear();
            mainCategoriesDataArrayList.addAll(hashSet);

//            mainCategoriesDataArrayList.addAll(categoriesResponse.getmData().getmMainCategories());


            mainCategoriesAdapter = new MainCategoriesAdapter(getActivity(), mainCategoriesDataArrayList, CategoryFragment.this);
            rvMainCategory.setAdapter(mainCategoriesAdapter);


        }

    }

    @Override
    public void onClick(View view) {
        try {
            int vId = view.getId();
            switch (vId) {
                case R.id.lySubCategory:

                    CategoriesResponse.SubCategoriesData subCategoriesData = (CategoriesResponse.SubCategoriesData) view.getTag();

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
