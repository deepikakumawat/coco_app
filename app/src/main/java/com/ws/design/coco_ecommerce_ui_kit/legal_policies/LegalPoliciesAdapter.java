package com.ws.design.coco_ecommerce_ui_kit.legal_policies;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.wolfsoft2.coco_ecommerce_ui_kit.R;
import com.ws.design.coco_ecommerce_ui_kit.my_wishlist.MyWishListResponse;
import com.ws.design.coco_ecommerce_ui_kit.my_wishlist.MyWishlistFragment;
import com.ws.design.coco_ecommerce_ui_kit.utility.Constant;

import java.util.ArrayList;


public class LegalPoliciesAdapter extends RecyclerView.Adapter<LegalPoliciesAdapter.ViewHolder> {
    private Context context;
    private String[] legalPoliciesArray;
    private LegalPoliciesFragment legalPoliciesFragment;


    public LegalPoliciesAdapter(Context context, String[] legalPoliciesArray, LegalPoliciesFragment legalPoliciesFragment) {
        this.context = context;
        this.legalPoliciesArray = legalPoliciesArray;
        this.legalPoliciesFragment = legalPoliciesFragment;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_legal_policies, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        String legal = legalPoliciesArray[position];
        holder.txtLegalPolicies.setText(legal);

        holder.txtLegalPolicies.setTag(position);
        holder.txtLegalPolicies.setTag(R.id.txtLegalPolicies,holder.txtLegalPolicies);
        holder.txtLegalPolicies.setOnClickListener(legalPoliciesFragment);

    }


    @Override
    public int getItemCount() {
        return legalPoliciesArray.length;
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {

        private TextView txtLegalPolicies;




        public ViewHolder(View view) {
            super(view);

            txtLegalPolicies = view.findViewById(R.id.txtLegalPolicies);


        }
    }
}
