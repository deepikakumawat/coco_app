package com.richkart.android.login

interface LoginView {

     fun showWait()

     fun removeWait()

     fun onFailure(appErrorMessage: String)

     fun onLoginSuccess(loginResponse: LoginResponse)

     fun forgotPassword(forgotPasswordResponse: ForgotPasswordResponse)
}