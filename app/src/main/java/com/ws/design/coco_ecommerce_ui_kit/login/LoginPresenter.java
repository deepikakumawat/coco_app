package com.ws.design.coco_ecommerce_ui_kit.login;


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


public class LoginPresenter {

    private final APIService service;
    private final LoginView view;
    private CompositeSubscription subscriptions;

    public LoginPresenter(LoginView view) {
        this.view = view;
        service = ApiUtils.getAPIService();
        this.subscriptions = new CompositeSubscription();
    }

    public void Login(String email, String password) {
        view.showWait();


     /*   service.doLogin(email, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<LoginResponse>() {
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
                    public void onNext(LoginResponse post) {
                        view.removeWait();
                        view.onLoginSuccess(post);
                    }
                });*/



        try {
//            ApiInterface apiCall = APIClient.getClient(URLs.BASE_URL).create(ApiInterface.class);

            Call call = service.doLogin(email,password);
            call.enqueue(new Callback<LoginResponse>() {
                @Override
                public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                    view.removeWait();

                  try{
                      if (response.isSuccessful()) {
                          Log.d(TAG, call.request().url().toString());

                          view.onLoginSuccess(response.body());
                      }else{


                          JSONObject jsonObject = new JSONObject(response.errorBody().string());
                          view.onFailure(jsonObject.getString("message"));
                      }
                  }catch (Exception e){
                      e.printStackTrace();
                  }


                }

                @Override
                public void onFailure(Call<LoginResponse> call, Throwable e) {
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

    public void forgotPassword(String email) {
        view.showWait();

        try {

            Call call = service.doPasswordReset(email);
            call.enqueue(new Callback<ForgotPasswordResponse>() {
                @Override
                public void onResponse(Call<ForgotPasswordResponse> call, Response<ForgotPasswordResponse> response) {
                    view.removeWait();

                    try{
                        if (response.isSuccessful()) {
                            Log.d(TAG, call.request().url().toString());

                            view.forgotPassword(response.body());
                        }else{


                            JSONObject jsonObject = new JSONObject(response.errorBody().string());
                            view.onFailure(jsonObject.getString("message"));
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }


                }

                @Override
                public void onFailure(Call<ForgotPasswordResponse> call, Throwable e) {
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