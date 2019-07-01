package com.ws.design.coco_ecommerce_ui_kit.product_details;


public interface ProductDetailsView {

    void showWait();

    void removeWait();

    void onFailure(String appErrorMessage);

    void addToCart(AddToCartResponse addToWishListResponse);

    void addToWishList(AddToWishListResponse addToWishListResponse);



}
