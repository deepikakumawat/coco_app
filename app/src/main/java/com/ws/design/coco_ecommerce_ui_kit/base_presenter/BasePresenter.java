package com.ws.design.coco_ecommerce_ui_kit.base_presenter;




import android.util.Log;

import com.ws.design.coco_ecommerce_ui_kit.address.AddUpdateAddressResponse;
import com.ws.design.coco_ecommerce_ui_kit.address.AddressListResponse;
import com.ws.design.coco_ecommerce_ui_kit.address.AddressListView;
import com.ws.design.coco_ecommerce_ui_kit.address.DeleteAddressResponse;

import org.json.JSONObject;

import Network.APIService;
import Network.ApiUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.adapter.rxjava2.HttpException;
import rx.subscriptions.CompositeSubscription;

import static android.support.constraint.Constraints.TAG;

public class BasePresenter {

    private final APIService service;
    private final AddressListView view;
    private CompositeSubscription subscriptions;

    public BasePresenter(AddressListView view) {
        this.view = view;
        service = ApiUtils.getAPIService();
        this.subscriptions = new CompositeSubscription();
    }

    public void addressList(String userid) {
        view.showWait();

      /*  service.getAddressList("87")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<AddressListResponse>() {
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
                    public void onNext(AddressListResponse post) {
                        view.removeWait();
                        view.getAddressList(post);
                    }
                });*/


        try {

            Call call = service.getAddressList(userid);
            call.enqueue(new Callback<AddressListResponse>() {
                @Override
                public void onResponse(Call<AddressListResponse> call, Response<AddressListResponse> response) {
                    Log.d(TAG, call.request().url().toString());
                    view.removeWait();
                    try{
                        if (response.isSuccessful()) {

                            view.getAddressList(response.body());
                        }else{


                            JSONObject jsonObject = new JSONObject(response.errorBody().string());
                            view.onFailure(jsonObject.getString("message"));
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<AddressListResponse> call, Throwable e) {
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


    public void deteleAddress(String addressid) {
        view.showWait();

        try {

            Call call = service.deleteAddress(addressid);
            call.enqueue(new Callback<DeleteAddressResponse>() {
                @Override
                public void onResponse(Call<DeleteAddressResponse> call, Response<DeleteAddressResponse> response) {
                    Log.d(TAG, call.request().url().toString());
                    view.removeWait();
                    try{
                        if (response.isSuccessful()) {

                            view.deleteAddress(response.body());
                        }else{


                            JSONObject jsonObject = new JSONObject(response.errorBody().string());
                            view.onFailure(jsonObject.getString("message"));
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<DeleteAddressResponse> call, Throwable e) {
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