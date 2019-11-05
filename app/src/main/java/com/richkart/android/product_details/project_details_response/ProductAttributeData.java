package com.richkart.android.product_details.project_details_response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductAttributeData {

    @SerializedName("attribute_id")
    @Expose
    private String mAttributeId;

    @SerializedName("attribute_type")
    @Expose
    private String mAttributeType;

    @SerializedName("attribute_name")
    @Expose
    private String mAttributeName;

    @SerializedName("status")
    @Expose
    private String mStatus;

    @SerializedName("attributereleteddata")
    @Expose
    private String mAttributeRelatedData;

    @SerializedName("createdby")
    @Expose
    private String mCreatedBy;

    @SerializedName("createddate")
    @Expose
    private String mCreatedData;

    @SerializedName("modifiedby")
    @Expose
    private String mModifiedBy;

    @SerializedName("modifieddate")
    @Expose
    private String mModifiedData;

    @SerializedName("attr_type")
    @Expose
    private String mAttrType;

    @SerializedName("productimg")
    @Expose
    private String mProductImg;

    public String getmProductImg() {
        return mProductImg;
    }


    public String getmAttributeId() {
        return mAttributeId;
    }

    public void setmAttributeId(String mAttributeId) {
        this.mAttributeId = mAttributeId;
    }

    public String getmAttributeType() {
        return mAttributeType;
    }

    public void setmAttributeType(String mAttributeType) {
        this.mAttributeType = mAttributeType;
    }

    public String getmAttributeName() {
        return mAttributeName;
    }

    public void setmAttributeName(String mAttributeName) {
        this.mAttributeName = mAttributeName;
    }

    public String getmStatus() {
        return mStatus;
    }

    public void setmStatus(String mStatus) {
        this.mStatus = mStatus;
    }

    public String getmAttributeRelatedData() {
        return mAttributeRelatedData;
    }

    public void setmAttributeRelatedData(String mAttributeRelatedData) {
        this.mAttributeRelatedData = mAttributeRelatedData;
    }

    public String getmCreatedBy() {
        return mCreatedBy;
    }

    public void setmCreatedBy(String mCreatedBy) {
        this.mCreatedBy = mCreatedBy;
    }

    public String getmCreatedData() {
        return mCreatedData;
    }

    public void setmCreatedData(String mCreatedData) {
        this.mCreatedData = mCreatedData;
    }

    public String getmModifiedBy() {
        return mModifiedBy;
    }

    public void setmModifiedBy(String mModifiedBy) {
        this.mModifiedBy = mModifiedBy;
    }

    public String getmModifiedData() {
        return mModifiedData;
    }

    public void setmModifiedData(String mModifiedData) {
        this.mModifiedData = mModifiedData;
    }

    public String getmAttrType() {
        return mAttrType;
    }

    public void setmAttrType(String mAttrType) {
        this.mAttrType = mAttrType;
    }
}
