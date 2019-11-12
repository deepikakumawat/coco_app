package com.richkart.android.sub_sub_category;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.richkart.android.R;
import com.richkart.android.base_fragment.BaseFragment;
import com.richkart.android.fragment.FragmentManagerUtils;
import com.richkart.android.interfaces.IFilterListener;
import com.richkart.android.interfaces.IFragmentListener;

import com.richkart.android.product_by_category.FilterFragment;
import com.richkart.android.product_by_category.ProductByCategoryResponse;
import com.richkart.android.product_by_category.WrapContentHeightViewPager;
import com.richkart.android.utility.Util;

import java.util.ArrayList;


public class SubSubProductCategoryFragment extends BaseFragment implements SubCatProductByCategoryView, IFilterListener {

    private TabLayout tabLayout;
    private Typeface mTypeface;
    private Typeface mTypefaceBold;

    WrapContentHeightViewPager wrapContentHeightViewPager;
    private View mView;
    private String catId;
    private IFragmentListener mListener;
    ArrayList<ProductByCategoryResponse.ProductAttribueData> productAttribueDataArrayList = new ArrayList<>();
    private String[] filterAttribues;
    private String maximumValue;
    private String minimumValue;
    private String catName;
    private SubCateProductByCategoryPresenter subCateProductByCategoryPresenter;
    private ArrayList<SubSubCategoriesResponse.MainSubCategoriesData> mainSubCategoriesDataArrayList = new ArrayList<>();
    private ShimmerFrameLayout mShimmerViewContainer;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mView = inflater.inflate(R.layout.fragment_sub_sub_product_by_category, container, false);

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

        tabLayout = mView.findViewById(R.id.tab_layout);


        Typeface mTypeface = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Roboto-Regular.ttf");

        ViewGroup vg = (ViewGroup) tabLayout.getChildAt(0);

        int tabsCount = vg.getChildCount();
        for (int j = 0; j < tabsCount; j++) {
            ViewGroup vgTab = (ViewGroup) vg.getChildAt(j);
            int tabChildsCount = vgTab.getChildCount();
            for (int i = 0; i < tabChildsCount; i++) {
                View tabViewChild = vgTab.getChildAt(i);
                if (tabViewChild instanceof TextView) {
                    ((TextView) tabViewChild).setTypeface(mTypeface, Typeface.NORMAL);
                }
            }
        }


        setCustomFontAndStyle(tabLayout, 0);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        mView.findViewById(R.id.btn_filter).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


              Bundle bundle = new Bundle();
                bundle.putSerializable("productAttribueDataArrayList", productAttribueDataArrayList);
                FilterFragment filterFragment = new FilterFragment();
                filterFragment.setmIFilterListener(SubSubProductCategoryFragment.this);
                filterFragment.setArguments(bundle);

                FragmentManagerUtils.replaceFragmentInRoot(getActivity().getSupportFragmentManager(), filterFragment, null, true, false);
            }
        });

        mShimmerViewContainer = view.findViewById(R.id.shimmer_view_container);

        wrapContentHeightViewPager = mView.findViewById(R.id.pager);
//        setAdapter();
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                wrapContentHeightViewPager.setCurrentItem(tab.getPosition());
                setCustomFontAndStyle(tabLayout, tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

        callAPI();
    }

    private void callAPI() {

        if (Util.isDeviceOnline(getActivity())) {
            subCateProductByCategoryPresenter.getSubCateProductByCat(catId);
        } else {
            Util.showNoInternetDialog(getActivity());
        }

    }

    private void setAdapter(int size, ArrayList<SubSubCategoriesResponse.MainSubCategoriesData> mainSubCategoriesDataArrayList) {
        SubSubCategoryPagerAdapterProductList adapter = new SubSubCategoryPagerAdapterProductList(getChildFragmentManager(), size, mainSubCategoriesDataArrayList);
        wrapContentHeightViewPager.setAdapter(adapter);
        wrapContentHeightViewPager.setOffscreenPageLimit(0);
        wrapContentHeightViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

    }



    private void setCustomFontAndStyle(TabLayout tabLayout, Integer position) {
        mTypeface = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Roboto-Medium.ttf");
        mTypefaceBold = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Roboto-Medium.ttf");
        ViewGroup vg = (ViewGroup) tabLayout.getChildAt(0);
        int tabsCount = vg.getChildCount();
        for (int j = 0; j < tabsCount; j++) {
            ViewGroup vgTab = (ViewGroup) vg.getChildAt(j);
            int tabChildsCount = vgTab.getChildCount();
            for (int i = 0; i < tabChildsCount; i++) {
                View tabViewChild = vgTab.getChildAt(i);
                if (tabViewChild instanceof TextView) {
                    if (j == position) {
                        ((TextView) tabViewChild).setTypeface(mTypefaceBold, Typeface.NORMAL);
                    } else {
                        ((TextView) tabViewChild).setTypeface(mTypeface, Typeface.NORMAL);
                    }
                }
            }
        }
    }


    public void getProductByCategory(ArrayList<ProductByCategoryResponse.ProductAttribueData> productAttribueDataArrayList) {
        this.productAttribueDataArrayList.clear();
        this.productAttribueDataArrayList.addAll(productAttribueDataArrayList);
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

    }

    @Override
    public void getSubCatProductByCategory(SubSubCategoriesResponse subSubCategoriesResponse) {
        if (subSubCategoriesResponse != null) {

            mainSubCategoriesDataArrayList.clear();
            mainSubCategoriesDataArrayList.addAll(subSubCategoriesResponse.getmData().getmMainSubCategories());

            for (SubSubCategoriesResponse.MainSubCategoriesData mainSubCategoriesData : mainSubCategoriesDataArrayList) {
                tabLayout.addTab(tabLayout.newTab().setText(mainSubCategoriesData.getmCatName()));
            }
            tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
            setAdapter(mainSubCategoriesDataArrayList.size(), mainSubCategoriesDataArrayList);

        }

    }


    @Override
    public void setSearchFilter(ArrayList<ProductByCategoryResponse.ProductAttribueData> mproductAttribueDataArrayList, String minimumValue, String maximumValue, ArrayList<ProductByCategoryResponse.Attribtues> selectedAttributesArrayList, int tabPostion) {

    }
}
