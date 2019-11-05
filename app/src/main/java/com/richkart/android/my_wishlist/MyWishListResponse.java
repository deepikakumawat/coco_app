package com.richkart.android.my_wishlist;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MyWishListResponse {

    @SerializedName("status")
    @Expose
    private String mStatus;


    @SerializedName("data")
    @Expose
    private MyWishListData mMyWishlistData;

    public String getmStatus() {
        return mStatus;
    }

    public void setmStatus(String mStatus) {
        this.mStatus = mStatus;
    }

    public MyWishListData getmMyWishlistData() {
        return mMyWishlistData;
    }

    public void setmMyWishlistData(MyWishListData mMyWishlistData) {
        this.mMyWishlistData = mMyWishlistData;
    }

    public class MyWishListData {

        @SerializedName("brudrumdata")
        @Expose
        private String mBrudrumdata;


        @SerializedName("products")
        @Expose
        private ArrayList<ProductData> mProductData;

        public String getmBrudrumdata() {
            return mBrudrumdata;
        }

        public void setmBrudrumdata(String mBrudrumdata) {
            this.mBrudrumdata = mBrudrumdata;
        }

        public ArrayList<ProductData> getmProductData() {
            return mProductData;
        }

        public void setmProductData(ArrayList<ProductData> mProductData) {
            this.mProductData = mProductData;
        }
    }

    public class ProductData {

        @SerializedName("avg_rating")
        @Expose
        private String mAvgRating;

        @SerializedName("wishlist")
        @Expose
        private String mWishList;

        @SerializedName("productid")
        @Expose
        private String mProductId;

        @SerializedName("productname")
        @Expose
        private String mProductName;

        @SerializedName("productslug")
        @Expose
        private String mProductSlug;

        @SerializedName("productsdetails")
        @Expose
        private String mProductDetails;

        @SerializedName("productshortdetails")
        @Expose
        private String mProductShortDetails;

        @SerializedName("productshippolicy")
        @Expose
        private String mProductShipPolicy;

        @SerializedName("refundpolicy")
        @Expose
        private String mRefundPolicy;

        @SerializedName("productcancelpolicy")
        @Expose
        private String mProductCancelPolicy;

        @SerializedName("crosssell")
        @Expose
        private String mCrossSell;

        @SerializedName("prodcutbrought")
        @Expose
        private String mProductBrought;

        @SerializedName("productnote")
        @Expose
        private String mProductNote;

        @SerializedName("attr_type")
        @Expose
        private String mAttrType;

        @SerializedName("attr_name")
        @Expose
        private String mAttrName;

        @SerializedName("upsell")
        @Expose
        private String mUpSell;

        @SerializedName("stockstatus")
        @Expose
        private String mStockStatus;

        @SerializedName("price")
        @Expose
        private String mPrice;

        @SerializedName("saleprice")
        @Expose
        private String mSalePrice;

        @SerializedName("taxdata")
        @Expose
        private String mTaxData;

        @SerializedName("productqty")
        @Expose
        private String mProductQty;

        @SerializedName("productsku")
        @Expose
        private String mProductSku;

        @SerializedName("productstatus")
        @Expose
        private String mProductStatus;

        @SerializedName("product_categories")
        @Expose
        private String mProductCategories;

        @SerializedName("productimg")
        @Expose
        private String mProductImg;

        @SerializedName("productgallery")
        @Expose
        private String mProductGallery;

        @SerializedName("dimension")
        @Expose
        private String mDimention;

        @SerializedName("productvideo")
        @Expose
        private String mProductVideo;

        @SerializedName("productweight")
        @Expose
        private String mProductWeight;

        @SerializedName("pagetags")
        @Expose
        private String mPageTags;

        @SerializedName("productuser")
        @Expose
        private String mProductUser;

        @SerializedName("productdate")
        @Expose
        private String mProductDate;

        @SerializedName("productusermodify")
        @Expose
        private String mProductUserModify;

        @SerializedName("productdatemodify")
        @Expose
        private String mProductDateModify;

        public String getmAvgRating() {
            return mAvgRating;
        }

        public void setmAvgRating(String mAvgRating) {
            this.mAvgRating = mAvgRating;
        }

        public String getmProductId() {
            return mProductId;
        }

        public void setmProductId(String mProductId) {
            this.mProductId = mProductId;
        }

        public String getmProductName() {
            return mProductName;
        }

        public void setmProductName(String mProductName) {
            this.mProductName = mProductName;
        }

        public String getmProductSlug() {
            return mProductSlug;
        }

        public void setmProductSlug(String mProductSlug) {
            this.mProductSlug = mProductSlug;
        }

        public String getmProductDetails() {
            return mProductDetails;
        }

        public void setmProductDetails(String mProductDetails) {
            this.mProductDetails = mProductDetails;
        }

        public String getmProductShortDetails() {
            return mProductShortDetails;
        }

        public void setmProductShortDetails(String mProductShortDetails) {
            this.mProductShortDetails = mProductShortDetails;
        }

        public String getmProductShipPolicy() {
            return mProductShipPolicy;
        }

        public void setmProductShipPolicy(String mProductShipPolicy) {
            this.mProductShipPolicy = mProductShipPolicy;
        }

        public String getmRefundPolicy() {
            return mRefundPolicy;
        }

        public void setmRefundPolicy(String mRefundPolicy) {
            this.mRefundPolicy = mRefundPolicy;
        }

        public String getmProductCancelPolicy() {
            return mProductCancelPolicy;
        }

        public void setmProductCancelPolicy(String mProductCancelPolicy) {
            this.mProductCancelPolicy = mProductCancelPolicy;
        }

        public String getmCrossSell() {
            return mCrossSell;
        }

        public void setmCrossSell(String mCrossSell) {
            this.mCrossSell = mCrossSell;
        }

        public String getmProductBrought() {
            return mProductBrought;
        }

        public void setmProductBrought(String mProductBrought) {
            this.mProductBrought = mProductBrought;
        }

        public String getmProductNote() {
            return mProductNote;
        }

        public void setmProductNote(String mProductNote) {
            this.mProductNote = mProductNote;
        }

        public String getmAttrType() {
            return mAttrType;
        }

        public void setmAttrType(String mAttrType) {
            this.mAttrType = mAttrType;
        }

        public String getmAttrName() {
            return mAttrName;
        }

        public void setmAttrName(String mAttrName) {
            this.mAttrName = mAttrName;
        }

        public String getmUpSell() {
            return mUpSell;
        }

        public void setmUpSell(String mUpSell) {
            this.mUpSell = mUpSell;
        }

        public String getmStockStatus() {
            return mStockStatus;
        }

        public void setmStockStatus(String mStockStatus) {
            this.mStockStatus = mStockStatus;
        }

        public String getmPrice() {
            return mPrice;
        }

        public void setmPrice(String mPrice) {
            this.mPrice = mPrice;
        }

        public String getmSalePrice() {
            return mSalePrice;
        }

        public void setmSalePrice(String mSalePrice) {
            this.mSalePrice = mSalePrice;
        }

        public String getmTaxData() {
            return mTaxData;
        }

        public void setmTaxData(String mTaxData) {
            this.mTaxData = mTaxData;
        }

        public String getmProductQty() {
            return mProductQty;
        }

        public void setmProductQty(String mProductQty) {
            this.mProductQty = mProductQty;
        }

        public String getmProductSku() {
            return mProductSku;
        }

        public void setmProductSku(String mProductSku) {
            this.mProductSku = mProductSku;
        }

        public String getmProductStatus() {
            return mProductStatus;
        }

        public void setmProductStatus(String mProductStatus) {
            this.mProductStatus = mProductStatus;
        }

        public String getmProductCategories() {
            return mProductCategories;
        }

        public void setmProductCategories(String mProductCategories) {
            this.mProductCategories = mProductCategories;
        }

        public String getmProductImg() {
            return mProductImg;
        }

        public void setmProductImg(String mProductImg) {
            this.mProductImg = mProductImg;
        }

        public String getmProductGallery() {
            return mProductGallery;
        }

        public void setmProductGallery(String mProductGallery) {
            this.mProductGallery = mProductGallery;
        }

        public String getmDimention() {
            return mDimention;
        }

        public void setmDimention(String mDimention) {
            this.mDimention = mDimention;
        }

        public String getmProductVideo() {
            return mProductVideo;
        }

        public void setmProductVideo(String mProductVideo) {
            this.mProductVideo = mProductVideo;
        }

        public String getmProductWeight() {
            return mProductWeight;
        }

        public void setmProductWeight(String mProductWeight) {
            this.mProductWeight = mProductWeight;
        }

        public String getmPageTags() {
            return mPageTags;
        }

        public void setmPageTags(String mPageTags) {
            this.mPageTags = mPageTags;
        }

        public String getmProductUser() {
            return mProductUser;
        }

        public void setmProductUser(String mProductUser) {
            this.mProductUser = mProductUser;
        }

        public String getmProductDate() {
            return mProductDate;
        }

        public void setmProductDate(String mProductDate) {
            this.mProductDate = mProductDate;
        }

        public String getmProductUserModify() {
            return mProductUserModify;
        }

        public void setmProductUserModify(String mProductUserModify) {
            this.mProductUserModify = mProductUserModify;
        }

        public String getmProductDateModify() {
            return mProductDateModify;
        }

        public void setmProductDateModify(String mProductDateModify) {
            this.mProductDateModify = mProductDateModify;
        }

        public String getmWishList() {
            return mWishList;
        }

        public void setmWishList(String mWishList) {
            this.mWishList = mWishList;
        }
    }
}
