package com.ws.design.coco_ecommerce_ui_kit.home;


import com.ws.design.coco_ecommerce_ui_kit.address.AddUpdateAddressResponse;
import com.ws.design.coco_ecommerce_ui_kit.address.AddressListResponse;
import com.ws.design.coco_ecommerce_ui_kit.address.DeleteAddressResponse;
import com.ws.design.coco_ecommerce_ui_kit.home.home_response.HomeResponse;

public interface HomeView {

    void showWait();

    void removeWait();

    void onFailure(String appErrorMessage);

    void getHomeData(HomeResponse homeResponse);




}
