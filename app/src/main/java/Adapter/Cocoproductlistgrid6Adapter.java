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

import Model.Cocoproductlistgrid6Model;

/**
 * Created by wolfsoft4 on 11/12/18.
 */

public class Cocoproductlistgrid6Adapter extends RecyclerView.Adapter<Cocoproductlistgrid6Adapter.ViewHolder> {

   Context context;
   private ArrayList<Cocoproductlistgrid6Model> cocoproductlistgrid6ModelArrayList;


    public Cocoproductlistgrid6Adapter(Context context, ArrayList<Cocoproductlistgrid6Model> cocoproductlistgrid6ModelArrayList) {
        this.context = context;
        this.cocoproductlistgrid6ModelArrayList = cocoproductlistgrid6ModelArrayList;
    }

    @NonNull
    @Override
    public Cocoproductlistgrid6Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cocoproductlistgrid6,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Cocoproductlistgrid6Adapter.ViewHolder holder, int position) {

        holder.company.setText(cocoproductlistgrid6ModelArrayList.get(position).getCompany());


    }

    @Override
    public int getItemCount() {
        return cocoproductlistgrid6ModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView company;

        public ViewHolder(View itemView) {
            super(itemView);

            company=itemView.findViewById(R.id.company);

        }
    }
}
