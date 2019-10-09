package com.nav.richkart.product_by_category;


import com.nav.richkart.product_details.AddToCartResponse;
import com.nav.richkart.sub_sub_category.SubSubCategoriesResponse;

public interface ProductByCategoryView {

    void showWait();

    void removeWait();

    void onFailure(String appErrorMessage);

    void getProductByCategory(ProductByCategoryResponse productByCategoryResponse);


    void addToCart(AddToCartResponse addToCartResponse);

    void getSubCatProductByCategory(SubSubCategoriesResponse subSubCategoriesResponse);

}
