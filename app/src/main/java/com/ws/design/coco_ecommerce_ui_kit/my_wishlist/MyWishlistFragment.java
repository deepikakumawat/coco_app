package com.ws.design.coco_ecommerce_ui_kit.my_wishlist;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.wolfsoft2.coco_ecommerce_ui_kit.R;
import com.ws.design.coco_ecommerce_ui_kit.base_fragment.BaseFragment;
import com.ws.design.coco_ecommerce_ui_kit.product_details.ProductDetailFragment;
import com.ws.design.coco_ecommerce_ui_kit.shared_preference.CocoPreferences;
import com.ws.design.coco_ecommerce_ui_kit.utility.Util;

import java.util.ArrayList;

import fragment.FragmentManagerUtils;

import static com.ws.design.coco_ecommerce_ui_kit.utility.Util.dismissProDialog;
import static com.ws.design.coco_ecommerce_ui_kit.utility.Util.showCenteredToast;
import static com.ws.design.coco_ecommerce_ui_kit.utility.Util.showProDialog;

public class MyWishlistFragment extends BaseFragment implements MyWishListView, View.OnClickListener {
    TextView txtTitle;

    private MyWishListAdapter myWishListAdapter;
    private RecyclerView rvMyWishList;
    private ArrayList<MyWishListResponse.ProductData> productDataArrayList = new ArrayList<>();
    private MyWishListPresenter myWishListPresenter;
    private LinearLayout lyEmpty;
    private int removePosstion = -1;
    private ImageView imgBack;
    private View mView;
    private RelativeLayout ryParent;


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

        ryParent =  view.findViewById(R.id.ryParent);
        txtTitle = (TextView) view.findViewById(R.id.txtTitle);
        lyEmpty = (LinearLayout)view. findViewById(R.id.lyEmpty);
        imgBack =view. findViewById(R.id.imgBack);
        rvMyWishList = (RecyclerView)view. findViewById(R.id.rvMyWishList);
        txtTitle.setText("Whishlist");

        if (Util.isDeviceOnline(getActivity())) {
            myWishListPresenter.getMyWishList(CocoPreferences.getUserId());

        }else{
            showCenteredToast(ryParent,getActivity(), getString(R.string.network_connection));

        }

        imgBack.setOnClickListener(this);


        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        rvMyWishList.setLayoutManager(gridLayoutManager);
//        rvMyWishList.addItemDecoration(new GridSpacingItemDecoration(2, 30, true));



     /*   LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvMyWishList.setLayoutManager(layoutManager);*/

    }

    @Override
    public void showWait() {
        lyEmpty.setVisibility(View.GONE);
        showProDialog(getActivity());
    }

    @Override
    public void removeWait() {
        dismissProDialog();
    }

    @Override
    public void onFailure(String appErrorMessage) {
        if (productDataArrayList.isEmpty()) {
            lyEmpty.setVisibility(View.VISIBLE);

        } else {
            lyEmpty.setVisibility(View.GONE);

        }
        showCenteredToast(ryParent,getActivity(), appErrorMessage);
    }

    @Override
    public void getMyWishList(MyWishListResponse myWishListResponse) {
        if (myWishListResponse != null) {

            if(myWishListResponse.getmMyWishlistData().getmProductData()!=null) {

                if (!myWishListResponse.getmMyWishlistData().getmProductData().isEmpty()) {

                    lyEmpty.setVisibility(View.GONE);
                    rvMyWishList.setVisibility(View.VISIBLE);

                    productDataArrayList.clear();
                    productDataArrayList.addAll(myWishListResponse.getmMyWishlistData().getmProductData());

                    myWishListAdapter = new MyWishListAdapter(getActivity(), productDataArrayList, MyWishlistFragment.this);
                    rvMyWishList.setAdapter(myWishListAdapter);


                } else {
                    lyEmpty.setVisibility(View.VISIBLE);
                    rvMyWishList.setVisibility(View.GONE);
                }
            }


        }
    }


    @Override
    public void removeWishList(RemoveWishListResponse removeWishListResponse) {
        if (!TextUtils.isEmpty(removeWishListResponse.getmStatus()) && ("1".equalsIgnoreCase(removeWishListResponse.getmStatus()))) {
            showCenteredToast(ryParent,getActivity(), removeWishListResponse.getmMessage());
            if (myWishListAdapter != null) {
                productDataArrayList.remove(removePosstion);
                myWishListAdapter.notifyDataSetChanged();
            }
        } else {
            showCenteredToast(ryParent,getActivity(), removeWishListResponse.getmData());
        }
    }


    @Override
    public void onClick(View view) {
        try {
            int vId = view.getId();
            switch (vId) {
                case R.id.txtProductName:
                    MyWishListResponse.ProductData productData = ((MyWishListResponse.ProductData) view.getTag());
                    removePosstion = (int) view.getTag(R.id.txtProductName);

                    if (productData != null) {

                        if (Util.isDeviceOnline(getActivity())) {
                            myWishListPresenter.removeWishList(productData.getmWishList());

                        }else{
                            showCenteredToast(ryParent,getActivity(), getString(R.string.network_connection));

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

                case R.id.imgBack:
                    getActivity().finish();
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
}
