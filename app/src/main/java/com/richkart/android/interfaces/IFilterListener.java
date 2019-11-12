package com.richkart.android.interfaces;

import com.richkart.android.product_by_category.ProductByCategoryResponse;

import java.util.ArrayList;

public interface IFilterListener {

    void setSearchFilter(ArrayList<ProductByCategoryResponse.ProductAttribueData> mproductAttribueDataArrayList, String minimumValue, String maximumValue, ArrayList<ProductByCategoryResponse.Attribtues> selectedAttributesArrayList, int tabPostion);

}
