package com.richkart.android.my_wishlist;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.richkart.android.R;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.richkart.android.base_fragment.BaseFragment;
import com.richkart.android.home.HomeFragment;
import com.richkart.android.product_details.ProductDetailFragment;
import com.richkart.android.shared_preference.CocoPreferences;
import com.richkart.android.utility.Constant;
import com.richkart.android.utility.Util;

import java.util.ArrayList;

import com.richkart.android.fragment.FragmentManagerUtils;

import static com.richkart.android.utility.Util.showCenteredToast;

public class MyWishlistFragment extends BaseFragment implements MyWishListView, View.OnClickListener {

    private MyWishListAdapter myWishListAdapter;
    private RecyclerView rvMyWishList;
    private ArrayList<MyWishListResponse.ProductData> productDataArrayList = new ArrayList<>();
    private MyWishListPresenter myWishListPresenter;
    private ScrollView svEmptyWishlistView;
    private int removePosstion = -1;
    private View mView;
    private RelativeLayout ryParent;
    private ShimmerFrameLayout mShimmerViewContainer;
    private Button btnGoToHome;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mView = inflater.inflate(R.layout.activity_wish_list, container, false);

        return mView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        myWishListPresenter = new MyWishListPresenter(this);

        mShimmerViewContainer = view.findViewById(R.id.shimmer_view_container);

        ryParent = view.findViewById(R.id.ryParent);
        svEmptyWishlistView = view.findViewById(R.id.svEmptyWishlistView);
        rvMyWishList = view.findViewById(R.id.rvMyWishList);
        btnGoToHome = view.findViewById(R.id.btnGoToHome);
        btnGoToHome.setOnClickListener(this);

        if (Util.isDeviceOnline(getActivity())) {
            myWishListPresenter.getMyWishList(CocoPreferences.getUserId());

        } else {
            Util.showNoInternetDialog(getActivity());
        }

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        rvMyWishList.setLayoutManager(mLayoutManager);




    }

    @Override
    public void showWait() {
        svEmptyWishlistView.setVisibility(View.GONE);
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
        if (productDataArrayList.isEmpty()) {
            svEmptyWishlistView.setVisibility(View.VISIBLE);


        } else {
            svEmptyWishlistView.setVisibility(View.GONE);

        }
    }

    @Override
    public void getMyWishList(MyWishListResponse myWishListResponse) {
        if (myWishListResponse != null) {

            if (myWishListResponse.getmMyWishlistData().getmProductData() != null) {

                if (!myWishListResponse.getmMyWishlistData().getmProductData().isEmpty()) {

                    svEmptyWishlistView.setVisibility(View.GONE);
                    rvMyWishList.setVisibility(View.VISIBLE);

                    productDataArrayList.clear();
                    productDataArrayList.addAll(myWishListResponse.getmMyWishlistData().getmProductData());

                    setAdapter();


                } else {
                    svEmptyWishlistView.setVisibility(View.VISIBLE);
                    rvMyWishList.setVisibility(View.GONE);
                }
            }


        }
    }

    private void setAdapter() {

        if (myWishListAdapter == null) {
            myWishListAdapter = new MyWishListAdapter(getActivity(), productDataArrayList, MyWishlistFragment.this);
            rvMyWishList.setAdapter(myWishListAdapter);
        } else {
            myWishListAdapter.notifyDataSetChanged();
        }


    }


    @Override
    public void removeWishList(RemoveWishListResponse removeWishListResponse) {
        if (!TextUtils.isEmpty(removeWishListResponse.getmStatus()) && ("1".equalsIgnoreCase(removeWishListResponse.getmStatus()))) {
            showCenteredToast(ryParent, getActivity(), removeWishListResponse.getmMessage(), Constant.API_SUCCESS);
            if (myWishListAdapter != null) {
                productDataArrayList.remove(removePosstion);


                if (!productDataArrayList.isEmpty()) {
                    setAdapter();
                } else {
                    svEmptyWishlistView.setVisibility(View.VISIBLE);
                    rvMyWishList.setVisibility(View.GONE);
                }
            }
        } else {
            showCenteredToast(ryParent, getActivity(), removeWishListResponse.getmData(), "");
        }
    }


    @Override
    public void onClick(View view) {
        try {
            int vId = view.getId();
            switch (vId) {
                case R.id.imgRemoveWishlist:
                    MyWishListResponse.ProductData productData = ((MyWishListResponse.ProductData) view.getTag());
                    removePosstion = (int) view.getTag(R.id.imgRemoveWishlist);

                    if (productData != null) {

                        if (Util.isDeviceOnline(getActivity())) {
                            myWishListPresenter.removeWishList(productData.getmWishList());

                        } else {
                            Util.showNoInternetDialog(getActivity());
                        }

                    }
                    break;

                case R.id.lyProduct:
                    productData = ((MyWishListResponse.ProductData) view.getTag());

                    if (productData != null) {

                        Bundle bundle = new Bundle();
                        bundle.putString("productSlug", productData.getmProductSlug());
                        bundle.putString("productId", productData.getmProductId());
                        bundle.putString("productQty", productData.getmProductQty());
                        bundle.putString("wishlistId", productData.getmWishList());
                        bundle.putString("screen", "MyWishList");

                        ProductDetailFragment productDetailFragment = new ProductDetailFragment();
                        productDetailFragment.setArguments(bundle);

                        FragmentManagerUtils.replaceFragmentInRoot(getActivity().getSupportFragmentManager(), productDetailFragment, "ProductDetailFragment", true, false);

                    }
                    break;
                case R.id.btnGoToHome:
                    FragmentManagerUtils.replaceFragmentInRoot(getActivity().getSupportFragmentManager(), new HomeFragment(), "HomeFragment", false, false);

                    break;


                default:
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    protected String getActionbarTitle() {
        return getString(R.string.my_wishlist);
    }

    @Override
    protected boolean isCartIconVisible() {
        return true;
    }

    @Override
    protected boolean isSearchIconVisible() {
        return true;
    }
}
