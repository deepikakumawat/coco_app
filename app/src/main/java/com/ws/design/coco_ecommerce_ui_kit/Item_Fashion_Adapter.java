package com.ws.design.coco_ecommerce_ui_kit;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.example.wolfsoft2.coco_ecommerce_ui_kit.R;

import java.util.ArrayList;

import Model.Item_Fashion_Model;



/**
 * Created by wolfsoft3 on 30/8/18.
 */

public class Item_Fashion_Adapter extends RecyclerView.Adapter<Item_Fashion_Adapter.ViewHolder> {


    Context context;
    private ArrayList<Item_Fashion_Model> modelArrayList;

    private int myPos =0;

    public Item_Fashion_Adapter(Context context, ArrayList<Item_Fashion_Model> modelArrayList) {
        this.context = context;
        this.modelArrayList = modelArrayList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_fashion, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {


        holder.image.setImageResource(modelArrayList.get(position).getImage());
        holder.text.setText(modelArrayList.get(position).getText());


        if (myPos == position){
            holder.linear1.setBackgroundResource(R.drawable.gradient_circle);
        }else {

            holder.linear1.setBackgroundColor(Color.parseColor("#00000000"));
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myPos = position;
                notifyDataSetChanged();
            }
        });


    }



    @Override
    public int getItemCount() {
        return modelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        LinearLayout linear1;
        TextView text;


        public ViewHolder(View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.image);
            text = itemView.findViewById(R.id.text);
            linear1 = itemView.findViewById(R.id.linear1);


        }
    }
}
