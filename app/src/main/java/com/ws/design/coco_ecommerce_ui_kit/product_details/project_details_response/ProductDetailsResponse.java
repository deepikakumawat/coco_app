package com.ws.design.coco_ecommerce_ui_kit.product_details.project_details_response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ProductDetailsResponse {

    @SerializedName("status")
    @Expose
    private String mStatus;

    @SerializedName("data")
    @Expose
    private ProductData mData;

    public String getmStatus() {
        return mStatus;
    }

    public void setmStatus(String mStatus) {
        this.mStatus = mStatus;
    }

    public ProductData getmData() {
        return mData;
    }

    public void setmData(ProductData mData) {
        this.mData = mData;
    }

    public class ProductData {

        @SerializedName("title")
        @Expose
        private String mTitle;

        @SerializedName("productsdatasimiler")
        @Expose
        private ArrayList<ProductDetailsSimilier> mProductDetailsSimilier;

        @SerializedName("brudrumdata")
        @Expose
        private String mBrudrumdata;

        @SerializedName("product")
        @Expose
        private ArrayList<ProductDetailsSimilier> mProduct;

        @SerializedName("cate_id")
        @Expose
        private ArrayList<String> mCatId;

        @SerializedName("productsbroughtdata")
        @Expose
        private ArrayList<ProductBroughtData> mProductBroughtData;


        public String getmTitle() {
            return mTitle;
        }

        public void setmTitle(String mTitle) {
            this.mTitle = mTitle;
        }

        public ArrayList<ProductDetailsSimilier> getmProductDetailsSimilier() {
            return mProductDetailsSimilier;
        }

        public void setmProductDetailsSimilier(ArrayList<ProductDetailsSimilier> mProductDetailsSimilier) {
            this.mProductDetailsSimilier = mProductDetailsSimilier;
        }

        public String getmBrudrumdata() {
            return mBrudrumdata;
        }

        public void setmBrudrumdata(String mBrudrumdata) {
            this.mBrudrumdata = mBrudrumdata;
        }

        public ArrayList<ProductDetailsSimilier> getmProduct() {
            return mProduct;
        }

        public void setmProduct(ArrayList<ProductDetailsSimilier> mProduct) {
            this.mProduct = mProduct;
        }



        public ArrayList<ProductBroughtData> getmProductBroughtData() {
            return mProductBroughtData;
        }

        public void setmProductBroughtData(ArrayList<ProductBroughtData> mProductBroughtData) {
            this.mProductBroughtData = mProductBroughtData;
        }


        public ArrayList<String> getmCatId() {
            return mCatId;
        }

        public void setmCatId(ArrayList<String> mCatId) {
            this.mCatId = mCatId;
        }
    }
}
