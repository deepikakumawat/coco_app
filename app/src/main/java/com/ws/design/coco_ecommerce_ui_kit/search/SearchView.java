package com.ws.design.coco_ecommerce_ui_kit.search;


import com.ws.design.coco_ecommerce_ui_kit.my_order.CancelOrderResponse;
import com.ws.design.coco_ecommerce_ui_kit.my_order.MyOrderResponse;

public interface SearchView {

    void showWait();

    void removeWait();

    void onFailure(String appErrorMessage);

    void getSearchProduct(SearchResponse searchResponse);

}
