package com.ws.design.coco_ecommerce_ui_kit.product_details.project_details_response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductGalleryData  {

    @SerializedName("proimgid")
    @Expose
    private String mProImgId;

    @SerializedName("proimgename")
    @Expose
    private String mProImgName;

    @SerializedName("proimgurl")
    @Expose
    private String mProImgUrl;

    @SerializedName("gallerystatus")
    @Expose
    private String mGalleryStatus;

    @SerializedName("proimgcreateddate")
    @Expose
    private String mProImgCreatedDate;

    @SerializedName("proimgcreateduser")
    @Expose
    private String mProImgCreatedUser;


    public String getmProImgId() {
        return mProImgId;
    }

    public void setmProImgId(String mProImgId) {
        this.mProImgId = mProImgId;
    }

    public String getmProImgName() {
        return mProImgName;
    }

    public void setmProImgName(String mProImgName) {
        this.mProImgName = mProImgName;
    }

    public String getmProImgUrl() {
        return mProImgUrl;
    }

    public void setmProImgUrl(String mProImgUrl) {
        this.mProImgUrl = mProImgUrl;
    }

    public String getmGalleryStatus() {
        return mGalleryStatus;
    }

    public void setmGalleryStatus(String mGalleryStatus) {
        this.mGalleryStatus = mGalleryStatus;
    }

    public String getmProImgCreatedDate() {
        return mProImgCreatedDate;
    }

    public void setmProImgCreatedDate(String mProImgCreatedDate) {
        this.mProImgCreatedDate = mProImgCreatedDate;
    }

    public String getmProImgCreatedUser() {
        return mProImgCreatedUser;
    }

    public void setmProImgCreatedUser(String mProImgCreatedUser) {
        this.mProImgCreatedUser = mProImgCreatedUser;
    }
}
