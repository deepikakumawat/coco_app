package com.richkart.android.search;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class TrendingResponse {

    @SerializedName("status")
    @Expose
    private String mStatus;




    @SerializedName("data")
    @Expose
    private ArrayList<TrendingData> mTrendingData;

    public String getmStatus() {
        return mStatus;
    }

    public void setmStatus(String mStatus) {
        this.mStatus = mStatus;
    }

    public ArrayList<TrendingData> getmTrendingData() {
        return mTrendingData;
    }

    public void setmTrendingData(ArrayList<TrendingData> mTrendingData) {
        this.mTrendingData = mTrendingData;
    }

    protected class TrendingData {


        @SerializedName("id")
        @Expose
        private int mId;

        @SerializedName("name")
        @Expose
        private String mName;

        public int getmId() {
            return mId;
        }

        public void setmId(int mId) {
            this.mId = mId;
        }

        public String getmName() {
            return mName;
        }

        public void setmName(String mName) {
            this.mName = mName;
        }
    }
}
