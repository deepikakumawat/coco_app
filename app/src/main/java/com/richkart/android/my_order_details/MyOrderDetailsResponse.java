package com.richkart.android.my_order_details;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MyOrderDetailsResponse {

    @SerializedName("status")
    @Expose
    private String mStatus;

    @SerializedName("data")
    @Expose
    private OrderDetailsReponse mOrderDetailsData;

    public String getmStatus() {
        return mStatus;
    }

    public void setmStatus(String mStatus) {
        this.mStatus = mStatus;
    }

    public OrderDetailsReponse getmOrderDetailsData() {
        return mOrderDetailsData;
    }

    public void setmOrderDetailsData(OrderDetailsReponse mOrderDetailsData) {
        this.mOrderDetailsData = mOrderDetailsData;
    }

    public class OrderDetailsReponse {

        @SerializedName("orderdetail")
        @Expose
        private OrderDetailsData mOrderDetail;

        @SerializedName("product")
        @Expose
        private ArrayList<OrderProduct> mProduct;

        public OrderDetailsData getmOrderDetail() {
            return mOrderDetail;
        }

        public void setmOrderDetail(OrderDetailsData mOrderDetail) {
            this.mOrderDetail = mOrderDetail;
        }

        public ArrayList<OrderProduct> getmProduct() {
            return mProduct;
        }

        public void setmProduct(ArrayList<OrderProduct> mProduct) {
            this.mProduct = mProduct;
        }
    }
}
