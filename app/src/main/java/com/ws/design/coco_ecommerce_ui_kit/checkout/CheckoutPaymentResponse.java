package com.ws.design.coco_ecommerce_ui_kit.checkout;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class CheckoutPaymentResponse implements Serializable {

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("status")
    @Expose
    private String mStatus;

    @SerializedName("data")
    @Expose
    private  CheckoutPaymentData mData;

    public CheckoutPaymentData getmData() {
        return mData;
    }

    public void setmData(CheckoutPaymentData mData) {
        this.mData = mData;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getmStatus() {
        return mStatus;
    }

    public void setmStatus(String mStatus) {
        this.mStatus = mStatus;
    }


    public class CheckoutPaymentData implements  Serializable{

        @SerializedName("orderid")
        @Expose
        private String mOrderId;

        public String getmOrderId() {
            return mOrderId;
        }

        public void setmOrderId(String mOrderId) {
            this.mOrderId = mOrderId;
        }

        //        {"message":"Order successfully placed.","data":{"orderid":1,"invoiceno":"","amount":"399.00","taxamt":null,"sendername":"suresh","senderlastname":"klisasd","sendercontact":9810374615,"senderaddress":"dsamkd fsdfm","sendermail":"ashuandgupta19@gmail.com","senderlandmark":" ms mvdfv , d d f","recievername":"suresh","recieverlastname":"klisasd","recievermail":"ashuandgupta19@gmail.com","delivarycontact":9810374615,"deliveryaddress":"dsamkd fsdfm","deliverypincode":12056,"deliverystate":"Andhra Pradesh","deliverycity":"Port Blair","deliverylandmark":" ms mvdfv , d d f","paymentid":"pay_BtRQTB9CdewN0W","delivery_charges":"","status":1,"createdby":8,"modifiedby":8,"modifieddate":"2019-02-07 01:54:09","createddate":"2019-02-07 01:54:09","is_cancel":0,"cancelation_reason":""},"status":1}
    }
}
