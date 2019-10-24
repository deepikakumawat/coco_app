package com.richkart.android.seller;


import com.richkart.android.product_details.AddToCartResponse;

public interface SellerView {

    void showWait();

    void removeWait();

    void onFailure(String appErrorMessage);

    void getSellerProduct(SellerResponse sellerResponse);


    void addToCart(AddToCartResponse addToCartResponse);
}
