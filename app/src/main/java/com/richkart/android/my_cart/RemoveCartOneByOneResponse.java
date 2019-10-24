package com.richkart.android.my_cart;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.richkart.android.product_details.AddToCartResponse;

public class RemoveCartOneByOneResponse {

    @SerializedName("status")
    @Expose
    private String mStatus;

    @SerializedName("data")
    @Expose
    private AddToCartResponse.CartData mData;

    @SerializedName("message")
    @Expose
    private String mMessage;

    public String getmStatus() {
        return mStatus;
    }

    public void setmStatus(String mStatus) {
        this.mStatus = mStatus;
    }

    public AddToCartResponse.CartData getmData() {
        return mData;
    }

    public void setmData(AddToCartResponse.CartData mData) {
        this.mData = mData;
    }

    public String getmMessage() {
        return mMessage;
    }

    public void setmMessage(String mMessage) {
        this.mMessage = mMessage;
    }
}
