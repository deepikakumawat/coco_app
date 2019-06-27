package com.ws.design.coco_ecommerce_ui_kit.my_order;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MyOrderResponse {

    @SerializedName("status")
    @Expose
    private String mStatus;


    @SerializedName("data")
    @Expose
    private ArrayList<MyOrderData> mAddressData;

    public String getmStatus() {
        return mStatus;
    }

    public void setmStatus(String mStatus) {
        this.mStatus = mStatus;
    }

    public ArrayList<MyOrderData> getmAddressData() {
        return mAddressData;
    }

    public void setmAddressData(ArrayList<MyOrderData> mAddressData) {
        this.mAddressData = mAddressData;
    }

    public class MyOrderData {

        @SerializedName("itemid")
        @Expose
        private String mItemId;

        @SerializedName("orderid")
        @Expose
        private String mOrderId;

        @SerializedName("productname")
        @Expose
        private String mProductName;

        @SerializedName("createddate")
        @Expose
        private String mCreatedDate;

        @SerializedName("name")
        @Expose
        private String mName;

        @SerializedName("status")
        @Expose
        private String mStatus;

        @SerializedName("amount")
        @Expose
        private String mAmount;

        public String getmItemId() {
            return mItemId;
        }

        public void setmItemId(String mItemId) {
            this.mItemId = mItemId;
        }

        public String getmOrderId() {
            return mOrderId;
        }

        public void setmOrderId(String mOrderId) {
            this.mOrderId = mOrderId;
        }

        public String getmProductName() {
            return mProductName;
        }

        public void setmProductName(String mProductName) {
            this.mProductName = mProductName;
        }

        public String getmCreatedDate() {
            return mCreatedDate;
        }

        public void setmCreatedDate(String mCreatedDate) {
            this.mCreatedDate = mCreatedDate;
        }

        public String getmName() {
            return mName;
        }

        public void setmName(String mName) {
            this.mName = mName;
        }

        public String getmStatus() {
            return mStatus;
        }

        public void setmStatus(String mStatus) {
            this.mStatus = mStatus;
        }

        public String getmAmount() {
            return mAmount;
        }

        public void setmAmount(String mAmount) {
            this.mAmount = mAmount;
        }
    }
}
