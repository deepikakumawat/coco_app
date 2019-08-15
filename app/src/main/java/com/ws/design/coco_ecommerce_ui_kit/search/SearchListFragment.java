package com.ws.design.coco_ecommerce_ui_kit.search;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.wolfsoft2.coco_ecommerce_ui_kit.R;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.ws.design.coco_ecommerce_ui_kit.base_fragment.BaseFragment;
import com.ws.design.coco_ecommerce_ui_kit.product_details.ProductDetailFragment;
import com.ws.design.coco_ecommerce_ui_kit.product_details.project_details_response.ProductDetailsSimilier;
import com.ws.design.coco_ecommerce_ui_kit.utility.Util;

import java.util.ArrayList;

import fragment.FragmentManagerUtils;

public class SearchListFragment extends BaseFragment implements SearchView, View.OnClickListener {

    private ArrayList<ProductDetailsSimilier> productDetailsSimilierList = new ArrayList<>();
    private SearchPresenter searchPresenter;
    private LinearLayout lyParent;
    private View mView;
    private RecyclerView rvProducts;
    private SearchProductAdapter mAdapter2;
    private String searchTerm;
    private ShimmerFrameLayout mShimmerViewContainer;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mView = inflater.inflate(R.layout.fragment_search_list, container, false);


        Bundle bundle = getArguments();
        if (bundle != null) {
            searchTerm = bundle.getString("searchTerm");
        }


        return mView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        searchPresenter = new SearchPresenter(this);


        init(view);


    }

    private void init(View view) {
        mShimmerViewContainer = mView.findViewById(R.id.shimmer_view_container);

        lyParent = view.findViewById(R.id.lyParent);
        rvProducts = view.findViewById(R.id.rvProducts);

        searchPresenter.getSearchItem(searchTerm);

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
        Util.showCenteredToast(lyParent, getActivity(), appErrorMessage, "");
    }

    @Override
    public void getSearchProduct(SearchResponse searchResponse) {
        if (searchResponse != null) {
            if (!searchResponse.getmSearchData().getmProducts().isEmpty()) {
                productDetailsSimilierList.clear();

                productDetailsSimilierList.addAll(searchResponse.getmSearchData().getmProducts());

                mAdapter2 = new SearchProductAdapter(getActivity(), productDetailsSimilierList, SearchListFragment.this);
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
                rvProducts.setLayoutManager(mLayoutManager);
                rvProducts.setItemAnimator(new DefaultItemAnimator());
                rvProducts.setAdapter(mAdapter2);

            }
        }

    }


    @Override
    public void onClick(View view) {
        try {
            int vId = view.getId();
            switch (vId) {
                case R.id.txtProductName:
                    ProductDetailsSimilier productDetailsSimilier = ((ProductDetailsSimilier) view.getTag());
                    if (productDetailsSimilier != null) {

                        Bundle bundle = new Bundle();
                        bundle.putString("productSlug", productDetailsSimilier.getmProductSlug());
                        bundle.putString("productId", productDetailsSimilier.getmProductId());
                        bundle.putString("productQty", productDetailsSimilier.getmProductQty());

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
    protected boolean isCartIconVisible() {
        return false;
    }

    @Override
    protected boolean isSearchIconVisible() {
        return false;
    }

    @Override
    protected String getActionbarTitle() {
        if (!TextUtils.isEmpty(searchTerm)) {
            return searchTerm;
        }
        return getString(R.string.search_title);
    }
}
