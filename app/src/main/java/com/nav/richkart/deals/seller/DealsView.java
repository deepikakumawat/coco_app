package com.nav.richkart.deals.seller;


import com.nav.richkart.product_details.AddToCartResponse;

public interface DealsView {

    void showWait();

    void removeWait();

    void onFailure(String appErrorMessage);

    void getSellerProduct(DealsResponse dealsResponse);


    void addToCart(AddToCartResponse addToCartResponse);
}
