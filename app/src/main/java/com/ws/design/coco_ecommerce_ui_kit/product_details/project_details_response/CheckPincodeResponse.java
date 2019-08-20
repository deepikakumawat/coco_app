package com.ws.design.coco_ecommerce_ui_kit.product_details.project_details_response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class CheckPincodeResponse {

    @SerializedName("message")
    @Expose
    private String mMessage;

    @SerializedName("status")
    @Expose
    private String mStatus;

    @SerializedName("data")
    @Expose
    private ArrayList<CheckPincodeData> mData;


    public String getmMessage() {
        return mMessage;
    }

    public void setmMessage(String mMessage) {
        this.mMessage = mMessage;
    }

    public String getmStatus() {
        return mStatus;
    }

    public void setmStatus(String mStatus) {
        this.mStatus = mStatus;
    }

    public ArrayList<CheckPincodeData> getmData() {
        return mData;
    }

    public void setmData(ArrayList<CheckPincodeData> mData) {
        this.mData = mData;
    }

    private class CheckPincodeData {
    }
}
