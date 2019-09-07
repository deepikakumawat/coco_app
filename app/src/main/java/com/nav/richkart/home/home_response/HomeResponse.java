package com.nav.richkart.home.home_response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class HomeResponse {

    @SerializedName("status")
    @Expose
    private String mStatus;

    @SerializedName("data")
    @Expose
    private HomeData mData;

    public String getmStatus() {
        return mStatus;
    }

    public void setmStatus(String mStatus) {
        this.mStatus = mStatus;
    }

    public HomeData getmData() {
        return mData;
    }

    public void setmData(HomeData mData) {
        this.mData = mData;
    }

    public class HomeData {

        @SerializedName("categories")
        @Expose
        private ArrayList<Categories> mCategories;

        @SerializedName("banner1")
        @Expose
        private ArrayList<Banner> mBanner1;

       @SerializedName("banner2")
        @Expose
        private ArrayList<Banner> mBanner2;

        @SerializedName("banner3")
        @Expose
        private ArrayList<Banner> mBanner3;

        @SerializedName("banner4")
        @Expose
        private ArrayList<Banner> mBanner4;

        @SerializedName("banner5")
        @Expose
        private ArrayList<Banner> mBanner5;

        @SerializedName("banner6")
        @Expose
        private ArrayList<Banner> mBanner6;

        @SerializedName("productsdata")
        @Expose
        private ArrayList<ProductData> mProductData;

        @SerializedName("clothing")
        @Expose
        private ArrayList<Clothing> mClothing;

        @SerializedName("electronics")
        @Expose
        private ArrayList<Electronics> mElectronics;

        @SerializedName("watches")
        @Expose
        private ArrayList<Watches> mWatches;

        @SerializedName("featured")
        @Expose
        private ArrayList<Featured> mFeatured;

        @SerializedName("bestseller")
        @Expose
        private ArrayList<BestSeller> mBestSeller;

        @SerializedName("specialoffer")
        @Expose
        private ArrayList<SpecialOffer> mSpecialOffer;

        @SerializedName("specialdeal")
        @Expose
        private ArrayList<SpecialDeal> mSpecialDeal;

        @SerializedName("bestoffassion")
        @Expose
        private ArrayList<BestOfFassion> mBestOfFassion;

        @SerializedName("fassionaccesory")
        @Expose
        private ArrayList<FassionAccesory> mFassionAccesory;

        @SerializedName("budgetbuy")
        @Expose
        private ArrayList<BudgetBuy> mBudgetBuy;

        @SerializedName("dealproducts")
        @Expose
        private ArrayList<DealProducts> mDealProducts;

        public ArrayList<Categories> getmCategories() {
            return mCategories;
        }

        public void setmCategories(ArrayList<Categories> mCategories) {
            this.mCategories = mCategories;
        }

        public ArrayList<Banner> getmBanner1() {
            return mBanner1;
        }

        public void setmBanner1(ArrayList<Banner> mBanner1) {
            this.mBanner1 = mBanner1;
        }

       public ArrayList<Banner> getmBanner2() {
            return mBanner2;
        }

        public void setmBanner2(ArrayList<Banner> mBanner2) {
            this.mBanner2 = mBanner2;
        }

        public ArrayList<Banner> getmBanner3() {
            return mBanner3;
        }

        public void setmBanner3(ArrayList<Banner> mBanner3) {
            this.mBanner3 = mBanner3;
        }

        public ArrayList<Banner> getmBanner4() {
            return mBanner4;
        }

        public void setmBanner4(ArrayList<Banner> mBanner4) {
            this.mBanner4 = mBanner4;
        }

        public ArrayList<Banner> getmBanner5() {
            return mBanner5;
        }

        public void setmBanner5(ArrayList<Banner> mBanner5) {
            this.mBanner5 = mBanner5;
        }

        public ArrayList<Banner> getmBanner6() {
            return mBanner6;
        }

        public void setmBanner6(ArrayList<Banner> mBanner6) {
            this.mBanner6 = mBanner6;
        }

        public ArrayList<ProductData> getmProductData() {
            return mProductData;
        }

        public void setmProductData(ArrayList<ProductData> mProductData) {
            this.mProductData = mProductData;
        }

        public ArrayList<Clothing> getmClothing() {
            return mClothing;
        }

        public void setmClothing(ArrayList<Clothing> mClothing) {
            this.mClothing = mClothing;
        }

        public ArrayList<Electronics> getmElectronics() {
            return mElectronics;
        }

        public void setmElectronics(ArrayList<Electronics> mElectronics) {
            this.mElectronics = mElectronics;
        }

        public ArrayList<Watches> getmWatches() {
            return mWatches;
        }

        public void setmWatches(ArrayList<Watches> mWatches) {
            this.mWatches = mWatches;
        }

        public ArrayList<Featured> getmFeatured() {
            return mFeatured;
        }

        public void setmFeatured(ArrayList<Featured> mFeatured) {
            this.mFeatured = mFeatured;
        }

        public ArrayList<BestSeller> getmBestSeller() {
            return mBestSeller;
        }

        public void setmBestSeller(ArrayList<BestSeller> mBestSeller) {
            this.mBestSeller = mBestSeller;
        }

        public ArrayList<SpecialOffer> getmSpecialOffer() {
            return mSpecialOffer;
        }

        public void setmSpecialOffer(ArrayList<SpecialOffer> mSpecialOffer) {
            this.mSpecialOffer = mSpecialOffer;
        }

        public ArrayList<SpecialDeal> getmSpecialDeal() {
            return mSpecialDeal;
        }

        public void setmSpecialDeal(ArrayList<SpecialDeal> mSpecialDeal) {
            this.mSpecialDeal = mSpecialDeal;
        }

        public ArrayList<BestOfFassion> getmBestOfFassion() {
            return mBestOfFassion;
        }

        public void setmBestOfFassion(ArrayList<BestOfFassion> mBestOfFassion) {
            this.mBestOfFassion = mBestOfFassion;
        }

        public ArrayList<FassionAccesory> getmFassionAccesory() {
            return mFassionAccesory;
        }

        public void setmFassionAccesory(ArrayList<FassionAccesory> mFassionAccesory) {
            this.mFassionAccesory = mFassionAccesory;
        }

        public ArrayList<BudgetBuy> getmBudgetBuy() {
            return mBudgetBuy;
        }

        public void setmBudgetBuy(ArrayList<BudgetBuy> mBudgetBuy) {
            this.mBudgetBuy = mBudgetBuy;
        }

        public ArrayList<DealProducts> getmDealProducts() {
            return mDealProducts;
        }

        public void setmDealProducts(ArrayList<DealProducts> mDealProducts) {
            this.mDealProducts = mDealProducts;
        }
    }
}
