package com.ws.design.coco_ecommerce_ui_kit.seller;


import com.ws.design.coco_ecommerce_ui_kit.my_wishlist.RemoveWishListResponse;
import com.ws.design.coco_ecommerce_ui_kit.product_details.AddToCartResponse;
import com.ws.design.coco_ecommerce_ui_kit.product_details.AddToWishListResponse;
import com.ws.design.coco_ecommerce_ui_kit.product_details.project_details_response.ProductDetailsResponse;

public interface SellerView {

    void showWait();

    void removeWait();

    void onFailure(String appErrorMessage);

    void getSellerProduct(SellerResponse sellerResponse);


    void addToCart(AddToCartResponse addToCartResponse);
}
