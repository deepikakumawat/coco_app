package Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.wolfsoft2.coco_ecommerce_ui_kit.R;

import java.util.ArrayList;

import Model.Cocoproductlistgrid9Model;

/**
 * Created by wolfsoft4 on 11/12/18.
 */

public class Cocoproductlistgrid9Adapter extends RecyclerView.Adapter<Cocoproductlistgrid9Adapter.ViewHolder> {

    Context context;
    private ArrayList<Cocoproductlistgrid9Model> cocoproductlistgrid9ModelArrayList;


    public Cocoproductlistgrid9Adapter(Context context, ArrayList<Cocoproductlistgrid9Model> cocoproductlistgrid9ModelArrayList) {
        this.context = context;
        this.cocoproductlistgrid9ModelArrayList = cocoproductlistgrid9ModelArrayList;
    }

    @NonNull
    @Override
    public Cocoproductlistgrid9Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cocoproductlistgrid9,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Cocoproductlistgrid9Adapter.ViewHolder holder, int position) {

        holder.apple.setText(cocoproductlistgrid9ModelArrayList.get(position).getApple());

    }

    @Override
    public int getItemCount() {
        return cocoproductlistgrid9ModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {



        TextView apple;

        public ViewHolder(View itemView) {
            super(itemView);


            apple=itemView.findViewById(R.id.apple);


        }
    }
}
