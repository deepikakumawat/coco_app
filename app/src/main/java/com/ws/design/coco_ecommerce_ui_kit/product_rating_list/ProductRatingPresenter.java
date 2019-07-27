package com.ws.design.coco_ecommerce_ui_kit.product_rating_list;


import android.util.Log;


import com.ws.design.coco_ecommerce_ui_kit.product_rating_list.product_rating_response.ProductRatingResponse;

import org.json.JSONObject;

import Network.APIService;
import Network.ApiUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.adapter.rxjava2.HttpException;
import rx.subscriptions.CompositeSubscription;

import static android.support.constraint.Constraints.TAG;

public class ProductRatingPresenter {

    private final APIService service;
    private final ProductRatingView view;
    private CompositeSubscription subscriptions;

    public ProductRatingPresenter(ProductRatingView view) {
        this.view = view;
        service = ApiUtils.getAPIService();
        this.subscriptions = new CompositeSubscription();
    }



    public void getProductRating(String productId) {
        view.showWait();
        try {

            Call call = service.getProductRating(productId);
            call.enqueue(new Callback<ProductRatingResponse>() {
                @Override
                public void onResponse(Call<ProductRatingResponse> call, Response<ProductRatingResponse> response) {
                    Log.d(TAG, call.request().url().toString());
                    view.removeWait();
                    try {
                        if (response.isSuccessful()) {

                            view.getProductRating(response.body());
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
                public void onFailure(Call<ProductRatingResponse> call, Throwable e) {
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


    public void addReview(String userId, String userComment, String productId,String productRate ) {
        view.showWait();
        try {

            Call call = service.addReview(userId, userComment,productId, productRate);
            call.enqueue(new Callback<AddRatingResponse>() {
                @Override
                public void onResponse(Call<AddRatingResponse> call, Response<AddRatingResponse> response) {
                    Log.d(TAG, call.request().url().toString());
                    view.removeWait();
                    try {
                        if (response.isSuccessful()) {

                            view.addRating(response.body());
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
                public void onFailure(Call<AddRatingResponse> call, Throwable e) {
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