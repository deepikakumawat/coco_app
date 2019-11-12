package com.richkart.android.login;

public interface LoginView {

    void showWait();

    void removeWait();

    void onFailure(String appErrorMessage);

    void onLoginSuccess(LoginResponse loginResponse );

    void forgotPassword(ForgotPasswordResponse forgotPasswordResponse);
}
