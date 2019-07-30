package com.ws.design.coco_ecommerce_ui_kit.filter_product_by_category;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.wolfsoft2.coco_ecommerce_ui_kit.R;
import com.ws.design.coco_ecommerce_ui_kit.product_by_category.FilterFragment;
import com.ws.design.coco_ecommerce_ui_kit.product_by_category.ProductByCategoryResponse;


import java.util.ArrayList;

public class FilterExpandableAdapter extends BaseExpandableListAdapter {

    private Context context;
    private ArrayList<ProductByCategoryResponse.ProductAttribueData> productAttribueDataArrayList;

    public FilterExpandableAdapter(Context context, ArrayList<ProductByCategoryResponse.ProductAttribueData> productAttribueDataArrayList, FilterFragment filterFragment) {
        this.context = context;
        this.productAttribueDataArrayList = productAttribueDataArrayList;
    }

    @Override
    public Object getChild(int listPosition, int expandedListPosition) {
        return productAttribueDataArrayList.get(expandedListPosition).getmAttributes().get(listPosition);
    }

    @Override
    public long getChildId(int listPosition, int expandedListPosition) {
        return expandedListPosition;
    }

    @Override
    public View getChildView(int listPosition, final int expandedListPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        final ProductByCategoryResponse.Attribtues productAttributes = (ProductByCategoryResponse.Attribtues) getChild(listPosition, expandedListPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list_child_item_filter, null);
        }
        TextView txtAttributeName = (TextView) convertView
                .findViewById(R.id.txtAttributeName);
        txtAttributeName.setText(productAttributes.getmAttributeName());
        return convertView;
    }

    @Override
    public int getChildrenCount(int listPosition) {
        return productAttribueDataArrayList.get(listPosition).getmAttributes().size();
    }

    @Override
    public Object getGroup(int listPosition) {
        return productAttribueDataArrayList.get(listPosition);
    }

    @Override
    public int getGroupCount() {
        return productAttribueDataArrayList.size();
    }

    @Override
    public long getGroupId(int listPosition) {
        return listPosition;
    }

    @Override
    public View getGroupView(int listPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        ProductByCategoryResponse.ProductAttribueData productAttribueData = (ProductByCategoryResponse.ProductAttribueData) getGroup(listPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list_group, null);
        }
        TextView txtAttributeType = (TextView) convertView
                .findViewById(R.id.txtAttributeType);
        txtAttributeType.setTypeface(null, Typeface.BOLD);
        txtAttributeType.setText(productAttribueData.getmType());
        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int listPosition, int expandedListPosition) {
        return true;
    }
}