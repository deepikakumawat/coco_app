package com.richkart.android.my_wishlist;


import android.util.Log;

import org.json.JSONObject;

import com.richkart.android.Network.APIService;
import com.richkart.android.Network.ApiUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.adapter.rxjava2.HttpException;
import rx.subscriptions.CompositeSubscription;

import static android.support.constraint.Constraints.TAG;

public class MyWishListPresenter {

    private final APIService service;
    private final MyWishListView view;
    private CompositeSubscription subscriptions;

    public MyWishListPresenter(MyWishListView view) {
        this.view = view;
        service = ApiUtils.getAPIService();
        this.subscriptions = new CompositeSubscription();
    }

    public void getMyWishList(String userid) {
        view.showWait();
        try {

            Call call = service.getMyWishList(userid);
            call.enqueue(new Callback<MyWishListResponse>() {
                @Override
                public void onResponse(Call<MyWishListResponse> call, Response<MyWishListResponse> response) {
                    Log.d(TAG, call.request().url().toString());
                    view.removeWait();
                    try {
                        if (response.isSuccessful()) {

                            view.getMyWishList(response.body());
                        } else {


                            JSONObject jsonObject = new JSONObject(response.errorBody().string());
                            view.onFailure(jsonObject.getString("message"));
                        }
                    } catch (Exception e) {
                        view.onFailure("Something Went Wrong. Please try again later");

                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<MyWishListResponse> call, Throwable e) {
                    view.removeWait();
                    try {
                        JSONObject jsonObject = new JSONObject(((HttpException) e).response().errorBody().string());
                        view.onFailure(jsonObject.getString("message"));
                    } catch (Exception ee) {
                        view.onFailure("Something Went Wrong. Please try again later");
                        e.printStackTrace();
                    }

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void removeWishList(String wishlistId) {
        view.showWait();
        try {

            Call call = service.removeWishList(wishlistId);
            call.enqueue(new Callback<RemoveWishListResponse>() {
                @Override
                public void onResponse(Call<RemoveWishListResponse> call, Response<RemoveWishListResponse> response) {
                    Log.d(TAG, call.request().url().toString());
                    view.removeWait();
                    try {
                        if (response.isSuccessful()) {

                            view.removeWishList(response.body());
                        } else {


                            JSONObject jsonObject = new JSONObject(response.errorBody().string());
                            view.onFailure(jsonObject.getString("message"));
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<RemoveWishListResponse> call, Throwable e) {
                    view.removeWait();
                    try {
                        JSONObject jsonObject = new JSONObject(((HttpException) e).response().errorBody().string());
                        view.onFailure(jsonObject.getString("message"));
                    } catch (Exception ee) {

                    }

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}