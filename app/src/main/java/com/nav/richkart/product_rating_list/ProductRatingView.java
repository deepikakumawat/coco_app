package com.nav.richkart.product_rating_list;


import com.nav.richkart.product_rating_list.product_rating_response.ProductRatingResponse;

public interface ProductRatingView {

    void showWait();

    void removeWait();

    void onFailure(String appErrorMessage);


    void getProductRating(ProductRatingResponse productRatingResponse);

    void addRating(AddRatingResponse addRatingResponse);



}
