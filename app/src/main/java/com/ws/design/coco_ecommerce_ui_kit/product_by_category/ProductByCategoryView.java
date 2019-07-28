package com.ws.design.coco_ecommerce_ui_kit.product_by_category;


import com.ws.design.coco_ecommerce_ui_kit.product_details.AddToCartResponse;

public interface ProductByCategoryView {

    void showWait();

    void removeWait();

    void onFailure(String appErrorMessage);

    void getProductByCategory(ProductByCategoryResponse productByCategoryResponse);


    void addToCart(AddToCartResponse addToCartResponse);
}
