package com.richkart.android.profile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class ChanePasswordResponse {

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("status")
    @Expose
    private String mStatus;



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
