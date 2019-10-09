package com.nav.richkart.product_by_category;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.crystal.crystalrangeseekbar.interfaces.OnRangeSeekbarChangeListener;
import com.crystal.crystalrangeseekbar.interfaces.OnRangeSeekbarFinalValueListener;
import com.crystal.crystalrangeseekbar.widgets.CrystalRangeSeekbar;
import com.nav.richkart.R;
import com.nav.richkart.base_fragment.BaseFragment;
import com.nav.richkart.filter_product_by_category.FilterExpandableAdapter;
import com.nav.richkart.interfaces.IFilterListener;
import com.nav.richkart.my_cart.CartListResponse;
import com.nav.richkart.utility.Util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import com.nav.richkart.fragment.FragmentManagerUtils;

public class FilterFragment extends BaseFragment implements View.OnClickListener {

    private View mView;
    private ExpandableListView expandableListView;
    private FilterExpandableAdapter filterExpandableAdapter;
    private ArrayList<ProductByCategoryResponse.ProductAttribueData> productAttribueDataArrayList = new ArrayList<>();
    private ArrayList<ProductByCategoryResponse.Attribtues> selectedAttributesArrayList = new ArrayList<>();
//    HashMap<String, String> filerHaspMap = new HashMap<String, String>();

    private Button btnFilter;
    private LinearLayout lyParent;
    private IFilterListener iFilterListener;
    private String minimumValue;
    private String maximumValue;

    public void setmIFilterListener(IFilterListener iFilterListener) {
        this.iFilterListener = iFilterListener;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        mView = inflater.inflate(R.layout.activity_filter, container, false);

        return mView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        try {
            Bundle bundle = getArguments();
            if (bundle != null) {
                productAttribueDataArrayList = (ArrayList<ProductByCategoryResponse.ProductAttribueData>) bundle.getSerializable("productAttribueDataArrayList");
                selectedAttributesArrayList = (ArrayList<ProductByCategoryResponse.Attribtues>) bundle.getSerializable("selectedAttributesArrayList");
//                setFilterHaspMap(productAttribueDataArrayList);
            }


            lyParent = mView.findViewById(R.id.lyParent);
            btnFilter = mView.findViewById(R.id.btnFilter);
            expandableListView = mView.findViewById(R.id.expandableListView);
            filterExpandableAdapter = new FilterExpandableAdapter(getActivity(), productAttribueDataArrayList, FilterFragment.this);
            expandableListView.setAdapter(filterExpandableAdapter);

            btnFilter.setOnClickListener(this);

            CrystalRangeSeekbar rangeSeekbar = mView.findViewById(R.id.rangeSeekbar1);


            TextView tvMin = mView.findViewById(R.id.textMin1);
            TextView tvMax = mView.findViewById(R.id.textMin2);

// set listener
            rangeSeekbar.setOnRangeSeekbarChangeListener(new OnRangeSeekbarChangeListener() {
                @Override
                public void valueChanged(Number minValue, Number maxValue) {
                    tvMin.setText(String.valueOf(minValue));
                    tvMax.setText(String.valueOf(maxValue));

                }
            });

// set final value listener
            rangeSeekbar.setOnRangeSeekbarFinalValueListener(new OnRangeSeekbarFinalValueListener() {
                @Override
                public void finalValue(Number minValue, Number maxValue) {
                    Log.d("CRS=>", String.valueOf(minValue) + " : " + String.valueOf(maxValue));

                    minimumValue = String.valueOf(minValue);
                    maximumValue = String.valueOf(maxValue);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

   /* private void setFilterHaspMap(ArrayList<ProductByCategoryResponse.ProductAttribueData> productAttribueDataArrayList) {
        for (ProductByCategoryResponse.ProductAttribueData productAttribueData : productAttribueDataArrayList) {
            for (ProductByCategoryResponse.Attribtues productAttributes : productAttribueData.getmAttributes()) {
                if (productAttributes != null) {
                    addFilterDataInHashmap(productAttributes);
                }
            }
        }
    }*/

    @Override
    public void onClick(View view) {
        try {
            int vId = view.getId();
            switch (vId) {
                case R.id.txtAttributeName:
                    ProductByCategoryResponse.Attribtues productAttributes = ((ProductByCategoryResponse.Attribtues) view.getTag());
                    if (productAttributes != null) {

                        if (productAttributes.isSelected()) {
                            productAttributes.setSelected(false);
                            if (!selectedAttributesArrayList.isEmpty()) {
                                selectedAttributesArrayList.remove(productAttributes);
                            }

                        } else {
                            productAttributes.setSelected(true);
                            selectedAttributesArrayList.add(productAttributes);
                        }
                        if (filterExpandableAdapter != null) {
                            filterExpandableAdapter.notifyDataSetChanged();
                        }


                        //  add filter data in hashmap

//                        addFilterDataInHashmap(productAttributes);


                    }
                    break;
                case R.id.btnFilter:

/*
                    if (!filerHaspMap.isEmpty() || !TextUtils.isEmpty(minimumValue) || !TextUtils.isEmpty(maximumValue)) {
                        String[] result = filerHaspMap.values().toArray(new String[0]);
                        iFilterListener.setSearchFilter(productAttribueDataArrayList, result, minimumValue, maximumValue,selectedAttributesArrayList);
                        FragmentManagerUtils.popFragment(getFragmentManager());
                    } else {
                        Util.showCenteredToast(lyParent, getActivity(), getString(R.string.select_filter), "");
                    }
*/

                    if (!selectedAttributesArrayList.isEmpty() || !TextUtils.isEmpty(minimumValue) || !TextUtils.isEmpty(maximumValue)) {
                        iFilterListener.setSearchFilter(productAttribueDataArrayList, minimumValue, maximumValue,selectedAttributesArrayList);
                        FragmentManagerUtils.popFragment(getFragmentManager());
                    } else {
                        Util.showCenteredToast(lyParent, getActivity(), getString(R.string.select_filter), "");
                    }

                    break;
                default:
                    break;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected String getActionbarTitle() {
        return getString(R.string.filer);
    }

    @Override
    protected boolean isSearchIconVisible() {
        return false;
    }

    @Override
    protected boolean isCartIconVisible() {
        return false;
    }

//    private void addFilterDataInHashmap(ProductByCategoryResponse.Attribtues productAttributes) {
//        if (filerHaspMap.isEmpty()) {
//            if (productAttributes.isSelected()) {
//                filerHaspMap.put(productAttributes.getmAttributeType(), productAttributes.getmAttributeId());
//            }
//        } else {
//
//            if (productAttributes.isSelected()) {
//                if (filerHaspMap.containsKey(productAttributes.getmAttributeType())) {
//                    String value = filerHaspMap.get(productAttributes.getmAttributeType());
//                    value = value + "," + productAttributes.getmAttributeId();
//
//                    filerHaspMap.put(productAttributes.getmAttributeType(), value);
//                } else {
//                    filerHaspMap.put(productAttributes.getmAttributeType(), productAttributes.getmAttributeId());
//
//                }
//            } else {
//                if (filerHaspMap.containsKey(productAttributes.getmAttributeType())) {
//                    String value = filerHaspMap.get(productAttributes.getmAttributeType());
//                    String removeValue = productAttributes.getmAttributeId();
//
//                    List<String> items = Arrays.asList(value.split("\\s*,\\s*"));
//                    List<String> newItems = new ArrayList<>();
//                    for (String item : items) {
//                        if (!removeValue.equalsIgnoreCase(item)) {
//                            newItems.add(item);
//                        }
//                    }
//
//                    String newValue = android.text.TextUtils.join(",", newItems);
//                    if (TextUtils.isEmpty(newValue)) {
//                        filerHaspMap.remove(productAttributes.getmAttributeType());
//                    } else {
//                        filerHaspMap.put(productAttributes.getmAttributeType(), newValue);
//                    }
//
//                } else {
//                    filerHaspMap.remove(productAttributes.getmAttributeType());
//                }
//            }
//        }
//        int size = filerHaspMap.size();
//        Log.d("size", size + "");
//    }
}
