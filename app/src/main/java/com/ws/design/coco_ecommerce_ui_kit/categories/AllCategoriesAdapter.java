package com.ws.design.coco_ecommerce_ui_kit.categories;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.example.wolfsoft2.coco_ecommerce_ui_kit.R;

import java.util.ArrayList;


/**
 * Created by wolfsoft3 on 30/8/18.
 */

public class AllCategoriesAdapter extends RecyclerView.Adapter<AllCategoriesAdapter.ViewHolder> {


    private Context context;
    private ArrayList<CategoriesResponse.MainCategoriesData> categoriesResponseArrayList;
    private CategoryFragment categoryFragment;

    private int myPos = 0;

    public AllCategoriesAdapter(Context context, ArrayList<CategoriesResponse.MainCategoriesData> categoriesResponseArrayList, CategoryFragment categoryFragment) {
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

        CategoriesResponse.MainCategoriesData mainCategoriesData = categoriesResponseArrayList.get(position);
        if (mainCategoriesData != null) {
            holder.text.setText(mainCategoriesData.getmCatName());

        }


      /*  String thumbnail = Constant.MEDIA_THUMBNAIL_BASE_URL + categoriesResponse.getmData().getmMainCategories().getmCatIconImg();
        Glide.with(context).load(thumbnail).placeholder(R.drawable.richkart).into(holder.imgCategories);

*/




    }


    @Override
    public int getItemCount() {
        return categoriesResponseArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imgCategories;
        LinearLayout linear1;
        TextView text;


        public ViewHolder(View itemView) {
            super(itemView);

            imgCategories = itemView.findViewById(R.id.imgCategories);
            text = itemView.findViewById(R.id.text);
            linear1 = itemView.findViewById(R.id.linear1);


        }
    }
}
