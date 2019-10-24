package com.richkart.android.home;


import com.richkart.android.home.home_response.HomeResponse;

public interface HomeView {

    void showWait();

    void removeWait();

    void onFailure(String appErrorMessage);

    void getHomeData(HomeResponse homeResponse);




}
