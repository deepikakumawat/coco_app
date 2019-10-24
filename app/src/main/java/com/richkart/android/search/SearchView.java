package com.richkart.android.search;


public interface SearchView {

    void showWait();

    void removeWait();

    void onFailure(String appErrorMessage);

    void getSearchProduct(SearchResponse searchResponse);

    void getTrendings(TrendingResponse trendingResponse);

}
