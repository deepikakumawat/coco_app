package com.nav.richkart.search;


public interface SearchView {

    void showWait();

    void removeWait();

    void onFailure(String appErrorMessage);

    void getSearchProduct(SearchResponse searchResponse);

}
