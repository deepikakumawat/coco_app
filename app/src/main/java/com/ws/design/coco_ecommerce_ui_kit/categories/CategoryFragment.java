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
import android.widget.TextView;

import com.example.wolfsoft2.coco_ecommerce_ui_kit.R;
import com.ws.design.coco_ecommerce_ui_kit.base_fragment.BaseFragment;
import com.ws.design.coco_ecommerce_ui_kit.home.home_response.Categories;
import com.ws.design.coco_ecommerce_ui_kit.interfaces.IFragmentListener;
import com.ws.design.coco_ecommerce_ui_kit.my_order.MyOrderActivity;
import com.ws.design.coco_ecommerce_ui_kit.my_order.MyOrderAdapter;
import com.ws.design.coco_ecommerce_ui_kit.product_by_category.ProductListByCategoryFragment;
import com.ws.design.coco_ecommerce_ui_kit.utility.Util;

import java.util.ArrayList;

import fragment.FragmentManagerUtils;

import static com.ws.design.coco_ecommerce_ui_kit.utility.Util.showCenteredToast;

public class CategoryFragment extends BaseFragment implements CategoriesView, View.OnClickListener {

    TextView title;


    private ArrayList<CategoriesResponse.MainCategoriesData> mainCategoriesDataArrayList = new ArrayList<>();


    private MainCategoriesAdapter mainCategoriesAdapter;
    private View mView;
    CategoriesPresenter categoriesPresenter;
    private LinearLayout lyParent;
    private RecyclerView rvMainCategory;

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

        lyParent = mView.findViewById(R.id.lyParent);
        rvMainCategory = mView.findViewById(R.id.rvMainCategory);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rvMainCategory.setLayoutManager(layoutManager);

        if (Util.isDeviceOnline(getActivity())) {
            categoriesPresenter.getCategories();

        } else {
            showCenteredToast(lyParent, getActivity(), getString(R.string.network_connection), "");

        }


    }


    @Override
    protected String getActionbarTitle() {
        return getString(R.string.categories);
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

    }

    @Override
    public void removeWait() {

    }

    @Override
    public void onFailure(String appErrorMessage) {
        showCenteredToast(lyParent, getActivity(), appErrorMessage, "");

    }

    @Override
    public void getCategories(CategoriesResponse categoriesResponse) {
        if (categoriesResponse != null) {

            mainCategoriesDataArrayList.clear();
            mainCategoriesDataArrayList.addAll(categoriesResponse.getmData().getmMainCategories());


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
