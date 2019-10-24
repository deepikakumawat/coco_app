package com.richkart.android.search;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.richkart.android.product_details.project_details_response.ProductDetailsSimilier;

import java.util.ArrayList;

public class SearchResponse {

    @SerializedName("status")
    @Expose
    private String mStatus;

    @SerializedName("message")
    @Expose
    private String mMessage;


    @SerializedName("data")
    @Expose
    private SearchData mSearchData;

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

    public SearchData getmSearchData() {
        return mSearchData;
    }

    public void setmSearchData(SearchData mSearchData) {
        this.mSearchData = mSearchData;
    }

    protected class SearchData {

        @SerializedName("brudrumdata")
        @Expose
        private String mBrudrumdata;


        @SerializedName("productsdata")
        @Expose
        private ArrayList<ProductDetailsSimilier> mProducts;

        public String getmBrudrumdata() {
            return mBrudrumdata;
        }

        public void setmBrudrumdata(String mBrudrumdata) {
            this.mBrudrumdata = mBrudrumdata;
        }

        public ArrayList<ProductDetailsSimilier> getmProducts() {
            return mProducts;
        }

        public void setmProducts(ArrayList<ProductDetailsSimilier> mProducts) {
            this.mProducts = mProducts;
        }
    }
}
