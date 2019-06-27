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

import Model.HomeGrideModel;


public class HomeGrideAdapter extends RecyclerView.Adapter<HomeGrideAdapter.ViewHolder> {
    private Context context;
    private ArrayList<HomeGrideModel> modelArrayList;

    public HomeGrideAdapter(Context context, ArrayList<HomeGrideModel> modelArrayList) {
        this.context = context;
        this.modelArrayList = modelArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_gride,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.mobi.setImageResource(modelArrayList.get(position).getMobi());
    }

    @Override
    public int getItemCount() {
        return modelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView mobi;
        public ViewHolder(View itemView) {
            super(itemView);

            mobi = itemView.findViewById(R.id.mobi);
        }
    }
}
