package com.ws.design.coco_ecommerce_ui_kit.fcm;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.ws.design.coco_ecommerce_ui_kit.shared_preference.CocoPreferences;

public class MyFirebaseId extends FirebaseInstanceIdService {


    @Override
    public void onTokenRefresh() {
      //  super.onTokenRefresh();

        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
       Log.d("TAG", "Refreshed token: " + refreshedToken);

        CocoPreferences.setFCMRefreshToken(refreshedToken);
        CocoPreferences.savePreferencese();



    }
}
