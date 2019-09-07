package com.nav.richkart.profile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class UpdateProfileResponse {

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("status")
    @Expose
    private String mStatus;

    @SerializedName("data")
    @Expose
    private UpdateProfileData mLoginData;

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

    public UpdateProfileData getmLoginData() {
        return mLoginData;
    }

    public void setmLoginData(UpdateProfileData mLoginData) {
        this.mLoginData = mLoginData;
    }
}
