package com.nav.richkart.home.home_response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Categories {

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
    private String mCatIconImgId;

    @SerializedName("category_lavel")
    @Expose
    private String mCategoryLevel;

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

    public String getmCatOrder() {
        return mCatOrder;
    }

    public void setmCatOrder(String mCatOrder) {
        this.mCatOrder = mCatOrder;
    }

    public String getmCatImgId() {
        return mCatImgId;
    }

    public void setmCatImgId(String mCatImgId) {
        this.mCatImgId = mCatImgId;
    }

    public String getmCatIconImgId() {
        return mCatIconImgId;
    }

    public void setmCatIconImgId(String mCatIconImgId) {
        this.mCatIconImgId = mCatIconImgId;
    }

    public String getmCategoryLevel() {
        return mCategoryLevel;
    }

    public void setmCategoryLevel(String mCategoryLevel) {
        this.mCategoryLevel = mCategoryLevel;
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
}
