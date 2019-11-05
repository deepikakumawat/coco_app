package com.richkart.android.my_order_details;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OrderDetailsData {

    @SerializedName("orderid")
    @Expose
    private String mOrderId;

    @SerializedName("invoiceno")
    @Expose
    private String mInvoiceNo;

    @SerializedName("amount")
    @Expose
    private String mAmount;

    @SerializedName("taxamt")
    @Expose
    private String mTaxAmt;

    @SerializedName("sendername")
    @Expose
    private String mSenderName;

    @SerializedName("senderlastname")
    @Expose
    private String mSenderLastName;

    @SerializedName("sendercontact")
    @Expose
    private String mSenderContact;

    @SerializedName("senderaddress")
    @Expose
    private String mSenderAddress;

    @SerializedName("sendermail")
    @Expose
    private String mSenderEmail;

    @SerializedName("senderlandmark")
    @Expose
    private String mSenderLandmark;

    @SerializedName("recievername")
    @Expose
    private String mRecieverName;

    @SerializedName("recieverlastname")
    @Expose
    private String mReciverLastName;

    @SerializedName("recievermail")
    @Expose
    private String mReciverEmail;

    @SerializedName("delivarycontact")
    @Expose
    private String mDeliveryContact;

    @SerializedName("deliveryaddress")
    @Expose
    private String mDeliveryAddress;

    @SerializedName("deliverypincode")
    @Expose
    private String mDeliveryPincode;

    @SerializedName("deliverystate")
    @Expose
    private String mDeliveryState;

    @SerializedName("deliverycity")
    @Expose
    private String mDeliveryCity;

    @SerializedName("deliverylandmark")
    @Expose
    private String mDeliveryLandmark;

    @SerializedName("paymentid")
    @Expose
    private String mPaymentId;

    @SerializedName("delivery_charges")
    @Expose
    private String mDeliveryCharges;

    @SerializedName("status")
    @Expose
    private String mStauts;

    @SerializedName("createdby")
    @Expose
    private String mCreatedBy;

    @SerializedName("modifiedby")
    @Expose
    private String mModifiyBy;

    @SerializedName("modifieddate")
    @Expose
    private String mModifiedData;


    @SerializedName("createddate")
    @Expose
    private String mCreatedDate;

    @SerializedName("is_cancel")
    @Expose
    private String mIsCancel;

    @SerializedName("cancelation_reason")
    @Expose
    private String mCancellationRason;

    @SerializedName("itemid")
    @Expose
    private String mItemId;

    public String getmOrderId() {
        return mOrderId;
    }

    public void setmOrderId(String mOrderId) {
        this.mOrderId = mOrderId;
    }

    public String getmInvoiceNo() {
        return mInvoiceNo;
    }

    public void setmInvoiceNo(String mInvoiceNo) {
        this.mInvoiceNo = mInvoiceNo;
    }

    public String getmAmount() {
        return mAmount;
    }

    public void setmAmount(String mAmount) {
        this.mAmount = mAmount;
    }

    public String getmTaxAmt() {
        return mTaxAmt;
    }

    public void setmTaxAmt(String mTaxAmt) {
        this.mTaxAmt = mTaxAmt;
    }

    public String getmSenderName() {
        return mSenderName;
    }

    public void setmSenderName(String mSenderName) {
        this.mSenderName = mSenderName;
    }

    public String getmSenderLastName() {
        return mSenderLastName;
    }

    public void setmSenderLastName(String mSenderLastName) {
        this.mSenderLastName = mSenderLastName;
    }

    public String getmSenderContact() {
        return mSenderContact;
    }

    public void setmSenderContact(String mSenderContact) {
        this.mSenderContact = mSenderContact;
    }

    public String getmSenderAddress() {
        return mSenderAddress;
    }

    public void setmSenderAddress(String mSenderAddress) {
        this.mSenderAddress = mSenderAddress;
    }

    public String getmSenderEmail() {
        return mSenderEmail;
    }

    public void setmSenderEmail(String mSenderEmail) {
        this.mSenderEmail = mSenderEmail;
    }

    public String getmSenderLandmark() {
        return mSenderLandmark;
    }

    public void setmSenderLandmark(String mSenderLandmark) {
        this.mSenderLandmark = mSenderLandmark;
    }

    public String getmRecieverName() {
        return mRecieverName;
    }

    public void setmRecieverName(String mRecieverName) {
        this.mRecieverName = mRecieverName;
    }

    public String getmReciverLastName() {
        return mReciverLastName;
    }

    public void setmReciverLastName(String mReciverLastName) {
        this.mReciverLastName = mReciverLastName;
    }

    public String getmReciverEmail() {
        return mReciverEmail;
    }

    public void setmReciverEmail(String mReciverEmail) {
        this.mReciverEmail = mReciverEmail;
    }

    public String getmDeliveryContact() {
        return mDeliveryContact;
    }

    public void setmDeliveryContact(String mDeliveryContact) {
        this.mDeliveryContact = mDeliveryContact;
    }

    public String getmDeliveryAddress() {
        return mDeliveryAddress;
    }

    public void setmDeliveryAddress(String mDeliveryAddress) {
        this.mDeliveryAddress = mDeliveryAddress;
    }

    public String getmDeliveryPincode() {
        return mDeliveryPincode;
    }

    public void setmDeliveryPincode(String mDeliveryPincode) {
        this.mDeliveryPincode = mDeliveryPincode;
    }

    public String getmDeliveryState() {
        return mDeliveryState;
    }

    public void setmDeliveryState(String mDeliveryState) {
        this.mDeliveryState = mDeliveryState;
    }

    public String getmDeliveryCity() {
        return mDeliveryCity;
    }

    public void setmDeliveryCity(String mDeliveryCity) {
        this.mDeliveryCity = mDeliveryCity;
    }

    public String getmDeliveryLandmark() {
        return mDeliveryLandmark;
    }

    public void setmDeliveryLandmark(String mDeliveryLandmark) {
        this.mDeliveryLandmark = mDeliveryLandmark;
    }

    public String getmPaymentId() {
        return mPaymentId;
    }

    public void setmPaymentId(String mPaymentId) {
        this.mPaymentId = mPaymentId;
    }

    public String getmDeliveryCharges() {
        return mDeliveryCharges;
    }

    public void setmDeliveryCharges(String mDeliveryCharges) {
        this.mDeliveryCharges = mDeliveryCharges;
    }

    public String getmStauts() {
        return mStauts;
    }

    public void setmStauts(String mStauts) {
        this.mStauts = mStauts;
    }

    public String getmCreatedBy() {
        return mCreatedBy;
    }

    public void setmCreatedBy(String mCreatedBy) {
        this.mCreatedBy = mCreatedBy;
    }

    public String getmModifiyBy() {
        return mModifiyBy;
    }

    public void setmModifiyBy(String mModifiyBy) {
        this.mModifiyBy = mModifiyBy;
    }

    public String getmModifiedData() {
        return mModifiedData;
    }

    public void setmModifiedData(String mModifiedData) {
        this.mModifiedData = mModifiedData;
    }

    public String getmCreatedDate() {
        return mCreatedDate;
    }

    public void setmCreatedDate(String mCreatedDate) {
        this.mCreatedDate = mCreatedDate;
    }

    public String getmIsCancel() {
        return mIsCancel;
    }

    public void setmIsCancel(String mIsCancel) {
        this.mIsCancel = mIsCancel;
    }

    public String getmCancellationRason() {
        return mCancellationRason;
    }

    public void setmCancellationRason(String mCancellationRason) {
        this.mCancellationRason = mCancellationRason;
    }

    public String getmItemId() {
        return mItemId;
    }

    public void setmItemId(String mItemId) {
        this.mItemId = mItemId;
    }
}
