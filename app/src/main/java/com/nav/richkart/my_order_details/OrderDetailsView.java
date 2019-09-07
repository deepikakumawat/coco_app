package com.nav.richkart.my_order_details;



public interface OrderDetailsView {

    void showWait();

    void removeWait();

    void onFailure(String appErrorMessage);

    void getOrderDetails(MyOrderDetailsResponse myOrderDetailsResponse);




}
