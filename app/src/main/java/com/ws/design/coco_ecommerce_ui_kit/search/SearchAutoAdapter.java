package com.ws.design.coco_ecommerce_ui_kit.search;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

import android.widget.Filter;
import android.widget.TextView;

import com.example.wolfsoft2.coco_ecommerce_ui_kit.R;
import com.ws.design.coco_ecommerce_ui_kit.product_details.project_details_response.ProductDetailsSimilier;


public class SearchAutoAdapter extends ArrayAdapter<ProductDetailsSimilier> {
    private final String MY_DEBUG_TAG = "CustomerAdapter";
    private ArrayList<ProductDetailsSimilier> items;
    private ArrayList<ProductDetailsSimilier> itemsAll;
    private ArrayList<ProductDetailsSimilier> suggestions;
    private int viewResourceId;
    private SearchFragment searchFragment;

    public SearchAutoAdapter(Context context, int viewResourceId, ArrayList<ProductDetailsSimilier> items, SearchFragment searchFragment) {
        super(context, viewResourceId, items);
        this.items = items;
        this.itemsAll = (ArrayList<ProductDetailsSimilier>) items.clone();
        this.suggestions = new ArrayList<ProductDetailsSimilier>();
        this.viewResourceId = viewResourceId;
        this.searchFragment = searchFragment;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater vi = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(viewResourceId, null);
        }
        ProductDetailsSimilier productDetailsSimilier = items.get(position);
        if (productDetailsSimilier != null) {
            TextView txtProductName = v.findViewById(R.id.txtProductName);
            if (txtProductName != null) {
                txtProductName.setText(productDetailsSimilier.getmProductName());
                txtProductName.setTag(productDetailsSimilier);
                txtProductName.setOnClickListener(searchFragment);
            }
        }
        return v;
    }

    @Override
    public Filter getFilter() {
        return nameFilter;
    }

    Filter nameFilter = new Filter() {
        @Override
        public String convertResultToString(Object resultValue) {
            String str = ((ProductDetailsSimilier)(resultValue)).getmProductName();
            return str;
        }
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            if(constraint != null) {
                suggestions.clear();
                for (ProductDetailsSimilier productDetailsSimilier : itemsAll) {
                    if(productDetailsSimilier.getmProductName().toLowerCase().startsWith(constraint.toString().toLowerCase())){
                        suggestions.add(productDetailsSimilier);
                    }
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = suggestions;
                filterResults.count = suggestions.size();
                return filterResults;
            } else {
                return new FilterResults();
            }
        }
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            ArrayList<ProductDetailsSimilier> filteredList = (ArrayList<ProductDetailsSimilier>) results.values;
            if(results != null && results.count > 0) {
                clear();
                for (ProductDetailsSimilier c : filteredList) {
                    add(c);
                }
                notifyDataSetChanged();
            }
        }
    };

}