package com.ws.design.coco_ecommerce_ui_kit.product_details;

import com.google.gson.JsonObject;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.ws.design.coco_ecommerce_ui_kit.my_cart.CartListResponse;

import org.json.JSONObject;

import java.util.ArrayList;

public class ProductByCategoryRequest {

    @SerializedName("fattributes")
    @Expose
    private String[] mFAttributes;

    @SerializedName("cat_id")
    @Expose
    private String cateId;

  /*  @SerializedName("min_price")
    @Expose
    private int minValue;

    @SerializedName("max_price")
    @Expose
    private int maxValue;*/

   /* public int getMinValue() {
        return minValue;
    }

    public void setMinValue(int minValue) {
        this.minValue = minValue;
    }

    public int getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }*/

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
