package com.nav.richkart.my_cart;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class CartListResponse implements Serializable {

    @SerializedName("status")
    @Expose
    private String mStatus;


    @SerializedName("data")
    @Expose
    private CartListData mCartlistData;

    public String getmStatus() {
        return mStatus;
    }

    public void setmStatus(String mStatus) {
        this.mStatus = mStatus;
    }

    public CartListData getmCartlistData() {
        return mCartlistData;
    }

    public void setmCartlistData(CartListData mCartlistData) {
        this.mCartlistData = mCartlistData;
    }


    public class CartListData implements Serializable {

        @SerializedName("brudrumdata")
        @Expose
        private String mBrudrumdata;

        @SerializedName("total_price")
        @Expose
        private String mTotalPrice;


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

        public String getmTotalPrice() {
            return mTotalPrice;
        }

        public void setmTotalPrice(String mTotalPrice) {
            this.mTotalPrice = mTotalPrice;
        }
    }

    public class ProductData implements Serializable {


        @SerializedName("cart_id")
        @Expose
        private String mCartId;

        @SerializedName("attributes")
        @Expose
        private ArrayList<AttributesData> mAttributes;


        @SerializedName("productid")
        @Expose
        private String mProductId;

        @SerializedName("productname")
        @Expose
        private String mProductName;


        @SerializedName("price")
        @Expose
        private String mPrice;

        @SerializedName("saleprice")
        @Expose
        private String mSalePrice;

        @SerializedName("quantity")
        @Expose
        private String mQuantity;

        @SerializedName("productslug")
        @Expose
        private String mProductSlug;

        @SerializedName("productimg")
        @Expose
        private String mProductImg;

        @SerializedName("prodcutbrought")
        @Expose
        private String mProductBrought;

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

        public String getmQuantity() {
            return mQuantity;
        }

        public void setmQuantity(String mQuantity) {
            this.mQuantity = mQuantity;
        }

        public String getmProductSlug() {
            return mProductSlug;
        }

        public void setmProductSlug(String mProductSlug) {
            this.mProductSlug = mProductSlug;
        }

        public String getmProductImg() {
            return mProductImg;
        }

        public void setmProductImg(String mProductImg) {
            this.mProductImg = mProductImg;
        }

        public String getmProductBrought() {
            return mProductBrought;
        }

        public void setmProductBrought(String mProductBrought) {
            this.mProductBrought = mProductBrought;
        }

        public String getmCartId() {
            return mCartId;
        }

        public void setmCartId(String mCartId) {
            this.mCartId = mCartId;
        }

        public ArrayList<AttributesData> getmAttributes() {
            return mAttributes;
        }

        public void setmAttributes(ArrayList<AttributesData> mAttributes) {
            this.mAttributes = mAttributes;
        }
    }

    public class AttributesData implements Serializable {

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
