package com.richkart.android;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.richkart.android.R;

import java.util.List;


import com.richkart.android.Model.NavigationModelClass;
import com.richkart.android.fragment.CustomItemClickListener;


/**
 * Created by Rp on 6/14/2016.
 */
public class RecycleAdapteNavigation extends RecyclerView.Adapter<RecycleAdapteNavigation.MyViewHolder> {
    Context context;


    private List<NavigationModelClass> moviesList;
    CustomItemClickListener listener;

    int myPos = 0;


    public class MyViewHolder extends RecyclerView.ViewHolder {


        TextView title;
        ImageView image;
        LinearLayout linear;


        public MyViewHolder(View view) {
            super(view);

            title = (TextView) view.findViewById(R.id.title);

            linear = (LinearLayout) view.findViewById(R.id.linear);

        }

    }


    public RecycleAdapteNavigation(Context context, List<NavigationModelClass> moviesList, CustomItemClickListener customItemClickListener) {
        this.moviesList = moviesList;
        this.context = context;
        this.listener=customItemClickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_navigation_list, parent, false);

        final MyViewHolder myViewHolder= new MyViewHolder(itemView);
        itemView.setOnClickListener(v -> listener.onItemClick(v, myViewHolder.getAdapterPosition()));

        return myViewHolder;


    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(final MyViewHolder holder,final int position) {
        NavigationModelClass movie = moviesList.get(position);
        holder.title.setText(movie.getTitlel());


        if (myPos == position){
            holder.title.setTextColor(Color.parseColor("#000000"));

            holder.linear.setBackgroundResource(R.drawable.navigation_rect);
        }else {

            holder.title.setTextColor(Color.parseColor("#000000"));
            holder.linear.setBackgroundColor(Color.parseColor("#00000000"));
        }


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myPos = position;
                listener.onItemClick(view, holder.getAdapterPosition());
                notifyDataSetChanged();
            }
        });


    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }



}


