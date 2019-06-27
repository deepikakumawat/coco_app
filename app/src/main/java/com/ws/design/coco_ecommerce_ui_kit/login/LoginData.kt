package com.ws.design.coco_ecommerce_ui_kit.login

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class LoginData(


    @SerializedName("id")
    @Expose
    var mId : String = "",

    @SerializedName("name")
    @Expose
    var mName : String = "",

    @SerializedName("lastname")
    @Expose
     var mLastName : String = "",

    @SerializedName("email")
    @Expose
     var mEmail : String = "",

    @SerializedName("mobileno")
    @Expose
     var mMobileNo : String = "",

    @SerializedName("address")
    @Expose
     var mAddress : String = "",

    @SerializedName("usertype")
    @Expose
     var mUserType : String = "",

    @SerializedName("email_verified_at")
    @Expose
     var mEmailVerifiedAt : String = "",

    @SerializedName("password")
    @Expose
     var mPassword : String = "",

    @SerializedName("remember_token")
    @Expose
     var mRememberToken : String = "",

    @SerializedName("status")
    @Expose
     var mStatus : String = "",

    @SerializedName("created_at")
    @Expose
     var mCreatedAt : String = "",

    @SerializedName("createdby")
    @Expose
     var mCreatedBy : String = "",

    @SerializedName("updated_at")
    @Expose
     var mUpdatedAt : String = "",

    @SerializedName("modifiedby")
    @Expose
     var mModifiedBy : String = ""


)