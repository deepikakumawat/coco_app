package com.ws.design.coco_ecommerce_ui_kit.checkout_payment;




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

public class CheckoutPaymentPresenter {

    private final APIService service;
    private final CheckoutPaymentView view;
    private CompositeSubscription subscriptions;

    public CheckoutPaymentPresenter(CheckoutPaymentView view) {
        this.view = view;
        service = ApiUtils.getAPIService();
        this.subscriptions = new CompositeSubscription();
    }



    public void getCheckoutPayment(String userId,String razorPaymentId, String shipCharge,String amount, String sameInfo, String rFname, String rLname, String rEmail, String rLandmark, String rNumber, String rAddress, String rZip, String rState, String rCity, String sFname, String sLname, String sEmail, String sLandmark,String sNumber,String sAddress,String sZip,String sState,String sCity ) {
        view.showWait();
        try {

            Call call = service.getCheckoutPayment(userId,razorPaymentId, shipCharge,amount,sameInfo,rFname,rLname,rEmail,rLandmark,rNumber,rAddress,rZip,rState,rCity,sFname,sLname,sEmail,sLandmark,sNumber,sAddress,sZip,sState,sCity);
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

}