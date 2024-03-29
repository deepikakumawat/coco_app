package com.richkart.android.home;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.richkart.android.R;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.richkart.android.base_fragment.BaseFragment;
import com.richkart.android.interfaces.IFragmentListener;
import com.richkart.android.home.home_response.Banner;
import com.richkart.android.home.home_response.Categories;
import com.richkart.android.home.home_response.DealProducts;
import com.richkart.android.home.home_response.HomeResponse;
import com.richkart.android.home.home_response.ProductData;
import com.richkart.android.product_by_category.ProductListByCategoryFragment;
import com.richkart.android.product_details.ProductDetailFragment;
import com.richkart.android.utility.Util;

import java.util.ArrayList;

import com.richkart.android.Model.HomeBannerModelClass;
import com.richkart.android.Model.HomeCategoryModelClass;
import com.richkart.android.Model.TopTenModelClass;
import com.richkart.android.fragment.FragmentManagerUtils;

import static com.richkart.android.utility.Util.showCenteredToast;

public class HomeFragment extends BaseFragment implements View.OnClickListener, HomeView, SwipeRefreshLayout.OnRefreshListener {


    private ArrayList<HomeBannerModelClass> homeBannerModelClasses;
    private RecyclerView rvBanner;
    private ArrayList<Banner> bannerArrayList = new ArrayList<>();
    private HomeBannerAdapter homeBannerAdapter;

    private ArrayList<HomeCategoryModelClass> homeCategoryModelClasses;
    private RecyclerView rvCategory;
    private HomeCategoryAdapter homeCategoryAdapter;


    private ArrayList<TopTenModelClass> topTenModelClasses;
    private RecyclerView rvTopRatedProducts;

    private ArrayList<ProductData> productDataArrayList = new ArrayList<>();
    private HomeTopRatedProductsAdapter homeTopRatedProductsAdapter;


    private ArrayList<DealProducts> dealProductsArrayList = new ArrayList<>();
    private HomeLikeProductsAdapter homeLikeProductsAdapter;
    private RecyclerView rvLike;

    private View mView;
    private HomePresenter homePresenter;
    private ArrayList<Categories> categoriesArrayList = new ArrayList<>();
    private ProductData productData;
    private DealProducts dealProduct;
    private IFragmentListener mListener;
    private RelativeLayout ryParent;
    private ShimmerFrameLayout mShimmerViewContainer;

    private SwipeRefreshLayout pullDownRefreshCall;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        mView = inflater.inflate(R.layout.fragment_home, container, false);

        return mView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        homePresenter = new HomePresenter(this);

        ryParent = mView.findViewById(R.id.ryParent);

        mShimmerViewContainer = mView.findViewById(R.id.shimmer_view_container);
        pullDownRefreshCall = view.findViewById(R.id.pullDownRefreshCall);


       callAPI();


        pullDownRefreshCall.setOnRefreshListener(this);
        pullDownRefreshCall.setColorSchemeResources(R.color.navigation_bar_color, R.color.navigation_bar_color, R.color.navigation_bar_color, R.color.navigation_bar_color);


        rvBanner = mView.findViewById(R.id.rvBanner);

        homeBannerModelClasses = new ArrayList<>();


        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        rvBanner.setLayoutManager(mLayoutManager);


        rvBanner.setLayoutManager(mLayoutManager);
        rvBanner.setItemAnimator(new DefaultItemAnimator());


        rvCategory = mView.findViewById(R.id.rvCategory);

        homeCategoryModelClasses = new ArrayList<>();


        RecyclerView.LayoutManager mLayoutManager1 = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        rvCategory.setLayoutManager(mLayoutManager1);


        rvCategory.setLayoutManager(mLayoutManager1);
        rvCategory.setItemAnimator(new DefaultItemAnimator());


        //        Top Ten  Recyclerview Code is here

        rvTopRatedProducts = (RecyclerView) mView.findViewById(R.id.rvTopRatedProducts);

        topTenModelClasses = new ArrayList<>();


        RecyclerView.LayoutManager mLayoutManager2 = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        rvTopRatedProducts.setLayoutManager(mLayoutManager2);


        rvTopRatedProducts.setLayoutManager(mLayoutManager2);
        rvTopRatedProducts.setItemAnimator(new DefaultItemAnimator());

        //      Like  Recyclerview Code is here

        rvLike = (RecyclerView) mView.findViewById(R.id.rvLike);

        RecyclerView.LayoutManager mLayoutManager3 = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        rvLike.setLayoutManager(mLayoutManager3);


        rvLike.setLayoutManager(mLayoutManager3);
        rvLike.setItemAnimator(new DefaultItemAnimator());


        //        Recent  Recyclerview Code is here

        rvTopRatedProducts = (RecyclerView) mView.findViewById(R.id.rvTopRatedProducts);

        topTenModelClasses = new ArrayList<>();


        RecyclerView.LayoutManager mLayoutManager4 = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        rvTopRatedProducts.setLayoutManager(mLayoutManager4);


        rvTopRatedProducts.setLayoutManager(mLayoutManager4);
        rvTopRatedProducts.setItemAnimator(new DefaultItemAnimator());


    }

    private void callAPI() {
        if (Util.isDeviceOnline(getActivity())) {
            homePresenter.getHomeData();

        } else {
            Util.showNoInternetDialog(getActivity());
        }
    }

    @Override
    public void onClick(View view) {
        try {
            int vId = view.getId();
            switch (vId) {
                case R.id.txtCategories:
//                    FragmentManagerUtils.replaceFragmentInRoot(getActivity().getSupportFragmentManager(), new ExploreFragment(), null, false, false);

                    Categories categories = (Categories) view.getTag();

                    Bundle bundle = new Bundle();
                    bundle.putString("catId", categories.getmCatId());


                    ProductListByCategoryFragment productListByCategoryFragment = new ProductListByCategoryFragment();
                    productListByCategoryFragment.setArguments(bundle);

                    FragmentManagerUtils.replaceFragmentInRoot(getActivity().getSupportFragmentManager(), productListByCategoryFragment, "ProductListByCategoryFragment", true, false);

                    break;
                case R.id.lyProduct:

                    productData = (ProductData) view.getTag();
//                    removeCorssPostion = (int) view.getTag(R.id.txtCross);
                    if (productData != null) {

                        bundle = new Bundle();
                        bundle.putString("productSlug", productData.getmProductSlug());
                        bundle.putString("productId", productData.getmProductId());
                        bundle.putString("productQty", productData.getmProductQty());

                        ProductDetailFragment productDetailFragment = new ProductDetailFragment();
                        productDetailFragment.setArguments(bundle);

                        FragmentManagerUtils.replaceFragmentInRoot(getActivity().getSupportFragmentManager(), productDetailFragment, "ProductDetailFragment", true, false);

                    }

                    break;

                case R.id.lyLikeProduct:

                    dealProduct = (DealProducts) view.getTag();
//                    removeCorssPostion = (int) view.getTag(R.id.txtCross);
                    if (dealProduct != null) {

                        bundle = new Bundle();
                        bundle.putString("productSlug", dealProduct.getmProductSlug());
                        bundle.putString("productId", dealProduct.getmProductId());
                        bundle.putString("productQty", dealProduct.getmProductQty());

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
    public void showWait() {
        mShimmerViewContainer.setVisibility(View.VISIBLE);
        mShimmerViewContainer.startShimmerAnimation();
    }

    @Override
    public void removeWait() {
        mShimmerViewContainer.stopShimmerAnimation();
        mShimmerViewContainer.setVisibility(View.GONE);
        setPullToRefreshFalse();

    }

    @Override
    public void onFailure(String appErrorMessage) {

        showCenteredToast(ryParent, getActivity(), appErrorMessage, "");
    }

    @Override
    public void getHomeData(HomeResponse homeResponse) {
        if (homeResponse != null) {
            if (homeResponse.getmData() != null) {
                if (homeResponse.getmData().getmCategories() != null) {
                    categoriesArrayList.clear();
                    categoriesArrayList.addAll(homeResponse.getmData().getmCategories());
                    homeCategoryAdapter = new HomeCategoryAdapter(getActivity(), categoriesArrayList, HomeFragment.this);
                    rvCategory.setAdapter(homeCategoryAdapter);
                }


                if (homeResponse.getmData().getmBanner1() != null) {
                    bannerArrayList.clear();
                    bannerArrayList.addAll(homeResponse.getmData().getmBanner1());
                    homeBannerAdapter = new HomeBannerAdapter(getActivity(), bannerArrayList, HomeFragment.this);
                    rvBanner.setAdapter(homeBannerAdapter);
                }

                if (homeResponse.getmData().getmProductData() != null) {
                    productDataArrayList.clear();
                    productDataArrayList.addAll(homeResponse.getmData().getmProductData());
                    homeTopRatedProductsAdapter = new HomeTopRatedProductsAdapter(getActivity(), productDataArrayList, HomeFragment.this);
                    rvTopRatedProducts.setAdapter(homeTopRatedProductsAdapter);
                }

                if (homeResponse.getmData().getmDealProducts() != null) {
                    dealProductsArrayList.clear();
                    dealProductsArrayList.addAll(homeResponse.getmData().getmDealProducts());
                    homeLikeProductsAdapter = new HomeLikeProductsAdapter(getActivity(), dealProductsArrayList, HomeFragment.this);
                    rvLike.setAdapter(homeLikeProductsAdapter);
                }


            }
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
    public void onRefresh() {
        categoriesArrayList.clear();
        if (homeCategoryAdapter != null) {
            homeBannerAdapter.notifyDataSetChanged();
        }

        bannerArrayList.clear();
        if (homeBannerAdapter != null) {
            homeBannerAdapter.notifyDataSetChanged();
        }

        productDataArrayList.clear();
        if (homeTopRatedProductsAdapter != null) {
            homeTopRatedProductsAdapter.notifyDataSetChanged();
        }

        dealProductsArrayList.clear();
        if (homeLikeProductsAdapter != null) {
            homeLikeProductsAdapter.notifyDataSetChanged();
        }


        callAPI();
    }

    private void setPullToRefreshFalse() {
        if (pullDownRefreshCall.isRefreshing()) {
            pullDownRefreshCall.setRefreshing(false);
        }
    }
}
