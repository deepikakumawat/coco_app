package com.ws.design.coco_ecommerce_ui_kit.categories;


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

public class CategoriesPresenter {

    private final APIService service;
    private final CategoriesView view;
    private CompositeSubscription subscriptions;

    public CategoriesPresenter(CategoriesView view) {
        this.view = view;
        service = ApiUtils.getAPIService();
        this.subscriptions = new CompositeSubscription();
    }



    public void getCategories() {
        view.showWait();

        try {

            Call call = service.getCategories();
            call.enqueue(new Callback<CategoriesResponse>() {
                @Override
                public void onResponse(Call<CategoriesResponse> call, Response<CategoriesResponse> response) {
                    Log.d(TAG, call.request().url().toString());
                    view.removeWait();
                    try{
                        if (response.isSuccessful()) {

                            view.getCategories(response.body());
                        }else{


                            JSONObject jsonObject = new JSONObject(response.errorBody().string());
                            view.onFailure(jsonObject.getString("message"));
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<CategoriesResponse> call, Throwable e) {
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