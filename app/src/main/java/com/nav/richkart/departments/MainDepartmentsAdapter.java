package com.nav.richkart.departments;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nav.richkart.R;

import java.util.ArrayList;


/**
 * Created by wolfsoft3 on 30/8/18.
 */

public class MainDepartmentsAdapter extends RecyclerView.Adapter<MainDepartmentsAdapter.ViewHolder> {


    private Context context;
    private ArrayList<CategoriesResponse.MainCategoriesData> categoriesResponseArrayList;
    private DepartmentFragment departmentFragment;


    public MainDepartmentsAdapter(Context context, ArrayList<CategoriesResponse.MainCategoriesData> categoriesResponseArrayList, DepartmentFragment departmentFragment) {
        this.context = context;
        this.categoriesResponseArrayList = categoriesResponseArrayList;
        this.departmentFragment = departmentFragment;
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


            holder.txtCategories.setText(mainCategoriesData.getmCatName().substring(0, 1).toUpperCase() + mainCategoriesData.getmCatName().substring(1).toLowerCase());


            holder.rvSubCategory.setLayoutManager(new GridLayoutManager(context, 3));
            holder.rvSubCategory.setFocusableInTouchMode(false);
            holder.rvSubCategory.setNestedScrollingEnabled(false);

            SubDepartmentAdapter allCategories_adapter = new SubDepartmentAdapter(context, mainCategoriesData.getmSubCategories(), departmentFragment);
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
        LinearLayout lyTop;


        public ViewHolder(View itemView) {
            super(itemView);

            txtCategories = itemView.findViewById(R.id.txtCategories);


            rvSubCategory = itemView.findViewById(R.id.rvSubCategory);

            lyTop = itemView.findViewById(R.id.lyTop);


        }
    }
}
