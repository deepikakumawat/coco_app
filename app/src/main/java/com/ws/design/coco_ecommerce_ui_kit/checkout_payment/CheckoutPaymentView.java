package com.ws.design.coco_ecommerce_ui_kit.checkout_payment;




public interface CheckoutPaymentView {

    void showWait();

    void removeWait();

    void onFailure(String appErrorMessage);


    void getCheckoutPayment(CheckoutPaymentResponse checkoutPaymentResponse);


}
