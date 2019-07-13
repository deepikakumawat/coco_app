package com.ws.design.coco_ecommerce_ui_kit.product_rating_list;


import com.ws.design.coco_ecommerce_ui_kit.product_details.AddToCartResponse;
import com.ws.design.coco_ecommerce_ui_kit.product_details.AddToWishListResponse;
import com.ws.design.coco_ecommerce_ui_kit.product_details.project_details_response.ProductDetailsResponse;
import com.ws.design.coco_ecommerce_ui_kit.product_rating_list.product_rating_response.ProductRatingResponse;

public interface ProductRatingView {

    void showWait();

    void removeWait();

    void onFailure(String appErrorMessage);


    void getProductRating(ProductRatingResponse productRatingResponse);

    void addRating(AddRatingResponse addRatingResponse);



}
