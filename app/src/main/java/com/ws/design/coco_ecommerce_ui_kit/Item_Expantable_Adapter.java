package com.ws.design.coco_ecommerce_ui_kit;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;


import com.example.wolfsoft2.coco_ecommerce_ui_kit.R;

import java.util.ArrayList;

import Model.Item_Expantable_Model;


/**
 * Created by wolfsoft3 on 30/8/18.
 */

public class Item_Expantable_Adapter extends RecyclerView.Adapter<Item_Expantable_Adapter.ViewHolder> {


    Context context;
    private ArrayList<Item_Expantable_Model> modelArrayList;


    private int lastSelectedPosition = 0;

    public Item_Expantable_Adapter(Context context, ArrayList<Item_Expantable_Model> modelArrayList) {
        this.context = context;
        this.modelArrayList = modelArrayList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_expantable, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        holder.category_name.setText(modelArrayList.get(position).getCategory());

//        Typeface font = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Medium.ttf");
//
//        holder.btn1.setTypeface(font);
//        holder.btn2.setTypeface(font);
//        holder.btn3.setTypeface(font);
//
//        holder.btn4.setTypeface(font);
//        holder.btn5.setTypeface(font);
//        holder.btn6.setTypeface(font);
//
//        holder.btn7.setTypeface(font);
//        holder.btn8.setTypeface(font);

        holder.btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (holder.btn1.isSelected()) {
                    holder.btn1.setSelected(false);
                    holder.btn1.setChecked(false);
                } else {
                    holder.btn1.setSelected(true);
                    holder.btn1.setChecked(true);
                }
            }
        });


        holder.btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (holder.btn2.isSelected()) {
                    holder.btn2.setSelected(false);
                    holder.btn2.setChecked(false);
                } else {
                    holder.btn2.setSelected(true);
                    holder.btn2.setChecked(true);
                }
            }
        });

        holder.btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (holder.btn3.isSelected()) {
                    holder.btn3.setSelected(false);
                    holder.btn3.setChecked(false);
                } else {
                    holder.btn3.setSelected(true);
                    holder.btn3.setChecked(true);
                }
            }
        });


        holder.btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (holder.btn4.isSelected()) {
                    holder.btn4.setSelected(false);
                    holder.btn4.setChecked(false);
                } else {
                    holder.btn4.setSelected(true);
                    holder.btn4.setChecked(true);
                }
            }
        });


        holder.btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (holder.btn5.isSelected()) {
                    holder.btn5.setSelected(false);
                    holder.btn5.setChecked(false);
                } else {
                    holder.btn5.setSelected(true);
                    holder.btn5.setChecked(true);
                }
            }
        });


        holder.btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (holder.btn6.isSelected()) {
                    holder.btn6.setSelected(false);
                    holder.btn6.setChecked(false);
                } else {
                    holder.btn6.setSelected(true);
                    holder.btn6.setChecked(true);
                }
            }
        });

        holder.btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (holder.btn7.isSelected()) {
                    holder.btn7.setSelected(false);
                    holder.btn7.setChecked(false);
                } else {
                    holder.btn7.setSelected(true);
                    holder.btn7.setChecked(true);
                }
            }
        });


        holder.btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (holder.btn8.isSelected()) {
                    holder.btn8.setSelected(false);
                    holder.btn8.setChecked(false);
                } else {
                    holder.btn8.setSelected(true);
                    holder.btn8.setChecked(true);
                }
            }
        });




        holder.category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (holder.select_brand.getVisibility() == View.VISIBLE) {
                    holder.select_brand.setVisibility(View.GONE);
                    holder.arrow_id.setImageResource(R.drawable.ic_down_arrow);
                } else {
                    holder.select_brand.setVisibility(View.VISIBLE);
                    holder.arrow_id.setImageResource(R.drawable.ic_left_arrow_3);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return modelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        RadioButton btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8;
        TextView category_name;
        LinearLayout category, select_brand;
        ImageView arrow_id;

        public ViewHolder(View itemView) {
            super(itemView);

            category_name = itemView.findViewById(R.id.category_name);

            btn1 = itemView.findViewById(R.id.btn1);
            btn2 = itemView.findViewById(R.id.btn2);
            btn3 = itemView.findViewById(R.id.btn3);
            btn4 = itemView.findViewById(R.id.btn4);
            btn5 = itemView.findViewById(R.id.btn5);
            btn6 = itemView.findViewById(R.id.btn6);
            btn7 = itemView.findViewById(R.id.btn7);
            btn8 = itemView.findViewById(R.id.btn8);

            category = itemView.findViewById(R.id.category);
            select_brand = itemView.findViewById(R.id.select_brand);

            arrow_id = itemView.findViewById(R.id.arrow_id);


        }
    }
}
