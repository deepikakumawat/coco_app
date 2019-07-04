package com.ws.design.coco_ecommerce_ui_kit.product_details;


import com.ws.design.coco_ecommerce_ui_kit.product_details.project_details_response.ProductDetailsResponse;

public interface ProductDetailsView {

    void showWait();

    void removeWait();

    void onFailure(String appErrorMessage);

    void addToCart(AddToCartResponse addToWishListResponse);

    void addToWishList(AddToWishListResponse addToWishListResponse);

    void getProductDetails(ProductDetailsResponse productDetailsResponse);



}
