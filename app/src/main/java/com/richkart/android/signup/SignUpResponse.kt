package com.richkart.android.signup

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class SignUpResponse (

    @SerializedName("message")
    @Expose
    var  message : String,

    @SerializedName("status")
    @Expose
    var  mStatus : String,

    @SerializedName("data")
    @Expose
    var  mLoginData : SignupData
)