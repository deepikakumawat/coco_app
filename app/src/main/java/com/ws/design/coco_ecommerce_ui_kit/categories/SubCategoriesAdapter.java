package com.ws.design.coco_ecommerce_ui_kit.categories;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.example.wolfsoft2.coco_ecommerce_ui_kit.R;
import com.ws.design.coco_ecommerce_ui_kit.utility.Constant;

import java.util.ArrayList;


/**
 * Created by wolfsoft3 on 30/8/18.
 */

public class SubCategoriesAdapter extends RecyclerView.Adapter<SubCategoriesAdapter.ViewHolder> {


    private Context context;
    private ArrayList<CategoriesResponse.SubCategoriesData> categoriesResponseArrayList;
    private CategoryFragment categoryFragment;

    private int myPos = 0;

    public SubCategoriesAdapter(Context context, ArrayList<CategoriesResponse.SubCategoriesData> categoriesResponseArrayList, CategoryFragment categoryFragment) {
        this.context = context;
        this.categoriesResponseArrayList = categoriesResponseArrayList;
        this.categoryFragment = categoryFragment;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_sub_categories, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        CategoriesResponse.SubCategoriesData subCategoriesData = categoriesResponseArrayList.get(position);
        if (subCategoriesData != null) {


            holder.txtSubCategory.setText(subCategoriesData.getmCatName());

            String thumbnail = Constant.MEDIA_THUMBNAIL_BASE_URL + subCategoriesData.getmCatIconImg();
            Glide.with(context).load(thumbnail).placeholder(R.drawable.richkart).into(holder.imgCategories);

            holder.lySubCategory.setTag(subCategoriesData);
            holder.lySubCategory.setOnClickListener(categoryFragment);

        }


    }


    @Override
    public int getItemCount() {
        return categoriesResponseArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imgCategories;
        TextView txtSubCategory;
        LinearLayout lySubCategory;


        public ViewHolder(View itemView) {
            super(itemView);


            txtSubCategory = itemView.findViewById(R.id.txtSubCategory);

            imgCategories = itemView.findViewById(R.id.imgCategories);
            lySubCategory = itemView.findViewById(R.id.lySubCategory);


        }
    }
}
