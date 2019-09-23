package com.nav.richkart.deals.seller;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.nav.richkart.product_details.project_details_response.ProductDetailsSimilier;

import java.util.ArrayList;

public class DealsResponse {

    @SerializedName("status")
    @Expose
    private String mStatus;

    @SerializedName("data")
    @Expose
    private ArrayList<ProductDetailsSimilier> mSellerProducts;

    public String getmStatus() {
        return mStatus;
    }

    public void setmStatus(String mStatus) {
        this.mStatus = mStatus;
    }

    public ArrayList<ProductDetailsSimilier> getmSellerProducts() {
        return mSellerProducts;
    }

    public void setmSellerProducts(ArrayList<ProductDetailsSimilier> mSellerProducts) {
        this.mSellerProducts = mSellerProducts;
    }

}
