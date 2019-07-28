package com.ws.design.coco_ecommerce_ui_kit.product_rating_list;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.wolfsoft2.coco_ecommerce_ui_kit.R;
import com.ws.design.coco_ecommerce_ui_kit.product_rating_list.product_rating_response.Ratings;

import java.util.ArrayList;


public class ReviewListAdapter extends RecyclerView.Adapter<ReviewListAdapter.ViewHolder> implements View.OnClickListener {
    private Context context;
    private ArrayList<Ratings> ratingsArrayList;
    private ReviewActivity reviewActivity;


    public ReviewListAdapter(Context context, ArrayList<Ratings> ratingsArrayList, ReviewActivity reviewActivity) {
        this.context = context;
        this.ratingsArrayList = ratingsArrayList;
        this.reviewActivity = reviewActivity;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_reviews, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Ratings ratings = ratingsArrayList.get(position);
        if (ratings != null) {

            holder.rating.setRating(TextUtils.isEmpty(ratings.getmProdRate()) ? 0 : Float.parseFloat(ratings.getmProdRate()));

            holder.txtUserComment.setText(TextUtils.isEmpty(ratings.getmUserComment()) ? "-" :ratings.getmUserComment());

            if(!TextUtils.isEmpty(ratings.getmUserName()) && !TextUtils.isEmpty(ratings.getmRatingTime())){
                holder.txtUserNameDate.setText("By " + ratings.getmUserName() + " on " + ratings.getmRatingTime() );

            }else if(!TextUtils.isEmpty(ratings.getmUserName()) && TextUtils.isEmpty(ratings.getmRatingTime())){
                holder.txtUserNameDate.setText("By " + ratings.getmUserName() );

            }else if(TextUtils.isEmpty(ratings.getmUserName()) && !TextUtils.isEmpty(ratings.getmRatingTime())){
                holder.txtUserNameDate.setText( "on " + ratings.getmRatingTime() );

            }else{
                holder.txtUserNameDate.setText("-");

            }





        }

    }


    @Override
    public int getItemCount() {
        return ratingsArrayList.size();
    }

    @Override
    public void onClick(View view) {

    }

    protected class ViewHolder extends RecyclerView.ViewHolder {

        private TextView txtUserComment;
        private TextView txtUserNameDate;
        private RatingBar rating;






        public ViewHolder(View view) {
            super(view);

            txtUserComment = view.findViewById(R.id.txtUserComment);
            txtUserNameDate = view.findViewById(R.id.txtUserNameDate);
            rating = view.findViewById(R.id.rating);



        }
    }
}
