package com.ws.design.coco_ecommerce_ui_kit.login

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class LoginResponse(

        @SerializedName("message")
        @Expose
        var message: String = "",

        @SerializedName("status")
        @Expose
        var mStatus: String = "",

        @SerializedName("data")
        @Expose
        var loginData: LoginData? = null
)