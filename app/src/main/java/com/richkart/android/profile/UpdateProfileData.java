package com.richkart.android.profile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UpdateProfileData {

    @SerializedName("id")
    @Expose
    private String mId;

    @SerializedName("name")
    @Expose
    private String mName;

    @SerializedName("lastname")
    @Expose
    private String mLastName;

    @SerializedName("email")
    @Expose
    private String mEmail;

    @SerializedName("mobileno")
    @Expose
    private String mMobileNo;

    @SerializedName("address")
    @Expose
    private String mAddress;

    @SerializedName("usertype")
    @Expose
    private String mUserType;

    @SerializedName("email_verified_at")
    @Expose
    private String mEmailVerifiedAt;

    @SerializedName("password")
    @Expose
    private String mPassword;

    @SerializedName("remember_token")
    @Expose
    private String mRememberToken;

    @SerializedName("status")
    @Expose
    private String mStatus;

    @SerializedName("created_at")
    @Expose
    private String mCreatedAt;

    @SerializedName("createdby")
    @Expose
    private String mCreatedBy;

    @SerializedName("updated_at")
    @Expose
    private String mUpdatedAt;

    @SerializedName("modifiedby")
    @Expose
    private String mModifiedBy;


    public String getmId() {
        return mId;
    }

    public void setmId(String mId) {
        this.mId = mId;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmLastName() {
        return mLastName;
    }

    public void setmLastName(String mLastName) {
        this.mLastName = mLastName;
    }

    public String getmEmail() {
        return mEmail;
    }

    public void setmEmail(String mEmail) {
        this.mEmail = mEmail;
    }

    public String getmMobileNo() {
        return mMobileNo;
    }

    public void setmMobileNo(String mMobileNo) {
        this.mMobileNo = mMobileNo;
    }

    public String getmAddress() {
        return mAddress;
    }

    public void setmAddress(String mAddress) {
        this.mAddress = mAddress;
    }

    public String getmUserType() {
        return mUserType;
    }

    public void setmUserType(String mUserType) {
        this.mUserType = mUserType;
    }

    public String getmEmailVerifiedAt() {
        return mEmailVerifiedAt;
    }

    public void setmEmailVerifiedAt(String mEmailVerifiedAt) {
        this.mEmailVerifiedAt = mEmailVerifiedAt;
    }

    public String getmPassword() {
        return mPassword;
    }

    public void setmPassword(String mPassword) {
        this.mPassword = mPassword;
    }

    public String getmRememberToken() {
        return mRememberToken;
    }

    public void setmRememberToken(String mRememberToken) {
        this.mRememberToken = mRememberToken;
    }

    public String getmStatus() {
        return mStatus;
    }

    public void setmStatus(String mStatus) {
        this.mStatus = mStatus;
    }

    public String getmCreatedAt() {
        return mCreatedAt;
    }

    public void setmCreatedAt(String mCreatedAt) {
        this.mCreatedAt = mCreatedAt;
    }

    public String getmCreatedBy() {
        return mCreatedBy;
    }

    public void setmCreatedBy(String mCreatedBy) {
        this.mCreatedBy = mCreatedBy;
    }

    public String getmUpdatedAt() {
        return mUpdatedAt;
    }

    public void setmUpdatedAt(String mUpdatedAt) {
        this.mUpdatedAt = mUpdatedAt;
    }

    public String getmModifiedBy() {
        return mModifiedBy;
    }

    public void setmModifiedBy(String mModifiedBy) {
        this.mModifiedBy = mModifiedBy;
    }
}

