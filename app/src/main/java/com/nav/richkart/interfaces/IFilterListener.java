package com.nav.richkart.interfaces;

import com.nav.richkart.product_by_category.ProductByCategoryResponse;

import java.util.ArrayList;

public interface IFilterListener {

//    void setSearchFilter(ArrayList<ProductByCategoryResponse.ProductAttribueData> mproductAttribueDataArrayList, String[] filterAttribues, String minimumValue, String maximumValue,ArrayList<ProductByCategoryResponse.Attribtues> selectedAttributesArrayList);
    void setSearchFilter(ArrayList<ProductByCategoryResponse.ProductAttribueData> mproductAttribueDataArrayList,  String minimumValue, String maximumValue,ArrayList<ProductByCategoryResponse.Attribtues> selectedAttributesArrayList);

}
