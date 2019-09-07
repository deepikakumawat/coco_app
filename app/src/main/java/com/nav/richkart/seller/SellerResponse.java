package com.nav.richkart.seller;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.nav.richkart.product_details.project_details_response.ProductDetailsSimilier;

import java.util.ArrayList;

public class SellerResponse {

    @SerializedName("status")
    @Expose
    private String mStatus;

    @SerializedName("data")
    @Expose
    private SellerData mData;

    public String getmStatus() {
        return mStatus;
    }

    public void setmStatus(String mStatus) {
        this.mStatus = mStatus;
    }

    public SellerData getmData() {
        return mData;
    }

    public void setmData(SellerData mData) {
        this.mData = mData;
    }

    public class SellerData {



        @SerializedName("products")
        @Expose
        private ArrayList<ProductDetailsSimilier> mSellerProducts;


        @SerializedName("users")
        @Expose
        private UserData mUsers;


        public ArrayList<ProductDetailsSimilier> getmSellerProducts() {
            return mSellerProducts;
        }

        public void setmSellerProducts(ArrayList<ProductDetailsSimilier> mSellerProducts) {
            this.mSellerProducts = mSellerProducts;
        }

        public UserData getmUsers() {
            return mUsers;
        }

        public void setmUsers(UserData mUsers) {
            this.mUsers = mUsers;
        }
    }
}
