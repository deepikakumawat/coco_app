package com.ws.design.coco_ecommerce_ui_kit.seller;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.wolfsoft2.coco_ecommerce_ui_kit.R;
import com.ws.design.coco_ecommerce_ui_kit.checkout.CheckoutActivity;
import com.ws.design.coco_ecommerce_ui_kit.my_wishlist.MyWishListAdapter;
import com.ws.design.coco_ecommerce_ui_kit.my_wishlist.MyWishListPresenter;
import com.ws.design.coco_ecommerce_ui_kit.my_wishlist.MyWishListResponse;
import com.ws.design.coco_ecommerce_ui_kit.my_wishlist.MyWishListView;
import com.ws.design.coco_ecommerce_ui_kit.my_wishlist.RemoveWishListResponse;
import com.ws.design.coco_ecommerce_ui_kit.product_details.AddToCartResponse;
import com.ws.design.coco_ecommerce_ui_kit.product_details.ProductDetailFragment;
import com.ws.design.coco_ecommerce_ui_kit.product_details.project_details_response.ProductBroughtData;
import com.ws.design.coco_ecommerce_ui_kit.product_details.project_details_response.ProductDetailsSimilier;
import com.ws.design.coco_ecommerce_ui_kit.shared_preference.CocoPreferences;
import com.ws.design.coco_ecommerce_ui_kit.utility.Util;

import java.util.ArrayList;

import fragment.FragmentManagerUtils;
import fragment.ToolbarBaseFragment;

import static com.ws.design.coco_ecommerce_ui_kit.utility.Util.dismissProDialog;
import static com.ws.design.coco_ecommerce_ui_kit.utility.Util.showCenteredToast;
import static com.ws.design.coco_ecommerce_ui_kit.utility.Util.showProDialog;

public class SellerProductFragment extends ToolbarBaseFragment implements SellerView, View.OnClickListener {


    private SellerProductAdapter sellerProductAdapter;
    private RecyclerView rvSellerProduct;
    private ArrayList<ProductDetailsSimilier> sellerProductArrayList = new ArrayList<>();
    private SellerPresenter sellerPresenter;

    private View mView;
    private String sellerId;
    private TextView txtNoDataFound;
    private LinearLayout lySellerProduct;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mView = inflater.inflate(R.layout.fragment_seller, container, false);

        return mView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        sellerPresenter = new SellerPresenter(this);
        Bundle bundle = getArguments();
        sellerId = bundle.getString("sellerId");

        lySellerProduct = view.findViewById(R.id.lySellerProduct);
        txtNoDataFound = view.findViewById(R.id.txtNoDataFound);
        rvSellerProduct = view.findViewById(R.id.rvSellerProduct);

        if (Util.isDeviceOnline(getActivity())) {
            sellerPresenter.getSellerProduct(sellerId);
        } else {
            showCenteredToast(getActivity(), getString(R.string.network_connection));

        }


        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rvSellerProduct.setLayoutManager(layoutManager);

    }

    @Override
    public void showWait() {
        showProDialog(getActivity());
    }

    @Override
    public void removeWait() {
        dismissProDialog();
    }

    @Override
    public void onFailure(String appErrorMessage) {
        txtNoDataFound.setVisibility(View.GONE);
        lySellerProduct.setVisibility(View.VISIBLE);
        showCenteredToast(getActivity(), appErrorMessage);
    }

    @Override
    public void getSellerProduct(SellerResponse sellerResponse) {
        if (sellerResponse != null) {

            if (sellerResponse.getmData() != null) {

                if (!sellerResponse.getmData().getmSellerProducts().isEmpty()) {

                    txtNoDataFound.setVisibility(View.GONE);
                    lySellerProduct.setVisibility(View.VISIBLE);

                    sellerProductArrayList.clear();
                    sellerProductArrayList.addAll(sellerResponse.getmData().getmSellerProducts());

                    sellerProductAdapter = new SellerProductAdapter(getActivity(), sellerProductArrayList, SellerProductFragment.this);
                    rvSellerProduct.setAdapter(sellerProductAdapter);


                } else {
                    lySellerProduct.setVisibility(View.GONE);
                    txtNoDataFound.setVisibility(View.VISIBLE);
                }
            }


        }
    }

    @Override
    public void addToCart(AddToCartResponse addToCartResponse) {
        if (!TextUtils.isEmpty(addToCartResponse.getmStatus()) && ("1".equalsIgnoreCase(addToCartResponse.getmStatus()))) {
            showCenteredToast(getActivity(), addToCartResponse.getmMessage());




        } else {
            showCenteredToast(getActivity(), addToCartResponse.getmMessage());
        }
    }


    @Override
    public void onClick(View view) {
        try {
            int vId = view.getId();
            switch (vId) {
                case R.id.imgAddToCart:
                    ProductDetailsSimilier sellerProductData = ((ProductDetailsSimilier) view.getTag());

                    if (sellerProductData != null) {

                        if (Util.isDeviceOnline(getActivity())) {
                            sellerPresenter.addToCart(CocoPreferences.getUserId(), sellerProductData.getmProductId(), "1");

                        } else {
                            showCenteredToast(getActivity(), getString(R.string.network_connection));

                        }

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