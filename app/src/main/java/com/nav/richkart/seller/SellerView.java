package com.nav.richkart.seller;


import com.nav.richkart.product_details.AddToCartResponse;

public interface SellerView {

    void showWait();

    void removeWait();

    void onFailure(String appErrorMessage);

    void getSellerProduct(SellerResponse sellerResponse);


    void addToCart(AddToCartResponse addToCartResponse);
}
