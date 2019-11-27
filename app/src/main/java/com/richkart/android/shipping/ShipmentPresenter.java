package com.richkart.android.shipping;


import android.util.Log;

import com.richkart.android.Network.APIService;
import com.richkart.android.Network.ApiUtils;
import com.richkart.android.address.AddressListResponse;
import com.richkart.android.checkout.CheckoutPaymentResponse;

import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.adapter.rxjava2.HttpException;
import rx.subscriptions.CompositeSubscription;

import static android.support.constraint.Constraints.TAG;

public class ShipmentPresenter {

    private final APIService service;
    private final ShipmentView view;
    private CompositeSubscription subscriptions;

    public ShipmentPresenter(ShipmentView view) {
        this.view = view;
        service = ApiUtils.getAPIService();
        this.subscriptions = new CompositeSubscription();
    }



    public void getCheckoutPayment(String userId,String razorPaymentId, String shipCharge,String amount, String sameInfo, String rFname, String rLname, String rEmail, String rLandmark, String rNumber, String rAddress, String rZip, String rState, String rCity, String sFname, String sLname, String sEmail, String sLandmark,String sNumber,String sAddress,String sZip,String sState,String sCity,String shitType, String shipBy ) {
        view.showWait();
        try {

            Call call = service.getCheckoutPayment(userId,razorPaymentId, shipCharge,amount,sameInfo,rFname,rLname,rEmail,rLandmark,rNumber,rAddress,rZip,rState,rCity,sFname,sLname,sEmail,sLandmark,sNumber,sAddress,sZip,sState,sCity,shitType,shipBy);
            call.enqueue(new Callback<CheckoutPaymentResponse>() {
                @Override
                public void onResponse(Call<CheckoutPaymentResponse> call, Response<CheckoutPaymentResponse> response) {
                    Log.d(TAG, call.request().url().toString());
                    view.removeWait();
                    try{
                        if (response.isSuccessful()) {

                            view.getCheckoutPayment(response.body());
                        }else{


                            JSONObject jsonObject = new JSONObject(response.errorBody().string());
                            view.onFailure(jsonObject.getString("message"));
                        }
                    }catch (Exception e){
                        view.onFailure("Something Went Wrong. Please try again later");

                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<CheckoutPaymentResponse> call, Throwable e) {
                    view.removeWait();
                    try {
                        JSONObject jsonObject = new JSONObject(((HttpException) e).response().errorBody().string());
                        view.onFailure(jsonObject.getString("message"));
                    } catch (Exception ee) {
                        view.onFailure("Something Went Wrong. Please try again later");

                        ee.printStackTrace();
                    }

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void shippingList(String userid,String radress, String rzip, String rstate, String rcity) {
        view.showWait();
//{"user_id":"4","radress":"66/61, heera path, mansarovar","rzip":"302025","rstate":"Ra","rcity":"Jaipur"}

        try {

            Call call = service.getShippingList(userid,radress,rzip,rstate,rcity);
            call.enqueue(new Callback<List<ShippingListResponse>>() {
                @Override
                public void onResponse(Call<List<ShippingListResponse>> call, Response<List<ShippingListResponse>> response) {
                    Log.d(TAG, call.request().url().toString());
                    view.removeWait();
                    try{
                        if (response.isSuccessful()) {

                            view.getShippingList(response.body());
                        }else{


                            JSONObject jsonObject = new JSONObject(response.errorBody().string());
                            view.onFailure(jsonObject.getString("message"));
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<List<ShippingListResponse>> call, Throwable e) {
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