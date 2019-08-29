package com.ws.design.coco_ecommerce_ui_kit.profile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class ChangeProfileImageResponse {



    @SerializedName("status")
    @Expose
    private String mStatus;

    @SerializedName("data")
    @Expose
    private ChangeProfileImage mData;

    public String getmStatus() {
        return mStatus;
    }

    public void setmStatus(String mStatus) {
        this.mStatus = mStatus;
    }

    public ChangeProfileImage getmData() {
        return mData;
    }

    public void setmData(ChangeProfileImage mData) {
        this.mData = mData;
    }

    public class ChangeProfileImage {
        @SerializedName("profilepic")
        @Expose
        private String mProfilePic;

        public String getmProfilePic() {
            return mProfilePic;
        }

        public void setmProfilePic(String mProfilePic) {
            this.mProfilePic = mProfilePic;
        }
    }
}
