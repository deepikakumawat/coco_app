package com.richkart.android.login

import android.support.constraint.Constraints.TAG
import android.util.Log
import com.richkart.android.Network.APIService
import com.richkart.android.Network.ApiUtils
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.adapter.rxjava2.HttpException
import rx.subscriptions.CompositeSubscription

class LoginPresenter {

    var view: LoginView
    var service: APIService
    var subscriptions: CompositeSubscription


    constructor(view: LoginView) {
        this.view = view
        service = ApiUtils.getAPIService()
        this.subscriptions = CompositeSubscription()
    }

    fun Login(email: String, password: String) {
        view.showWait()


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

            val call = service.doLogin(email, password)
            call.enqueue(object : Callback<LoginResponse> {
                override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                    view.removeWait()

                    try {
                        if (response.isSuccessful) {
                            Log.d(TAG, call.request().url().toString())
                            view.onLoginSuccess(response.body()!!)
                        } else {
                            val jsonObject = JSONObject(response.errorBody()!!.string())
                            view.onFailure(jsonObject.getString("message"))
                        }
                    } catch (e: Exception) {
                        view.onFailure("Something Went Wrong. Please try again later")

                        e.printStackTrace()

                    }


                }

                override fun onFailure(call: Call<LoginResponse>, e: Throwable) {
                    view.removeWait()
                    try {
                        val jsonObject = JSONObject((e as HttpException).response().errorBody()!!.string())
                        view.onFailure(jsonObject.getString("message"))
                    } catch (ee: Exception) {

                    }

                }
            })
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    fun forgotPassword(email: String) {
        view.showWait()

        try {

            var call = service.doPasswordReset(email)
            call.enqueue(object : Callback<ForgotPasswordResponse> {
                override fun onResponse(call: Call<ForgotPasswordResponse>, response: Response<ForgotPasswordResponse>) {
                    view.removeWait()

                    try {
                        if (response.isSuccessful()) {
                            Log.d(TAG, call.request().url().toString());

                            view.forgotPassword(response.body()!!)
                        } else {


                            var jsonObject = JSONObject(response.errorBody()!!.string())
                            view.onFailure(jsonObject.getString("message"))
                        }
                    } catch (e: Exception) {
                        view.onFailure("Something Went Wrong. Please try again later")

                        e.printStackTrace()
                    }


                }

                override fun onFailure(call: Call<ForgotPasswordResponse>, e: Throwable) {
                    view.removeWait()
                    try {
                        var jsonObject = JSONObject((e as (HttpException)).response().errorBody()!!.string())
                        view.onFailure(jsonObject.getString("message"))
                    } catch (e: Exception) {

                    }

                }
            })
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}