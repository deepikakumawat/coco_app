package com.ws.design.coco_ecommerce_ui_kit.product_details;


import android.util.Log;

import com.ws.design.coco_ecommerce_ui_kit.my_wishlist.MyWishListView;

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




    public void addToWishList(String userid, String productId) {
        view.showWait();
        try {

            Call call = service.addToWishList(userid,productId);
            call.enqueue(new Callback<AddToWishListResponse>() {
                @Override
                public void onResponse(Call<AddToWishListResponse> call, Response<AddToWishListResponse> response) {
                    Log.d(TAG, call.request().url().toString());
                    view.removeWait();
                    try{
                        if (response.isSuccessful()) {

                            view.addToWishList(response.body());
                        }else{


                            JSONObject jsonObject = new JSONObject(response.errorBody().string());
                            view.onFailure(jsonObject.getString("message"));
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<AddToWishListResponse> call, Throwable e) {
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


    public void addToCart(String userid, String productId) {
        view.showWait();
        try {

            Call call = service.addToCart(userid,productId);
            call.enqueue(new Callback<AddToCartResponse>() {
                @Override
                public void onResponse(Call<AddToCartResponse> call, Response<AddToCartResponse> response) {
                    Log.d(TAG, call.request().url().toString());
                    view.removeWait();
                    try{
                        if (response.isSuccessful()) {

                            view.addToCart(response.body());
                        }else{


                            JSONObject jsonObject = new JSONObject(response.errorBody().string());
                            view.onFailure(jsonObject.getString("message"));
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<AddToCartResponse> call, Throwable e) {
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