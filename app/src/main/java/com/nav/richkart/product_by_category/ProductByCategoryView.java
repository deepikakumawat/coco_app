package com.nav.richkart.product_by_category;


import com.nav.richkart.product_details.AddToCartResponse;

public interface ProductByCategoryView {

    void showWait();

    void removeWait();

    void onFailure(String appErrorMessage);

    void getProductByCategory(ProductByCategoryResponse productByCategoryResponse);


    void addToCart(AddToCartResponse addToCartResponse);
}
