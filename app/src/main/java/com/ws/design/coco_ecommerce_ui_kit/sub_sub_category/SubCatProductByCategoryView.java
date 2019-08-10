package com.ws.design.coco_ecommerce_ui_kit.sub_sub_category;


import com.ws.design.coco_ecommerce_ui_kit.product_by_category.ProductByCategoryResponse;
import com.ws.design.coco_ecommerce_ui_kit.product_details.AddToCartResponse;

public interface SubCatProductByCategoryView {

    void showWait();

    void removeWait();

    void onFailure(String appErrorMessage);

    void getSubCatProductByCategory(SubSubCategoriesResponse subSubCategoriesResponse);


}
