package com.ws.design.coco_ecommerce_ui_kit.signup;

public interface SignUpView {

        void showWait();

        void removeWait();

        void onFailure(String appErrorMessage);

        void onSignSuccess(SignUpResponse signUpResponse);
}