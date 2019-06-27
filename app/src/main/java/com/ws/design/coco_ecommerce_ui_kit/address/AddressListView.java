package com.ws.design.coco_ecommerce_ui_kit.address;


public interface AddressListView {

    void showWait();

    void removeWait();

    void onFailure(String appErrorMessage);

    void getAddressList(AddressListResponse addressListResponse);

    void deleteAddress(DeleteAddressResponse deleteAddressResponse);

}
