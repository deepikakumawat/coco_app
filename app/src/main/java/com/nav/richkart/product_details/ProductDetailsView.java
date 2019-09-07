package com.nav.richkart.product_details;


import com.nav.richkart.my_wishlist.RemoveWishListResponse;
import com.nav.richkart.product_details.project_details_response.CheckPincodeResponse;
import com.nav.richkart.product_details.project_details_response.ProductDetailsResponse;

public interface ProductDetailsView {

    void showWait();

    void removeWait();

    void onFailure(String appErrorMessage);

    void addToCart(AddToCartResponse addToWishListResponse);

    void addToWishList(AddToWishListResponse addToWishListResponse);

    void getProductDetails(ProductDetailsResponse productDetailsResponse);

    void removeWishList(RemoveWishListResponse removeWishListResponse);

    void checkPincode(CheckPincodeResponse checkPincodeResponse);



}
