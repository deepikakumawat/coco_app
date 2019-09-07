package com.nav.richkart.checkout;


import com.nav.richkart.address.AddressListResponse;

public interface CheckoutView {

    void showWait();

    void removeWait();

    void onFailure(String appErrorMessage);

    void getAddressList(AddressListResponse addressListResponse);

    void getCheckoutPayment(CheckoutPaymentResponse checkoutPaymentResponse);




}
