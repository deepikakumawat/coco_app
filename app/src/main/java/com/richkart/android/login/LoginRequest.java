package com.richkart.android.login;

import com.google.gson.annotations.SerializedName;

public class LoginRequest {

    @SerializedName("email")
    private String mLoginName;
    @SerializedName("password")
    private String mPassword;

    public LoginRequest(String loginName, String password) {
        mLoginName = loginName;
        mPassword = password;
    }
}
