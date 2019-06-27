package com.ws.design.coco_ecommerce_ui_kit.signup;

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

public class SignUpPresenter {

    private final APIService service;
    private final SignUpView view;
    private CompositeSubscription subscriptions;

    public SignUpPresenter(SignUpView view) {
        this.view = view;
        service = ApiUtils.getAPIService();
        this.subscriptions = new CompositeSubscription();
    }

    public void doSignUp(String email, String fName, String lName, String phone, String password, String confirmPassword) {
        view.showWait();

     /*   service.doSignUp(email,fName,lName, phone, password,confirmPassword).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<SignUpResponse>() {
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
                    public void onNext(SignUpResponse signUpResponse) {
                        view.removeWait();
                        view.onSignSuccess(signUpResponse);
                    }
                });*/


        try {

            Call call = service.doSignUp(email, fName, lName, phone, password, confirmPassword);
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
}

