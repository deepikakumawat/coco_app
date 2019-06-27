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

import Model.Cocoproductlistgrid7Model;



/**
 * Created by wolfsoft4 on 11/12/18.
 */

public class Cocoproductlistgrid7Adapter extends RecyclerView.Adapter<Cocoproductlistgrid7Adapter.ViewHolder> {

    Context context;
    private ArrayList<Cocoproductlistgrid7Model> cocoproductlistgrid7ModelArrayList;


    public Cocoproductlistgrid7Adapter(Context context, ArrayList<Cocoproductlistgrid7Model> cocoproductlistgrid7ModelArrayList) {
        this.context = context;
        this.cocoproductlistgrid7ModelArrayList = cocoproductlistgrid7ModelArrayList;
    }

    @NonNull
    @Override
    public Cocoproductlistgrid7Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cocoproductlistgrid7,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Cocoproductlistgrid7Adapter.ViewHolder holder, int position) {

        holder.modelname.setText(cocoproductlistgrid7ModelArrayList.get(position).getModelname());

    }

    @Override
    public int getItemCount() {
        return cocoproductlistgrid7ModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView modelname;

        public ViewHolder(View itemView) {
            super(itemView);

            modelname=itemView.findViewById(R.id.modelname);


        }
    }
}
