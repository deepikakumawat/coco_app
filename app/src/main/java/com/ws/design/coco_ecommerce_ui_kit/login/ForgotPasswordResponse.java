package com.ws.design.coco_ecommerce_ui_kit.login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ForgotPasswordResponse {




    @SerializedName("data")
    @Expose
    private ForgotPasswordData mForgotPasswordData;

    public ForgotPasswordData getmForgotPasswordData() {
        return mForgotPasswordData;
    }

    public void setmForgotPasswordData(ForgotPasswordData mForgotPasswordData) {
        this.mForgotPasswordData = mForgotPasswordData;
    }

    public class ForgotPasswordData {

        @SerializedName("message")
        @Expose
        private String message;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
