package com.richkart.android.checkout;


import com.richkart.android.address.AddressListResponse;

public interface CheckoutView {

    void showWait();

    void removeWait();

    void onFailure(String appErrorMessage);

    void getAddressList(AddressListResponse addressListResponse);





}
