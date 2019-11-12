package com.richkart.android.login

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ForgotPasswordResponse(

        @SerializedName("data")
        @Expose
        var mForgotPasswordData: String = "",

        @SerializedName("message")
        @Expose
        var message: String = "",

        @SerializedName("status")
        @Expose
        var mStatus: String = ""

)