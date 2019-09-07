package com.nav.richkart.my_order;

import com.google.gson.annotations.SerializedName;

public class MyOrderRequest {

    @SerializedName("userid")
    private String mUserId;

    public MyOrderRequest(String userId) {
        mUserId = userId;
    }
}
