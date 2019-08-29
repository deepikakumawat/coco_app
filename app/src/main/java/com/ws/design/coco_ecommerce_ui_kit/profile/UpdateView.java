package com.ws.design.coco_ecommerce_ui_kit.profile;

public interface UpdateView {

        void showWait();

        void removeWait();

        void onFailure(String appErrorMessage);

        void onUpdateProfileSuccess(UpdateProfileResponse updateProfileResponse);

        void changeProfileImages(ChangeProfileImageResponse changeProfileImageResponse);
}