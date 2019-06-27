package com.ws.design.coco_ecommerce_ui_kit.my_order;

import com.google.gson.annotations.SerializedName;

public class MyOrderRequest {

    @SerializedName("userid")
    private String mUserId;

    public MyOrderRequest(String userId) {
        mUserId = userId;
    }
}
