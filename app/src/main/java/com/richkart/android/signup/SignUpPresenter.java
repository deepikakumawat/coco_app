package com.richkart.android.signup;

import android.util.Log;

import org.json.JSONObject;

import com.richkart.android.Network.APIService;
import com.richkart.android.Network.ApiUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.adapter.rxjava2.HttpException;
import rx.subscriptions.CompositeSubscription;

import static android.support.constraint.Constraints.TAG;

public class SignUpPresenter {

    private final APIService service;
    private final SignUpView view;
    private CompositeSubscription subscriptions;

    public SignUpPresenter(SignUpView view) {
        this.view = view;
        service = ApiUtils.getAPIService();
        this.subscriptions = new CompositeSubscription();
    }

    public void doSignUp(String email, String fName, String lName, String phone,String otp, String VCToken ,String password,String gender, String confirmPassword) {
        view.showWait();


        try {

            Call call = service.doSignUp(email, fName, lName, phone,otp,VCToken, password,gender, confirmPassword);
            call.enqueue(new Callback<SignUpResponse>() {
                @Override
                public void onResponse(Call<SignUpResponse> call, Response<SignUpResponse> response) {
                    Log.d(TAG, call.request().url().toString());
                    view.removeWait();

                    try{
                        if (response.isSuccessful()) {
                            view.onSignSuccess(response.body());
                        }else{

                            JSONObject jsonObject = new JSONObject(response.errorBody().string());
                            view.onFailure(jsonObject.getString("message"));
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }


                }

                @Override
                public void onFailure(Call<SignUpResponse> call, Throwable e) {
                    view.removeWait();
                    try {
                        JSONObject jsonObject = new JSONObject(((HttpException) e).response().errorBody().string());
                        view.onFailure(jsonObject.getString("message"));
                    } catch (Exception ee) {
                        e.printStackTrace();
                    }

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getOtp(String phone) {
        view.showWait();


        try {

            Call call = service.getOTP(phone);
            call.enqueue(new Callback<GetOTPResponse>() {
                @Override
                public void onResponse(Call<GetOTPResponse> call, Response<GetOTPResponse> response) {
                    Log.d(TAG, call.request().url().toString());
                    view.removeWait();

                    try{
                        if (response.isSuccessful()) {
                            view.getOTP(response.body());
                        }else{

                            JSONObject jsonObject = new JSONObject(response.errorBody().string());
                            view.onFailure(jsonObject.getString("message"));
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }


                }

                @Override
                public void onFailure(Call<GetOTPResponse> call, Throwable e) {
                    view.removeWait();
                    try {
                        JSONObject jsonObject = new JSONObject(((HttpException) e).response().errorBody().string());
                        view.onFailure(jsonObject.getString("message"));
                    } catch (Exception ee) {
                        e.printStackTrace();
                    }

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}

