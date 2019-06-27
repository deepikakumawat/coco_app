package com.ws.design.coco_ecommerce_ui_kit.login;

public interface LoginView {

    void showWait();

    void removeWait();

    void onFailure(String appErrorMessage);

    void onLoginSuccess(LoginResponse loginResponse);

    void forgotPassword(ForgotPasswordResponse forgotPasswordResponse);
}
