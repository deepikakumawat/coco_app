package com.ws.design.coco_ecommerce_ui_kit.product_by_category;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.ws.design.coco_ecommerce_ui_kit.home.home_response.ProductData;
import com.ws.design.coco_ecommerce_ui_kit.my_cart.CartListResponse;
import com.ws.design.coco_ecommerce_ui_kit.product_details.project_details_response.ProductBroughtData;
import com.ws.design.coco_ecommerce_ui_kit.product_details.project_details_response.ProductDetailsResponse;
import com.ws.design.coco_ecommerce_ui_kit.product_details.project_details_response.ProductDetailsSimilier;

import java.io.Serializable;
import java.util.ArrayList;

public class ProductByCategoryResponse implements Serializable {

    @SerializedName("status")
    @Expose
    private String mStatus;

    @SerializedName("data")
    @Expose
    private ProductByCategoryData mData;

    public String getmStatus() {
        return mStatus;
    }

    public void setmStatus(String mStatus) {
        this.mStatus = mStatus;
    }

    public ProductByCategoryData getmData() {
        return mData;
    }

    public void setmData(ProductByCategoryData mData) {
        this.mData = mData;
    }

    public class ProductByCategoryData implements Serializable{


        @SerializedName("products")
        @Expose
        private ArrayList<ProductData> mProduct;


        @SerializedName("attributedata")
        @Expose
        private ArrayList<ProductAttribueData> mProductAttributeData;

        public ArrayList<ProductData> getmProduct() {
            return mProduct;
        }

        public void setmProduct(ArrayList<ProductData> mProduct) {
            this.mProduct = mProduct;
        }

        public ArrayList<ProductAttribueData> getmProductAttributeData() {
            return mProductAttributeData;
        }

        public void setmProductAttributeData(ArrayList<ProductAttribueData> mProductAttributeData) {
            this.mProductAttributeData = mProductAttributeData;
        }
    }

    public class ProductAttribueData implements  Serializable {

        @SerializedName("type")
        @Expose
        private String mType;

        @SerializedName("attributes")
        @Expose
        private ArrayList<Attribtues> mAttributes;

        public String getmType() {
            return mType;
        }

        public void setmType(String mType) {
            this.mType = mType;
        }

        public ArrayList<Attribtues> getmAttributes() {
            return mAttributes;
        }

        public void setmAttributes(ArrayList<Attribtues> mAttributes) {
            this.mAttributes = mAttributes;
        }
    }

    public class Attribtues implements Serializable{

        @SerializedName("attribute_id")
        @Expose
        private String mAttributeId;

        @SerializedName("attribute_type")
        @Expose
        private String mAttributeType;

        @SerializedName("attribute_name")
        @Expose
        private String mAttributeName;

        @SerializedName("attributereleteddata")
        @Expose
        private String mAttributeRelatedData;

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

        public String getmAttributeRelatedData() {
            return mAttributeRelatedData;
        }

        public void setmAttributeRelatedData(String mAttributeRelatedData) {
            this.mAttributeRelatedData = mAttributeRelatedData;
        }
    }
}
