package Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.wolfsoft2.coco_ecommerce_ui_kit.R;

import java.util.ArrayList;

import Model.Cocoproduct5Model;

/**
 * Created by wolfsoft4 on 11/12/18.
 */

public class Coco5RecyAdapter extends RecyclerView.Adapter<Coco5RecyAdapter.ViewHolder> {

    Context context;
    private ArrayList<Cocoproduct5Model>cocoproduct5ModelArrayList;


    public Coco5RecyAdapter(Context context, ArrayList<Cocoproduct5Model> cocoproduct5ModelArrayList) {
        this.context = context;
        this.cocoproduct5ModelArrayList = cocoproduct5ModelArrayList;
    }

    @Override
    public Coco5RecyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cocoproduct5,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(Coco5RecyAdapter.ViewHolder holder, int position) {

        holder.mobile.setImageResource(cocoproduct5ModelArrayList.get(position).getMobile());
        holder.star.setImageResource(cocoproduct5ModelArrayList.get(position).getStar());
        holder.modelname.setText(cocoproduct5ModelArrayList.get(position).getModelname());
        holder.txtrating.setText(cocoproduct5ModelArrayList.get(position).getTxtrating());
        holder.rating.setText(cocoproduct5ModelArrayList.get(position).getRating());

    }

    @Override
    public int getItemCount() {
        return cocoproduct5ModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView mobile,star;
        TextView  modelname,txtrating,rating;



        public ViewHolder(View itemView) {
            super(itemView);

            mobile=itemView.findViewById(R.id.mobile);
            star=itemView.findViewById(R.id.star);
            modelname=itemView.findViewById(R.id.modelname);
            txtrating=itemView.findViewById(R.id.txtrating);
            rating=itemView.findViewById(R.id.rating);

        }
    }
}
