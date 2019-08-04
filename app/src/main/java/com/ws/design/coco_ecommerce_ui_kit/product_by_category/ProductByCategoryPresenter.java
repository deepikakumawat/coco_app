package com.ws.design.coco_ecommerce_ui_kit.product_by_category;


import android.text.TextUtils;
import android.util.Log;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.ws.design.coco_ecommerce_ui_kit.product_details.AddToCartResponse;
import com.ws.design.coco_ecommerce_ui_kit.product_details.ProductByCategoryRequest;

import org.json.JSONObject;

import java.util.Collection;

import Network.APIService;
import Network.ApiUtils;
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


    public void getProductByCat(String catId, String filterAttribues) {
        view.showWait();
        try {


         /*   ProductByCategoryRequest productByCategoryRequest = new ProductByCategoryRequest();
            productByCategoryRequest.setCateId(catId);

            if (!TextUtils.isEmpty(filterAttribues)) {
                JSONObject jsonObj = new JSONObject(filterAttribues);
                productByCategoryRequest.setJsonObject(jsonObj);

            }




            Gson gson = new GsonBuilder().registerTypeHierarchyAdapter(Collection.class, new CollectionAdapter()).disableHtmlEscaping().create();
            String requestBody = gson.toJson(productByCategoryRequest);

            Call call = service.getProductByCategory(requestBody);*/


            Call call = service.getProductByCategory(catId);
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


}