package com.ws.design.coco_ecommerce_ui_kit.interfaces;

public interface IPaymentListener {

    void onPaymentSuccess(String razorpayPaymentID);

    void onPaymentError(int code, String response);


}
