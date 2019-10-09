package com.nav.richkart.product_by_category;

import android.content.Context;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.nav.richkart.R;
import com.nav.richkart.home.home_response.ProductData;
import com.nav.richkart.sub_sub_category.SubSubCategoryProductListFragment;
import com.nav.richkart.utility.Constant;

import java.util.ArrayList;
import java.util.List;


public class SelectedAttributesAdapter extends RecyclerView.Adapter<SelectedAttributesAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<ProductByCategoryResponse.Attribtues> selectedAttributesArrayList;
    private ProductListByCategoryFragment productListByCategoryFragment;


    public SelectedAttributesAdapter(Context context, ArrayList<ProductByCategoryResponse.Attribtues> selectedAttributesArrayList, ProductListByCategoryFragment productListByCategoryFragment) {
        this.selectedAttributesArrayList = selectedAttributesArrayList;
        this.productListByCategoryFragment = productListByCategoryFragment;
        this.context = context;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_items_selected_attributes, parent, false);


        return new MyViewHolder(itemView);


    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        ProductByCategoryResponse.Attribtues productData = selectedAttributesArrayList.get(position);

        if (productData != null) {

         
            holder.txtAttributeName.setText(!TextUtils.isEmpty(productData.getmAttributeName()) ? Html.fromHtml(productData.getmAttributeName()) : "-");


            holder.lyRemoveAttribute.setTag(productData);
            holder.lyRemoveAttribute.setTag(R.id.lyRemoveAttribute, position);
            holder.lyRemoveAttribute.setOnClickListener(productListByCategoryFragment);

        

        }


    }

    @Override
    public int getItemCount() {
        return selectedAttributesArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {


        TextView txtAttributeName;

        private LinearLayout lyRemoveAttribute;


        public MyViewHolder(View view) {
            super(view);


            lyRemoveAttribute = view.findViewById(R.id.lyRemoveAttribute);
            txtAttributeName = view.findViewById(R.id.txtAttributeName);


        }

    }

}


