package com.ws.design.coco_ecommerce_ui_kit.departments;


public interface DepartmentView {

    void showWait();

    void removeWait();

    void onFailure(String appErrorMessage);

    void getCategories(CategoriesResponse categoriesResponse);




}
