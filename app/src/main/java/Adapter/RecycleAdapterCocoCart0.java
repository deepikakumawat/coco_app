package Adapter;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.wolfsoft2.coco_ecommerce_ui_kit.R;

import java.util.List;

import Model.CocoCartModel1;

public class RecycleAdapterCocoCart0 extends RecyclerView.Adapter<RecycleAdapterCocoCart0.MyViewHolder> {
    Context context;


    private List<CocoCartModel1> moviesList;

    int myPos = 0;


    public class MyViewHolder extends RecyclerView.ViewHolder {


        TextView phoneName,rupees,display_inc_dec;
        ImageView phoneImage;
        LinearLayout viewline,increment,decrement;



        public MyViewHolder(View view) {
            super(view);

            phoneName = (TextView) view.findViewById(R.id.phoneName);
            rupees = (TextView) view.findViewById(R.id.amount);
            phoneImage=(ImageView)view.findViewById(R.id.phoneImage);
            viewline=(LinearLayout) view.findViewById(R.id.viewline);

            increment=(LinearLayout) view.findViewById(R.id.increment);
            decrement=(LinearLayout) view.findViewById(R.id.decrement);
            display_inc_dec=(TextView) view.findViewById(R.id.display_inc_dec);


        }

    }


    public RecycleAdapterCocoCart0(Context context, List<CocoCartModel1> moviesList) {
        this.moviesList = moviesList;
        this.context = context;
    }

    @Override
    public RecycleAdapterCocoCart0.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_phone_recycler, parent, false);


        return new RecycleAdapterCocoCart0.MyViewHolder(itemView);


    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(final RecycleAdapterCocoCart0.MyViewHolder holder, final int position) {
        CocoCartModel1 movie = moviesList.get(position);
        holder.phoneName.setText(movie.getPhoneName());
        holder.rupees.setText(movie.getRupees());
        holder.phoneImage.setImageResource(movie.getPhoneImage());


        holder.increment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int count= Integer.parseInt(String.valueOf(holder.display_inc_dec.getText()));
                count++;
                holder.display_inc_dec.setText(String.valueOf(count));
            }

        });

        holder.decrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int count= Integer.parseInt(String.valueOf(holder.display_inc_dec.getText()));
                if (count > 1)
                    count--;
                holder.display_inc_dec.setText(String.valueOf(count));


            }
        });

        if(position==1){

            holder.viewline.setVisibility(View.GONE);
        }else {
            holder.viewline.setVisibility(View.VISIBLE);
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
        return moviesList.size();
    }



}



