package com.nav.richkart.home;


import com.nav.richkart.home.home_response.HomeResponse;

public interface HomeView {

    void showWait();

    void removeWait();

    void onFailure(String appErrorMessage);

    void getHomeData(HomeResponse homeResponse);




}
