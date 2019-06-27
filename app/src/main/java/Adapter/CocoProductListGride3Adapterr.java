package Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import com.example.wolfsoft2.coco_ecommerce_ui_kit.R;

import java.util.ArrayList;

import Model.CocoListGridmodel;

public class CocoProductListGride3Adapterr extends RecyclerView.Adapter<CocoProductListGride3Adapterr.ViewHolder> {
    private Context context;
    private ArrayList<CocoListGridmodel>models;

    public CocoProductListGride3Adapterr(Context context, ArrayList<CocoListGridmodel> models) {
        this.context = context;
        this.models = models;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_coco_product_list_grid3,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.mobi.setImageResource(models.get(position).getMobi());
    }
    @Override
    public int getItemCount() {
        return models.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView mobi;
        public ViewHolder(View itemView) {
            super(itemView);
            mobi = itemView.findViewById(R.id.mobi);
        }
    }
}