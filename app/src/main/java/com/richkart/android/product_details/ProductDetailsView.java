package com.richkart.android.product_details;


import com.richkart.android.my_wishlist.RemoveWishListResponse;
import com.richkart.android.product_details.project_details_response.CheckPincodeResponse;
import com.richkart.android.product_details.project_details_response.ProductDetailsResponse;

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
