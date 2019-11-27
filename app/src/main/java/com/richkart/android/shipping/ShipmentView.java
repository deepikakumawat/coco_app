package com.richkart.android.shipping;


import com.richkart.android.address.AddressListResponse;
import com.richkart.android.checkout.CheckoutPaymentResponse;

import java.util.List;

public interface ShipmentView {

    void showWait();

    void removeWait();

    void onFailure(String appErrorMessage);

    void getCheckoutPayment(CheckoutPaymentResponse checkoutPaymentResponse);

    void getShippingList(List<ShippingListResponse> addressListResponse);

}
