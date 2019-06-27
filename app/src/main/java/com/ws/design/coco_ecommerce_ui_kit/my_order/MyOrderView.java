package com.ws.design.coco_ecommerce_ui_kit.my_order;


public interface MyOrderView {

    void showWait();

    void removeWait();

    void onFailure(String appErrorMessage);

    void onMyOrderList(MyOrderResponse myOrderResponse);
}
