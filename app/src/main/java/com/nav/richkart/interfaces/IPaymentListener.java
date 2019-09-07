package com.nav.richkart.interfaces;

public interface IPaymentListener {

    void onPaymentSuccess(String razorpayPaymentID);

    void onPaymentError(int code, String response);


}
