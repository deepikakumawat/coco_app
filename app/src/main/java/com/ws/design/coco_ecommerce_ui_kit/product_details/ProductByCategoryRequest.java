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
    private JSONObject jsonObject;

    @SerializedName("cat_id")
    @Expose
    private String cateId;

    public JSONObject getJsonObject() {
        return jsonObject;
    }

    public void setJsonObject(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    public String getCateId() {
        return cateId;
    }

    public void setCateId(String cateId) {
        this.cateId = cateId;
    }
}
