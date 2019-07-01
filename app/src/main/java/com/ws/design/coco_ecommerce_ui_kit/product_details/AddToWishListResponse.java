package com.ws.design.coco_ecommerce_ui_kit.product_details;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class AddToWishListResponse {

    @SerializedName("status")
    @Expose
    private String mStatus;

    @SerializedName("message")
    @Expose
    private String mMessage;


    @SerializedName("data")
    @Expose
    private ArrayList<AddToWishListData> mAddToWishlistData;

    public String getmStatus() {
        return mStatus;
    }

    public void setmStatus(String mStatus) {
        this.mStatus = mStatus;
    }

    public String getmMessage() {
        return mMessage;
    }

    public void setmMessage(String mMessage) {
        this.mMessage = mMessage;
    }

    public ArrayList<AddToWishListData> getmAddToWishlistData() {
        return mAddToWishlistData;
    }

    public void setmAddToWishlistData(ArrayList<AddToWishListData> mAddToWishlistData) {
        this.mAddToWishlistData = mAddToWishlistData;
    }

    public class AddToWishListData {

        @SerializedName("wishlist")
        @Expose
        private String mWishlist;

        @SerializedName("userid")
        @Expose
        private String mUserId;

        @SerializedName("productid")
        @Expose
        private String mProductId;

        @SerializedName("createdate")
        @Expose
        private String mCreatedDate;

        public String getmWishlist() {
            return mWishlist;
        }

        public void setmWishlist(String mWishlist) {
            this.mWishlist = mWishlist;
        }

        public String getmUserId() {
            return mUserId;
        }

        public void setmUserId(String mUserId) {
            this.mUserId = mUserId;
        }

        public String getmProductId() {
            return mProductId;
        }

        public void setmProductId(String mProductId) {
            this.mProductId = mProductId;
        }

        public String getmCreatedDate() {
            return mCreatedDate;
        }

        public void setmCreatedDate(String mCreatedDate) {
            this.mCreatedDate = mCreatedDate;
        }
    }
}
