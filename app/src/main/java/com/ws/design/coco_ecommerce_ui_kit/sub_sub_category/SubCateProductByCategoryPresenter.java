package com.ws.design.coco_ecommerce_ui_kit.sub_sub_category;


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

public class SubCateProductByCategoryPresenter {

    private final APIService service;
    private final SubCatProductByCategoryView view;
    private CompositeSubscription subscriptions;

    public SubCateProductByCategoryPresenter(SubCatProductByCategoryView view) {
        this.view = view;
        service = ApiUtils.getAPIService();
        this.subscriptions = new CompositeSubscription();
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