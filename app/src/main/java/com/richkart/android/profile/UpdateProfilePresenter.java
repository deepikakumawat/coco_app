package com.richkart.android.profile;


import android.util.Log;


import com.richkart.android.shared_preference.CocoPreferences;

import org.json.JSONObject;

import java.io.File;

import com.richkart.android.Network.APIService;
import com.richkart.android.Network.ApiUtils;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
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

    public void doUpdateProfile(String userid, String fName, String lName, String phone, String password) {
        view.showWait();

        try {

            Call call = service.doUpdateProfile(userid, fName, lName, phone, password);
            call.enqueue(new Callback<UpdateProfileResponse>() {
                @Override
                public void onResponse(Call<UpdateProfileResponse> call, Response<UpdateProfileResponse> response) {
                    Log.d(TAG, call.request().url().toString());
                    view.removeWait();


                    try {
                        if (response.isSuccessful()) {
                            Log.d(TAG, call.request().url().toString());

                            view.onUpdateProfileSuccess(response.body());
                        } else {


                            JSONObject jsonObject = new JSONObject(response.errorBody().string());
                            view.onFailure(jsonObject.getString("message"));
                        }
                    } catch (Exception e) {
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

    public void changeProfilePic(File file, String profileFileName) {
        view.showWait();

        try {

            Call call = service.changeProfilePic(
                    RequestBody.create(MediaType.parse("multipart/form-data"), CocoPreferences.getUserId()),
                    MultipartBody.Part.createFormData("profilepic", profileFileName,
                    RequestBody.create(MediaType.parse("image/*"), file)));

            call.enqueue(new Callback<ChangeProfileImageResponse>() {
                @Override
                public void onResponse(Call<ChangeProfileImageResponse> call, Response<ChangeProfileImageResponse> response) {
                    Log.d(TAG, call.request().url().toString());
                    view.removeWait();


                    try {
                        if (response.isSuccessful()) {
                            Log.d(TAG, call.request().url().toString());

                            view.changeProfileImages(response.body());
                        } else {


                            JSONObject jsonObject = new JSONObject(response.errorBody().string());
                            view.onFailure(jsonObject.getString("message"));
                        }
                    } catch (Exception e) {
                        view.onFailure("Something Went Wrong. Please try again later");

                        e.printStackTrace();                    }

                }

                @Override
                public void onFailure(Call<ChangeProfileImageResponse> call, Throwable e) {
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

    public void changePassword(String userid, String oldPass,  String newPass) {
        view.showWait();

        try {

            Call call = service.changePassword(userid, oldPass, newPass);
            call.enqueue(new Callback<ChanePasswordResponse>() {
                @Override
                public void onResponse(Call<ChanePasswordResponse> call, Response<ChanePasswordResponse> response) {
                    Log.d(TAG, call.request().url().toString());
                    view.removeWait();


                    try {
                        if (response.isSuccessful()) {
                            Log.d(TAG, call.request().url().toString());

                            view.changePassword(response.body());
                        } else {


                            JSONObject jsonObject = new JSONObject(response.errorBody().string());
                            view.onFailure(jsonObject.getString("message"));
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }

                @Override
                public void onFailure(Call<ChanePasswordResponse> call, Throwable e) {
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

