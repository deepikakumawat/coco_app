package com.richkart.android.my_cart;


import com.richkart.android.product_details.AddToCartResponse;
import com.richkart.android.product_details.AddToWishListResponse;

public interface CartView {

    void showWait();

    void removeWait();

    void onFailure(String appErrorMessage);

    void getCartList(CartListResponse cartListResponse);

    void addToCart(AddToCartResponse addToWishListResponse);

    void emptyCart(EmptyCartResponse emptyCartResponse);

    void removeCartOneByOne(RemoveCartOneByOneResponse removeCartOneByOneResponse);

    void removeCartByCross(RemoveCartByCrossResponse removeCartByCrossResponse);

    void addToWishList(AddToWishListResponse addToWishListResponse);

}
