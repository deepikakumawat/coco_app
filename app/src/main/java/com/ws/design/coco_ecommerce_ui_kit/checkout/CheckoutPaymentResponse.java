package com.ws.design.coco_ecommerce_ui_kit.checkout;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class CheckoutPaymentResponse {

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("status")
    @Expose
    private String mStatus;

    @SerializedName("data")
    @Expose
    private String mData;

    public String getmData() {
        return mData;
    }

    public void setmData(String mData) {
        this.mData = mData;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getmStatus() {
        return mStatus;
    }

    public void setmStatus(String mStatus) {
        this.mStatus = mStatus;
    }


}
