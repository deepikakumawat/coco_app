package com.richkart.android.signup

interface SignUpView {

    fun showWait()

    fun removeWait()

    fun onFailure(appErrorMessage: String)

    fun onSignSuccess(signUpResponse: SignUpResponse)

    fun getOTP(getOTPResponse: GetOTPResponse)
}