package com.ws.design.coco_ecommerce_ui_kit.address;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.ws.design.coco_ecommerce_ui_kit.profile.UpdateProfileData;

import java.util.ArrayList;


public class AddUpdateAddressResponse {

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("status")
    @Expose
    private String mStatus;

    @SerializedName("data")
    @Expose
    private ArrayList<AddUpdateAddressData> mLoginData;

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


    public class AddUpdateAddressData {

        @SerializedName("id")
        @Expose
        private String mId;

        @SerializedName("userid")
        @Expose
        private String mUserId;

        @SerializedName("addresstype")
        @Expose
        private String mAddressType;

        @SerializedName("name")
        @Expose
        private String mName;

        @SerializedName("address1")
        @Expose
        private String mAddress1;

        @SerializedName("address2")
        @Expose
        private String mAddress2;

        @SerializedName("landmark")
        @Expose
        private String mLandmark;

        @SerializedName("city")
        @Expose
        private String mCity;

        @SerializedName("state")
        @Expose
        private String mState;

        @SerializedName("country")
        @Expose
        private String mCountry;

        @SerializedName("zipcode")
        @Expose
        private String mZipcode;

        @SerializedName("phone")
        @Expose
        private String mPhone;


    }
}
