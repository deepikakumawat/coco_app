package com.nav.richkart.address;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class AddressListResponse implements Serializable {

    @SerializedName("status")
    @Expose
    private String mStatus;


    @SerializedName("data")
    @Expose
    private ArrayList<AddressData> mAddressData;

    public String getmStatus() {
        return mStatus;
    }

    public void setmStatus(String mStatus) {
        this.mStatus = mStatus;
    }

    public ArrayList<AddressData> getmAddressData() {
        return mAddressData;
    }

    public void setmAddressData(ArrayList<AddressData> mAddressData) {
        this.mAddressData = mAddressData;
    }


    public class AddressData implements Serializable{

        private boolean mSelecetdAddress;

        private boolean isDeleteButtonVisible;


        @SerializedName("id")
        @Expose
        private String mId;

        @SerializedName("userid")
        @Expose
        private String mUserID;

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


        public String getmId() {
            return mId;
        }

        public void setmId(String mId) {
            this.mId = mId;
        }

        public String getmUserID() {
            return mUserID;
        }

        public void setmUserID(String mUserID) {
            this.mUserID = mUserID;
        }

        public String getmAddressType() {
            return mAddressType;
        }

        public void setmAddressType(String mAddressType) {
            this.mAddressType = mAddressType;
        }

        public String getmName() {
            return mName;
        }

        public void setmName(String mName) {
            this.mName = mName;
        }

        public String getmAddress1() {
            return mAddress1;
        }

        public void setmAddress1(String mAddress1) {
            this.mAddress1 = mAddress1;
        }

        public String getmAddress2() {
            return mAddress2;
        }

        public void setmAddress2(String mAddress2) {
            this.mAddress2 = mAddress2;
        }

        public String getmLandmark() {
            return mLandmark;
        }

        public void setmLandmark(String mLandmark) {
            this.mLandmark = mLandmark;
        }

        public String getmCity() {
            return mCity;
        }

        public void setmCity(String mCity) {
            this.mCity = mCity;
        }

        public String getmState() {
            return mState;
        }

        public void setmState(String mState) {
            this.mState = mState;
        }

        public String getmCountry() {
            return mCountry;
        }

        public void setmCountry(String mCountry) {
            this.mCountry = mCountry;
        }

        public String getmZipcode() {
            return mZipcode;
        }

        public void setmZipcode(String mZipcode) {
            this.mZipcode = mZipcode;
        }

        public String getmPhone() {
            return mPhone;
        }

        public void setmPhone(String mPhone) {
            this.mPhone = mPhone;
        }

        public boolean ismSelecetdAddress() {
            return mSelecetdAddress;
        }

        public void setmSelecetdAddress(boolean mSelecetdAddress) {
            this.mSelecetdAddress = mSelecetdAddress;
        }

        public boolean isDeleteButtonVisible() {
            return isDeleteButtonVisible;
        }

        public void setDeleteButtonVisible(boolean deleteButtonVisible) {
            isDeleteButtonVisible = deleteButtonVisible;
        }
    }
}
