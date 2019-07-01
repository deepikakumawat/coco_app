package com.ws.design.coco_ecommerce_ui_kit.login

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class LoginResponse(

        val KEY_USERID: String = "UserID",
        val KEY_USEREMAIL: String = "UserEmail",
        val KEY_USERPHONE: String = "UserPhone",
        val KEY_FIRST_NAME: String = "FirstName",
        val KEY_LAST_NAME: String = "LastName",


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