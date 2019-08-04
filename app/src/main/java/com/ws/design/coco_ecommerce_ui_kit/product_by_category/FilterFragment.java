package com.ws.design.coco_ecommerce_ui_kit.product_by_category;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.crystal.crystalrangeseekbar.interfaces.OnRangeSeekbarChangeListener;
import com.crystal.crystalrangeseekbar.interfaces.OnRangeSeekbarFinalValueListener;
import com.crystal.crystalrangeseekbar.widgets.CrystalRangeSeekbar;
import com.example.wolfsoft2.coco_ecommerce_ui_kit.R;
import com.ws.design.coco_ecommerce_ui_kit.base_fragment.BaseFragment;
import com.ws.design.coco_ecommerce_ui_kit.filter_product_by_category.FilterExpandableAdapter;
import com.ws.design.coco_ecommerce_ui_kit.interfaces.IFilterListener;
import com.ws.design.coco_ecommerce_ui_kit.utility.Util;

import java.util.ArrayList;
import java.util.List;

import fragment.FragmentManagerUtils;

public class FilterFragment extends BaseFragment implements View.OnClickListener {

    private View mView;
    private ExpandableListView expandableListView;
    private FilterExpandableAdapter filterExpandableAdapter;
    private ArrayList<ProductByCategoryResponse.ProductAttribueData> productAttribueDataArrayList = new ArrayList<>();
    List<String> filterAttribuesList = new ArrayList<>();
    private TextView txtFilter;
    private String filterAttribues;
    private LinearLayout lyParent;
    private IFilterListener iFilterListener;

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

        Bundle bundle = getArguments();
        if (bundle != null) {
            productAttribueDataArrayList = (ArrayList<ProductByCategoryResponse.ProductAttribueData>) bundle.getSerializable("productAttribueDataArrayList");

        }


        lyParent = mView.findViewById(R.id.lyParent);
        txtFilter = mView.findViewById(R.id.txtFilter);
        expandableListView = mView.findViewById(R.id.expandableListView);
        filterExpandableAdapter = new FilterExpandableAdapter(getActivity(), productAttribueDataArrayList, FilterFragment.this);
        expandableListView.setAdapter(filterExpandableAdapter);

        txtFilter.setOnClickListener(this);

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
            }
        });
    }

    @Override
    public void onClick(View view) {
        try {
            int vId = view.getId();
            switch (vId) {
                case R.id.txtAttributeName:
                    ProductByCategoryResponse.Attribtues productAttributes = ((ProductByCategoryResponse.Attribtues) view.getTag());
                    if (productAttributes != null) {

                        productAttributes.setSelected(true);
                        if (filterExpandableAdapter != null) {
                            filterExpandableAdapter.notifyDataSetChanged();
                        }

                        filterAttribuesList.add(productAttributes.getmAttributeId());

                        int size = filterAttribuesList.size();
                        Log.d("size", size + "");

                    }
                    break;
                case R.id.txtFilter:

                    if (!filterAttribuesList.isEmpty()) {
                        filterAttribues = android.text.TextUtils.join(",", filterAttribuesList);
                        iFilterListener.setSearchFilter(filterAttribues);
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
}
