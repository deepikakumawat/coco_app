package com.richkart.android.sub_sub_category;


public interface SubCatProductByCategoryView {

    void showWait();

    void removeWait();

    void onFailure(String appErrorMessage);

    void getSubCatProductByCategory(SubSubCategoriesResponse subSubCategoriesResponse);


}
