package com.richkart.android.shipping;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.richkart.android.address.AddressListResponse;

import java.io.Serializable;
import java.util.ArrayList;

public class ShippingListResponse implements Serializable {


    private boolean isSelected;

    @SerializedName("shipType")
    @Expose
    private String mShipType;

    @SerializedName("shipTitle")
    @Expose
    private String mShipTitle;

    @SerializedName("shipBy")
    @Expose
    private String mshipBy;

    @SerializedName("shipAmount")
    @Expose
    private String mShipAmount;

    public String getmShipType() {
        return mShipType;
    }

    public void setmShipType(String mShipType) {
        this.mShipType = mShipType;
    }

    public String getmShipTitle() {
        return mShipTitle;
    }

    public void setmShipTitle(String mShipTitle) {
        this.mShipTitle = mShipTitle;
    }

    public String getMshipBy() {
        return mshipBy;
    }

    public void setMshipBy(String mshipBy) {
        this.mshipBy = mshipBy;
    }

    public String getmShipAmount() {
        return mShipAmount;
    }

    public void setmShipAmount(String mShipAmount) {
        this.mShipAmount = mShipAmount;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
