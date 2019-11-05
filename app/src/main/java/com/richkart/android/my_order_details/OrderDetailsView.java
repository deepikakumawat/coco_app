package com.richkart.android.my_order_details;


import com.richkart.android.product_details.AddToCartResponse;

public interface OrderDetailsView {

    void showWait();

    void removeWait();

    void onFailure(String appErrorMessage);

    void addToCart(AddToCartResponse addToWishListResponse);

    void getOrderDetails(MyOrderDetailsResponse myOrderDetailsResponse);




}
