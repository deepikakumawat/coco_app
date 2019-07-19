package com.ws.design.coco_ecommerce_ui_kit.product_by_category;


import android.util.Log;



import org.json.JSONObject;

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



    public void getProductByCat(String catId) {
        view.showWait();
        try {

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
        }

    }


   


}