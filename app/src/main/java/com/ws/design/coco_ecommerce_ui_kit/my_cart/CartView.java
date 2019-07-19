package com.ws.design.coco_ecommerce_ui_kit.my_cart;


import com.ws.design.coco_ecommerce_ui_kit.product_details.AddToCartResponse;

public interface CartView {

    void showWait();

    void removeWait();

    void onFailure(String appErrorMessage);

    void getCartList(CartListResponse cartListResponse);

    void addToCart(AddToCartResponse addToWishListResponse);

    void emptyCart(EmptyCartResponse emptyCartResponse);

    void removeCartOneByOne(RemoveCartOneByOneResponse removeCartOneByOneResponse);

    void removeCartByCross(RemoveCartByCrossResponse removeCartByCrossResponse);


}
