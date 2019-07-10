package com.ws.design.coco_ecommerce_ui_kit.product_details;


import android.util.Log;

import com.example.wolfsoft2.coco_ecommerce_ui_kit.R;
import com.ws.design.coco_ecommerce_ui_kit.my_wishlist.MyWishListView;
import com.ws.design.coco_ecommerce_ui_kit.product_details.project_details_response.ProductDetailsResponse;

import org.json.JSONObject;

import Network.APIService;
import Network.ApiUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.adapter.rxjava2.HttpException;
import rx.subscriptions.CompositeSubscription;

import static android.support.constraint.Constraints.TAG;

public class ProductDetailsPresenter {

    private final APIService service;
    private final ProductDetailsView view;
    private CompositeSubscription subscriptions;

    public ProductDetailsPresenter(ProductDetailsView view) {
        this.view = view;
        service = ApiUtils.getAPIService();
        this.subscriptions = new CompositeSubscription();
    }



    public void getProductDetails(String slug) {
        view.showWait();
        try {

            Call call = service.getProductDetails(slug);
            call.enqueue(new Callback<ProductDetailsResponse>() {
                @Override
                public void onResponse(Call<ProductDetailsResponse> call, Response<ProductDetailsResponse> response) {
                    Log.d(TAG, call.request().url().toString());
                    view.removeWait();
                    try {
                        if (response.isSuccessful()) {

                            view.getProductDetails(response.body());
                        } else {


                            JSONObject jsonObject = new JSONObject(response.errorBody().string());
                            view.onFailure(jsonObject.getString("message"));
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        view.onFailure("Something Went Wrong. Please try again later");

                    }
                }

                @Override
                public void onFailure(Call<ProductDetailsResponse> call, Throwable e) {
                    view.removeWait();
                    try {
                        JSONObject jsonObject = new JSONObject(((HttpException) e).response().errorBody().string());
                        view.onFailure(jsonObject.getString("message"));
                    } catch (Exception ee) {
                        e.printStackTrace();
                        view.onFailure("Something Went Wrong. Please try again later");
                    }

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void addToWishList(String userid, String productId) {
        view.showWait();
        try {

            Call call = service.addToWishList(userid, productId);
            call.enqueue(new Callback<AddToWishListResponse>() {
                @Override
                public void onResponse(Call<AddToWishListResponse> call, Response<AddToWishListResponse> response) {
                    Log.d(TAG, call.request().url().toString());
                    view.removeWait();
                    try {
                        if (response.isSuccessful()) {

                            view.addToWishList(response.body());
                        } else {


                            JSONObject jsonObject = new JSONObject(response.errorBody().string());
                            view.onFailure(jsonObject.getString("message"));
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        view.onFailure("Something Went Wrong. Please try again later");

                    }
                }

                @Override
                public void onFailure(Call<AddToWishListResponse> call, Throwable e) {
                    view.removeWait();
                    try {
                        JSONObject jsonObject = new JSONObject(((HttpException) e).response().errorBody().string());
                        view.onFailure(jsonObject.getString("message"));
                    } catch (Exception ee) {
                        e.printStackTrace();
                        view.onFailure("Something Went Wrong. Please try again later");
                    }

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void addToCart(String userid, String productId, String quantity) {
        view.showWait();
        try {

            Call call = service.addToCart(userid, productId, quantity);
            call.enqueue(new Callback<AddToCartResponse>() {
                @Override
                public void onResponse(Call<AddToCartResponse> call, Response<AddToCartResponse> response) {
                    Log.d(TAG, call.request().url().toString());
                    view.removeWait();
                    try {
                        if (response.isSuccessful()) {
                            view.addToCart(response.body());
                        } else {

                            JSONObject jsonObject = new JSONObject(response.errorBody().string());
                            view.onFailure(jsonObject.getString("message"));
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        view.onFailure("Something Went Wrong. Please try again later");

                    }
                }

                @Override
                public void onFailure(Call<AddToCartResponse> call, Throwable e) {
                    view.removeWait();
                    try {
                        JSONObject jsonObject = new JSONObject(((HttpException) e).response().errorBody().string());
                        view.onFailure(jsonObject.getString("message"));
                    } catch (Exception ee) {
                        e.printStackTrace();
                        view.onFailure("Something Went Wrong. Please try again later");

                    }

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}