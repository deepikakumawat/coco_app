package com.richkart.android.product_rating_list;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddRatingResponse {

    @SerializedName("status")
    @Expose
    private String mStatus;


    @SerializedName("data")
    @Expose
    private AddRatingData mAddRatingData;

    public String getmStatus() {
        return mStatus;
    }

    public void setmStatus(String mStatus) {
        this.mStatus = mStatus;
    }

    public AddRatingData getmAddRatingData() {
        return mAddRatingData;
    }

    public void setmAddRatingData(AddRatingData mAddRatingData) {
        this.mAddRatingData = mAddRatingData;
    }

    public class AddRatingData {

        @SerializedName("productratingid")
        @Expose
        private String mProductRatingId;

        @SerializedName("productid")
        @Expose
        private String mProductId;

        @SerializedName("userid")
        @Expose
        private String mUserId;

        @SerializedName("prod_rate")
        @Expose
        private String mProdRate;

        @SerializedName("rate_status")
        @Expose
        private String mRateStatus;

        @SerializedName("usercommnet")
        @Expose
        private String mUserComment;

        @SerializedName("ratingtime")
        @Expose
        private String mRatingTime;

        @SerializedName("modifieduser")
        @Expose
        private String mModifiedUser;

        @SerializedName("modifydate")
        @Expose
        private String mModifiedDate;

        public String getmProductRatingId() {
            return mProductRatingId;
        }

        public void setmProductRatingId(String mProductRatingId) {
            this.mProductRatingId = mProductRatingId;
        }

        public String getmProductId() {
            return mProductId;
        }

        public void setmProductId(String mProductId) {
            this.mProductId = mProductId;
        }

        public String getmUserId() {
            return mUserId;
        }

        public void setmUserId(String mUserId) {
            this.mUserId = mUserId;
        }

        public String getmProdRate() {
            return mProdRate;
        }

        public void setmProdRate(String mProdRate) {
            this.mProdRate = mProdRate;
        }

        public String getmRateStatus() {
            return mRateStatus;
        }

        public void setmRateStatus(String mRateStatus) {
            this.mRateStatus = mRateStatus;
        }

        public String getmUserComment() {
            return mUserComment;
        }

        public void setmUserComment(String mUserComment) {
            this.mUserComment = mUserComment;
        }

        public String getmRatingTime() {
            return mRatingTime;
        }

        public void setmRatingTime(String mRatingTime) {
            this.mRatingTime = mRatingTime;
        }

        public String getmModifiedUser() {
            return mModifiedUser;
        }

        public void setmModifiedUser(String mModifiedUser) {
            this.mModifiedUser = mModifiedUser;
        }

        public String getmModifiedDate() {
            return mModifiedDate;
        }

        public void setmModifiedDate(String mModifiedDate) {
            this.mModifiedDate = mModifiedDate;
        }
    }

}
