package com.nav.richkart.my_cart;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EmptyCartResponse {

    @SerializedName("status")
    @Expose
    private String mStatus;

    @SerializedName("data")
    @Expose
    private String mData;

    @SerializedName("message")
    @Expose
    private String mMessage;

    public String getmStatus() {
        return mStatus;
    }

    public void setmStatus(String mStatus) {
        this.mStatus = mStatus;
    }

    public String getmData() {
        return mData;
    }

    public void setmData(String mData) {
        this.mData = mData;
    }

    public String getmMessage() {
        return mMessage;
    }

    public void setmMessage(String mMessage) {
        this.mMessage = mMessage;
    }
}
