package com.ws.design.coco_ecommerce_ui_kit.categories;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.ws.design.coco_ecommerce_ui_kit.home.home_response.Banner;
import com.ws.design.coco_ecommerce_ui_kit.home.home_response.BestOfFassion;
import com.ws.design.coco_ecommerce_ui_kit.home.home_response.BestSeller;
import com.ws.design.coco_ecommerce_ui_kit.home.home_response.BudgetBuy;
import com.ws.design.coco_ecommerce_ui_kit.home.home_response.Categories;
import com.ws.design.coco_ecommerce_ui_kit.home.home_response.Clothing;
import com.ws.design.coco_ecommerce_ui_kit.home.home_response.DealProducts;
import com.ws.design.coco_ecommerce_ui_kit.home.home_response.Electronics;
import com.ws.design.coco_ecommerce_ui_kit.home.home_response.FassionAccesory;
import com.ws.design.coco_ecommerce_ui_kit.home.home_response.Featured;
import com.ws.design.coco_ecommerce_ui_kit.home.home_response.ProductData;
import com.ws.design.coco_ecommerce_ui_kit.home.home_response.SpecialDeal;
import com.ws.design.coco_ecommerce_ui_kit.home.home_response.SpecialOffer;
import com.ws.design.coco_ecommerce_ui_kit.home.home_response.Watches;

import java.util.ArrayList;

public class CategoriesResponse {

    @SerializedName("status")
    @Expose
    private String mStatus;

    @SerializedName("data")
    @Expose
    private CategoriesData mData;

    public String getmStatus() {
        return mStatus;
    }

    public void setmStatus(String mStatus) {
        this.mStatus = mStatus;
    }

    public CategoriesData getmData() {
        return mData;
    }

    public void setmData(CategoriesData mData) {
        this.mData = mData;
    }

    public class CategoriesData {

        @SerializedName("main_cats")
        @Expose
        private ArrayList<MainCategoriesData> mMainCategories;

        public ArrayList<MainCategoriesData> getmMainCategories() {
            return mMainCategories;
        }

        public void setmMainCategories(ArrayList<MainCategoriesData> mMainCategories) {
            this.mMainCategories = mMainCategories;
        }
    }

    public class MainCategoriesData {

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

        @SerializedName("sub_categories")
        @Expose
        private ArrayList<Categories> mSubCategories;

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

        public ArrayList<Categories> getmSubCategories() {
            return mSubCategories;
        }

        public void setmSubCategories(ArrayList<Categories> mSubCategories) {
            this.mSubCategories = mSubCategories;
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