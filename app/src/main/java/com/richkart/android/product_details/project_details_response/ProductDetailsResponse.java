package com.richkart.android.product_details.project_details_response;

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

        @SerializedName("in_wishlist")
        @Expose
        private int mInWishlist;

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

        @SerializedName("pro_attr_array")
        @Expose
        private ArrayList<ProductAttrAraay> mProAttrArray;

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

        @SerializedName("attributes")
        @Expose
        private ArrayList<ProductAttributeData> mProductAttributes;

        @SerializedName("user_recent_views")
        @Expose
        private ArrayList<ProductDetailsSimilier> mUserRecentViews;


        public ArrayList<ProductAttrAraay> getmProAttrArray() {
            return mProAttrArray;
        }

        public void setmProAttrArray(ArrayList<ProductAttrAraay> mProAttrArray) {
            this.mProAttrArray = mProAttrArray;
        }

        public ArrayList<ProductDetailsSimilier> getmUserRecentViews() {
            return mUserRecentViews;
        }

        public void setmUserRecentViews(ArrayList<ProductDetailsSimilier> mUserRecentViews) {
            this.mUserRecentViews = mUserRecentViews;
        }

        public int getmInWishlist() {
            return mInWishlist;
        }

        public void setmInWishlist(int mInWishlist) {
            this.mInWishlist = mInWishlist;
        }

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

        public ArrayList<ProductAttributeData> getmProductAttributes() {
            return mProductAttributes;
        }

        public void setmProductAttributes(ArrayList<ProductAttributeData> mProductAttributes) {
            this.mProductAttributes = mProductAttributes;
        }
    }

    public class ProductAttrAraay {
        @SerializedName("type")
        @Expose
        private String mType;

        @SerializedName("data")
        @Expose
        private ArrayList<ProductAttrAraayData> mProductAttrAraayData;

        public String getmType() {
            return mType;
        }

        public void setmType(String mType) {
            this.mType = mType;
        }

        public ArrayList<ProductAttrAraayData> getmProductAttrAraayData() {
            return mProductAttrAraayData;
        }

        public void setmProductAttrAraayData(ArrayList<ProductAttrAraayData> mProductAttrAraayData) {
            this.mProductAttrAraayData = mProductAttrAraayData;
        }
    }

    public class ProductAttrAraayData {

        @SerializedName("productid")
        @Expose
        private String mProductId;

        @SerializedName("productslug")
        @Expose
        private String mProductSlug;

        @SerializedName("attr_type")
        @Expose
        private String mAttrType;

        @SerializedName("attribute_id")
        @Expose
        private String mAttributeId;

        @SerializedName("productimg")
        @Expose
        private String mProductImg;

        @SerializedName("attribute_name")
        @Expose
        private String mAttributeName;

        @SerializedName("attributereleteddata")
        @Expose
        private String mAttributeRelatedData;

        @SerializedName("slected")
        @Expose
        private boolean mSelected;

        public String getmProductId() {
            return mProductId;
        }

        public void setmProductId(String mProductId) {
            this.mProductId = mProductId;
        }

        public String getmProductSlug() {
            return mProductSlug;
        }

        public void setmProductSlug(String mProductSlug) {
            this.mProductSlug = mProductSlug;
        }

        public String getmAttrType() {
            return mAttrType;
        }

        public void setmAttrType(String mAttrType) {
            this.mAttrType = mAttrType;
        }

        public String getmAttributeId() {
            return mAttributeId;
        }

        public void setmAttributeId(String mAttributeId) {
            this.mAttributeId = mAttributeId;
        }

        public String getmProductImg() {
            return mProductImg;
        }

        public void setmProductImg(String mProductImg) {
            this.mProductImg = mProductImg;
        }

        public String getmAttributeName() {
            return mAttributeName;
        }

        public void setmAttributeName(String mAttributeName) {
            this.mAttributeName = mAttributeName;
        }

        public String getmAttributeRelatedData() {
            return mAttributeRelatedData;
        }

        public void setmAttributeRelatedData(String mAttributeRelatedData) {
            this.mAttributeRelatedData = mAttributeRelatedData;
        }

        public boolean getmSelected() {
            return mSelected;
        }

        public void setmSelected(boolean mSelected) {
            this.mSelected = mSelected;
        }
    }
}
