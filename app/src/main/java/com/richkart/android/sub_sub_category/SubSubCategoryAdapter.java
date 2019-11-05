package com.richkart.android.sub_sub_category;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.richkart.android.R;
import com.richkart.android.utility.Constant;

import java.util.ArrayList;


/**
 * Created by wolfsoft3 on 30/8/18.
 */

public class SubSubCategoryAdapter extends RecyclerView.Adapter<SubSubCategoryAdapter.ViewHolder> {


    private Context context;
    private ArrayList<SubSubCategoriesResponse.MainSubCategoriesData> mainSubCategoriesDataArrayList;
    private SubCategoryFragment subCategoryFragment;


    public SubSubCategoryAdapter(Context context, ArrayList<SubSubCategoriesResponse.MainSubCategoriesData> mainSubCategoriesDataArrayList, SubCategoryFragment subCategoryFragment) {
        this.context = context;
        this.mainSubCategoriesDataArrayList = mainSubCategoriesDataArrayList;
        this.subCategoryFragment = subCategoryFragment;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_sub_categories, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        SubSubCategoriesResponse.MainSubCategoriesData subCategoriesData = mainSubCategoriesDataArrayList.get(position);
        if (subCategoriesData != null ) {


            holder.txtSubCategory.setText(subCategoriesData.getmCatName());

            String thumbnail = Constant.MEDIA_THUMBNAIL_BASE_URL + subCategoriesData.getmCatIconImg();
            Glide.with(context).load(thumbnail).placeholder(R.drawable.richkart).into(holder.imgCategories);

            holder.lySubCategory.setTag(subCategoriesData);
            holder.lySubCategory.setOnClickListener(subCategoryFragment);

        }


    }


    @Override
    public int getItemCount() {
        return mainSubCategoriesDataArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imgCategories;
        TextView txtSubCategory;
        LinearLayout lySubCategory;
        LinearLayout lyTop;


        public ViewHolder(View itemView) {
            super(itemView);


            txtSubCategory = itemView.findViewById(R.id.txtSubCategory);

            imgCategories = itemView.findViewById(R.id.imgCategories);
            lySubCategory = itemView.findViewById(R.id.lySubCategory);
            lyTop = itemView.findViewById(R.id.lyTop);


        }
    }
}
