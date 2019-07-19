package com.ws.design.coco_ecommerce_ui_kit.product_details;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.ws.design.coco_ecommerce_ui_kit.my_cart.CartListResponse;

import java.util.ArrayList;

public class AddToCartResponse {

    @SerializedName("status")
    @Expose
    private String mStatus;

    @SerializedName("data")
    @Expose
    private CartData mData;


    @SerializedName("message")
    @Expose
    private String mMessage;

    public String getmStatus() {
        return mStatus;
    }

    public void setmStatus(String mStatus) {
        this.mStatus = mStatus;
    }



    public String getmMessage() {
        return mMessage;
    }

    public void setmMessage(String mMessage) {
        this.mMessage = mMessage;
    }

    public CartData getmData() {
        return mData;
    }

    public void setmData(CartData mData) {
        this.mData = mData;
    }

    public class CartData{

        @SerializedName("total_price")
        @Expose
        private String mTotalPrice;


        @SerializedName("products")
        @Expose
        private ArrayList<CartListResponse.ProductData> mProductData;

        public String getmTotalPrice() {
            return mTotalPrice;
        }

        public void setmTotalPrice(String mTotalPrice) {
            this.mTotalPrice = mTotalPrice;
        }

        public ArrayList<CartListResponse.ProductData> getmProductData() {
            return mProductData;
        }

        public void setmProductData(ArrayList<CartListResponse.ProductData> mProductData) {
            this.mProductData = mProductData;
        }
    }
}
