package com.nav.richkart.deals.seller;


import android.util.Log;

import com.nav.richkart.Network.APIService;
import com.nav.richkart.Network.ApiUtils;
import com.nav.richkart.product_details.AddToCartResponse;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.adapter.rxjava2.HttpException;
import rx.subscriptions.CompositeSubscription;

import static android.support.constraint.Constraints.TAG;

public class DealsPresenter {

    private final APIService service;
    private final DealsView view;
    private CompositeSubscription subscriptions;

    public DealsPresenter(DealsView view) {
        this.view = view;
        service = ApiUtils.getAPIService();
        this.subscriptions = new CompositeSubscription();
    }



    public void getSellerProduct() {
        view.showWait();
        try {

            Call call = service.getDealsProduct();
            call.enqueue(new Callback<DealsResponse>() {
                @Override
                public void onResponse(Call<DealsResponse> call, Response<DealsResponse> response) {
                    Log.d(TAG, call.request().url().toString());
                    view.removeWait();
                    try {
                        if (response.isSuccessful()) {

                            view.getSellerProduct(response.body());
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
                public void onFailure(Call<DealsResponse> call, Throwable e) {
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

            Call call = service.addToCart(userid, productId, quantity,"");
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