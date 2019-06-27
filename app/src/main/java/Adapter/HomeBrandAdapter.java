package Adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.wolfsoft2.coco_ecommerce_ui_kit.R;

import java.util.ArrayList;

import Model.HomeBrandModel;


public class HomeBrandAdapter extends RecyclerView.Adapter<HomeBrandAdapter.ViewHolder> {

    int myPos = 0;

    private Context context;
    private ArrayList<HomeBrandModel>modelArrayList;

    public HomeBrandAdapter(Context context, ArrayList<HomeBrandModel> modelArrayList) {
        this.context = context;
        this.modelArrayList = modelArrayList;
    }

    @NonNull
    @Override
    public HomeBrandAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_brand,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeBrandAdapter.ViewHolder holder, final int position) {


        holder.tv1.setText(modelArrayList.get(position).getTv1());

        holder.image1.setImageResource(modelArrayList.get(position).getImage1());

        if (myPos == position){
            holder.red_rect.setBackgroundResource(R.drawable.circle_red_outline);
        }else {

            holder.red_rect.setBackgroundColor(Color.parseColor("#00000000"));
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
        ImageView image1,red_rect;

        TextView tv1;
        public ViewHolder(View itemView) {
            super(itemView);

            image1 = itemView.findViewById(R.id.image1);
            red_rect = itemView.findViewById(R.id.red_rect);
            tv1 = itemView.findViewById(R.id.tv1);
        }
    }
}
