package com.ws.design.coco_ecommerce_ui_kit.categories;


import com.ws.design.coco_ecommerce_ui_kit.home.home_response.HomeResponse;

public interface CategoriesView {

    void showWait();

    void removeWait();

    void onFailure(String appErrorMessage);

    void getCategories(CategoriesResponse categoriesResponse);




}
