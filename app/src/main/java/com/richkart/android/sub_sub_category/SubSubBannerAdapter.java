package com.richkart.android.sub_sub_category;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.richkart.android.R;
import com.richkart.android.home.home_response.Banner;
import com.richkart.android.utility.Constant;

import java.util.ArrayList;


public class SubSubBannerAdapter extends RecyclerView.Adapter<SubSubBannerAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Banner> bannerArrayList;
    private SubCategoryFragment subCategoryFragment;


    public SubSubBannerAdapter(Context context, ArrayList<Banner> bannerArrayList, SubCategoryFragment subCategoryFragment) {
        this.context = context;
        this.bannerArrayList = bannerArrayList;
        this.subCategoryFragment = subCategoryFragment;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_home_banner, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Banner banner = bannerArrayList.get(position);
        if (banner != null) {
            String thumbnail = Constant.MEDIA_THUMBNAIL_BASE_URL + banner.getmImageId();
            Glide.with(context).load(thumbnail).placeholder(R.drawable.ic_richkart_logo).dontAnimate().into(holder.imgBanner);



        }

    }


    @Override
    public int getItemCount() {
        return bannerArrayList.size();
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgBanner;




        public ViewHolder(View view) {
            super(view);

            imgBanner = view.findViewById(R.id.imgBanner);



        }
    }
}
