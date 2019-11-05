package com.richkart.android.my_order;


public interface MyOrderView {

    void showWait();

    void removeWait();

    void onFailure(String appErrorMessage);

    void onMyOrderList(MyOrderResponse myOrderResponse);

    void cancelOrder(CancelOrderResponse cancelOrderResponse);
}