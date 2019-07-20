package com.ws.design.coco_ecommerce_ui_kit.product_details.project_details_response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ProductDetailsResponse {

    @SerializedName("status")
    @Expose
    private String mStatus;

    @SerializedName("data")
    @Expose
    private ProductData mData;

    public String getmStatus() {
        return mStatus;
    }

    public void setmStatus(String mStatus) {
        this.mStatus = mStatus;
    }

    public ProductData getmData() {
        return mData;
    }

    public void setmData(ProductData mData) {
        this.mData = mData;
    }

    public class ProductData {

        @SerializedName("title")
        @Expose
        private String mTitle;

        @SerializedName("ratings_count")
        @Expose
        private String mRatingCount;

        @SerializedName("avg_rating")
        @Expose
        private String mAvgRating;

        @SerializedName("productsdatasimiler")
        @Expose
        private ArrayList<ProductDetailsSimilier> mProductDetailsSimilier;

        @SerializedName("brudrumdata")
        @Expose
        private String mBrudrumdata;

        @SerializedName("product")
        @Expose
        private ProductDetailsSimilier mProduct;


        @SerializedName("top_review")
        @Expose
        private TopReview mTopReview;

        @SerializedName("cate_id")
        @Expose
        private ArrayList<String> mCatId;

        @SerializedName("productsbroughtdata")
        @Expose
        private ArrayList<ProductBroughtData> mProductBroughtData;

        @SerializedName("productgallery")
        @Expose
        private ArrayList<ProductGalleryData> mProductGallery;


        public String getmTitle() {
            return mTitle;
        }

        public void setmTitle(String mTitle) {
            this.mTitle = mTitle;
        }

        public ArrayList<ProductDetailsSimilier> getmProductDetailsSimilier() {
            return mProductDetailsSimilier;
        }

        public void setmProductDetailsSimilier(ArrayList<ProductDetailsSimilier> mProductDetailsSimilier) {
            this.mProductDetailsSimilier = mProductDetailsSimilier;
        }

        public String getmBrudrumdata() {
            return mBrudrumdata;
        }

        public void setmBrudrumdata(String mBrudrumdata) {
            this.mBrudrumdata = mBrudrumdata;
        }

        public ProductDetailsSimilier getmProduct() {
            return mProduct;
        }

        public void setmProduct(ProductDetailsSimilier mProduct) {
            this.mProduct = mProduct;
        }


        public ArrayList<ProductBroughtData> getmProductBroughtData() {
            return mProductBroughtData;
        }

        public void setmProductBroughtData(ArrayList<ProductBroughtData> mProductBroughtData) {
            this.mProductBroughtData = mProductBroughtData;
        }


        public ArrayList<String> getmCatId() {
            return mCatId;
        }

        public void setmCatId(ArrayList<String> mCatId) {
            this.mCatId = mCatId;
        }


        public String getmAvgRating() {
            return mAvgRating;
        }

        public void setmAvgRating(String mAvgRating) {
            this.mAvgRating = mAvgRating;
        }

        public ArrayList<ProductGalleryData> getmProductGallery() {
            return mProductGallery;
        }

        public void setmProductGallery(ArrayList<ProductGalleryData> mProductGallery) {
            this.mProductGallery = mProductGallery;
        }

        public TopReview getmTopReview() {
            return mTopReview;
        }

        public void setmTopReview(TopReview mTopReview) {
            this.mTopReview = mTopReview;
        }

        public String getmRatingCount() {
            return mRatingCount;
        }

        public void setmRatingCount(String mRatingCount) {
            this.mRatingCount = mRatingCount;
        }
    }
}
