package com.ws.design.coco_ecommerce_ui_kit.signup;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetOTPResponse {

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("status")
    @Expose
    private String mStatus;

    @SerializedName("data")
    @Expose
    private GetOTPData mOTPData;

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

    public GetOTPData getmOTPData() {
        return mOTPData;
    }

    public void setmOTPData(GetOTPData mOTPData) {
        this.mOTPData = mOTPData;
    }

    public class GetOTPData {

        @SerializedName("vctoken")
        @Expose
        private String mVcToken;

        public String getmVcToken() {
            return mVcToken;
        }

        public void setmVcToken(String mVcToken) {
            this.mVcToken = mVcToken;
        }
    }
}
