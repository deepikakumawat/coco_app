package com.ws.design.coco_ecommerce_ui_kit.home;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.wolfsoft2.coco_ecommerce_ui_kit.R;
import com.ws.design.coco_ecommerce_ui_kit.address.AddressListActivity;
import com.ws.design.coco_ecommerce_ui_kit.address.AddressListResponse;
import com.ws.design.coco_ecommerce_ui_kit.home.home_response.Categories;

import java.util.ArrayList;


public class HomeCategoryAdapter extends RecyclerView.Adapter<HomeCategoryAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Categories> categoriesArrayList;
    private HomeActivity homeActivity;


    public HomeCategoryAdapter(Context context, ArrayList<Categories> categoriesArrayList, HomeActivity homeActivity) {
        this.context = context;
        this.categoriesArrayList = categoriesArrayList;
        this.homeActivity = homeActivity;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_category, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Categories categories = categoriesArrayList.get(position);
        if (categories != null) {
            holder.txtCategories.setText(TextUtils.isEmpty(categories.getmCatName()) ? "-" :categories.getmCatName());


            holder.txtCategories.setTag(categories);
            holder.txtCategories.setTag(R.id.txtCategories,position);
            holder.txtCategories.setOnClickListener( homeActivity);
        }

    }


    @Override
    public int getItemCount() {
        return categoriesArrayList.size();
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {

        private TextView txtCategories;




        public ViewHolder(View view) {
            super(view);

            txtCategories = view.findViewById(R.id.txtCategories);



        }
    }
}
