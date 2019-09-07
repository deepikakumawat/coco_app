package com.nav.richkart.my_cart;


import com.nav.richkart.product_details.AddToCartResponse;
import com.nav.richkart.product_details.AddToWishListResponse;

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
