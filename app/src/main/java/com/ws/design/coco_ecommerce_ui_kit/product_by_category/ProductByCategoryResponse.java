package com.ws.design.coco_ecommerce_ui_kit.product_by_category;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.ws.design.coco_ecommerce_ui_kit.home.home_response.ProductData;
import com.ws.design.coco_ecommerce_ui_kit.my_cart.CartListResponse;
import com.ws.design.coco_ecommerce_ui_kit.product_details.project_details_response.ProductBroughtData;
import com.ws.design.coco_ecommerce_ui_kit.product_details.project_details_response.ProductDetailsResponse;
import com.ws.design.coco_ecommerce_ui_kit.product_details.project_details_response.ProductDetailsSimilier;

import java.util.ArrayList;

public class ProductByCategoryResponse {

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

    public class ProductByCategoryData {





        @SerializedName("products")
        @Expose
        private ArrayList<ProductData> mProduct;



        @SerializedName("attributedata")
        @Expose
        private ArrayList<ProductBroughtData> mProductBroughtData;

        public ArrayList<ProductData> getmProduct() {
            return mProduct;
        }

        public void setmProduct(ArrayList<ProductData> mProduct) {
            this.mProduct = mProduct;
        }

        public ArrayList<ProductBroughtData> getmProductBroughtData() {
            return mProductBroughtData;
        }

        public void setmProductBroughtData(ArrayList<ProductBroughtData> mProductBroughtData) {
            this.mProductBroughtData = mProductBroughtData;
        }
    }
}
