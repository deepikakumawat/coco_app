package com.richkart.android.departments;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.richkart.android.R;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.richkart.android.base_fragment.BaseFragment;
import com.richkart.android.product_by_category.ProductListByCategoryFragment;
import com.richkart.android.utility.Util;

import java.util.ArrayList;
import java.util.HashSet;

import com.richkart.android.fragment.FragmentManagerUtils;

import static com.richkart.android.utility.Util.showCenteredToast;

public class DepartmentFragment extends BaseFragment implements DepartmentView, View.OnClickListener {

    TextView title;


    private static ArrayList<CategoriesResponse.MainCategoriesData> mainCategoriesDataArrayList = new ArrayList<>();


    private MainDepartmentsAdapter mainDepartmentsAdapter;
    private View mView;
    private DepartmentsPresenter departmentsPresenter;
    private RelativeLayout ryParent;
    private RecyclerView rvMainCategory;
    private ShimmerFrameLayout mShimmerViewContainer;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mView = inflater.inflate(R.layout.fragment_departments, container, false);
        return mView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        departmentsPresenter = new DepartmentsPresenter(this);

        ryParent = mView.findViewById(R.id.ryParent);
        rvMainCategory = mView.findViewById(R.id.rvMainCategory);
        mShimmerViewContainer = mView.findViewById(R.id.shimmer_view_container);


        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rvMainCategory.setLayoutManager(layoutManager);
        rvMainCategory.setNestedScrollingEnabled(false);

        if (mainCategoriesDataArrayList.isEmpty()) {
            if (Util.isDeviceOnline(getActivity())) {
                departmentsPresenter.getCategories();

            } else {
                Util.showNoInternetDialog(getActivity());
            }

        }else{
            removeWait();
            setAdapter();
        }

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

                                if(subCategoriesData.getmProductCount()>0)
                                {

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


            setAdapter();


        }

    }

    private void setAdapter() {
        mainDepartmentsAdapter = new MainDepartmentsAdapter(getActivity(), mainCategoriesDataArrayList, DepartmentFragment.this);
        rvMainCategory.setAdapter(mainDepartmentsAdapter);
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
//                        bundle.putString("catName", subCategoriesData.getmCatName());
                        bundle.putString("screen", "Department");

                      /*  SubSubProductCategoryFragment subSubProductCategoryFragment = new SubSubProductCategoryFragment();
                        subSubProductCategoryFragment.setArguments(bundle);

                        FragmentManagerUtils.replaceFragmentInRoot(getActivity().getSupportFragmentManager(), subSubProductCategoryFragment, "ProductListByCategoryFragment", true, false);
*/
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
