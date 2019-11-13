package com.richkart.android.signup

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class GetOTPResponse(

        @SerializedName("message")
        @Expose
        var message: String? = null,

        @SerializedName("status")
        @Expose
        var mStatus: String? = null,

        @SerializedName("data")
        @Expose
        var mOTPData: GetOTPData? = null


)

data class GetOTPData(

        @SerializedName("vctoken")
        @Expose
        var mVcToken: String? = null


)