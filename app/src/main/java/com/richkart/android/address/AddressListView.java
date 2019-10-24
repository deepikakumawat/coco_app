package com.richkart.android.address;


public interface AddressListView {

    void showWait();

    void removeWait();

    void onFailure(String appErrorMessage);

    void getAddressList(AddressListResponse addressListResponse);

    void deleteAddress(DeleteAddressResponse deleteAddressResponse);

    void addUpdateAddress(AddUpdateAddressResponse addUpdateAddressResponse);


}
