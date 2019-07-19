package com.ws.design.coco_ecommerce_ui_kit.product_by_category;




public interface ProductByCategoryView {

    void showWait();

    void removeWait();

    void onFailure(String appErrorMessage);

    void getProductByCategory(ProductByCategoryResponse productByCategoryResponse);





}
