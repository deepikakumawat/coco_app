package com.ws.design.coco_ecommerce_ui_kit.my_wishlist;



public interface MyWishListView {

    void showWait();

    void removeWait();

    void onFailure(String appErrorMessage);

    void getMyWishList(MyWishListResponse myWishListResponse);


    void removeWishList(RemoveWishListResponse removeWishListResponse);


}
