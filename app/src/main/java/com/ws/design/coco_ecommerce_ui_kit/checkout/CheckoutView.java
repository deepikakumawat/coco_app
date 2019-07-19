package com.ws.design.coco_ecommerce_ui_kit.checkout;


import com.ws.design.coco_ecommerce_ui_kit.address.AddressListResponse;

public interface CheckoutView {

    void showWait();

    void removeWait();

    void onFailure(String appErrorMessage);

    void getAddressList(AddressListResponse addressListResponse);

    void getCheckoutPayment(CheckoutPaymentResponse checkoutPaymentResponse);




}
