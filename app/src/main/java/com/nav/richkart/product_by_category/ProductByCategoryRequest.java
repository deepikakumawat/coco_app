package com.nav.richkart.product_by_category;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductByCategoryRequest {

    @SerializedName("fattributes")
    @Expose
    private String[] mFAttributes;

    @SerializedName("cat_id")
    @Expose
    private String cateId;

    @SerializedName("min_price")
    @Expose
    private String minValue;

    @SerializedName("offset")
    @Expose
    private int offset;

    @SerializedName("max_price")
    @Expose
    private String maxValue;

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public String getMinValue() {
        return minValue;
    }

    public void setMinValue(String minValue) {
        this.minValue = minValue;
    }

    public String getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(String maxValue) {
        this.maxValue = maxValue;
    }

    public String[] getmFAttributes() {
        return mFAttributes;
    }

    public void setmFAttributes(String[] mFAttributes) {
        this.mFAttributes = mFAttributes;
    }

    public String getCateId() {
        return cateId;
    }

    public void setCateId(String cateId) {
        this.cateId = cateId;
    }
}
