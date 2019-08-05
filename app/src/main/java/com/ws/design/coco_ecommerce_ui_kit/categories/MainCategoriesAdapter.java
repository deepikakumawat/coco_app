package com.ws.design.coco_ecommerce_ui_kit.categories;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.wolfsoft2.coco_ecommerce_ui_kit.R;
import com.ws.design.coco_ecommerce_ui_kit.utility.Constant;

import java.util.ArrayList;


/**
 * Created by wolfsoft3 on 30/8/18.
 */

public class MainCategoriesAdapter extends RecyclerView.Adapter<MainCategoriesAdapter.ViewHolder> {


    private Context context;
    private ArrayList<CategoriesResponse.MainCategoriesData> categoriesResponseArrayList;
    private CategoryFragment categoryFragment;


    public MainCategoriesAdapter(Context context, ArrayList<CategoriesResponse.MainCategoriesData> categoriesResponseArrayList, CategoryFragment categoryFragment) {
        this.context = context;
        this.categoriesResponseArrayList = categoriesResponseArrayList;
        this.categoryFragment = categoryFragment;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_catreogires, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        CategoriesResponse.MainCategoriesData mainCategoriesData = categoriesResponseArrayList.get(position);
        if (mainCategoriesData != null) {


            holder.txtCategories.setText(mainCategoriesData.getmCatName());

            holder.rvSubCategory.setLayoutManager(new GridLayoutManager(context, 3));
            holder.rvSubCategory.setFocusableInTouchMode(false);
            holder.rvSubCategory.setNestedScrollingEnabled(false);

            SubCategoriesAdapter allCategories_adapter = new SubCategoriesAdapter(context, mainCategoriesData.getmSubCategories(), categoryFragment);
            holder.rvSubCategory.setAdapter(allCategories_adapter);

        }


    }


    @Override
    public int getItemCount() {
        return categoriesResponseArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        RecyclerView rvSubCategory;
        TextView txtCategories;


        public ViewHolder(View itemView) {
            super(itemView);

            txtCategories = itemView.findViewById(R.id.txtCategories);


             rvSubCategory = itemView.findViewById(R.id.rvSubCategory);




        }
    }
}
