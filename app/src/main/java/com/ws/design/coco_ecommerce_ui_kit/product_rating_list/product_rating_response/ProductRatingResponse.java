package com.ws.design.coco_ecommerce_ui_kit.product_rating_list.product_rating_response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;


public class ProductRatingResponse {

    @SerializedName("status")
    @Expose
    private String mStatus;

    @SerializedName("data")
    @Expose
    private RatingData mData;

    public String getmStatus() {
        return mStatus;
    }

    public void setmStatus(String mStatus) {
        this.mStatus = mStatus;
    }

    public RatingData getmData() {
        return mData;
    }

    public void setmData(RatingData mData) {
        this.mData = mData;
    }

    public class RatingData{

        @SerializedName("total_reviews")
        @Expose
        private String mTotalReviews;

        @SerializedName("ratings")
        @Expose
        private ArrayList<Ratings> mRatings;

        @SerializedName("overall")
        @Expose
        private ArrayList<Overall> mOverAll;

        public String getmTotalReviews() {
            return mTotalReviews;
        }

        public void setmTotalReviews(String mTotalReviews) {
            this.mTotalReviews = mTotalReviews;
        }

        public ArrayList<Ratings> getmRatings() {
            return mRatings;
        }

        public void setmRatings(ArrayList<Ratings> mRatings) {
            this.mRatings = mRatings;
        }

        public ArrayList<Overall> getmOverAll() {
            return mOverAll;
        }

        public void setmOverAll(ArrayList<Overall> mOverAll) {
            this.mOverAll = mOverAll;
        }
    }

}
