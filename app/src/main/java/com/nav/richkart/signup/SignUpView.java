package com.nav.richkart.signup;

public interface SignUpView {

        void showWait();

        void removeWait();

        void onFailure(String appErrorMessage);

        void onSignSuccess(SignUpResponse signUpResponse);

        void getOTP(GetOTPResponse getOTPResponse);
}