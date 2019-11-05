package com.richkart.android.deals.seller;


import com.richkart.android.product_details.AddToCartResponse;

public interface DealsView {

    void showWait();

    void removeWait();

    void onFailure(String appErrorMessage);

    void getSellerProduct(DealsResponse dealsResponse);


    void addToCart(AddToCartResponse addToCartResponse);
}
