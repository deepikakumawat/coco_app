package com.ws.design.coco_ecommerce_ui_kit.address;




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

public class AddressPresenter {

    private final APIService service;
    private final AddressListView view;
    private CompositeSubscription subscriptions;

    public AddressPresenter(AddressListView view) {
        this.view = view;
        service = ApiUtils.getAPIService();
        this.subscriptions = new CompositeSubscription();
    }

    public void addressList(String userid) {
        view.showWait();

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
                        view.onFailure("Something Went Wrong. Please try again later");

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
                        view.onFailure("Something Went Wrong. Please try again later");

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



    public void addAddress(String name, String userId, String address1, String address2,String landmark, String city, String state, String country, String phone, String id, String zipcode, String addressType ) {
        view.showWait();
        try {

            Call call = service.doAddUpdateAddress(id,name,userId,address1,address2,city,state,country,zipcode,addressType,landmark,phone);
            call.enqueue(new Callback<AddUpdateAddressResponse>() {
                @Override
                public void onResponse(Call<AddUpdateAddressResponse> call, Response<AddUpdateAddressResponse> response) {
                    Log.d(TAG, call.request().url().toString());
                    view.removeWait();
                    try{
                        if (response.isSuccessful()) {

                            view.addUpdateAddress(response.body());
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
                public void onFailure(Call<AddUpdateAddressResponse> call, Throwable e) {
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