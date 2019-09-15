package com.nav.richkart.profile;

public interface UpdateView {

        void showWait();

        void removeWait();

        void onFailure(String appErrorMessage);

        void onUpdateProfileSuccess(UpdateProfileResponse updateProfileResponse);

        void changeProfileImages(ChangeProfileImageResponse changeProfileImageResponse);

        void changePassword(ChanePasswordResponse chanePasswordResponse);
}