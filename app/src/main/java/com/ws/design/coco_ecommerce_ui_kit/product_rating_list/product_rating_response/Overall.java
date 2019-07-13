package com.ws.design.coco_ecommerce_ui_kit.product_rating_list.product_rating_response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Overall {

    @SerializedName("avg_rating")
    @Expose
    private String mAvgRating;

    @SerializedName("five_star")
    @Expose
    private String mFiveStar;

    @SerializedName("four_star")
    @Expose
    private String mFourStar;

    @SerializedName("three_star")
    @Expose
    private String mThreeStar;

    @SerializedName("two_star")
    @Expose
    private String mTwoStar;

    @SerializedName("one_star")
    @Expose
    private String mOneStar;

    public String getmAvgRating() {
        return mAvgRating;
    }

    public void setmAvgRating(String mAvgRating) {
        this.mAvgRating = mAvgRating;
    }

    public String getmFiveStar() {
        return mFiveStar;
    }

    public void setmFiveStar(String mFiveStar) {
        this.mFiveStar = mFiveStar;
    }

    public String getmFourStar() {
        return mFourStar;
    }

    public void setmFourStar(String mFourStar) {
        this.mFourStar = mFourStar;
    }

    public String getmThreeStar() {
        return mThreeStar;
    }

    public void setmThreeStar(String mThreeStar) {
        this.mThreeStar = mThreeStar;
    }

    public String getmTwoStar() {
        return mTwoStar;
    }

    public void setmTwoStar(String mTwoStar) {
        this.mTwoStar = mTwoStar;
    }

    public String getmOneStar() {
        return mOneStar;
    }

    public void setmOneStar(String mOneStar) {
        this.mOneStar = mOneStar;
    }
}
