package com.nav.richkart.product_by_category;

import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nav.richkart.R;
import com.nav.richkart.base_fragment.BaseFragment;
import com.nav.richkart.interfaces.IFilterListener;
import com.nav.richkart.interfaces.IFragmentListener;

import java.util.ArrayList;

import com.nav.richkart.fragment.FragmentManagerUtils;


public class ProductListByCategoryFragment extends BaseFragment implements IFilterListener {

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


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mView = inflater.inflate(R.layout.fragment_product_by_category, container, false);

        Bundle bundle = getArguments();
        if (bundle != null) {
            catId = bundle.getString("catId");
        }

        return mView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        tabLayout = mView.findViewById(R.id.tab_layout);

        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.addTab(tabLayout.newTab().setText("Popular"));
        tabLayout.addTab(tabLayout.newTab().setText("Low Price"));
        tabLayout.addTab(tabLayout.newTab().setText("High Price"));
        tabLayout.addTab(tabLayout.newTab().setText("Sale"));

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
                filterFragment.setmIFilterListener(ProductListByCategoryFragment.this);
                filterFragment.setArguments(bundle);

                FragmentManagerUtils.replaceFragmentInRoot(getActivity().getSupportFragmentManager(), filterFragment, null, true, false);
            }
        });


        wrapContentHeightViewPager = mView.findViewById(R.id.pager);
        setAdapter();
        wrapContentHeightViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
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

    }

    private void setAdapter() {
        CategoryPagerAdapterProductList adapter = new CategoryPagerAdapterProductList(getChildFragmentManager(), 4, catId);
        wrapContentHeightViewPager.setAdapter(adapter);
        wrapContentHeightViewPager.setOffscreenPageLimit(1);
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
    public void setSearchFilter(String[] filterAttribues, String minimumValue, String maximumValue) {
        this.filterAttribues = filterAttribues;
        this.minimumValue = minimumValue;
        this.maximumValue = maximumValue;

    }

    public ProductByCategoryRequest getSearchFilter() {
        ProductByCategoryRequest productByCategoryRequest = new ProductByCategoryRequest();
        productByCategoryRequest.setmFAttributes(filterAttribues);
        productByCategoryRequest.setMinValue(minimumValue);
        productByCategoryRequest.setMaxValue(maximumValue);
        return productByCategoryRequest;

    }

    @Override
    protected String getActionbarTitle() {
        return getString(R.string.trandings);
    }

    @Override
    protected boolean isSearchIconVisible() {
        return true;
    }

    @Override
    protected boolean isCartIconVisible() {
        return true;
    }
}