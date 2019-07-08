package com.ws.design.coco_ecommerce_ui_kit.home;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.wolfsoft2.coco_ecommerce_ui_kit.R;
import com.ws.design.coco_ecommerce_ui_kit.home.home_response.Banner;
import com.ws.design.coco_ecommerce_ui_kit.home.home_response.Categories;
import com.ws.design.coco_ecommerce_ui_kit.utility.Constant;

import java.util.ArrayList;


public class HomeBannerAdapter extends RecyclerView.Adapter<HomeBannerAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Banner> bannerArrayList;
    private HomeActivity homeActivity;


    public HomeBannerAdapter(Context context, ArrayList<Banner> bannerArrayList, HomeActivity homeActivity) {
        this.context = context;
        this.bannerArrayList = bannerArrayList;
        this.homeActivity = homeActivity;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_home_banner, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Banner banner = bannerArrayList.get(position);
        if (banner != null) {
            String thumbnail = Constant.THUMBNAIL_BASE_URL + banner.getmImageId();
            Glide.with(context).load(thumbnail).placeholder(R.drawable.banner).dontAnimate().into(holder.imgBanner);



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
