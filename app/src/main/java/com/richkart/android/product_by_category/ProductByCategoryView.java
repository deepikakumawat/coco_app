package com.richkart.android.product_by_category;


import com.richkart.android.product_details.AddToCartResponse;
import com.richkart.android.sub_sub_category.SubSubCategoriesResponse;

public interface ProductByCategoryView {

    void showWait();

    void removeWait();

    void onFailure(String appErrorMessage);

    void getProductByCategory(ProductByCategoryResponse productByCategoryResponse);


    void addToCart(AddToCartResponse addToCartResponse);

    void getSubCatProductByCategory(SubSubCategoriesResponse subSubCategoriesResponse);

}
