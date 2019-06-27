package com.ws.design.coco_ecommerce_ui_kit.profile;


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

public class UpdateProfilePresenter {

    private final APIService service;
    private final UpdateView view;
    private CompositeSubscription subscriptions;

    public UpdateProfilePresenter(UpdateView view) {
        this.view = view;
        service = ApiUtils.getAPIService();
        this.subscriptions = new CompositeSubscription();
    }

    public void doUpdateProfile(String userid,String fName, String lName, String phone, String password) {
        view.showWait();

      /*  service.doUpdateProfile(fName,lName,password,confirmPassword).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<UpdateProfileResponse>() {
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
                    public void onNext(UpdateProfileResponse updateProfileResponse) {
                        view.removeWait();
                        view.onUpdateProfileSuccess(updateProfileResponse);
                    }
                });*/

        try {

            Call call = service.doUpdateProfile(userid, fName,lName,phone,password);
            call.enqueue(new Callback<UpdateProfileResponse>() {
                @Override
                public void onResponse(Call<UpdateProfileResponse> call, Response<UpdateProfileResponse> response) {
                    Log.d(TAG, call.request().url().toString());
                    view.removeWait();


                    try{
                        if (response.isSuccessful()) {
                            Log.d(TAG, call.request().url().toString());

                            view.onUpdateProfileSuccess(response.body());
                        }else{


                            JSONObject jsonObject = new JSONObject(response.errorBody().string());
                            view.onFailure(jsonObject.getString("message"));
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                }

                @Override
                public void onFailure(Call<UpdateProfileResponse> call, Throwable e) {
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

