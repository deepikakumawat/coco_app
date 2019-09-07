package com.nav.richkart.home;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nav.richkart.R;
import com.nav.richkart.home.home_response.Categories;

import java.util.ArrayList;


public class HomeCategoryAdapter extends RecyclerView.Adapter<HomeCategoryAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Categories> categoriesArrayList;
    private HomeFragment homeFragment;


    public HomeCategoryAdapter(Context context, ArrayList<Categories> categoriesArrayList, HomeFragment homeFragment) {
        this.context = context;
        this.categoriesArrayList = categoriesArrayList;
        this.homeFragment = homeFragment;

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
            holder.txtCategories.setOnClickListener(homeFragment);
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
