package com.nav.richkart.departments;


public interface DepartmentView {

    void showWait();

    void removeWait();

    void onFailure(String appErrorMessage);

    void getCategories(CategoriesResponse categoriesResponse);




}
