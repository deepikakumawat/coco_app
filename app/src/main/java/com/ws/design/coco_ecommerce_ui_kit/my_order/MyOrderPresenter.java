package com.ws.design.coco_ecommerce_ui_kit.my_order;


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

public class MyOrderPresenter {

    private final APIService service;
    private final MyOrderView view;
    private CompositeSubscription subscriptions;

    public MyOrderPresenter(MyOrderView view) {
        this.view = view;
        service = ApiUtils.getAPIService();
        this.subscriptions = new CompositeSubscription();
    }

    public void myOrder(String userid) {
        view.showWait();

    /*    service.getMyOrder("userid")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<MyOrderResponse>() {
                    @Override
                    public void onCompleted() {

                    }


                    @Override
                    public void onError(Throwable e) {
                        view.removeWait();
                        try {
                            JSONObject jsonObject = new JSONObject(((HttpException) e).response().errorBody().string());
                            view.onFailure(jsonObject.getString("message"));
                        } catch (Exception ee) {

                        }

                    }

                    @Override
                    public void onNext(MyOrderResponse post) {
                        view.removeWait();
                        view.onMyOrderList(post);
                    }
                });*/

        try {

            Call call = service.getMyOrder(userid);
            call.enqueue(new Callback<MyOrderResponse>() {
                @Override
                public void onResponse(Call<MyOrderResponse> call, Response<MyOrderResponse> response) {
                    Log.d(TAG, call.request().url().toString());
                    view.removeWait();
                    try{
                        if (response.isSuccessful()) {
                            Log.d(TAG, call.request().url().toString());

                            view.onMyOrderList(response.body());
                        }else{


                            JSONObject jsonObject = new JSONObject(response.errorBody().string());
                            view.onFailure(jsonObject.getString("message"));
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<MyOrderResponse> call, Throwable e) {
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