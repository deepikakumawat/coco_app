package com.richkart.android.product_by_category;


import android.util.Log;


import com.richkart.android.product_details.AddToCartResponse;

import org.json.JSONObject;


import com.richkart.android.Network.APIService;
import com.richkart.android.Network.ApiUtils;
import com.richkart.android.sub_sub_category.SubSubCategoriesResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.adapter.rxjava2.HttpException;
import rx.subscriptions.CompositeSubscription;

import static android.support.constraint.Constraints.TAG;

public class ProductByCategoryPresenter {

    private final APIService service;
    private final ProductByCategoryView view;
    private CompositeSubscription subscriptions;

    public ProductByCategoryPresenter(ProductByCategoryView view) {
        this.view = view;
        service = ApiUtils.getAPIService();
        this.subscriptions = new CompositeSubscription();
    }


    public void getProductByCat(ProductByCategoryRequest productByCategoryRequest) {
        view.showWait();
        try {

            Call call = service.getProductByCategory(productByCategoryRequest);

            call.enqueue(new Callback<ProductByCategoryResponse>() {
                @Override
                public void onResponse(Call<ProductByCategoryResponse> call, Response<ProductByCategoryResponse> response) {
                    Log.d(TAG, call.request().url().toString());
                    view.removeWait();
                    try {
                        if (response.isSuccessful()) {

                            view.getProductByCategory(response.body());
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
                public void onFailure(Call<ProductByCategoryResponse> call, Throwable e) {
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
            view.onFailure("Something Went Wrong. Please try again later");

        }

    }


    public void addToCart(String userid, String productId, String quantity, String attributes) {
        view.showWait();
        try {

            Call call = service.addToCart(userid, productId, quantity, attributes);
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

    public void getSubCateProductByCat(String catId) {
        view.showWait();
        try {


            Call call = service.getSubCateProductByCategory(catId);
            call.enqueue(new Callback<SubSubCategoriesResponse>() {
                @Override
                public void onResponse(Call<SubSubCategoriesResponse> call, Response<SubSubCategoriesResponse> response) {
                    Log.d(TAG, call.request().url().toString());
                    view.removeWait();
                    try {
                        if (response.isSuccessful()) {

                            view.getSubCatProductByCategory(response.body());
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
                public void onFailure(Call<SubSubCategoriesResponse> call, Throwable e) {
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
            view.onFailure("Something Went Wrong. Please try again later");

        }

    }

}