package com.nav.richkart.my_order;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CancelOrderResponse {

    @SerializedName("status")
    @Expose
    private String mStatus;

    @SerializedName("message")
    @Expose
    private String mMessage;

    @SerializedName("data")
    @Expose
    private String mData;


    public String getmStatus() {
        return mStatus;
    }

    public void setmStatus(String mStatus) {
        this.mStatus = mStatus;
    }

    public String getmMessage() {
        return mMessage;
    }

    public void setmMessage(String mMessage) {
        this.mMessage = mMessage;
    }

    public String getmData() {
        return mData;
    }

    public void setmData(String mData) {
        this.mData = mData;
    }
}
