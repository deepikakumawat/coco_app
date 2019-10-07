package com.nav.richkart.product_by_category;

import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.nav.richkart.R;
import com.nav.richkart.base_fragment.BaseFragment;
import com.nav.richkart.home.HomeFragment;
import com.nav.richkart.home.home_response.ProductData;
import com.nav.richkart.interfaces.IFilterListener;
import com.nav.richkart.interfaces.IFragmentListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import com.nav.richkart.fragment.FragmentManagerUtils;
import com.nav.richkart.product_details.AddToCartResponse;
import com.nav.richkart.product_details.ProductDetailFragment;
import com.nav.richkart.shared_preference.CocoPreferences;
import com.nav.richkart.utility.Constant;
import com.nav.richkart.utility.Util;

import static com.nav.richkart.utility.Util.dismissProDialog;
import static com.nav.richkart.utility.Util.showCenteredToast;
import static com.nav.richkart.utility.Util.showProDialog;


public class ProductListByCategoryFragment extends BaseFragment implements View.OnClickListener, IFilterListener, SwipeRefreshLayout.OnRefreshListener, ProductByCategoryView {

    private TabLayout tabLayout;
    private Typeface mTypeface;
    private Typeface mTypefaceBold;


    private View mView;
    private String catId;
    private IFragmentListener mListener;
    ArrayList<ProductByCategoryResponse.ProductAttribueData> productAttribueDataArrayList = new ArrayList<>();
    private String[] filterAttribues;
    private String maximumValue;
    private String minimumValue;


    private ArrayList<ProductData> productGridModellClasses;
    private RecyclerView recyclerview;
    private ProductByCategoryAdapter mAdapter2;
    private ProductByCategoryPresenter productByCategoryPresenter;
//    private String catId;
    private ShimmerFrameLayout mShimmerViewContainer;
    private RelativeLayout ryParent;
    private int tabPostion =0 ;
    boolean isShimmerShow = true;
    private ProductByCategoryRequest productByCategoryRequest;
    private ScrollView svNotFound;
    private Button btnGoToHome;
    private int offset = 0;
    private boolean loading = false;
    private SwipeRefreshLayout pullDownRefreshCall;


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

        productByCategoryPresenter = new ProductByCategoryPresenter(this);

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

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                setCustomFontAndStyle(tabLayout, tab.getPosition());
                tabPostion = tab.getPosition();
                offset = 0;
                callProductByCategoryAPI();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });


        productByCategoryRequest = getSearchFilter();



        ryParent = view.findViewById(R.id.ryParent);
        recyclerview = view.findViewById(R.id.recyclerview);
        svNotFound = view.findViewById(R.id.svNotFound);
        btnGoToHome = view.findViewById(R.id.btnGoToHome);
        btnGoToHome.setOnClickListener(this);
        pullDownRefreshCall = (SwipeRefreshLayout) view.findViewById(R.id.pullDownRefreshCall);
        pullDownRefreshCall.setOnRefreshListener(this);
        pullDownRefreshCall.setColorSchemeResources(R.color.navigation_bar_color, R.color.navigation_bar_color, R.color.navigation_bar_color, R.color.navigation_bar_color);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerview.setLayoutManager(mLayoutManager);
        recyclerview.setItemAnimator(new DefaultItemAnimator());

        mShimmerViewContainer = view.findViewById(R.id.shimmer_view_container);

        productGridModellClasses = new ArrayList<>();

        callProductByCategoryAPI();


        recyclerview.addOnScrollListener(new RecyclerView.OnScrollListener()
        {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy)
            {
                if(dy > 0) //check for scroll down
                {
                   int visibleItemCount = mLayoutManager.getChildCount();
                  int  totalItemCount = mLayoutManager.getItemCount();
                  int  pastVisiblesItems = mLayoutManager.findFirstVisibleItemPosition();

                    if (!loading)
                    {
                        if ( (visibleItemCount + pastVisiblesItems) >= totalItemCount)
                        {
                            loading = true;
                            offset = offset + 20;
                            callProductByCategoryAPI();
                            Log.v("...", "Last Item Wow !");

                        }
                    }
                }
            }
        });



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

    private void setPullToRefreshFalse() {
        if (pullDownRefreshCall.isRefreshing()) {
            pullDownRefreshCall.setRefreshing(false);
        }
    }

    private void callProductByCategoryAPI() {
        if (Util.isDeviceOnline(getActivity())) {
            productByCategoryRequest.setCateId(catId);
            productByCategoryRequest.setOffset(offset);
            if(offset > 1) {
                isShimmerShow = false;
            }
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

        setPullToRefreshFalse();
    }

    @Override
    public void onFailure(String appErrorMessage) {
        if(productGridModellClasses.size() == 0)
        nodataFound(appErrorMessage);
    }


    @Override
    public void getProductByCategory(ProductByCategoryResponse productByCategoryResponse) {
        loading = false;
        if (productByCategoryResponse != null) {
            if (productByCategoryResponse.getmData() != null) {
                if (!productByCategoryResponse.getmData().getmProduct().isEmpty()) {
                    //productGridModellClasses.clear();
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

                  getProductByCategory(productByCategoryResponse.getmData().getmProductAttributeData());


                    mAdapter2 = new ProductByCategoryAdapter(getActivity(), productGridModellClasses, ProductListByCategoryFragment.this);

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


    @Override
    public void onRefresh() {
        productGridModellClasses.clear();
        if (mAdapter2 != null) {
            mAdapter2.notifyDataSetChanged();
        }
        offset = 0;
        callProductByCategoryAPI();
    }
}
