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

import Model.ReviewModel;

/**
 * Created by wolfsoft4 on 12/12/18.
 */

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ViewHolder> {
    Context context;
    private ArrayList<ReviewModel> reviewModelArrayList;

    public ReviewAdapter(Context context, ArrayList<ReviewModel> reviewModelArrayList) {
        this.context = context;
        this.reviewModelArrayList = reviewModelArrayList;
    }

    @NonNull
    @Override
    public ReviewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_reviews,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewAdapter.ViewHolder holder, int position) {

        holder.great.setText(reviewModelArrayList.get(position).getGreat());

    }

    @Override
    public int getItemCount() {
        return reviewModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView great;

        public ViewHolder(View itemView) {
            super(itemView);


        }
    }
}
