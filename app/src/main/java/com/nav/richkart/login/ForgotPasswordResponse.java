package com.nav.richkart.login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ForgotPasswordResponse {


    @SerializedName("data")
    @Expose
    private String mForgotPasswordData;

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("status")
    @Expose
    private String mStatus;


    public String getmForgotPasswordData() {
        return mForgotPasswordData;
    }

    public void setmForgotPasswordData(String mForgotPasswordData) {
        this.mForgotPasswordData = mForgotPasswordData;
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
