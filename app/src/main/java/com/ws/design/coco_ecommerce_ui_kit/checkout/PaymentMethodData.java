package com.ws.design.coco_ecommerce_ui_kit.checkout;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class PaymentMethodData {


    private boolean isSelectedPayment;

    private String paymentMethod;

    public PaymentMethodData(boolean isSelectedPayment, String paymentMethod) {
        this.isSelectedPayment = isSelectedPayment;
        this.paymentMethod = paymentMethod;
    }

    public boolean isSelectedPayment() {
        return isSelectedPayment;
    }

    public void setSelectedPayment(boolean selectedPayment) {
        isSelectedPayment = selectedPayment;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}
