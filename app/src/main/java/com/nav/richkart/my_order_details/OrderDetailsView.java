package com.nav.richkart.my_order_details;


import com.nav.richkart.product_details.AddToCartResponse;

public interface OrderDetailsView {

    void showWait();

    void removeWait();

    void onFailure(String appErrorMessage);

    void addToCart(AddToCartResponse addToWishListResponse);

    void getOrderDetails(MyOrderDetailsResponse myOrderDetailsResponse);




}
