package com.nav.richkart.my_order_details;


import android.util.Log;



import org.json.JSONObject;

import com.nav.richkart.Network.APIService;
import com.nav.richkart.Network.ApiUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.adapter.rxjava2.HttpException;
import rx.subscriptions.CompositeSubscription;

import static android.support.constraint.Constraints.TAG;

public class OrderDetailsPresenter {

    private final APIService service;
    private final OrderDetailsView view;
    private CompositeSubscription subscriptions;

    public OrderDetailsPresenter(OrderDetailsView view) {
        this.view = view;
        service = ApiUtils.getAPIService();
        this.subscriptions = new CompositeSubscription();
    }



    public void getOrderDetails(String orderId) {
        view.showWait();

        try {

            Call call = service.getOrderDetails(orderId);
            call.enqueue(new Callback<MyOrderDetailsResponse>() {
                @Override
                public void onResponse(Call<MyOrderDetailsResponse> call, Response<MyOrderDetailsResponse> response) {
                    Log.d(TAG, call.request().url().toString());
                    view.removeWait();
                    try{
                        if (response.isSuccessful()) {

                            view.getOrderDetails(response.body());
                        }else{


                            JSONObject jsonObject = new JSONObject(response.errorBody().string());
                            view.onFailure(jsonObject.getString("message"));
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<MyOrderDetailsResponse> call, Throwable e) {
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