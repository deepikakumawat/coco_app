package com.richkart.android.signup

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class SignupData(
        @SerializedName("id")
        @Expose
         var mId: String? = null,

        @SerializedName("name")
        @Expose
         var mName: String? = null,

        @SerializedName("gender")
        @Expose
         val mGender: String? = null,

        @SerializedName("lastname")
        @Expose
         var mLastName: String? = null,

        @SerializedName("email")
        @Expose
         var mEmail: String? = null,

        @SerializedName("mobileno")
        @Expose
         var mMobileNo: String? = null,

        @SerializedName("address")
        @Expose
         var mAddress: String? = null,

        @SerializedName("usertype")
        @Expose
         var mUserType: String? = null,

        @SerializedName("email_verified_at")
        @Expose
         var mEmailVerifiedAt: String? = null,

        @SerializedName("password")
        @Expose
         var mPassword: String? = null,

        @SerializedName("remember_token")
        @Expose
         var mRememberToken: String? = null,

        @SerializedName("status")
        @Expose
         var mStatus: String? = null,

        @SerializedName("created_at")
        @Expose
         var mCreatedAt: String? = null,

        @SerializedName("createdby")
        @Expose
         var mCreatedBy: String? = null,

        @SerializedName("updated_at")
        @Expose
         var mUpdatedAt: String? = null,

        @SerializedName("modifiedby")
        @Expose
         var mModifiedBy: String? = null

)