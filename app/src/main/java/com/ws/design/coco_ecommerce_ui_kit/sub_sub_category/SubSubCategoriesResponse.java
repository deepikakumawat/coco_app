package com.ws.design.coco_ecommerce_ui_kit.sub_sub_category;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.ws.design.coco_ecommerce_ui_kit.departments.CategoriesResponse;
import com.ws.design.coco_ecommerce_ui_kit.home.home_response.Banner;
import com.ws.design.coco_ecommerce_ui_kit.product_by_category.ProductByCategoryResponse;
import com.ws.design.coco_ecommerce_ui_kit.product_details.project_details_response.ProductDetailsSimilier;

import java.util.ArrayList;

public class SubSubCategoriesResponse {

    @SerializedName("status")
    @Expose
    private String mStatus;

    @SerializedName("data")
    @Expose
    private Data mData;

    public String getmStatus() {
        return mStatus;
    }

    public void setmStatus(String mStatus) {
        this.mStatus = mStatus;
    }

    public Data getmData() {
        return mData;
    }

    public void setmData(Data mData) {
        this.mData = mData;
    }

    public class Data {

        @SerializedName("sub_cats")
        @Expose
        private ArrayList<MainSubCategoriesData> mMainSubCategories;

        @SerializedName("products")
        @Expose
        private ArrayList<ProductDetailsSimilier> mProduct;

        @SerializedName("attributedata")
        @Expose
        private ArrayList<ProductByCategoryResponse.ProductAttribueData> mProductAttributeData;

        @SerializedName("nextoffset")
        @Expose
        private int mNextOffset;

        @SerializedName("total_products")
        @Expose
        private int mTotalProducts;

        @SerializedName("banners")
        @Expose
        private ArrayList<Banner> mBanner;

        public ArrayList<MainSubCategoriesData> getmMainSubCategories() {
            return mMainSubCategories;
        }

        public void setmMainSubCategories(ArrayList<MainSubCategoriesData> mMainSubCategories) {
            this.mMainSubCategories = mMainSubCategories;
        }

        public ArrayList<ProductDetailsSimilier> getmProduct() {
            return mProduct;
        }

        public void setmProduct(ArrayList<ProductDetailsSimilier> mProduct) {
            this.mProduct = mProduct;
        }

        public ArrayList<ProductByCategoryResponse.ProductAttribueData> getmProductAttributeData() {
            return mProductAttributeData;
        }

        public void setmProductAttributeData(ArrayList<ProductByCategoryResponse.ProductAttribueData> mProductAttributeData) {
            this.mProductAttributeData = mProductAttributeData;
        }

        public int getmNextOffset() {
            return mNextOffset;
        }

        public void setmNextOffset(int mNextOffset) {
            this.mNextOffset = mNextOffset;
        }

        public int getmTotalProducts() {
            return mTotalProducts;
        }

        public void setmTotalProducts(int mTotalProducts) {
            this.mTotalProducts = mTotalProducts;
        }

        public ArrayList<Banner> getmBanner() {
            return mBanner;
        }

        public void setmBanner(ArrayList<Banner> mBanner) {
            this.mBanner = mBanner;
        }
    }

    public class MainSubCategoriesData {

        @SerializedName("cat_id")
        @Expose
        private String mCatId;

        @SerializedName("cat_name")
        @Expose
        private String mCatName;

        @SerializedName("parent_id")
        @Expose
        private String mParentId;

        @SerializedName("cat_slug")
        @Expose
        private String mCatSlug;

        @SerializedName("cat_order")
        @Expose
        private String mCatOrder;

        @SerializedName("cat_imgid")
        @Expose
        private String mCatImgId;

        @SerializedName("cate_iconimg")
        @Expose
        private String mCatIconImg;

        @SerializedName("category_lavel")
        @Expose
        private String mCategoryLavel;

        @SerializedName("status")
        @Expose
        private String mStatus;

        @SerializedName("createdby")
        @Expose
        private String mCreatedBy;

        @SerializedName("createddate")
        @Expose
        private String mCreatedDate;

        @SerializedName("modifiedby")
        @Expose
        private String mModifiedBy;

        @SerializedName("modifieddate")
        @Expose
        private String mModifiedDate;

        @SerializedName("sub_sub_categories")
        @Expose
        private ArrayList<CategoriesResponse.SubCategoriesData> mSubSubCategories;

        public String getmCatId() {
            return mCatId;
        }

        public void setmCatId(String mCatId) {
            this.mCatId = mCatId;
        }

        public String getmCatName() {
            return mCatName;
        }

        public void setmCatName(String mCatName) {
            this.mCatName = mCatName;
        }

        public String getmParentId() {
            return mParentId;
        }

        public void setmParentId(String mParentId) {
            this.mParentId = mParentId;
        }

        public String getmCatSlug() {
            return mCatSlug;
        }

        public void setmCatSlug(String mCatSlug) {
            this.mCatSlug = mCatSlug;
        }

        public String getmStatus() {
            return mStatus;
        }

        public void setmStatus(String mStatus) {
            this.mStatus = mStatus;
        }

        public String getmCreatedBy() {
            return mCreatedBy;
        }

        public void setmCreatedBy(String mCreatedBy) {
            this.mCreatedBy = mCreatedBy;
        }

        public String getmCreatedDate() {
            return mCreatedDate;
        }

        public void setmCreatedDate(String mCreatedDate) {
            this.mCreatedDate = mCreatedDate;
        }

        public String getmModifiedBy() {
            return mModifiedBy;
        }

        public void setmModifiedBy(String mModifiedBy) {
            this.mModifiedBy = mModifiedBy;
        }

        public String getmModifiedDate() {
            return mModifiedDate;
        }

        public void setmModifiedDate(String mModifiedDate) {
            this.mModifiedDate = mModifiedDate;
        }

        public ArrayList<CategoriesResponse.SubCategoriesData> getmSubSubCategories() {
            return mSubSubCategories;
        }

        public void setmSubSubCategories(ArrayList<CategoriesResponse.SubCategoriesData> mSubSubCategories) {
            this.mSubSubCategories = mSubSubCategories;
        }

        public String getmCatImgId() {
            return mCatImgId;
        }

        public void setmCatImgId(String mCatImgId) {
            this.mCatImgId = mCatImgId;
        }

        public String getmCatIconImg() {
            return mCatIconImg;
        }

        public void setmCatIconImg(String mCatIconImg) {
            this.mCatIconImg = mCatIconImg;
        }

        public String getmCategoryLavel() {
            return mCategoryLavel;
        }

        public void setmCategoryLavel(String mCategoryLavel) {
            this.mCategoryLavel = mCategoryLavel;
        }

        public String getmCatOrder() {
            return mCatOrder;
        }

        public void setmCatOrder(String mCatOrder) {
            this.mCatOrder = mCatOrder;
        }
    }


}
