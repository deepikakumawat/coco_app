package com.richkart.android.interfaces;

public interface IPaymentListener {

    void onPaymentSuccess(String razorpayPaymentID);

    void onPaymentError(int code, String response);


}
